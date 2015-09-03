/*
 * InvoiceHeadInBr.java
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
package com.mg.merp.warehouse.support.ui;

import java.util.Set;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.core.support.CoreUtils;
import com.mg.merp.warehouse.InvoiceHeadInServiceLocal;
import com.mg.merp.warehouse.model.InvoiceHead;

/**
 * Контроллер формы списка входящих накладных
 * 
 * @author Julia 'Jetta' Konyashkina
 * @author Artem V. Sharapov
 * @version $Id: InvoiceHeadInBr.java,v 1.12 2009/02/10 14:29:13 safonov Exp $
 */
public class InvoiceHeadInBr extends InvoiceHeadBr {

	public InvoiceHeadInBr() throws Exception {
		super();
		folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder"); //$NON-NLS-1$
		treeUIProperties.put("FolderType", InvoiceHeadInServiceLocal.FOLDER_PART); //$NON-NLS-1$
		restrictionFormName = "com/mg/merp/warehouse/resources/InvoiceHeadInRest.mfd.xml"; //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultHierarchyBrowseForm#loadFolders()
	 */
	@Override
	protected TreeNode loadFolders() throws ApplicationException {
		return CoreUtils.loadFolderHierarchy(InvoiceHeadInServiceLocal.FOLDER_PART);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
	 */
	@Override
	protected String createQueryText() {
		super.createQueryText();		
//		fieldDefs = ((InvoiceHeadMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
//		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
//		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
//		if (whereText.toString().contains("Catalog")) {			
//		fromList = (", DocSpec as ds ").concat(fromList);			
//		}
		return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);	
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createModel()
	 */
	@Override
	protected MaintenanceTableModel createModel() {
		return new InvoiceHeadMaintenanceEJBQLTableModel() {

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(InvoiceHead.class, "AddExpenses", "d.AddExpenses", false)); //$NON-NLS-1$ //$NON-NLS-2$
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