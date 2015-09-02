/*
 * CalcListSectionServiceLocal.java
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

import com.mg.merp.salary.model.CalcListSection;

/**
 * Сервис бизнес-компонента "Разделы расчетного листка"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: CalcListSectionServiceLocal.java,v 1.2 2007/07/09 08:20:19 sharapov Exp $
 */
public interface CalcListSectionServiceLocal extends com.mg.framework.api.DataBusinessObjectService<CalcListSection, Integer> {
	
	/**
	 * Имя сервиса
	 */
	final static String LOCAL_SERVICE_NAME= "merp/salary/CalcListSection"; //$NON-NLS-1$
	
}
