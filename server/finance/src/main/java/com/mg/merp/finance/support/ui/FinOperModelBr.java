/*
 * FinOperBr.java
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
package com.mg.merp.finance.support.ui;

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
import com.mg.merp.finance.OperationModelServiceLocal;
import com.mg.merp.finance.model.OperationModel;
import com.mg.merp.reference.support.ReferenceUtils;

/**
 * Браузер образцов финансовых операций
 * 
 * @author leonova
 * @version $Id: FinOperModelBr.java,v 1.4 2006/10/26 06:37:15 leonova Exp $
 */
public class FinOperModelBr extends DefaultHierarchyBrowseForm{
	private final String INIT_QUERY_TEXT = "select distinct %s from OperationModel fo %s %s";
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();

	public FinOperModelBr() throws Exception {
		super();
		folderService =  (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder");
		tree.setParentPropertyName("Folder.Id");
		treeUIProperties.put("FolderType", OperationModelServiceLocal.FOLDER_PART);

	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#initializeMaster()
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
		return ReferenceUtils.loadFolderHierarchy(OperationModelServiceLocal.FOLDER_PART);
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
		whereText = " where ".concat(DatabaseUtils.formatEJBQLHierarchyRestriction(true, "fo.Folder", 0, "folder", folderEntity, paramsName, paramsValue, true));
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
				result.add(new TableEJBQLFieldDef(OperationModel.class, "Id", "fo.Id", true));
				result.add(new TableEJBQLFieldDef(OperationModel.class, "ModelName", "fo.ModelName", false));
				result.add(new TableEJBQLFieldDef(OperationModel.class, "ModelDestFolder", "mdf.FName", "left join fo.ModelDestFolder as mdf", false));
				result.add(new TableEJBQLFieldDef(OperationModel.class, "Planned", "fo.Planned", false));
				result.add(new TableEJBQLFieldDef(OperationModel.class, "KeepDate", "fo.KeepDate", false));
				result.add(new TableEJBQLFieldDef(OperationModel.class, "Comment", "fo.Comment", false));
				result.add(new TableEJBQLFieldDef(OperationModel.class, "SumNat", "fo.SumNat", false));
				result.add(new TableEJBQLFieldDef(OperationModel.class, "SumCur", "fo.SumCur", false));
				result.add(new TableEJBQLFieldDef(OperationModel.class, "CurRate", "fo.CurRate", false));
				result.add(new TableEJBQLFieldDef(OperationModel.class, "Currency", "cur.Code", "left join fo.Currency as cur", false));
				result.add(new TableEJBQLFieldDef(OperationModel.class, "BaseDocType", "bdt.Code", "left join fo.BaseDocType as bdt", false));
				result.add(new TableEJBQLFieldDef(OperationModel.class, "BaseDocDate", "fo.BaseDocDate", false));
				result.add(new TableEJBQLFieldDef(OperationModel.class, "BaseDocNumber", "fo.BaseDocNumber", false));
				result.add(new TableEJBQLFieldDef(OperationModel.class, "ContractType", "ct.Code", "left join fo.ContractType as ct", false));
				result.add(new TableEJBQLFieldDef(OperationModel.class, "ContractNumber", "fo.ContractNumber", false));
				result.add(new TableEJBQLFieldDef(OperationModel.class, "ContractDate", "fo.ContractDate", false));				
				result.add(new TableEJBQLFieldDef(OperationModel.class, "ConfirmDocType", "cdt.Code", "left join fo.ConfirmDocType as cdt", false));
				result.add(new TableEJBQLFieldDef(OperationModel.class, "ConfirmDocNumber", "fo.ConfirmDocNumber", false));
				result.add(new TableEJBQLFieldDef(OperationModel.class, "ConfirmDocDate", "fo.ConfirmDocDate", false));				
				result.add(new TableEJBQLFieldDef(OperationModel.class, "From", "f.Code", "left join fo.From as f", false));	
				result.add(new TableEJBQLFieldDef(OperationModel.class, "To", "t.Code", "left join fo.To as t", false));	
				result.add(new TableEJBQLFieldDef(OperationModel.class, "Responsible", "res.Code", "left join fo.Responsible as res", false));				
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
	
	public void onActionShowEconomicOper(WidgetEvent event) throws Exception {
		final OperationModelServiceLocal service = (OperationModelServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/finance/OperationModel");
		FinOperModelBr form = (FinOperModelBr)ApplicationDictionaryLocator.locate().getBrowseForm(service, null);
		form.run();
	}

}
