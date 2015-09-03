/*
 * CostsAnlResult.java
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
package com.mg.merp.salary.support;

import com.mg.merp.personnelref.model.CostsAnl;

/**
 * Класс-результат отбора аналитики состава затрат
 * 
 * @author Artem V. Sharapov
 * @version $Id: CostsAnlResult.java,v 1.1 2007/08/21 05:33:09 sharapov Exp $
 */
public class CostsAnlResult {
	
	private CostsAnl costsAnl1;
	private CostsAnl costsAnl2;
	private CostsAnl costsAnl3;
	private CostsAnl costsAnl4;
	private CostsAnl costsAnl5;
	
	
	public CostsAnlResult() {
	}
	
	/**
	 * @param costsAnl1 - аналитика состава затрат 1-го уровня
	 * @param costsAnl2 - аналитика состава затрат 2-го уровня
	 * @param costsAnl3 - аналитика состава затрат 3-го уровня
	 * @param costsAnl4 - аналитика состава затрат 4-го уровня
	 * @param costsAnl5 - аналитика состава затрат 5-го уровня
	 */
	public CostsAnlResult(CostsAnl costsAnl1, CostsAnl costsAnl2, CostsAnl costsAnl3, CostsAnl costsAnl4, CostsAnl costsAnl5) {
		this.costsAnl1 = costsAnl1;
		this.costsAnl2 = costsAnl2;
		this.costsAnl3 = costsAnl3;
		this.costsAnl4 = costsAnl4;
		this.costsAnl5 = costsAnl5;
	}

	
	/**
	 * Получить аналитику состава затрат 1-го уровня
	 * @return аналитика состава затрат 1-го уровня
	 */
	public CostsAnl getCostsAnl1() {
		return this.costsAnl1;
	}

	/**
	 * Получить аналитику состава затрат 2-го уровня
	 * @return аналитика состава затрат 2-го уровня
	 */
	public CostsAnl getCostsAnl2() {
		return this.costsAnl2;
	}

	/**
	 * Получить аналитику состава затрат 3-го уровня
	 * @return аналитика состава затрат 3-го уровня
	 */
	public CostsAnl getCostsAnl3() {
		return this.costsAnl3;
	}

	/**
	 * Получить аналитику состава затрат 4-го уровня
	 * @return аналитика состава затрат 4-го уровня
	 */
	public CostsAnl getCostsAnl4() {
		return this.costsAnl4;
	}

	/**
	 * Получить аналитику состава затрат 5-го уровня
	 * @return аналитика состава затрат 5-го уровня
	 */
	public CostsAnl getCostsAnl5() {
		return this.costsAnl5;
	}
	
}
