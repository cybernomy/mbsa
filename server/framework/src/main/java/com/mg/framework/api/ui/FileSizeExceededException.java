/**
 * FileSizeExceededException.java
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
package com.mg.framework.api.ui;

import com.mg.framework.api.ReasonException;
import com.mg.framework.support.Messages;

/**
 * ИС превышения допустимого размера файла для загрузки/выгрузки
 * 
 * @author Oleg V. Safonov
 * @version $Id: FileSizeExceededException.java,v 1.1 2008/05/29 13:41:01 safonov Exp $
 */
public class FileSizeExceededException extends ReasonException {

	public FileSizeExceededException(String reason) {
		super("file size exceeded", reason);
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getLocalizedMessage()
	 */
	@Override
	public String getLocalizedMessage() {
		return Messages.getInstance().getMessage(Messages.FILE_SIZE_EXCEEDED_EXCEPTION);
	}

	
}
