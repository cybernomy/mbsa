/*
 * ItemKindSearchHelp.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.lbschedule.support.ui;

import com.mg.framework.generic.ui.DefaultLegacySearchHelp;

/**
 * @author leonova
 * @version $Id: ItemKindSearchHelp.java,v 1.2 2006/07/10 11:47:38 leonova Exp $
 */
public class ItemKindSearchHelp extends DefaultLegacySearchHelp {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacySearchHelp#getServiceName()
   */
  @Override
  protected String getServiceName() {
    return "merp/lbschedule/ItemKind";
  }

}
