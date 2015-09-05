/*
 * PayRoll.java
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
package com.mg.merp.salary.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: PayRoll.java,v 1.6 2006/06/20 08:23:10 leonova Exp $
 */
public class PayRoll extends com.mg.framework.service.PersistentObjectHibernate
		implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.personnelref.model.CalcPeriod CalcPeriod;

	private com.mg.merp.salary.model.RollKind RollKind;

	private java.lang.String Number;

	private java.lang.String Name;

	private java.lang.String DocType;

	private java.lang.String DocNumber;

	private java.util.Date DocDate;

	private java.lang.String Comments;

	private java.util.Date CalcBeginDate;

	private java.util.Date CalcEndDate;

	private java.util.Set SetOfSalCalcList;

	// Constructors

	/** default constructor */
	public PayRoll() {
	}

	/** constructor with id */
	public PayRoll(java.lang.Integer Id) {
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
	public com.mg.merp.salary.model.RollKind getRollKind() {
		return this.RollKind;
	}

	public void setRollKind(com.mg.merp.salary.model.RollKind SalRollKind) {
		this.RollKind = SalRollKind;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.PayRoll.Number")
	public java.lang.String getNumber() {
		return this.Number;
	}

	public void setNumber(java.lang.String Rnumber) {
		this.Number = Rnumber;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.Name")
	public java.lang.String getName() {
		return this.Name;
	}

	public void setName(java.lang.String Rname) {
		this.Name = Rname;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.PayRoll.DocType")
	public java.lang.String getDocType() {
		return this.DocType;
	}

	public void setDocType(java.lang.String Doctype) {
		this.DocType = Doctype;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.PayRoll.DocNumber")
	public java.lang.String getDocNumber() {
		return this.DocNumber;
	}

	public void setDocNumber(java.lang.String Docnumber) {
		this.DocNumber = Docnumber;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.PayRoll.DocDate")
	public java.util.Date getDocDate() {
		return this.DocDate;
	}

	public void setDocDate(java.util.Date Docdate) {
		this.DocDate = Docdate;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.PayRoll.Comments")
	public java.lang.String getComments() {
		return this.Comments;
	}

	public void setComments(java.lang.String Comments) {
		this.Comments = Comments;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.PayRoll.CalcBeginDate")
	public java.util.Date getCalcBeginDate() {
		return this.CalcBeginDate;
	}

	public void setCalcBeginDate(java.util.Date CalcBegindate) {
		this.CalcBeginDate = CalcBegindate;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.PayRoll.CalcEndDate")
	public java.util.Date getCalcEndDate() {
		return this.CalcEndDate;
	}

	public void setCalcEndDate(java.util.Date CalcEnddate) {
		this.CalcEndDate = CalcEnddate;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfSalCalcList() {
		return this.SetOfSalCalcList;
	}

	public void setSetOfSalCalcList(java.util.Set SetOfSalCalcList) {
		this.SetOfSalCalcList = SetOfSalCalcList;
	}

}