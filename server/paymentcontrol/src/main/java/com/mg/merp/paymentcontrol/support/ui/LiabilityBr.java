/*
 * LiabilityBr.java
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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.HierarchyRestrictionSupport;
import com.mg.framework.api.ui.MaintenanceFormActionListener;
import com.mg.framework.api.ui.MaintenanceFormEvent;
import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultHierarchyBrowseForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.core.model.Folder;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.support.DocumentUtils;
import com.mg.merp.paymentcontrol.LiabilityModelServiceLocal;
import com.mg.merp.paymentcontrol.LiabilityServiceLocal;
import com.mg.merp.paymentcontrol.model.Liability;
import com.mg.merp.reference.support.ReferenceUtils;

/**
 * Контроллер браузера "Реестр обязательств"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: LiabilityBr.java,v 1.14 2007/06/05 15:10:15 sharapov Exp $
 */
public class LiabilityBr extends DefaultHierarchyBrowseForm {

	private final String INIT_QUERY_TEXT = "select distinct %s from Liability l %s where l.IsModel = 0 and l.Version is NULL %s"; //$NON-NLS-1$
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();

	public LiabilityBr() throws Exception {
		super();		
		folderService =  (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder"); //$NON-NLS-1$
		treeUIProperties.put("FolderType", LiabilityServiceLocal.FOLDER_PART); //$NON-NLS-1$
		tree.setParentPropertyName("Folder.Id"); //$NON-NLS-1$
		restrictionFormName = "com/mg/merp/paymentcontrol/resources/LiabilityRest.mfd.xml"; //$NON-NLS-1$
		uiProperties.put("IsShared", true); //$NON-NLS-1$
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
		return ReferenceUtils.loadFolderHierarchy(LiabilityServiceLocal.FOLDER_PART);
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
				result.add(new TableEJBQLFieldDef(Liability.class, "Id", "l.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Liability.class, "Priority", "l.Priority", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Liability.class, "Num", "l.Num", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Liability.class, "Receivable", "l.Receivable", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Liability.class, "RegDate", "l.RegDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Liability.class, "DateToExecute", "l.DateToExecute", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Liability.class, "SumCur", "l.SumCur", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Liability.class, "From", "f.Code", "left join l.From as f", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Liability.class, "FromBankAcc", "fa.Name", "left join l.FromBankAcc as fa", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Liability.class, "To", "t.Code", "left join l.To as t", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Liability.class, "ToBankAcc", "ta.Name", "left join l.ToBankAcc as ta", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Liability.class, "CurCode", "l.CurCode.Code", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Liability.class, "CurRateAuthority", "ca.Code", "left join l.CurRateAuthority as ca", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Liability.class, "CurRateType", "ct.Code", "left join l.CurRateType as ct", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Liability.class, "PaymentDelay", "l.PaymentDelay", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Liability.class, "DocDate", "l.DocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Liability.class, "DocNumber", "l.DocNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Liability.class, "DocType", "dt.Code", "left join l.DocType as dt", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Liability.class, "BaseDocDate", "l.BaseDocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Liability.class, "BaseDocNumber", "l.BaseDocNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Liability.class, "BaseDocType", "bdt.Code", "left join l.BaseDocType as bdt", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Liability.class, "ContractDate", "l.ContractDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Liability.class, "ContractNumber", "l.ContractNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Liability.class, "ContractType", "crt.Code", "left join l.ContractType as crt", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Liability.class, "Comments", "l.Comments", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Liability.class, "PrefResource", "pr.Name", "left join l.PrefResource as pr", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Liability.class, "PrefResourceFolder", "prf.FName", "left join l.PrefResourceFolder as prf", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
		LiabilityRest liabilityRestForm = (LiabilityRest) getRestrictionForm();
		StringBuilder whereText = new StringBuilder()
		.append(DatabaseUtils.formatEJBQLHierarchyRestriction(((HierarchyRestrictionSupport) liabilityRestForm).isUseHierarchy(), "l.Folder", 0, "folder", folderEntity, paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
		.append(DatabaseUtils.formatEJBQLObjectRangeRestriction("l.RegDate", liabilityRestForm.getRegDate1(), liabilityRestForm.getRegDate2(), "regDate1", "regDate2", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		.append(DatabaseUtils.formatEJBQLObjectRangeRestriction("l.DateToExecute", liabilityRestForm.getDateToExecute1(), liabilityRestForm.getDateToExecute2(), "dateToExecute1", "dateToExecute2", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		.append(DatabaseUtils.formatEJBQLObjectRangeRestriction("l.SumCur", liabilityRestForm.getSum1(), liabilityRestForm.getDateToExecute2(), "sum1", "sum2", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		.append(DatabaseUtils.formatEJBQLObjectRestriction("l.To", liabilityRestForm.getToCode(), "toCode", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
		.append(DatabaseUtils.formatEJBQLObjectRestriction("l.From", liabilityRestForm.getFromCode(), "fromCode", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
		.append(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "l.Id", liabilityRestForm.getAddinFieldsRestriction(), false)); //$NON-NLS-1$

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
		return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);	
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
		LiabilityServiceLocal service = (LiabilityServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/paymentcontrol/Liability"); //$NON-NLS-1$
		Liability liability = service.createByPattern(model, (Folder) folderEntity);
		liability.setIsShared(true);
		MaintenanceHelper.add(service, liability, null, new MaintenanceFormActionListener() {

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
				table.refresh();		
			}
		});
	}

	/**
	 * Обработчик пункта КМ "Просмотреть документ"
	 * @param event - событие
	 */
	public void onActionViewDoc(WidgetEvent event) {
		viewDocument(getLiabilityService().findDoc(getSelectedEntity()));
	}	 

	/**
	 * Обработчик пункта КМ "Просмотреть документ-основание"
	 * @param event - событие
	 */
	public void onActionViewBaseDoc(WidgetEvent event) {
		viewDocument(getLiabilityService().findBaseDoc(getSelectedEntity()));
	}

	/**
	 * Обработчик пункта КМ "Просмотреть контракт"
	 * @param event - событие
	 */
	public void onActionViewContract(WidgetEvent event) {
		viewDocument(getLiabilityService().findContract(getSelectedEntity()));
	}

	/**
	 * Показать документ в режиме просмотра
	 * @param docHead - документ
	 */
	@SuppressWarnings("unchecked") //$NON-NLS-1$
	private void viewDocument(DocHead docHead) {
		if(docHead != null) 
			MaintenanceHelper.view(DocumentUtils.getDocumentService(docHead.getDocSection()), docHead.getId(), null, null);
	}

	private Liability getSelectedEntity() {
		PersistentObject[] selectedEntities = getSearchedEntities();
		if(selectedEntities != null && selectedEntities.length > 0)
			return (Liability) selectedEntities[0];
		else
			return null;
	}

	private LiabilityServiceLocal getLiabilityService() {
		return (LiabilityServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/paymentcontrol/Liability"); //$NON-NLS-1$
	}

}
