/*
 * EmployeesServiceLocal.java
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

import com.mg.merp.reference.model.Employees;

/**
 * Бизнес-компонент "Сотрудники"
 * 
 * @author leonova
 * @version $Id: EmployeesServiceLocal.java,v 1.2 2007/08/16 14:14:04 safonov Exp $
 */
public interface EmployeesServiceLocal extends com.mg.merp.reference.Contractor<Employees> {
	/**
	 * имя сервиса
	 */
	final static String SERVICE_NAME = "merp/reference/Employees";
}
