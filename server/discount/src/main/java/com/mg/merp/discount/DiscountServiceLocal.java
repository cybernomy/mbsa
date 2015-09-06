/*
 * DiscountServiceLocal.java
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
package com.mg.merp.discount;

import com.mg.merp.discount.model.Discount;

/**
 * Бизнес-компонент "Скидки / наценки"
 *
 * @author leonova
 * @version $Id: DiscountServiceLocal.java,v 1.3 2007/09/07 12:01:22 safonov Exp $
 */
public interface DiscountServiceLocal extends com.mg.framework.api.DataBusinessObjectService<Discount, Integer> {

  /**
   * имя сервиса
   */
  final static String SERVICE_NAME = "merp/discount/Discount";

  /**
   * тип папки для скидок / наценок
   */
  final static short FOLDER_PART = 13101;

}
