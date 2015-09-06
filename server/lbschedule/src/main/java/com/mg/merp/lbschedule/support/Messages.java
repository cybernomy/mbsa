/*
 * Messages.java
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
package com.mg.merp.lbschedule.support;

import com.mg.framework.generic.MessageSourceAccessor;

/**
 * Класс пользовательских сообщений
 *
 * @author Artem V. Sharapov
 * @version $Id: Messages.java,v 1.1 2007/04/17 12:50:59 sharapov Exp $
 */
public class Messages extends MessageSourceAccessor {
  //message keys
  public static final String INVALID_DATE_CONDITIONS = "InvalidDateConditions"; //$NON-NLS-1$
  public static final String INVALID_SUM_CONDITIONS = "InvalidSumConditions"; //$NON-NLS-1$
  public static final String PRICE_LIST_SPEC_TITLE = "PriceListSpecTitle"; //$NON-NLS-1$
  private static final String BUNDLE_NAME = "com.mg.merp.lbschedule.resources.messages"; //$NON-NLS-1$
  private static Messages instance;

  static {
    MessageSourceAccessor.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  public static Messages getInstance() {
    return instance;
  }
}
