/*
 * PersonnelLeave.java
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
 * @version $Id: PersonnelLeave.java,v 1.4 2006/06/19 07:54:39 leonova Exp $
 */
public class PersonnelLeave extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.humanresources.model.Order Order;

	private com.mg.merp.personnelref.model.Personnel Personnel;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.personnelref.model.LeaveKind LeaveKind;

	private java.util.Date WorkBeginDate;

	private java.util.Date WorkEndDate;

	private java.lang.Integer HolidayNumber;

	private java.util.Date BeginDate;

	private java.util.Date EndDate;

	// Constructors

	/** default constructor */
	public PersonnelLeave() {
	}

	/** constructor with id */
	public PersonnelLeave(java.lang.Integer Id) {
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

	public com.mg.merp.humanresources.model.Order getOrder() {
		return this.Order;
	}

	public void setOrder(com.mg.merp.humanresources.model.Order HrOrder) {
		this.Order = HrOrder;
	}

	/**
	 * 
	 */

	public com.mg.merp.personnelref.model.Personnel getPersonnel() {
		return this.Personnel;
	}

	public void setPersonnel(
			com.mg.merp.personnelref.model.Personnel PrefPersonnel) {
		this.Personnel = PrefPersonnel;
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
	public com.mg.merp.personnelref.model.LeaveKind getLeaveKind() {
		return this.LeaveKind;
	}

	public void setLeaveKind(
			com.mg.merp.personnelref.model.LeaveKind PrefLeaveKind) {
		this.LeaveKind = PrefLeaveKind;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Personnel.WorkBeginDate")
	public java.util.Date getWorkBeginDate() {
		return this.WorkBeginDate;
	}

	public void setWorkBeginDate(java.util.Date WorkBegindate) {
		this.WorkBeginDate = WorkBegindate;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Personnel.WorkEndDate")
	public java.util.Date getWorkEndDate() {
		return this.WorkEndDate;
	}

	public void setWorkEndDate(java.util.Date WorkEnddate) {
		this.WorkEndDate = WorkEnddate;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Personnel.HolidayNumber")
	public java.lang.Integer getHolidayNumber() {
		return this.HolidayNumber;
	}

	public void setHolidayNumber(java.lang.Integer HolidayNumber) {
		this.HolidayNumber = HolidayNumber;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Personnel.BeginDate")
	public java.util.Date getBeginDate() {
		return this.BeginDate;
	}

	public void setBeginDate(java.util.Date Begindate) {
		this.BeginDate = Begindate;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Personnel.EndDate")
	public java.util.Date getEndDate() {
		return this.EndDate;
	}

	public void setEndDate(java.util.Date Enddate) {
		this.EndDate = Enddate;
	}

}