/*
 * PriceTypeFromPriceListSpecRestSearchHelp.java
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
package com.mg.merp.reference.support.ui;

/**
 * Поиск типов цен из списка связей с прайс-листом.<br> Специализирован для формы условий отбора
 * позиций прайс-листа
 *
 * @author Artem V. Sharapov
 * @version $Id: PriceTypeFromPriceListSpecRestSearchHelp.java,v 1.1 2008/10/13 05:50:58 sharapov
 *          Exp $
 */
public class PriceTypeFromPriceListSpecRestSearchHelp extends PriceTypeByPriceListHeadIdSearchHelp {

  /* (non-Javadoc)
   * @see com.mg.merp.reference.support.ui.PriceTypeByPriceListHeadIdSearchHelp#getPriceListHeadIdProperty()
   */
  @Override
  protected String getPriceListHeadIdAttribute() {
    return "priceListHeadId"; //$NON-NLS-1$
  }

}
