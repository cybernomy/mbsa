/*
 * DefaultSQLExceptionTranslatorImpl.java
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
package com.mg.framework.service;

import java.sql.SQLException;

import com.mg.framework.api.DataAccessException;
import com.mg.framework.generic.AbstractSQLExceptionTranslator;
import com.mg.framework.support.Messages;

/**
 * Обработчик стандартных SQL ИС
 * 
 * @author Oleg V. Safonov
 * @version $Id: DefaultSQLExceptionTranslatorImpl.java,v 1.1 2006/11/17 14:28:55 safonov Exp $
 */
public class DefaultSQLExceptionTranslatorImpl extends AbstractSQLExceptionTranslator {
	private final static String DBMS_FOREIGN_KEY_VIOLATION = "VIOLATION OF FOREIGN KEY"; //$NON-NLS-1$
	private final static String DBMS_PRIMARY_KEY_VIOLATION = "VIOLATION OF PRIMARY OR UNIQUE KEY"; //$NON-NLS-1$
	private final static String DBMS_LOCK_CONFLICT = "lock conflict"; //$NON-NLS-1$

	/* (non-Javadoc)
	 * @see com.mg.framework.api.SQLExceptionTranslator#translate(java.sql.SQLException)
	 */
	@Override
	public DataAccessException translate(SQLException sqlException) {
		String msg = null;
		String sqlMsg = sqlException.getMessage().toUpperCase();
		if (sqlMsg.indexOf(DBMS_FOREIGN_KEY_VIOLATION) > 0)
			msg = Messages.getInstance().getMessage(Messages.DBMS_FOREIGN_KEY_VIOLATION);
		else if (sqlMsg.indexOf(DBMS_PRIMARY_KEY_VIOLATION) > 0)
			msg = Messages.getInstance().getMessage(Messages.DBMS_PRIMARY_KEY_VIOLATION);
		else if (sqlMsg.indexOf(DBMS_LOCK_CONFLICT) > 0)
			msg = Messages.getInstance().getMessage(Messages.DBMS_LOCK_CONFLICT);
		
		if (msg != null)
			return new DataAccessException(msg, sqlException);
		else
			return null;
	}

}
