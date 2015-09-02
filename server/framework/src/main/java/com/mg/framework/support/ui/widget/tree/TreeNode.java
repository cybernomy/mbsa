/*
 * TreeNode.java
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
package com.mg.framework.support.ui.widget.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Oleg V. Safonov
 * @version $Id: TreeNode.java,v 1.2 2006/08/31 08:45:45 safonov Exp $
 */
public class TreeNode implements Serializable {
	protected TreeNode parent = null;
	protected List<TreeNode> children = new ArrayList<TreeNode>();

	public TreeNode(TreeNode parent) {
		this.parent = parent;
	}
	
	/**
	 * 
	 * @param index
	 * @return
	 */
	public TreeNode getChildAt(int index) {
		return children.get(index);
	}

	public int getChildCount() {
		return children.size();
	}
	
	public TreeNode getParent() {
		return parent;
	}
	
	public int getIndex(TreeNode child) {
		return child.getIndex(child);
	}
	
	public boolean isLeaf() {
		return children.size() == 0;
	}
	
	public String getText() {
		return toString();
	}
}
