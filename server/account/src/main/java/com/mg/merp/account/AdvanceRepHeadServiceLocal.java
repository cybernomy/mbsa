/*
 * AdvanceRepHeadServiceLocal.java
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

import com.mg.merp.account.model.AccPlan;
import com.mg.merp.account.model.AdvanceRepHead;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.Currency;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Реализация бизнес-компонента "Авансовые отчеты"
 *
 * @author Konstantin S. Alikaev
 * @version $Id: AdvanceRepHeadServiceLocal.java,v 1.4 2008/03/12 14:35:37 alikaev Exp $
 */
public interface AdvanceRepHeadServiceLocal extends com.mg.merp.document.GoodsDocument<AdvanceRepHead, Integer, AdvanceRepHeadModelServiceLocal, AdvanceRepSpecServiceLocal> {

  /**
   * тип папки для авансовых отчетов
   */
  final static short FOLDER_PART = 6001;

  /**
   * docsection для авансовых отчетов
   */
  final static short DOCSECTION = 6001;

  /**
   * Локальное имя сервиса
   */
  static final String LOCAL_SERVICE_NAME = "merp/account/AdvanceRepHead";

  /**
   * Расчитать сумму остатка предыдущего аванса
   *
   * @param accPlan    - счет АО
   * @param contractor - сотрудник АО
   * @param currency   - валюта документа
   * @return - сумму предыдущих авансов
   */
  public BigDecimal getPrevAdvanceSum(AccPlan accPlan, Contractor contractor, Currency currency) throws com.mg.framework.api.ApplicationException;

  /**
   * Расчитать последний дебитовый оборот
   *
   * @param accPlan    - счет АО
   * @param contractor - сотрудник АО
   * @param currency   - валюта АО
   * @param curDate    - дата документа АО
   * @return - сумму последнего дебитового оброта и его дату
   */
  public AdvanceRepHeadResult getReceivedSum(AccPlan accPlan, Contractor contractor, Currency currency, Date curDate) throws com.mg.framework.api.ApplicationException;

}
