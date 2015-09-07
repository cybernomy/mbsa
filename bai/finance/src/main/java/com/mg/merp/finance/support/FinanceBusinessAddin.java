/**
 * FinanceBusinessAddin.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.merp.finance.support;

import com.mg.merp.baiengine.generic.AbstractBusinessAddin;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;
import com.mg.merp.docflow.DocumentSpecItem;
import com.mg.merp.docprocess.model.DocProcessStage;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.finance.model.FinOperation;
import com.mg.merp.finance.model.Specification;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Базовый класс BAi формирования финансовых проводок. Класс должен реализовывать следующий метод
 * <code>protected void doPerform() throws Exception</code>. Метод возвращает сумму проводки в
 * валюте отрабатываемого документа.
 *
 * <p>Пример данного метода:
 * <pre>
 * protected void doPerform() throws Exception {
 *     complete(getDocumentSpecItem().getPerformedSum()); //сумма спецификации отмеченная к
 * отработке
 * }</pre>
 *
 * @author Oleg V. Safonov
 * @version $Id: FinanceBusinessAddin.java,v 1.1 2007/12/03 15:04:59 safonov Exp $
 */
public abstract class FinanceBusinessAddin extends AbstractBusinessAddin<BigDecimal> {
  /**
   * имя параметра этап ДО
   */
  public static final String DOCFLOW_PARAM_NAME = "DOCFLOW_PARAM_NAME";
  /**
   * имя параметра спецификация документа отмеченная к отработке
   */
  public static final String DOCSPEC_PARAM_NAME = "DOCSPEC_PARAM_NAME";
  /**
   * имя параметра финансовая операция
   */
  public static final String FINOPER_PARAM_NAME = "FINOPER_PARAM_NAME";
  /**
   * имя параметра финансовая проводка/признак
   */
  public static final String FINSPEC_PARAM_NAME = "FINSPEC_PARAM_NAME";

  private DocFlowPluginInvokeParams mDocFlowParams;
  private DocumentSpecItem mDocumentSpecItem;
  private FinOperation mFinanceOperation;
  private Specification mFinanceSpecification;

  /* (non-Javadoc)
   * @see com.mg.merp.baiengine.generic.AbstractBusinessAddin#extractParams(java.util.Map)
   */
  @Override
  protected void extractParams(Map<String, ? extends Object> params) {
    mDocFlowParams = (DocFlowPluginInvokeParams) params.get(DOCFLOW_PARAM_NAME);
    mDocumentSpecItem = (DocumentSpecItem) params.get(DOCSPEC_PARAM_NAME);
    mFinanceOperation = (FinOperation) params.get(FINOPER_PARAM_NAME);
    mFinanceSpecification = (Specification) params.get(FINSPEC_PARAM_NAME);
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
   * получить финансовую операцию
   *
   * @return создаваемая финансовая операция
   */
  protected FinOperation getFinanceOperation() {
    return mFinanceOperation;
  }

  /**
   * получить финансовую спецификацию/признак
   *
   * @return создаваемая финансовая спецификация/признак
   */
  protected Specification getFinanceSpecification() {
    return mFinanceSpecification;
  }

}
