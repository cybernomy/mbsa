/*
 * WorkSchedule.java
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
 * @version $Id: WorkSchedule.java,v 1.5 2006/06/19 07:30:48 leonova Exp $
 */
@DataItemName("PersonnelRef.WorkSchedule")
public class WorkSchedule extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.personnelref.model.WorkSchedule LeaveSchedule;

	private java.lang.String SCode;

	private java.lang.String SName;

	private java.util.Date BeginDate;

	private java.util.Date EndDate;

	private java.util.Set SetOfPrefWorkTimeNorm;

	// Constructors

	/** default constructor */
	public WorkSchedule() {
	}

	/** constructor with id */
	public WorkSchedule(java.lang.Integer Id) {
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

	@DataItemName("PersonnelRef.WorkSchedule.LeaveSchedule")
	public com.mg.merp.personnelref.model.WorkSchedule getLeaveSchedule() {
		return this.LeaveSchedule;
	}

	public void setLeaveSchedule(
			com.mg.merp.personnelref.model.WorkSchedule PrefWorkSchedule) {
		this.LeaveSchedule = PrefWorkSchedule;
	}

	/**
	 * 
	 */

	@DataItemName("PersonnelRef.WorkSchedule.SCode")
	public java.lang.String getSCode() {
		return this.SCode;
	}

	public void setSCode(java.lang.String Scode) {
		this.SCode = Scode;
	}

	/**
	 * 
	 */

	@DataItemName("PersonnelRef.WorkSchedule.SName")
	public java.lang.String getSName() {
		return this.SName;
	}

	public void setSName(java.lang.String Sname) {
		this.SName = Sname;
	}

	/**
	 * 
	 */

	@DataItemName("PersonnelRef.WorkSchedule.BeginDate")
	public java.util.Date getBeginDate() {
		return this.BeginDate;
	}

	public void setBeginDate(java.util.Date Begindate) {
		this.BeginDate = Begindate;
	}

	/**
	 * 
	 */

	@DataItemName("PersonnelRef.WorkSchedule.EndDate")
	public java.util.Date getEndDate() {
		return this.EndDate;
	}

	public void setEndDate(java.util.Date Enddate) {
		this.EndDate = Enddate;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfPrefWorkTimeNorm() {
		return this.SetOfPrefWorkTimeNorm;
	}

	public void setSetOfPrefWorkTimeNorm(java.util.Set SetOfPrefWorkTimeNorm) {
		this.SetOfPrefWorkTimeNorm = SetOfPrefWorkTimeNorm;
	}

}