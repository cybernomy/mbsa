/*
 * BOMIsEmptyException.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.manufacture;

import com.mg.framework.api.BusinessException;

import javax.ejb.ApplicationException;

/**
 * @author safonov
 * @version $Id: BOMIsEmptyException.java,v 1.2 2007/07/30 10:28:17 safonov Exp $
 */
@ApplicationException
public class BOMIsEmptyException extends BusinessException {
  public BOMIsEmptyException() {
    super();
  }
}
