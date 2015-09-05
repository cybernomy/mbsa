/*
 * FormEvent.java
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


/**
 * @author Oleg V. Safonov
 * @version $Id: FormEvent.java,v 1.1 2006/01/24 13:48:59 safonov Exp $
 */
public class FormEvent extends UIEvent {
	public FormEvent(Form source) {
		super(source);
	}
	
	public Form getForm() {
		return (Form) getSource();
	}
}
