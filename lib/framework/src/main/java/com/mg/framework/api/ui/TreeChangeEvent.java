/*
 * TreeChangeEvent.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.framework.api.ui;

import com.mg.framework.support.ui.widget.tree.TreeNode;

public class TreeChangeEvent extends UIEvent {
	TreeNode node;
	
	public TreeChangeEvent(Object source, TreeNode node) {
		super(source);
		this.node = node;
	}
	
	public TreeNode getNode() {
		return node;
	}
}