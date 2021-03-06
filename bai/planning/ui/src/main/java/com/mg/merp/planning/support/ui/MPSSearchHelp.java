/*
 * MPSSearchHelp.java
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
package com.mg.merp.planning.support.ui;

import com.mg.framework.generic.ui.DefaultLegacySearchHelp;

/**
 * @author leonova
 * @version $Id: MPSSearchHelp.java,v 1.2 2006/07/12 09:35:09 leonova Exp $
 */
public class MPSSearchHelp extends DefaultLegacySearchHelp {

  @Override
  protected String getServiceName() {
    return "merp/planning/MPS";
  }


}
