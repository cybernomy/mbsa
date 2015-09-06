/*
 * DocTypeInterceptorImpl.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.security.SecuritySystem;
import com.mg.framework.generic.AbstractEntityInterceptor;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.document.model.DocType;

/**
 * Перехватчик для объекта-сущности "Тип документа", реализует инициализацию прав пользователей
 * после создания
 *
 * @author Oleg V. Safonov
 * @version $Id: DocTypeInterceptorImpl.java,v 1.1 2006/10/18 10:32:09 safonov Exp $
 */
public class DocTypeInterceptorImpl extends AbstractEntityInterceptor {

  /**
   * выдать права на объект
   *
   * @param docTypeId идентификатор типа документа
   * @param groupId   группа пользователя
   */
  private void grantDocTypePermission(int docTypeId, int groupId) {
    JdbcTemplate.getInstance().update("INSERT INTO DOCTYPE_RIGHTS (ID, REC_ID, GROUP_ID, RIGHTS) VALUES (?, ?, ?, 1)", new Object[]{DatabaseUtils.getSequenceNextValue("DOCTYPE_RIGHTS_ID_GEN"), docTypeId, groupId}); //$NON-NLS-1$ //$NON-NLS-2$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.EntityInterceptor#getName()
   */
  public String getName() {
    return "DocTypeInterceptor"; //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractEntityInterceptor#getHandledEntities()
   */
  @Override
  public String[] getHandledEntities() {
    return new String[]{DocType.class.getName()};
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.EntityInterceptor#onPostInsert(com.mg.framework.api.orm.PersistentObject)
   */
  public void onPostPersist(PersistentObject entity) {
    int docTypeId = ((DocType) entity).getId();
    //add admins
    grantDocTypePermission(docTypeId, SecuritySystem.ADMIN_GROUP);
    for (int group : ServerUtils.getUserProfile().getGroups()) {
      if (group != SecuritySystem.ADMIN_GROUP) //exclude admins duplicate
        grantDocTypePermission(docTypeId, group);
    }
  }

}
