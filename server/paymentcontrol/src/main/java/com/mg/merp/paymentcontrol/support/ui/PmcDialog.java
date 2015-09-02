/*
 * PmcDialog.java
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
package com.mg.merp.paymentcontrol.support.ui;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.MaintenanceFormActionListener;
import com.mg.framework.api.ui.MaintenanceFormEvent;
import com.mg.framework.api.ui.RestrictionForm;
import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.TreeChangeEvent;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.MaintenanceTable;
import com.mg.framework.api.ui.widget.PopupMenu;
import com.mg.framework.generic.ui.AbstractForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultTreeModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.MaintenanceTreeController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.TreeSelectionListener;
import com.mg.framework.support.ui.widget.tree.EntityTreeNode;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.paymentcontrol.ExecutionServiceLocal;
import com.mg.merp.paymentcontrol.LiabilityModelServiceLocal;
import com.mg.merp.paymentcontrol.LiabilityServiceLocal;
import com.mg.merp.paymentcontrol.PmcHelperListener;
import com.mg.merp.paymentcontrol.ResourceServiceLocal;
import com.mg.merp.paymentcontrol.model.Execution;
import com.mg.merp.paymentcontrol.model.Liability;
import com.mg.merp.paymentcontrol.model.PmcResource;
import com.mg.merp.reference.support.ReferenceUtils;

/**
 * Контроллер формы "Управление платежами"
 * 
 * @author Artem V. Sharapov
 * @version $Id: PmcDialog.java,v 1.11 2007/09/10 11:19:21 sharapov Exp $
 */
public class PmcDialog extends AbstractForm {

	// Fields

	private Date dateAct;
	private boolean isExecutionByLiability;
	
	private boolean isShowExecutedSum;
	private BigDecimal executedSum = BigDecimal.ZERO;
	
	private BigDecimal resourceBalanceSum = BigDecimal.ZERO;
	private boolean isShowResourceBalanceSum;

	private PersistentObject folderEntity = null;
	private MaintenanceTreeController resourceTree;
	private AttributeMap treeUIProperties = new LocalDataTransferObject();
	private DataBusinessObjectService folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder"); //$NON-NLS-1$

