CREATE TRIGGER ABH_RECALC_NEXT_HIST_AU FOR ACCBATCHHISTORY
ACTIVE AFTER UPDATE POSITION 0
as
  declare variable ans integer;
  declare variable next_id integer;
  declare variable accbatchquan numeric(15,3);
  declare variable quantity numeric(15,3);
  declare variable accbatchcostnat numeric(15,3);
  declare variable accbatchcostcur numeric(15,3);
  declare variable newcostnat numeric(15,3);
  declare variable newcostcur numeric(15,3);
  declare variable esquan numeric(15,3);
  declare variable essummanat numeric(15,4);
  declare variable essummacur numeric(15,4);
  declare variable nextbegindate timestamp;
begin
  /* if change state and not last */
  if (((new.quantity <> old.quantity) or (new.costnat <> old.costnat) or (new.costcur <> old.costcur))and(old.enddate is not null)) then begin
    /* get begin quan */
    select a.beginquan, a.incomecostnat, a.incomecostcur from accbatch a
    where (a.id = old.accbatch_id)
    into :accbatchquan, :accbatchcostnat, :accbatchcostcur;
    /* find next accbatchhistory */
    ans=DM('next');
    select a.id, a.begindate from accbatchhistory a
    where (a.accbatch_id = old.accbatch_id)and
      (a.begindate = (select min(a1.begindate) from accbatchhistory a1 where (a1.accbatch_id = old.accbatch_id) and (a1.begindate>=new.enddate)))
    into :next_id, :nextbegindate;
    /* get revalue sum */
    ans=wi('next_id',next_id);
    select e.quantity, e.summanat, e.summacur from economicspec e
    where (((e.accbatchhistorydb_id = :next_id) or (e.accbatchhistorykt_id = :next_id))and
      ((e.quantity=0)or((e.summanat=0)and(e.summacur=0))))
    into :esquan, :essummanat, :essummacur;
    /* calculate out quantity up to income date */
    ans=dm('summa');
    select sum(a.quantity) from accbatchhistory a
    where (a.accbatch_id = old.accbatch_id)and(a.begindate <= :nextbegindate)and(a.enddate <= :nextbegindate)
    into :quantity;
    /* calculate new cost */
    if (quantity is null) then quantity = 0;
    quantity = accbatchquan - quantity;
    newcostnat = (new.costnat*quantity + essummanat)/quantity;
    newcostcur = (new.costcur*quantity + essummacur)/quantity;
    update accbatchhistory
    set
      costnat = :newcostnat,
      costcur = :newcostcur
    where id = :next_id;
  end
end
^

CREATE TRIGGER ABH_RECALC_OUT_COST_AD FOR ACCBATCHHISTORY
ACTIVE AFTER DELETE POSITION 0
as
declare variable ans integer;
  declare variable prev_id integer;
begin
  /* if history is revalue */
  if (old.actiontype = 1) then begin
    /* find prev accbatchhistory */
    select a.id from accbatchhistory a
    where (a.id = (select max(a1.id) from accbatchhistory a1 where (a1.accbatch_id = old.accbatch_id) and (a1.enddate=old.begindate)))
    into :prev_id;
    ans=wi('prev',prev_id);
    update accbatchhistory
    set
      enddate = old.enddate,
      quantity = quantity + old.quantity
    where id = :prev_id;
  end
end
^

CREATE TRIGGER ABH_SET_NEW_ES_BATCHHIST_BD FOR ACCBATCHHISTORY
ACTIVE BEFORE DELETE POSITION 0
as
  declare variable ans integer;
  declare variable prev_id integer;
begin
  /* if history is revalue */
  if (old.actiontype = 1) then begin
    /* find prev accbatchhistory */
    select a.id from accbatchhistory a
    where (a.id = (select max(a1.id) from accbatchhistory a1 where (a1.accbatch_id = old.accbatch_id) and (a1.enddate=old.begindate)))
    into :prev_id;
    ans=wi('prev',prev_id);
    update economicspec
    set
      accbatchhistorydb_id = :prev_id
    where accbatchhistorydb_id = old.id;
    update economicspec
    set
      accbatchhistorykt_id = :prev_id
    where accbatchhistorykt_id = old.id;
  end
end
^

CREATE TRIGGER ACCPLAN_POST_EVENT_AI FOR ACCPLAN
ACTIVE AFTER INSERT POSITION 0
as
begin
  post_event 'EVENT_ACCPLAN_UPDATE'; 
end
^

CREATE TRIGGER ACCPLAN_SET_ISANL_BI FOR ACCPLAN
ACTIVE BEFORE INSERT POSITION 0
as
declare variable anlcount integer;
begin
  select count(*) from anlplan where accplan_id = new.id
  into anlcount;
  if (anlcount > 0) then new.IsAnl = 1; else new.IsAnl = 0;
end
^

CREATE TRIGGER ACCPLAN_SET_ISANL_BU FOR ACCPLAN
ACTIVE BEFORE UPDATE POSITION 0
as
declare variable anlcount integer;
begin
  select count(*) from anlplan where accplan_id = new.id
  into anlcount;
  if (anlcount > 0) then new.IsAnl = 1; else new.IsAnl = 0;
end
^

CREATE TRIGGER ACC_AMRATE_ACTMONTH_BI FOR ACC_AMRATE
ACTIVE BEFORE INSERT POSITION 0
as
begin
  if (exists(select * from acc_amrate r 
             where (r.amcode_id = new.amcode_id) and
                   (r.actmonth = new.actmonth))) then
    exception e_actmonth_exists;
end
^

