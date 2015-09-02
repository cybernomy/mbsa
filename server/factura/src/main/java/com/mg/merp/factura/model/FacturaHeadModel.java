/*
 * FacturaHeadModel.java
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
package com.mg.merp.factura.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: FacturaHeadModel.java,v 1.4 2006/04/13 10:15:25 safonov Exp $
 */
public class FacturaHeadModel extends com.mg.merp.document.model.DocHeadModel
		implements java.io.Serializable {

	// Fields

	private com.mg.merp.reference.model.BankAccount PartnerToBankReq;

	private com.mg.merp.reference.model.Contractor Consignee;

	private com.mg.merp.reference.model.Contractor Shipper;

	private com.mg.merp.reference.model.BankAccount PartnerFromBankReq;

	private java.util.Date InDate;

	private java.util.Date StockDate;

	private java.util.Date PayDate;

	private java.lang.String ToPayDocNumber;

	private java.util.Date ToPayDocDate;

	private java.lang.String Comment;

	// Constructors

	/** default constructor */
	public FacturaHeadModel() {
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("Factura.FacturaHeadModel.PartnerToBankReq")
	public com.mg.merp.reference.model.BankAccount getPartnerToBankReq() {
		return this.PartnerToBankReq;
	}

	public void setPartnerToBankReq(
			com.mg.merp.reference.model.BankAccount RefBankAccount) {
		this.PartnerToBankReq = RefBankAccount;
	}

	/**
	 * 
	 */
	@DataItemName("Factura.FacturaHeadModel.Consignee")
	public com.mg.merp.reference.model.Contractor getConsignee() {
		return this.Consignee;
	}

	public void setConsignee(com.mg.merp.reference.model.Contractor Contractor) {
		this.Consignee = Contractor;
	}

	@DataItemName("Factura.FacturaHeadModel.Shipper")
	public com.mg.merp.reference.model.Contractor getShipper() {
		return this.Shipper;
	}

	public void setShipper(com.mg.merp.reference.model.Contractor Contractor_1) {
		this.Shipper = Contractor_1;
	}

	/**
	 * 
	 */
	@DataItemName("Factura.FacturaHeadModel.PartnerFromBankReq")
	public com.mg.merp.reference.model.BankAccount getPartnerFromBankReq() {
		return this.PartnerFromBankReq;
	}

	public void setPartnerFromBankReq(
			com.mg.merp.reference.model.BankAccount RefBankAccount_1) {
		this.PartnerFromBankReq = RefBankAccount_1;
	}

	/**
	 * 
	 */
	@DataItemName("Factura.FacturaHeadModel.InDate")
	public java.util.Date getInDate() {
		return this.InDate;
	}

	public void setInDate(java.util.Date Indate) {
		this.InDate = Indate;
	}

	/**
	 * 
	 */
	@DataItemName("Factura.FacturaHeadModel.StockDate")
	public java.util.Date getStockDate() {
		return this.StockDate;
	}

	public void setStockDate(java.util.Date Stockdate) {
		this.StockDate = Stockdate;
	}

	/**
	 * 
	 */
	@DataItemName("Factura.FacturaHeadModel.PayDate")
	public java.util.Date getPayDate() {
		return this.PayDate;
	}

	public void setPayDate(java.util.Date Paydate) {
		this.PayDate = Paydate;
	}

	/**
	 * 
	 */
	@DataItemName("Factura.FacturaHeadModel.ToPayDocNumber")
	public java.lang.String getToPayDocNumber() {
		return this.ToPayDocNumber;
	}

	public void setToPayDocNumber(java.lang.String Topaydocnumber) {
		this.ToPayDocNumber = Topaydocnumber;
	}

	/**
	 * 
	 */
	@DataItemName("Factura.FacturaHeadModel.ToPayDocDate")
	public java.util.Date getToPayDocDate() {
		return this.ToPayDocDate;
	}

	public void setToPayDocDate(java.util.Date Topaydocdate) {
		this.ToPayDocDate = Topaydocdate;
	}

	/**
	 * 
	 */
	@DataItemName("Factura.FacturaHeadModel.Comment")
	public java.lang.String getComment() {
		return this.Comment;
	}

	public void setComment(java.lang.String Comment) {
		this.Comment = Comment;
	}

}