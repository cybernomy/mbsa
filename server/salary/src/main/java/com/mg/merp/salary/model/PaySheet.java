/*
 * PaySheet.java
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
 * @version $Id: PaySheet.java,v 1.5 2006/10/23 06:18:42 leonova Exp $
 */
public class PaySheet extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.reference.model.Contractor Cashier;

	private com.mg.merp.reference.model.Contractor Chief;

	private com.mg.merp.document.model.DocHead DocHead;

	private com.mg.merp.reference.model.Contractor Bookkeeper;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.salary.model.PayRoll PayRoll;

	private com.mg.merp.document.model.DocHead InDocHead;

	private java.lang.String SNumber;

	private java.util.Date BeginDate;

	private java.util.Date EndDate;

	private java.math.BigDecimal SummaFull;

	private java.util.Set SetOfSalPaySheetSpec;

	// Constructors

	/** default constructor */
	public PaySheet() {
	}

	/** constructor with id */
	public PaySheet(java.lang.Integer Id) {
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
	@DataItemName("Salary.PaySheet.Cashier")
	public com.mg.merp.reference.model.Contractor getCashier() {
		return this.Cashier;
	}

	public void setCashier(com.mg.merp.reference.model.Contractor Contractor) {
		this.Cashier = Contractor;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.PaySheet.Chief")
	public com.mg.merp.reference.model.Contractor getChief() {
		return this.Chief;
	}

	public void setChief(com.mg.merp.reference.model.Contractor Contractor_1) {
		this.Chief = Contractor_1;
	}

	/**
	 * 
	 */

	public com.mg.merp.document.model.DocHead getDocHead() {
		return this.DocHead;
	}

	public void setDocHead(com.mg.merp.document.model.DocHead Dochead) {
		this.DocHead = Dochead;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.PaySheet.Bookkeeper")
	public com.mg.merp.reference.model.Contractor getBookkeeper() {
		return this.Bookkeeper;
	}

	public void setBookkeeper(
			com.mg.merp.reference.model.Contractor Contractor_2) {
		this.Bookkeeper = Contractor_2;
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
	@DataItemName("Salary.PaySheet.PayRoll")
	public com.mg.merp.salary.model.PayRoll getPayRoll() {
		return this.PayRoll;
	}

	public void setPayRoll(com.mg.merp.salary.model.PayRoll SalPayRoll) {
		this.PayRoll = SalPayRoll;
	}

	/**
	 * 
	 */

	public com.mg.merp.document.model.DocHead getInDocHead() {
		return this.InDocHead;
	}

	public void setInDocHead(com.mg.merp.document.model.DocHead Dochead_1) {
		this.InDocHead = Dochead_1;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.PaySheet.SNumber")
	public java.lang.String getSNumber() {
		return this.SNumber;
	}

	public void setSNumber(java.lang.String Snumber) {
		this.SNumber = Snumber;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.PaySheet.BeginDate")
	public java.util.Date getBeginDate() {
		return this.BeginDate;
	}

	public void setBeginDate(java.util.Date Begindate) {
		this.BeginDate = Begindate;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.PaySheet.EndDate")
	public java.util.Date getEndDate() {
		return this.EndDate;
	}

	public void setEndDate(java.util.Date Enddate) {
		this.EndDate = Enddate;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.PaySheet.SummaFull")
	public java.math.BigDecimal getSummaFull() {
		return this.SummaFull;
	}

	public void setSummaFull(java.math.BigDecimal SummaFull) {
		this.SummaFull = SummaFull;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfSalPaySheetSpec() {
		return this.SetOfSalPaySheetSpec;
	}

	public void setSetOfSalPaySheetSpec(java.util.Set SetOfSalPaySheetSpec) {
		this.SetOfSalPaySheetSpec = SetOfSalPaySheetSpec;
	}

}