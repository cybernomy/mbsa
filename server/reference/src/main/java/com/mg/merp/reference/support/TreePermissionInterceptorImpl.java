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
package com.mg.merp.reference.support;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.AbstractEntityInterceptor;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.Folder;
import com.mg.merp.reference.model.CatalogFolder;
import com.mg.merp.reference.model.OrgUnit;
import com.mg.merp.reference.model.PriceListFolder;

/**
 * Реализация перехватчика для добавления прав на элементы иерархии при создании
 *
 * @author Oleg V. Safonov
 * @version $Id: TreePermissionInterceptorImpl.java,v 1.8 2006/11/02 16:22:10 safonov Exp $
 */
public class TreePermissionInterceptorImpl extends AbstractEntityInterceptor {
  private static String TREE_PERMISSION_INTERCEPTOR = "TreePermissionInterceptor";
  private static String ORGUNIT_CLASS = OrgUnit.class.getName();
  private static String FOLDER_CLASS = Folder.class.getName();
  private static String CATALOGFOLDER_CLASS = CatalogFolder.class.getName();
  private static String PRICELST_CLASS = PriceListFolder.class.getName();
//	private static String PERSONNEL_GROUP_CLASS = PersonnelGroup.class.getName();
//	private static String STAFF_LIST_UNIT_CLASS = StaffListUnit.class.getName();

  /* (non-Javadoc)
   * @see com.mg.framework.api.EntityInterceptor#getName()
   */
  public String getName() {
    return TREE_PERMISSION_INTERCEPTOR;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractEntityInterceptor#getHandledEntities()
   */
  @Override
  public String[] getHandledEntities() {
    return new String[]{ORGUNIT_CLASS, FOLDER_CLASS, CATALOGFOLDER_CLASS, PRICELST_CLASS/*, PERSONNEL_GROUP_CLASS, STAFF_LIST_UNIT_CLASS*/};
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.EntityInterceptor#onPostInsert(com.mg.framework.api.orm.PersistentObject)
   */
  public void onPostPersist(PersistentObject entity) {
    String entityName = entity.getEntityName();

    if (FOLDER_CLASS.equals(entityName)) //папки
      ServerUtils.getSecuritySystem().grantTreePermission((Integer) entity.getPrimaryKey(), ((Folder) entity).getFolder().getId(), 0);
    else if (CATALOGFOLDER_CLASS.equals(entityName)) //папки каталога
      ServerUtils.getSecuritySystem().grantTreePermission((Integer) entity.getPrimaryKey(), ((CatalogFolder) entity).getCatalogFolder().getId(), 1);
    else if (ORGUNIT_CLASS.equals(entityName)) //подразделения
      ServerUtils.getSecuritySystem().grantTreePermission((Integer) entity.getPrimaryKey(), ((OrgUnit) entity).getFolderId(), 4);
    else if (PRICELST_CLASS.equals(entityName)) {//папки прайс-листов
      PriceListFolder folder = ((PriceListFolder) entity).getParent();
      ServerUtils.getSecuritySystem().grantTreePermission((Integer) entity.getPrimaryKey(), folder == null ? (Integer) entity.getPrimaryKey() : folder.getId(), 2);
    }
//		else if (PERSONNEL_GROUP_CLASS.equals(entityName)) //папки основных сведений о сотрудниках		
//			ServerUtils.getSecuritySystem().grantTreePermission((Integer) entity.getPrimaryKey(), ((PersonnelGroup) entity).getParentId(), 5);
//		else if (STAFF_LIST_UNIT_CLASS.equals(entityName)) //папки штатного расписания		
//			ServerUtils.getSecuritySystem().grantTreePermission((Integer) entity.getPrimaryKey(), ((StaffListUnit) entity).getParent().getId(), 6);
  }

}
