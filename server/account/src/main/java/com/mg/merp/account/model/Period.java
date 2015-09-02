/*
 * FinPeriod.java
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
package com.mg.merp.account.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: Period.java,v 1.6 2006/06/22 11:11:58 leonova Exp $
 */
@DataItemName("Account.Period")
public class Period extends com.mg.framework.service.PersistentObjectHibernate
		implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String Name;

	private java.util.Date DateFrom;

	private java.util.Date DateTo;

	private java.lang.String WhoClosed;

	private java.util.Date DateClose;

	// Constructors

	/** default constructor */
	public Period() {
	}

	/** constructor with id */
	public Period(java.lang.Integer Id) {
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
	@DataItemName("Account.Period.Name")
	public java.lang.String getName() {
		return this.Name;
	}

	public void setName(java.lang.String Pname) {
		this.Name = Pname;
	}

	/**
	 * 
	 */
	@DataItemName("Account.Period.DateFrom")
	public java.util.Date getDateFrom() {
		return this.DateFrom;
	}

	public void setDateFrom(java.util.Date Datefrom) {
		this.DateFrom = Datefrom;
	}

	/**
	 * 
	 */
	@DataItemName("Account.Period.DateTo")
	public java.util.Date getDateTo() {
		return this.DateTo;
	}

	public void setDateTo(java.util.Date Dateto) {
		this.DateTo = Dateto;
	}

	/**
	 * 
	 */
	@DataItemName("Account.Period.WhoClosed")
	public java.lang.String getWhoClosed() {
		return this.WhoClosed;
	}

	public void setWhoClosed(java.lang.String Whoclosed) {
		this.WhoClosed = Whoclosed;
	}

	/**
	 * 
	 */
	@DataItemName("Account.Period.DateClose")
	public java.util.Date getDateClose() {
		return this.DateClose;
	}

	public void setDateClose(java.util.Date Dateclose) {
		this.DateClose = Dateclose;
	}
}