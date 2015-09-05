/*
 * TaxHead.java
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
 * @version $Id: TaxHead.java,v 1.5 2006/09/08 07:12:28 leonova Exp $
 */
public class TaxHead extends com.mg.framework.service.PersistentObjectHibernate
		implements java.io.Serializable {

	// Fields

	private int Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String TName;

	private java.lang.String TCode;

	private java.util.Set SetOfSalTaxScale;

	// Constructors

	/** default constructor */
	public TaxHead() {
	}

	/** constructor with id */
	public TaxHead(int Id) {
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
	@DataItemName("Salary.Name")
	public java.lang.String getTName() {
		return this.TName;
	}

	public void setTName(java.lang.String Tname) {
		this.TName = Tname;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.BigCode")
	public java.lang.String getTCode() {
		return this.TCode;
	}

	public void setTCode(java.lang.String Tcode) {
		this.TCode = Tcode;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfSalTaxScale() {
		return this.SetOfSalTaxScale;
	}

	public void setSetOfSalTaxScale(java.util.Set SetOfSalTaxScale) {
		this.SetOfSalTaxScale = SetOfSalTaxScale;
	}

}