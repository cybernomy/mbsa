/*
 * StaffListPersonalAccountBr.java
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
package com.mg.merp.salary.support.ui;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.widget.MaintenanceTable;
import com.mg.framework.api.ui.widget.PopupMenu;
import com.mg.framework.generic.ui.DefaultHierarchyBrowseForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.MiscUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.personnelref.CalcPeriodServiceLocal;
import com.mg.merp.personnelref.model.PersonalAccount;
import com.mg.merp.personnelref.model.PositionFill;
import com.mg.merp.personnelref.model.StaffListPosition;
import com.mg.merp.personnelref.model.StaffListUnit;
import com.mg.merp.personnelref.support.ui.StaffListUnitTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер браузера "Лицевые счета сотрудников"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: StaffListPersonalAccountBr.java,v 1.9 2007/11/08 16:36:30 safonov Exp $
 */
public class StaffListPersonalAccountBr extends DefaultHierarchyBrowseForm {

  private final String INIT_QUERY_TEXT = "select %s from PersonalAccount pa, StaffListPosition sp join pa.SetOfSalPositionFill pf %s %s and sp.SlPositionUniqueId = pf.SlPositionUnique.SlPositionUniqueId and sp.StaffListUnit.StaffList.Id = :staffList"; //$NON-NLS-1$
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();

  private Integer staffListId;


  public StaffListPersonalAccountBr() throws Exception {
    super();
    tree.setParentPropertyName("Parent.Id"); //$NON-NLS-1$
    folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/personnelref/StaffListUnit"); //$NON-NLS-1$
    staffListId = getCalcPeriodService().getCurrentCalcPeriod().getStaffList().getId();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#initializeMaster(java.io.Serializable)
   */
  @Override
  protected void initializeMaster(PersistentObject master) {
    uiProperties.put("StaffListUnit", master); //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#loadFolders()
   */
  protected TreeNode loadFolders() {
    List<StaffListUnit> list = MiscUtils.convertUncheckedList(StaffListUnit.class, OrmTemplate.getInstance().findByNamedParam(String.format("from StaffListUnit slu where %s and slu.StaffList.Id = :staffList order by slu.Parent.Id, slu.UName", DatabaseUtils.generateFlatBrowseWhereEJBQL("slu.Id", 6)), "staffList", staffListId)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return StaffListUnitTreeNode.createTree(list);
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
        result.add(new TableEJBQLFieldDef(PositionFill.class, "Id", "pf.PersonalAccount.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PositionFill.class, "SlPositionUnique", "pf.SlPositionUnique.SlPositionUniqueId", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PositionFill.class, "Position", "p.Name", "left join pf.Position as p", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(PositionFill.class, "PositionFillKind", "pfk.KCode", "left join pf.PositionFillKind as pfk", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(PositionFill.class, "BeginDate", "pf.BeginDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PositionFill.class, "EndDate", "pf.EndDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PositionFill.class, "RateNumber", "pf.RateNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PositionFill.class, "IsBasic", "pf.IsBasic", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonalAccount.class, "Personnel.Person.Surname", "pa.Personnel.Person.Surname", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonalAccount.class, "Personnel.Person.Name", "pa.Personnel.Person.Name", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonalAccount.class, "Personnel.Person.Patronymic", "pa.Personnel.Person.Patronymic", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonalAccount.class, "ANumber", "pa.ANumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "StaffCategory", "sc.CCode", "left join sp.StaffCategory as sc", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "RateNumber", "sp.RateNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "HolidayNumber", "sp.HolidayNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "WorkCondition", "wc.CCode", "left join sp.WorkCondition as wc", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "WorkSchedule", "ws.SCode", "left join sp.WorkSchedule as ws", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
    String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
    paramsName.clear();
    paramsValue.clear();
    paramsName.add("staffList"); //$NON-NLS-1$
    paramsValue.add(staffListId);
    whereText = " where ".concat(DatabaseUtils.formatEJBQLHierarchyRestriction(true, "sp.StaffListUnit", 6, "folder", folderEntity, paramsName, paramsValue, true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultHierarchyBrowseForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    super.doOnRun();
    PopupMenu tablePopupMenu = view.getWidget(TABLE_WIDGET).getPopupMenu();
    tablePopupMenu.getMenuItem(MaintenanceTable.ADD_MENU_ITEM).setEnabled(false);
    tablePopupMenu.getMenuItem(MaintenanceTable.ERASE_MENU_ITEM).setEnabled(false);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultHierarchyBrowseForm#setupFolderPermissions()
   */
  @Override
  protected void setupFolderPermissions() {
    if (folderEntity != null)
      ServerUtils.getSecuritySystem().setupTreePermission((Integer) folderEntity.getPrimaryKey(), 6, "com.mg.merp.personnelref.model.StaffListUnit", "Parent.Id"); //$NON-NLS-1$ //$NON-NLS-2$
  }

  private CalcPeriodServiceLocal getCalcPeriodService() {
    return (CalcPeriodServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CalcPeriodServiceLocal.LOCAL_SERVICE_NAME);
  }

}
