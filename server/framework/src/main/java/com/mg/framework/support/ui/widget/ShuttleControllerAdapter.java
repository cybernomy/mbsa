/*
 * ShuttleControllerAdapter.java
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

/**
 * Адаптер элмента "Shuttle"
 * 
 * @author Oleg V. Safonov
 * @version $Id: ShuttleControllerAdapter.java,v 1.1 2006/08/31 08:36:41 safonov Exp $
 */
public interface ShuttleControllerAdapter {

	/**
	 * получить модель
	 * 
	 * @return	модель
	 */
	ShuttleModel getModel();
	
	/**
	 * добавить слушателя на события элмента
	 * 
	 * @param listener	слушатель
	 */
	void addShuttleListener(ShuttleListener listener);
	
	/**
	 * удалить слушателя на события элемента
	 * 
	 * @param listener	слушатель
	 */
	void removeShuttleListener(ShuttleListener listener);
	
	/**
	 * переместить список из списка источника в список приемник
	 * 
	 * @param contents	содержимое
	 */
	void move(Object[] contents);
	
	/**
	 * переместить список из списка приемника в список источник
	 * 
	 * @param contents	содержимое
	 */
	void remove(Object[] contents);

}
