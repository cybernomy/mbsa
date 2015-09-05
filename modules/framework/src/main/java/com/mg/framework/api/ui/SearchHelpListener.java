/*
 * SearchHelpListener.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.framework.api.ui;

import java.util.EventListener;

/**
 * Слушатель событий поиска сущностей
 * 
 * @author Oleg V. Safonov
 * @version $Id: SearchHelpListener.java,v 1.3 2006/08/25 11:24:41 safonov Exp $
 */
public interface SearchHelpListener extends EventListener {
	
	/**
	 * выполняется если поиск был произведен
	 * 
	 * @param event	событие поиска
	 */
	void searchPerformed(SearchHelpEvent event);
	
	/**
	 * выполняется если поиск не был произведен
	 * 
	 * @param event	событие
	 */
	void searchCanceled(SearchHelpEvent event);
}
