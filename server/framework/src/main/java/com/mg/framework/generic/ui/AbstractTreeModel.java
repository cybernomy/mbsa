/*
 * AbstractTreeModel.java
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mg.framework.support.ui.widget.TreeModel;
import com.mg.framework.support.ui.widget.TreeModelListener;
import com.mg.framework.support.ui.widget.tree.TreeNode;

/**
 * @author Oleg V. Safonov
 * @version $Id: AbstractTreeModel.java,v 1.2 2006/08/31 08:56:24 safonov Exp $
 */
public abstract class AbstractTreeModel implements TreeModel, Serializable {
	private List<TreeModelListener> modelListenerList = new ArrayList<TreeModelListener>();

	protected void doLoad() {
		
	}

	public void fireTreeChange() {
		for (TreeModelListener listener : modelListenerList)
			listener.treeStructureChanged();
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TreeModel#getRootNode()
	 */
	public TreeNode getRootNode() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TreeModel#addTreeModelListener(com.mg.framework.support.ui.widget.TreeModelListener)
	 */
	public void addTreeModelListener(TreeModelListener listener) {
		modelListenerList.add(listener);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TreeModel#removeTreeModelListener(com.mg.framework.support.ui.widget.TreeModelListener)
	 */
	public void removeTreeModelListener(TreeModelListener listener) {
		modelListenerList.remove(listener);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TreeModel#load()
	 */
	public void load() {
		doLoad();
		fireTreeChange();
	}

}
