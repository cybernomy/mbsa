/*
 * TreeControllerAdapter.java
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

import com.mg.framework.api.ui.widget.Tree;
import com.mg.framework.support.ui.widget.tree.TreeNode;

/**
 * ������� ����������� �������� ������
 * 
 * @author Oleg V. Safonov
 * @version $Id: TreeControllerAdapter.java,v 1.3 2009/02/09 13:41:21 safonov Exp $
 */
public interface TreeControllerAdapter {
	
	/**
	 * �������� ������
	 * 
	 * @return	������
	 */
	TreeModel getModel();
	
	/**
	 * ��������� �������� ���� ������
	 * 
	 * @param node	������� ����
	 */
	void setCurrentNode(TreeNode node);

	/**
	 * ��������� �������� ����������������� ���������� "������" ���������� � ������ ������������
	 * 
	 * @param tree	������� UI "������"
	 */
	void setTree(Tree tree);

}
