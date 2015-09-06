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
package com.mg.merp.reference.support;

import com.mg.framework.generic.MessageSourceAccessor;

/**
 * @author Oleg V. Safonov
 * @author Konstantin S. Alikaev
 * @version $Id: Messages.java,v 1.16 2009/02/11 14:37:47 sharapov Exp $
 */
public class Messages extends MessageSourceAccessor {
  //message keys
  public static final String TAXLINK_INPUT_ORDER = "TaxLinkInputOrder"; //$NON-NLS-1$
  public static final String TAXLINK_ORDER = "TaxLinkOrder"; //$NON-NLS-1$
  public static final String TAX_LINK_INPUT_INCLUDED = "TaxLinkInputIncluded"; //$NON-NLS-1$
  public static final String TAX_LINK_INCLUDED = "TaxLinkIncluded"; //$NON-NLS-1$
  public static final String EXISTS_PRICE = "ExistsPrice"; //$NON-NLS-1$
  public static final String EXISTS_PARTNER = "ExistsPartner"; //$NON-NLS-1$
  public static final String EXISTS_CONTRACTOR = "ExistsContractor";
  public static final String CHECK_SUBJECT = "CheckSubject";
  public static final String CONTRACTOR_PARTNER = "ContractorPartner";
  public static final String CONTRACTOR_ORGUNIT = "ContractorOrgUnit";
  public static final String CONTRACTOR_EMPLOYEE = "ContractorEmployee";
  public static final String MEASURE_CONVERSION_MAINTENANCE_NOT_FOUND = "MeasureConversionMaintenanceNotFound";
  public static final String DBMS_CATALOG_TO_FOLDER = "DBMSCatalogToFolder";
  public static final String DBMS_CATALOGPRICE_INACTION_CUR = "DBMSCatalogPriceInActionCur";
  public static final String CURRENCY_RATE_NOT_FOUND = "CurrencyRateNotFound";
  public static final String ERASE_ATTACHMENT_QUESTION = "EraseAttachmentQuestion";
  public static final String CANNOT_DELETE_NATURAL_PERSON_HIST = "CannotDeleteNaturalPersonHist";
  public static final String BANKACCOUNT_ISDEFAULT_UNIQUE = "BankAccountIsDefaultUnique";
  public static final String EMPLOYESS_ISDEFAULT_UNIQUE = "EmployeesIsDefaultUnique";
  public static final String EMPLOYESS_CODE = "EmployeesCode";
  public static final String OVERESTIMATION_PARAM_PRICE_LIST_HEAD_INVALID = "OverestimationParamPriceListInvalid";
  public static final String OVERESTIMATION_PARAM_ACTUAL_DATE_INVALID = "OverestimationParamActualDateInvalid";
  public static final String OVERESTIMATION_PARAM_PERCENT_INVALID = "OverestimationParamPercentInvalid";
  public static final String OVERESTIMATION_PARAM_PRECISION_INVALID = "OverestimationParamPrecisionInvalid";
  private static final String BUNDLE_NAME = "com.mg.merp.reference.resources.messages"; //$NON-NLS-1$
  private static Messages instance;

  static {
    MessageSourceAccessor.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  public static Messages getInstance() {
    return instance;
  }
}
