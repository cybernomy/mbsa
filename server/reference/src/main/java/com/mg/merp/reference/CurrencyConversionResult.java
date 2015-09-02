/*
 * CurrencyServiceLocal.java
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

import java.math.BigDecimal;

/**
 * Результат конвертации валют
 * 
 * @author Oleg V. Safonov
 * @version $Id: CurrencyConversionResult.java,v 1.2 2007/04/13 11:37:25 safonov Exp $
 */
public interface CurrencyConversionResult {
	/**
	 * получить конвертированную сумму
	 * 
	 * @return конвертированная сумма
	 */
	BigDecimal getAmount();
	
	/**
	 * получить признак прямой или обратный курс
	 * 
	 * @return	если <code>true</code>, то курс прямой, иначе обратный
	 */
	boolean isDirect();
	
	/**
	 * получить значение курса по которому была произведена конвертация
	 * 
	 * @return	значение курса
	 */
	BigDecimal getRate();
}
