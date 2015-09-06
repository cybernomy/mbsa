/*
 * Projection.java
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
package com.mg.framework.api.orm;

/**
 * An object-oriented representation of a query result set projection in a <tt>Criteria</tt> query.
 * Built-in projection types are provided by the <tt>Projections</tt> factory class. This interface
 * might be implemented by application classes that define custom projections.
 *
 * @author Oleg V. Safonov
 * @version $Id: Projection.java,v 1.2 2006/12/12 13:54:52 safonov Exp $
 */
public interface Projection {

  /**
   * set alias for projection
   *
   * @return this (for method chaining)
   */
  Projection as(String alias);

}
