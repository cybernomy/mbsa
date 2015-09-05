/*
 * TaxLink.java
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
 * @version $Id: TaxLink.java,v 1.2 2006/09/25 13:49:27 safonov Exp $
 */
public class TaxLink extends com.mg.framework.service.PersistentObjectHibernate
		implements java.io.Serializable {

	// Fields

	private int Id;

	private com.mg.merp.reference.model.TaxGroup TaxGroup;

	private com.mg.merp.reference.model.Tax Tax;

	private com.mg.merp.core.model.SysClient SysClient;

	private short FeeOrder;

	// Constructors

	/** default constructor */
	public TaxLink() {
	}

	/** constructor with id */
	public TaxLink(int Id) {
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

	public com.mg.merp.reference.model.TaxGroup getTaxGroup() {
		return this.TaxGroup;
	}

	public void setTaxGroup(com.mg.merp.reference.model.TaxGroup TaxGroup) {
		this.TaxGroup = TaxGroup;
	}

	/**
	 * 
	 */

	public com.mg.merp.reference.model.Tax getTax() {
		return this.Tax;
	}

	public void setTax(com.mg.merp.reference.model.Tax Tax) {
		this.Tax = Tax;
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
	@DataItemName("Reference.TaxLink.FeeOrder")
	public short getFeeOrder() {
		return this.FeeOrder;
	}

	public void setFeeOrder(short FeeOrder) {
		this.FeeOrder = FeeOrder;
	}

}