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
package com.mg.merp.manufacture.support;

import com.mg.framework.generic.MessageSourceAccessor;

/**
 * @author Oleg V. Safonov
 * @version $Id: Messages.java,v 1.1 2007/07/30 10:27:27 safonov Exp $
 */
public class Messages extends MessageSourceAccessor {
  //message keys
  public static final String BOM_NOT_FOUND = "BOMNotFound"; //$NON-NLS-1$
  public static final String INVALID_JOB_STATUS = "InvalidJobStatus"; //$NON-NLS-1$
  public static final String CURRENT_JOB_STATUS = "CurrentJobStatus"; //$NON-NLS-1$
  public static final String BOM_ALREADY_EXIST = "BOMAlreadyExist"; //$NON-NLS-1$
  public static final String JOB_PRODUCT_IS_EMPTY = "JobProductIsEmpty"; //$NON-NLS-1$
  public static final String JOB_BOM_IS_EMPTY = "JobBOMIsEmpty"; //$NON-NLS-1$
  public static final String ACTUALITY_DATE = "ActualityDate"; //$NON-NLS-1$
  public static final String INPUT_DATE = "InputDate"; //$NON-NLS-1$
  public static final String CALC_STD_COST_SUCCESSFULLY_COMPLETE = "CalculateStandartCostSuccessfullyComplete"; //$NON-NLS-1$
  public static final String STANDART_COST_NOT_FOUND = "StandartCostNotFound"; //$NON-NLS-1$
  private static final String BUNDLE_NAME = "com.mg.merp.manufacture.resources.messages"; //$NON-NLS-1$
  private static Messages instance;

  static {
    MessageSourceAccessor.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  public static Messages getInstance() {
    return instance;
  }
}