CREATE TRIGGER ACC_AMRATE_ACTMONTH_BU FOR ACC_AMRATE
ACTIVE BEFORE UPDATE POSITION 0
as
begin
  if ((new.actmonth <> old.actmonth) and 
      (exists(select * from acc_amrate r 
             where (r.amcode_id = new.amcode_id) and
                   (r.actmonth = new.actmonth)))) then
    exception e_actmonth_exists;
end
^

CREATE TRIGGER CONTRACTORCARDHIST_AI FOR CONTRACTORCARDHIST
ACTIVE AFTER INSERT POSITION 0
as
begin
  if (new.kind = 0) then begin
    update contractorcard c
      set c.totalincome = c.totalincome + new.sumnat
      where c.id = new.contractorcard_id;
  end
  else begin
    update contractorcard c
      set c.totalexpenses = c.totalexpenses + new.sumnat
      where c.id = new.contractorcard_id;
  end
end
^

CREATE TRIGGER CONTRACTORCARDHIST_BD FOR CONTRACTORCARDHIST
ACTIVE BEFORE DELETE POSITION 0
as
begin
  if (old.kind = 0) then begin
    update contractorcard c
      set c.totalincome = c.totalincome - old.sumnat
      where c.id = old.contractorcard_id;
  end
  else begin
    update contractorcard c
      set c.totalexpenses = c.totalexpenses - old.sumnat
      where c.id = old.contractorcard_id;
  end
end
^

CREATE TRIGGER CONTRACTORCARDPLAN_AI FOR CONTRACTORCARDPLAN
ACTIVE AFTER INSERT POSITION 0
as
begin
  if (new.kind = 0) then begin
    if (new.is_set = 1) then
      update contractorcard c
      set c.planincome = c.planincome + new.sumnat
      where c.id = new.contractorcard_id;
    else
      update contractorcard c
      set c.planincome = c.planincome - new.sumnat
      where c.id = new.contractorcard_id;
  end
  else begin
    if (new.is_set = 1) then
      update contractorcard c
      set c.planexpenses = c.planexpenses + new.sumnat
      where c.id = new.contractorcard_id;
    else
      update contractorcard c
      set c.planexpenses = c.planexpenses - new.sumnat
      where c.id = new.contractorcard_id;
  end
end
^

CREATE TRIGGER CONTRACTORCARDPLAN_BD FOR CONTRACTORCARDPLAN
ACTIVE BEFORE DELETE POSITION 0
as
begin
  if (old.kind = 0) then begin
    if (old.is_set = 1) then
      update contractorcard c
      set c.planincome = c.planincome - old.sumnat
      where c.id = old.contractorcard_id;
    else
      update contractorcard c
      set c.planincome = c.planincome + old.sumnat
      where c.id = old.contractorcard_id;
  end
  else begin
    if (old.is_set = 1) then
      update contractorcard c
      set c.planexpenses = c.planexpenses - old.sumnat
      where c.id = old.contractorcard_id;
    else
      update contractorcard c
      set c.planexpenses = c.planexpenses + old.sumnat
      where c.id = old.contractorcard_id;
  end
end
^

CREATE TRIGGER DH_CALC_TOTAL_SUM_BU FOR DOCHEAD
ACTIVE BEFORE UPDATE POSITION 0
as
begin
  /* calculation total values */
  select sum(s.weight), sum(s.volume)/*, sum(s.summa)*/ from docspec s
  where (s.dochead_id = old.id)
  into new.weight, new.volume/*, new.summacur*/;
  /*new.summanat = new.summacur * new.curcource;*/
end
^

CREATE TRIGGER DH_UPDATE_SPEC_BESTBEFORE_AU FOR DOCHEAD
ACTIVE AFTER UPDATE POSITION 0
as
declare variable spec_id integer;
declare variable shelflife numeric(15,3);
declare variable shelflife_meas integer;
declare variable calc_kind integer;
declare variable prod_date timestamp;
declare variable bestbefore timestamp;
begin
  for 
    select s.id, s.shelflife, s.shelflife_meas, s.productiondate, c.expdate_calc_kind
    from docspec s, catalog c
    where (s.catalog_id = c.id) and
          (s.dochead_id = new.id)
    into :spec_id, :shelflife, :shelflife_meas, :prod_date, :calc_kind
  do begin
    if ((shelflife <> 0) and (shelflife_meas <> 0)) then begin
      bestbefore = null;
      
      if (calc_kind = 0/*by production date*/) then 
        execute procedure f_add_period_to_date(:prod_date, :shelflife, :shelflife_meas)
          returning_values :bestbefore;
      
      else if (calc_kind = 1/*by document date*/) then 
        execute procedure f_add_period_to_date(new.docdate, :shelflife, :shelflife_meas)
          returning_values :bestbefore;
      
      if (bestbefore is not null) then
        update docspec s set s.bestbefore = :bestbefore where s.id = :spec_id;
    end
  end
end
^

CREATE TRIGGER DISABLE_CATALOGFOLDER FOR CATALOGFOLDER
ACTIVE AFTER DELETE POSITION 0
as
begin
delete from folder_rights
where (folder_id = old.id) and (folderpart = 1);
end
^

CREATE TRIGGER DISABLE_FOLDER FOR FOLDER
ACTIVE AFTER DELETE POSITION 0
as
begin
delete from folder_rights
where (folder_id = old.id) and (folderpart = 0);
end
^

CREATE TRIGGER DISABLE_ORGUNIT FOR ORGUNIT
ACTIVE AFTER DELETE POSITION 0
as
begin
  delete from folder_rights
  where (folder_id = old.id) and (folderpart = 4);
