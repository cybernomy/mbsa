/*
 * TreeModel.java
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
package com.mg.framework.support.ui.widget;

import com.mg.framework.support.ui.widget.tree.TreeNode;

/**
 * модель дерева
 * 
 * @author Oleg V. Safonov
 * @version $Id: TreeModel.java,v 1.1 2006/06/26 11:47:21 safonov Exp $
 */
public interface TreeModel {
	
	/**
	 * получить корневой узел
	 * 
	 * @return корневой узел
	 */
	TreeNode getRootNode();
	
	/**
	 * Adds a listener for the TreeModelEvent posted after the tree changes.
	 * 
	 * @param listener the listener to add
	 * 
	 * @see #removeTreeModelListener(TreeModelListener)
	 */
	void addTreeModelListener(TreeModelListener listener);
	
	/**
	 * Removes a listener previously added with <code>addTreeModelListener()</code>.
	 * 
	 * @param listener the listener to remove
	 * 
	 * @see #addTreeModelListener(TreeModelListener)
	 */
	void removeTreeModelListener(TreeModelListener listener);
	
	/**
	 * загрузка модели
	 *
	 */
	void load();
}
