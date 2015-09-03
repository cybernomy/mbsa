/*
 * ContractBr.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.contract.support.ui;

import java.util.Set;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.contract.ContractServiceLocal;
import com.mg.merp.contract.model.Contract;
import com.mg.merp.core.support.CoreUtils;
import com.mg.merp.document.generic.ui.DocumentBrowseForm;
import com.mg.merp.document.generic.ui.DocumentMaintenanceEJBQLTableModel;

/**
 * Контроллер браузера контрактов
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ContractBr.java,v 1.13 2009/02/10 14:36:31 safonov Exp $
 */
public class ContractBr extends DocumentBrowseForm {

	protected String INIT_QUERY_TEXT = "select distinct %s from Contract d %s %s  order by d.DocDate, d.Id "; //$NON-NLS-1$

	public ContractBr() throws Exception{
		super();
		folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder"); //$NON-NLS-1$
		treeUIProperties.put("FolderType", ContractServiceLocal.FOLDER_PART); //$NON-NLS-1$
		restrictionFormName = "com/mg/merp/contract/resources/ContractRest.mfd.xml"; //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultHierarchyBrowseForm#loadFolders()
	 */
	@Override
	protected TreeNode loadFolders() throws ApplicationException {
		return CoreUtils.loadFolderHierarchy(ContractServiceLocal.FOLDER_PART);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
	 */
	@Override
	protected String createQueryText() {
		super.createQueryText();		
		fieldDefs = ((DocumentMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
		//String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
		ContractRest restDocument = (ContractRest) getRestrictionForm();		

		whereText.append(DatabaseUtils.formatEJBQLObjectRestriction("d.Responsible", restDocument.getResponsible(), "responsible", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
		append(DatabaseUtils.formatEJBQLObjectRestriction("d.ContractorResponsible", restDocument.getContrResponsible(), "contrResponsible", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
		append(DatabaseUtils.formatEJBQLObjectRestriction("d.Status", restDocument.getStatus(), "status", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
		append(DatabaseUtils.formatEJBQLStringRestriction("d.IncomingNumber", restDocument.getIncomingNumber(), "incomingNumber", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
		append(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "d.Id", restDocument.getAddinFieldsRestriction(), false)); //$NON-NLS-1$
		if (restDocument.getDocModifyDate() != null || restDocument.getDocModifyType() != null || !restDocument.getDocModifyNumber().equals("")) { //$NON-NLS-1$
			whereText.append(DatabaseUtils.formatEJBQLObjectRestriction("dm.DocType", restDocument.getDocModifyType(), "docModifyType", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
			append(DatabaseUtils.formatEJBQLObjectRestriction("dm.DocDate", restDocument.getDocModifyDate(), "docModifyDate", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
			append(DatabaseUtils.formatEJBQLStringRestriction("dm.DocNumber", restDocument.getDocModifyNumber(), "docModifyNumber", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
			append(" and (dm.DocHead = d) "); //$NON-NLS-1$
			fromList = (", ModifyDocument as dm ").concat(fromList); //$NON-NLS-1$
		}
		if (restDocument.getIncoming() == 1) {
			whereText.append(" and d.IncomingNumber <> '' "); //$NON-NLS-1$
		}
		if (restDocument.getIncoming() == 2) {
			whereText.append(" and d.IncomingNumber = '' "); //$NON-NLS-1$
		}	
		if (restDocument.getPlanKind() != null || restDocument.getEndActionPlan() != null ||
				restDocument.getBeginActionPlan() != null) {
			fromList = fromList.concat(", Phase as ph, PhasePlanItem as ppi "); //$NON-NLS-1$

			whereText.append(DatabaseUtils.formatEJBQLObjectRangeRestriction("ppi.BeginActionDate", restDocument.getBeginActionPlan(), restDocument.getEndActionPlan(), "beginActionPlan", "endActionPlan", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			append(DatabaseUtils.formatEJBQLObjectRangeRestriction("ppi.EndActionDate", restDocument.getBeginActionPlan(), restDocument.getEndActionPlan(), "beginActionPlan", "endActionPlan", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			append(DatabaseUtils.formatEJBQLObjectRestriction("ppi.Kind", restDocument.getPlanKind(), "planKind", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
			append(" and ph.DocHead = d.Id and ppi.ContractPhase = ph.Id "); //$NON-NLS-1$
		}
		if (restDocument.getFactKind() != null || restDocument.getEndActionFact() != null ||
				restDocument.getBeginActionFact() != null) {			
			whereText = whereText.
			append(DatabaseUtils.formatEJBQLObjectRangeRestriction("pfi.RegDate", restDocument.getBeginActionFact(), restDocument.getEndActionFact(), "beginActionFact", "endActionFact", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			append(DatabaseUtils.formatEJBQLObjectRangeRestriction("pfi.RegDate", restDocument.getBeginActionFact(), restDocument.getEndActionFact(), "beginActionFact", "endActionFact", paramsName, paramsValue, false)).	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			append(DatabaseUtils.formatEJBQLObjectRestriction("pfi.Kind", restDocument.getFactKind(), "factKind", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
			append(" and  pfi.DocHead = d.Id "); //$NON-NLS-1$
			fromList = fromList.concat(", PhaseFactItem as pfi "); //$NON-NLS-1$
		}		
		return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);	
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createModel()
	 */
	@Override
	protected MaintenanceTableModel createModel() {
		return new DocumentMaintenanceEJBQLTableModel() {

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(Contract.class, "From", "f.Code", "left join d.From as f", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Contract.class, "To", "to.Code", "left join d.To as to", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Contract.class, "ContractCategory", "cc", "left join d.ContractCategory as cc", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Contract.class, "ShippedPayment", "d.ShippedPayment", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Contract.class, "ReceivedPayment", "d.ReceivedPayment", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Contract.class, "ShippedGood", "d.ShippedGood", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Contract.class, "ReceivedGood", "d.ReceivedGood", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Contract.class, "FactShippedPayment", "d.FactShippedPayment", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Contract.class, "FactReceivedPayment", "d.FactReceivedPayment", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Contract.class, "FactShippedGood", "d.FactShippedGood", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Contract.class, "FactReceivedGood", "d.FactReceivedGood", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Contract.class, "PhaseShippedPayment", "d.PhaseShippedPayment", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Contract.class, "PhaseReceivedPayment", "d.PhaseReceivedPayment", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Contract.class, "PhaseShippedGood", "d.PhaseShippedGood", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Contract.class, "PhaseReceivedGood", "d.PhaseReceivedGood", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Contract.class, "IncomingNumber", "d.IncomingNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Contract.class, "CompletedDate", "d.CompletedDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Contract.class, "Responsible", "resp.Code", "left join d.Responsible as resp", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setQuery(java.lang.String)
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));				
			}
			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
			 */

			@Override
			protected int getPrimaryKeyFieldIndex() {
				return 0;
			}

		};
	}

}
