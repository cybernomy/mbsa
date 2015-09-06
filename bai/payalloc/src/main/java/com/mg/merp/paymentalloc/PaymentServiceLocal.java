/*
 * PaymentServiceLocal.java
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
package com.mg.merp.paymentalloc;

import com.mg.merp.core.model.Folder;
import com.mg.merp.paymentalloc.model.Payment;

/**
 * Сервис бизнес-компонента "Журнал платежей"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PaymentServiceLocal.java,v 1.6 2007/06/04 05:13:36 sharapov Exp $
 */
public interface PaymentServiceLocal extends com.mg.framework.api.DataBusinessObjectService<Payment, Integer> {

  /**
   * Имя сервиса
   */
  final static String LOCAL_SERVICE_NAME = "merp/paymentalloc/Payment"; //$NON-NLS-1$

  /**
   * Тип папки для журнала платажей
   */
  final static short FOLDER_PART = 13300;

  /**
   * Создать запись журнала платежей по образцу
   *
   * @param pattern - образец записи
   * @param folder  - папка-приемник
   * @return запись журнала платежей
   */
  Payment createByPattern(Payment pattern, Folder folder);

  /**
   * Рассчитать курс и сумму в НДЕ
   *
   * @param payment - запись журнала платежей
   */
  void computeCurRateAndSumNat(Payment payment);

}