end
^

CREATE TRIGGER DISABLE_PERSONNELGROUP FOR PREF_PERSONNEL_GROUP
ACTIVE AFTER DELETE POSITION 0
as
begin
  delete from folder_rights
  where (folder_id = old.id) and (folderpart = 5);
end
^

CREATE TRIGGER DISABLE_PRICELISTFOLDER FOR PRICELISTFOLDER
ACTIVE AFTER DELETE POSITION 0
as
begin
  delete from folder_rights
  where (folder_id = old.id) and (folderpart = 2);
end
^

CREATE TRIGGER DOCHEAD_TRG_UID FOR DOCHEAD
ACTIVE BEFORE INSERT POSITION 0
as begin
   if (new.UNID is null) then new.UNID = CreateUID();
 end
^

CREATE TRIGGER DOCPROCESSTAGE_BD FOR DOCPROCESSSTAGE
ACTIVE BEFORE DELETE POSITION 0
as
begin
  if (exists (select * from docaction da where da.stage_id=old.id)) then
    exception E_CANNOT_DEL_DOCPROCESSTAGE;
end
^

CREATE TRIGGER DOCSPEC_TRG_UID FOR DOCSPEC
ACTIVE BEFORE INSERT POSITION 0
as begin
   if (new.UNID is null) then new.UNID = CreateUID();
 end
^

CREATE TRIGGER DPSR_SET_ID_BI FOR DOCPROCESSSTAGE_RIGHTS
ACTIVE BEFORE INSERT POSITION 0
as
begin
  new.id = gen_id(DOCPROCESSSTAGERIGHTS_ID_GEN, 1);
end
^

CREATE TRIGGER DSS_GEN_ID_BI FOR DOCSPECSTATE
ACTIVE BEFORE INSERT POSITION 0
as
begin
  new.id = gen_id(docspecstate_id_gen,1);
end
^

CREATE TRIGGER DS_DELETE_TAXSUM_IN_BUF_AU FOR DOCSPEC
ACTIVE AFTER UPDATE POSITION 0
as
begin
  delete from taxsum_buf where (specification_id = old.id);
end
^

CREATE TRIGGER EO_UPDATE_REMANTS_BU FOR ECONOMICOPER
ACTIVE BEFORE UPDATE POSITION 0
as
declare variable ans integer;
/*declare variable accid integer;*/
declare variable period_id integer;
begin
  /* steady in locking */
  if (old.insertsign = 1) then begin
    /* check period */
    execute procedure f_find_period(new.keepdate)
    returning_values :period_id;
    execute procedure p_check_period(:period_id);
    ans = DM('insert');
    execute procedure acc_update_all_remn(old.id, new.keepdate, 0,
      new.from_id, new.to_id,
      new.docbasetype,new.docbasenumber,new.docbasedate,
      new.doctype,new.docnumber,new.docdate,
      new.contracttype,new.contractnumber,new.contractdate,new.specmark);
  end
  /* copy economic oper and spec in buffer while lock */
  if (new.insertsign = 2) then begin
    /* check period */
    execute procedure f_find_period(old.keepdate)
    returning_values :period_id;
    execute procedure p_check_period(:period_id);
    ans = DM('lock');
  end
  /* commit update economic oper and spec */
  if (old.insertsign = 2) then begin
    ans = DM('update');
    /* insert new turn */
    execute procedure acc_update_all_remn(old.id, new.keepdate, 0,
      new.from_id, new.to_id,
      new.docbasetype,new.docbasenumber,new.docbasedate,
      new.doctype,new.docnumber,new.docdate,
      new.contracttype,new.contractnumber,new.contractdate,new.specmark);
  end
end
^

CREATE TRIGGER EO_UPDATE_REMNTURN_ONDELETE FOR ECONOMICOPER
ACTIVE BEFORE DELETE POSITION 0
as
declare variable summa numeric(15,4);
declare variable period_id integer;
begin
  if (old.comment is not null) then begin
    execute procedure f_find_period old.keepdate
    returning_values :period_id;
    execute procedure p_check_period :period_id;
    /* delete turn from register */
    if (old.insertsign <> 1) then
      execute procedure acc_update_all_remn( old.id, old.keepdate, 1,
        old.from_id, old.to_id,
        old.docbasetype,old.docbasenumber,old.docbasedate,
        old.doctype,old.docnumber,old.docdate,
        old.contracttype,old.contractnumber,old.contractdate,old.specmark);
  end
end
^

CREATE TRIGGER EO_UPDATE_SUMM_BU FOR ECONOMICOPER
ACTIVE BEFORE UPDATE POSITION 1
as
begin
  select sum(summanat) from economicspec
  where economicoper_id = old.id
  into new.summa;
end
^

CREATE TRIGGER ES_CHECK_PERMISACC_BI FOR ECONOMICSPEC
ACTIVE BEFORE INSERT POSITION 0
as
begin
  execute procedure P_CHECK_PERMISSIBLEACC
    new.accdb_id, new.anldb1_id, new.anldb2_id, new.anldb3_id, new.anldb4_id, new.anldb5_id,
    new.acckt_id, new.anlkt1_id, new.anlkt2_id, new.anlkt3_id, new.anlkt4_id, new.anlkt5_id;
end
^

CREATE TRIGGER ES_CHECK_PERMISACC_BU FOR ECONOMICSPEC
ACTIVE BEFORE UPDATE POSITION 0
as
begin
  execute procedure P_CHECK_PERMISSIBLEACC
    new.accdb_id, new.anldb1_id, new.anldb2_id, new.anldb3_id, new.anldb4_id, new.anldb5_id,
    new.acckt_id, new.anlkt1_id, new.anlkt2_id, new.anlkt3_id, new.anlkt4_id, new.anlkt5_id;
