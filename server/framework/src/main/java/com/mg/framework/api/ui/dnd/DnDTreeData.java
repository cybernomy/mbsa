/*
 * DnDTreeData.java
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
package com.mg.framework.api.ui.dnd;

import com.mg.framework.api.ui.widget.Tree;
import com.mg.framework.support.ui.widget.tree.TreeNode;

/**
 * Данные операции DnD над элементом "дерево"
 * 
 * @author Oleg V. Safonov
 * @version $Id: DnDTreeData.java,v 1.1 2007/08/16 13:48:06 safonov Exp $
 */
public class DnDTreeData extends DnDData {
	private TreeNode[] treeNodes;
	private Tree tree;

	public DnDTreeData(Tree tree, TreeNode[] treeNodes) {
		this.treeNodes = treeNodes;
		this.tree = tree;
	}

	/**
	 * получить список веток дерева, как правило, если дерево является приемником, то содержится один элемент,
	 * если дерево является источником, то возможно несколько элементов перетаскиваемых при операции
	 * 
	 * @return	список веток
	 */
	public TreeNode[] getTreeNodes() {
		return treeNodes;
	}
	
	/**
	 * элемент дерево
	 * 
	 * @return	дерево
	 */
	public Tree getTree() {
		return tree;
	}

}
