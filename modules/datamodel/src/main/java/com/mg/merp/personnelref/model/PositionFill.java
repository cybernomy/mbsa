/*
 * PositionFill.java
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
 * @version $Id: PositionFill.java,v 1.9 2007/02/05 15:11:07 safonov Exp $
 */
@DataItemName("Salary.PositionFill")
public class PositionFill extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.personnelref.model.PersonalAccount PersonalAccount;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.personnelref.model.PositionFillKind PositionFillKind;

	private com.mg.merp.personnelref.model.PrefPosition Position;

	private com.mg.merp.personnelref.model.StaffListPosition SlPositionUnique;

	private java.util.Date BeginDate;

	private java.util.Date EndDate;

	private java.lang.String DocType;

	private java.lang.String DocNumber;

	private java.util.Date DocDate;

	private java.math.BigDecimal RateNumber;

	private boolean IsBasic;

	// Constructors

	/** default constructor */
	public PositionFill() {
	}

	/** constructor with id */
	public PositionFill(java.lang.Integer Id) {
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

	public com.mg.merp.personnelref.model.PersonalAccount getPersonalAccount() {
		return this.PersonalAccount;
	}

	public void setPersonalAccount(
			com.mg.merp.personnelref.model.PersonalAccount SalPersonalAccount) {
		this.PersonalAccount = SalPersonalAccount;
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
	@DataItemName("Salary.PosFill.PosFillKind")
	public com.mg.merp.personnelref.model.PositionFillKind getPositionFillKind() {
		return this.PositionFillKind;
	}

	public void setPositionFillKind(
			com.mg.merp.personnelref.model.PositionFillKind PrefPositionFillKind) {
		this.PositionFillKind = PrefPositionFillKind;
	}

	/**
	 * 
	 */	
	public com.mg.merp.personnelref.model.PrefPosition getPosition() {
		return this.Position;
	}

	public void setPosition(
			com.mg.merp.personnelref.model.PrefPosition PrefPosition) {
		this.Position = PrefPosition;
	}

	/**
	 * 
	 */
	public com.mg.merp.personnelref.model.StaffListPosition getSlPositionUnique() {
		return this.SlPositionUnique;
	}

	public void setSlPositionUnique(com.mg.merp.personnelref.model.StaffListPosition SlPositionUnique) {
		this.SlPositionUnique = SlPositionUnique;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.PosFill.BeginDate")
	public java.util.Date getBeginDate() {
		return this.BeginDate;
	}

	public void setBeginDate(java.util.Date Begindate) {
		this.BeginDate = Begindate;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.PosFill.EndDate")
	public java.util.Date getEndDate() {
		return this.EndDate;
	}

	public void setEndDate(java.util.Date Enddate) {
		this.EndDate = Enddate;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.PosFill.DocType")
	public java.lang.String getDocType() {
		return this.DocType;
	}

	public void setDocType(java.lang.String Doctype) {
		this.DocType = Doctype;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.PosFill.DateNumber")
	public java.lang.String getDocNumber() {
		return this.DocNumber;
	}

	public void setDocNumber(java.lang.String Docnumber) {
		this.DocNumber = Docnumber;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.PosFill.DateDate")
	public java.util.Date getDocDate() {
		return this.DocDate;
	}

	public void setDocDate(java.util.Date Docdate) {
		this.DocDate = Docdate;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.PosFill.RateNumber")
	public java.math.BigDecimal getRateNumber() {
		return this.RateNumber;
	}

	public void setRateNumber(java.math.BigDecimal RateNumber) {
		this.RateNumber = RateNumber;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.PosFill.IsBasic")
	public boolean getIsBasic() {
		return this.IsBasic;
	}

	public void setIsBasic(boolean IsBasic) {
		this.IsBasic = IsBasic;
	}

}