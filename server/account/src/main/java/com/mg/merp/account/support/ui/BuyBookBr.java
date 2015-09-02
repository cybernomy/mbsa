/*
 * BuyBookBr.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.BooleanUtils;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.HierarchyRestrictionSupport;
import com.mg.framework.generic.ui.DefaultHierarchyBrowseForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.account.BuyBookServiceLocal;
import com.mg.merp.account.model.BuyBook;
import com.mg.merp.reference.support.ReferenceUtils;

/**
 * Браузер книги покупок
 * 
 * @author leonova
 * @version $Id: BuyBookBr.java,v 1.7 2006/10/20 06:12:20 leonova Exp $
 */
public class BuyBookBr extends DefaultHierarchyBrowseForm {
	private final String INIT_QUERY_TEXT = "select %s from BuyBook bb %s %s";
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();

	public BuyBookBr() throws Exception {
		super();
		folderService =  (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder");
		tree.setParentPropertyName("Folder.Id");
		treeUIProperties.put("FolderType", BuyBookServiceLocal.FOLDER_PART);
		restrictionFormName = "com/mg/merp/account/resources/BuyBookRest.mfd.xml";
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
		return ReferenceUtils.loadFolderHierarchy(BuyBookServiceLocal.FOLDER_PART);
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
		BuyBookRest restForm = (BuyBookRest) getRestrictionForm();

		whereText = " where ".concat(DatabaseUtils.formatEJBQLHierarchyRestriction(((HierarchyRestrictionSupport) restForm).isUseHierarchy(), "bb.Folder", 0, "folder", folderEntity, paramsName, paramsValue, true)).
		concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("bb.InsertDate", restForm.getInsertDate1(), restForm.getInsertDate2(), "insertDate1", "insertDate1", paramsName, paramsValue, false)).
		concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("bb.TotalSum", restForm.getTotalSum1(), restForm.getTotalSum2(), "totalSum1", "totalSum2", paramsName, paramsValue, false)).
		concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("bb.Nds1Sum", restForm.getNds1Sum1(), restForm.getNds1Sum2(), "nds1Sum1", "nds1Sum2", paramsName, paramsValue, false)).
		concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("bb.Nds2Sum", restForm.getNds2Sum1(), restForm.getNds2Sum2(), "nds2Sum1", "nds2Sum2", paramsName, paramsValue, false)).
		concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("bb.Nds3Sum", restForm.getNds3Sum1(), restForm.getNds3Sum2(), "nds3Sum1", "nds3Sum2", paramsName, paramsValue, false)).		
		concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("bb.DocDate", restForm.getDocDate1(), restForm.getDocDate2(), "docDate1", "docDate2", paramsName, paramsValue, false)).
		concat(DatabaseUtils.formatEJBQLStringRestriction("bb.DocNumber", restForm.getDocNumber(), "docNumber", paramsName, paramsValue, false)).
		concat(DatabaseUtils.formatEJBQLObjectRestriction("bb.DocType", restForm.getDocType(), "docType", paramsName, paramsValue, false)).
		concat(DatabaseUtils.formatEJBQLObjectRestriction("bb.Provider", restForm.getContractorCode(), "contractorCode", paramsName, paramsValue, false)).		
		concat(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "bb.Id", restForm.getAddinFieldsRestriction(), false));
		if (restForm.getApproved() != 0) {
			whereText = whereText.concat(DatabaseUtils.formatEJBQLObjectRestriction("bb.Approved", BooleanUtils.toBoolean(restForm.getApproved(), 1, 2), "approved", paramsName, paramsValue, false));
		}		
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
				result.add(new TableEJBQLFieldDef(BuyBook.class, "Id", "bb.Id", true));
				result.add(new TableEJBQLFieldDef(BuyBook.class, "DocType", "bb.DocType.Code", false));
				result.add(new TableEJBQLFieldDef(BuyBook.class, "DocNumber", "bb.DocNumber", false));
				result.add(new TableEJBQLFieldDef(BuyBook.class, "DocDate", "bb.DocDate", false));				
				result.add(new TableEJBQLFieldDef(BuyBook.class, "InsertDate", "bb.InsertDate", false));
				result.add(new TableEJBQLFieldDef(BuyBook.class, "InDate", "bb.InDate", false));	
				result.add(new TableEJBQLFieldDef(BuyBook.class, "StockDate", "bb.StockDate", false));	
				result.add(new TableEJBQLFieldDef(BuyBook.class, "PayDate", "bb.PayDate", false));
				result.add(new TableEJBQLFieldDef(BuyBook.class, "Provider", "bb.Provider.Code", false));	
				//result.add(new TableEJBQLFieldDef(BuyBook.class, "Provider.Partner.INN", "bb.Provider.Partner.INN", false));
				result.add(new TableEJBQLFieldDef(BuyBook.class, "SumWithoutNds1", "bb.SumWithoutNds1", false));
				result.add(new TableEJBQLFieldDef(BuyBook.class, "SumWithoutNds2", "bb.SumWithoutNds2", false));
				result.add(new TableEJBQLFieldDef(BuyBook.class, "SumWithoutNds3", "bb.SumWithoutNds3", false));
				result.add(new TableEJBQLFieldDef(BuyBook.class, "Nds1Sum", "bb.Nds1Sum", false));
				result.add(new TableEJBQLFieldDef(BuyBook.class, "Nds2Sum", "bb.Nds2Sum", false));
				result.add(new TableEJBQLFieldDef(BuyBook.class, "Nds3Sum", "bb.Nds3Sum", false));
				result.add(new TableEJBQLFieldDef(BuyBook.class, "TotalSum", "bb.TotalSum", false));		
				result.add(new TableEJBQLFieldDef(BuyBook.class, "NotTaxableSum", "bb.NotTaxableSum", false));
				result.add(new TableEJBQLFieldDef(BuyBook.class, "OrgUnit", "ou.Code", "left join bb.OrgUnit as ou", false));	
				result.add(new TableEJBQLFieldDef(BuyBook.class, "Approved", "bb.Approved", false));				
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
