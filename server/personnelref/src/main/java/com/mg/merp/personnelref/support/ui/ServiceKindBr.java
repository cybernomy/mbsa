/*
 * ServiceKindBr.java
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

package com.mg.merp.personnelref.support.ui;

import java.util.List;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.ui.widget.MaintenanceTree;
import com.mg.framework.generic.ui.DefaultTreeBrowseForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.merp.personnelref.model.ServiceKind;

/**
 * Контроллер браузера "Виды стажа"
 * 
 * @author Julia 'Jetta' Konyashkina
 * @author Artem V. Sharapov
 * @version $Id: ServiceKindBr.java,v 1.5 2007/11/08 16:16:22 safonov Exp $
 */
public class ServiceKindBr extends DefaultTreeBrowseForm {
	
	public ServiceKindBr() throws Exception {
		super();
		
		folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/personnelref/ServiceKind"); //$NON-NLS-1$
		tree.setParentPropertyName("ParentId"); //$NON-NLS-1$
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultLegacyTreeBrowseForm#loadFolders()
	 */
	@Override
	protected TreeNode loadFolders() {
		List<ServiceKind> list = OrmTemplate.getInstance().find(ServiceKind.class, String.format("from ServiceKind sk order by sk.ParentId, sk.KName")); //$NON-NLS-1$
		return ServiceKindTreeNode.createTree(list);
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultTreeBrowseForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		super.doOnRun();
		view.getWidget(TREE_WIDGET).getPopupMenu().getMenuItem(MaintenanceTree.PERMISSION_MENU_ITEM).setEnabled(false);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultTreeBrowseForm#setupFolderPermissions()
	 */
	@Override
	protected void setupFolderPermissions() {
		// Для исключения данного функционала при нажатии горячей клавиши(F7) неактивного пункта КМ "Доступ"
		//if (currentNode != null)
		//	ServerUtils.getSecuritySystem().setupTreePermission((Integer) currentNode.getPrimaryKey(), 7, "com.mg.merp.personnelref.model.ServiceKind", "ParentId"); //$NON-NLS-1$ //$NON-NLS-2$
	}

}