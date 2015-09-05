/*
 * ResultReader.java
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

import java.util.List;

/**
 * Extension of RowCallbackHandler interface that saves the accumulated results
 * as a List.
 *
 * <p>Allows to make a results list available in a uniform manner. JdbcTemplate's
 * query methods will return the results list in that case, else returning null
 * (-> result state is solely available from RowCallbackHandler object).
 *
 * <p>A convenient out-of-the-box implementation of ResultReader is the
 * RowMapperResultReader adapter which delegates row mapping to a RowMapper.
 * Note that a RowMapper object is typically stateless and thus reusable;
 * just the RowMapperResultReader adapter is stateful.
 * 
 * @author Oleg V. Safonov
 * @author Rod Johnson
 * @version $Id: ResultReader.java,v 1.2 2005/04/01 08:10:41 safonov Exp $
 *
 */
public interface ResultReader extends RowCallbackHandler {
    
    /**
     * Return all results, disconnected from the JDBC ResultSet.
     * Never returns null; returns the empty collection if there
     * were no results.
     */ 
    List getResults();
}
