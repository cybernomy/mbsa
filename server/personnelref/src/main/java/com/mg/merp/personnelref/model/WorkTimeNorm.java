/*
 * WorkTimeNorm.java
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
package com.mg.merp.personnelref.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: WorkTimeNorm.java,v 1.6 2006/10/12 11:50:17 safonov Exp $
 */
public class WorkTimeNorm extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.personnelref.model.CalcPeriod CalcPeriod;

	private com.mg.merp.personnelref.model.WorkSchedule WorkSchedule;

	private com.mg.merp.baiengine.model.Repository WorkDaysAlg;

	private com.mg.merp.baiengine.model.Repository WorkHoursAlg;

	private java.lang.Integer WorkDaysNumber;

	private java.lang.Integer WorkHoursNumber;

	// Constructors

	/** default constructor */
	public WorkTimeNorm() {
	}

	/** constructor with id */
	public WorkTimeNorm(java.lang.Integer Id) {
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

	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.SysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	/**
	 * 
	 */

	@DataItemName("PersonnelRef.WorkTimeNorm.CalcPeriod")
	public com.mg.merp.personnelref.model.CalcPeriod getCalcPeriod() {
		return this.CalcPeriod;
	}

	public void setCalcPeriod(
			com.mg.merp.personnelref.model.CalcPeriod PrefCalcPeriod) {
		this.CalcPeriod = PrefCalcPeriod;
	}

	/**
	 * 
	 */

	public com.mg.merp.personnelref.model.WorkSchedule getWorkSchedule() {
		return this.WorkSchedule;
	}

	public void setWorkSchedule(
			com.mg.merp.personnelref.model.WorkSchedule PrefWorkSchedule) {
		this.WorkSchedule = PrefWorkSchedule;
	}

	/**
	 * 
	 */

	@DataItemName("PersonnelRef.WorkTimeNorm.WorkDaysNumber")
	public java.lang.Integer getWorkDaysNumber() {
		return this.WorkDaysNumber;
	}

	public void setWorkDaysNumber(java.lang.Integer WorkdaysNumber) {
		this.WorkDaysNumber = WorkdaysNumber;
	}

	/**
	 * 
	 */

	@DataItemName("PersonnelRef.WorkTimeNorm.WorkHoursNumber")
	public java.lang.Integer getWorkHoursNumber() {
		return this.WorkHoursNumber;
	}

	public void setWorkHoursNumber(java.lang.Integer WorkhoursNumber) {
		this.WorkHoursNumber = WorkhoursNumber;
	}

	/**
	 * 
	 */

	@DataItemName("PersonnelRef.WorkTimeNorm.WorkDaysAlg")
	public com.mg.merp.baiengine.model.Repository getWorkDaysAlg() {
		return this.WorkDaysAlg;
	}

	public void setWorkDaysAlg(
			com.mg.merp.baiengine.model.Repository WorkDaysAlg) {
		this.WorkDaysAlg = WorkDaysAlg;
	}

	/**
	 * 
	 */

	@DataItemName("PersonnelRef.WorkTimeNorm.WorkHoursAlg")
	public com.mg.merp.baiengine.model.Repository getWorkHoursAlg() {
		return this.WorkHoursAlg;
	}

	public void setWorkHoursAlg(
			com.mg.merp.baiengine.model.Repository WorkHoursAlg) {
		this.WorkHoursAlg = WorkHoursAlg;
	}

}