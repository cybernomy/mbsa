/*
 * OrderHeadModel.java
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
 * @version $Id: OrderHeadModel.java,v 1.6 2007/02/06 08:20:40 safonov Exp $
 */
public class OrderHeadModel extends com.mg.merp.document.model.DocHeadModel
		implements java.io.Serializable {

	// Fields

	private com.mg.merp.reference.model.Contractor Responsible;

	private com.mg.merp.reference.model.Contractor Consumer;

	private java.math.BigDecimal SummaCurWithDiscount;

	private java.math.BigDecimal SummaNatWithDiscount;

	private java.math.BigDecimal AddExpenses;

	private java.math.BigDecimal DiscountOnDoc;

	private java.math.BigDecimal DiscountOnLine;

	private OrderDueDateKind DueDateKind;

	private java.math.BigDecimal DueDateQuan;

	private java.util.Date DueDate;

	private java.lang.Short CreditTerm;

	private java.math.BigDecimal Penalty;

	private java.lang.String Comment;

	private boolean FixedInput;

	private OrderStatus Status;

	// Constructors

	/** default constructor */
	public OrderHeadModel() {
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("Warehouse.OrderHeadModel.Responsible")
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
	@DataItemName("Warehouse.OrderHeadModel.Consumer")
	public com.mg.merp.reference.model.Contractor getConsumer() {
		return this.Consumer;
	}

	public void setConsumer(com.mg.merp.reference.model.Contractor Consumer) {
		this.Consumer = Consumer;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.BillHead.SummaCurWithDiscount")
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
	@DataItemName("Warehouse.BillHead.SummaNatWithDiscount")
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
	@DataItemName("Warehouse.OrderHead.AddExpenses")
	public java.math.BigDecimal getAddExpenses() {
		return this.AddExpenses;
	}

	public void setAddExpenses(java.math.BigDecimal AddExpenses) {
		this.AddExpenses = AddExpenses;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.OrderHead.DiscountOnDoc")
	public java.math.BigDecimal getDiscountOnDoc() {
		return this.DiscountOnDoc;
	}

	public void setDiscountOnDoc(java.math.BigDecimal DiscountOnDoc) {
		this.DiscountOnDoc = DiscountOnDoc;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.OrderHead.DiscountOnLine")
	public java.math.BigDecimal getDiscountOnLine() {
		return this.DiscountOnLine;
	}

	public void setDiscountOnLine(java.math.BigDecimal DiscountOnLine) {
		this.DiscountOnLine = DiscountOnLine;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.OrderHeadModel.DueDateKind")
	public OrderDueDateKind getDueDateKind() {
		return this.DueDateKind;
	}

	public void setDueDateKind(OrderDueDateKind DueDateKind) {
		this.DueDateKind = DueDateKind;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.OrderHeadModel.DueDateQuan")
	public java.math.BigDecimal getDueDateQuan() {
		return this.DueDateQuan;
	}

	public void setDueDateQuan(java.math.BigDecimal DueDateQuan) {
		this.DueDateQuan = DueDateQuan;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.OrderHeadModel.DueDate")
	public java.util.Date getDueDate() {
		return this.DueDate;
	}

	public void setDueDate(java.util.Date DueDate) {
		this.DueDate = DueDate;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.OrderHeadModel.CreditTerm")
	public java.lang.Short getCreditTerm() {
		return this.CreditTerm;
	}

	public void setCreditTerm(java.lang.Short CreditTerm) {
		this.CreditTerm = CreditTerm;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.OrderHeadModel.Penalty")
	public java.math.BigDecimal getPenalty() {
		return this.Penalty;
	}

	public void setPenalty(java.math.BigDecimal Penalty) {
		this.Penalty = Penalty;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.OrderHeadModel.Comment")
	public java.lang.String getComment() {
		return this.Comment;
	}

	public void setComment(java.lang.String Comment) {
		this.Comment = Comment;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.OrderHeadModel.FixedInput")
	public boolean getFixedInput() {
		return this.FixedInput;
	}

	public void setFixedInput(boolean FixedInput) {
		this.FixedInput = FixedInput;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.OrderHeadModel.Status")
	public OrderStatus getStatus() {
		return this.Status;
	}

	public void setStatus(OrderStatus Status) {
		this.Status = Status;
	}

}