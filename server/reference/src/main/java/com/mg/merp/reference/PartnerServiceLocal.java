/*
 * PartnerServiceLocal.java
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
package com.mg.merp.reference;

import com.mg.merp.reference.model.BankAccount;
import com.mg.merp.reference.model.Partner;

/**
 * Бизнес-компонент "Партнеры"
 *
 * @author leonova
 * @version $Id: PartnerServiceLocal.java,v 1.5 2007/11/08 14:51:10 sharapov Exp $
 */
public interface PartnerServiceLocal extends com.mg.merp.reference.Contractor<Partner> {
  /**
   * тип папки для партнеров
   */
  final static short FOLDER_PART = 1;
  /**
   * имя сервиса
   */
  final static String SERVICE_NAME = "merp/reference/Partner";

  /**
   * формирует полный почтовый адрес
   *
   * @param partner партнер
   */
  void getFullAddress(Partner partner);

  /**
   * формирует полный юридический адрес
   *
   * @param partner партнер
   */
  void getFullAddressLegal(Partner partner);

  /**
   * Получить банковский счет по умолчанию
   *
   * @param partner - партнер
   * @return банковский счет по умолчанию, или <code>null</code> если не найден
   */
  BankAccount getDefaultBankAccount(Partner partner);

}
