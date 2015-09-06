/*
 * InvalidMeasureConversion.java
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
package com.mg.merp.reference;

import com.mg.framework.api.BusinessException;
import com.mg.merp.reference.support.Messages;

import javax.ejb.ApplicationException;

/**
 * ИС преобразования ЕИ, генерируется при возниконовении ошибок преобразования ЕИ
 *
 * @author Oleg V. Safonov
 * @version $Id: InvalidMeasureConversion.java,v 1.3 2007/06/20 13:55:07 safonov Exp $
 */
@ApplicationException
public class InvalidMeasureConversion extends BusinessException {
  private String measureFrom;
  private String measureTo;

  public InvalidMeasureConversion(String measureFrom, String measureTo) {
    super(String.format("Measure conversion maintenance not found: %s, %s", measureFrom, measureTo));
    this.measureFrom = measureFrom;
    this.measureTo = measureTo;
  }

  /* (non-Javadoc)
   * @see java.lang.Throwable#getLocalizedMessage()
   */
  @Override
  public String getLocalizedMessage() {
    return Messages.getInstance().getMessage(Messages.MEASURE_CONVERSION_MAINTENANCE_NOT_FOUND, new Object[]{measureFrom, measureTo});
  }

}
