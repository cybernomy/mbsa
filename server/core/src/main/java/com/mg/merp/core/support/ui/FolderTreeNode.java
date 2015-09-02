/**
 * FolderTreeNode.java.java
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
package com.mg.merp.core.support.ui;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mg.framework.support.ui.widget.tree.EntityTreeNode;
import com.mg.merp.core.model.Folder;

/**
 * ���� ������ ������� �����
 * 
 * @author Oleg V. Safonov
 * @version $Id: FolderTreeNode.java,v 1.1 2007/08/21 13:38:31 safonov Exp $
 */
public class FolderTreeNode extends EntityTreeNode {

	public FolderTreeNode(FolderTreeNode parent, Folder entity) {
		super(parent);
		this.entity = entity;
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
		return ((Folder) entity).getFName();
	}

	public void addChild(FolderTreeNode child) {
		children.add(child);
	}
	
	/**
	 * �������������� ������ ����� � ������������� ���������
	 * 
	 * @param folders
	 * @return
	 */
	public static FolderTreeNode createTree(List<Folder> folders) {
		FolderTreeNode root = null;
		Map<Integer, FolderTreeNode> nodeMap = new HashMap<Integer, FolderTreeNode>();
		for (Folder folder : folders) {
			if (folder.getFolder() == null) {
				root = new FolderTreeNode(null, folder);
				nodeMap.put(folder.getId(), root);
			}
			else {
				FolderTreeNode parentNode, node;
				parentNode = nodeMap.get(folder.getFolder().getId());
				if (parentNode != null) {
					node = new FolderTreeNode(parentNode, folder);
					nodeMap.put(folder.getId(), node);
					parentNode.addChild(node);
				}
			}
		}
		return root;
	}

}
