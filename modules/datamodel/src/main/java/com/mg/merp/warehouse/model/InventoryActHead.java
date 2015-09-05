/*
 * InventoryActHead.java
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
package com.mg.merp.warehouse.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: InventoryActHead.java,v 1.6 2008/02/29 12:33:44 safonov Exp $
 */
public class InventoryActHead extends com.mg.merp.document.model.DocHead
		implements java.io.Serializable, org.hibernate.bytecode.javassist.FieldHandled {

	// Fields

	private com.mg.merp.reference.model.Contractor Contractor;

	private java.math.BigDecimal RealSummaNat;

	private java.math.BigDecimal RealSummaCur;

	private java.util.Date EndDate;
	
	private java.math.BigDecimal exsessSummaCur;
	
	private java.math.BigDecimal exsessSummaNat;
	
	private java.math.BigDecimal shortageSummaCur;
	
	private java.math.BigDecimal shortageSummaNat;

	// Constructors

	/** default constructor */
	public InventoryActHead() {
	}

	// Property accessors
	/**
	 * 
	 */

	@DataItemName("Warehouse.InventoryActHead.Contractor")
	public com.mg.merp.reference.model.Contractor getContractor() {
		return this.Contractor;
	}

	public void setContractor(com.mg.merp.reference.model.Contractor Contractor) {
		this.Contractor = Contractor;
	}

	/**
	 * 
	 */

	@DataItemName("Warehouse.InventoryActHead.RealSummaNat")
	public java.math.BigDecimal getRealSummaNat() {
		return this.RealSummaNat;
	}

	public void setRealSummaNat(java.math.BigDecimal RealSummaNat) {
		this.RealSummaNat = RealSummaNat;
	}

	/**
	 * 
	 */

	@DataItemName("Warehouse.InventoryActHead.RealSummaCur")
	public java.math.BigDecimal getRealSummaCur() {
		return this.RealSummaCur;
	}

	public void setRealSummaCur(java.math.BigDecimal RealSummaCur) {
		this.RealSummaCur = RealSummaCur;
	}

	/**
	 * 
	 */

	@DataItemName("Warehouse.InventoryActHead.EndDate")
	public java.util.Date getEndDate() {
		return this.EndDate;
	}

	public void setEndDate(java.util.Date EndDate) {
		this.EndDate = EndDate;
	}

	@DataItemName("Warehouse.InventoryActHead.ExsessSummaCur")
	public java.math.BigDecimal getExsessSummaCur() {
		return exsessSummaCur;
	}

	public void setExsessSummaCur(java.math.BigDecimal exsessSummaCur) {
		this.exsessSummaCur = exsessSummaCur;
	}

	@DataItemName("Warehouse.InventoryActHead.ExsessSummaNat")
	public java.math.BigDecimal getExsessSummaNat() {
		return exsessSummaNat;
	}

	public void setExsessSummaNat(java.math.BigDecimal exsessSummaNat) {
		this.exsessSummaNat = exsessSummaNat;
	}

	@DataItemName("Warehouse.InventoryActHead.ShortageSummaCur")
	public java.math.BigDecimal getShortageSummaCur() {
		return shortageSummaCur;
	}

	public void setShortageSummaCur(java.math.BigDecimal shortageSummaCur) {
		this.shortageSummaCur = shortageSummaCur;
	}

	@DataItemName("Warehouse.InventoryActHead.ShortageSummaNat")
	public java.math.BigDecimal getShortageSummaNat() {
		return shortageSummaNat;
	}

	public void setShortageSummaNat(java.math.BigDecimal shortageSummaNat) {
		this.shortageSummaNat = shortageSummaNat;
	}

}