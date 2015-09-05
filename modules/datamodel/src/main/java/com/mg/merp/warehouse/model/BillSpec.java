/*
 * BillSpec.java
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
 * @version $Id: BillSpec.java,v 1.6 2007/09/27 15:38:46 safonov Exp $
 */
public class BillSpec extends BaseStockDocumentSpec implements
		java.io.Serializable {

	// Fields

	private java.math.BigDecimal AcceptanceQuan;

	private java.math.BigDecimal AcceptanceSum;

	private java.math.BigDecimal AcceptancePackingQuan;

	// Constructors

	/** default constructor */
	public BillSpec() {
	}

	// Property accessors

	/**
	 * 
	 */
	@DataItemName("Warehouse.BillSpec.AcceptanceQuan")
	public java.math.BigDecimal getAcceptanceQuan() {
		return this.AcceptanceQuan;
	}

	public void setAcceptanceQuan(java.math.BigDecimal AcceptanceQuan) {
		this.AcceptanceQuan = AcceptanceQuan;
	}

	/**
	 * 
	 */

	@DataItemName("Warehouse.BillSpec.AcceptanceSum")
	public java.math.BigDecimal getAcceptanceSum() {
		return this.AcceptanceSum;
	}

	public void setAcceptanceSum(java.math.BigDecimal AcceptanceSum) {
		this.AcceptanceSum = AcceptanceSum;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.BillSpec.AcceptancePackingQuan")
	public java.math.BigDecimal getAcceptancePackingQuan() {
		return this.AcceptancePackingQuan;
	}

	public void setAcceptancePackingQuan(
			java.math.BigDecimal AcceptancePackingQuan) {
		this.AcceptancePackingQuan = AcceptancePackingQuan;
	}

}