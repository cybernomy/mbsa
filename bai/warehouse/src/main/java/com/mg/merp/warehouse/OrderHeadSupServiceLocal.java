/*
 * OrderHeadSupServiceLocal.java
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

import com.mg.merp.warehouse.model.OrderHead;

/**
 * Бизнес-компонент "Зазаз поставщикам"
 *
 * @author leonova
 * @version $Id: OrderHeadSupServiceLocal.java,v 1.4 2007/07/27 11:59:29 safonov Exp $
 */
public interface OrderHeadSupServiceLocal
    extends com.mg.merp.document.GoodsDocument<OrderHead, Integer, OrderHeadModelSupServiceLocal, OrderSpecSupServiceLocal> {
  /**
   * Имя сервиса
   */
  static final String SERVICE_NAME = "merp/warehouse/OrderHeadSup";

  /**
   * тип папки для заказов поставщикам
   */
  final static short FOLDER_PART = 41;

  /**
   * docsection для заказов поставщикам
   */
  final static short DOCSECTION = 21;

  @Deprecated
  public int createDocument(int[] orderList, int docsumentSection, int modelId, int folderId) throws com.mg.framework.api.ApplicationException;

}
