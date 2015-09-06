/*
 * LiabilityModelSearchHelp.java
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
package com.mg.merp.paymentcontrol.support.ui;

import com.mg.framework.generic.ui.DefaultLegacySearchHelp;

/**
 * Поисковик бизнес-компонента "Образцы реестра обязательств"
 *
 * @author Artem V. Sharapov
 * @version $Id: LiabilityModelSearchHelp.java,v 1.1 2007/02/16 14:55:07 sharapov Exp $
 */
public class LiabilityModelSearchHelp extends DefaultLegacySearchHelp {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacySearchHelp#getServiceName()
   */
  @Override
  protected String getServiceName() {
    return "merp/paymentcontrol/LiabilityModel"; //$NON-NLS-1$
  }

}
