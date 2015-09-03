/*
 * InvalidStatusException.java
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
package com.mg.merp.manufacture;

import javax.ejb.ApplicationException;

import com.mg.framework.api.BusinessException;
import com.mg.merp.manufacture.model.JobStatus;
import com.mg.merp.manufacture.support.Messages;

/**
 * ИС генерируется если при выполнении обнаружен неверный статус ЗНП
 * 
 * @author Oleg V. Safonov
 * @version $Id: InvalidJobStatusException.java,v 1.2 2007/07/30 10:28:17 safonov Exp $
 */
@ApplicationException
public class InvalidJobStatusException extends BusinessException {
	public JobStatus currentStatus;
	
	public InvalidJobStatusException(JobStatus currentStatus) {
		super("Invalid job status: " + currentStatus);
		this.currentStatus = currentStatus;
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getLocalizedMessage()
	 */
	@Override
	public String getLocalizedMessage() {
		return Messages.getInstance().getMessage(Messages.INVALID_JOB_STATUS);
	}

}
