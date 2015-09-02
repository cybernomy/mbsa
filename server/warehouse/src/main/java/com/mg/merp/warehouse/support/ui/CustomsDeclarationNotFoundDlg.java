/*
 * CustomsDeclarationNotFoundDlg.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.warehouse.support.ui;

import com.mg.framework.generic.ui.DefaultDialog;

/**
 * Контроллер диалога "Номер ГТД и страна происхождения не найдены" 
 * 
 * @author Artem V. Sharapov
 * @version $Id: CustomsDeclarationNotFoundDlg.java,v 1.1 2008/08/27 09:44:07 sharapov Exp $
 */
public class CustomsDeclarationNotFoundDlg extends DefaultDialog {

	public static final String WINDOW_NAME = "com.mg.merp.warehouse.CustomsDeclarationNotFoundDlg"; //$NON-NLS-1$
	private String message;

	public CustomsDeclarationNotFoundDlg() {
	}

	public void execute(String message) {
		this.message = message;
		execute();
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
