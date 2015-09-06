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
package com.mg.merp.document.support;

import com.mg.framework.generic.MessageSourceAccessor;

/**
 * @author Oleg V. Safonov
 * @version $Id: Messages.java,v 1.4 2008/12/29 10:00:53 safonov Exp $
 */
public class Messages extends MessageSourceAccessor {
  //message keys
  public static final String DOCUMENT_TITLE = "DocumentTitle"; //$NON-NLS-1$
  public static final String GOODS_SELECTION_TITLE = "GoodsSelectionTitle"; //$NON-NLS-1$
  public static final String GOODS_CODE = "GoodsCode"; //$NON-NLS-1$
  public static final String GOODS_NAME = "GoodsName"; //$NON-NLS-1$
  public static final String GOODS_QUANTITY1 = "GoodsQuantity1"; //$NON-NLS-1$
  public static final String GOODS_QUANTITY2 = "GoodsQuantity2"; //$NON-NLS-1$
  public static final String GOODS_PRICE = "GoodsPrice"; //$NON-NLS-1$
  public static final String ERASE_LBSCHEDULE_QUESTION = "EraseLBScheduleQuestion"; //$NON-NLS-1$
  public static final String DOCUMENT_NOT_CREATED = "DocumentNotCreated"; //$NON-NLS-1$
  private static final String BUNDLE_NAME = "com.mg.merp.document.resources.messages"; //$NON-NLS-1$
  private static Messages instance;

  static {
    MessageSourceAccessor.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  public static Messages getInstance() {
    return instance;
  }

}