end
^

CREATE TRIGGER ES_UPDATE_ACCBATCHHIST_AD FOR ECONOMICSPEC
ACTIVE AFTER DELETE POSITION 0
as
declare variable ans integer;
/*declare variable id integer;*/
declare variable accbatch_id integer;
declare variable quan numeric(15,3);
declare variable beginquan numeric(15,3);
declare variable eo_insertsign integer;
begin
  eo_insertsign = null;
  select e.insertsign from economicoper e where e.id = old.economicoper_id
  into :eo_insertsign;
  if ((eo_insertsign = 0) or (eo_insertsign is null)) then begin
    ans=dm('ok');
    ans=wi('hisdb',old.accbatchhistorydb_id);
    ans=wi('hiskt',old.accbatchhistorykt_id);
    ans=wi('id',old.id);
    if ((old.accbatchhistorydb_id is not null)or(old.accbatchhistorykt_id is not null)) then begin
      if ((old.quantity=0)or((old.summanat=0)and(old.summacur=0))) then begin
        /* is revalue */
        if (old.accbatchhistorydb_id is not null) then begin
          if (old.quantity <> 0) then begin
            update accbatch
            set
              endquan = endquan - old.quantity
            where (id = (select a.accbatch_id from accbatchhistory a where (a.id = old.accbatchhistorydb_id)));
          end
          delete from accbatchhistory where id = old.accbatchhistorydb_id;
        end
        else begin
          if (old.accbatchhistorykt_id is not null) then
            if (old.quantity <> 0) then
              update accbatch
              set
                endquan = endquan - old.quantity
              where (id = (select a.accbatch_id from accbatchhistory a where (a.id = old.accbatchhistorykt_id)));
          delete from accbatchhistory where id = old.accbatchhistorykt_id;
        end
      end
      else begin
        if ((old.accbatchhistorydb_id is not null)
        /*Konst-11.12.2003*/
          or (old.accbatchdb_id is not null))
        /*end Konst-11.12.2003*/
        then begin
          select a.beginquan from accbatch a where (a.id = old.accbatchdb_id)
          into :beginquan;
          ans=wf('bq',15,3,beginquan);
          if ((beginquan - old.quantity) = 0) then
            delete from accbatch where id = old.accbatchdb_id;
          else update accbatch set beginquan = beginquan - old.quantity where id = old.accbatchdb_id;
        end
        if (old.accbatchhistorykt_id is not null) then begin
          quan = -old.quantity;
          select a.id from accbatch a, accbatchhistory ah where ((a.id = ah.accbatch_id)and(ah.id = old.accbatchhistorykt_id))
          into :accbatch_id;
          /* out good */
          execute procedure p_out_good_with_accbatch accbatch_id, old.accbatchhistorykt_id, :quan;
        end
      end
    end
    else
      if (old.accbatchdb_id <> 0) then begin
        select a.beginquan from accbatch a where (a.id = old.accbatchdb_id)
        into :beginquan;
        ans=wf('bq',15,3,beginquan);
        if ((beginquan - old.quantity) = 0) then
          delete from accbatch where id = old.accbatchdb_id;
        else update accbatch set beginquan = beginquan - old.quantity where id = old.accbatchdb_id;
      end
  end
end
^

CREATE TRIGGER FINOPER_UPDATE_FINTURN_BU FOR FINOPER
ACTIVE BEFORE UPDATE POSITION 0
as
declare variable period_id integer;
declare variable ans integer;
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: давно
 Назначение: триггер обновления финансовых оборотных ведомостей
   после изменения операции
 Используется: сервером приложения
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 14.04.2003 Кривопустов Введение валют в оборотку
**********************************************************/
  execute procedure fin_find_opened_period new.keepdate
  returning_values :period_id;
  /* update after insert */
  if (old.insertsign = 1) then begin
    execute procedure fin_update_turn(old.id, :period_id, new.user_id, new.curcode);
  end

  /* update after lock */
  if (old.insertsign = 2) then begin
    /*delete from finoper_buf where (id = old.id);*/
    execute procedure fin_update_turn(old.id, :period_id, new.user_id, new.curcode);
  end
END
^

CREATE TRIGGER FOLDER_BD FOR FOLDER
ACTIVE BEFORE DELETE POSITION 0
AS
begin
  if (old.foldertype = 1/*Partner*/) then begin
    if (exists(select * from contractor c where (c.folder_id = old.id) and (c.kind = 0))) then
      exception e_foreign_key_violation;
  end
end
^

CREATE TRIGGER INVAH_CALC_TOTAL_VALUE_BU FOR INVENTORYACTHEAD
ACTIVE BEFORE UPDATE POSITION 0
as
declare variable curcource numeric(15,4);
begin
  /* calculation total values */
  select sum(realsumma) from inventoryactspec i, docspec ds
  where (i.docspec_id = ds.id) and (ds.dochead_id = new.dochead_id)
  into new.realsummacur;
  select dh.curcource from dochead dh where dh.id = new.dochead_id
  into :curcource;
  new.realsummanat = new.realsummacur * curcource;
end
^

