/*
 * InternalInvoiceSpec.java
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
 * @version $Id: InternalInvoiceSpec.java,v 1.4 2005/07/15 13:12:41 pashistova
 *          Exp $
 */
public class InternalInvoiceSpec extends com.mg.merp.document.model.DocSpec
		implements java.io.Serializable {

	// Fields

	private java.math.BigDecimal RequestQuan;

	private java.math.BigDecimal RequestSumma;

	// Constructors

	/** default constructor */
	public InternalInvoiceSpec() {
	}

	// Property accessors
	@DataItemName("Account.InvInvoice.RequestQuan")
	public java.math.BigDecimal getRequestQuan() {
		return this.RequestQuan;
	}

	public void setRequestQuan(java.math.BigDecimal Requestquan) {
		this.RequestQuan = Requestquan;
	}

	/**
	 * 
	 */
	@DataItemName("Account.InvInvoice.RequestSumma")
	public java.math.BigDecimal getRequestSumma() {
		return this.RequestSumma;
	}

	public void setRequestSumma(java.math.BigDecimal Requestsumma) {
		this.RequestSumma = Requestsumma;
	}

}