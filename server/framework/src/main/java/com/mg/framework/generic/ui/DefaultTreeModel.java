/*
 * DefaultTreeModel.java
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
package com.mg.framework.generic.ui;

import com.mg.framework.support.ui.widget.tree.TreeNode;

/**
 * @author Oleg V. Safonov
 * @version $Id: DefaultTreeModel.java,v 1.1 2006/06/26 11:58:56 safonov Exp $
 */
public class DefaultTreeModel extends AbstractTreeModel {
	private TreeNode rootNode;

	protected void setRootNode(TreeNode rootNode) {
		this.rootNode = rootNode;
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TreeModel#getRootNode()
	 */
	public TreeNode getRootNode() {
		if (rootNode == null)
			load();
		if (rootNode == null)
			throw new IllegalStateException("Model is not load");
		return rootNode;
	}

}
