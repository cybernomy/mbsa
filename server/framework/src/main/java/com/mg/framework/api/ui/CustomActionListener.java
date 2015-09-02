/**
 * CustomActionListener.java
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

import java.util.EventListener;

/**
 * Слушатель настраиваемых действий
 * 
 * @author Oleg V. Safonov
 * @version $Id: CustomActionListener.java,v 1.1 2007/11/15 08:35:11 safonov Exp $
 */
public interface CustomActionListener extends EventListener {

	/**
	 * действие выполнено
	 * 
	 * @param refresh	признак обновления содержимого элемента пользовательского интерфейса
	 */
	void completed(boolean refresh);
	
	/**
	 * действие отменено
	 */
	void aborted();

}
