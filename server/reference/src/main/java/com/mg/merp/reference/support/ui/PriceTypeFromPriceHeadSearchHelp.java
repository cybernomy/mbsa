/*
 * PriceTypeFromPriceHeadSearchHelp.java
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
package com.mg.merp.reference.support.ui;


/**
 * Поиск типа цен из заголовка прайс-листа
 *
 * @author Oleg V. Safonov
 * @version $Id: PriceTypeFromPriceHeadSearchHelp.java,v 1.1 2007/05/10 07:31:12 safonov Exp $
 */
public class PriceTypeFromPriceHeadSearchHelp extends PriceTypeBoundedSearchHelp {

  /* (non-Javadoc)
   * @see com.mg.merp.reference.support.ui.PriceTypeBoundedSearchHelp#getPriceHeadProperty()
   */
  @Override
  protected String getPriceHeadProperty() {
    return "entity"; //$NON-NLS-1$
  }

}
