/*
 * EntityTreeNode.java
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
import java.util.Collections;
import java.util.List;

import com.mg.framework.api.orm.PersistentObject;

/**
 * ���� ������ ��������� � �������� ���������
 * 
 * @author Oleg V. Safonov
 * @version $Id: EntityTreeNode.java,v 1.3 2009/02/09 13:36:35 safonov Exp $
 */
public abstract class EntityTreeNode extends TreeNode {
	protected PersistentObject entity = null;

	protected abstract Serializable doGetPrimaryKey();
	
	/**
	 * @param parent
	 */
	public EntityTreeNode(TreeNode parent) {
		super(parent);
	}

	/**
	 * ��������� ���������� ����� �������� ��������� � ������ ����� ������
	 * 
	 * @return
	 */
	public Serializable getPrimaryKey() {
		return doGetPrimaryKey();
	}

	/**
	 * @return Returns the entity.
	 */
	public PersistentObject getEntity() {
		return entity;
	}

	private boolean findPathToChild(Serializable primaryKey, List<EntityTreeNode> list) {
		if (getPrimaryKey().equals(primaryKey)) {
			list.add(this);
			return true;
		}
		
		for (int i = 0; i < getChildCount(); i++) {
			boolean find = ((EntityTreeNode) getChildAt(i)).findPathToChild(primaryKey, list);
			//������� �������� � ����
			if (find) {
				list.add(this);
				return true;
			}
		}
		
		// No match at this branch
		return false;
	}

	/**
	 * ����� �������� �� ���� ����������� �������� � ��������� ��������� ������
	 * 
	 * @param primaryKey	��������� ����
	 * @return	������� �� ��������� ���� �� ���� ����������� �������� ��� <code>null</code> ���� ������� �� ������
	 */
	public EntityTreeNode[] findPathToEntity(Serializable primaryKey) {
		if (primaryKey == null)
			return null;
		
		List<EntityTreeNode> result = new ArrayList<EntityTreeNode>();
		findPathToChild(primaryKey, result);
		Collections.reverse(result);
		return result.size() == 0 ? null : result.toArray(new EntityTreeNode[result.size()]);
	}
	
}