CREATE TRIGGER INV_INSERT_BI FOR INVENTORY
ACTIVE BEFORE INSERT POSITION 0
as
begin
  /* Trigger text */
  select
    h.FOLDER_ID, h.PARENT_ID, h.GROUPNUM, h.CARDNUM, h.OBJNUM, h.OKOF_ID,
    h.INVLOCATION_ID, h.MANUFACTURER, h.MODEL, h.SERIALNUM, h.PASSPNUM,
    h.INOPERDOCNUM, h.INOPERDATE, h.OUTOPERDOCNUM, h.OUTOPERDATE, h.ISCOMPLEX,
    h.ISCOMMON, h.COMMENT, h.CATALOG_ID, h.CONTRACTOR_ID, h.INVNAME,
    h.INCOMEDOCNUM, h.INCOMEDATE
  from acc_invhead h
  where h.id = new.invhead_id
  into
    new.FOLDER_ID, new.PARENT_ID, new.GROUPNUM, new.CARDNUM, new.OBJNUM, new.OKOF_ID,
    new.INVLOCATION_ID, new.MANUFACTURER, new.MODEL, new.SERIALNUM, new.PASSPNUM,
    new.INOPERDOCNUM, new.INOPERDATE, new.OUTOPERDOCNUM, new.OUTOPERDATE, new.ISCOMPLEX,
    new.ISCOMMON, new.COMMENT, new.CATALOG_ID, new.CONTRACTOR_ID, new.INVNAME,
    new.INCOMEDOCNUM, new.INCOMEDATE;
end
^

CREATE TRIGGER INV_UPDATE_HEAD_AU FOR ACC_INVHEAD
ACTIVE AFTER UPDATE POSITION 0
as
begin
  /* Trigger text */
  update INVENTORY I
  set
    I.FOLDER_ID = new.FOLDER_ID,
    I.PARENT_ID = new.PARENT_ID,
    I.GROUPNUM = new.GROUPNUM,
    I.CARDNUM = new.CARDNUM,
    I.OBJNUM = new.OBJNUM,
    I.OKOF_ID = new.OKOF_ID,
    I.INVLOCATION_ID = new.INVLOCATION_ID,
    I.MANUFACTURER = new.MANUFACTURER,
    I.MODEL = new.MODEL,
    I.SERIALNUM = new.SERIALNUM,
    I.PASSPNUM = new.PASSPNUM,
    I.INOPERDOCNUM = new.INOPERDOCNUM,
    I.INOPERDATE = new.INOPERDATE,
    I.OUTOPERDOCNUM = new.OUTOPERDOCNUM,
    I.OUTOPERDATE = new.OUTOPERDATE,
    I.ISCOMPLEX = new.ISCOMPLEX,
    I.ISCOMMON = new.ISCOMMON,
    I.COMMENT = new.COMMENT,
    I.CATALOG_ID = new.CATALOG_ID,
    I.CONTRACTOR_ID = new.CONTRACTOR_ID,
    I.INVNAME = new.INVNAME,
    I.INCOMEDOCNUM = new.INCOMEDOCNUM,
    I.INCOMEDATE = new.INCOMEDATE
  where I.invhead_id = new.id;
end
^

CREATE TRIGGER LS_CHECK_LINK_BI FOR LINKSTAGE
ACTIVE BEFORE INSERT POSITION 0
as
declare variable stage smallint;
begin
  select dps.stage from docprocessstage dps where (dps.id = new.nextstage_id)
  into :stage;
  if (stage between 1 and 2) then exception E_Invalid_Next_Stage;
end
^

CREATE TRIGGER LS_POSSIBLE_BRANCH_AI FOR LINKSTAGE
ACTIVE AFTER INSERT POSITION 0
as
declare variable linkcount integer;
declare variable isand smallint;
declare variable isor smallint;
begin
  select count(l.prevstage_id) from linkstage l where (l.prevstage_id = new.prevstage_id)
  into :linkcount;
  if (linkcount > 1) then begin
    select isandout, isorout from docprocessstage where id = new.prevstage_id
    into :isand, :isor;
    if (not ((isand = 1) or (isor = 1))) then
      update docprocessstage set
        isandout = 1
      where (id = new.prevstage_id);
  end
  select count(l.nextstage_id) from linkstage l where (l.nextstage_id = new.nextstage_id)
  into :linkcount;
  if (linkcount > 1) then begin
    select isandin, isorin from docprocessstage where id = new.nextstage_id
    into :isand, :isor;
    if (not ((isand = 1) or (isor = 1))) then
      update docprocessstage set
        isandin = 1
      where (id = new.nextstage_id);
  end
end
^

CREATE TRIGGER PA_CHECK_UNIQUE_BI FOR PERMISSIBLEACCOUNTS
ACTIVE BEFORE INSERT POSITION 0
as
declare variable result integer;
begin
  execute procedure F_FIND_PERMISSIBLEACCOUNTS(
    new.accdb_id, new.anldb1_id, new.anldb2_id, new.anldb3_id, new.anldb4_id, new.anldb5_id,
    new.acckt_id, new.anlkt1_id, new.anlkt2_id, new.anlkt3_id, new.anlkt4_id, new.anlkt5_id)
  returning_values :result;
  if (result <> 0) then exception E_PERMISSIBLEACCOUNTS_UNIQUE;
end
^

