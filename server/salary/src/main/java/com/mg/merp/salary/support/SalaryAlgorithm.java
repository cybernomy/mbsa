/*
 * SalaryAlgorithm.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.salary.support;

import com.mg.framework.api.ApplicationException;
import com.mg.merp.baiengine.generic.AbstractBusinessAddin;

/**
 * Базовый класс расчета з/п
 *
 * @author Oleg V. Safonov
 * @version $Id$
 */
public abstract class SalaryAlgorithm<T> extends AbstractBusinessAddin<T> {
  private SalaryHelper salaryHelper = null;
  private TableHelper tableHelper = null;
  //	private FeeRefAlgorithmResult calcResult = null;
  private SalaryAlgorithmContext calcContext = null;

  /**
   * Возвращает класс помошник для вычисления з/п
   *
   * @return класс помошник
   * @see SalaryHelper
   */
  final public SalaryHelper getSalaryHelper() throws ApplicationException {
//		if (this.salaryHelper == null)
//			this.salaryHelper = nativeGetSalaryHelper(handle);
    //TODO
    return this.salaryHelper;
  }

  /**
   * Возвращает класс помошник для работы с графиками работ и табелем
   *
   * @return класс помошник
   * @see TableHelper
   */
  final public TableHelper getTableHelper() throws ApplicationException {
//		if (this.tableHelper == null)
//			this.tableHelper = nativeGetTableHelper(handle);
    //TODO
    return this.tableHelper;
  }

  /**
   * Возвращает контекст выполнения расчета з/п
   *
   * @return контекст
   * @see SalaryAlgorithmContext
   */
  final public SalaryAlgorithmContext getContext() throws ApplicationException {
//		if (this.calcContext == null)
//			this.calcContext = nativeGetContext(handle);
    //TODO
    return this.calcContext;
  }

}
