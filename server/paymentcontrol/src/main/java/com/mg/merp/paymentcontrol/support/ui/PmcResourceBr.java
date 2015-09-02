/*
 * PmcResourceBr.java
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
package com.mg.merp.paymentcontrol.support.ui;

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
import com.mg.merp.paymentcontrol.ResourceServiceLocal;
import com.mg.merp.paymentcontrol.model.PmcResource;
import com.mg.merp.reference.support.ReferenceUtils;

/**
 * Контроллер браузера "Cредство платежа"
 * 
 * @author leonova
 * @version $Id: PmcResourceBr.java,v 1.5 2007/05/14 05:23:52 sharapov Exp $
 */
public class PmcResourceBr extends DefaultHierarchyBrowseForm {
	
	private final String INIT_QUERY_TEXT = "select distinct %s from PmcResource pr %s %s"; //$NON-NLS-1$
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();

	public PmcResourceBr() throws Exception {
		super();		
		folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder"); //$NON-NLS-1$
		treeUIProperties.put("FolderType", ResourceServiceLocal.FOLDER_PART); //$NON-NLS-1$
		tree.setParentPropertyName("Folder.Id"); //$NON-NLS-1$
		restrictionFormName = "com/mg/merp/paymentcontrol/resources/PmcResourceRest.mfd.xml"; //$NON-NLS-1$
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
		return ReferenceUtils.loadFolderHierarchy(ResourceServiceLocal.FOLDER_PART);
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
				result.add(new TableEJBQLFieldDef(PmcResource.class, "Id", "pr.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PmcResource.class, "OrgUnit", "pr.OrgUnit.Code", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PmcResource.class, "Name", "pr.Name", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PmcResource.class, "Description", "pr.Description", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PmcResource.class, "Catalog", "cat.Code", "left join pr.Catalog as cat", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(PmcResource.class, "ActDateFrom", "pr.ActDateFrom", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PmcResource.class, "ActDateTill", "pr.ActDateTill", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PmcResource.class, "CurCode", "cur.Code", "left join pr.CurCode as cur", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(PmcResource.class, "CurRateAuthority", "ca.Code", "left join pr.CurRateAuthority as ca", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(PmcResource.class, "CurRateType", "ct.Code", "left join pr.CurRateType as ct", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				
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
		Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);				
		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
		paramsName.clear();
		paramsValue.clear();
		PmcResourceRest restForm = (PmcResourceRest) getRestrictionForm();
		
		StringBuilder whereText = new StringBuilder(" where ") //$NON-NLS-1$
		.append(DatabaseUtils.formatEJBQLHierarchyRestriction(((HierarchyRestrictionSupport) restForm).isUseHierarchy(), "pr.Folder", 0, "folder", folderEntity, paramsName, paramsValue, true)) //$NON-NLS-1$ //$NON-NLS-2$
		.append(DatabaseUtils.formatEJBQLStringRestriction("pr.Name", restForm.getName(), "goodType", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
		.append(DatabaseUtils.formatEJBQLObjectRangeRestriction("pr.ActDateFrom", null, restForm.getActDate(), null, "actDate", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
		.append(DatabaseUtils.formatEJBQLObjectRangeRestriction("pr.ActDateTill", restForm.getActDate(), null, "actDate", null, paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
		.append(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "pr.Id", restForm.getAddinFieldsRestriction(), false)); //$NON-NLS-1$
		
		return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);	
	}	

}