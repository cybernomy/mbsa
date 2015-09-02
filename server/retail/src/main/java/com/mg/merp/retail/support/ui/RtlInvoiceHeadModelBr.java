/*
 * RtlInvoiceHeadBr.java
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
package com.mg.merp.retail.support.ui;

import java.util.Set;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.document.generic.ui.DocModelMaintenanceEJBQLTableModel;
import com.mg.merp.document.generic.ui.DocumentModelBrowseForm;
import com.mg.merp.reference.support.ReferenceUtils;
import com.mg.merp.retail.InvoiceHeadModelServiceLocal;
import com.mg.merp.retail.model.RtlInvoiceHeadModel;

/**
 * Контроллер формы списка образцов документов на отпуск
 * 
 * @author leonova
 * @version $Id: RtlInvoiceHeadModelBr.java,v 1.4 2006/09/12 11:06:25 leonova Exp $
 */
public class RtlInvoiceHeadModelBr extends DocumentModelBrowseForm{
	protected final String INIT_QUERY_TEXT = "select %s from RtlInvoiceHeadModel dhm %s %s";
	
	public RtlInvoiceHeadModelBr() throws Exception {
		super();
		folderService =  (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder");
		treeUIProperties.put("FolderType", InvoiceHeadModelServiceLocal.FOLDER_PART);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultHierarchyBrowseForm#loadFolders()
	 */
	@Override
	protected TreeNode loadFolders() throws ApplicationException {
		return ReferenceUtils.loadFolderHierarchy(InvoiceHeadModelServiceLocal.FOLDER_PART);
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
				result.add(new TableEJBQLFieldDef(RtlInvoiceHeadModel.class, "ContractDate", "dhm.ContractDate", false));
				result.add(new TableEJBQLFieldDef(RtlInvoiceHeadModel.class, "ContractType", "ct.Code", "left join dhm.ContractType as ct", false));
				result.add(new TableEJBQLFieldDef(RtlInvoiceHeadModel.class, "ContractNumber", "dhm.ContractNumber", false));
				result.add(new TableEJBQLFieldDef(RtlInvoiceHeadModel.class, "DstStock", "dst.Code", "left join dhm.DstStock as dst", false));
				result.add(new TableEJBQLFieldDef(RtlInvoiceHeadModel.class, "DstMol", "dm.Code", "left join dhm.DstMol as dm", false));
				result.add(new TableEJBQLFieldDef(RtlInvoiceHeadModel.class, "PriceList", "plh.PrName", "left join dhm.PriceList as plh", false));
				result.add(new TableEJBQLFieldDef(RtlInvoiceHeadModel.class, "PriceType", "pt.Code", "left join dhm.PriceType as pt", false));
				result.add(new TableEJBQLFieldDef(RtlInvoiceHeadModel.class, "Weight", "dhm.Weight", false));
				result.add(new TableEJBQLFieldDef(RtlInvoiceHeadModel.class, "Volume", "dhm.Volume", false));		
				result.add(new TableEJBQLFieldDef(RtlInvoiceHeadModel.class, "Through", "t.Code", "left join dhm.Through as t", false));
				result.add(new TableEJBQLFieldDef(RtlInvoiceHeadModel.class, "CalcTaxesKind", "ctk.Code", "left join dhm.CalcTaxesKind as ctk", false));				
				result.add(new TableEJBQLFieldDef(RtlInvoiceHeadModel.class, "DiscountOnDoc", "dhm.DiscountOnDoc", false));				
			
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