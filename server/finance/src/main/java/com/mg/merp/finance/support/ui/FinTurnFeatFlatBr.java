/*
 * FinTurnFeatFlatBr.java
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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import com.mg.merp.finance.FeatureServiceLocal;
import com.mg.merp.finance.model.Account;
import com.mg.merp.finance.model.TurnFeature;
import com.mg.merp.finance.totals.FinanceTotalsGate;

/**
 * Контроллер браузера бизнес-компонента "Остатки и обороты по признакам"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: FinTurnFeatFlatBr.java,v 1.14 2009/02/16 07:46:40 sharapov Exp $
 */
public class FinTurnFeatFlatBr extends AbstractFinTurnBrowseForm { //DefaultHierarchyBrowseForm {
	
	@SuppressWarnings("unchecked")
	public FinTurnFeatFlatBr() throws Exception {		
		super();
		folderService =  (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder"); //$NON-NLS-1$
		tree.setParentPropertyName("Folder.Id"); //$NON-NLS-1$
		treeUIProperties.put("FolderType", FeatureServiceLocal.FOLDER_PART); //$NON-NLS-1$
		restrictionFormName = "com/mg/merp/finance/resources/FinTurnFeatFlatRest.mfd.xml"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultHierarchyBrowseForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		super.doOnRun();
		PopupMenu menu = view.getWidget("table").getPopupMenu(); //$NON-NLS-1$
		menu.getMenuItem(MaintenanceTable.ADD_MENU_ITEM).setEnabled(false);
		menu.getMenuItem(MaintenanceTable.EDIT_MENU_ITEM).setEnabled(false);
		//TODO закрыть пункт "Копировать"
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#initializeMaster()
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
		return CoreUtils.loadFolderHierarchy(FeatureServiceLocal.FOLDER_PART);
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
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "Id", "tf.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "Account", "tf.Account.Code", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "Analytics1", "tf.Analytics1", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "Analytics2", "tf.Analytics2", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "Analytics3", "tf.Analytics3", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "Analytics4", "tf.Analytics4", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "Analytics5", "tf.Analytics5", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "Feature", "tf.Feature.Code", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "FeatureAnalytics1", "tf.FeatureAnalytics1", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "FeatureAnalytics2", "tf.FeatureAnalytics2", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "FeatureAnalytics3", "tf.FeatureAnalytics3", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "FeatureAnalytics4", "tf.FeatureAnalytics4", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "FeatureAnalytics5", "tf.FeatureAnalytics5", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "CurCode", "tf.CurCode.Code", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "Period", "tf.Period.PName", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "RemnBegCur", "tf.RemnBegCur", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "RemnBegNat", "tf.RemnBegNat", true));	 //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "IncomeCur", "tf.IncomeCur", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "IncomeNat", "tf.IncomeNat", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "OutcomeCur", "tf.OutcomeCur", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "OutcomeNat", "tf.OutcomeNat", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "RemnEndCur", "tf.RemnEndCur", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "RemnEndNat", "tf.RemnEndNat", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "RemnBegCurPlan", "tf.RemnBegCurPlan", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "RemnBegNatPlan", "tf.RemnBegNatPlan", true));	 //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "IncomeCurPlan", "tf.IncomeCurPlan", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "IncomeNatPlan", "tf.IncomeNatPlan", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "OutcomeCurPlan", "tf.OutcomeCurPlan", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "OutcomeNatPlan", "tf.OutcomeNatPlan", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "RemnEndCurPlan", "tf.RemnEndCurPlan", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "RemnEndNatPlan", "tf.RemnEndNatPlan", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "RemnBegCurDiff", "tf.RemnBegCurDiff", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "RemnBegNatDiff", "tf.RemnBegNatDiff", true));	//$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "IncomeCurDiff", "tf.IncomeCurDiff", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "IncomeNatDiff", "tf.IncomeNatDiff", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "OutcomeCurDiff", "tf.OutcomeCurDiff", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "OutcomeNatDiff", "tf.OutcomeNatDiff", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "RemnEndCurDiff", "tf.RemnEndCurDiff", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TurnFeature.class, "RemnEndNatDiff", "tf.RemnEndNatDiff", true)); //$NON-NLS-1$ //$NON-NLS-2$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
			}			

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected void doLoad() {	
				FinTurnFeatFlatRest restForm = (FinTurnFeatFlatRest) getRestrictionForm();
				boolean restIsHierarchy = ((HierarchyRestrictionSupport) restForm).isUseHierarchy();
				Account restFeatAcc = restForm.getFeatAccCode();

				Integer restAnlId1 = restForm.getFeatAnl1Code() != null ? restForm.getFeatAnl1Code().getId() : null;
				Integer restAnlId2 = restForm.getFeatAnl2Code() != null ? restForm.getFeatAnl2Code().getId() : null;
				Integer restAnlId3 = restForm.getFeatAnl3Code() != null ? restForm.getFeatAnl3Code().getId() : null;
				Integer restAnlId4 = restForm.getFeatAnl4Code() != null ? restForm.getFeatAnl4Code().getId() : null;
				Integer restAnlId5 = restForm.getFeatAnl5Code() != null ? restForm.getFeatAnl5Code().getId() : null;				
				List<Object[]> result = new ArrayList<Object[]>();
				FinanceTotalsGate ft = new FinanceTotalsGate();
				String txtSetField[] = new String[] {"ID", "ACC_CODE", "ANL1_CODE", "ANL2_CODE", "ANL3_CODE", "ANL4_CODE", "ANL5_CODE" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
						, "FEAT_CODE", "FANL1_CODE", "FANL2_CODE", "FANL3_CODE", "FANL4_CODE", "FANL5_CODE" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
						, "CURCODE", "PNAME", "RBC", "RBN","IC","IN","OC","ON","REC","REN","RBCP","RBNP","ICP","INP","OCP","ONP","RECP","RENP" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$ //$NON-NLS-13$ //$NON-NLS-14$ //$NON-NLS-15$ //$NON-NLS-16$ //$NON-NLS-17$ //$NON-NLS-18$
						, "RBND", "RBCD", "IND", "ICD", "OND", "OCD", "REND", "RECD"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
				ft.setFields(txtSetField);
				if (restFeatAcc != null){ 
					ft.setFeatureId(restFeatAcc.getId());
					if (restAnlId1 != null) {
						ft.setFeatAnalytics(1, restAnlId1);
					}
					if (restAnlId2 != null) {
						ft.setFeatAnalytics(2, restAnlId1);
					}
					if (restAnlId3 != null) {
						ft.setFeatAnalytics(3, restAnlId1);
					}
					if (restAnlId4 != null) {
						ft.setFeatAnalytics(4, restAnlId1);
					}
					if (restAnlId5 != null) {
						ft.setFeatAnalytics(5, restAnlId1);
					}
				}
				if (restIsHierarchy == true) {
					List<Integer> list = new ArrayList<Integer>();
					Integer integ = (Integer)folderEntity.getPrimaryKey();
					list.add(integ);
					ft.setFeatureFolderId(list);
				}
				ft.setPeriods(restForm.getPeriodBegin() != null ? restForm.getPeriodBegin().getId() : new Integer(0), restForm.getPeriodEnd() != null ? restForm.getPeriodEnd().getId() : new Integer(0));
				try {
					ft.open();
				} catch (Exception e) {
					logger.error("Error during load FinTurnFeatFlat", e); //$NON-NLS-1$
				}
				DataSet turnDS = ft.getDataSet();
				turnDS.firstRow();
				while(!turnDS.isEndOfSet()) {
					Object[] turn = {turnDS.getValueAt(turnDS.getColIDbyName("ID")), //$NON-NLS-1$
							turnDS.getValueAt(turnDS.getColIDbyName("ACC_CODE")), //$NON-NLS-1$
							turnDS.getValueAt(turnDS.getColIDbyName("ANL1_CODE")), //$NON-NLS-1$
							turnDS.getValueAt(turnDS.getColIDbyName("ANL2_CODE")), //$NON-NLS-1$
							turnDS.getValueAt(turnDS.getColIDbyName("ANL3_CODE")), //$NON-NLS-1$
							turnDS.getValueAt(turnDS.getColIDbyName("ANL4_CODE")), //$NON-NLS-1$
							turnDS.getValueAt(turnDS.getColIDbyName("ANL5_CODE")), //$NON-NLS-1$
							turnDS.getValueAt(turnDS.getColIDbyName("FEAT_CODE")), //$NON-NLS-1$
							turnDS.getValueAt(turnDS.getColIDbyName("FANL1_CODE")), //$NON-NLS-1$
							turnDS.getValueAt(turnDS.getColIDbyName("FANL2_CODE")), //$NON-NLS-1$
							turnDS.getValueAt(turnDS.getColIDbyName("FANL3_CODE")), //$NON-NLS-1$
							turnDS.getValueAt(turnDS.getColIDbyName("FANL4_CODE")), //$NON-NLS-1$
							turnDS.getValueAt(turnDS.getColIDbyName("FANL5_CODE")), //$NON-NLS-1$
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
		return new String[] {
				getColumnNameByDataItem("Finance.Analytics1"),
				getColumnNameByDataItem("Finance.Analytics2"),
				getColumnNameByDataItem("Finance.Analytics3"),
				getColumnNameByDataItem("Finance.Analytics4"),
				getColumnNameByDataItem("Finance.Analytics5"),
				getColumnNameByDataItem("Finance.FeatAnalytics1"),
				getColumnNameByDataItem("Finance.FeatAnalytics2"),
				getColumnNameByDataItem("Finance.FeatAnalytics3"),
				getColumnNameByDataItem("Finance.FeatAnalytics4"),
				getColumnNameByDataItem("Finance.FeatAnalytics5"),
			};
	}

}
