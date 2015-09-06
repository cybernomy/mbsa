/*
 * OfferKindSerachHelp.java
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
package com.mg.merp.crm.support.ui;

import com.mg.framework.generic.ui.DefaultLegacySearchHelp;

/**
 * @author leonova
 * @version $Id: OfferKindSearchHelp.java,v 1.1 2006/07/11 07:45:37 leonova Exp $
 */
public class OfferKindSearchHelp extends DefaultLegacySearchHelp {

  @Override
  protected String getServiceName() {
    return "merp/crm/OfferKind";
  }

}
