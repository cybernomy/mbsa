/*
 * GraphModelEvent.java
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

import com.mg.framework.api.ui.UIEvent;
import com.mg.framework.support.ui.widget.graph.GraphElement;

/**
 * Событие на изменение модели графа
 * 
 * @author Oleg V. Safonov
 * @version $Id: GraphModelEvent.java,v 1.2 2006/11/21 15:39:15 safonov Exp $
 */
public class GraphModelEvent extends UIEvent {
	private GraphElement cell;

	public GraphModelEvent(GraphControllerAdapter source, GraphElement cell) {
		super(source);
		this.cell = cell;
	}

	/**
	 * получить измененный элемент графа
	 * 
	 * @return
	 */
	public GraphElement getCell() {
		return cell;
	}
}