CREATE TRIGGER PA_CHECK_UNIQUE_BU FOR PERMISSIBLEACCOUNTS
ACTIVE BEFORE UPDATE POSITION 0
as
begin
  if (exists(
       select * from permissibleaccounts p
       where (p.id <> new.id) and
        ( p.accdb_id = new.accdb_id ) and
        ((p.anldb1_id = new.anldb1_id)or((p.anldb1_id is null)and(new.anldb1_id is null))) and
        ((p.anldb2_id = new.anldb2_id)or((p.anldb2_id is null)and(new.anldb2_id is null))) and
        ((p.anldb3_id = new.anldb3_id)or((p.anldb3_id is null)and(new.anldb3_id is null))) and
        ((p.anldb4_id = new.anldb4_id)or((p.anldb4_id is null)and(new.anldb4_id is null))) and
        ((p.anldb5_id = new.anldb5_id)or((p.anldb5_id is null)and(new.anldb5_id is null))) and
        ( p.acckt_id = new.acckt_id ) and
        ((p.anlkt1_id = new.anlkt1_id)or((p.anlkt1_id is null)and(new.anlkt1_id is null))) and
        ((p.anlkt2_id = new.anlkt2_id)or((p.anlkt2_id is null)and(new.anlkt2_id is null))) and
        ((p.anlkt3_id = new.anlkt3_id)or((p.anlkt3_id is null)and(new.anlkt3_id is null))) and
        ((p.anlkt4_id = new.anlkt4_id)or((p.anlkt4_id is null)and(new.anlkt4_id is null))) and
        ((p.anlkt5_id = new.anlkt5_id)or((p.anlkt5_id is null)and(new.anlkt5_id is null)))
      )) then
    exception E_PERMISSIBLEACCOUNTS_UNIQUE;
end
^

CREATE TRIGGER PIL_UPDATE_DIST_SUM_AD FOR PHASEITEM_LINK
ACTIVE AFTER DELETE POSITION 0
as
begin
  /* Plan */
  update phaseitemplan set
    factsum = Round4(factsum - old.distsum)
  where id = old.planitem_id;
  /* Fact */ 
  update phaseitemfact set
    distsum = Round4(distsum - old.distsum)
  where id = old.factitem_id; 
end
^

CREATE TRIGGER PIL_UPDATE_DIST_SUM_AI FOR PHASEITEM_LINK
ACTIVE AFTER INSERT POSITION 0
as
begin
  /* Plan */
  update phaseitemplan set
    factsum = Round4(factsum + new.distsum)
  where id = new.planitem_id;
  /* Fact */ 
  update phaseitemfact set
    distsum = Round4(distsum + new.distsum)
  where id = new.factitem_id; 
end
^

CREATE TRIGGER PLH_SET_INSERTSIGN_BU FOR PRICELISTHEAD
ACTIVE BEFORE UPDATE POSITION 0
as
begin
  new.insertsign = null;
end
^

CREATE TRIGGER PLS_COPY_PRICES_AI FOR PRICELISTSPEC
ACTIVE AFTER INSERT POSITION 0
as
begin
  insert into pricelistspec_price (pricelistspec_id, pricetype_id, price)
  select new.id, l.pricetype_id, 0 from pricelist_pricetype_link l, pricelistfolder f
  where (l.pricelist_id = f.pricelisthead_id) and (f.id = new.folder_id);
end
^

CREATE TRIGGER PPL_INS_PLSPEC_PRICETYPE_AI FOR PRICELIST_PRICETYPE_LINK
ACTIVE AFTER INSERT POSITION 0
as
declare variable plspec_id integer;
declare variable price numeric(15,4);
begin
  for
    select pls.id, pls.price from pricelistspec pls, pricelistfolder f, pricelisthead plh
    where (pls.folder_id = f.id) and (f.pricelisthead_id = plh.id) and (plh.id = new.pricelist_id)
    into :plspec_id, :price
  do begin
    insert into pricelistspec_price (pricelistspec_id, pricetype_id, price)
    values (:plspec_id, new.pricetype_id, Round4(:price + :price * new.relative_percent / 100.0));
  end
end
^

CREATE TRIGGER PRICELISTSPEC_TRG_UID FOR PRICELISTSPEC
ACTIVE BEFORE INSERT POSITION 0
as begin
   if (new.UNID is null) then new.UNID = CreateUID();
 end
^

CREATE TRIGGER PR_CONTACT_HIST_TRG_UID FOR PR_CONTACT_HIST
ACTIVE BEFORE INSERT POSITION 0
as begin
   if (new.UNID is null) then new.UNID = CreateUID();
 end
^

CREATE TRIGGER P_CHECK_FINPERIOD_CROSS_BI FOR FINPERIOD
ACTIVE BEFORE INSERT POSITION 0
as
begin
  if (exists(
        select * from finperiod p
        where ((new.datefrom between p.datefrom and p.dateto) or (new.dateto between p.datefrom and p.dateto))
            )
     ) then exception E_FINPERIODS_IS_CROSS;
end
^

CREATE TRIGGER P_CHECK_FINPERIOD_CROSS_BU FOR FINPERIOD
ACTIVE BEFORE UPDATE POSITION 0
as
begin
  if ((new.datefrom <> old.datefrom) or (new.dateto <> old.dateto)) then
    if (exists(
          select * from finperiod p
          where ((new.datefrom between p.datefrom and p.dateto) or (new.dateto between p.datefrom and p.dateto))
              )
       ) then exception E_FINPERIODS_IS_CROSS;
end
^

CREATE TRIGGER P_CHECK_PERIOD_CROSS_BI FOR PERIOD
ACTIVE BEFORE INSERT POSITION 0
as
begin
  execute procedure P_Check_Periods_Cross new.datefrom, new.dateto;
end
^

CREATE TRIGGER P_CHECK_PERIOD_CROSS_BU FOR PERIOD
ACTIVE BEFORE UPDATE POSITION 0
as
begin
  if ((new.datefrom <> old.datefrom) or (new.dateto <> old.dateto)) then
    execute procedure P_Check_Periods_Cross new.datefrom, new.dateto;
end
^

