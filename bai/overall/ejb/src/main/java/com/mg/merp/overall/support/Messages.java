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
package com.mg.merp.overall.support;

import com.mg.framework.generic.MessageSourceAccessor;

/**
 * @author Konstantin S. Alikaev
 * @version $Id: Messages.java,v 1.1 2008/06/30 04:19:36 alikaev Exp $
 */
public class Messages extends MessageSourceAccessor {

  //message keys
  public static final String ATTRIBUTE_NOT_UNIQUE = "AttributeNotUnique"; //$NON-NLS-1$
  private static final String BUNDLE_NAME = "com.mg.merp.overall.resources.messages"; //$NON-NLS-1$
  private static Messages instance;

  static {
    MessageSourceAccessor.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  public static Messages getInstance() {
    return instance;
  }

}
