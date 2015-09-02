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
 * Ёлемент пользовательского интерфейса "ƒерево"
 * 
 * @author Oleg V. Safonov
 * @version $Id: Tree.java,v 1.4 2009/02/09 13:17:47 safonov Exp $
 */
public interface Tree extends Widget, ControllableWidget {

	/**
	 * выдел€ет узел на указанном р€ду
	 * 
	 * @param row	р€д, нумераци€ начинаетс€ с <code>0</code>
	 */
	void setSelectionRow(int row);

	/**
	 * получить список выделенных р€дов
	 * 
	 * @return	список выделенных р€дов
	 */
	int[] getSelectionRows();

	/**
	 * выделить узел по указанному маршруту
	 * 
	 * @param nodes	маршрут узлов начина€ с корневого
	 */
	void setSelectionPath(TreeNode nodes[]);

}
