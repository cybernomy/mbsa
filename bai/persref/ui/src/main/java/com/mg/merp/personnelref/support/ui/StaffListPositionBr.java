/*
 * StaffListPositionBr.java
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

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.HierarchyRestrictionSupport;
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
import com.mg.merp.personnelref.model.StaffListPosition;
import com.mg.merp.personnelref.model.StaffListUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер браузера "Должности в штатном расписании"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: StaffListPositionBr.java,v 1.4 2007/11/08 16:21:40 safonov Exp $
 */
public class StaffListPositionBr extends DefaultHierarchyBrowseForm {

  private final String INIT_QUERY_TEXT = "select %s from StaffListPosition sp %s %s"; //$NON-NLS-1$
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();

  private Integer staffListId;

  public StaffListPositionBr() throws Exception {
    super();
    folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/personnelref/StaffListUnit"); //$NON-NLS-1$
    tree.setParentPropertyName("Parent.Id"); //$NON-NLS-1$
    restrictionFormName = "com/mg/merp/personnelref/resources/StaffListPositionRest.mfd.xml"; //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#initializeMaster()
   */
  @Override
  protected void initializeMaster(PersistentObject master) {
    uiProperties.put("StaffListUnit", master); //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacyTreeBrowseForm#loadFolders()
   */
  @Override
  protected TreeNode loadFolders() {
    List<StaffListUnit> list = MiscUtils.convertUncheckedList(StaffListUnit.class, OrmTemplate.getInstance().findByNamedParam(String.format("from StaffListUnit slu where %s and slu.StaffList.Id = :staffListId order by slu.Parent.Id, slu.UName", DatabaseUtils.generateFlatBrowseWhereEJBQL("slu.Id", 6)), "staffListId", getStaffListId())); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return StaffListUnitTreeNode.createTree(list);
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
    StaffListPositionRest restForm = (StaffListPositionRest) getRestrictionForm();
    whereText = " where ".concat(DatabaseUtils.formatEJBQLHierarchyRestriction(((HierarchyRestrictionSupport) restForm).isUseHierarchy(), "sp.StaffListUnit", 6, "folder", folderEntity, paramsName, paramsValue, true)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        concat(DatabaseUtils.formatEJBQLObjectRestriction("sp.Position", restForm.getPositionName(), "positionName", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        concat(DatabaseUtils.formatEJBQLObjectRestriction("sp.StaffCategory", restForm.getStaffCategoryCode(), "staffCategoryCode", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("sp.BeginDate", restForm.getBeginDate(), restForm.getEndDate(), "beginDate", "endDate", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("sp.EndDate", restForm.getBeginDate(), restForm.getEndDate(), "beginDate", "endDate", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        concat(DatabaseUtils.formatEJBQLObjectRestriction("sp.WorkSchedule", restForm.getWorkScheduleCode(), "workScheduleCode", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        concat(DatabaseUtils.formatEJBQLObjectRestriction("sp.WorkCondition", restForm.getWorkConditionCode(), "workConditionCode", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        concat(DatabaseUtils.formatEJBQLObjectRestriction("sp.TaxCalcKind", restForm.getTaxCalcKindCode(), "taxCalcKindCode", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        concat(DatabaseUtils.formatEJBQLObjectRestriction("sp.CostsAnl1", restForm.getAnlCode1(), "anlCode1", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        concat(DatabaseUtils.formatEJBQLObjectRestriction("sp.CostsAnl2", restForm.getAnlCode2(), "anlCode2", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        concat(DatabaseUtils.formatEJBQLObjectRestriction("sp.CostsAnl3", restForm.getAnlCode3(), "anlCode3", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        concat(DatabaseUtils.formatEJBQLObjectRestriction("sp.CostsAnl4", restForm.getAnlCode4(), "anlCode4", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        concat(DatabaseUtils.formatEJBQLObjectRestriction("sp.CostsAnl5", restForm.getAnlCode5(), "anlCode5", paramsName, paramsValue, false)).    //$NON-NLS-1$ //$NON-NLS-2$
        concat(DatabaseUtils.formatEJBQLObjectRestriction("tar.Category", restForm.getTariffingCategoryCode(), "tariffingCategoryCode", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("tar.BeginDate", restForm.getBeginDateTC(), restForm.getEndDateTC(), "beginDateTC", "endDateTC", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("tar.EndDate", restForm.getBeginDateTC(), restForm.getEndDateTC(), "beginDateTC", "endDateTC", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("tar.RateOfSalary", restForm.getMinSalaryFrom(), restForm.getMinSalaryTill(), "minSalaryFrom", "minSalaryTill", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

        concat(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "sp.Id", restForm.getAddinFieldsRestriction(), false)); //$NON-NLS-1$

    if (whereText.contains("tar.")) { //$NON-NLS-1$
      whereText = whereText.concat(" and tar.StaffList = sp.StaffListUnit.StaffList and tar.SlPositionUniqueId = sp.SlPositionUniqueId"); //$NON-NLS-1$
      fromList = (", Tariffing tar ").concat(fromList); //$NON-NLS-1$
    }
    return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createModel()
   */
  @Override
  protected MaintenanceTableModel createModel() {
    return new DefaultMaintenanceEJBQLTableModel() {

      /* (non-Javadoc)
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
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "Id", "sp.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "Position", "sp.Position.Name", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "SlPositionUniqueId", "sp.SlPositionUniqueId", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "StaffCategory", "sc.CCode", "left join sp.StaffCategory as sc", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "BeginDate", "sp.BeginDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "EndDate", "sp.EndDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "RateNumber", "sp.RateNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "HolidayNumber", "sp.HolidayNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "WorkCondition", "wc.CCode", "left join sp.WorkCondition as wc", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "WorkSchedule", "ws.SCode", "left join sp.WorkSchedule as ws", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "CostsAnl1", "anl1.ACode", "left join sp.CostsAnl1 anl1", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "CostsAnl2", "anl2.ACode", "left join sp.CostsAnl2 anl2", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "CostsAnl3", "anl3.ACode", "left join sp.CostsAnl3 anl3", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "CostsAnl4", "anl4.ACode", "left join sp.CostsAnl4 anl4", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "CostsAnl5", "anl5.ACode", "left join sp.CostsAnl5 anl5", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "TaxCalcKind", "tck.Code", "left join sp.TaxCalcKind as tck", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    };
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultHierarchyBrowseForm#setupFolderPermissions()
   */
  @Override
  protected void setupFolderPermissions() {
    if (folderEntity != null)
      ServerUtils.getSecuritySystem().setupTreePermission((Integer) folderEntity.getPrimaryKey(), 6, "com.mg.merp.personnelref.model.StaffListUnit", "Parent.Id"); //$NON-NLS-1$ //$NON-NLS-2$
  }

  public void setStaffList(Integer staffListId) {
    this.staffListId = staffListId;
  }

  /**
   * Получить идентификатор варианта ШР
   *
   * @return идентификатор варианта ШР
   */
  private Integer getStaffListId() {
    if (staffListId == null)
      staffListId = getCalcPeriodService().getCurrentCalcPeriod().getStaffList().getId();
    return staffListId;
  }

  private CalcPeriodServiceLocal getCalcPeriodService() {
    return (CalcPeriodServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CalcPeriodServiceLocal.LOCAL_SERVICE_NAME);
  }

}

