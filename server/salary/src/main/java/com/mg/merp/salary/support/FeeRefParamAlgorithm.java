/*
 * FeeRefParamAlgorithm.java
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

/**
 * Базовый класс расчета параметров н/у. Класс алгоритма должен реализовывать следующий метод
 * <code>protected Double internalExecute() throws Exception</code>.
 *
 * <p>Пример данного метода:
 * <pre>
 * 		protected Double internalExecute() throws Exception {
 * 			return 100.0;
 *        }
 * 	</pre>
 *
 * @author Oleg V. Safonov
 * @version $Id$
 * @see SalaryAlgorithm
 */
public abstract class FeeRefParamAlgorithm extends SalaryAlgorithm<Double> {

}
