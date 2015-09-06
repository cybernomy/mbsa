/*
 * PriceTypeInDocSearchHelp.java
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
package com.mg.merp.reference.support.ui;


/**
 * Поиск типов цен из заголовков документов
 *
 * @author leonova
 * @version $Id: PriceTypeInDocSearchHelp.java,v 1.3 2007/05/10 07:32:01 safonov Exp $
 */
public class PriceTypeInDocSearchHelp extends PriceTypeBoundedSearchHelp {

  /* (non-Javadoc)
   * @see com.mg.merp.reference.support.ui.PriceTypeBoundedSearchHelp#getPriceHeadProperty()
   */
  @Override
  protected String getPriceHeadProperty() {
    return "PriceList"; //$NON-NLS-1$
  }

}
