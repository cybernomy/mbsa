/*
 * CatalogFolderTreeNode.java
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
package com.mg.merp.reference.support.ui;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mg.framework.support.ui.widget.tree.EntityTreeNode;
import com.mg.merp.reference.model.CatalogFolder;

/**
 * @author Oleg V. Safonov
 * @version $Id: CatalogFolderTreeNode.java,v 1.2 2007/03/14 10:19:57 safonov Exp $
 */
public class CatalogFolderTreeNode extends EntityTreeNode {

	public CatalogFolderTreeNode(CatalogFolderTreeNode parent, CatalogFolder entity) {
		super(parent);
		this.entity = entity;
	}
	
	public void addChild(CatalogFolderTreeNode child) {
		children.add(child);
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.tree.EntityTreeNode#doGetPrimaryKey()
	 */
	@Override
	protected Serializable doGetPrimaryKey() {
		return (Serializable) entity.getPrimaryKey();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.tree.TreeNode#getText()
	 */
	@Override
	public String getText() {
		return ((CatalogFolder) entity).getFName();
	}

	public static CatalogFolderTreeNode createTree(List<CatalogFolder> folders) {
		CatalogFolderTreeNode root = null;
		Map<Integer, CatalogFolderTreeNode> nodeMap = new HashMap<Integer, CatalogFolderTreeNode>();
		for (CatalogFolder folder : folders) {
			if (folder.getCatalogFolder() == null) {
				root = new CatalogFolderTreeNode(null, folder);
				nodeMap.put(folder.getId(), root);
			}
			else {
				CatalogFolderTreeNode parentNode, node;
				parentNode = nodeMap.get(folder.getCatalogFolder().getId());
				if (parentNode != null) {
					node = new CatalogFolderTreeNode(parentNode, folder);
					nodeMap.put(folder.getId(), node);
					parentNode.addChild(node);
				}
			}
		}
		return root;
	}
}