	private MaintenanceTableController resourceTable;
	private AttributeMap resourceServiceProperties = new LocalDataTransferObject();
	private ResourceServiceLocal resourceService = (ResourceServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/paymentcontrol/Resource"); //$NON-NLS-1$
	private PmcResourceRest resourceRestForm = (PmcResourceRest) UIProducer.produceForm("com/mg/merp/paymentcontrol/resources/PmcResourceRest.mfd.xml"); //$NON-NLS-1$
	
	private MaintenanceTableController liabilityTable;
	private AttributeMap liabilityServiceProperties = new LocalDataTransferObject();
	private LiabilityServiceLocal liabilityService = (LiabilityServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/paymentcontrol/Liability"); //$NON-NLS-1$
	private LiabilityRest liabilityRestForm = (LiabilityRest) UIProducer.produceForm("com/mg/merp/paymentcontrol/resources/LiabilityRest.mfd.xml"); //$NON-NLS-1$

	private MaintenanceTableController executionTable;
	private AttributeMap executionServiceProperties = new LocalDataTransferObject();
	private ExecutionServiceLocal executionService = (ExecutionServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/paymentcontrol/Execution"); //$NON-NLS-1$

	// Default constructor
	public PmcDialog() {
	}

	// Methods

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		dateAct = Calendar.getInstance().getTime();
		liabilityRestForm.setDateToExecuteEnabled(false);
		resourceRestForm.setActDateEnabled(false);

		resourceTree = new MaintenanceTreeController(treeUIProperties, new DefaultTreeModel() {
			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultTreeModel#getRootNode()
			 */
			@Override
			public TreeNode getRootNode() {
				return ReferenceUtils.loadFolderHierarchy(ResourceServiceLocal.FOLDER_PART);
			}
		});

		resourceTree.initController(folderService);
		treeUIProperties.put("FolderType", ResourceServiceLocal.FOLDER_PART); //$NON-NLS-1$
		resourceTree.setParentPropertyName("Folder.Id"); //$NON-NLS-1$
		resourceTree.addTreeSelectionListener(new TreeSelectionListener(){
			public void valueChanged(TreeChangeEvent event) {
				folderEntity = ((EntityTreeNode) event.getNode()).getEntity();
				resourceServiceProperties.clear();
				resourceServiceProperties.put("Folder", folderEntity); //$NON-NLS-1$
			}
		});

		resourceTable = new MaintenanceTableController(resourceServiceProperties);
		resourceTable.initController(resourceService, new DefaultMaintenanceEJBQLTableModel() {

			private final String INIT_QUERY_TEXT = "select distinct %s from PmcResource pr %s %s"; //$NON-NLS-1$
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();

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
				result.add(new TableEJBQLFieldDef(PmcResource.class, "Id", "pr.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PmcResource.class, "OrgUnit", "pr.OrgUnit.Code", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PmcResource.class, "Name", "pr.Name", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PmcResource.class, "Description", "pr.Description", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PmcResource.class, "Catalog", "cat.Code", "left join pr.Catalog as cat", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(PmcResource.class, "ActDateFrom", "pr.ActDateFrom", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PmcResource.class, "ActDateTill", "pr.ActDateTill", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PmcResource.class, "CurCode", "cur.Code", "left join pr.CurCode as cur", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(PmcResource.class, "CurRateAuthority", "ca.Code", "left join pr.CurRateAuthority as ca", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(PmcResource.class, "CurRateType", "ct.Code", "left join pr.CurRateType as ct", false));				 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, resourceService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));			
			}

			protected String createQueryText() {
				view.flushForm();
				Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) resourceTable.getModel()).getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);				
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);

				paramsName.clear();
				paramsValue.clear();
				StringBuilder whereText = new StringBuilder("where") //$NON-NLS-1$
					.append(DatabaseUtils.formatEJBQLHierarchyRestriction(resourceRestForm.isUseHierarchy(), "pr.Folder", 0, "folder", folderEntity, paramsName, paramsValue, true)) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					.append(DatabaseUtils.formatEJBQLStringRestriction("pr.Name", resourceRestForm.getName(), "Name", paramsName, paramsValue, false)); //$NON-NLS-1$ //$NON-NLS-2$

				if(dateAct != null) {
					paramsName.add("actDate"); //$NON-NLS-1$
					paramsValue.add(dateAct);
					whereText.append(" and (pr.ActDateFrom <= :actDate or pr.ActDateFrom is null) and (pr.ActDateTill >= :actDate or pr.ActDateTill is null)"); //$NON-NLS-1$
				}
				return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
			 */
			@Override
			public void setSelectedRows(int[] rows) {
				super.setSelectedRows(rows);
				if(rows != null && rows.length > 0)
					refreshResourceBalanceSum((Integer) rowList.get(rows[0])[0]);
				else
					refreshResourceBalanceSum(null);
			}
			
		});
		resourceTree.addMasterModelListener(resourceTable);
		resourceTable.setRestrictionForm(resourceRestForm);
		resourceTable.refresh();

		
		liabilityServiceProperties.put("Folder", liabilityService.getRootFolder()); //$NON-NLS-1$
		liabilityTable = new MaintenanceTableController(liabilityServiceProperties);
		liabilityTable.initController(liabilityService, new DefaultMaintenanceEJBQLTableModel() {

			private final String INIT_QUERY_TEXT = "select distinct %s from Liability l %s where l.IsModel = 0 AND l.Version is NULL %s"; //$NON-NLS-1$
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();

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
				result.add(new TableEJBQLFieldDef(Liability.class, "Id", "l.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Liability.class, "Priority", "l.Priority", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Liability.class, "Num", "l.Num", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Liability.class, "Receivable", "l.Receivable", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Liability.class, "RegDate", "l.RegDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Liability.class, "DateToExecute", "l.DateToExecute", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Liability.class, "SumCur", "l.SumCur", false));				 //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Liability.class, "From", "f.Code", "left join l.From as f", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Liability.class, "FromBankAcc", "fa.Name", "left join l.FromBankAcc as fa", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Liability.class, "To", "t.Code", "left join l.To as t", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Liability.class, "ToBankAcc", "ta.Name", "left join l.ToBankAcc as ta", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Liability.class, "CurCode", "l.CurCode.Code", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Liability.class, "CurRateAuthority", "ca.Code", "left join l.CurRateAuthority as ca", false));				 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Liability.class, "CurRateType", "ct.Code", "left join l.CurRateType as ct", false));					 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Liability.class, "PaymentDelay", "l.PaymentDelay", false));					 //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Liability.class, "DocDate", "l.DocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Liability.class, "DocNumber", "l.DocNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Liability.class, "DocType", "dt.Code", "left join l.DocType as dt", false));				 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Liability.class, "BaseDocDate", "l.BaseDocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Liability.class, "BaseDocNumber", "l.BaseDocNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Liability.class, "BaseDocType", "bdt.Code", "left join l.BaseDocType as bdt", false));				 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Liability.class, "ContractDate", "l.ContractDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Liability.class, "ContractNumber", "l.ContractNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Liability.class, "ContractType", "crt.Code", "left join l.ContractType as crt", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Liability.class, "Comments", "l.Comments", false));				 //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Liability.class, "PrefResource", "pr.Name", "left join l.PrefResource as pr", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Liability.class, "PrefResourceFolder", "prf.FName", "left join l.PrefResourceFolder as prf", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, liabilityService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}

			protected String createQueryText() {
				view.flushForm();
				Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) liabilityTable.getModel()).getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);				
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);

