/*
 * FinTurnAcc.java
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
package com.mg.merp.finance.support.ui;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.dataset.DataSet;
import com.mg.framework.api.metadata.ui.FieldMetadata;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.HierarchyRestrictionSupport;
import com.mg.framework.api.ui.widget.MaintenanceTable;
import com.mg.framework.api.ui.widget.PopupMenu;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.core.support.CoreUtils;
import com.mg.merp.finance.AccountServiceLocal;
import com.mg.merp.finance.model.Account;
import com.mg.merp.finance.model.TurnAccount;
import com.mg.merp.finance.totals.FinanceTotalsGate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер браузера бизнес-компонента "Остатки и оброты по счетам"
 *
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: FinTurnAccFlatBr.java,v 1.12 2009/02/16 07:46:40 sharapov Exp $
 */
public class FinTurnAccFlatBr extends AbstractFinTurnBrowseForm { //DefaultHierarchyBrowseForm {

  public FinTurnAccFlatBr() throws Exception {
    super();
    folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder"); //$NON-NLS-1$
    tree.setParentPropertyName("Folder.Id"); //$NON-NLS-1$
    treeUIProperties.put("FolderType", AccountServiceLocal.FOLDER_PART); //$NON-NLS-1$
    restrictionFormName = "com/mg/merp/finance/resources/FinTurnAccFlatRest.mfd.xml"; //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultHierarchyBrowseForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    super.doOnRun();
    PopupMenu menu = view.getWidget("table").getPopupMenu(); //$NON-NLS-1$
    menu.getMenuItem(MaintenanceTable.ADD_MENU_ITEM).setEnabled(false);
    menu.getMenuItem(MaintenanceTable.EDIT_MENU_ITEM).setEnabled(false);
    menu.getMenuItem(MaintenanceTable.CLONE_MENU_ITEM).setEnabled(false);
    menu.getMenuItem(MaintenanceTable.DEEP_CLONE_MENU_ITEM).setEnabled(false);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#initializeMaster()
   */
  @Override
  protected void initializeMaster(PersistentObject master) {
    uiProperties.put("Account.Folder", master); //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#loadFolders()
   */
  @Override
  protected TreeNode loadFolders() throws ApplicationException {
    return CoreUtils.loadFolderHierarchy(AccountServiceLocal.FOLDER_PART);
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
        result.add(new TableEJBQLFieldDef(TurnAccount.class, "Id", "ta.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TurnAccount.class, "Account", "ta.Account.Code", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TurnAccount.class, "Analytics1", "ta.Analytics1", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TurnAccount.class, "Analytics2", "ta.Analytics2", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TurnAccount.class, "Analytics3", "ta.Analytics3", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TurnAccount.class, "Analytics4", "ta.Analytics4", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TurnAccount.class, "Analytics5", "ta.Analytics5", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TurnAccount.class, "CurCode", "ta.CurCode.Code", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TurnAccount.class, "Period", "ta.Period.PName", true));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TurnAccount.class, "RemnBegCur", "ta.RemnBegCur", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TurnAccount.class, "RemnBegNat", "ta.RemnBegNat", true));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TurnAccount.class, "IncomeCur", "ta.IncomeCur", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TurnAccount.class, "IncomeNat", "ta.IncomeNat", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TurnAccount.class, "OutcomeCur", "ta.OutcomeCur", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TurnAccount.class, "OutcomeNat", "ta.OutcomeNat", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TurnAccount.class, "RemnEndCur", "ta.RemnEndCur", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TurnAccount.class, "RemnEndNat", "ta.RemnEndNat", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TurnAccount.class, "RemnBegCurPlan", "ta.RemnBegCurPlan", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TurnAccount.class, "RemnBegNatPlan", "ta.RemnBegNatPlan", true));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TurnAccount.class, "IncomeCurPlan", "ta.IncomeCurPlan", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TurnAccount.class, "IncomeNatPlan", "ta.IncomeNatPlan", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TurnAccount.class, "OutcomeCurPlan", "ta.OutcomeCurPlan", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TurnAccount.class, "OutcomeNatPlan", "ta.OutcomeNatPlan", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TurnAccount.class, "RemnEndCurPlan", "ta.RemnEndCurPlan", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TurnAccount.class, "RemnEndNatPlan", "ta.RemnEndNatPlan", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TurnAccount.class, "RemnBegCurDiff", "ta.RemnBegCurDiff", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TurnAccount.class, "RemnBegNatDiff", "ta.RemnBegNatDiff", true));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TurnAccount.class, "IncomeCurDiff", "ta.IncomeCurDiff", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TurnAccount.class, "IncomeNatDiff", "ta.IncomeNatDiff", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TurnAccount.class, "OutcomeCurDiff", "ta.OutcomeCurDiff", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TurnAccount.class, "OutcomeNatDiff", "ta.OutcomeNatDiff", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TurnAccount.class, "RemnEndCurDiff", "ta.RemnEndCurDiff", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TurnAccount.class, "RemnEndNatDiff", "ta.RemnEndNatDiff", true)); //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        FinTurnAccFlatRest restForm = (FinTurnAccFlatRest) getRestrictionForm();
        boolean restIsHierarchy = ((HierarchyRestrictionSupport) restForm).isUseHierarchy();
        Account restAccount = restForm.getSrcAcc();

        Integer restAnlId1 = restForm.getAnlLevel1SrcId();
        Integer restAnlId2 = restForm.getAnlLevel2SrcId();
        Integer restAnlId3 = restForm.getAnlLevel3SrcId();
        Integer restAnlId4 = restForm.getAnlLevel4SrcId();
        Integer restAnlId5 = restForm.getAnlLevel5SrcId();
        List<Object[]> result = new ArrayList<Object[]>();
        FinanceTotalsGate ft = new FinanceTotalsGate();
        String txtSetField[] = new String[]{"ID", "ACC_CODE", "ANL1_CODE", "ANL2_CODE", "ANL3_CODE", "ANL4_CODE", "ANL5_CODE" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
            , "CURCODE", "PNAME", "RBC", "RBN", "IC", "IN", "OC", "ON", "REC", "REN", "RBCP", "RBNP", "ICP", "INP", "OCP", "ONP", "RECP", "RENP" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$ //$NON-NLS-13$ //$NON-NLS-14$ //$NON-NLS-15$ //$NON-NLS-16$ //$NON-NLS-17$ //$NON-NLS-18$
            , "RBND", "RBCD", "IND", "ICD", "OND", "OCD", "REND", "RECD"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
        ft.setFields(txtSetField);
        if (restForm.currencyCode != null)
          ft.setCurrencyCode(restForm.currencyCode.getCode());
        if (restAccount != null) {
          ft.setAccountsId(restAccount.getId());
          if (restAnlId1 != null) {
            ft.setAnalytics(1, restAnlId1);
          }
          if (restAnlId2 != null) {
            ft.setAnalytics(2, restAnlId2);
          }
          if (restAnlId3 != null) {
            ft.setAnalytics(3, restAnlId3);
          }
          if (restAnlId4 != null) {
            ft.setAnalytics(4, restAnlId4);
          }
          if (restAnlId5 != null) {
            ft.setAnalytics(5, restAnlId5);
          }
        }
        if (restIsHierarchy == true) {
          List<Integer> list = new ArrayList<Integer>();
          Integer integ = (Integer) folderEntity.getPrimaryKey();
          list.add(integ);
          ft.setAccountFolderId(list);
        }
        ft.setPeriods(restForm.getPeriodBegin() != null ? restForm.getPeriodBegin().getId() : new Integer(0), restForm.getPeriodEnd() != null ? restForm.getPeriodEnd().getId() : new Integer(0));
        try {
          ft.open();
        } catch (Exception e) {
          logger.error("Error during load FinTurnAccFlat", e); //$NON-NLS-1$
        }
        DataSet turnDS = ft.getDataSet();
        turnDS.firstRow();
        while (!turnDS.isEndOfSet()) {
          Object[] turn = {turnDS.getValueAt(turnDS.getColIDbyName("ID")), //$NON-NLS-1$
              turnDS.getValueAt(turnDS.getColIDbyName("ACC_CODE")),                             //$NON-NLS-1$
              turnDS.getValueAt(turnDS.getColIDbyName("ANL1_CODE")),     //$NON-NLS-1$
              turnDS.getValueAt(turnDS.getColIDbyName("ANL2_CODE")),     //$NON-NLS-1$
              turnDS.getValueAt(turnDS.getColIDbyName("ANL3_CODE")),     //$NON-NLS-1$
              turnDS.getValueAt(turnDS.getColIDbyName("ANL4_CODE")),     //$NON-NLS-1$
              turnDS.getValueAt(turnDS.getColIDbyName("ANL5_CODE")), //$NON-NLS-1$
              turnDS.getValueAt(turnDS.getColIDbyName("CURCODE")), //$NON-NLS-1$
              turnDS.getValueAt(turnDS.getColIDbyName("PNAME")), //$NON-NLS-1$
              turnDS.getValueAt(turnDS.getColIDbyName("REMNBEGCUR")), //$NON-NLS-1$
              turnDS.getValueAt(turnDS.getColIDbyName("REMNBEGNAT")), //$NON-NLS-1$
              turnDS.getValueAt(turnDS.getColIDbyName("INCOMECUR")), //$NON-NLS-1$
              turnDS.getValueAt(turnDS.getColIDbyName("INCOMENAT")), //$NON-NLS-1$
              turnDS.getValueAt(turnDS.getColIDbyName("OUTCOMECUR")), //$NON-NLS-1$
              turnDS.getValueAt(turnDS.getColIDbyName("OUTCOMENAT")), //$NON-NLS-1$
              turnDS.getValueAt(turnDS.getColIDbyName("REMNENDCUR")), //$NON-NLS-1$
              turnDS.getValueAt(turnDS.getColIDbyName("REMNENDNAT")), //$NON-NLS-1$
              turnDS.getValueAt(turnDS.getColIDbyName("REMNBEGCURPLAN")), //$NON-NLS-1$
              turnDS.getValueAt(turnDS.getColIDbyName("REMNBEGNATPLAN")), //$NON-NLS-1$
              turnDS.getValueAt(turnDS.getColIDbyName("INCOMECURPLAN")), //$NON-NLS-1$
              turnDS.getValueAt(turnDS.getColIDbyName("INCOMENATPLAN")), //$NON-NLS-1$
              turnDS.getValueAt(turnDS.getColIDbyName("OUTCOMECURPLAN")), //$NON-NLS-1$
              turnDS.getValueAt(turnDS.getColIDbyName("OUTCOMENATPLAN")), //$NON-NLS-1$
              turnDS.getValueAt(turnDS.getColIDbyName("REMNENDCURPLAN")), //$NON-NLS-1$
              turnDS.getValueAt(turnDS.getColIDbyName("REMNENDNATPLAN")), //$NON-NLS-1$
              turnDS.getValueAt(turnDS.getColIDbyName("REMNBEGNATDIFF")), //$NON-NLS-1$
              turnDS.getValueAt(turnDS.getColIDbyName("REMNBEGCURDIFF")), //$NON-NLS-1$
              turnDS.getValueAt(turnDS.getColIDbyName("INCOMENATDIFF")), //$NON-NLS-1$
              turnDS.getValueAt(turnDS.getColIDbyName("INCOMECURDIFF")), //$NON-NLS-1$
              turnDS.getValueAt(turnDS.getColIDbyName("OUTCOMENATDIFF")), //$NON-NLS-1$
              turnDS.getValueAt(turnDS.getColIDbyName("OUTCOMECURDIFF")), //$NON-NLS-1$
              turnDS.getValueAt(turnDS.getColIDbyName("REMNENDNATDIFF")), //$NON-NLS-1$
              turnDS.getValueAt(turnDS.getColIDbyName("REMNENDCURDIFF")), //$NON-NLS-1$
          };
          result.add(turn);
          turnDS.nextRow();
        }
        setRowList(result);
      }

//			/*
//			 * (non-Javadoc)
//			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getColumnName(int)
//			 */
//			@Override
//			public String getColumnName(int column) {
//				switch (column) {
//				case 2: return Messages.getInstance().getMessage(Messages.ACCOUNT_ANALYTICS1);
//				case 3: return Messages.getInstance().getMessage(Messages.ACCOUNT_ANALYTICS2);
//				case 4: return Messages.getInstance().getMessage(Messages.ACCOUNT_ANALYTICS3);
//				case 5: return Messages.getInstance().getMessage(Messages.ACCOUNT_ANALYTICS4);
//				case 6: return Messages.getInstance().getMessage(Messages.ACCOUNT_ANALYTICS5);
//				default: return super.getColumnName(column);
//				}
//			}

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getColumnMetadata(int)
       */
      @Override
      public FieldMetadata getColumnMetadata(int column) {
        //для полей аналитик встроенные метаданные представляют целые значения,
        //но требуется отображение строк
        if (isAnalyticsColumn(getColumnName(column)))
          return null;
        else
          return super.getColumnMetadata(column);
      }

    };
  }

  /* (non-Javadoc)
   * @see com.mg.merp.finance.support.ui.AbstractFinTurnBrowseForm#doGetAnalitycsColumnNames()
   */
  @Override
  protected String[] doGetAnalitycsColumnNames() {
    return new String[]{
        getColumnNameByDataItem("Finance.Analytics1"),
        getColumnNameByDataItem("Finance.Analytics2"),
        getColumnNameByDataItem("Finance.Analytics3"),
        getColumnNameByDataItem("Finance.Analytics4"),
        getColumnNameByDataItem("Finance.Analytics5"),
    };
  }

}
