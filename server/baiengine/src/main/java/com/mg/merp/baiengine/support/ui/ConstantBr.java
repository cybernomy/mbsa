/*
 * ConstantBr.java
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
package com.mg.merp.baiengine.support.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.DefaultHierarchyBrowseForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.baiengine.ConstantServiceLocal;
import com.mg.merp.baiengine.model.Constant;
import com.mg.merp.core.support.CoreUtils;


/**
 * Контроллер браузера бизнес-компонента "Константа"
 * 
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: ConstantBr.java,v 1.2 2007/08/21 14:12:47 alikaev Exp $
 */
public class ConstantBr extends DefaultHierarchyBrowseForm  {

	private final String INIT_QUERY_TEXT = "select %s from Constant c %s %s"; //$NON-NLS-1$
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();
	protected AttributeMap constantProperties = new LocalDataTransferObject();

	/**
	 * 
	 */
	public ConstantBr() throws Exception{
		super();
		folderService =  (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder"); //$NON-NLS-1$
		treeUIProperties.put("FolderType", ConstantServiceLocal.FOLDER_PART); //$NON-NLS-1$
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
		return CoreUtils.loadFolderHierarchy(ConstantServiceLocal.FOLDER_PART);
	}

	@Override
	protected MaintenanceTableModel createModel() {
		return  new DefaultMaintenanceEJBQLTableModel() {

			@Override
			protected int getPrimaryKeyFieldIndex(){
				return 0;
			}

			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(Constant.class, "Id", "c.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Constant.class, "Code", "c.Code", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Constant.class, "Description", "c.Description", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Constant.class, "DataType", "c.DataType", false)); //$NON-NLS-1$ //$NON-NLS-2$

				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
			}

			@Override
			protected void doLoad() {				
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));				
			}

		};
	}

	@Override
	protected String createQueryText() {
		Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);				
		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
		paramsName.clear();
		paramsValue.clear();	
		StringBuilder whereText = new StringBuilder(" where ") //$NON-NLS-1$
				.append(DatabaseUtils.formatEJBQLHierarchyRestriction(true, "c.Folder", 0, "folder", folderEntity, paramsName, paramsValue, true));
		return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText.toString());
	}
	
}