				paramsName.clear();
				paramsValue.clear();
				StringBuilder whereText = new StringBuilder()
				.append(DatabaseUtils.formatEJBQLHierarchyRestriction(false, "l.Folder", 0, "folder", folderEntity, paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
				.append(DatabaseUtils.formatEJBQLObjectRangeRestriction("l.RegDate", liabilityRestForm.getRegDate1(), liabilityRestForm.getRegDate2(), "regDate1", "regDate2", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				.append(DatabaseUtils.formatEJBQLObjectRangeRestriction("l.SumCur", liabilityRestForm.getSum1(), liabilityRestForm.getSum2(), "sum1", "sum2", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				.append(DatabaseUtils.formatEJBQLObjectRestriction("l.To", liabilityRestForm.getToCode(), "toCode", paramsName, paramsValue, false))	 //$NON-NLS-1$ //$NON-NLS-2$
				.append(DatabaseUtils.formatEJBQLObjectRestriction("l.From", liabilityRestForm.getFromCode(), "fromCode", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
				.append(DatabaseUtils.formatEJBQLAddinFieldsRestriction(liabilityService, "l.Id", liabilityRestForm.getAddinFieldsRestriction(), false)); //$NON-NLS-1$

				if(dateAct != null)
					whereText.append(DatabaseUtils.formatEJBQLObjectRangeRestriction("l.DateToExecute", null, dateAct, null, "actDate", paramsName, paramsValue, false)); //$NON-NLS-1$ //$NON-NLS-2$

				if(liabilityRestForm.isLesExecuted() || liabilityRestForm.isLesPartExecuted() || liabilityRestForm.isLesNotExecuted()) {
					boolean isFirstCondition = true;
					final String initSubQueryText =  " (select nvl(sum(e1.SumCur), 0) from Execution e1 where e1.Liability = l) "; //$NON-NLS-1$
					whereText.append(" and "); //$NON-NLS-1$

					if(liabilityRestForm.isLesExecuted()) {
						whereText.append(initSubQueryText).append(" >= l.SumCur "); //$NON-NLS-1$
						isFirstCondition = false;
					}
					if(liabilityRestForm.isLesPartExecuted()) {
						if(!isFirstCondition)
							whereText.append(" or "); //$NON-NLS-1$
						whereText.append(initSubQueryText).append(" < l.SumCur and ").append(initSubQueryText).append(" > 0 "); //$NON-NLS-1$ //$NON-NLS-2$
						isFirstCondition = false;
					}
					if(liabilityRestForm.isLesNotExecuted()) {
						if(!isFirstCondition)
							whereText.append(" or "); //$NON-NLS-1$
						whereText.append(initSubQueryText).append(" = 0 "); //$NON-NLS-1$
					}
				}
				return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText.toString());	
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
			 */
			@Override
			public void setSelectedRows(int[] rows) {
				super.setSelectedRows(rows);
				if(rows != null && rows.length > 0)
					refreshExecutedSum((Integer) rowList.get(rows[0])[0]);
				else
					refreshExecutedSum(null);
			}
			
		});
		liabilityTable.setRestrictionForm(liabilityRestForm);
		liabilityTable.refresh();

		executionTable = new MaintenanceTableController(executionServiceProperties);
		executionTable.initController(executionService, new DefaultMaintenanceEJBQLTableModel() {

			private final String INIT_QUERY_TEXT = "select %s from Execution e %s %s"; //$NON-NLS-1$
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();

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
				result.add(new TableEJBQLFieldDef(Execution.class, "Id", "e.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Execution.class, "PlanDate", "e.PlanDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Execution.class, "FactDate", "e.FactDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Execution.class, "SumNat", "e.SumNat", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Execution.class, "SumCur", "e.SumCur", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Execution.class, "CurCode", "e.CurCode.Code", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Execution.class, "CurRateAuthority", "ca.Code", "left join e.CurRateAuthority as ca", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Execution.class, "CurRateType", "ct.Code", "left join e.CurRateType as ct", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Execution.class, "ResourceFolder", "f.FName", "left join e.ResourceFolder as f", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Execution.class, "Resource", "r.Name", "left join e.Resource as r", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Execution.class, "Receivable", "e.Receivable", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Execution.class, "DocCreated", "e.DocCreated", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Execution.class, "DocProcessed", "e.DocProcessed", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Execution.class, "Approved", "e.Approved", false)); //$NON-NLS-1$ //$NON-NLS-2$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, executionService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}

			protected String createQueryText() {
				view.flushForm();
				Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) executionTable.getModel()).getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);				
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);

				paramsName.clear();
				paramsValue.clear();
				StringBuilder whereText = new StringBuilder();
				
				if(dateAct != null) {  
					whereText.append(" where ");
					whereText.append(DatabaseUtils.formatEJBQLObjectRangeRestriction("e.PlanDate", null, dateAct, null, "actDate", paramsName, paramsValue, true)); //$NON-NLS-1$ //$NON-NLS-2$
				}

				if(isExecutionByLiability) {
					Serializable[] liabilityIds = ((DefaultMaintenanceEJBQLTableModel) liabilityTable.getModel()).getSelectedPrimaryKeys();
					if(liabilityIds != null && liabilityIds.length > 0) {
						Integer liabilityId = (Integer) liabilityIds[0];
						if(dateAct == null) {
							whereText.append(" where ");
							whereText.append(DatabaseUtils.formatEJBQLObjectRestriction("e.Liability.Id", liabilityId, "liabilityId", paramsName, paramsValue, true)); //$NON-NLS-1$ //$NON-NLS-2$
						}
						else
							whereText.append(DatabaseUtils.formatEJBQLObjectRestriction("e.Liability.Id", liabilityId, "liabilityId", paramsName, paramsValue, false)); //$NON-NLS-1$ //$NON-NLS-2$
					}
				}
				return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);	
			}
		});
		executionTable.refresh();

		liabilityTable.addMasterModelListener(executionTable);

		super.doOnRun();
		PopupMenu executionMenu = view.getWidget("executionTable").getPopupMenu(); //$NON-NLS-1$
		executionMenu.getMenuItem(MaintenanceTable.ADD_MENU_ITEM).setVisible(false);
		executionMenu.getMenuItem(MaintenanceTable.ADD_MENU_ITEM).setEnabled(false);
	}

	/**
	 * Обработчик кнопки/пункта КМ "Исполнить обязательство"
	 * @param event - событие 
	 */
	public void onActionExecuteLiability(WidgetEvent event) {
		Serializable[] resourceIds = ((DefaultMaintenanceEJBQLTableModel) resourceTable.getModel()).getSelectedPrimaryKeys();
		Serializable[] liabilityIds = ((DefaultMaintenanceEJBQLTableModel) liabilityTable.getModel()).getSelectedPrimaryKeys();
		if(resourceIds != null && resourceIds.length > 0 && liabilityIds != null && liabilityIds.length > 0 && dateAct != null)
			PmcHelper.executeLiabilityByPmcManagment((Integer) resourceIds[0], (Integer) liabilityIds[0], dateAct, new PmcHelperListener() {

				public void complete() {
					executionTable.refresh();
				}
			});
	}

	/**
	 * Обработчик кнопки "Переместить средства"
	 * @param event - событие 
	 */
	public void onActionTransferResources(WidgetEvent event) {
		Serializable[] resourceIds = ((DefaultMaintenanceEJBQLTableModel) resourceTable.getModel()).getSelectedPrimaryKeys();
		if(resourceIds == null || resourceIds.length != 1)
			return;
		else 
			PmcHelper.transferResourses((Integer) resourceIds[0], null, null, dateAct, new PmcHelperListener() {

				public void complete() {
					executionTable.refresh();
					liabilityTable.refresh();
				}
			});
	}

	/**
	 * Обработчик кнопки "Сформировать документы"
	 * @param event - событие
	 */
	public void onActionCreateDocuments(WidgetEvent event) {
		Serializable[] executionIds = ((DefaultMaintenanceEJBQLTableModel) executionTable.getModel()).getSelectedPrimaryKeys();
		if(executionIds != null && executionIds.length > 0 && dateAct != null)
			PmcHelper.createDocuments(executionIds, dateAct, new PmcHelperListener() {

				public void complete() {
					executionTable.refresh();
				}
			});
	}

	/**
	 * Обработчик пункта КМ "Утвердить"
	 * @param event - событие
	 */
	public void onActionApprove(WidgetEvent event) {
		Serializable[] executionIds = ((DefaultMaintenanceEJBQLTableModel) executionTable.getModel()).getSelectedPrimaryKeys();
		if(executionIds != null && executionIds.length > 0) {
			executionService.setApproved(executionIds, true);
			executionTable.refresh();
		}
	}

	/**
	 * Обработчик пункта КМ "Снять утверждение"
	 * @param event - событие
	 */
	public void onActionDisApprove(WidgetEvent event) {
		Serializable[] executionIds = ((DefaultMaintenanceEJBQLTableModel) executionTable.getModel()).getSelectedPrimaryKeys();
		if(executionIds != null && executionIds.length > 0) {
			executionService.setApproved(executionIds, false);
			executionTable.refresh();
		}
	}

	/**
	 * Обработчик пункта КМ "Показать созданный документ"
	 * @param event - событие
	 */
	public void onActionShowCreatedDocument(WidgetEvent event) {
		Serializable[] executionIds = ((DefaultMaintenanceEJBQLTableModel) executionTable.getModel()).getSelectedPrimaryKeys();
		if(executionIds != null && executionIds.length > 0)
			executionService.showCreatedDocument((Integer) executionIds[0]);
	}

	/**
	 * Обработчик пункта КМ "Удалить созданный документ"
	 * @param event - событие
	 */
	public void onActionDeleteCreatedDocument(WidgetEvent event) {
		Serializable[] executionIds = ((DefaultMaintenanceEJBQLTableModel) executionTable.getModel()).getSelectedPrimaryKeys();
		if(executionIds != null && executionIds.length > 0) {
			PmcHelper.deleteCreatedDocument(executionIds, new PmcHelperListener() {

				public void complete() {
					executionTable.refresh();
				}
			});
		}
	}

	/**
	 * Обработчик кнопки "Обновить"
	 * @param event - событие
	 */
	public void onActionRefreshByDate(WidgetEvent event) {
		executionTable.refresh();
		liabilityTable.refresh();
		resourceTable.refresh();
	}

	/**
	 * Обработчик события изменения значения
	 * @param event - событие
	 */
	public void onActionRefreshByLiability(WidgetEvent event) {
		executionTable.refresh();
	}
	
	/**
	 * Обработчик пункта КМ "Поддержка образцов"
	 * @param event - событие
	 * @throws Exception - ИС
	 */
	public void onActionShowLiabilityModel(WidgetEvent event) throws Exception {
		final LiabilityModelServiceLocal service = (LiabilityModelServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/paymentcontrol/LiabilityModel"); //$NON-NLS-1$
		LiabilityModelBr form = (LiabilityModelBr) ApplicationDictionaryLocator.locate().getBrowseForm(service, null);
		form.run();
	}

	/**
	 * Обработчик пункта КМ "Вставка с образцом"
	 * @param event - событие
	 * @throws Exception - ИС
	 */
	public void onActionInsertModel(WidgetEvent event) throws Exception {
		SearchHelp searchHelp = SearchHelpProcessor.createSearch("com.mg.merp.paymentcontrol.support.ui.LiabilityModelSearchHelp"); //$NON-NLS-1$
		searchHelp.addSearchHelpListener(new SearchHelpListener() {

			public void searchPerformed(SearchHelpEvent event) {
				doOnActionInsertModel((Liability) event.getItems()[0]);
			}

			public void searchCanceled(SearchHelpEvent event) {
				//do nothing
			}
		});
		searchHelp.search();
	}

	/**
	 * Создание обязательства по образцу
	 * @param model - образец
	 */
	private void doOnActionInsertModel(Liability model) {
		Liability liability = liabilityService.createByPattern(model, liabilityService.getRootFolder());
		MaintenanceHelper.add(liabilityService, liability, null, new MaintenanceFormActionListener() {

			/* (non-Javadoc)
			 * @see com.mg.framework.api.ui.MaintenanceFormActionListener#canceled(com.mg.framework.api.ui.MaintenanceFormEvent)
			 */
			public void canceled(MaintenanceFormEvent event) {
				// do nothing
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.api.ui.MaintenanceFormActionListener#performed(com.mg.framework.api.ui.MaintenanceFormEvent)
			 */
			public void performed(MaintenanceFormEvent event) {
				liabilityTable.refresh();		
			}
		});
	}
	
	/**
	 * Обработчик измения поля "Показывать исполненную сумму"
	 * @param event - событие
	 */
	public void onActionRefreshByExecutedSum(WidgetEvent event) {
		Serializable[] liabilityIds = ((DefaultMaintenanceEJBQLTableModel) liabilityTable.getModel()).getSelectedPrimaryKeys();
		if(liabilityIds != null && liabilityIds.length > 0)
			refreshExecutedSum((Integer) liabilityIds[0]);
	}
	
	/**
	 * Обработчик измения поля "Показывать остаток текущего средства платежа"
	 * @param event - событие
	 */
	public void onActionRefreshByResourceBalanceSum(WidgetEvent event) {
		Serializable[] resourceIds = ((DefaultMaintenanceEJBQLTableModel) resourceTable.getModel()).getSelectedPrimaryKeys();
		if(resourceIds != null && resourceIds.length > 0)
			refreshResourceBalanceSum((Integer) resourceIds[0]);
	}
	
	private void refreshExecutedSum(Integer liabilityId) {
		if(isShowExecutedSum && liabilityId != null) 
			executedSum = liabilityService.getExecutedSum(liabilityId, null);
		else
			executedSum = BigDecimal.ZERO;
		view.flushModel();
	}
	
	private void refreshResourceBalanceSum(Integer resourceId) {
		if(isShowResourceBalanceSum && resourceId != null && dateAct != null) 
			resourceBalanceSum = resourceService.getBalance(resourceId, dateAct);
		else
			resourceBalanceSum = BigDecimal.ZERO;
		view.flushModel();
	}

	/**
	 * @return the dateAct
	 */
	public Date getDateAct() {
		return dateAct;
	}

	/**
	 * @param dateAct the dateAct to set
	 */
	public void setDateAct(Date dateAct) {
		this.dateAct = dateAct;
	}

	public boolean isExecutionByLiability() {
		return isExecutionByLiability;
	}

	public void setExecutionByLiability(boolean isExecutionByLiability) {
		this.isExecutionByLiability = isExecutionByLiability;
	}

}
