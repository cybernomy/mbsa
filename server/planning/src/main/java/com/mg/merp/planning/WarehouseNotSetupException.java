/*
 * WarehouseNotSetupException.java
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
package com.mg.merp.planning;

import javax.ejb.ApplicationException;

import com.mg.framework.api.BusinessException;
import com.mg.merp.planning.support.Messages;

/**
 * ИС генерируется если не настроен склад
 * 
 * @author Oleg V. Safonov
 * @version $Id: WarehouseNotSetupException.java,v 1.1 2007/07/30 10:37:51 safonov Exp $
 */
@ApplicationException
public class WarehouseNotSetupException extends BusinessException {
	private String reference;

	public WarehouseNotSetupException(String reference) {
		super("warehouse not setup");
		this.reference = reference;
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getLocalizedMessage()
	 */
	@Override
	public String getLocalizedMessage() {
		return Messages.getInstance().getMessage(Messages.WAREHOUSE_NOT_SETUP, new Object[] {reference});
	}

}
