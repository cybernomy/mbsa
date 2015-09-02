/*
 * DefaultLegacyHierarchyBrowseForm.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.framework.generic.ui;

import java.io.Serializable;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.BrowseCond;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.TreeChangeEvent;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.MaintenanceTreeController;
import com.mg.framework.support.ui.widget.TreeSelectionListener;

/**
 * @author Oleg V. Safonov
 * @version $Id: DefaultLegacyHierarchyBrowseForm.java,v 1.8 2006/09/22 11:59:36 safonov Exp $
 */
@Deprecated
public class DefaultLegacyHierarchyBrowseForm extends DefaultLegacyPlainBrowseForm {
	protected DataBusinessObjectService folderService;
	protected MaintenanceTreeController tree;
	protected AttributeMap treeUIProperties = new LocalDataTransferObject();
	
	public DefaultLegacyHierarchyBrowseForm() {
		tree = new MaintenanceTreeController(treeUIProperties, new DefaultTreeModel() {
			@Override
			protected void doLoad() {
				//setRootNode(LegacyTreeNode.convertDataSetToTreeNode(loadFolders()));
			}
		});
		tree.addMasterModelListener(table);
		tree.addTreeSelectionListener(new TreeSelectionListener(){

			public void valueChanged(TreeChangeEvent event) {
				//initializeMaster(((LegacyTreeNode) event.getNode()).getPrimaryKey());
			}
			
		});
		cond.browseCond.put(BrowseCond.BY_FOLDER_ATTRIBUTE, true);
	}

	/**
	 * Метод устанавливает ссылку на объект мастер. Необходимо переопределить в
	 * наследнике. Например: <code>uiProperties.put("FolderId", master)</code>
	 * 
	 * @param master первичный ключ мастера
	 */
	protected void initializeMaster(Serializable master) {
		
	}
	
	/**
	 * Загрузка списка папок текщего сервиса. Необходимо переопределить в наследнике
	 * если сервис не реализует метод loadFolders()
	 * 
	 * <p>Пример данного метода:
	 * <pre>
	 *  protected Object loadFolders() throws ApplicationException {
	 *  	LocalDataTransferObject restProps = new LocalDataTransferObject();
	 *  	restProps.put("FolderType", new Integer(12001));
	 *  
	 *  	BrowseCond cond = new BrowseCond(null, "", restProps, false, DataBusinessObjectService.INTERNAL_LEGACY_FORMAT);
	 *  	return folderService.loadBrowse(cond);
	 *  }
	 * </pre>
	 * 
	 * @return
	 * @throws ApplicationException
	 */
	protected Object loadFolders() throws ApplicationException {
		return null;
	}
	
	@Override
	public void setService(DataBusinessObjectService<PersistentObject, Serializable> service) {
		/*try {
			tree.initController(service);
		}
		catch (Exception e) {
			logger.debug("Initialize of tree controller failed", e);
			tree.setData(loadFolders());
		}*/
		super.setService(service);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultLegacyPlainBrowseForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		if (folderService == null)
			throw new IllegalStateException("Folder service cann't be null");
		
		tree.initController(folderService);
		//tree.setData(loadFolders());
		super.doOnRun();
	}
}
