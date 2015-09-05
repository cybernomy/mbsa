/*
 * UIEvent.java
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

import java.util.EventObject;

/**
 * @author Oleg V. Safonov
 * @version $Id: UIEvent.java,v 1.1 2006/01/24 13:48:59 safonov Exp $
 */
public class UIEvent extends EventObject {
	public UIEvent(Object source) {
		super(source);
	}
}
