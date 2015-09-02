/*
 * PmaFolderSearchForm.java
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

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.TreeChangeEvent;
import com.mg.framework.api.ui.widget.MaintenanceTree;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultTreeModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.MaintenanceTreeController;
import com.mg.framework.support.ui.widget.TreeSelectionListener;
import com.mg.framework.support.ui.widget.tree.EntityTreeNode;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.merp.reference.support.ReferenceUtils;

/**
 * Контроллер формы поиска сущностей "Папки" модуля <Журнал платежей>
 * 
 * @author Artem V. Sharapov
 * @version $Id: PmaFolderSearchForm.java,v 1.1 2007/05/25 08:42:43 sharapov Exp $
 */
public class PmaFolderSearchForm extends AbstractSearchForm {
		
	private PersistentObject folderEntity = null;
	private MaintenanceTreeController tree;
	private AttributeMap treeUIProperties = new LocalDataTransferObject();
	private DataBusinessObjectService folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder"); //$NON-NLS-1$

	private short folderPart;
	private final String TREE_WIDGET_NAME = "tree"; //$NON-NLS-1$

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
				return ReferenceUtils.loadFolderHierarchy(folderPart);
			}
		});

		tree.initController(folderService);
		treeUIProperties.put("FolderType", folderPart); //$NON-NLS-1$
		tree.setParentPropertyName("Folder.Id"); //$NON-NLS-1$
		tree.addTreeSelectionListener(new TreeSelectionListener(){
			public void valueChanged(TreeChangeEvent event) {
				folderEntity = ((EntityTreeNode) event.getNode()).getEntity();
			}
		});
		super.doOnRun();
		view.getWidget(TREE_WIDGET_NAME).setReadOnly(true);
		view.getWidget(TREE_WIDGET_NAME).getPopupMenu().getMenuItem(MaintenanceTree.PERMISSION_MENU_ITEM).setEnabled(false);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
	 */
	@Override
	protected PersistentObject[] getSearchedEntities() {
		return new PersistentObject[] {folderEntity};
	}

	/**
	 * @return the folderPart
	 */
	public short getFolderPart() {
		return folderPart;
	}

	/**
	 * @param folderPart the folderPart to set
	 */
	public void setFolderPart(short folderPart) {
		this.folderPart = folderPart;
	}

}
