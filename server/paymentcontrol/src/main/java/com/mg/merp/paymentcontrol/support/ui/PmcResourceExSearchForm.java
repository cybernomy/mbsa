/*
 * PmcResourceExSearchForm.java
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.TreeChangeEvent;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultTreeModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.MaintenanceTreeController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.TreeSelectionListener;
import com.mg.framework.support.ui.widget.tree.EntityTreeNode;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.paymentcontrol.ResourceServiceLocal;
import com.mg.merp.paymentcontrol.model.PmcResource;
import com.mg.merp.reference.support.ReferenceUtils;

/**
 * Контроллер формы поиска сущностей "Средств платежа"
 * Специализирован для диалога "Внутреннее перемещение средств"
 * 
 * @author Artem V. Sharapov
 * @version $Id: PmcResourceExSearchForm.java,v 1.1 2007/05/14 05:23:52 sharapov Exp $
 */
public class PmcResourceExSearchForm extends AbstractSearchForm {

	private PersistentObject folderEntity = null;
	private MaintenanceTreeController tree;
	private AttributeMap treeUIProperties = new LocalDataTransferObject();
	private DataBusinessObjectService folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder"); //$NON-NLS-1$

	private DefaultTableController table;

	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();
	private final String INIT_QUERY_TEXT = "select distinct %s from PmcResource pr %s %s"; //$NON-NLS-1$

	private Date actDate;
	private PmcResource excludeResource;

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {

		tree = new MaintenanceTreeController(treeUIProperties, new DefaultTreeModel() {
			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultTreeModel#getRootNode()
			 */
			@Override
			public TreeNode getRootNode() {
				return ReferenceUtils.loadFolderHierarchy(ResourceServiceLocal.FOLDER_PART);
			}
		});

		tree.initController(folderService);
		treeUIProperties.put("FolderType", ResourceServiceLocal.FOLDER_PART); //$NON-NLS-1$
		tree.setParentPropertyName("Folder.Id"); //$NON-NLS-1$
		tree.addTreeSelectionListener(new TreeSelectionListener(){
			public void valueChanged(TreeChangeEvent event) {
				folderEntity = ((EntityTreeNode) event.getNode()).getEntity();
				table.getModel().load();
			}
		});

		table = new DefaultTableController(new DefaultMaintenanceEJBQLTableModel() {

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
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, null);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}

			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);			
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				StringBuilder whereText =  new StringBuilder(" where "); //$NON-NLS-1$
				whereText.append(DatabaseUtils.formatEJBQLHierarchyRestriction(true, "pr.Folder", 0, "folder", folderEntity, paramsName, paramsValue, true)); //$NON-NLS-1$ //$NON-NLS-2$
				if(actDate != null) {
					whereText.append(DatabaseUtils.formatEJBQLObjectRangeRestriction("pr.ActDateFrom", null, actDate, null, "actDate", paramsName, paramsValue, false)); //$NON-NLS-1$ //$NON-NLS-2$
					whereText.append(DatabaseUtils.formatEJBQLObjectRangeRestriction("pr.ActDateTill", actDate, null, "actDate", null, paramsName, paramsValue, false)); //$NON-NLS-1$ //$NON-NLS-2$
				}
				if(excludeResource != null) 
					whereText.append(" and pr <> :excludeResource"); //$NON-NLS-1$

				return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);				
			}
		});
		table.getModel().load();
		super.doOnRun();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
	 */
	@Override
	protected PersistentObject[] getSearchedEntities() {
		Serializable[] selectedKeys = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getSelectedPrimaryKeys();
		if(selectedKeys != null && selectedKeys.length > 0)
			return new PersistentObject[] {ServerUtils.getPersistentManager().find(PmcResource.class, selectedKeys[0])};
		else
			return null;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchForm#doOnActionChoose(com.mg.framework.api.ui.WidgetEvent)
	 */
	@Override
	protected void doOnActionChoose(WidgetEvent event) {
		if(getSearchedEntities() != null)
			super.doOnActionChoose(event);
	}

	/**
	 * @return the actDate
	 */
	public Date getActDate() {
		return actDate;
	}

	/**
	 * @param actDate the actDate to set
	 */
	public void setActDate(Date actDate) {
		this.actDate = actDate;
	}

	/**
	 * @return the excludeResource
	 */
	public PmcResource getExcludeResource() {
		return excludeResource;
	}

	/**
	 * @param excludeResource the excludeResource to set
	 */
	public void setExcludeResource(PmcResource excludeResource) {
		this.excludeResource = excludeResource;
	}

	public void setSearchParams(Date actDate, PmcResource excludeResource) {
		this.excludeResource = excludeResource;
		this.actDate = actDate;
		if(excludeResource != null) {
			paramsName.add("excludeResource"); //$NON-NLS-1$
			paramsValue.add(excludeResource);
		}
	}

}
