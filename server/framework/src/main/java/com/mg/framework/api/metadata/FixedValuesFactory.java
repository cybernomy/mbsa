/*
 * FixedValuesFactory.java
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
package com.mg.framework.api.metadata;

import java.util.List;

/**
 * Фабрика фиксированных значений для домена
 * 
 * @author Oleg V. Safonov
 * @version $Id: FixedValuesFactory.java,v 1.2 2008/03/03 13:11:02 safonov Exp $
 */
public interface FixedValuesFactory {

	/**
	 * создание фиксированных значений
	 * 
	 * @return	фиксированные значения
	 */
	List<FixedValue<?>> createFixedValues();

}
