/*
 * BillHeadOutServiceLocal.java
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
package com.mg.merp.warehouse;

import com.mg.merp.warehouse.model.BillHead;

/**
 * Бизнес-компонент "Исходящие счета"
 *
 * @author leonova
 * @version $Id: BillHeadOutServiceLocal.java,v 1.4 2007/09/07 12:23:15 safonov Exp $
 */
public interface BillHeadOutServiceLocal
    extends com.mg.merp.document.GoodsDocument<BillHead, Integer, BillHeadModelOutServiceLocal, BillSpecOutServiceLocal>, DiscountDocument {

  /**
   * тип папки для исходящих счетов
   */
  final static short FOLDER_PART = 50;

  /**
   * docsection для исходящих счетов
   */
  final static short DOCSECTION = 26;
}
