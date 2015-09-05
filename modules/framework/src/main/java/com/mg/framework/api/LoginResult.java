/*
 * LoginResult.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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
package com.mg.framework.api;

import java.io.Serializable;

/**
 * @author Oleg V. Safonov
 *
 */
public class LoginResult implements Serializable {
	public String locale;
	public LoginResult(String locale) {
		this.locale = locale;
	}
}
