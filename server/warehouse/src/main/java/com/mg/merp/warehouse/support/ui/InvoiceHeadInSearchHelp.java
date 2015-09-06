/*
 * InvoiceHeadInSearchHelp.java
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
package com.mg.merp.warehouse.support.ui;

import com.mg.framework.generic.ui.DefaultLegacySearchHelp;

/**
 * Поисковик сущностей "Входящие накладные"
 *
 * @author Artem V. Sharapov
 * @version $Id: InvoiceHeadInSearchHelp.java,v 1.3 2007/10/22 10:39:21 sharapov Exp $
 */
public class InvoiceHeadInSearchHelp extends DefaultLegacySearchHelp {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacySearchHelp#getServiceName()
   */
  @Override
  protected String getServiceName() {
    return "merp/warehouse/InvoiceHeadIn"; //$NON-NLS-1$
  }

}
