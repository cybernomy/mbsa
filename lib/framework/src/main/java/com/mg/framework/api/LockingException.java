/*
 * LockingException.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.framework.api;

import com.mg.framework.support.Messages;

/**
 * ИС блокировки объекта, генерируется в случае попытки блокировки объекта,
 * заблокированного другой транзакцией
 * 
 * @author Oleg V. Safonov
 * @version $Id: LockingException.java,v 1.1 2006/12/15 09:26:17 safonov Exp $
 */
@javax.ejb.ApplicationException
public class LockingException extends ApplicationException {

	public LockingException() {
		super(Messages.getInstance().getMessage(Messages.LOCK_FAILED));
	}
	
	public LockingException(Throwable cause) {
		super(Messages.getInstance().getMessage(Messages.LOCK_FAILED), cause);
	}
	
}
