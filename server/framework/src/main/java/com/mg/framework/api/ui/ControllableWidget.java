/*
 * ControllableWidget.java
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
package com.mg.framework.api.ui;

/**
 * Элемент пользовательского интерфейса взаимодействующий с контроллером формы через
 * механизм адаптеров, и не имеющий прямой связи с моделью контроллера
 * 
 * @author Oleg V. Safonov
 * @version $Id: ControllableWidget.java,v 1.2 2006/11/21 15:31:23 safonov Exp $
 */
public interface ControllableWidget extends Widget {
	/**
	 * установка адаптера для управления данным элементом
	 * 
	 * @param adapter	адаптер
	 */
	public void setAdapter(Object adapter);
}
