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
 * Millennium Business Suite Anywhere System.
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
import com.mg.merp.core.support.CoreUtils;
import com.mg.merp.document.generic.ui.AbstractGoodsDocSpecTableModel;
import com.mg.merp.document.generic.ui.DocumentMaintenanceEJBQLTableModel;
import com.mg.merp.document.generic.ui.GoodsDocumentBrowseForm;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.retail.InvoiceHeadServiceLocal;
import com.mg.merp.retail.model.RtlInvoiceHead;

/**
 * Контроллер формы списка документов на отпуск
 * 
 * @author leonova
 * @version $Id: RtlInvoiceHeadBr.java,v 1.15 2009/02/12 08:23:40 safonov Exp $
 */
public class RtlInvoiceHeadBr extends GoodsDocumentBrowseForm{
	private final String INIT_QUERY_TEXT = "select %s from RtlInvoiceHead d %s %s order by d.DocDate, d.Id ";

	public RtlInvoiceHeadBr() throws Exception {
		super();
		folderService =  (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder");
		treeUIProperties.put("FolderType", InvoiceHeadServiceLocal.FOLDER_PART);
		restrictionFormName = "com/mg/merp/retail/resources/RtlInvoiceHeadRest.mfd.xml";		
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultHierarchyBrowseForm#loadFolders()
	 */
	@Override
	protected TreeNode loadFolders() throws ApplicationException {
		return CoreUtils.loadFolderHierarchy(InvoiceHeadServiceLocal.FOLDER_PART);
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
	 */
	@Override
	protected String createQueryText() {
		super.createQueryText();		
		fieldDefs = ((DocumentMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
//		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
//		if (whereText.toString().contains("Catalog")) {			
//			fromList = (", DocSpec as ds ").concat(fromList);			
//		}
		whereText.append(DatabaseUtils.formatEJBQLObjectRestriction("d.SrcStock", restGoodDocument.getSrcStockCode(), "srcStockCode", paramsName, paramsValue, false))
				.append(DatabaseUtils.formatEJBQLObjectRestriction("d.SrcMol", restGoodDocument.getSrcMolCode(), "srcMolCode", paramsName, paramsValue, false));

		return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);	
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createModel()
	 */
	@Override
	protected MaintenanceTableModel createModel() {
		return new DocumentMaintenanceEJBQLTableModel() {

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(DocHead.class, "From", "fcode.Code", "left join d.From as fcode", false));
				result.add(new TableEJBQLFieldDef(DocHead.class, "To", "tcode.Code", "left join d.To as tcode", false));
				result.add(new TableEJBQLFieldDef(DocHead.class, "ContractDate", "d.ContractDate", false));
				result.add(new TableEJBQLFieldDef(DocHead.class, "ContractType", "ct.Code", "left join d.ContractType as ct", false));
				result.add(new TableEJBQLFieldDef(DocHead.class, "ContractNumber", "d.ContractNumber", false));
				result.add(new TableEJBQLFieldDef(RtlInvoiceHead.class, "PlanPayDate", "d.PlanPayDate", false));
				result.add(new TableEJBQLFieldDef(RtlInvoiceHead.class, "PayDate", "d.PayDate", false));
				result.add(new TableEJBQLFieldDef(RtlInvoiceHead.class, "PaySum", "d.PaySum", false));
				result.add(new TableEJBQLFieldDef(RtlInvoiceHead.class, "PlanShipDate", "d.PlanShipDate", false));				
				result.add(new TableEJBQLFieldDef(DocHead.class, "DiscountFolder", "df.FName", "left join d.DiscountFolder as df", false));
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
			}
			
			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setQuery(java.lang.String)
			 */
			@Override
			protected void doLoad() {
				//throw new ApplicationException(createQueryText());
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

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.ui.GoodsDocumentBrowseForm#createGoodsDocSpecTableModel()
	 */
	@Override
	protected AbstractGoodsDocSpecTableModel createGoodsDocSpecTableModel() {
		return new RetailDocSpecTableModel();
	}

}

