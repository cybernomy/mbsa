/*
 * AdvanceRepHeadBr.java
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
import com.mg.merp.account.AdvanceRepHeadServiceLocal;
import com.mg.merp.account.model.AdvanceRepHead;
import com.mg.merp.core.support.CoreUtils;
import com.mg.merp.document.generic.ui.DocumentMaintenanceEJBQLTableModel;
import com.mg.merp.document.generic.ui.GoodsDocumentBrowseForm;

/**
 * Браузер авансовых отчетов
 * 
 * @author Julia 'Jetta' Konyashkina
 * @author Artem V. Sharapov
 * @version $Id: AdvanceRepHeadBr.java,v 1.14 2009/02/10 14:19:51 safonov Exp $
 */
public class AdvanceRepHeadBr extends GoodsDocumentBrowseForm {

	protected String INIT_QUERY_TEXT = "select distinct %s from AdvanceRepHead d %s %s order by d.DocDate, d.Id "; //$NON-NLS-1$

	public AdvanceRepHeadBr() throws Exception {
		super();
		folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder"); //$NON-NLS-1$
		treeUIProperties.put("FolderType", AdvanceRepHeadServiceLocal.FOLDER_PART); //$NON-NLS-1$
		restrictionFormName = "com/mg/merp/account/resources/AdvanceRepHeadRest.mfd.xml"; //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultHierarchyBrowseForm#loadFolders()
	 */
	@Override
	protected TreeNode loadFolders() throws ApplicationException {
		return CoreUtils.loadFolderHierarchy(AdvanceRepHeadServiceLocal.FOLDER_PART);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
	 */
	@Override
	protected String createQueryText() {
		super.createQueryText();		
		AdvanceRepHeadRest restRepHead = (AdvanceRepHeadRest) getRestrictionForm();
		whereText.append(DatabaseUtils.formatEJBQLObjectRestriction("d.Company", restRepHead.getCompanyCode(), "companyCode", paramsName, paramsValue, false)); //$NON-NLS-1$ //$NON-NLS-2$
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
				result.add(new TableEJBQLFieldDef(AdvanceRepHead.class, "From", "d.From.Code", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(AdvanceRepHead.class, "To", "d.To.Code", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(AdvanceRepHead.class, "ContractDate", "d.ContractDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(AdvanceRepHead.class, "ContractType", "ct.Code", "left join d.ContractType as ct", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(AdvanceRepHead.class, "ContractNumber", "d.ContractNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(AdvanceRepHead.class, "Company", "comp.Code", "left join d.Company as comp", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(AdvanceRepHead.class, "Acc", "acc.Acc", "left join d.Acc as acc", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(AdvanceRepHead.class, "Purpose", "d.Purpose", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(AdvanceRepHead.class, "Comments", "d.Comments", false));				 //$NON-NLS-1$ //$NON-NLS-2$
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