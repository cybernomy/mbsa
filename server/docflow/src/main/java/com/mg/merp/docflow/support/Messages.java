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
package com.mg.merp.docflow.support;

import com.mg.framework.generic.MessageSourceAccessor;

/**
 * @author Oleg V. Safonov
 * @author Konstantin S. Alikaev
 * @version $Id: Messages.java,v 1.3 2008/09/01 07:43:20 safonov Exp $
 */
public class Messages extends MessageSourceAccessor {
  //message keys
  public static final String DOCFLOW_MAP_TITLE = "DocFlowMapTitle";
  public static final String DOCUMENT_TITLE = "DocumentTitle";
  public static final String DOCFLOW_HISTORY_TITLE = "DocFlowHistoryTitle";
  public static final String CHOOSE_NEXT_STAGE_TITLE = "ChooseNextStageTitle";
  public static final String PLUGIN_NOT_IMPLEMENTED_MESSAGE = "PluginNotImplementedMessage";
  public static final String ALREADY_COMPLETED_MESSAGE = "AlreadyCompletedMessage";
  public static final String DOCUMENT_NOT_FOUND_MESSAGE = "DocumentNotFoundMessage";
  public static final String DOCUMENT_OWNER_MISMATCH_MESSAGE = "DocumentOwnerMismatchMessage";
  public static final String INACCESIBLE_STATE_MESSAGE = "InaccesibleStateMessage";
  public static final String ROLLBACK_NOT_ALLOWED_MESSAGE = "RollbackNotAllowedMessage";
  public static final String SILENT_DOCFLOW_MESSAGE = "SilentDocFlowMessage";
  public static final String DOCSPEC_CODE = "DocSpecCode";
  public static final String DOCSPEC_NAME = "DocSpecName";
  public static final String DOCSPEC_PRICE = "DocSpecPrice";
  public static final String DOCSPEC_SUMMA = "DocSpecSumma";
  public static final String DOCSPEC_QUANTITY = "DocSpecQuantity";
  public static final String DOCSPEC_QUANTITY1 = "DocSpecQuantity1";
  public static final String PERFORMEDSUM = "PerformedSum";
  public static final String PERFORMED_QUANTITY = "PerformedQuantity";
  public static final String PERFORMED_QUANTITY1 = "PerformedQuantity1";
  private static final String BUNDLE_NAME = "com.mg.merp.docflow.resources.messages"; //$NON-NLS-1$
  private static Messages instance;

  static {
    MessageSourceAccessor.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  public static Messages getInstance() {
    return instance;
  }

}
