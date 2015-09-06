/*
 * DocTypeAccessServiceBean.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 *
 */
package com.mg.merp.document.support;

import com.mg.framework.api.jdbc.JdbcTemplate;
import com.mg.framework.api.jdbc.RowMapper;
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.utils.JdbcUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.document.DocTypeAccessServiceLocal;
import com.mg.merp.document.DocTypePermission;
import com.mg.merp.document.model.DocType;
import com.mg.merp.document.model.DocTypeRights;
import com.mg.merp.security.model.Groups;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Права на типы документов"
 *
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id: DocTypeAccessServiceBean.java,v 1.5 2009/03/17 09:13:06 safonov Exp $
 */
@Stateless(name = "merp/document/DocTypeAccessService") //$NON-NLS-1$
public class DocTypeAccessServiceBean extends AbstractPOJODataBusinessObjectServiceBean<DocTypeRights, Integer> implements DocTypeAccessServiceLocal {

  private static final String LOAD_DOC_TYPE_PERMISSIONS_SQL = "select g.id, g.name, r.rights, r.id from sec_groups g left join doctype_rights r on (r.group_id = g.id) and (r.rec_id = ?) order by g.name"; //$NON-NLS-1$


  /* (non-Javadoc)
   * @see com.mg.merp.document.DocTypeAccessServiceLocal#loadDocTypePermissions(java.lang.Integer)
   */
  public List<DocTypePermission> loadDocTypePermissions(Integer docTypeId) {
    return doLoadDocTypePermissions(docTypeId);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.DocTypeAccessServiceLocal#grantPermission(com.mg.merp.document.DocTypePermission, java.lang.Integer)
   */
  public void grantPermission(DocTypePermission permission, Integer docTypeId) {
    doGrantPermission(permission, docTypeId);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.DocTypeAccessServiceLocal#revokePermission(com.mg.merp.document.DocTypePermission)
   */
  public void revokePermission(DocTypePermission permission) {
    doRevokePermission(permission);
  }

  /**
   * Загрузить список прав доступа для типа документа
   *
   * @param docTypeId - идентификатор типа документа
   * @return список прав доступа для типа документа
   */
  protected List<DocTypePermission> doLoadDocTypePermissions(Integer docTypeId) {
    return JdbcTemplate.getInstance().query(LOAD_DOC_TYPE_PERMISSIONS_SQL, new Object[]{docTypeId}, new RowMapper<DocTypePermission>() {

      public DocTypePermission mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new DocTypePermission(rs.getInt(1), rs.getString(2), new Integer(1).equals(JdbcUtils.getIntegerValue(rs, 3)), JdbcUtils.getIntegerValue(rs, 4));
      }

    });
  }

  /**
   * Установить право доступа для типа документа
   *
   * @param permission - право доступа
   * @param docTypeId  - идентификатор типа документа
   */
  protected void doGrantPermission(DocTypePermission permission, Integer docTypeId) {
    if (permission == null) {
      getLogger().info("DocTypePermission is null"); //$NON-NLS-1$
      return;
    }
    PersistentManager pm = getPersistentManager();

    if (permission.getPermissionId() != null) {
      DocTypeRights docTypeRights = pm.find(DocTypeRights.class, permission.getPermissionId());
      docTypeRights.setRights(true);
      pm.merge(docTypeRights);
    } else {
      DocTypeRights docTypeRights = new DocTypeRights();
      docTypeRights.setDocType(pm.find(DocType.class, docTypeId));
      docTypeRights.setRights(true);
      docTypeRights.setSecGroups(pm.find(Groups.class, permission.getRoleId()));
      pm.persist(docTypeRights);

      permission.setPermissionId(docTypeRights.getId());
      permission.setPermission(true);
    }
  }

  /**
   * Отменить право доступа для типа документа
   *
   * @param permission - право доступа
   */
  protected void doRevokePermission(DocTypePermission permission) {
    if (permission == null) {
      getLogger().info("DocTypePermission is null"); //$NON-NLS-1$
      return;
    }
    PersistentManager pm = ServerUtils.getPersistentManager();

    DocTypeRights docTypeRights = pm.find(DocTypeRights.class, permission.getPermissionId());
    docTypeRights.setRights(false);
    pm.merge(docTypeRights);
  }

}
