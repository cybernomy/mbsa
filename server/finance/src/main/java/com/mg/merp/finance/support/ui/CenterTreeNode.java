/*
 * PeriodTreeNode.java
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
package com.mg.merp.finance.support.ui;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mg.framework.support.ui.widget.tree.EntityTreeNode;
import com.mg.merp.finance.model.Center;

/**
 * @author leonova
 * @version $Id: CenterTreeNode.java,v 1.3 2007/11/08 16:01:42 safonov Exp $
 */
public class CenterTreeNode extends EntityTreeNode {

	public CenterTreeNode(CenterTreeNode parent, Center entity) {
		super(parent);
		this.entity = entity;
	}
	
	public void addChild(CenterTreeNode child) {
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
		return ((Center) entity).getName();
	}

	public static CenterTreeNode createTree(List<Center> folders) {
		CenterTreeNode root = null;
		Map<Integer, CenterTreeNode> nodeMap = new HashMap<Integer, CenterTreeNode>();
		for (Center folder : folders) {
			if (folder.getParent() == null || folder.getParent() == 0) { //проверим на 0, т.к. в некоторых базах корневой parent не null
				root = new CenterTreeNode(null, folder);
				nodeMap.put(folder.getId(), root);
			}
			else {
				CenterTreeNode parentNode, node;
				parentNode = nodeMap.get(folder.getParent());
				if (parentNode != null) {
					node = new CenterTreeNode(parentNode, folder);
					nodeMap.put(folder.getId(), node);
					parentNode.addChild(node);
				}
			}
		}
		return root;
	}
}
