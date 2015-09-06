/*
 * RollbackNotAllowedException.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.docflow;

import com.mg.framework.api.BusinessException;
import com.mg.merp.docflow.support.Messages;

/**
 * @author Oleg V. Safonov
 * @version $Id: RollbackNotAllowedException.java,v 1.2 2006/08/25 11:44:01 safonov Exp $
 */
@javax.ejb.ApplicationException
public class RollbackNotAllowedException extends BusinessException {

  public RollbackNotAllowedException() {
    super(Messages.getInstance().getMessage(Messages.ROLLBACK_NOT_ALLOWED_MESSAGE));
  }
}
