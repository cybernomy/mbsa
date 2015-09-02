/*
 * SaleBooBr.java
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
package com.mg.merp.account.support.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.DefaultHierarchyBrowseForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.account.OperationModelServiceLocal;
import com.mg.merp.account.model.EconomicOperModel;
import com.mg.merp.reference.support.ReferenceUtils;

/**
 * @author leonova
 * @version $Id: EconomicOperModelBr.java,v 1.5 2006/10/25 08:22:04 leonova Exp $
 */
public class EconomicOperModelBr extends DefaultHierarchyBrowseForm {
	private final String INIT_QUERY_TEXT = "select %s from EconomicOperModel eo %s %s";
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();	
	
	public EconomicOperModelBr() throws Exception {
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
		whereText = " where ".concat(DatabaseUtils.formatEJBQLHierarchyRestriction(true, "eo.Folder", 0, "folder", folderEntity, paramsName, paramsValue, true));
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
				result.add(new TableEJBQLFieldDef(EconomicOperModel.class, "Id", "eo.Id", true));
				result.add(new TableEJBQLFieldDef(EconomicOperModel.class, "ModelName", "eo.ModelName", false));
				result.add(new TableEJBQLFieldDef(EconomicOperModel.class, "KeepDate", "eo.KeepDate", false));
				result.add(new TableEJBQLFieldDef(EconomicOperModel.class, "Comment", "eo.Comment", false));
				result.add(new TableEJBQLFieldDef(EconomicOperModel.class, "SpecMark", "sm.Code", "left join eo.SpecMark as sm", false));
				result.add(new TableEJBQLFieldDef(EconomicOperModel.class, "BaseDocType", "bdt.Code", "left join eo.BaseDocType as bdt", false));
				result.add(new TableEJBQLFieldDef(EconomicOperModel.class, "BaseDocDate", "eo.BaseDocDate", false));
				result.add(new TableEJBQLFieldDef(EconomicOperModel.class, "BaseDocNumber", "eo.BaseDocNumber", false));				
				result.add(new TableEJBQLFieldDef(EconomicOperModel.class, "ContractType", "ct.Code", "left join eo.ContractType as ct", false));
				result.add(new TableEJBQLFieldDef(EconomicOperModel.class, "ContractNumber", "eo.ContractNumber", false));
				result.add(new TableEJBQLFieldDef(EconomicOperModel.class, "ContractDate", "eo.ContractDate", false));				
				result.add(new TableEJBQLFieldDef(EconomicOperModel.class, "ConfirmDocType", "cdt.Code", "left join eo.ConfirmDocType as cdt", false));
				result.add(new TableEJBQLFieldDef(EconomicOperModel.class, "ConfirmDocNumber", "eo.ConfirmDocNumber", false));
				result.add(new TableEJBQLFieldDef(EconomicOperModel.class, "ConfirmDocDate", "eo.ConfirmDocDate", false));				
				result.add(new TableEJBQLFieldDef(EconomicOperModel.class, "Summa", "eo.Summa", false));	
				result.add(new TableEJBQLFieldDef(EconomicOperModel.class, "From", "f.Code", "left join eo.From as f", false));	
				result.add(new TableEJBQLFieldDef(EconomicOperModel.class, "To", "t.Code", "left join eo.To as t", false));					
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

}
