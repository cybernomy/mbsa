/*
 * BuyBook.java
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
 * @version $Id: BuyBook.java,v 1.5 2006/11/02 15:40:06 safonov Exp $
 */
public class BuyBook extends com.mg.framework.service.PersistentObjectHibernate
		implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.reference.model.Contractor Provider;

	private com.mg.merp.document.model.DocHead DocHead;

	private com.mg.merp.core.model.Folder Folder;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.document.model.DocType DocType;

	private com.mg.merp.reference.model.Contractor OrgUnit;

	private java.lang.String DocNumber;

	private java.util.Date DocDate;

	private java.util.Date InsertDate;

	private java.util.Date InDate;

	private java.util.Date StockDate;

	private java.util.Date PayDate;

	private java.math.BigDecimal TotalSum;

	private java.math.BigDecimal SumWithoutNds1;

	private java.math.BigDecimal SumWithoutNds2;

	private java.math.BigDecimal Nds1Sum;

	private java.math.BigDecimal Nds2Sum;

	private java.math.BigDecimal NotTaxableSum;

	private java.math.BigDecimal NspSum;

	private java.math.BigDecimal SumWithoutNds3;

	private java.math.BigDecimal Nds3Sum;

	private boolean Approved;

	// Constructors

	/** default constructor */
	public BuyBook() {
	}

	/** constructor with id */
	public BuyBook(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("ID")
	public java.lang.Integer getId() {
		return this.Id;
	}

	public void setId(java.lang.Integer Id) {
		this.Id = Id;
	}

	/**
	 * 
	 */
	@DataItemName("Account.BuyBook.Provider")
	public com.mg.merp.reference.model.Contractor getProvider() {
		return this.Provider;
	}

	public void setProvider(com.mg.merp.reference.model.Contractor Provider) {
		this.Provider = Provider;
	}

	/**
	 * 
	 */

	public com.mg.merp.document.model.DocHead getDocHead() {
		return this.DocHead;
	}

	public void setDocHead(com.mg.merp.document.model.DocHead DocHead) {
		this.DocHead = DocHead;
	}

	/**
	 * 
	 */

	public com.mg.merp.core.model.Folder getFolder() {
		return this.Folder;
	}

	public void setFolder(com.mg.merp.core.model.Folder Folder) {
		this.Folder = Folder;
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
	@DataItemName("Account.BuyBook.DocType")
	public com.mg.merp.document.model.DocType getDocType() {
		return this.DocType;
	}

	public void setDocType(com.mg.merp.document.model.DocType TypeDoc) {
		this.DocType = TypeDoc;
	}

	/**
	 * 
	 */
	@DataItemName("Account.BuyBook.OrgUnit")
	public com.mg.merp.reference.model.Contractor getOrgUnit() {
		return this.OrgUnit;
	}

	public void setOrgUnit(com.mg.merp.reference.model.Contractor OrgUnit) {
		this.OrgUnit = OrgUnit;
	}

	/**
	 * 
	 */
	@DataItemName("Account.BuyBook.DocNumber")
	public java.lang.String getDocNumber() {
		return this.DocNumber;
	}

	public void setDocNumber(java.lang.String DocNumber) {
		this.DocNumber = DocNumber;
	}

	/**
	 * 
	 */
	@DataItemName("Account.BuyBook.DocDate")
	public java.util.Date getDocDate() {
		return this.DocDate;
	}

	public void setDocDate(java.util.Date DocDate) {
		this.DocDate = DocDate;
	}

	/**
	 * 
	 */
	@DataItemName("Account.BuyBook.InsertDate")
	public java.util.Date getInsertDate() {
		return this.InsertDate;
	}

	public void setInsertDate(java.util.Date InsertDate) {
		this.InsertDate = InsertDate;
	}

	/**
	 * 
	 */
	@DataItemName("Account.BuyBook.InDate")
	public java.util.Date getInDate() {
		return this.InDate;
	}

	public void setInDate(java.util.Date InDate) {
		this.InDate = InDate;
	}

	/**
	 * 
	 */
	@DataItemName("Account.BuyBook.StockDate")
	public java.util.Date getStockDate() {
		return this.StockDate;
	}

	public void setStockDate(java.util.Date StockDate) {
		this.StockDate = StockDate;
	}

	/**
	 * 
	 */
	@DataItemName("Account.BuyBook.PayDate")
	public java.util.Date getPayDate() {
		return this.PayDate;
	}

	public void setPayDate(java.util.Date PayDate) {
		this.PayDate = PayDate;
	}

	/**
	 * 
	 */
	@DataItemName("Account.BuyBook.TotalSum")
	public java.math.BigDecimal getTotalSum() {
		return this.TotalSum;
	}

	public void setTotalSum(java.math.BigDecimal TotalSum) {
		this.TotalSum = TotalSum;
	}

	/**
	 * 
	 */
	@DataItemName("Account.BuyBook.SumWithoutNds1")
	public java.math.BigDecimal getSumWithoutNds1() {
		return this.SumWithoutNds1;
	}

	public void setSumWithoutNds1(java.math.BigDecimal SumWithoutNds1) {
		this.SumWithoutNds1 = SumWithoutNds1;
	}

	/**
	 * 
	 */
	@DataItemName("Account.BuyBook.SumWithoutNds2")
	public java.math.BigDecimal getSumWithoutNds2() {
		return this.SumWithoutNds2;
	}

	public void setSumWithoutNds2(java.math.BigDecimal SumWithoutNds2) {
		this.SumWithoutNds2 = SumWithoutNds2;
	}

	/**
	 * 
	 */
	@DataItemName("Account.BuyBook.Nds1Sum")
	public java.math.BigDecimal getNds1Sum() {
		return this.Nds1Sum;
	}

	public void setNds1Sum(java.math.BigDecimal Nds1Sum) {
		this.Nds1Sum = Nds1Sum;
	}

	/**
	 * 
	 */
	@DataItemName("Account.BuyBook.Nds2Sum")
	public java.math.BigDecimal getNds2Sum() {
		return this.Nds2Sum;
	}

	public void setNds2Sum(java.math.BigDecimal Nds2Sum) {
		this.Nds2Sum = Nds2Sum;
	}

	/**
	 * 
	 */
	@DataItemName("Account.BuyBook.NotTaxableSum")
	public java.math.BigDecimal getNotTaxableSum() {
		return this.NotTaxableSum;
	}

	public void setNotTaxableSum(java.math.BigDecimal NotTaxableSum) {
		this.NotTaxableSum = NotTaxableSum;
	}

	/**
	 * 
	 */
	@DataItemName("Account.BuyBook.NspSum")
	public java.math.BigDecimal getNspSum() {
		return this.NspSum;
	}

	public void setNspSum(java.math.BigDecimal NspSum) {
		this.NspSum = NspSum;
	}

	/**
	 * 
	 */
	@DataItemName("Account.BuyBook.SumWithoutNds3")
	public java.math.BigDecimal getSumWithoutNds3() {
		return this.SumWithoutNds3;
	}

	public void setSumWithoutNds3(java.math.BigDecimal SumWithoutNds3) {
		this.SumWithoutNds3 = SumWithoutNds3;
	}

	/**
	 * 
	 */
	@DataItemName("Account.BuyBook.Nds3Sum")
	public java.math.BigDecimal getNds3Sum() {
		return this.Nds3Sum;
	}

	public void setNds3Sum(java.math.BigDecimal Nds3Sum) {
		this.Nds3Sum = Nds3Sum;
	}

	/**
	 * 
	 */
	@DataItemName("Account.BuyBook.Approved")
	public boolean getApproved() {
		return this.Approved;
	}

	public void setApproved(boolean Approved) {
		this.Approved = Approved;
	}

}