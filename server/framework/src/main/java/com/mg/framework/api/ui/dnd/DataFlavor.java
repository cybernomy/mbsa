/*
 * DataFlavor.java
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
package com.mg.framework.api.ui.dnd;


/**
 * Вид данных операции DnD
 * 
 * @author Oleg V. Safonov
 * @version $Id: DataFlavor.java,v 1.1 2007/08/16 13:48:06 safonov Exp $
 */
public enum DataFlavor {
	/**
	 * перетаскиваемые данные
	 */
	DRAG_FLAVOR,
	/**
	 * данные при отпускании
	 */
	DROP_FLAVOR
}
