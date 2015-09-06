/*
 * MatchMode.java
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
 * Represents an strategy for matching strings using "like".
 *
 * @author Oleg V. Safonov
 * @version $Id: MatchMode.java,v 1.1 2006/01/24 13:47:40 safonov Exp $
 */
public enum MatchMode {
  EXACT,
  START,
  END,
  ANYWHERE
}
