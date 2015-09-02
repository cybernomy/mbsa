/*
 * RemnDbKt.java
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
 * @version $Id: RemnDbKt.java,v 1.11 2007/02/20 08:34:20 sharapov Exp $
 */
public class RemnDbKt extends RemnAnl implements java.io.Serializable {

	// Fields

	private com.mg.merp.reference.model.Contractor Contractor;

	private java.util.Date RiseDebts;

	private java.util.Date ClearDebts;

	private java.lang.String Comment;

	private com.mg.merp.document.model.DocType DocBaseType;

	private java.lang.String DocBaseNumber;

	private java.util.Date DocBaseDate;

	private com.mg.merp.document.model.DocType DocType;

	private java.lang.String DocNumber;

	private java.util.Date DocDate;

	// Constructors

	/** default constructor */
	public RemnDbKt() {
	}

	/** constructor with id */
	public RemnDbKt(int Id) {
		super(Id);
	}

	// Property accessors
	/**
	 * 
	 */

	/*@DataItemName("Account.RemnDbKt.Contractor")*/
	public com.mg.merp.reference.model.Contractor getContractor() {
		return this.Contractor;
	}

	public void setContractor(com.mg.merp.reference.model.Contractor Contractor) {
		this.Contractor = Contractor;
	}

	/**
	 * 
	 */

	public java.util.Date getRiseDebts() {
		return this.RiseDebts;
	}

	public void setRiseDebts(java.util.Date Risedebts) {
		this.RiseDebts = Risedebts;
	}

	/**
	 * 
	 */

	public java.util.Date getClearDebts() {
		return this.ClearDebts;
	}

	public void setClearDebts(java.util.Date Cleardebts) {
		this.ClearDebts = Cleardebts;
	}

	/**
	 * 
	 */
	@DataItemName("Account.RemnDbKt.Comment")
	public java.lang.String getComment() {
		return this.Comment;
	}

	public void setComment(java.lang.String Comment) {
		this.Comment = Comment;
	}

	/**
	 * 
	 */
	@DataItemName("Document.DocTypeModel")
	public com.mg.merp.document.model.DocType getDocBaseType() {
		return this.DocBaseType;
	}

	public void setDocBaseType(com.mg.merp.document.model.DocType Docbasetype) {
		this.DocBaseType = Docbasetype;
	}

	/**
	 * 
	 */
	@DataItemName("Document.BaseDocNumber")
	public java.lang.String getDocBaseNumber() {
		return this.DocBaseNumber;
	}

	public void setDocBaseNumber(java.lang.String Docbasenumber) {
		this.DocBaseNumber = Docbasenumber;
	}

	/**
	 * 
	 */
	@DataItemName("Document.BaseDocDate")
	public java.util.Date getDocBaseDate() {
		return this.DocBaseDate;
	}

	public void setDocBaseDate(java.util.Date Docbasedate) {
		this.DocBaseDate = Docbasedate;
	}

	/**
	 * 
	 */
	@DataItemName("Document.DocTypeModel")
	public com.mg.merp.document.model.DocType getDocType() {
		return this.DocType;
	}

	public void setDocType(com.mg.merp.document.model.DocType Doctype) {
		this.DocType = Doctype;
	}

	/**
	 * 
	 */
	@DataItemName("Document.ContractNumber")
	public java.lang.String getDocNumber() {
		return this.DocNumber;
	}

	public void setDocNumber(java.lang.String Docnumber) {
		this.DocNumber = Docnumber;
	}

	/**
	 * 
	 */
	@DataItemName("Document.ContractDate")
	public java.util.Date getDocDate() {
		return this.DocDate;
	}

	public void setDocDate(java.util.Date Docdate) {
		this.DocDate = Docdate;
	}
}