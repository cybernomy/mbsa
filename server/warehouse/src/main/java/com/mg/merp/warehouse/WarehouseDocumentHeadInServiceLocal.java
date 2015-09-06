/*
 * WarehouseDocumentHeadInServiceLocal.java
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
package com.mg.merp.warehouse;

import com.mg.merp.warehouse.model.StockDocumentHead;

/**
 * @author leonova
 * @version $Id: WarehouseDocumentHeadInServiceLocal.java,v 1.3 2006/09/20 11:02:09 safonov Exp $
 */
public interface WarehouseDocumentHeadInServiceLocal
    extends com.mg.merp.document.GoodsDocument<StockDocumentHead, Integer, WarehouseDocumentHeadModelInServiceLocal, WarehouseDocumentSpecInServiceLocal> {

  /**
   * тип папки для приходных ордеров
   */
  final static short FOLDER_PART = 30;

  /**
   * docsection для приходных ордеров
   */
  final static short DOCSECTION = 16;
}
