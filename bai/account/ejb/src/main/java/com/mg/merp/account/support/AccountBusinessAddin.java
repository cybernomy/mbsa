/*
 * AccountBusinessAddin.java
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
package com.mg.merp.account.support;

import com.mg.merp.account.model.EconomicOper;
import com.mg.merp.account.model.EconomicSpec;
import com.mg.merp.baiengine.generic.AbstractBusinessAddin;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;
import com.mg.merp.docflow.DocumentSpecItem;
import com.mg.merp.docprocess.model.DocProcessStage;
import com.mg.merp.document.model.DocHead;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Базовый класс BAi формирования проводок хоз. операций. Класс должен реализовывать следующий метод
 * <code>protected void doPerform() throws Exception</code>. Метод возвращает результат (это может
 * быть сумма в валюте отрабатываемого документа или количество).
 *
 * <p>Пример данного метода:
 * <pre>
 * protected void doPerform() throws Exception {
 *     complete(getDocumentSpecItem().getPerformedSum()); //сумма спецификации отмеченная к
 * отработке
 * }</pre>
 *
 * @author Konstantin S. Alikaev
 * @version $Id: AccountBusinessAddin.java,v 1.1 2008/03/13 06:20:53 alikaev Exp $
 */
public abstract class AccountBusinessAddin extends AbstractBusinessAddin<BigDecimal> {

  /**
   * имя параметра этап ДО
   */
  public static final String DOCFLOW_PARAM_NAME = "DOCFLOW_PARAM_NAME";
  /**
   * имя параметра спецификация документа отмеченная к отработке
   */
  public static final String DOCSPEC_PARAM_NAME = "DOCSPEC_PARAM_NAME";
  /**
   * имя параметра хозяйственной операции
   */
  public static final String ECONOMIC_OPER_PARAM_NAME = "ECONOMIC_OPER_PARAM_NAME";
  /**
   * имя параметра хозяйственная проводка/признак
   */
  public static final String ECONOMIC_SPEC_PARAM_NAME = "ECONOMIC_SPEC_PARAM_NAME";

  private DocFlowPluginInvokeParams mDocFlowParams;
  private DocumentSpecItem mDocumentSpecItem;
  private EconomicOper mEconomicOper;
  private EconomicSpec mEconomicSpec;

  /* (non-Javadoc)
   * @see com.mg.merp.baiengine.generic.AbstractBusinessAddin#extractParams(java.util.Map)
   */
  @Override
  protected void extractParams(Map<String, ? extends Object> params) {
    mDocFlowParams = (DocFlowPluginInvokeParams) params.get(DOCFLOW_PARAM_NAME);
    mDocumentSpecItem = (DocumentSpecItem) params.get(DOCSPEC_PARAM_NAME);
    mEconomicOper = (EconomicOper) params.get(ECONOMIC_OPER_PARAM_NAME);
    mEconomicSpec = (EconomicSpec) params.get(ECONOMIC_SPEC_PARAM_NAME);
  }

  /**
   * получить отрабатываемый документ
   *
   * @return текущий документ
   */
  protected DocHead getDocument() {
    return mDocFlowParams.getDocument();
  }

  /**
   * получить отрабатываемую сумму документа, используется для документов без спецификаций
   *
   * @return отрабатываемая сумма документа
   */
  protected BigDecimal getPerformedSum() {
    return mDocFlowParams.getPerformedSum();
  }

  /**
   * получить текущий этап ДО
   *
   * @return текущий этап ДО
   */
  protected DocProcessStage getDocProcessStage() {
    return mDocFlowParams.getPerformedStage();
  }

  /**
   * получить текущую спецификацию документа
   *
   * @return текущая спецификация документа или <code>null</code> если документ без спецификаций
   */
  protected DocumentSpecItem getDocumentSpecItem() {
    return mDocumentSpecItem;
  }

  /**
   * Получить хозяйственную операцию
   *
   * @return создаваемая хозяйственная операция
   */
  public EconomicOper getEconomicOper() {
    return mEconomicOper;
  }

  /**
   * Получить хозяйственную спецификацию
   *
   * @return создаваемая хозяйственная спецификация
   */
  public EconomicSpec getEconomicSpec() {
    return mEconomicSpec;
  }

}
