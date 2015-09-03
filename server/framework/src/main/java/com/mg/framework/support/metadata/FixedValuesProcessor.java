/*
 * FixedValuesProcessor.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.framework.support.metadata;

import java.util.List;

import com.mg.framework.api.Logger;
import com.mg.framework.api.metadata.FixedValue;
import com.mg.framework.api.metadata.FixedValuesFactory;
import com.mg.framework.utils.ServerUtils;

/**
 * Процессор обработки фиксированных значений
 * 
 * @author Oleg V. Safonov
 * @version $Id: FixedValuesProcessor.java,v 1.2 2008/03/03 13:13:18 safonov Exp $
 */
public class FixedValuesProcessor {
	private static Logger logger = ServerUtils.getLogger(FixedValuesProcessor.class);

	/**
	 * создание фиксированных значений
	 * 
	 * @param name	наименование фабрики фиксированных значений
	 * @return	фиксированные значения
	 */
	public static List<FixedValue<?>> createFixedValues(String name) {
		if (name == null)
			return null;
		try {
			return ((FixedValuesFactory) ServerUtils.loadClass(name).newInstance()).createFixedValues();
		} catch (Throwable e) {
			logger.error("FixedValues instantiation error", e);
			return null;
		}
	}
}
