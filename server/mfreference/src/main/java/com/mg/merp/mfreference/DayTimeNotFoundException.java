/*
 * DayTimeNotFoundException.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.mfreference;

import com.mg.framework.api.BusinessException;
import com.mg.merp.mfreference.model.DayCalendar;
import com.mg.merp.mfreference.support.Messages;

import javax.ejb.ApplicationException;

/**
 * ИС генерируется если не найден сменный календарь
 *
 * @author Oleg V. Safonov
 * @version $Id: DayTimeNotFoundException.java,v 1.1 2007/07/30 10:25:31 safonov Exp $
 */
@ApplicationException
public class DayTimeNotFoundException extends BusinessException {
  private String code;

  public DayTimeNotFoundException(DayCalendar dayCalendar) {
    super("Day time not found");
    this.code = dayCalendar.getCode();
  }

  /* (non-Javadoc)
   * @see java.lang.Throwable#getLocalizedMessage()
   */
  @Override
  public String getLocalizedMessage() {
    return Messages.getInstance().getMessage(Messages.DAY_TIME_NOT_FOUND, new Object[]{code});
  }

}
