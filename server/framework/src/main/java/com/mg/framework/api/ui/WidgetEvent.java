/*
 * WidgetEvent.java
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
package com.mg.framework.api.ui;

/**
 * Событие элемента пользовательского интерфейса
 * 
 * @author Oleg V. Safonov
 * @version $Id: WidgetEvent.java,v 1.2 2007/11/15 08:34:38 safonov Exp $
 */
public class WidgetEvent extends UIEvent {
	private String actionCommand = null;

	/**
	 * конструктор
	 * 
	 * @param source	элемент пользовательского интерфейса
	 */
	public WidgetEvent(Widget source) {
		super(source);
	}

	/**
	 * конструктор
	 * 
	 * @param source	элемент пользовательского интерфейса
	 * @param actionCommand	команда
	 */
	public WidgetEvent(Widget source, String actionCommand) {
		super(source);
		this.actionCommand = actionCommand;
	}

	/**
	 * получить элемент пользовательского интерфейса сгенерирующего данное событие
	 * 
	 * @return	элемент пользовательского интерфейса
	 */
	public Widget getWidget() {
		return (Widget) getSource();
	}

	/**
	 * название команды, как правило используется для определения команды если
	 * используется один обработчик для нескольких элементов
	 * 
	 * @return the actionCommand	команда
	 */
	public String getActionCommand() {
		return actionCommand;
	}

}
