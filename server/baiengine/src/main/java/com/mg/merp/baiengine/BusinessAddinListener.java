/*
 * BusinessAddinListener.java
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
package com.mg.merp.baiengine;

import java.util.EventListener;

/**
 * Слушатель событий в BAi
 * 
 * @author Oleg V. Safonov
 * @version $Id: BusinessAddinListener.java,v 1.1 2006/06/08 11:39:47 safonov Exp $
 */
public interface BusinessAddinListener<T> extends EventListener {
	/**
	 * Обработка успешного завершения BAi
	 * 
	 * @param event
	 */
	public void completed(BusinessAddinEvent<T> event);
	
	/**
	 * Обработка прерывания выполнения BAi
	 * 
	 * @param event
	 */
	public void aborted(BusinessAddinEvent<T> event);
}
