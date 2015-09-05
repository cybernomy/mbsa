/*
 * CalculationParams.java
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
package com.mg.merp.salary.model;

import java.util.Date;

import com.mg.merp.personnelref.model.StaffList;

/**
 * Модель данных "Параметры расчета"
 * 
 * @author Artem V. Sharapov
 * @version $Id: CalculationParams.java,v 1.1 2007/07/09 08:21:56 sharapov Exp $
 */
public class CalculationParams {
		
	Date periodBeginDate;
	Date periodEndDate;
	StaffList staffList;
	
	/**
	 * @param periodBeginDate
	 * @param periodEndDate
	 * @param staffList
	 */
	public CalculationParams(Date periodBeginDate, Date periodEndDate, StaffList staffList) {
		this.periodBeginDate = periodBeginDate;
		this.periodEndDate = periodEndDate;
		this.staffList = staffList;
	}
	
	
	/**
	 * @return the periodBeginDate
	 */
	public Date getPeriodBeginDate() {
		return this.periodBeginDate;
	}

	/**
	 * @return the periodEndDate
	 */
	public Date getPeriodEndDate() {
		return this.periodEndDate;
	}

	/**
	 * @return the staffList
	 */
	public StaffList getStaffList() {
		return this.staffList;
	}
	
}
