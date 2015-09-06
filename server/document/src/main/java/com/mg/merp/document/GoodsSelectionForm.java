/**
 * GoodsSelectionForm.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.merp.document;

import com.mg.merp.document.model.DocHead;
import com.mg.merp.reference.model.PriceListHead;
import com.mg.merp.reference.model.PriceType;

import java.util.Date;

/**
 * Форма подбора спецификаций для документов, данный интерфейс должны реализовывать контроллеры форм
 * подбора
 *
 * @author Oleg V. Safonov
 * @version $Id: GoodsSelectionForm.java,v 1.1 2008/03/10 15:42:16 safonov Exp $
 */
public interface GoodsSelectionForm {

  /**
   * запустить форму подбора для документа
   *
   * @param listener слушатель формы
   * @param docHead  документ
   */
  void execute(GoodsSelectionListener listener, DocHead docHead);

  /**
   * запустить форму подбора
   *
   * @param listener  слушатель
   * @param priceList прайс-лист
   * @param priceType тип цен
   * @param actDate   дата актуальности
   * @param title     заголовок формы
   */
  void execute(GoodsSelectionListener listener, PriceListHead priceList, PriceType priceType, Date actDate, String title);

}
