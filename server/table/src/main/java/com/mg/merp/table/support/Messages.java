/*
 * Messages.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.table.support;

import com.mg.framework.generic.MessageSourceAccessor;

/**
 * Класс сообщений
 *
 * @author Artem V. Sharapov
 * @version $Id: Messages.java,v 1.1 2008/08/12 14:36:17 sharapov Exp $
 */
public class Messages extends MessageSourceAccessor {
  //message keys
  public static final String SETUP_TIME_KINDS = "SetupTimeKinds"; //$NON-NLS-1$
  public static final String NEED_TO_FILL_HOLIDAY_WORK_TIME_KIND = "NeedToFillHolidayWorkTimeKind"; //$NON-NLS-1$
  public static final String INPUT_HOURS_DIALOG_TITLE = "InputHoursDialogTitle"; //$NON-NLS-1$
  private static final String BUNDLE_NAME = "com.mg.merp.table.resources.messages"; //$NON-NLS-1$
  private static Messages instance;

  static {
    MessageSourceAccessor.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  public static Messages getInstance() {
    return instance;
  }

}
