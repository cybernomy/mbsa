/*
 * ScheduleHead.java
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
package com.mg.merp.table.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Графики работ в табельном учете"
 * 
 * @author Artem V. Sharapov
 * @version $Id: ScheduleHead.java,v 1.5 2008/08/12 14:11:44 sharapov Exp $
 */
public class ScheduleHead extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.table.model.PatternHead DefaultPatternHead;

	private com.mg.merp.table.model.TimeKind HolidayWorkTimeKind;

	private com.mg.merp.personnelref.model.WorkSchedule WorkSchedule;

	private HolidayAccountKind HolidayAccountKind;

	private java.math.BigDecimal PreHolidayReduction;

	private java.util.Set<ScheduleSpec> scheduleSpecs;

	// Constructors

	/** default constructor */
	public ScheduleHead() {
	}

	/** constructor with id */
	public ScheduleHead(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors

	/**
	 * 
	 */
	@DataItemName("ID") //$NON-NLS-1$
	public java.lang.Integer getId() {
		return this.Id;
	}

	public void setId(java.lang.Integer Id) {
		this.Id = Id;
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
	@DataItemName("Table.Schedule.DefaultPatternHead") //$NON-NLS-1$
	public com.mg.merp.table.model.PatternHead getDefaultPatternHead() {
		return this.DefaultPatternHead;
	}

	public void setDefaultPatternHead(com.mg.merp.table.model.PatternHead TabPatternHead) {
		this.DefaultPatternHead = TabPatternHead;
	}

	/**
	 * 
	 */
	@DataItemName("Table.Schedule.HolidayWorkTime") //$NON-NLS-1$
	public com.mg.merp.table.model.TimeKind getHolidayWorkTimeKind() {
		return this.HolidayWorkTimeKind;
	}

	public void setHolidayWorkTimeKind(com.mg.merp.table.model.TimeKind TabTimeKind) {
		this.HolidayWorkTimeKind = TabTimeKind;
	}

	/**
	 * 
	 */
	@DataItemName("Table.Schedule.WorkSchedule") //$NON-NLS-1$
	public com.mg.merp.personnelref.model.WorkSchedule getWorkSchedule() {
		return this.WorkSchedule;
	}

	public void setWorkSchedule(com.mg.merp.personnelref.model.WorkSchedule PrefWorkSchedule) {
		this.WorkSchedule = PrefWorkSchedule;
	}

	/**
	 * 
	 */
	public HolidayAccountKind getHolidayAccountKind() {
		return this.HolidayAccountKind;
	}

	public void setHolidayAccountKind(HolidayAccountKind HolidayAccountKind) {
		this.HolidayAccountKind = HolidayAccountKind;
	}

	/**
	 * 
	 */
	@DataItemName("Table.Schedule.PreHolidayReduction") //$NON-NLS-1$
	public java.math.BigDecimal getPreHolidayReduction() {
		return this.PreHolidayReduction;
	}

	public void setPreHolidayReduction(java.math.BigDecimal PreholidayReduction) {
		this.PreHolidayReduction = PreholidayReduction;
	}

	/**
	 * 
	 */
	public java.util.Set<ScheduleSpec> getScheduleSpecs() {
		return this.scheduleSpecs;
	}

	public void setScheduleSpecs(java.util.Set<ScheduleSpec> scheduleSpecs) {
		this.scheduleSpecs = scheduleSpecs;
	}

}