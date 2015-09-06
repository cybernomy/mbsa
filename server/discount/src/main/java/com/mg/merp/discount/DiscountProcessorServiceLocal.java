/**
 * DiscountProcessorServiceLocal.java.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.merp.discount;

import com.mg.framework.api.BusinessObjectService;
import com.mg.merp.core.model.Folder;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocSpec;

import java.util.List;

/**
 * Бизнес-компонент "Процессор расчета скидок/наценок"
 *
 * @author Oleg V. Safonov
 * @version $Id: DiscountProcessorServiceLocal.java,v 1.3 2009/01/22 06:47:35 sharapov Exp $
 */
public interface DiscountProcessorServiceLocal extends BusinessObjectService {
  /**
   * имя сервиса
   */
  final static String SERVICE_NAME = "merp/discount/DiscountProcessor";

  /**
   * расчет скидки/наценки для спецификации
   *
   * @param discountGroup группа скидок/наценок
   * @param docSpec       спецификация документа
   * @param listener      слушатель
   */
  void calculateDiscountValue(Folder discountGroup, DocSpec docSpec, CalculateDiscountListener listener);

  /**
   * применить скидки/наценки на документ
   *
   * @param docHead документ
   */
  void applyDiscount(DocHead docHead);

  /**
   * Применить скидки/наценки на документ для позиций спецификации
   *
   * @param docHead - документ
   * @param specs   - список позиций спецификации
   */
  void applyDiscount(DocHead docHead, List<DocSpec> specs);

  /**
   * Применить скидки/наценки на документ
   *
   * @param docHead               - документ
   * @param applyDiscountListener - cлушатель применения скидки/наценки
   */
  void applyDiscount(DocHead docHead, ApplyDiscountListener applyDiscountListener);

  /**
   * Применить скидки/наценки на документ для позиций спецификации
   *
   * @param docHead               - документ
   * @param specs                 - список позиций спецификации
   * @param applyDiscountListener - cлушатель применения скидки/наценки
   */
  void applyDiscount(DocHead docHead, List<DocSpec> specs, ApplyDiscountListener applyDiscountListener);

}
