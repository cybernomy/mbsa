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
	public static final String SINVALID_ANL_LEVEL = "������� ������ ������� ���������";
	public static final String SINVALID_PERIOD = "������ �� ����������";
	public static final String SCANT_SEL_CORACCOUNTS = "������� ����������������� ������ ��� ��������� ��������� �� ��������� �� ��������";
	public static final String SCOR_ACCOUNTS_NOT_SEL = "������� ����������������� ������ �� ������";
	public static final String SINVALID_FIELD_NAME = "������������ ��� ���� %s";
	public static final String SINVALID_ACC_CODE = "� ������ ������ �������������� ���";
	public static final String SBETWEEN_DATES_SQL = "select %s from finspec s join finoper h on h.id = s.finoper_id" +
              " where (s.planned= %d) and (s.%s = %d ) %s;";
}
