/*
 * AmortizationServiceLocal.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.account;

import com.mg.merp.account.model.Amortization;
import com.mg.merp.core.model.Folder;

/**
 * Бизнес-компонент "Ведомость начисления амортизации"
 *
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: AmortizationServiceLocal.java,v 1.2 2008/04/28 10:09:51 alikaev Exp $
 */
public interface AmortizationServiceLocal extends com.mg.framework.api.DataBusinessObjectService<Amortization, Integer> {

  /**
   * Создание хоз. операций по партии амортизаций
   *
   * @param batch  - служебное поле у амортизации(номер партии)
   * @param folder - папка для создания хоз. операции
   */
  void commitAmortization(Integer batch, Folder folder);

  /**
   * Откат создания хоз. операций по партиям амортизаций
   *
   * @param batch - служебное поле у амортизации(номер партии)
   */
  void rollbackAmortization(Integer batch);

}
