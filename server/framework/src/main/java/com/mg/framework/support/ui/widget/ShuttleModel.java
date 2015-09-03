/*
 * ShuttleModel.java
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
 * Модель элемента "Shuttle"
 * 
 * @author Oleg V. Safonov
 * @version $Id: ShuttleModel.java,v 1.1 2006/08/31 08:36:41 safonov Exp $
 */
public interface ShuttleModel {

	/**
	 * получить список источника
	 * 
	 * @return	список значений
	 */
	Object[] getLeadingList();
	
	/**
	 * установить список источника
	 * 
	 * @param contents	список значений
	 */
	void setLeadingList(Object[] contents);
	
	/**
	 * получить список приемника
	 * 
	 * @return	список значений
	 */
	Object[] getTrailingList();
	
	/**
	 * установить список приемника
	 * 
	 * @param contents	список значений
	 */
	void setTrailingList(Object[] contents);
	
	/**
	 * добавить слушателя на модель
	 * 
	 * @param listener	слушатель
	 */
	void addShuttleModelListener(ShuttleModelListener listener);
	
	/**
	 * удалить слушателя на модель
	 * 
	 * @param listener	слушатель
	 */
	void removeShuttleModelListener(ShuttleModelListener listener);
}
