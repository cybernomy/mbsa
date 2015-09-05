/*
 * SQLWarningException.java
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
package com.mg.framework.api.jdbc;

/**
 * Exception thrown when we're not ignoring warnings.
 *
 * <p>If such an exception is thrown, the operation completed,
 * so we will need to explicitly roll it back if we're not happy
 * on looking at the warning. We might choose to ignore (or merely log)
 * the warning and throw the exception away.
 *
 * @author Oleg V. Safonov
 * @author Rod Johnson
 * @version $Id: SQLWarningException.java,v 1.2 2005/04/01 08:10:42 safonov Exp $
 *
 */
public class SQLWarningException extends DataAccessException {

}
