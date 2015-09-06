/*
 * SqlProvider.java
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
 * Interface to be implemented by objects that can provide SQL strings.
 *
 * <p>Typically implemented by statement creators that want to expose the SQL they use to create
 * their statements, to allow for better contextual information in case of exceptions.
 *
 * @author Oleg V. Safonov
 * @author Juergen Hoeller
 */
public interface SqlProvider {
  String getSql();
}
