/*
 * ManufactureDocumentBr.java
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
package com.mg.merp.manufacture.support.ui;

import com.mg.merp.document.generic.ui.GoodsDocumentBrowseForm;

/**
 * Контроллер формы списка производственных документов
 * 
 * @author leonova
 * @version $Id: ManufactureDocumentBr.java,v 1.3 2007/08/06 12:43:54 safonov Exp $ 
 */
public class ManufactureDocumentBr extends GoodsDocumentBrowseForm {
//	protected String INIT_QUERY_TEXT = "select distinct %s from DocHead d %s %s";
//	protected List<String> paramsName = new ArrayList<String>();
//	protected List<Object> paramsValue = new ArrayList<Object>();
//	protected String fieldsList;
//	protected String fromList;
//	protected Set<TableEJBQLFieldDef> fieldDefs;
//	protected ManufactureDocumentRest restDocument;	
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultHierarchyBrowseForm#initializeMaster(com.mg.framework.api.orm.PersistentObject)
	 */
//	@Override
//	protected void initializeMaster(PersistentObject master) {
//		uiProperties.put("Folder", master);
//	}
//	
//	@Override
//	protected String createQueryText() {		
//		Set<TableEJBQLFieldDef> fieldDefs = ((ManufactureDocumentMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
//		fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
//		fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
//		paramsName.clear();
//		paramsValue.clear();
//		restDocument = (ManufactureDocumentRest) getRestrictionForm();
//		whereText = new StringBuilder(" where ").append(DatabaseUtils.formatEJBQLHierarchyRestriction(((HierarchyRestrictionSupport) restDocument).isUseHierarchy(), "d.Folder", 0, "folder", folderEntity, paramsName, paramsValue, true))
//				.append(DatabaseUtils.formatEJBQLObjectRangeRestriction("d.DocDate", restDocument.getDocDateFrom(), restDocument.getDocDateTill(), "actDateFrom", "actDateTill", paramsName, paramsValue, false))
//				.append(DatabaseUtils.formatEJBQLStringRestriction("d.DocNumber", restDocument.getDocNumber(), "docNumber", paramsName, paramsValue, false))
//				.append(DatabaseUtils.formatEJBQLObjectRestriction("d.DocType", restDocument.getDocType(), "docType", paramsName, paramsValue, false))
//				.append(DatabaseUtils.formatEJBQLObjectRestriction("ds.Catalog", ((ManufactureDocumentRest) restDocument).getCatalogName(), "catalogName", paramsName, paramsValue, false))
//				.append(DatabaseUtils.formatEJBQLObjectRestriction("ds.Catalog.Folder", ((ManufactureDocumentRest) restDocument).getCatalogFolder(), "catalogFolder", paramsName, paramsValue, false))
//				.append(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "d.Id", restDocument.getAddinFieldsRestriction(), false));
//		if (whereText.indexOf("Catalog") != -1) {			
//			whereText.append(" and ds.DocHead = d.id ");
//		}
//		
//		return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText.toString());
//	}

}

