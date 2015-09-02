/*
 * FeeRefParamServiceLocal.java
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
package com.mg.merp.salary;

import com.mg.merp.salary.model.FeeRefParam;

/**
 * Сервис бизнес-компонента "Параметр начисления/удержания"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: FeeRefParamServiceLocal.java,v 1.2 2007/07/09 08:20:19 sharapov Exp $
 */
public interface FeeRefParamServiceLocal extends com.mg.framework.api.DataBusinessObjectService<FeeRefParam, Integer> {

	/**
	 * Имя сервиса
	 */
	final static String LOCAL_SERVICE_NAME= "merp/salary/FeeRefParam"; //$NON-NLS-1$
	
}