CREATE TRIGGER REF_BANK_ACCOUNT_BI FOR REF_BANK_ACCOUNT
ACTIVE BEFORE INSERT POSITION 0
as
begin
  if (new.UNID is null) then new.UNID = CreateUID();
end
^

CREATE TRIGGER REF_FEATUREVAL_BD FOR FEATUREVAL
ACTIVE BEFORE DELETE POSITION 0
as
begin
  if (exists(select * from featurelink fl
             where (fl.feature_id = old.feature_id) and (fl.val = old.val) and (fl.rec_id is not null)
            )
     ) then
    exception REF_E_FEATUREVAL_IS_USED;
end
^

CREATE TRIGGER REF_FEATUREVAL_BU FOR FEATUREVAL
ACTIVE BEFORE UPDATE POSITION 0
as
begin
  if ( (new.val <> old.val) and
       (exists(select * from featurelink fl
               where (fl.feature_id = old.feature_id) and (fl.val = old.val) and (fl.rec_id is not null)
              )
       )
     ) then
    exception REF_E_FEATUREVAL_IS_USED;
end
^

CREATE TRIGGER RPTBANDS_DEL_CHILDREN FOR RPTBANDS
ACTIVE BEFORE DELETE POSITION 0
as
begin
delete from rptbands where parent_id = old.id;
end
^

CREATE TRIGGER RPTBANDS_SET_PARENT_ID_BI FOR RPTBANDS
ACTIVE BEFORE INSERT POSITION 0
as
begin
  if (new.parent_id is not null) then new.parent_id_nn = new.parent_id;
end
^

CREATE TRIGGER RPTPARAMS_DEL FOR RPTPARAMS
ACTIVE BEFORE DELETE POSITION 0
as
begin
  delete from rptparams where meta_id = old.id;
end
^

CREATE TRIGGER RVAL_CREATE_ACCBATCH_BI FOR REMNVAL
ACTIVE BEFORE INSERT POSITION 1
as
declare variable anlform smallint;
declare variable abh_id integer;
declare variable aid timestamp;
begin
  if ((new.isremove is null) and ((new.remnbeginnat <> 0) or (new.remnbegincur <> 0) or (new.beginquan <> 0))) then begin

    select a.anlform from accplan a where (a.id = new.acc_id) into :anlform;

    if ((anlform = 4)or(anlform = 6)or(anlform = 7)) then begin
      select p.datefrom from period p where p.id = new.period_id into :aid;
      execute procedure f_create_accbatch(
        :aid, new.acc_id, new.anlplan1_id, new.anlplan2_id, new.anlplan3_id, new.anlplan4_id, new.anlplan5_id,
        new.catalog_id, 0, 0, '', '', '30.12.1899', new.beginquan, new.remnbeginnat, new.remnbegincur,
        new.contractor_id, null)
      returning_values new.batch_id, :abh_id;
      if (anlform <> 4) then new.batch_id = null;
    end
  end
  new.isremove = null;
end
^

CREATE TRIGGER SBH_UPDATE_STOCKBATCH_AD FOR STOCKBATCHHISTORY
ACTIVE AFTER DELETE POSITION 0
as
declare variable beginquan numeric(18,6);
begin
  if (old.kind = 0) then begin
    /* income */
    update stockbatch set
      beginquan = beginquan - old.quantity,
      beginquan2 = beginquan2 - old.quantity2,
      endquan = endquan - old.quantity,
      endquan2 = endquan2 - old.quantity2
    where id = old.stockbatch_id;
    /* update serial number */
    delete from WH_SERIAL_NUM n
    where (n.income_docspec_id = old.documentspec_id) and (n.batch_id = old.stockbatch_id);
    /* update bin location */
    delete from wh_bin_location_detail
    where (kind = 0) and (docspec_id = old.documentspec_id) and (stockbatch_id = old.stockbatch_id);
  end
  else begin
    /* outcome */
    update stockbatch set
      endquan = endquan + old.quantity,
      endquan2 = endquan2 + old.quantity2
    where id = old.stockbatch_id;
    /* update serial number */
    update wh_serial_num set
      outcome_docspec_id = null
    where (outcome_docspec_id = old.documentspec_id) and (batch_id = old.stockbatch_id);
    /* update bin location */
    delete from wh_bin_location_detail
    where (kind = 1) and (docspec_id = old.documentspec_id) and (stockbatch_id = old.stockbatch_id);
  end
  /* check stockbatch on empty */
  select sb.beginquan from stockbatch sb where (sb.id = old.stockbatch_id) into :beginquan;
  if (beginquan = 0) then begin
    delete from stockbatch where (id = old.stockbatch_id);
    when sqlcode -530 do exception E_STOCKBATCHHISTORY_EXISTS; 
  end 
end
^

CREATE TRIGGER SBH_UPDATE_STOCKBATCH_AI FOR STOCKBATCHHISTORY
ACTIVE AFTER INSERT POSITION 0
as
begin
  if (new.kind = 0) then
    /* income */
    update stockbatch set
      beginquan = beginquan + new.quantity,
      beginquan2 = beginquan2 + new.quantity2,
      endquan = endquan + new.quantity,
      endquan2 = endquan2 + new.quantity2
    where id = new.stockbatch_id;
  else
    /* outcome */
    update stockbatch set
      endquan = endquan - new.quantity,
      endquan2 = endquan2 - new.quantity2
    where id = new.stockbatch_id;
end
^

