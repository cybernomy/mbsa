/*
 * Messages.java
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
package com.mg.merp.paymentcontrol.support;

import com.mg.framework.generic.MessageSourceAccessor;

/**
 * Класс пользовательских сообщений
 *
 * @author Artem V. Sharapov
 * @version $Id: Messages.java,v 1.5 2007/08/21 06:36:52 sharapov Exp $
 */
public class Messages extends MessageSourceAccessor {
  //message keys
  public static final String PMCPERIOD_YEAR = "PmcPeriodYear"; //$NON-NLS-1$
  public static final String PMCPERIOD_HALFYEAR = "PmcPeriodHalfYear"; //$NON-NLS-1$
  public static final String PMCPERIOD_QUARTER = "PmcPeriodQuarter"; //$NON-NLS-1$
  public static final String PMCPERIOD_MONTH = "PmcPeriodMonth"; //$NON-NLS-1$
  public static final String PMCPERIOD_DEKADA = "PmcPeriodDekada"; //$NON-NLS-1$
  public static final String PMCPERIOD_WEEK = "PmcPeriodWeek"; //$NON-NLS-1$
  public static final String PMCPERIOD_DAY = "PmcPeriodDay"; //$NON-NLS-1$
  public static final String PMCPERIOD_DATE_INVALID = "PmcPeriodDateInvalid"; //$NON-NLS-1$
  public static final String PMCPERIOD_UPLEVELQUANTITY_INVALID = "PmcPeriodUpLevelQuantityInvalid"; //$NON-NLS-1$
  public static final String PMCPERIOD_EXCLUSION_INVALID = "PmcPeriodExclutionInvalid"; //$NON-NLS-1$
  public static final String PMCPERIOD_CANT_DELETE_ROOT = "PmcPeriodCantDeleteRoot"; //$NON-NLS-1$
  public static final String PMCPERIOD_CANT_DELETE_NESTED = "PmcPeriodCantDeleteNested"; //$NON-NLS-1$
  public static final String PMCPERIOD_DBMS_CROSS_PERIOD = "PmcPeriodDbmsCrossPeriod"; //$NON-NLS-1$
  public static final String LIABILITY_DST_FOLDER_INVALID = "LiabilityDstFolderInvalid"; //$NON-NLS-1$
  public static final String IS_NOT_SAME_CURRENCY = "IsNotSameCurrency"; //$NON-NLS-1$
  public static final String REPORT_HEADER = "ReportHeader"; //$NON-NLS-1$
  public static final String REPORT_RESOURCE_INVALID = "ReportResourceInvalid"; //$NON-NLS-1$
  public static final String REPORT_PATTERN_INVALID = "ReportPatternInvalid"; //$NON-NLS-1$
  public static final String REPORT_DOCUMENT_CREATED = "ReportDocumentCreated"; //$NON-NLS-1$
  public static final String VERSION_STAUS_IN_WORK = "VersionStatusInWork"; //$NON-NLS-1$
  public static final String VERSION_STAUS_READY = "VersionStatusReady"; //$NON-NLS-1$
  public static final String VERSION_STAUS_EXECUTE = "VersionStatusExecute"; //$NON-NLS-1$
  public static final String EXECUTION_SUM_INVALID = "ExecutionSumInvalid"; //$NON-NLS-1$
  public static final String CAN_NOT_BE_EXECUTED = "CantBeExecuted"; //$NON-NLS-1$
  public static final String CANT_BE_CHANGED_OR_DELETED = "CantBeChangedOrDeleted"; //$NON-NLS-1$
  public static final String ERASE_DOC_QUESTION = "EraseDocQuestion"; //$NON-NLS-1$
  public static final String COLUMN_NAME_ID = "ColumnNameId"; //$NON-NLS-1$
  public static final String COLUMN_NAME_PERIOD = "ColumnNamePeriod"; //$NON-NLS-1$
  public static final String COLUMN_NAME_DATE_FROM = "ColumnNameDateFrom"; //$NON-NLS-1$
  public static final String COLUMN_NAME_DATE_TILL = "ColumnNameDateTill"; //$NON-NLS-1$
  public static final String COLUMN_NAME_CURRENCY = "ColumnNameCurrency"; //$NON-NLS-1$
  public static final String COLUMN_NAME_RESOURCE = "ColumnNameResource"; //$NON-NLS-1$
  public static final String COLUMN_NAME_BEGIN_SALDO = "ColumnNameBeginSaldo"; //$NON-NLS-1$
  public static final String COLUMN_NAME_INCOME = "ColumnNameIncome"; //$NON-NLS-1$
  public static final String COLUMN_NAME_EXPENSE = "ColumnNameExpense"; //$NON-NLS-1$
  public static final String COLUMN_NAME_END_SALDO = "ColumnNameEndSaldo"; //$NON-NLS-1$
  public static final String EXECUTION_NOT_FOUND = "ExecutionNotFound"; //$NON-NLS-1$
  public static final String NOT_BANK_DOCUMENT_CREATION_ERROR = "NotBankDocumentCreationError"; //$NON-NLS-1$
  private static final String BUNDLE_NAME = "com.mg.merp.paymentcontrol.resources.messages"; //$NON-NLS-1$
  private static Messages instance;

  static {
    MessageSourceAccessor.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  public static Messages getInstance() {
    return instance;
  }
}
