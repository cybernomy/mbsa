/*
 * InternalInvoiceHeadBr.java
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
package com.mg.merp.account.support.ui;

import java.util.Set;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.account.InternalInvoiceHeadServiceLocal;
import com.mg.merp.core.support.CoreUtils;
import com.mg.merp.document.generic.ui.GoodsDocumentBrowseForm;
import com.mg.merp.document.generic.ui.GoodsDocumentMaintenanceEJBQLTableModel;
import com.mg.merp.document.model.DocHead;

/**
 *  онтроллер формы списка внутренних накладных
 * 
 * @author Julia 'Jetta' Konyashkina
 * @author Artem V. Sharapov
 * @version $Id: InternalInvoiceHeadBr.java,v 1.14 2009/02/10 14:19:51 safonov Exp $
 */
public class InternalInvoiceHeadBr extends GoodsDocumentBrowseForm {

	public InternalInvoiceHeadBr() throws Exception {
		super();
		folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder"); //$NON-NLS-1$
		treeUIProperties.put("FolderType", InternalInvoiceHeadServiceLocal.FOLDER_PART); //$NON-NLS-1$
		restrictionFormName = "com/mg/merp/account/resources/InternalInvoiceHeadRest.mfd.xml"; //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultHierarchyBrowseForm#loadFolders()
	 */
	@Override
	protected TreeNode loadFolders() throws ApplicationException {
		return CoreUtils.loadFolderHierarchy(InternalInvoiceHeadServiceLocal.FOLDER_PART);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
	 */
	@Override
	protected String createQueryText() {
		super.createQueryText();		
//		fieldDefs = ((GoodsDocumentMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
//		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
//		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
//		if (whereText.toString().contains("Catalog")) {			 //$NON-NLS-1$
//		fromList = (", DocSpec as ds ").concat(fromList);			 //$NON-NLS-1$
//		}
		whereText.append(DatabaseUtils.formatEJBQLObjectRestriction("d.SrcStock", restGoodDocument.getSrcStockCode(), "srcStockCode", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
		.append(DatabaseUtils.formatEJBQLObjectRestriction("d.SrcMol", restGoodDocument.getSrcMolCode(), "srcMolCode", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
		.append(DatabaseUtils.formatEJBQLObjectRestriction("d.DstStock", restGoodDocument.getDstStockCode(), "dstStockCode", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
		.append(DatabaseUtils.formatEJBQLObjectRestriction("d.DstMol", restGoodDocument.getDstMolCode(), "dstMolCode", paramsName, paramsValue, false)); //$NON-NLS-1$ //$NON-NLS-2$
		return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);	
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createModel()
	 */
	@Override
	protected MaintenanceTableModel createModel() {
		return new GoodsDocumentMaintenanceEJBQLTableModel() {

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(DocHead.class, "SrcStock", "ss.Code", "left join d.SrcStock as ss", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(DocHead.class, "SrcMol", "sm.Code", "left join d.SrcMol as sm", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(DocHead.class, "Through", "t.Code", "left join d.Through as t", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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