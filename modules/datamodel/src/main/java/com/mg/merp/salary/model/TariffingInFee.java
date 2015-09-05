/*
 * TariffingInFee.java
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
 * @version $Id: TariffingInFee.java,v 1.4 2006/09/08 07:12:28 leonova Exp $
 */
public class TariffingInFee extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.salary.model.FeeRef FeeRef;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.personnelref.model.TariffingCategory TariffingCategory;

	// Constructors

	/** default constructor */
	public TariffingInFee() {
	}

	/** constructor with id */
	public TariffingInFee(java.lang.Integer Id) {
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

	public com.mg.merp.salary.model.FeeRef getFeeRef() {
		return this.FeeRef;
	}

	public void setFeeRef(com.mg.merp.salary.model.FeeRef SalFeeRef) {
		this.FeeRef = SalFeeRef;
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
	@DataItemName("Salary.TariffIn.TariffingCategory")
	public com.mg.merp.personnelref.model.TariffingCategory getTariffingCategory() {
		return this.TariffingCategory;
	}

	public void setTariffingCategory(
			com.mg.merp.personnelref.model.TariffingCategory PrefTariffingCategory) {
		this.TariffingCategory = PrefTariffingCategory;
	}

}