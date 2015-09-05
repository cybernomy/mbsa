/*
 * PartnEmplLink.java
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
package com.mg.merp.reference.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: PartnEmplLink.java,v 1.5 2006/08/16 04:32:31 leonova Exp $
 */
public class PartnEmplLink extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private int Id;

	private com.mg.merp.reference.model.Partner Partner;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.reference.model.Employees Employees;

	private java.util.Date DateBegin;

	private java.util.Date DateEnd;

	// Constructors

	/** default constructor */
	public PartnEmplLink() {
	}

	/** constructor with id */
	public PartnEmplLink(int Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("ID")
	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	/**
	 * 
	 */

	public com.mg.merp.reference.model.Partner getPartner() {
		return this.Partner;
	}

	public void setPartner(com.mg.merp.reference.model.Partner Partner) {
		this.Partner = Partner;
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
	@DataItemName("Reference.Partner.Empl.Employees")
	public com.mg.merp.reference.model.Employees getEmployees() {
		return this.Employees;
	}

	public void setEmployees(com.mg.merp.reference.model.Employees Employees) {
		this.Employees = Employees;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Partner.Empl.DateBegin")
	public java.util.Date getDateBegin() {
		return this.DateBegin;
	}

	public void setDateBegin(java.util.Date Datebegin) {
		this.DateBegin = Datebegin;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Partner.Empl.DateEnd")
	public java.util.Date getDateEnd() {
		return this.DateEnd;
	}

	public void setDateEnd(java.util.Date Dateend) {
		this.DateEnd = Dateend;
	}

}