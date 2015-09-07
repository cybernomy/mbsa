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
package com.mg.merp.finance.support;

import com.mg.framework.generic.MessageSourceAccessor;

/**
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @author Konstantin S. Alikaev
 * @version $Id: Messages.java,v 1.10 2009/02/16 07:46:00 sharapov Exp $
 */
public class Messages extends MessageSourceAccessor {
  //message keys
  public static final String OPERATION_TITLE = "OperationTitle"; //$NON-NLS-1$
  public static final String TURNACC_INVALID_PERIOD_RANGE = "TurnAccountInvalidPeriodRange"; //$NON-NLS-1$
  public static final String TURNACC_INVALID_ACCOUNTS_LIST = "TurnAccountInvalidAccountsList"; //$NON-NLS-1$
  public static final String PERIOD_CLOSED = "PeriodClosed"; //$NON-NLS-1$
  public static final String INVALID_PERIOD = "InvalidPeriod"; //$NON-NLS-1$
  public static final String TURNACC_SELECTED_ACCOUNTS = "TurnAccountSelectedAcounts"; //$NON-NLS-1$
  public static final String FINPERIOD_IS_CROSS = "FinPeriodIsCross"; //$NON-NLS-1$
  public static final String FINPERIOD_IS_IN_USE = "FinPeriodIsInUse"; //$NON-NLS-1$
  public static final String FEATURE_NOT_NULL = "FeatureNotNull"; //$NON-NLS-1$
  public static final String PARENT_NOT_NULL = "ParentNotNull"; //$NON-NLS-1$
  public static final String FEATURE = "Feature"; //$NON-NLS-1$
  public static final String FEATURE_ANALYTICS1 = "FeatureAnalitycs1"; //$NON-NLS-1$
  public static final String FEATURE_ANALYTICS2 = "FeatureAnalitycs2"; //$NON-NLS-1$
  public static final String FEATURE_ANALYTICS3 = "FeatureAnalitycs3"; //$NON-NLS-1$
  public static final String FEATURE_ANALYTICS4 = "FeatureAnalitycs4"; //$NON-NLS-1$
  public static final String FEATURE_ANALYTICS5 = "FeatureAnalitycs5"; //$NON-NLS-1$
  public static final String SRCACCOUNT_ANALYTICS1 = "SrcAccountAnalitycs1"; //$NON-NLS-1$
  public static final String SRCACCOUNT_ANALYTICS2 = "SrcAccountAnalitycs2"; //$NON-NLS-1$
  public static final String SRCACCOUNT_ANALYTICS3 = "SrcAccountAnalitycs3"; //$NON-NLS-1$
  public static final String SRCACCOUNT_ANALYTICS4 = "SrcAccountAnalitycs4"; //$NON-NLS-1$
  public static final String SRCACCOUNT_ANALYTICS5 = "SrcAccountAnalitycs5"; //$NON-NLS-1$
  public static final String DSTACCOUNT_ANALYTICS1 = "DstAccountAnalitycs1"; //$NON-NLS-1$
  public static final String DSTACCOUNT_ANALYTICS2 = "DstAccountAnalitycs2"; //$NON-NLS-1$
  public static final String DSTACCOUNT_ANALYTICS3 = "DstAccountAnalitycs3"; //$NON-NLS-1$
  public static final String DSTACCOUNT_ANALYTICS4 = "DstAccountAnalitycs4"; //$NON-NLS-1$
  public static final String DSTACCOUNT_ANALYTICS5 = "DstAccountAnalitycs5"; //$NON-NLS-1$
  public static final String ACCOUNT_ANALYTICS1 = "AccountAnalytics1"; //$NON-NLS-1$
  public static final String ACCOUNT_ANALYTICS2 = "AccountAnalytics2"; //$NON-NLS-1$
  public static final String ACCOUNT_ANALYTICS3 = "AccountAnalytics3"; //$NON-NLS-1$
  public static final String ACCOUNT_ANALYTICS4 = "AccountAnalytics4"; //$NON-NLS-1$
  public static final String ACCOUNT_ANALYTICS5 = "AccountAnalytics5"; //$NON-NLS-1$
  public static final String ENTITY_NOT_EXISTS = "EntityNotExists"; //$NON-NLS-1$
  private static final String BUNDLE_NAME = "com.mg.merp.finance.resources.messages"; //$NON-NLS-1$
  private static Messages instance;

  static {
    MessageSourceAccessor.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  public static Messages getInstance() {
    return instance;
  }

}
