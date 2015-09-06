/*
 * GoodsSelectionListener.java
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
package com.mg.merp.document;

/**
 * Слушатель подбора номенклатуры
 *
 * @author Oleg V. Safonov
 * @version $Id: GoodsSelectionListener.java,v 1.1 2006/12/02 12:35:32 safonov Exp $
 */
public interface GoodsSelectionListener {

  /**
   * генерация события о выборе номенклатуры
   */
  void doSelect(GoodsSelectionEvent event);
}
