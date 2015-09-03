/*
 * SelectionRowListener.java
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
package com.mg.merp.paymentalloc;

import java.util.EventListener;

/**
 * Слушатель события изменения позиции курсора таблицы
 * 
 * @author Artem V. Sharapov
 * @version $Id: SelectionRowListener.java,v 1.1 2007/06/05 12:44:31 sharapov Exp $
 */
public interface SelectionRowListener extends EventListener {

	public void selectedRowChange(Integer primaryKey);
	
}
