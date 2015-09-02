
CREATE OR REPLACE FUNCTION sec_p_create_bean_method(v_class_id integer, v_name character varying, v_description character varying)
RETURNS void AS '
DECLARE
   a_method_id INTEGER;
begin
  a_method_id = nextval(''sys_method_id_gen'');
  INSERT INTO sys_method (id, class_id, corba_name, description)
  VALUES (a_method_id, v_class_id, v_name, v_description);

  INSERT INTO sec_method_access (id, group_id, method_id, permission)
  VALUES (nextval(''sec_method_access_id_gen''), 1, a_method_id, true);
END;'
LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION SEC_CREATE_METHODS (
    v_class_id IN INTEGER)
RETURNS void AS '
begin
  EXECUTE sec_p_create_bean_method(v_class_id, ''load'', ''Просмотр'');
  EXECUTE sec_p_create_bean_method(v_class_id, ''browse'', ''Просмотр списка'');
  EXECUTE sec_p_create_bean_method(v_class_id, ''create'', ''Создание'');
  EXECUTE sec_p_create_bean_method(v_class_id, ''erase'', ''Удаление'');
  EXECUTE sec_p_create_bean_method(v_class_id, ''store'', ''Изменение'');
END;'
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sys_create_class(v_module character varying, v_bean_name character varying, v_description character varying)
RETURNS void AS '
DECLARE
  a_class_id INTEGER;
BEGIN
  SELECT c.id INTO a_class_id FROM sys_class c WHERE c.bean_name = v_bean_name;
  IF FOUND THEN
    UPDATE sys_class SET description = v_description WHERE id = a_class_id;
  ELSE
    a_class_id = nextval(''sys_class_id_gen'');
    INSERT INTO sys_class (id, module_id, bean_name, description) VALUES (a_class_id, (select m.id from sys_module m where m.name = v_module), v_bean_name, v_description);
  END IF;
  EXECUTE SEC_CREATE_METHODS(a_class_id);
END;'
LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION sys_create_class_ex(v_module character varying, v_bean_name character varying, v_bean_impl character varying, v_client_id integer, v_app_layer smallint, v_impl_kind smallint, v_description character varying)
RETURNS void AS '
DECLARE
  a_class_id INTEGER;
  a_class_impl_id INTEGER;
BEGIN
  EXECUTE SYS_CREATE_CLASS(v_module, v_bean_name, v_description);
  SELECT c.id INTO a_class_id FROM sys_class c WHERE c.bean_name = v_bean_name;
  SELECT ci.ID INTO a_class_impl_id FROM sys_class_impl ci WHERE ci.client_id = v_client_id AND ci.app_layer = v_app_layer AND ci.class_id = a_class_id;
  IF FOUND THEN
    UPDATE sys_class_impl SET
      kind = v_impl_kind,
      name = v_bean_impl
    WHERE id = a_class_impl_id;
  ELSE
    INSERT INTO sys_class_impl (id, client_id, app_layer, class_id, kind, name)
    VALUES (nextval(''sys_class_impl_id_gen''), v_client_id, v_app_layer, a_class_id, v_impl_kind, v_bean_impl);
  END IF;
END;'
LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION bai_create_or_update_repo(v_client_id integer, v_code character varying, v_name character varying, v_impl_name character varying)
RETURNS void AS '
DECLARE
  a_bai_id integer;
  a_folder_id integer;
BEGIN
  select ar.id into a_bai_id from alg_repository ar where ar.client_id = v_client_id and ar.code = v_code;
  if not found then
    select f.id into a_folder_id from folder f where f.foldertype = 9500 and f.parent_id is null;

    insert into alg_repository (id, folder_id, code, name, engine, impl_name, client_id, sys_version)
    values (nextval(''alg_repository_id_gen''), a_folder_id, v_code, v_name, 0, v_impl_name, v_client_id, now());
  else
    update alg_repository set
      name = v_name,
      impl_name = v_impl_name,
      engine = 0,
      sys_version = now()
    where id = a_bai_id;
  end if;
END;'
LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION rpt_create_or_update_report(
v_client_id integer, v_code character varying, v_name character varying, v_comment character varying,
v_askparams boolean, v_direct_print boolean, v_priority integer, v_template bytea, v_params_form_name varchar,
v_output_format varchar, v_bean_name varchar)
RETURNS void AS '
DECLARE
a_rpt_main_id integer;
a_sys_class_id integer;
a_rpt_class_link_id integer;
BEGIN
  /* create or update report description, used by build scripts */
  /* try find report by code */
  select rm.id into a_rpt_main_id from rpt_main rm where rm.client_id = v_client_id and rm.code = v_code;
  if not found then
    /* not found */
    a_rpt_main_id = nextval(''rpt_main_id_gen'');
    /* create report */
    insert into rpt_main (ID, CLIENT_ID, CODE, RPTNAME, ASKPARAMS, DIRECT_PRINT, COMMENT, INVOKE_FROM_EDIT, PRIORITY, SYS_VERSION, TEMPLATE, PARAMS_FORM_NAME, OUTPUT_FORMAT)
    values (a_rpt_main_id, v_client_id, v_code, v_name, v_askparams, v_direct_print, v_comment, false, v_priority, now(), v_template, v_params_form_name, v_output_format);
    /* create admin rights */
    insert into rpt_rights (ID, RPT_ID, GROUP_ID, RIGHTS, CLIENT_ID)
    values (nextval(''rpt_rights_id_gen''), a_rpt_main_id, 1, 1, v_client_id);
  else
    /* update report */
    update rpt_main set
      RPTNAME = v_name,
      ASKPARAMS = v_askparams,
      DIRECT_PRINT = v_direct_print,
      COMMENT = v_comment,
      PRIORITY = v_priority,
      SYS_VERSION = now(),
      TEMPLATE = v_template,
      PARAMS_FORM_NAME = v_params_form_name,
      OUTPUT_FORMAT = v_output_format
    where id = a_rpt_main_id;
  end if;

  /* link with business bean */
  if (v_bean_name is not null) then
    select sc.id into a_sys_class_id from sys_class sc where sc.bean_name = upper(v_bean_name);
    if found then
      select cl.id into a_rpt_class_link_id from rpt_class_link cl where cl.rpt_id = a_rpt_main_id and cl.class_id = a_sys_class_id and cl.client_id = v_client_id;
      if not found then
        insert into rpt_class_link (id, rpt_id, class_id, client_id)
        values (nextval(''rpt_class_link_id_gen''), a_rpt_main_id, a_sys_class_id, v_client_id);
      end if;
    end if;
  end if;
END;'
LANGUAGE 'plpgsql';
