/*
 * PromotionServiceLocal.java
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
import com.mg.merp.discount.model.Promotion;
import com.mg.merp.discount.model.PromotionLine;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.CatalogFolder;

import java.util.Date;
import java.util.List;

/**
 * Бизнес-компонент "Рекламное мероприятие"
 *
 * @author Artem V. Sharapov
 * @version $Id: PromotionServiceLocal.java,v 1.1 2007/10/30 13:55:56 sharapov Exp $
 */
public interface PromotionServiceLocal extends DataBusinessObjectService<Promotion, Integer> {

  /**
   * имя сервиса
   */
  static final String SERVICE_NAME = "merp/discount/Promotion"; //$NON-NLS-1$

  /**
   * Получить список действующих рекламных мероприятий по приоритету на дату, для позиции каталога
   * или для папки каталога
   *
   * @param actualDate    - на дату
   * @param catalog       - позиция каталога
   * @param catalogFolder - папка каталога
   * @return список действующих рекламных мероприятий по приоритету на дату, по позиции каталога или
   * папке каталога
   */
  List<PromotionLine> getPromotions(Date actualDate, Catalog catalog, CatalogFolder catalogFolder);

}
