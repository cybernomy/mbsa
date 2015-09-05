/*
 * GraphControllerAdapter.java
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
package com.mg.framework.support.ui.widget;

import java.util.List;

import com.mg.framework.support.ui.widget.graph.GraphElement;

/**
 * Адаптер визуального элемента Граф
 * 
 * @author Oleg V. Safonov
 * @version $Id: GraphControllerAdapter.java,v 1.2 2006/11/21 15:39:15 safonov Exp $
 */
public interface GraphControllerAdapter {
	/**
	 * получить список элементов графа
	 * 
	 * @return	список элементов
	 */
	List<? extends GraphElement> getElements();
	
	/**
	 * установить слушатель модели графа
	 * 
	 * @param listener	слушатель
	 */
	void addGraphModelListener(GraphModelListener listener);
	
	/**
	 * отметить элементы графа
	 * 
	 * @param cells	элементы графа
	 * @param areNewRoots	признаки новых элементов
	 */
	void selectCells(GraphElement[] cells, boolean[] areNewRoots);
	
	/**
	 * отправка сообщения об изменении элемента графа
	 * 
	 * @param cell	элемент графа
	 */
	void cellChanged(GraphElement cell);
}
