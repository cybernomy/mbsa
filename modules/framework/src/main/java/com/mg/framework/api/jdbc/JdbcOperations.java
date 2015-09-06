/*
 * JdbcOperations.java
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
 * Interface that specifies a basic set of JDBC operations. Implemented by JdbcTemplate. Not often
 * used, but a useful option to enhance testability, as it can easily be mocked or stubbed.
 *
 * <p>Alternatively, the standard JDBC infrastructure can be mocked. However, mocking this interface
 * constitutes significantly less work.
 *
 * @author Oleg V. Safonov
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @version $Id: JdbcOperations.java,v 1.2 2005/04/01 08:10:41 safonov Exp $
 */
public interface JdbcOperations {

}
