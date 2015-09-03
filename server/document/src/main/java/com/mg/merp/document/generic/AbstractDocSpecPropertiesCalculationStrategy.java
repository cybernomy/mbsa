/*
 * AbstractDocSpecPropertiesCalculationStrategy.java
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
package com.mg.merp.document.generic;

import com.mg.merp.document.DocSpecPropertiesCalculationStrategy;

/**
 * Базовая реализация стратегии расчета свойств спецификации документа
 * 
 * @author Artem V. Sharapov
 * @version $Id: AbstractDocSpecPropertiesCalculationStrategy.java,v 1.1 2007/10/30 14:26:47 sharapov Exp $
 */
public abstract class AbstractDocSpecPropertiesCalculationStrategy implements DocSpecPropertiesCalculationStrategy {
	
	/**
	 * реализация вычисления цен и сумм
	 */
	protected abstract void doAdjust();
	
	/**
	 * реализация вычисления налогов
	 */
	protected abstract void doCalculateTaxes();
	
	/* (non-Javadoc)
	 * @see com.mg.merp.retail.DocSpecPropertiesCalculationStrategy#adjust()
	 */
	public final void adjust() {
		doAdjust();
		doCalculateTaxes();
	}

}
