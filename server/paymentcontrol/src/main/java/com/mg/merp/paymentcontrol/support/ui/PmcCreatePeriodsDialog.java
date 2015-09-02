/*
 * PmcCreatePeriodsDialog.java
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

package com.mg.merp.paymentcontrol.support.ui;

import java.util.Date;

import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.CheckBox;
import com.mg.framework.generic.ui.DefaultDialog;

/**
 * Контроллер диалога "Cоздание периодов планирования"
 * 
 * @author Artem V. Sharapov
 * @version $Id: PmcCreatePeriodsDialog.java,v 1.2 2007/05/14 05:23:52 sharapov Exp $
 */
public class PmcCreatePeriodsDialog extends DefaultDialog {

	// Fields

	private boolean pmcYear;
	private boolean pmcHalfYear;
	private boolean pmcQuarter;
	private boolean pmcMonth;
	private boolean pmcTenDays;
	private boolean pmcWeek;
	private boolean pmcDay;
	private Date beginDate;
	private Integer upLevelQuantity;

	private final String WEEK_WIDGET = "pmcWeek"; //$NON-NLS-1$
	private final String TEN_DAYS_WIDGET = "pmcTenDays"; //$NON-NLS-1$
	

	// Default constructor
	public PmcCreatePeriodsDialog() {
		upLevelQuantity = 1;
		pmcDay = true;
	}


	// Methods

	/**
	 * Обработчик изменения значения "Декады"
	 * @param event - событие
	 */
	public void onActionPmcTenDaysChanged(WidgetEvent event) {
		view.getWidget(WEEK_WIDGET).setEnabled(!(Boolean) ((CheckBox) view.getWidget(TEN_DAYS_WIDGET)).getEditorValue());
	}

	/**
	 * Обработчик изменения значения "Недели"
	 * @param event - событие
	 */
	public void onActionPmcWeekChanged(WidgetEvent event) {
		view.getWidget(TEN_DAYS_WIDGET).setEnabled(!(Boolean) ((CheckBox) view.getWidget(WEEK_WIDGET)).getEditorValue());
	}

	// Property accessors

	public boolean isPmcDay() {
		return pmcDay;
	}

	public void setPmcDay(boolean pmcDay) {
		this.pmcDay = pmcDay;
	}

	public boolean isPmcHalfYear() {
		return pmcHalfYear;
	}

	public void setPmcHalfYear(boolean pmcHalfYear) {
		this.pmcHalfYear = pmcHalfYear;
	}
	public boolean isPmcMonth() {
		return pmcMonth;
	}

	public void setPmcMonth(boolean pmcMonth) {
		this.pmcMonth = pmcMonth;
	}

	public boolean isPmcQuarter() {
		return pmcQuarter;
	}

	public void setPmcQuarter(boolean pmcQuarter) {
		this.pmcQuarter = pmcQuarter;
	}

	public boolean isPmcTenDays() {
		return pmcTenDays;
	}

	public void setPmcTenDays(boolean pmcTenDays) {
		this.pmcTenDays = pmcTenDays;
	}

	public boolean isPmcWeek() {
		return pmcWeek;
	}

	public void setPmcWeek(boolean pmcWeek) {
		this.pmcWeek = pmcWeek;
	}

	public boolean isPmcYear() {
		return pmcYear;
	}

	public void setPmcYear(boolean pmcYear) {
		this.pmcYear = pmcYear;
	}

	public java.util.Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(java.util.Date beginDate) {
		this.beginDate = beginDate;
	}

	public java.lang.Integer getUpLevelQuantity() {
		return upLevelQuantity;
	}

	public void setUpLevelQuantity(java.lang.Integer upLevelQuantity) {
		this.upLevelQuantity = upLevelQuantity;
	}

}
