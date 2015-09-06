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
package com.mg.merp.mfreference.support;

import com.mg.framework.generic.MessageSourceAccessor;

/**
 * @author Oleg V. Safonov
 * @version $Id: Messages.java,v 1.4 2008/03/04 13:37:11 alikaev Exp $
 */
public class Messages extends MessageSourceAccessor {
  //message keys
  public static final String CHOOSED_MEASURE = "ChoosedMeasure"; //$NON-NLS-1$
  public static final String INVALID_TIME_FORMAT = "InvalidTimeFormat"; //$NON-NLS-1$
  public static final String DAY_CALENDAR_NOT_FOUND = "DayCalendarNotFound"; //$NON-NLS-1$
  public static final String DAY_TIME_NOT_FOUND = "DayTimeNotFound"; //$NON-NLS-1$
  public static final String ACTUALITY_DATE_TITLE = "ActualityDateTitle"; //$NON-NLS-1$
  public static final String ACTUALITY_DATE_PROMPT = "ActualityDatePrompt"; //$NON-NLS-1$
  public static final String INVALID_RESOURSE_FAMILY_STRUCTURE = "InvalidResourceFamilyStructure"; //$NON-NLS-1$
  public static final String INVALID_RESOURSE_FAMILY_PLANNINGLEVEL = "InvalidResourceFamilyPlanningLevel"; //$NON-NLS-1$
  private static final String BUNDLE_NAME = "com.mg.merp.mfreference.resources.messages"; //$NON-NLS-1$
  private static Messages instance;

  static {
    MessageSourceAccessor.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  public static Messages getInstance() {
    return instance;
  }
}
