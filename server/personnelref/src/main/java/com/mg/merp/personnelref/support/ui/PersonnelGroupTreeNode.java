/*
 * PersonnelGroupTreeNode.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.personnelref.support.ui;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mg.framework.support.ui.widget.tree.EntityTreeNode;
import com.mg.merp.personnelref.model.PersonnelGroup;

/**
 * Узел дерева "Группы сотрудников"
 * 
 * @author leonova
 * @author Oleg V. Safonov
 * @version $Id: PersonnelGroupTreeNode.java,v 1.3 2008/01/11 10:00:59 safonov Exp $
 */
public class PersonnelGroupTreeNode extends EntityTreeNode {

	public PersonnelGroupTreeNode(PersonnelGroupTreeNode parent, PersonnelGroup entity) {
		super(parent);
		this.entity = entity;
	}

	public void addChild(PersonnelGroupTreeNode child) {
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
		return ((PersonnelGroup) entity).getFldName();
	}

	public static PersonnelGroupTreeNode createTree(List<PersonnelGroup> folders) {
		PersonnelGroupTreeNode root = null;
		Map<Integer, PersonnelGroupTreeNode> nodeMap = new HashMap<Integer, PersonnelGroupTreeNode>();
		for (PersonnelGroup folder : folders) {
			if (folder.getParentId() == null || folder.getParentId() == 0) {
				root = new PersonnelGroupTreeNode(null, folder);
				nodeMap.put(folder.getId(), root);
			}
			else {
				PersonnelGroupTreeNode parentNode, node;				
				parentNode = nodeMap.get(folder.getParentId());
				if (parentNode != null) {
					node = new PersonnelGroupTreeNode(parentNode, folder);
					nodeMap.put(folder.getId(), node);
					parentNode.addChild(node);
				}
			}
		}
		return root;
	}

}
