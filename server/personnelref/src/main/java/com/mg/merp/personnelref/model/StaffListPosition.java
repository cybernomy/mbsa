/*
 * StaffListPosition.java
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
package com.mg.merp.personnelref.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Должности в штатном расписании"
 * 
 * @author Artem V. Sharapov
 * @version $Id: StaffListPosition.java,v 1.7 2007/07/09 07:47:11 sharapov Exp $
 */
@DataItemName("PersonnelRef.StaffListPosition") //$NON-NLS-1$
public class StaffListPosition extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.personnelref.model.PrefPosition Position;
	private java.lang.String SlPositionUniqueId;

	private com.mg.merp.personnelref.model.WorkSchedule WorkSchedule;

	private com.mg.merp.personnelref.model.TaxCalcKind TaxCalcKind;

	private com.mg.merp.personnelref.model.StaffCategory StaffCategory;

	private com.mg.merp.personnelref.model.WorkCondition WorkCondition;

	private com.mg.merp.personnelref.model.StaffListUnit StaffListUnit;

	private com.mg.merp.personnelref.model.CostsAnl CostsAnl1;
	private com.mg.merp.personnelref.model.CostsAnl CostsAnl2;
	private com.mg.merp.personnelref.model.CostsAnl CostsAnl3;
	private com.mg.merp.personnelref.model.CostsAnl CostsAnl4;
	private com.mg.merp.personnelref.model.CostsAnl CostsAnl5;

	private java.util.Date BeginDate;
	private java.util.Date EndDate;

	private java.math.BigDecimal RateNumber;
	private java.lang.Integer HolidayNumber;

	private com.mg.merp.core.model.SysClient SysClient;


	// Constructors

	/** default constructor */
	public StaffListPosition() {
	}

	/** constructor with id */
	public StaffListPosition(java.lang.Integer Id) {
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
	//@DataItemName("PersonnelRef.Position.CostsAnl")
	@DataItemName("Personnelref.CostsAnl1") //$NON-NLS-1$
	public com.mg.merp.personnelref.model.CostsAnl getCostsAnl1() {
		return this.CostsAnl1;
	}

	public void setCostsAnl1(com.mg.merp.personnelref.model.CostsAnl PrefCostsAnl) {
		this.CostsAnl1 = PrefCostsAnl;
	}

	/**
	 * 
	 */
	public com.mg.merp.personnelref.model.WorkCondition getWorkCondition() {
		return this.WorkCondition;
	}

	public void setWorkCondition(com.mg.merp.personnelref.model.WorkCondition PrefWorkCondition) {
		this.WorkCondition = PrefWorkCondition;
	}

	/**
	 * 
	 */
	@DataItemName("Personnelref.CostsAnl3") //$NON-NLS-1$
	public com.mg.merp.personnelref.model.CostsAnl getCostsAnl3() {
		return this.CostsAnl3;
	}

	public void setCostsAnl3(com.mg.merp.personnelref.model.CostsAnl PrefCostsAnl_1) {
		this.CostsAnl3 = PrefCostsAnl_1;
	}

	/**
	 * 
	 */
	@DataItemName("Personnelref.CostsAnl2") //$NON-NLS-1$
	public com.mg.merp.personnelref.model.CostsAnl getCostsAnl2() {
		return this.CostsAnl2;
	}

	public void setCostsAnl2(com.mg.merp.personnelref.model.CostsAnl PrefCostsAnl_2) {
		this.CostsAnl2 = PrefCostsAnl_2;
	}

	/**
	 * 
	 */
	public com.mg.merp.personnelref.model.StaffCategory getStaffCategory() {
		return this.StaffCategory;
	}

	public void setStaffCategory(com.mg.merp.personnelref.model.StaffCategory PrefStaffCategory) {
		this.StaffCategory = PrefStaffCategory;
	}

	/**
	 * 
	 */
	@DataItemName("Personnelref.CostsAnl4") //$NON-NLS-1$
	public com.mg.merp.personnelref.model.CostsAnl getCostsAnl4() {
		return this.CostsAnl4;
	}

	public void setCostsAnl4(com.mg.merp.personnelref.model.CostsAnl PrefCostsAnl_3) {
		this.CostsAnl4 = PrefCostsAnl_3;
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
	public com.mg.merp.personnelref.model.TaxCalcKind getTaxCalcKind() {
		return this.TaxCalcKind;
	}

	public void setTaxCalcKind(com.mg.merp.personnelref.model.TaxCalcKind PrefTaxCalcKind) {
		this.TaxCalcKind = PrefTaxCalcKind;
	}

	/**
	 * 
	 */
	public com.mg.merp.personnelref.model.PrefPosition getPosition() {
		return this.Position;
	}

	public void setPosition(com.mg.merp.personnelref.model.PrefPosition PrefPosition) {
		this.Position = PrefPosition;
	}

	/**
	 * 
	 */
	@DataItemName("Personnelref.CostsAnl5") //$NON-NLS-1$
	public com.mg.merp.personnelref.model.CostsAnl getCostsAnl5() {
		return this.CostsAnl5;
	}

	public void setCostsAnl5(com.mg.merp.personnelref.model.CostsAnl PrefCostsAnl_4) {
		this.CostsAnl5 = PrefCostsAnl_4;
	}

	/**
	 * 
	 */
	public com.mg.merp.personnelref.model.WorkSchedule getWorkSchedule() {
		return this.WorkSchedule;
	}

	public void setWorkSchedule(com.mg.merp.personnelref.model.WorkSchedule PrefWorkSchedule) {
		this.WorkSchedule = PrefWorkSchedule;
	}

	/**
	 * 
	 */
	public com.mg.merp.personnelref.model.StaffListUnit getStaffListUnit() {
		return this.StaffListUnit;
	}

	public void setStaffListUnit(com.mg.merp.personnelref.model.StaffListUnit PrefStaffListUnit) {
		this.StaffListUnit = PrefStaffListUnit;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Position.SlPositionUniqueId") //$NON-NLS-1$
	public java.lang.String getSlPositionUniqueId() {
		return this.SlPositionUniqueId;
	}

	public void setSlPositionUniqueId(java.lang.String SlPositionUniqueId) {
		this.SlPositionUniqueId = SlPositionUniqueId;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Position.BeginDate") //$NON-NLS-1$
	public java.util.Date getBeginDate() {
		return this.BeginDate;
	}

	public void setBeginDate(java.util.Date Begindate) {
		this.BeginDate = Begindate;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Position.EndDate") //$NON-NLS-1$
	public java.util.Date getEndDate() {
		return this.EndDate;
	}

	public void setEndDate(java.util.Date Enddate) {
		this.EndDate = Enddate;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Position.RateNumber") //$NON-NLS-1$
	public java.math.BigDecimal getRateNumber() {
		return this.RateNumber;
	}

	public void setRateNumber(java.math.BigDecimal RateNumber) {
		this.RateNumber = RateNumber;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Position.HolidayNumber") //$NON-NLS-1$
	public java.lang.Integer getHolidayNumber() {
		return this.HolidayNumber;
	}

	public void setHolidayNumber(java.lang.Integer HolidayNumber) {
		this.HolidayNumber = HolidayNumber;
	}

}