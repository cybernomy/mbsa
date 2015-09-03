/*
 * GraphModelListener.java
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

import java.util.EventListener;

/**
 * Слушатель модели графа
 * 
 * @author Oleg V. Safonov
 * @version $Id: GraphModelListener.java,v 1.2 2006/11/21 15:39:15 safonov Exp $
 */
public interface GraphModelListener extends EventListener {
	/**
	 * обработчик события добавлен узел графа
	 * 
	 * @param event	событие
	 */
	void vertexAdded(GraphModelEvent event);
	
	/**
	 * обработчик события добавлено ребро графа
	 * 
	 * @param event	событие
	 */
	void edgeAdded(GraphModelEvent event);
	
	/**
	 * обработчик события удален элемент графа
	 * 
	 * @param event	событие
	 */
	void cellRemoved(GraphModelEvent event);
}
