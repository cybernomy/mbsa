/*
 * PaymentModelBr.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.paymentalloc.support.ui;

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
import com.mg.merp.core.support.CoreUtils;
import com.mg.merp.paymentalloc.PaymentModelServiceLocal;
import com.mg.merp.paymentalloc.model.Payment;

/**
 * Контроллер браузера "Образцы записей журнала платежей"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PaymentModelBr.java,v 1.5 2008/02/13 09:13:51 sharapov Exp $
 */
public class PaymentModelBr extends DefaultHierarchyBrowseForm {

	private final String INIT_QUERY_TEXT = "select %s from Payment p %s where p.IsModel = 1 %s"; //$NON-NLS-1$
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();

	@SuppressWarnings("unchecked") //$NON-NLS-1$
	public PaymentModelBr() throws Exception {
		super();
		folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder"); //$NON-NLS-1$
		treeUIProperties.put("FolderType", PaymentModelServiceLocal.FOLDER_PART); //$NON-NLS-1$
		tree.setParentPropertyName("Folder.Id"); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#initializeMaster(java.io.Serializable)
	 */
	@Override
	protected void initializeMaster(PersistentObject master) {		
		uiProperties.put("Folder", master); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#loadFolders()
	 */
	@Override
	protected TreeNode loadFolders() throws ApplicationException {
		return CoreUtils.loadFolderHierarchy(PaymentModelServiceLocal.FOLDER_PART);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createModel()
	 */
	@Override
	protected MaintenanceTableModel createModel() {
		return new DefaultMaintenanceEJBQLTableModel() {

			/*
			 * (non-Javadoc)
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
				result.add(new TableEJBQLFieldDef(Payment.class, "Id", "p.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Payment.class, "Planned", "p.Planned", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Payment.class, "PDate", "p.PDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Payment.class, "ModelName", "p.ModelName", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Payment.class, "Name", "p.Name", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Payment.class, "CurCode", "cur.Code", "left join p.CurCode as cur", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Payment.class, "CurRateType", "crt.Code", "left join p.CurRateType as crt", false));				 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Payment.class, "CurRateAuthority", "cra.Code", "left join p.CurRateAuthority as cra", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Payment.class, "CurRate", "p.CurRate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Payment.class, "SumCur", "p.SumCur", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Payment.class, "SumNat", "p.SumNat", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Payment.class, "DocType", "dt.Code", "left join p.DocType dt", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Payment.class, "DocNumber", "p.DocNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Payment.class, "DocDate", "p.DocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Payment.class, "BaseDocType", "bdt.Code", "left join p.BaseDocType bdt", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Payment.class, "BaseDocNumber", "p.BaseDocNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Payment.class, "BaseDocDate", "p.BaseDocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Payment.class, "ContractType", "ct.Code", "left join p.ContractType ct", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Payment.class, "ContractNumber", "p.ContractNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Payment.class, "ContractDate", "p.ContractDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Payment.class, "ContractorFrom", "cfrom.Code", "left join p.ContractorFrom as cfrom", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Payment.class, "ContractorTo", "cto.Code", "left join p.ContractorTo as cto", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Payment.class, "Description", "p.Description", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Payment.class, "Comments", "p.Comments", false)); //$NON-NLS-1$ //$NON-NLS-2$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setQuery(java.lang.String)
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));				
			}
		};
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
	 */
	@Override
	protected String createQueryText() {
		StringBuilder whereText = new StringBuilder();
		Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);				
		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
		paramsName.clear();
		paramsValue.clear();	
		whereText.append(DatabaseUtils.formatEJBQLHierarchyRestriction(true, "p.Folder", 0, "folder", folderEntity, paramsName, paramsValue, false)); //$NON-NLS-1$ //$NON-NLS-2$
		return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);	
	}	

}
