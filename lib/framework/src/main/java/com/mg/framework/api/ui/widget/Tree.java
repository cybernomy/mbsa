/*
 * Tree.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.framework.api.ui.widget;

import com.mg.framework.api.ui.ControllableWidget;
import com.mg.framework.api.ui.Widget;
import com.mg.framework.support.ui.widget.tree.TreeNode;

/**
 * Элемент пользовательского интерфейса "Дерево"
 * 
 * @author Oleg V. Safonov
 * @version $Id: Tree.java,v 1.4 2009/02/09 13:17:47 safonov Exp $
 */
public interface Tree extends Widget, ControllableWidget {

	/**
	 * выделяет узел на указанном ряду
	 * 
	 * @param row	ряд, нумерация начинается с <code>0</code>
	 */
	void setSelectionRow(int row);

	/**
	 * получить список выделенных рядов
	 * 
	 * @return	список выделенных рядов
	 */
	int[] getSelectionRows();

	/**
	 * выделить узел по указанному маршруту
	 * 
	 * @param nodes	маршрут узлов начиная с корневого
	 */
	void setSelectionPath(TreeNode nodes[]);

}
