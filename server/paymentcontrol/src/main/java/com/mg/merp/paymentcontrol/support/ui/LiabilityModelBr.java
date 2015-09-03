/*
 * PmcResourceBr.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.paymentcontrol.support.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import com.mg.merp.paymentcontrol.LiabilityModelServiceLocal;
import com.mg.merp.paymentcontrol.LiabilityServiceLocal;
import com.mg.merp.paymentcontrol.model.Liability;
import com.mg.merp.reference.support.ReferenceUtils;

/**
 * Браузер образцов реестра обязательств
 * 
 * @author leonova
 * @version $Id: LiabilityModelBr.java,v 1.3 2006/09/14 10:41:46 leonova Exp $
 */
public class LiabilityModelBr extends DefaultHierarchyBrowseForm {
	private final String INIT_QUERY_TEXT = "select distinct %s from Liability l %s where l.IsModel = 1 %s";
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();

	public LiabilityModelBr() throws Exception{
		super();		
		folderService =  (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder");
		treeUIProperties.put("FolderType", LiabilityServiceLocal.FOLDER_PART);
		tree.setParentPropertyName("Folder.Id");
		treeUIProperties.put("FolderType", LiabilityModelServiceLocal.FOLDER_PART);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#initializeMaster(java.io.Serializable)
	 */
	@Override
	protected void initializeMaster(PersistentObject master) {		
		uiProperties.put("Folder", master);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#loadFolders()
	 */
	@Override
	protected TreeNode loadFolders() throws ApplicationException {
		return ReferenceUtils.loadFolderHierarchy(LiabilityModelServiceLocal.FOLDER_PART);
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
				result.add(new TableEJBQLFieldDef(Liability.class, "Id", "l.Id", true));				
				result.add(new TableEJBQLFieldDef(Liability.class, "ModelName", "l.ModelName", false));
				result.add(new TableEJBQLFieldDef(Liability.class, "Priority", "l.Priority", false));
				result.add(new TableEJBQLFieldDef(Liability.class, "Num", "l.Num", false));
				result.add(new TableEJBQLFieldDef(Liability.class, "Receivable", "l.Receivable", false));
				result.add(new TableEJBQLFieldDef(Liability.class, "RegDate", "l.RegDate", false));
				result.add(new TableEJBQLFieldDef(Liability.class, "DateToExecute", "l.DateToExecute", false));
				result.add(new TableEJBQLFieldDef(Liability.class, "SumCur", "l.SumCur", false));				
				result.add(new TableEJBQLFieldDef(Liability.class, "From", "f.Code", "left join l.From as f", false));
				result.add(new TableEJBQLFieldDef(Liability.class, "FromBankAcc", "fa.Name", "left join l.FromBankAcc as fa", false));
				result.add(new TableEJBQLFieldDef(Liability.class, "To", "t.Code", "left join l.To as t", false));
				result.add(new TableEJBQLFieldDef(Liability.class, "ToBankAcc", "ta.Name", "left join l.ToBankAcc as ta", false));
				result.add(new TableEJBQLFieldDef(Liability.class, "CurRateAuthority", "ca.Code", "left join l.CurRateAuthority as ca", false));				
				result.add(new TableEJBQLFieldDef(Liability.class, "CurRateType", "ct.Code", "left join l.CurRateType as ct", false));	
				result.add(new TableEJBQLFieldDef(Liability.class, "PaymentDelay", "l.PaymentDelay", false));					
				result.add(new TableEJBQLFieldDef(Liability.class, "DocDate", "l.DocDate", false));
				result.add(new TableEJBQLFieldDef(Liability.class, "DocNumber", "l.DocNumber", false));
				result.add(new TableEJBQLFieldDef(Liability.class, "DocType", "dt.Code", "left join l.DocType as dt", false));				
				result.add(new TableEJBQLFieldDef(Liability.class, "BaseDocDate", "l.BaseDocDate", false));
				result.add(new TableEJBQLFieldDef(Liability.class, "BaseDocNumber", "l.BaseDocNumber", false));
				result.add(new TableEJBQLFieldDef(Liability.class, "BaseDocType", "bdt.Code", "left join l.BaseDocType as bdt", false));				
				result.add(new TableEJBQLFieldDef(Liability.class, "ContractDate", "l.ContractDate", false));
				result.add(new TableEJBQLFieldDef(Liability.class, "ContractNumber", "l.ContractNumber", false));
				result.add(new TableEJBQLFieldDef(Liability.class, "ContractType", "crt.Code", "left join l.ContractType as crt", false));
				result.add(new TableEJBQLFieldDef(Liability.class, "Comments", "l.Comments", false));				
				result.add(new TableEJBQLFieldDef(Liability.class, "PrefResource", "pr.Name", "left join l.PrefResource as pr", false));
				result.add(new TableEJBQLFieldDef(Liability.class, "PrefResourceFolder", "prf.FName", "left join l.PrefResourceFolder as prf", false));
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
		String whereText = "";
		Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);				
		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
		paramsName.clear();
		paramsValue.clear();		
		whereText = whereText.concat(DatabaseUtils.formatEJBQLHierarchyRestriction(true, "l.Folder", 0, "folder", folderEntity, paramsName, paramsValue, false));

		return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);	

	}	
	
	public void onActionShowLiabilityModel(WidgetEvent event) throws Exception {
		final LiabilityModelServiceLocal service = (LiabilityModelServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/paymentcontrol/LiabilityModel");
		LiabilityModelBr form = (LiabilityModelBr)ApplicationDictionaryLocator.locate().getBrowseForm(service, null);
		form.run();		
	}

}
