/*
 * DefaultTreeBrowseTransferHandler.java
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
package com.mg.framework.support.ui.dnd;

import java.io.Serializable;
import java.util.Arrays;
import java.util.EnumSet;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.Widget;
import com.mg.framework.api.ui.dnd.DataFlavor;
import com.mg.framework.api.ui.dnd.DnDTreeData;
import com.mg.framework.api.ui.dnd.DropAction;
import com.mg.framework.api.ui.dnd.TransferHandler;
import com.mg.framework.api.ui.dnd.Transferable;
import com.mg.framework.support.ui.widget.MaintenanceTreeController;
import com.mg.framework.support.ui.widget.tree.EntityTreeNode;

/**
 * Стандартная реализация обработчика операций DnD для форм браузеров папок
 * 
 * @author Oleg V. Safonov
 * @version $Id: DefaultTreeBrowseTransferHandler.java,v 1.1 2007/08/16 13:59:03 safonov Exp $
 */
public class DefaultTreeBrowseTransferHandler implements TransferHandler {
	private DataBusinessObjectService<PersistentObject, Serializable> folderService;
	private MaintenanceTreeController tree;

	public DefaultTreeBrowseTransferHandler(DataBusinessObjectService<PersistentObject, Serializable> folderService,
			MaintenanceTreeController tree) {
		this.folderService = folderService;
		this.tree = tree;
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.dnd.TransferHandler#exportDone(com.mg.framework.api.ui.Widget, com.mg.framework.api.ui.dnd.Transferable, com.mg.framework.api.ui.dnd.DropAction)
	 */
	public void exportDone(Widget sourceComponent, Transferable transferable,
			DropAction dropAction) {
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.dnd.TransferHandler#getSourceActions()
	 */
	public EnumSet<DropAction> getSourceActions() {
		return EnumSet.of(DropAction.ACTION_MOVE);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.dnd.TransferHandler#getTargetActions()
	 */
	public EnumSet<DropAction> getTargetActions() {
		return EnumSet.of(DropAction.ACTION_MOVE);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.dnd.TransferHandler#importData(com.mg.framework.api.ui.Widget, com.mg.framework.api.ui.dnd.Transferable)
	 */
	public boolean importData(Widget targetComponent, Transferable transferable) {
		Object dragData = transferable.getTransferData(DataFlavor.DRAG_FLAVOR);
		DnDTreeData dropData = (DnDTreeData) transferable.getTransferData(DataFlavor.DROP_FLAVOR);
		if (dropData.getTreeNodes().length < 1)
			return false;
		
		if (dragData instanceof DnDTreeData) {
			//переносим папки
			DnDTreeData treeData = (DnDTreeData) dragData;

			//только в пределах одного браузера обрабатываем перенос
			if (treeData.getTree().getView() != dropData.getTree().getView())
				return false;
			
			Serializable[] keys = new Serializable[treeData.getTreeNodes().length];
			for (int i = 0; i < keys.length; i++)
				keys[i] = ((EntityTreeNode) treeData.getTreeNodes()[i]).getPrimaryKey();
			if (folderService.move(Arrays.asList(keys), ((EntityTreeNode) dropData.getTreeNodes()[0]).getEntity())) {
				tree.refresh();
				return true;
			} else
				return false;
		}
		return false;
	}

}
