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
 * Millennium Business Suite Anywhere System.
 *
 */
package com.mg.merp.account.support.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import com.mg.merp.account.SaleBookServiceLocal;
import com.mg.merp.account.model.SaleBook;
import com.mg.merp.reference.support.ReferenceUtils;

/**
 * Браузер книги продаж
 * 
 * @author leonova
 * @version $Id: SaleBookBr.java,v 1.7 2006/10/20 06:12:28 leonova Exp $
 */
public class SaleBookBr extends DefaultHierarchyBrowseForm {
	private final String INIT_QUERY_TEXT = "select %s from SaleBook sb %s %s";
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();

	public SaleBookBr() throws Exception {
		super();
		folderService =  (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder");
		treeUIProperties.put("FolderType", SaleBookServiceLocal.FOLDER_PART);
		tree.setParentPropertyName("Folder.Id");		
		restrictionFormName = "com/mg/merp/account/resources/SaleBookRest.mfd.xml";
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
		return ReferenceUtils.loadFolderHierarchy(SaleBookServiceLocal.FOLDER_PART);
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
		
		SaleBookRest restForm = (SaleBookRest) getRestrictionForm();
		whereText = " where ".concat(DatabaseUtils.formatEJBQLHierarchyRestriction(((HierarchyRestrictionSupport) restForm).isUseHierarchy(), "sb.Folder", 0, "folder", folderEntity, paramsName, paramsValue, true)).
		concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("sb.InsertDate", restForm.getInsertDate1(), restForm.getInsertDate2(), "insertDate1", "insertDate1", paramsName, paramsValue, false)).
		concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("sb.TotalSum", restForm.getTotalSum1(), restForm.getTotalSum2(), "totalSum1", "totalSum2", paramsName, paramsValue, false)).
		concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("sb.Nds1Sum", restForm.getNds1Sum1(), restForm.getNds1Sum2(), "nds1Sum1", "nds1Sum2", paramsName, paramsValue, false)).
		concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("sb.Nds2Sum", restForm.getNds2Sum1(), restForm.getNds2Sum2(), "nds2Sum1", "nds2Sum2", paramsName, paramsValue, false)).
		concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("sb.Nds3Sum", restForm.getNds3Sum1(), restForm.getNds3Sum2(), "nds3Sum1", "nds3Sum2", paramsName, paramsValue, false)).		
		concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("sb.DocDate", restForm.getDocDate1(), restForm.getDocDate2(), "docDate1", "docDate2", paramsName, paramsValue, false)).
		concat(DatabaseUtils.formatEJBQLStringRestriction("sb.DocNumber", restForm.getDocNumber(), "docNumber", paramsName, paramsValue, false)).
		concat(DatabaseUtils.formatEJBQLObjectRestriction("sb.DocType", restForm.getDocType(), "docType", paramsName, paramsValue, false)).
		concat(DatabaseUtils.formatEJBQLObjectRestriction("sb.Customer", restForm.getContractorCode(), "contractorCode", paramsName, paramsValue, false)).
		concat(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "sb.Id", restForm.getAddinFieldsRestriction(), false));

	
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
				result.add(new TableEJBQLFieldDef(SaleBook.class, "Id", "sb.Id", true));
				result.add(new TableEJBQLFieldDef(SaleBook.class, "DocType", "sb.DocType.Code", false));
				result.add(new TableEJBQLFieldDef(SaleBook.class, "DocNumber", "sb.DocNumber", false));
				result.add(new TableEJBQLFieldDef(SaleBook.class, "DocDate", "sb.DocDate", false));
				result.add(new TableEJBQLFieldDef(SaleBook.class, "InsertDate", "sb.InsertDate", false));
				result.add(new TableEJBQLFieldDef(SaleBook.class, "DocDate", "sb.DocDate", false));
				result.add(new TableEJBQLFieldDef(SaleBook.class, "Customer", "sb.Customer.Code", false));	
				//result.add(new TableEJBQLFieldDef(SaleBook.class, "Customer.Partner.INN", "sb.Customer.Partner.INN", false));
				result.add(new TableEJBQLFieldDef(SaleBook.class, "TotalSum", "sb.TotalSum", false));
				result.add(new TableEJBQLFieldDef(SaleBook.class, "SumWithoutNds1", "sb.SumWithoutNds1", false));
				result.add(new TableEJBQLFieldDef(SaleBook.class, "SumWithoutNds2", "sb.SumWithoutNds2", false));
				result.add(new TableEJBQLFieldDef(SaleBook.class, "SumWithoutNds3", "sb.SumWithoutNds3", false));
				result.add(new TableEJBQLFieldDef(SaleBook.class, "Nds1Sum", "sb.Nds1Sum", false));
				result.add(new TableEJBQLFieldDef(SaleBook.class, "Nds2Sum", "sb.Nds2Sum", false));
				result.add(new TableEJBQLFieldDef(SaleBook.class, "Nds3Sum", "sb.Nds3Sum", false));		
				result.add(new TableEJBQLFieldDef(SaleBook.class, "NotTaxableSum", "sb.NotTaxableSum", false));		
				result.add(new TableEJBQLFieldDef(SaleBook.class, "NotTaxableExportSum", "sb.NotTaxableExportSum", false));						
				result.add(new TableEJBQLFieldDef(SaleBook.class, "OrgUnit", "ou.Code", "left join sb.OrgUnit as ou", false));		
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

