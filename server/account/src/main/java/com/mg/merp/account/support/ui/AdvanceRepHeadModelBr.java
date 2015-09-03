/*
 * AdvanceRepHeadBr.java
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

import java.util.Set;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.account.AdvanceRepHeadModelServiceLocal;
import com.mg.merp.account.model.AdvanceRepHeadModel;
import com.mg.merp.document.generic.ui.DocModelMaintenanceEJBQLTableModel;
import com.mg.merp.document.generic.ui.DocumentModelBrowseForm;
import com.mg.merp.reference.support.ReferenceUtils;

/**
 * Браузер образцов авансовых отчетов
 * 
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: AdvanceRepHeadModelBr.java,v 1.4 2006/09/12 11:24:05 leonova Exp $
 */
public class AdvanceRepHeadModelBr extends DocumentModelBrowseForm {
	private final String INIT_QUERY_TEXT = "select %s from AdvanceRepHeadModel dhm %s %s";
	
	public AdvanceRepHeadModelBr() throws Exception {
		super();
		folderService =  (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder");
		treeUIProperties.put("FolderType", AdvanceRepHeadModelServiceLocal.FOLDER_PART);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultHierarchyBrowseForm#loadFolders()
	 */
	@Override
	protected TreeNode loadFolders() throws ApplicationException {
		return ReferenceUtils.loadFolderHierarchy(AdvanceRepHeadModelServiceLocal.FOLDER_PART);
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
	 */
	@Override
	protected String createQueryText() {
		super.createQueryText();		
		fieldDefs = ((DocModelMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);

		return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);	
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createModel()
	 */
	@Override
	protected MaintenanceTableModel createModel() {
		return new DocModelMaintenanceEJBQLTableModel() {

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(AdvanceRepHeadModel.class, "ContractType", "contrt.Code", "left join dhm.ContractType as contrt", false));
				result.add(new TableEJBQLFieldDef(AdvanceRepHeadModel.class, "ContractNumber", "dhm.ContractNumber", false));
				result.add(new TableEJBQLFieldDef(AdvanceRepHeadModel.class, "ContractDate", "dhm.ContractDate", false));
				result.add(new TableEJBQLFieldDef(AdvanceRepHeadModel.class, "Company", "comp.Code", "left join dhm.Company comp", false));
				result.add(new TableEJBQLFieldDef(AdvanceRepHeadModel.class, "AccountAnt", "ant.Code", "left join dhm.AccountAnt as ant", false));				
				result.add(new TableEJBQLFieldDef(AdvanceRepHeadModel.class, "Acc", "a.Acc", "left join dhm.Acc as a", false));
				result.add(new TableEJBQLFieldDef(AdvanceRepHeadModel.class, "Purpose", "dhm.Purpose", false));
				result.add(new TableEJBQLFieldDef(AdvanceRepHeadModel.class, "Comments", "dhm.Comments", false));				
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
