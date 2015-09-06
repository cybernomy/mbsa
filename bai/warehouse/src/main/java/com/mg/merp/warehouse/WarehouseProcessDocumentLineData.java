/**
 * WarehouseProcessDocumentLineData.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.merp.warehouse;

import com.mg.merp.docflow.DocumentSpecItem;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Employees;
import com.mg.merp.reference.model.Measure;
import com.mg.merp.warehouse.model.Warehouse;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Данные по спецификации для отработки по складам
 *
 * @author Oleg V. Safonov
 * @version $Id: WarehouseProcessDocumentLineData.java,v 1.1 2008/04/18 15:15:53 safonov Exp $
 */
public interface WarehouseProcessDocumentLineData {

  /**
   * получить данные ДО
   */
  DocumentSpecItem getDocumentSpecItem();

  /**
   * получить спецификацию
   */
  DocSpec getDocumentSpec();

  /**
   * получить МОЛ источника
   */
  Employees getSrcMol();

  /**
   * получить склад источник
   */
  Warehouse getSrcStock();

  /**
   * получить МОЛ приемника
   */
  Employees getDstMol();

  /**
   * получить склад приемник
   */
  Warehouse getDstStock();

  /**
   * получить позицию каталога
   */
  Catalog getCatalog();

  /**
   * получить 1ю ЕИ
   */
  Measure getMeasure1();

  /**
   * получить 2ю ЕИ
   */
  Measure getMeasure2();

  /**
   * получить 1е количество к отработке
   */
  BigDecimal getQuantity1();

  /**
   * получить 2е количество к отработке
   */
  BigDecimal getQuantity2();

  /**
   * получить цену без не включенных в цену налогов
   */
  BigDecimal getPrice();

  /**
   * получить полную ценю
   */
  BigDecimal getPrice1();

  /**
   * получить сумму без не включенных в цену налогов
   */
  BigDecimal getSum();

  /**
   * получить полную сумму
   */
  BigDecimal getSum1();

  /**
   * получить дату транзакции
   */
  Date getProcessDate();

}
