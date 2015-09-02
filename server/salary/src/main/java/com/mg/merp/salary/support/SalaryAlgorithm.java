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
 * Ѕазовый класс расчета з/п
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
	 * ¬озвращает класс помошник дл€ вычислени€ з/п
	 * 
	 * @see SalaryHelper
	 * 
	 * @return	класс помошник
	 * @throws ApplicationException
	 */
	final public SalaryHelper getSalaryHelper() throws ApplicationException {
//		if (this.salaryHelper == null)
//			this.salaryHelper = nativeGetSalaryHelper(handle);
		//TODO
		return this.salaryHelper;
	}
	
	/**
	 * ¬озвращает класс помошник дл€ работы с графиками работ и табелем
	 * 
	 * @see TableHelper
	 * 
	 * @return	класс помошник
	 * @throws ApplicationException
	 */
	final public TableHelper getTableHelper() throws ApplicationException {
//		if (this.tableHelper == null)
//			this.tableHelper = nativeGetTableHelper(handle);
		//TODO
		return this.tableHelper;
	}
	
	/**
	 * ¬озвращает контекст выполнени€ расчета з/п
	 * 
	 * @see SalaryAlgorithmContext
	 * 
	 * @return	контекст
	 * @throws ApplicationException
	 */
	final public SalaryAlgorithmContext getContext() throws ApplicationException {
//		if (this.calcContext == null)
//			this.calcContext = nativeGetContext(handle);
		//TODO
		return this.calcContext;
	}
	
}
