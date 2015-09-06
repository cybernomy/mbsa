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
package com.mg.merp.settlement.support;

import com.mg.framework.generic.MessageSourceAccessor;


/**
 * Класс сообщений
 *
 * @author Artem V. Sharapov
 * @author Konstantin S. Alikaev
 * @version $Id: Messages.java,v 1.3 2008/04/29 07:56:36 alikaev Exp $
 */
public class Messages extends MessageSourceAccessor {
  //message keys
  public static final String ROLLBACK_FAIL = "RollBackFail"; //$NON-NLS-1$
  public static final String CREATE_TREE_FAIL = "CreateTreeFail"; //$NON-NLS-1$
  public static final String CONTRACTOR_CARD_FORMBR_TITLE = "ContractorCardFormBrTitle"; //$NON-NLS-1$
  private static final String BUNDLE_NAME = "com.mg.merp.settlement.resources.messages"; //$NON-NLS-1$
  private static Messages instance;

  static {
    MessageSourceAccessor.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  public static Messages getInstance() {
    return instance;
  }

}
