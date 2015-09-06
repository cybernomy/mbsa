/*
 * TreePermissionInterceptorImpl.java
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
package com.mg.merp.personnelref.support;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.AbstractEntityInterceptor;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.personnelref.model.PersonnelGroup;
import com.mg.merp.personnelref.model.StaffListUnit;

/**
 * Реализация перехватчика для добавления прав на элементы иерархии при создании
 *
 * @author Oleg V. Safonov
 * @version $Id: TreePermissionInterceptorImpl.java,v 1.2 2007/07/09 07:55:22 sharapov Exp $
 */
public class TreePermissionInterceptorImpl extends AbstractEntityInterceptor {
  private static String TREE_PERMISSION_INTERCEPTOR = "PersonnelTreePermissionInterceptor";
  private static String PERSONNEL_GROUP_CLASS = PersonnelGroup.class.getName();
  private static String STAFF_LIST_UNIT_CLASS = StaffListUnit.class.getName();

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractEntityInterceptor#getHandledEntities()
   */
  @Override
  public String[] getHandledEntities() {
    return new String[]{PERSONNEL_GROUP_CLASS, STAFF_LIST_UNIT_CLASS};
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractEntityInterceptor#getName()
   */
  @Override
  public String getName() {
    return TREE_PERMISSION_INTERCEPTOR;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractEntityInterceptor#onPostPersist(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  public void onPostPersist(PersistentObject entity) {
    String entityName = entity.getEntityName();
    if (PERSONNEL_GROUP_CLASS.equals(entityName)) //папки основных сведений о сотрудниках
      ServerUtils.getSecuritySystem().grantTreePermission((Integer) entity.getPrimaryKey(), ((PersonnelGroup) entity).getParentId(), 5);
    else if (STAFF_LIST_UNIT_CLASS.equals(entityName)) { //папки штатного расписания
      StaffListUnit parentStaffListUnit = ((StaffListUnit) entity).getParent();
      ServerUtils.getSecuritySystem().grantTreePermission((Integer) entity.getPrimaryKey(), parentStaffListUnit == null ? (Integer) entity.getPrimaryKey() : ((StaffListUnit) entity).getParent().getId(), 6);
    }
  }

}
