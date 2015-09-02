/*
 * DefaultValueProcessor.java
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

import com.mg.framework.api.Logger;
import com.mg.framework.api.metadata.DefaultValueFactory;
import com.mg.framework.utils.ServerUtils;

/**
 * Процессор обработки значений по умолчанию
 * 
 * @author Oleg V. Safonov
 * @version $Id: DefaultValueProcessor.java,v 1.1 2006/09/30 11:42:05 safonov Exp $
 */
public class DefaultValueProcessor {
	private static Logger logger = ServerUtils.getLogger(DefaultValueProcessor.class);
	
	/**
	 * создание значения по умолчанию
	 * 
	 * @param name	наименование фабрики значений
	 * @return	объект значение
	 */
	public static Object createDefaultValue(String name) {
		if (name == null)
			return null;
		try {
			return ((DefaultValueFactory) ServerUtils.loadClass(name).newInstance()).createDefaultValue();
		} catch (Throwable e) {
			logger.error("DefaultValue instantiation error", e);
			return null;
		}
	}

}
