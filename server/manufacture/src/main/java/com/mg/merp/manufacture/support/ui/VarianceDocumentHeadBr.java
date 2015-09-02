/*
 * VarianceDocumentHeadBr.java
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

import java.util.Set;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.core.support.CoreUtils;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.manufacture.VarianceDocumentHeadServiceLocal;

/**
 * Контроллер формы списка документов по отклонениям
 * 
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: VarianceDocumentHeadBr.java,v 1.13 2009/02/10 14:34:19 safonov Exp $
 */
public class VarianceDocumentHeadBr extends ManufactureDocumentBr {
	protected String INIT_QUERY_TEXT = "select distinct %s from VarianceDocumentHead d %s %s  order by d.DocDate, d.Id ";
	protected VarianceDocumentHeadRest restDocument;
	
	public VarianceDocumentHeadBr() throws Exception {
		super();
		folderService =  (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder");
		treeUIProperties.put("FolderType", VarianceDocumentHeadServiceLocal.FOLDER_PART);
		restrictionFormName = "com/mg/merp/manufacture/resources/VarianceDocumentHeadRest.mfd.xml";
	}
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultHierarchyBrowseForm#loadFolders()
	 */
	@Override
	protected TreeNode loadFolders() throws ApplicationException {
		return CoreUtils.loadFolderHierarchy(VarianceDocumentHeadServiceLocal.FOLDER_PART);
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
	 */
	@Override
	protected String createQueryText() {
		super.createQueryText();
		Set<TableEJBQLFieldDef> fieldDefs = ((ManufactureDocumentMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
		fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
		fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
		restDocument = (VarianceDocumentHeadRest) getRestrictionForm();		
		whereText.append(DatabaseUtils.formatEJBQLObjectRestriction("d.SrcMol", restDocument.getSrcMolCode(), "srcdstMolCode", paramsName, paramsValue, false))
				.append(DatabaseUtils.formatEJBQLObjectRestriction("d.SrcStock", restDocument.getSrcStockCode(), "srcStockCode", paramsName, paramsValue, false))
				.append(DatabaseUtils.formatEJBQLObjectRestriction("d.From", restDocument.getFromCode(), "fromCode", paramsName, paramsValue, false))
				.append(DatabaseUtils.formatEJBQLObjectRestriction("d.To", restDocument.getToCode(), "toCode", paramsName, paramsValue, false))		
				.append(DatabaseUtils.formatEJBQLObjectRestriction("d.Job", restDocument.getJob(), "job", paramsName, paramsValue, false))
				.append(DatabaseUtils.formatEJBQLObjectRestriction("d.WC", restDocument.getWorkCenter(), "wcCode", paramsName, paramsValue, false));
		if (whereText.indexOf("Catalog") != -1) {			
			fromList = (", VarianceDocumentSpec as ds ").concat(fromList);			
		}
		return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText.toString());	
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createModel()
	 */
	@Override
	protected MaintenanceTableModel createModel() {
		return new ManufactureDocumentMaintenanceEJBQLTableModel() {

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(DocHead.class, "To", "d.To.Code", false));
				result.add(new TableEJBQLFieldDef(DocHead.class, "From", "d.From.Code", false));
				result.add(new TableEJBQLFieldDef(DocHead.class, "ContractDate", "d.ContractDate", false));
				result.add(new TableEJBQLFieldDef(DocHead.class, "ContractNumber", "d.ContractNumber", false));
				result.add(new TableEJBQLFieldDef(DocHead.class, "ContractType", "ct.Code", "left join d.ContractType as ct", false));				
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

