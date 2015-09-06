/*
 * StaffListBr.java
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

package com.mg.merp.personnelref.support.ui;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultHierarchyBrowseForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.personnelref.StaffListPositionServiceLocal;
import com.mg.merp.personnelref.StaffListServiceLocal;
import com.mg.merp.personnelref.model.StaffList;
import com.mg.merp.reference.support.ReferenceUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер браузера "Варианты штатного расписания"
 *
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: StaffListBr.java,v 1.5 2007/07/09 08:07:47 sharapov Exp $
 */
public class StaffListBr extends DefaultHierarchyBrowseForm {

  private final String INIT_QUERY_TEXT = "select %s from StaffList sl %s"; //$NON-NLS-1$
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();

  public StaffListBr() throws Exception {
    super();
    folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder"); //$NON-NLS-1$
    tree.setParentPropertyName("Folder.Id"); //$NON-NLS-1$
    treeUIProperties.put("FolderType", StaffListServiceLocal.FOLDER_PART); //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#initializeMaster(java.io.Serializable)
   */
  @Override
  protected void initializeMaster(PersistentObject master) {
    uiProperties.put("Folder", master); //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#loadFolders()
   */
  @Override
  protected TreeNode loadFolders() throws ApplicationException {
    return ReferenceUtils.loadFolderHierarchy(StaffListServiceLocal.FOLDER_PART);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createModel()
   */
  @Override
  protected MaintenanceTableModel createModel() {
    return new DefaultMaintenanceEJBQLTableModel() {

      /*
       * (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
       */
      @Override
      protected int getPrimaryKeyFieldIndex() {
        return 0;
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(StaffList.class, "Id", "sl.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(StaffList.class, "LName", "sl.LName", false)); //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setQuery(java.lang.String)
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    };
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
   */
  @Override
  protected String createQueryText() {
    String whereText = ""; //$NON-NLS-1$
    Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
    String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    paramsName.clear();
    paramsValue.clear();
    whereText = " where ".concat(DatabaseUtils.formatEJBQLHierarchyRestriction(true, "sl.Folder", 0, "folder", folderEntity, paramsName, paramsValue, true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return String.format(INIT_QUERY_TEXT, fieldsList, whereText);
  }

  /**
   * Обработчик пункта КМ "Показать штатное расписание"
   *
   * @param event - событие
   */
  public void onActionShowStuffListPositions(WidgetEvent event) {
    Serializable[] selectedPrimaryKeys = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
    if (selectedPrimaryKeys != null && selectedPrimaryKeys.length > 0) {
      StaffListPositionBr staffListPositionBr = (StaffListPositionBr) ApplicationDictionaryLocator.locate().getBrowseForm((StaffListPositionServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/personnelref/StaffListPosition"), null); //$NON-NLS-1$
      staffListPositionBr.setStaffList((Integer) selectedPrimaryKeys[0]);
      staffListPositionBr.run();
    }
  }

}

