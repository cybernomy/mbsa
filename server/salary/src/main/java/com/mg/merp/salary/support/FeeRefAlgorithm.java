/*
 * FeeRefAlgorithm.java
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
import com.mg.merp.salary.FeeRefAlgorithmResult;

/**
 * Базовый класс расчета н/у. Класс алгоритма должен
 * реализовывать следующий метод <code>protected FeeRefAlgorithmResult internalExecute() throws Exception</code>.
 * 
 * <p>Пример данного метода:
 * 	<pre>
 * 		protected FeeRefAlgorithmResult internalExecute() throws Exception {
 * 			FeeRefAlgorithmResult result = new FeeRefAlgorithmResult();
 * 			return result;
 * 		}
 * 	</pre>
 * 
 * @see SalaryAlgorithm
 * @see FeeRefAlgorithmResult
 * 
 * @author Oleg V. Safonov
 * @version $Id$
 */
public abstract class FeeRefAlgorithm extends SalaryAlgorithm<FeeRefAlgorithmResult> {

	final protected Object convertToLegacyResult(FeeRefAlgorithmResult source) {
		try {
//			nativeSetCalcResult(handle, source.value,
//					source.beginDate == null ? 0 : source.beginDate.getTime(),
//					source.endDate == null ? 0 : source.endDate.getTime(),
//					source.periodBeginDate == null ? 0 : source.periodBeginDate
//							.getTime(), source.periodEndDate == null ? 0
//							: source.periodEndDate.getTime(),
//					source.costsAnl1Id, source.costsAnl2Id, source.costsAnl3Id,
//					source.costsAnl4Id, source.costsAnl5Id);
			//TODO
			return null;
		} catch (ApplicationException e) {
			getLogger().error(e);
		}
		return null;
	}
	
}
