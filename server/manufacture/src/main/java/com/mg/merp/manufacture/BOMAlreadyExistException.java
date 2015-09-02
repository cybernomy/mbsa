/*
 * BOMAlreadyExistException.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.manufacture;

import javax.ejb.ApplicationException;

import com.mg.framework.api.BusinessException;
import com.mg.merp.manufacture.support.Messages;

/**
 * ИС генерируется если ЗНП уже содержит состав изделия
 * 
 * @author Oleg V. Safonov
 * @version $Id: BOMAlreadyExistException.java,v 1.2 2007/07/30 10:28:17 safonov Exp $
 */
@ApplicationException
public class BOMAlreadyExistException extends BusinessException {

	public BOMAlreadyExistException() {
		super("BOM already exist");
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getLocalizedMessage()
	 */
	@Override
	public String getLocalizedMessage() {
		return Messages.getInstance().getMessage(Messages.BOM_ALREADY_EXIST);
	}

}
