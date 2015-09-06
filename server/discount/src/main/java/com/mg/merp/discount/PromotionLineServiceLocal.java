/*
 * PromotionLineServiceLocal.java
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
package com.mg.merp.discount;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.merp.discount.model.PromotionLine;

/**
 * Бизнес-компонент "Позиция рекламного мероприятия"
 *
 * @author Artem V. Sharapov
 * @version $Id: PromotionLineServiceLocal.java,v 1.1 2007/10/30 13:55:56 sharapov Exp $
 */
public interface PromotionLineServiceLocal extends DataBusinessObjectService<PromotionLine, Integer> {

  /**
   * имя сервиса
   */
  static final String SERVICE_NAME = "merp/discount/PromotionLine"; //$NON-NLS-1$

}
