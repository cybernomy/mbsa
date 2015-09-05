/*
 * WeekCalendarChange.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.merp.mfreference.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: WeekCalendarChange.java,v 1.3 2006/09/04 05:54:46 leonova Exp $
 */
public class WeekCalendarChange extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.mfreference.model.DayCalendar DayCalThu;

	private com.mg.merp.mfreference.model.DayCalendar DayCalSat;

	private com.mg.merp.mfreference.model.WeekCalendar WeekCal;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.mfreference.model.DayCalendar DayCalMon;

	private com.mg.merp.mfreference.model.DayCalendar DayCalWed;

	private com.mg.merp.mfreference.model.DayCalendar DayCalSun;

	private com.mg.merp.mfreference.model.DayCalendar DayCalTue;

	private com.mg.merp.mfreference.model.DayCalendar DayCalFri;

	private java.util.Date EffOnDate;

	private java.util.Date EffOffDate;

	// Constructors

	/** default constructor */
	public WeekCalendarChange() {
	}

	/** constructor with id */
	public WeekCalendarChange(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("ID")
	public java.lang.Integer getId() {
		return this.Id;
	}

	public void setId(java.lang.Integer Id) {
		this.Id = Id;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.WeekCalendarChange.DayCalThu")
	public com.mg.merp.mfreference.model.DayCalendar getDayCalThu() {
		return this.DayCalThu;
	}

	public void setDayCalThu(com.mg.merp.mfreference.model.DayCalendar DayCalThu) {
		this.DayCalThu = DayCalThu;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.WeekCalendarChange.DayCalSat")
	public com.mg.merp.mfreference.model.DayCalendar getDayCalSat() {
		return this.DayCalSat;
	}

	public void setDayCalSat(com.mg.merp.mfreference.model.DayCalendar DayCalSat) {
		this.DayCalSat = DayCalSat;
	}

	/**
	 * 
	 */

	public com.mg.merp.mfreference.model.WeekCalendar getWeekCal() {
		return this.WeekCal;
	}

	public void setWeekCal(com.mg.merp.mfreference.model.WeekCalendar WeekCal) {
		this.WeekCal = WeekCal;
	}

	/**
	 * 
	 */

	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.SysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.WeekCalendarChange.DayCalMon")
	public com.mg.merp.mfreference.model.DayCalendar getDayCalMon() {
		return this.DayCalMon;
	}

	public void setDayCalMon(com.mg.merp.mfreference.model.DayCalendar DayCalMon) {
		this.DayCalMon = DayCalMon;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.WeekCalendarChange.DayCalWed")
	public com.mg.merp.mfreference.model.DayCalendar getDayCalWed() {
		return this.DayCalWed;
	}

	public void setDayCalWed(com.mg.merp.mfreference.model.DayCalendar DayCalWed) {
		this.DayCalWed = DayCalWed;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.WeekCalendarChange.DayCalSun")
	public com.mg.merp.mfreference.model.DayCalendar getDayCalSun() {
		return this.DayCalSun;
	}

	public void setDayCalSun(com.mg.merp.mfreference.model.DayCalendar DayCalSun) {
		this.DayCalSun = DayCalSun;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.WeekCalendarChange.DayCalTue")
	public com.mg.merp.mfreference.model.DayCalendar getDayCalTue() {
		return this.DayCalTue;
	}

	public void setDayCalTue(com.mg.merp.mfreference.model.DayCalendar DayCalTue) {
		this.DayCalTue = DayCalTue;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.WeekCalendarChange.DayCalFri")
	public com.mg.merp.mfreference.model.DayCalendar getDayCalFri() {
		return this.DayCalFri;
	}

	public void setDayCalFri(com.mg.merp.mfreference.model.DayCalendar DayCalFri) {
		this.DayCalFri = DayCalFri;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.WeekCalendarChange.EffOnDate")
	public java.util.Date getEffOnDate() {
		return this.EffOnDate;
	}

	public void setEffOnDate(java.util.Date EffOnDate) {
		this.EffOnDate = EffOnDate;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.WeekCalendarChange.EffOffDate")
	public java.util.Date getEffOffDate() {
		return this.EffOffDate;
	}

	public void setEffOffDate(java.util.Date EffOffDate) {
		this.EffOffDate = EffOffDate;
	}

}