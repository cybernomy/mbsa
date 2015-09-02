/*
 * BillHeadModel.java
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
 * @version $Id: BillHeadModel.java,v 1.6 2006/06/02 11:12:59 leonova Exp $
 */
public class BillHeadModel extends com.mg.merp.document.model.DocHeadModel
		implements java.io.Serializable {

	// Fields

	private com.mg.merp.reference.model.BankAccount PartnerToBankReq;

	private com.mg.merp.reference.model.Contractor Consignee;

	private com.mg.merp.reference.model.Contractor Shipper;

	private com.mg.merp.reference.model.Contractor Responsible;

	private com.mg.merp.reference.model.BankAccount PartnerFromBankReq;

	private com.mg.merp.reference.model.Contractor Consumer;

	private java.math.BigDecimal SummaCurWithDiscount;

	private java.math.BigDecimal SummaNatWithDiscount;

	private java.math.BigDecimal AddExpenses;

	private java.math.BigDecimal DiscountOnDoc;

	private java.math.BigDecimal DiscountOnLine;

	private java.util.Date RegistDate;

	private java.lang.Integer AcceptanceTerm;

	private java.util.Date AcceptanceDate;

	private java.math.BigDecimal AcceptanceSum;

	private java.lang.Integer PaymentTerm;

	private java.util.Date PlanPaymentDateDoc;

	private java.util.Date PlanPaymentDate;

	private java.util.Date PaymentDate;

	private java.math.BigDecimal PaymentSum;

	private java.math.BigDecimal AddExpensesSum;

	private java.lang.String Comment;

	private java.util.Date ToPayDocDate;

	private java.lang.String ToPayDocNumber;

	private java.lang.String ProviderOKONH;

	private java.lang.String ProviderINN;

	private java.lang.String ProviderOKPO;

	private java.lang.String CustomerOKPO;

	private java.lang.String CustomerOKONH;

	private java.lang.String CustomerINN;

	// Constructors

	/** default constructor */
	public BillHeadModel() {
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("Warehouse.BillHead.PartnerToBankReq")
	public com.mg.merp.reference.model.BankAccount getPartnerToBankReq() {
		return this.PartnerToBankReq;
	}

	public void setPartnerToBankReq(
			com.mg.merp.reference.model.BankAccount PartnerToBankReq) {
		this.PartnerToBankReq = PartnerToBankReq;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.BillHeadModel.Consignee")
	public com.mg.merp.reference.model.Contractor getConsignee() {
		return this.Consignee;
	}

	public void setConsignee(com.mg.merp.reference.model.Contractor Consignee) {
		this.Consignee = Consignee;
	}

	@DataItemName("Warehouse.BillHeadModel.Shipper")
	public com.mg.merp.reference.model.Contractor getShipper() {
		return this.Shipper;
	}

	public void setShipper(com.mg.merp.reference.model.Contractor Shipper) {
		this.Shipper = Shipper;
	}

	/**
	 * 
	 */

	@DataItemName("Warehouse.BillHeadModel.Responsible")
	public com.mg.merp.reference.model.Contractor getResponsible() {
		return this.Responsible;
	}

	public void setResponsible(
			com.mg.merp.reference.model.Contractor Responsible) {
		this.Responsible = Responsible;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.BillHead.PartnerFromBankReq")
	public com.mg.merp.reference.model.BankAccount getPartnerFromBankReq() {
		return this.PartnerFromBankReq;
	}

	public void setPartnerFromBankReq(
			com.mg.merp.reference.model.BankAccount PartnerFromBankReq) {
		this.PartnerFromBankReq = PartnerFromBankReq;
	}

	/**
	 * 
	 */

	@DataItemName("Warehouse.BillHeadModel.Consumer")
	public com.mg.merp.reference.model.Contractor getConsumer() {
		return this.Consumer;
	}

	public void setConsumer(com.mg.merp.reference.model.Contractor Consumer) {
		this.Consumer = Consumer;
	}

	/**
	 * 
	 */

	public java.math.BigDecimal getSummaCurWithDiscount() {
		return this.SummaCurWithDiscount;
	}

	public void setSummaCurWithDiscount(
			java.math.BigDecimal SummaCurWithDiscount) {
		this.SummaCurWithDiscount = SummaCurWithDiscount;
	}

	/**
	 * 
	 */

	public java.math.BigDecimal getSummaNatWithDiscount() {
		return this.SummaNatWithDiscount;
	}

	public void setSummaNatWithDiscount(
			java.math.BigDecimal SummaNatWithDiscount) {
		this.SummaNatWithDiscount = SummaNatWithDiscount;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.BillHeadModel.AddExpenses")
	public java.math.BigDecimal getAddExpenses() {
		return this.AddExpenses;
	}

	public void setAddExpenses(java.math.BigDecimal AddExpenses) {
		this.AddExpenses = AddExpenses;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.BillHeadModel.DiscountOnDoc")
	public java.math.BigDecimal getDiscountOnDoc() {
		return this.DiscountOnDoc;
	}

	public void setDiscountOnDoc(java.math.BigDecimal DiscountOnDoc) {
		this.DiscountOnDoc = DiscountOnDoc;
	}

	/**
	 * 
	 */

	@DataItemName("Warehouse.BillHeadModel.DiscountOnLine")
	public java.math.BigDecimal getDiscountOnLine() {
		return this.DiscountOnLine;
	}

	public void setDiscountOnLine(java.math.BigDecimal DiscountOnLine) {
		this.DiscountOnLine = DiscountOnLine;
	}

	/**
	 * 
	 */

	public java.util.Date getRegistDate() {
		return this.RegistDate;
	}

	public void setRegistDate(java.util.Date RegistDate) {
		this.RegistDate = RegistDate;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.BillHeadModel.AcceptanceTerm")
	public java.lang.Integer getAcceptanceTerm() {
		return this.AcceptanceTerm;
	}

	public void setAcceptanceTerm(java.lang.Integer AcceptanceTerm) {
		this.AcceptanceTerm = AcceptanceTerm;
	}

	/**
	 * 
	 */

	@DataItemName("Warehouse.BillHeadModel.AcceptanceDate")
	public java.util.Date getAcceptanceDate() {
		return this.AcceptanceDate;
	}

	public void setAcceptanceDate(java.util.Date AcceptanceDate) {
		this.AcceptanceDate = AcceptanceDate;
	}

	/**
	 * 
	 */

	@DataItemName("Warehouse.BillHeadModel.AcceptanceSum")
	public java.math.BigDecimal getAcceptanceSum() {
		return this.AcceptanceSum;
	}

	public void setAcceptanceSum(java.math.BigDecimal AcceptanceSum) {
		this.AcceptanceSum = AcceptanceSum;
	}

	/**
	 * 
	 */

	@DataItemName("Warehouse.BillHeadModel.PaymentTerm")
	public java.lang.Integer getPaymentTerm() {
		return this.PaymentTerm;
	}

	public void setPaymentTerm(java.lang.Integer PaymentTerm) {
		this.PaymentTerm = PaymentTerm;
	}

	/**
	 * 
	 */

	@DataItemName("Warehouse.BillHeadModel.PlanPaymentDateDoc")
	public java.util.Date getPlanPaymentDateDoc() {
		return this.PlanPaymentDateDoc;
	}

	public void setPlanPaymentDateDoc(java.util.Date PlanPaymentDateDoc) {
		this.PlanPaymentDateDoc = PlanPaymentDateDoc;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.BillHeadModel.PlanPaymentDate")
	public java.util.Date getPlanPaymentDate() {
		return this.PlanPaymentDate;
	}

	public void setPlanPaymentDate(java.util.Date PlanPaymentDate) {
		this.PlanPaymentDate = PlanPaymentDate;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.BillHeadModel.PaymentDate")
	public java.util.Date getPaymentDate() {
		return this.PaymentDate;
	}

	public void setPaymentDate(java.util.Date PaymentDate) {
		this.PaymentDate = PaymentDate;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.BillHeadModel.PaymentSum")
	public java.math.BigDecimal getPaymentSum() {
		return this.PaymentSum;
	}

	public void setPaymentSum(java.math.BigDecimal PaymentSum) {
		this.PaymentSum = PaymentSum;
	}

	/**
	 * 
	 */

	public java.math.BigDecimal getAddExpensesSum() {
		return this.AddExpensesSum;
	}

	public void setAddExpensesSum(java.math.BigDecimal AddExpensesSum) {
		this.AddExpensesSum = AddExpensesSum;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.BillHeadModel.Comment")
	public java.lang.String getComment() {
		return this.Comment;
	}

	public void setComment(java.lang.String Comment) {
		this.Comment = Comment;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.BillHeadModel.ToPayDocDate")
	public java.util.Date getToPayDocDate() {
		return this.ToPayDocDate;
	}

	public void setToPayDocDate(java.util.Date ToPayDocDate) {
		this.ToPayDocDate = ToPayDocDate;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.BillHeadModel.ToPayDocNumber")
	public java.lang.String getToPayDocNumber() {
		return this.ToPayDocNumber;
	}

	public void setToPayDocNumber(java.lang.String ToPayDocNumber) {
		this.ToPayDocNumber = ToPayDocNumber;
	}

	/**
	 * 
	 */
	public java.lang.String getProviderOKONH() {
		return this.ProviderOKONH;
	}

	public void setProviderOKONH(java.lang.String ProviderOKONH) {
		this.ProviderOKONH = ProviderOKONH;
	}

	/**
	 * 
	 */

	public java.lang.String getProviderINN() {
		return this.ProviderINN;
	}

	public void setProviderINN(java.lang.String ProviderINN) {
		this.ProviderINN = ProviderINN;
	}

	/**
	 * 
	 */

	public java.lang.String getProviderOKPO() {
		return this.ProviderOKPO;
	}

	public void setProviderOKPO(java.lang.String ProviderOKPO) {
		this.ProviderOKPO = ProviderOKPO;
	}

	/**
	 * 
	 */

	public java.lang.String getCustomerOKPO() {
		return this.CustomerOKPO;
	}

	public void setCustomerOKPO(java.lang.String CustomerOKPO) {
		this.CustomerOKPO = CustomerOKPO;
	}

	/**
	 * 
	 */

	public java.lang.String getCustomerOKONH() {
		return this.CustomerOKONH;
	}

	public void setCustomerOKONH(java.lang.String CustomerOKONH) {
		this.CustomerOKONH = CustomerOKONH;
	}

	/**
	 * 
	 */

	public java.lang.String getCustomerINN() {
		return this.CustomerINN;
	}

	public void setCustomerINN(java.lang.String CustomerINN) {
		this.CustomerINN = CustomerINN;
	}

}