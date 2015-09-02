/*
 * CashDocumentModel.java
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
 * @version $Id: CashDocumentModel.java,v 1.4 2006/03/30 11:22:12 safonov Exp $
 */
public class CashDocumentModel extends com.mg.merp.document.model.DocHeadModel
		implements java.io.Serializable {

	// Fields

	private com.mg.merp.reference.model.Contractor Bookkeeper;

	private com.mg.merp.reference.model.Contractor Chief;

	private com.mg.merp.account.model.AccPlan Acc;

	private com.mg.merp.reference.model.Contractor Company;

	private com.mg.merp.reference.model.Contractor Cashier;

	private java.lang.String AnlCode;

	private java.lang.String TargetCode;

	private java.lang.String Base;

	private java.lang.String Comment;

	private java.util.Date GetDate;

	private java.lang.String GetDocument;

	private java.lang.String Comment1;

	// Constructors

	/** default constructor */
	public CashDocumentModel() {
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("Account.CashIn.Bookkeeper")
	public com.mg.merp.reference.model.Contractor getBookkeeper() {
		return this.Bookkeeper;
	}

	public void setBookkeeper(com.mg.merp.reference.model.Contractor Bookkeeper) {
		this.Bookkeeper = Bookkeeper;
	}

	/**
	 * 
	 */
	@DataItemName("Account.CashIn.Chief")
	public com.mg.merp.reference.model.Contractor getChief() {
		return this.Chief;
	}

	public void setChief(com.mg.merp.reference.model.Contractor Chief) {
		this.Chief = Chief;
	}

	/**
	 * 
	 */
	@DataItemName("Account.CashIn.Acc")
	public com.mg.merp.account.model.AccPlan getAcc() {
		return this.Acc;
	}

	public void setAcc(com.mg.merp.account.model.AccPlan AccPlan) {
		this.Acc = AccPlan;
	}

	@DataItemName("Account.CashIn.Company")
	public com.mg.merp.reference.model.Contractor getCompany() {
		return this.Company;
	}

	public void setCompany(com.mg.merp.reference.model.Contractor Company) {
		this.Company = Company;
	}

	@DataItemName("Account.CashIn.Cashier")
	public com.mg.merp.reference.model.Contractor getCashier() {
		return this.Cashier;
	}

	public void setCashier(com.mg.merp.reference.model.Contractor Cashier) {
		this.Cashier = Cashier;
	}

	/**
	 * 
	 */
	@DataItemName("Account.CashIn.AnlCode")
	public java.lang.String getAnlCode() {
		return this.AnlCode;
	}

	public void setAnlCode(java.lang.String AnlCode) {
		this.AnlCode = AnlCode;
	}

	/**
	 * 
	 */
	@DataItemName("Account.CashIn.TargetCode")
	public java.lang.String getTargetCode() {
		return this.TargetCode;
	}

	public void setTargetCode(java.lang.String TargetCode) {
		this.TargetCode = TargetCode;
	}

	/**
	 * 
	 */
	@DataItemName("Account.CashIn.Base")
	public java.lang.String getBase() {
		return this.Base;
	}

	public void setBase(java.lang.String Base) {
		this.Base = Base;
	}

	/**
	 * 
	 */
	@DataItemName("Account.CashIn.Comment")
	public java.lang.String getComment() {
		return this.Comment;
	}

	public void setComment(java.lang.String Comment) {
		this.Comment = Comment;
	}

	/**
	 * 
	 */
	@DataItemName("Account.CashOut.GetDate")
	public java.util.Date getGetDate() {
		return this.GetDate;
	}

	public void setGetDate(java.util.Date GetDate) {
		this.GetDate = GetDate;
	}

	/**
	 * 
	 */
	@DataItemName("Account.CashOut.GetDocument")
	public java.lang.String getGetDocument() {
		return this.GetDocument;
	}

	public void setGetDocument(java.lang.String GetDocument) {
		this.GetDocument = GetDocument;
	}

	/**
	 * 
	 */
	@DataItemName("Account.CashIn.Comment1")
	public java.lang.String getComment1() {
		return this.Comment1;
	}

	public void setComment1(java.lang.String Comment1) {
		this.Comment1 = Comment1;
	}

}