CREATE TRIGGER SB_DEL_EMPTY_STOCKCARD_AD FOR STOCKBATCH
ACTIVE AFTER DELETE POSITION 0
as
declare variable reserve numeric(15,3);
declare variable plan_in numeric(15,3);
declare variable plan_out numeric(15,3);
begin
  select sc.reserve, sc.plan_in, sc.plan_out
    from stockcard sc where sc.id = old.stockcard_id
    into :reserve, :plan_in, :plan_out;  
  /*if (((reserve = 0) or (reserve is null)) and
      ((plan_in = 0) or (plan_in is null)) and
      ((plan_out = 0) or (plan_out is null))) then begin
    if (not exists (select * from stockbatch sb
                    where (sb.stockcard_id = old.stockcard_id))) then
      delete from stockcard sc where (sc.id = old.stockcard_id);
  end*/
  if (
      (not exists (select * from stockbatch sb
                  where (sb.stockcard_id = old.stockcard_id)))
      and
      (not exists (select * from stockplanhistory sph
                  where (sph.stockcard_id = old.stockcard_id)))
      ) then
    delete from stockcard sc where (sc.id = old.stockcard_id);
end
^

CREATE TRIGGER SB_UPDATE_STOCKCARD_AU FOR STOCKBATCH
ACTIVE AFTER UPDATE POSITION 0
as
begin
  update stockcard set
    quantity = quantity - old.endquan + new.endquan,
    quantity2 = quantity2 - old.endquan2 + new.endquan2
  where id = old.stockcard_id;
end
^

CREATE TRIGGER SC_CHECK_ACCESS_QUAN_BU FOR STOCKCARD
ACTIVE BEFORE UPDATE POSITION 0
as
begin
  if ((new.quantity < 0) or (new.quantity2 < 0)) then exception E_IS_NOT_ENOUGH_QUAN_ON_STOCK;
end
^

CREATE TRIGGER SC_CHECK_BD FOR STOCKCARD
ACTIVE BEFORE DELETE POSITION 0
as
begin
  if ((old.reserve <> 0) or
      (old.plan_in <> 0) or
      (old.plan_out <> 0)) then
    exception e_stockcard_is_not_empty;   
end
^

CREATE TRIGGER STOCKPLANHISTORY_AD FOR STOCKPLANHISTORY
ACTIVE AFTER DELETE POSITION 0
as
begin
  if (old.kind = 0) then begin
    if (old.direction = 0) then begin
      /* do plan in */
      update stockcard sc set 
        sc.plan_in = sc.plan_in - old.quantity,
        sc.plan_in2 = sc.plan_in2 - old.quantity2
      where sc.id = old.stockcard_id;
    end
    else begin
      /* do plan out */
      update stockcard sc set 
        sc.plan_out = sc.plan_out - old.quantity,
        sc.plan_out2 = sc.plan_out2 - old.quantity2
      where sc.id = old.stockcard_id;
    end 
  end
  else if (old.kind = 1) then begin
    if (old.direction = 0) then begin
      /* undo plan in */
      update stockcard sc set 
        sc.plan_in = sc.plan_in + old.quantity,
        sc.plan_in2 = sc.plan_in2 + old.quantity2
      where sc.id = old.stockcard_id;
    end
    else begin
      /* undo plan out */
      update stockcard sc set 
        sc.plan_out = sc.plan_out + old.quantity,
        sc.plan_out2 = sc.plan_out2 + old.quantity2
      where sc.id = old.stockcard_id;
    end 
  end
  else if (old.kind = 2) then begin
    /* do reserve */
    update stockcard sc set 
      sc.reserve = sc.reserve - old.quantity,
      sc.reserve2 = sc.reserve2 - old.quantity2
    where sc.id = old.stockcard_id;
  end
  else if (old.kind = 3) then begin
    /* undo reserve */
    update stockcard sc set 
      sc.reserve = sc.reserve + old.quantity,
      sc.reserve2 = sc.reserve2 + old.quantity2
    where sc.id = old.stockcard_id;
  end
end
^

CREATE TRIGGER TAXLINK_ADD FOR TAXLINK
ACTIVE BEFORE INSERT POSITION 0
as
begin
  new.id = gen_id(taxlink_id_gen,1);
end
^

CREATE TRIGGER TAXSUMM_SET_ID_BI FOR TAXSUMM
ACTIVE BEFORE INSERT POSITION 0
as
begin
  new.id = gen_id(taxsumm_id_gen, 1);
end
^

CREATE TRIGGER TD_INSERT_DPSTAGE_AI FOR TYPEDOC
ACTIVE AFTER INSERT POSITION 0
as
declare variable id integer;
begin
  /*if (new.insertsign = 1) then begin*/
  id = gen_id( docprocessstage_id_gen, 1 );
    insert into docprocessstage(id, doctype_id, partial, previscomplete, shownewdocument, stage, dpsname, comment, linkdocsection, isandin, isorin, isandout, isorout, priority, coorx, coory, code)
    values
    (:id, new.id, 0, 0, 0, 1, '', '', 0, 0, 0, 0, 0, 1, 50, 50, :id);
  id = gen_id( docprocessstage_id_gen, 1 );
    insert into docprocessstage(id, doctype_id, partial, previscomplete, shownewdocument, stage, dpsname, comment, linkdocsection, isandin, isorin, isandout, isorout, priority, coorx, coory, code)
    values
    (:id, new.id, 0, 0, 0, 2, '', '', 0, 0, 0, 0, 0, 1, 200, 50, :id);
  /*end*/
end
^

CREATE TRIGGER WF_ACTIVITY_DEL_REQUESTER_AD FOR WF_ACTIVITY
ACTIVE AFTER DELETE POSITION 0
as
begin
  delete from wf_requester where (requester_kind = 2) and (entity_id = old.id);
end
^
