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
package com.mg.merp.personnelref.support;

import com.mg.framework.generic.MessageSourceAccessor;

/**
 * Класс пользовательских сообщений
 *
 * @author Artem V. Sharapov
 * @version $Id: Messages.java,v 1.5 2008/03/27 14:01:32 alikaev Exp $
 */
public class Messages extends MessageSourceAccessor {

  //message keys
  public static final String CURRENT_CALC_PERIOD_IS_NOT_SET = "CurrentCalcPeriodIsNotSet"; //$NON-NLS-1$
  public static final String CALC_PERIOD_END_DATE_AFTER_BEGIN_DATE = "CalcPeriodEndDateAfterBeginDate"; //$NON-NLS-1$
  public static final String CALC_PERIOD_IS_CROSS = "CalcPeriodIsCross"; //$NON-NLS-1$
  public static final String CHOOSE_ROOT_FOLDER = "ChooseRootFolder"; //$NON-NLS-1$
  public static final String PARENT_ANALITICS_INVALID = "ParentAnaliticsInvalid"; //$NON-NLS-1$
  public static final String ANALITICS_LEVEL_INVALID = "AnaliticsLevelInvalid"; //$NON-NLS-1$
  public static final String NOT_CHOOSE_STAFFLIST_BY_SEARCHED_CALCPERIOD = "NotChooseStafflistBySearchedCalcPeriod";
  private static final String BUNDLE_NAME = "com.mg.merp.personnelref.resources.messages"; //$NON-NLS-1$
  private static Messages instance;

  static {
    MessageSourceAccessor.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  public static Messages getInstance() {
    return instance;
  }

}
