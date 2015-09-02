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
package com.mg.merp.paymentcontrol.support.ui;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mg.framework.support.ui.widget.tree.EntityTreeNode;
import com.mg.merp.paymentcontrol.model.PmcPeriod;

/**
 * @author leonova
 * @version $Id: PeriodTreeNode.java,v 1.1 2006/09/01 08:05:18 leonova Exp $
 */
public class PeriodTreeNode extends EntityTreeNode {

	public PeriodTreeNode(PeriodTreeNode parent, PmcPeriod entity) {
		super(parent);
		this.entity = entity;
	}
	
	public void addChild(PeriodTreeNode child) {
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
		return ((PmcPeriod) entity).getName();
	}

	public static PeriodTreeNode createTree(List<PmcPeriod> folders) {
		PeriodTreeNode root = null;
		Map<PmcPeriod, PeriodTreeNode> nodeMap = new HashMap<PmcPeriod, PeriodTreeNode>();
		for (PmcPeriod folder : folders) {
			if (folder.getParent() == null) {
				root = new PeriodTreeNode(null, folder);
				nodeMap.put(folder, root);
			}
			else {
				PeriodTreeNode parentNode, node;
				parentNode = nodeMap.get(folder.getParent());
				if (parentNode != null) {
					node = new PeriodTreeNode(parentNode, folder);
					nodeMap.put(folder, node);
					parentNode.addChild(node);
				}
			}
		}
		return root;
	}
}
