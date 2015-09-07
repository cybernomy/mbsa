/*
 * ContractorCardBr.java
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
package com.mg.merp.settlement.support.ui;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.HierarchyRestrictionSupport;
import com.mg.framework.api.ui.widget.MaintenanceTable;
import com.mg.framework.api.ui.widget.PopupMenu;
import com.mg.framework.generic.ui.DefaultHierarchyBrowseForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.reference.PartnerServiceLocal;
import com.mg.merp.reference.support.ReferenceUtils;
import com.mg.merp.settlement.model.ContractorCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер браузера "Картотеки расчетов с партнерами"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ContractorCardBr.java,v 1.7 2009/01/04 16:11:57 safonov Exp $
 */
public class ContractorCardBr extends DefaultHierarchyBrowseForm {

  private final String INIT_QUERY_TEXT = "select %s from ContractorCard cc left join cc.Contractor c %s where cc.OrgUnit.Id = :orgUnit %s"; //$NON-NLS-1$
  private final String TABLE_WIDGET_NAME = "table";  //$NON-NLS-1$
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();
  private Integer companyId;

  public ContractorCardBr() throws Exception {
    super();
    folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder");         //$NON-NLS-1$
    treeUIProperties.put("FolderType", PartnerServiceLocal.FOLDER_PART); //$NON-NLS-1$
    tree.setParentPropertyName("Contractor.FolderId"); //$NON-NLS-1$
    restrictionFormName = "com/mg/merp/settlement/resources/ContractorCardRest.mfd.xml"; //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#initializeMaster(java.io.Serializable)
   */
  @Override
  protected void initializeMaster(PersistentObject master) {
    uiProperties.put("Contractor.FolderId", master); //$NON-NLS-1$
  }

	/* (non-Javadoc)
     * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#loadFolders()
	 */

  protected TreeNode loadFolders() throws ApplicationException {
    return ReferenceUtils.loadFolderHierarchy(PartnerServiceLocal.FOLDER_PART);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createModel()
   */
  @Override
  protected MaintenanceTableModel createModel() {
    return new DefaultMaintenanceEJBQLTableModel() {

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
        result.add(new TableEJBQLFieldDef(ContractorCard.class, "Id", "cc.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(ContractorCard.class, "Contractor.Code", "c.Code", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(ContractorCard.class, "Contractor.FullName", "c.FullName", false));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(ContractorCard.class, "TotalIncome", "cc.TotalIncome", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(ContractorCard.class, "TotalExpenses", "cc.TotalExpenses", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(ContractorCard.class, "DebitorInDebLimit", "cc.DebitorInDebLimit", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(ContractorCard.class, "CreditorInDebLimit", "cc.CreditorInDebLimit", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(ContractorCard.class, "PlanIncome", "cc.PlanIncome", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(ContractorCard.class, "PlanExpenses", "cc.PlanExpenses", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(ContractorCard.class, "DebitorInDebSum", "cc.DebitorInDebSum", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(ContractorCard.class, "CreditorInDebSum", "cc.CreditorInDebSum", false)); //$NON-NLS-1$ //$NON-NLS-2$
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
    Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
    String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
    paramsName.clear();
    paramsValue.clear();
    paramsName.add("orgUnit"); //$NON-NLS-1$
    paramsValue.add(companyId);
    ContractorCardRest restForm = (ContractorCardRest) getRestrictionForm();

    StringBuilder whereText = new StringBuilder().
        append(DatabaseUtils.formatEJBQLHierarchyRestriction(((HierarchyRestrictionSupport) restForm).isUseHierarchy(), "cc.Contractor.FolderId", 0, "folder", folderEntity == null ? null : folderEntity.getAttribute("Id"), paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        append(DatabaseUtils.formatEJBQLStringRestriction("c.Code", restForm.getCode(), "code", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLStringRestriction("c.FullName", restForm.getName(), "name", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRangeRestriction("cc.TotalIncome", restForm.getTotalIncomeFrom(), restForm.getTotalIncomeTo(), "totalIncomeFrom", "totalIncomeTo", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        append(DatabaseUtils.formatEJBQLObjectRangeRestriction("cc.TotalExpenses", restForm.getTotalExpensesFrom(), restForm.getTotalExpensesTo(), "totalExpensesFrom", "totalExpensesTo", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        append(DatabaseUtils.formatEJBQLObjectRangeRestriction("cc.DebitorInDebSum", restForm.getDebitorInDebSumFrom(), restForm.getDebitorInDebSumTo(), "debitorInDebSumFrom", "debitorInDebSumTo", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        append(DatabaseUtils.formatEJBQLObjectRangeRestriction("cc.CreditorInDebSum", restForm.getCreditorInDebSumFrom(), restForm.getCreditorInDebSumTo(), "creditorInDebSumFrom", "creditorInDebSumTo", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        append(DatabaseUtils.formatEJBQLObjectRangeRestriction("cc.DebitorInDebLimit", restForm.getDebitorInDebLimitFrom(), restForm.getDebitorInDebLimitTo(), "debitorInDebLimitFrom", "debitorInDebLimitTo", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        append(DatabaseUtils.formatEJBQLObjectRangeRestriction("cc.CreditorInDebLimit", restForm.getCreditorInDebLimitFrom(), restForm.getCreditorInDebLimitTo(), "creditorInDebLimitFrom", "creditorInDebLimitTo", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        append(DatabaseUtils.formatEJBQLObjectRangeRestriction("cc.PlanIncome", restForm.getPlanIncomeFrom(), restForm.getPlanIncomeTo(), "planIncomeFrom", "planIncomeTo", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        append(DatabaseUtils.formatEJBQLObjectRangeRestriction("cc.PlanExpenses", restForm.getPlanExpensesFrom(), restForm.getPlanExpensesTo(), "planExpensesFrom", "planExpensesTo", paramsName, paramsValue, false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText.toString());
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultHierarchyBrowseForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    super.doOnRun();
    PopupMenu popupMenu = view.getWidget(TABLE_WIDGET_NAME).getPopupMenu();
    popupMenu.getMenuItem(MaintenanceTable.ADD_MENU_ITEM).setVisible(false);
  }

  /**
   * @param companyId The companyId to set.
   */
  protected void setCompanyId(Integer companyId) {
    this.companyId = companyId;
  }

}
