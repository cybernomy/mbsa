/*
 * OrderModelSearchHelp.java
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
package com.mg.merp.mfreference.support.ui;

import com.mg.merp.warehouse.OrderHeadSupServiceLocal;

/**
 * Поисковик "Образцов актов заказа поставщику"
 *
 * @author Artem V. Sharapov
 * @version $Id: OrderModelSearchHelp.java,v 1.1 2007/01/13 13:23:13 sharapov Exp $
 */
public class OrderModelSearchHelp extends ModelSearchHelp {

  /* (non-Javadoc)
   * @see com.mg.merp.mfreference.support.ui.ModelSearchHelp#getDocSectionPrimaryKey()
   */
  @Override
  protected short getDocSectionPrimaryKey() {
    return OrderHeadSupServiceLocal.DOCSECTION;
  }

}
