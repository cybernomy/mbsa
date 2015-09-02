/*
 * ShowExceptionDialog.java
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
package com.mg.framework.api.ui;

/**
 * Exception dialog
 * 
 * @author Oleg V. Safonov
 * @version $Id: ShowExceptionDialog.java,v 1.1 2006/06/27 12:06:24 safonov Exp $
 */
public interface ShowExceptionDialog {

	/**
	 * показать форму сообщени€ и »—
	 * 
	 * @param title			заголовок формы
	 * @param message		сообщение
	 * @param detailMessage	дополнительное сообщение
	 */
	void show(String title, String message, String detailMessage);

}
