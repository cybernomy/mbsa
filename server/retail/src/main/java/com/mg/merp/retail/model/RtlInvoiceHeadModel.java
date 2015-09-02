/*
 * RtlInvoiceHeadModel.java
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
package com.mg.merp.retail.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: RtlInvoiceHeadModel.java,v 1.4 2006/03/30 11:30:41 safonov Exp $
 */
public class RtlInvoiceHeadModel extends
		com.mg.merp.document.model.DocHeadModel implements java.io.Serializable {

	// Fields

	private com.mg.merp.discount.model.ExtraDiscount ExtraDiscount;

	private com.mg.merp.reference.model.Contractor Consignee;

	private com.mg.merp.reference.model.Contractor Shipper;

	private com.mg.merp.discount.model.Card DiscountCard;

	private com.mg.merp.reference.model.Contractor Responsible;

	private com.mg.merp.reference.model.Contractor Consumer;

	private java.math.BigDecimal SumCurDiscount;

	private java.math.BigDecimal SumNatDiscount;

	private java.math.BigDecimal DiscountOnDoc;

	private java.math.BigDecimal DiscountOnLine;

	private java.lang.String Comments;

	private java.util.Date PlanPayDate;

	private java.util.Date PayDate;

	private java.math.BigDecimal PaySum;

	private java.math.BigDecimal BaseDiscount;

	private java.util.Date PlanShipDate;

	private boolean ApplyDisKind;

	// Constructors

	/** default constructor */
	public RtlInvoiceHeadModel() {
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("Retail.InvHead.ExtraDiscount")
	public com.mg.merp.discount.model.ExtraDiscount getExtraDiscount() {
		return this.ExtraDiscount;
	}

	public void setExtraDiscount(
			com.mg.merp.discount.model.ExtraDiscount DisExtraDiscount) {
		this.ExtraDiscount = DisExtraDiscount;
	}

	/**
	 * 
	 */
	@DataItemName("Retail.Consignee")
	public com.mg.merp.reference.model.Contractor getConsignee() {
		return this.Consignee;
	}

	public void setConsignee(com.mg.merp.reference.model.Contractor Contractor) {
		this.Consignee = Contractor;
	}

	@DataItemName("Retail.Shipper")
	public com.mg.merp.reference.model.Contractor getShipper() {
		return this.Shipper;
	}

	public void setShipper(com.mg.merp.reference.model.Contractor Contractor_1) {
		this.Shipper = Contractor_1;
	}

	/**
	 * 
	 */
	@DataItemName("Retail.InvHead.DiscountCard")
	public com.mg.merp.discount.model.Card getDiscountCard() {
		return this.DiscountCard;
	}

	public void setDiscountCard(com.mg.merp.discount.model.Card DisCard) {
		this.DiscountCard = DisCard;
	}

	/**
	 * 
	 */
	@DataItemName("Retail.Responsible")
	public com.mg.merp.reference.model.Contractor getResponsible() {
		return this.Responsible;
	}

	public void setResponsible(
			com.mg.merp.reference.model.Contractor Contractor_2) {
		this.Responsible = Contractor_2;
	}

	/**
	 * 
	 */

	public com.mg.merp.reference.model.Contractor getConsumer() {
		return this.Consumer;
	}

	public void setConsumer(com.mg.merp.reference.model.Contractor Contractor_3) {
		this.Consumer = Contractor_3;
	}

	/**
	 * 
	 */
	@DataItemName("Retail.InvHead.SumCurDiscount")
	public java.math.BigDecimal getSumCurDiscount() {
		return this.SumCurDiscount;
	}

	public void setSumCurDiscount(java.math.BigDecimal SumcurDiscount) {
		this.SumCurDiscount = SumcurDiscount;
	}

	/**
	 * 
	 */

	public java.math.BigDecimal getSumNatDiscount() {
		return this.SumNatDiscount;
	}

	public void setSumNatDiscount(java.math.BigDecimal SumnatDiscount) {
		this.SumNatDiscount = SumnatDiscount;
	}

	/**
	 * 
	 */
	@DataItemName("Retail.InvHead.DiscountOnDoc")
	public java.math.BigDecimal getDiscountOnDoc() {
		return this.DiscountOnDoc;
	}

	public void setDiscountOnDoc(java.math.BigDecimal DiscountOnDoc) {
		this.DiscountOnDoc = DiscountOnDoc;
	}

	/**
	 * 
	 */

	public java.math.BigDecimal getDiscountOnLine() {
		return this.DiscountOnLine;
	}

	public void setDiscountOnLine(java.math.BigDecimal DiscountOnLine) {
		this.DiscountOnLine = DiscountOnLine;
	}

	/**
	 * 
	 */

	public java.lang.String getComments() {
		return this.Comments;
	}

	public void setComments(java.lang.String Comments) {
		this.Comments = Comments;
	}

	/**
	 * 
	 */
	@DataItemName("Retail.PlanPayDate")
	public java.util.Date getPlanPayDate() {
		return this.PlanPayDate;
	}

	public void setPlanPayDate(java.util.Date Planpaydate) {
		this.PlanPayDate = Planpaydate;
	}

	/**
	 * 
	 */
	@DataItemName("Retail.PayDate")
	public java.util.Date getPayDate() {
		return this.PayDate;
	}

	public void setPayDate(java.util.Date Paydate) {
		this.PayDate = Paydate;
	}

	/**
	 * 
	 */
	@DataItemName("Retail.PaySum")
	public java.math.BigDecimal getPaySum() {
		return this.PaySum;
	}

	public void setPaySum(java.math.BigDecimal Paysum) {
		this.PaySum = Paysum;
	}

	/**
	 * 
	 */
	@DataItemName("Retail.InvHead.BaseDiscount")
	public java.math.BigDecimal getBaseDiscount() {
		return this.BaseDiscount;
	}

	public void setBaseDiscount(java.math.BigDecimal BaseDiscount) {
		this.BaseDiscount = BaseDiscount;
	}

	/**
	 * 
	 */
	@DataItemName("Retail.PlanShipDate")
	public java.util.Date getPlanShipDate() {
		return this.PlanShipDate;
	}

	public void setPlanShipDate(java.util.Date Planshipdate) {
		this.PlanShipDate = Planshipdate;
	}

	/**
	 * 
	 */
	@DataItemName("Retail.ApplyDisKind")
	public boolean getApplyDisKind() {
		return this.ApplyDisKind;
	}

	public void setApplyDisKind(boolean ApplyDisKind) {
		this.ApplyDisKind = ApplyDisKind;
	}

}