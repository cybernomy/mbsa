/*
 * ResStr.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.finance.totals.helperclasses;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: ResStr.java,v 1.4 2006/07/05 09:03:17 poroxnenko Exp $
 */
public class ResStr
{
	public static final String SINVALID_ANL_LEVEL = "Неверно указан уровень аналитики";
	public static final String SINVALID_PERIOD = "Период не существует";
	public static final String SCANT_SEL_CORACCOUNTS = "Выборка корреспондирующих счетов для оборотной ведомости по признакам не возможна";
	public static final String SCOR_ACCOUNTS_NOT_SEL = "Выборка корреспондирующих счетов не задана";
	public static final String SINVALID_FIELD_NAME = "Недопустимое имя поля %s";
	public static final String SINVALID_ACC_CODE = "В списке указан несуществующий код";
	public static final String SBETWEEN_DATES_SQL = "select %s from finspec s join finoper h on h.id = s.finoper_id" +
              " where (s.planned= %d) and (s.%s = %d ) %s;";
}
