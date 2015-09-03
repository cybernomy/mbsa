/*
 * AbstractSQLExceptionTranslator.java
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
package com.mg.framework.generic;

import java.sql.SQLException;

import com.mg.framework.api.DataAccessException;
import com.mg.framework.api.jdbc.SQLExceptionTranslator;

/**
 * Абстрактная реализация траслятора SQL ИС
 * 
 * @author Oleg V. Safonov
 * @version $Id: AbstractSQLExceptionTranslator.java,v 1.1 2006/11/17 14:15:57 safonov Exp $
 */
public abstract class AbstractSQLExceptionTranslator implements SQLExceptionTranslator {

	/* (non-Javadoc)
	 * @see com.mg.framework.api.SQLExceptionTranslator#translate(java.sql.SQLException)
	 */
	public abstract DataAccessException translate(SQLException sqlException);

}
