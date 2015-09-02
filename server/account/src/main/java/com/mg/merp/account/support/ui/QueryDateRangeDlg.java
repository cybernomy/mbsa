/*
 * QueryDateRangeDlg.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.account.support.ui;

import java.util.Date;

import com.mg.framework.generic.ui.DefaultDialog;

/**
 * ���������� ������� "�������� ���"
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: QueryDateRangeDlg.java,v 1.1 2008/03/27 10:40:42 alikaev Exp $
 */
public class QueryDateRangeDlg extends DefaultDialog {

	private Date beginDate;
	
	private Date endDate;

	public QueryDateRangeDlg() {
	}

	/**
	 * � ����
	 * @return beginDate
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * �� ����
	 * @return endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	
}
