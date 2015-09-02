/*
 * TransientLicensingManager.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.framework.service;

/**
 * @author Oleg V. Safonov
 * @version $Id: TransientLicensingManager.java,v 1.2 2007/04/13 14:03:15 safonov Exp $
 */
public class TransientLicensingManager extends LicenseException {

	public TransientLicensingManager(String msg) {
		super(msg);
	}
	
}
