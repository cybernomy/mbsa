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
package com.mg.merp.discount.support;

import com.mg.framework.generic.MessageSourceAccessor;

/**
 * @author Konstantin S. Alikaev
 * @version $Id: Messages.java,v 1.1 2008/05/22 09:44:35 alikaev Exp $
 */
public class Messages extends MessageSourceAccessor {

  public static final String SEARCHED_FILD_CATALOG_OR_CATALOGFOLDER = "SearchedFildCatalogOrCatalogFolder";
  private static final String BUNDLE_NAME = "com.mg.merp.discount.resources.messages"; //$NON-NLS-1$
  private static Messages instance;

  static {
    MessageSourceAccessor.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  public static Messages getInstance() {
    return instance;
  }

}
