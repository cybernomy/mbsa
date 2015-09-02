/*
 s jn relf * FinOperation.java
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
package com.mg.merp.finance.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: FinOperation.java,v 1.10 2007/10/08 14:22:52 safonov Exp $
 */
public class FinOperation extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private Integer Id;

	private com.mg.merp.reference.model.Contractor From;

	private com.mg.merp.reference.model.Contractor To;

	private com.mg.merp.reference.model.CurrencyRateAuthority CurRateAuthority;

	private com.mg.merp.core.model.Folder Folder;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.reference.model.CurrencyRateType CurRateType;

	private com.mg.merp.reference.model.Currency Currency;

	private com.mg.merp.reference.model.Contractor Responsible;

	//private boolean Insert;

	private java.util.Date KeepDate;

	private java.lang.String Comment;

	private com.mg.merp.document.model.DocType BaseDocType;

	private java.lang.String BaseDocNumber;

	private java.util.Date BaseDocDate;

	private com.mg.merp.document.model.DocHead BaseDoc;

	private com.mg.merp.document.model.DocType ConfirmDocType;

	private java.lang.String ConfirmDocNumber;

	private java.util.Date ConfirmDocDate;

	private com.mg.merp.document.model.DocHead ConfirmDoc;

	private java.lang.Integer ContractId;

	private com.mg.merp.document.model.DocType ContractType;

	private java.lang.String ContractNumber;

	private java.util.Date ContractDate;

	private java.math.BigDecimal SumNat;

	private java.math.BigDecimal SumCur;

	private java.math.BigDecimal CurRate;

	private java.lang.Integer UserId;

	private boolean Planned;

	private java.util.Set<Specification> specifications;

	// Constructors

	/** default constructor */
	public FinOperation() {
	}

	/** constructor with id */
	public FinOperation(int Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("ID")
	public Integer getId() {
		return this.Id;
	}

	public void setId(Integer Id) {
		this.Id = Id;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Oper.From")
	public com.mg.merp.reference.model.Contractor getFrom() {
		return this.From;
	}

	public void setFrom(com.mg.merp.reference.model.Contractor from) {
		this.From = from;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Oper.To")
	public com.mg.merp.reference.model.Contractor getTo() {
		return this.To;
	}

	public void setTo(com.mg.merp.reference.model.Contractor to) {
		this.To = to;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Oper.CRateAuthority")
	public com.mg.merp.reference.model.CurrencyRateAuthority getCurRateAuthority() {
		return this.CurRateAuthority;
	}

	public void setCurRateAuthority(
			com.mg.merp.reference.model.CurrencyRateAuthority RefCurrencyRateAuthority) {
		this.CurRateAuthority = RefCurrencyRateAuthority;
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
	@DataItemName("Finance.Oper.CurRateType")
	public com.mg.merp.reference.model.CurrencyRateType getCurRateType() {
		return this.CurRateType;
	}

	public void setCurRateType(
			com.mg.merp.reference.model.CurrencyRateType RefCurrencyRateType) {
		this.CurRateType = RefCurrencyRateType;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Oper.Currency")
	public com.mg.merp.reference.model.Currency getCurrency() {
		return this.Currency;
	}

	public void setCurrency(com.mg.merp.reference.model.Currency Currency) {
		this.Currency = Currency;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Oper.Responsible")
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

	/*public boolean isInsert() {
		return this.Insert;
	}

	public void setInsert(boolean Insertsign) {
		this.Insert = Insertsign;
	}*/

	/**
	 * 
	 */
	@DataItemName("Finance.Oper.Keepdate")
	public java.util.Date getKeepDate() {
		return this.KeepDate;
	}

	public void setKeepDate(java.util.Date Keepdate) {
		this.KeepDate = Keepdate;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Oper.Comment")
	public java.lang.String getComment() {
		return this.Comment;
	}

	public void setComment(java.lang.String Comment) {
		this.Comment = Comment;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Oper.BaseType")
	public com.mg.merp.document.model.DocType getBaseDocType() {
		return this.BaseDocType;
	}

	public void setBaseDocType(com.mg.merp.document.model.DocType Docbasetype) {
		this.BaseDocType = Docbasetype;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Oper.BaseNumber")
	public java.lang.String getBaseDocNumber() {
		return this.BaseDocNumber;
	}

	public void setBaseDocNumber(java.lang.String Docbasenumber) {
		this.BaseDocNumber = Docbasenumber;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Oper.BaseDate")
	public java.util.Date getBaseDocDate() {
		return this.BaseDocDate;
	}

	public void setBaseDocDate(java.util.Date Docbasedate) {
		this.BaseDocDate = Docbasedate;
	}

	/**
	 * 
	 */

	public com.mg.merp.document.model.DocHead getBaseDoc() {
		return this.BaseDoc;
	}

	public void setBaseDoc(com.mg.merp.document.model.DocHead DocbaseId) {
		this.BaseDoc = DocbaseId;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Oper.Type")
	public com.mg.merp.document.model.DocType getConfirmDocType() {
		return this.ConfirmDocType;
	}

	public void setConfirmDocType(com.mg.merp.document.model.DocType Doctype) {
		this.ConfirmDocType = Doctype;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Oper.Number")
	public java.lang.String getConfirmDocNumber() {
		return this.ConfirmDocNumber;
	}

	public void setConfirmDocNumber(java.lang.String Docnumber) {
		this.ConfirmDocNumber = Docnumber;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Oper.Date")
	public java.util.Date getConfirmDocDate() {
		return this.ConfirmDocDate;
	}

	public void setConfirmDocDate(java.util.Date Docdate) {
		this.ConfirmDocDate = Docdate;
	}

	/**
	 * 
	 */

	public com.mg.merp.document.model.DocHead getConfirmDoc() {
		return this.ConfirmDoc;
	}

	public void setConfirmDoc(com.mg.merp.document.model.DocHead DocId) {
		this.ConfirmDoc = DocId;
	}

	/**
	 * 
	 */

	public java.lang.Integer getContractId() {
		return this.ContractId;
	}

	public void setContractId(java.lang.Integer ContractId) {
		this.ContractId = ContractId;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Oper.ContractType")
	public com.mg.merp.document.model.DocType getContractType() {
		return this.ContractType;
	}

	public void setContractType(com.mg.merp.document.model.DocType Contracttype) {
		this.ContractType = Contracttype;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Oper.ContractNumber")
	public java.lang.String getContractNumber() {
		return this.ContractNumber;
	}

	public void setContractNumber(java.lang.String Contractnumber) {
		this.ContractNumber = Contractnumber;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Oper.ContractDate")
	public java.util.Date getContractDate() {
		return this.ContractDate;
	}

	public void setContractDate(java.util.Date Contractdate) {
		this.ContractDate = Contractdate;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Oper.SumNat")
	public java.math.BigDecimal getSumNat() {
		return this.SumNat;
	}

	public void setSumNat(java.math.BigDecimal Sumnat) {
		this.SumNat = Sumnat;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Oper.SumCur")
	public java.math.BigDecimal getSumCur() {
		return this.SumCur;
	}

	public void setSumCur(java.math.BigDecimal Sumcur) {
		this.SumCur = Sumcur;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Oper.Rate")
	public java.math.BigDecimal getCurRate() {
		return this.CurRate;
	}

	public void setCurRate(java.math.BigDecimal Currate) {
		this.CurRate = Currate;
	}

	/**
	 * 
	 */

	public java.lang.Integer getUserId() {
		return this.UserId;
	}

	public void setUserId(java.lang.Integer UserId) {
		this.UserId = UserId;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Oper.Planned")
	public boolean getPlanned() {
		return this.Planned;
	}

	public void setPlanned(boolean Planned) {
		this.Planned = Planned;
	}

	/**
	 * 
	 */

	public java.util.Set<Specification> getSpecifications() {
		return this.specifications;
	}

	public void setSpecifications(java.util.Set<Specification> specifications) {
		this.specifications = specifications;
	}

}