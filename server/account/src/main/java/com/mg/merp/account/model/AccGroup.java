/*
 * AccGroup.java
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
 * @version $Id: AccGroup.java,v 1.4 2006/06/08 13:02:50 leonova Exp $
 */
@DataItemName("Account.AccGroup")
public class AccGroup extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private int Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.account.model.AccKind AccKind;

	private java.lang.String GCode;

	private java.lang.String GName;

	private java.lang.Integer MinUsagePeriod;

	private java.lang.Integer MaxUsagePeriod;

	// Constructors

	/** default constructor */
	public AccGroup() {
	}

	/** constructor with id */
	public AccGroup(int Id) {
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

	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.SysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	/**
	 * 
	 */

	public com.mg.merp.account.model.AccKind getAccKind() {
		return this.AccKind;
	}

	public void setAccKind(com.mg.merp.account.model.AccKind AccAcckind) {
		this.AccKind = AccAcckind;
	}

	/**
	 * 
	 */

	@DataItemName("Account.AccGroup.GCode")
	public java.lang.String getGCode() {
		return this.GCode;
	}

	public void setGCode(java.lang.String Gcode) {
		this.GCode = Gcode;
	}

	/**
	 * 
	 */

	@DataItemName("Account.AccGroup.GName")
	public java.lang.String getGName() {
		return this.GName;
	}

	public void setGName(java.lang.String Gname) {
		this.GName = Gname;
	}

	/**
	 * 
	 */

	@DataItemName("Account.AccGroup.MinUsagePeriod")
	public java.lang.Integer getMinUsagePeriod() {
		return this.MinUsagePeriod;
	}

	public void setMinUsagePeriod(java.lang.Integer MinUsageperiod) {
		this.MinUsagePeriod = MinUsageperiod;
	}

	/**
	 * 
	 */

	@DataItemName("Account.AccGroup.MaxUsagePeriod")
	public java.lang.Integer getMaxUsagePeriod() {
		return this.MaxUsagePeriod;
	}

	public void setMaxUsagePeriod(java.lang.Integer MaxUsageperiod) {
		this.MaxUsagePeriod = MaxUsageperiod;
	}
}