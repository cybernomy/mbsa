/*
 * DocHead.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 *
 */
package com.mg.merp.document.model;

import org.hibernate.bytecode.javassist.FieldHandler;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Объектная модель документов
 * 
 * @author hbm2java
 * @version $Id: DocHead.java,v 1.13 2008/06/07 07:58:35 alikaev Exp $
 */
@DataItemName("Document.DocHead")
public class DocHead extends com.mg.framework.service.PersistentObjectHibernate
		implements java.io.Serializable, org.hibernate.bytecode.javassist.FieldHandled {

	public final static int DOCNUMBER_LENGTH = 20;
	
	private FieldHandler fieldHandler;
	
	// Fields

	private java.lang.Integer Id;

	private java.util.Date SysVersion;
	
	private com.mg.merp.core.model.SysCompany sysCompany;
	
	private com.mg.merp.reference.model.Contractor Through;

	private com.mg.merp.reference.model.Contractor From;

	private com.mg.merp.reference.model.CurrencyRateAuthority CurrencyRateAuthority;

	private com.mg.merp.reference.model.Contractor SrcMol;

	private com.mg.merp.reference.model.PriceType PriceType;

	private com.mg.merp.core.model.Folder DiscountFolder;

	private com.mg.merp.core.model.Folder Folder;

	private com.mg.merp.document.model.DocType DocType;

	private com.mg.merp.document.model.DocHead Contract;

	private com.mg.merp.reference.model.Currency Currency;

	private com.mg.merp.reference.model.Contractor SrcStock;

	private com.mg.merp.document.model.DocType ContractType;

	private com.mg.merp.reference.model.Contractor To;

	private com.mg.merp.reference.model.Contractor DstMol;

	private com.mg.merp.document.model.DocHead BaseDocument;

//	private com.mg.merp.lbschedule.model.Schedule LsSchedule;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.reference.model.Contractor DstStock;

	private com.mg.merp.reference.model.CalcTaxesKind CalcTaxesKind;

	private com.mg.merp.document.model.DocType BaseDocType;

	private com.mg.merp.reference.model.CurrencyRateType CurrencyRateType;

	private com.mg.merp.reference.model.PriceListHead PriceList;

	private com.mg.merp.document.model.DocSection DocSection;

	private java.lang.String DocNumber;

	private java.util.Date DocDate;

	private java.math.BigDecimal CurCource;

	private java.math.BigDecimal SumCur;

	private java.math.BigDecimal SumNat;

	private java.lang.String BaseDocNumber;

	private java.util.Date BaseDocDate;

	private java.lang.String ContractNumber;

	private java.util.Date ContractDate;

	private java.math.BigDecimal Weight;

	private java.math.BigDecimal Volume;

	private java.lang.Short Requester;

	private byte[] original;
	
	private java.lang.String UNID;
	
	private boolean isManualDocNumber;

	private boolean isAdjusted;
	
	private String description;
	
	// Constructors

	/** default constructor */
	public DocHead() {
	}

	/** constructor with id */
	public DocHead(java.lang.Integer Id) {
		this.Id = Id;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.bytecode.javassist.FieldHandled#getFieldHandler()
	 */
	public FieldHandler getFieldHandler() {
		return fieldHandler;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.bytecode.javassist.FieldHandled#setFieldHandler(org.hibernate.bytecode.javassist.FieldHandler)
	 */
	public void setFieldHandler(FieldHandler handler) {
		this.fieldHandler = handler;
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
	 * @return the sysVersion
	 */
	public java.util.Date getSysVersion() {
		return SysVersion;
	}

	/**
	 * @param sysVersion the sysVersion to set
	 */
	public void setSysVersion(java.util.Date sysVersion) {
		SysVersion = sysVersion;
	}

	/**
	 * 
	 */
	@DataItemName("Document.Through")
	public com.mg.merp.reference.model.Contractor getThrough() {
		return this.Through;
	}

	public void setThrough(com.mg.merp.reference.model.Contractor Contractor) {
		this.Through = Contractor;
	}

	/**
	 * 
	 */
	@DataItemName("Document.From")
	public com.mg.merp.reference.model.Contractor getFrom() {
		return this.From;
	}

	public void setFrom(com.mg.merp.reference.model.Contractor Contractor_1) {
		this.From = Contractor_1;
	}

	/**
	 * 
	 */
	@DataItemName("Document.CurrencyRateAuthority")
	public com.mg.merp.reference.model.CurrencyRateAuthority getCurrencyRateAuthority() {
		return this.CurrencyRateAuthority;
	}

	public void setCurrencyRateAuthority(
			com.mg.merp.reference.model.CurrencyRateAuthority RefCurrencyRateAuthority) {
		this.CurrencyRateAuthority = RefCurrencyRateAuthority;
	}

	/**
	 * 
	 */
	@DataItemName("Document.SrcMol")
	public com.mg.merp.reference.model.Contractor getSrcMol() {
		return this.SrcMol;
	}

	public void setSrcMol(com.mg.merp.reference.model.Contractor Contractor_2) {
		this.SrcMol = Contractor_2;
	}

	/**
	 * 
	 */	
	@DataItemName("Document.PriceType")	
	public com.mg.merp.reference.model.PriceType getPriceType() {
		return this.PriceType;
	}

	public void setPriceType(com.mg.merp.reference.model.PriceType Pricetype) {
		this.PriceType = Pricetype;
	}

	/**
	 * 
	 */
	@DataItemName("Document.DiscountFolder")
	public com.mg.merp.core.model.Folder getDiscountFolder() {
		return this.DiscountFolder;
	}

	public void setDiscountFolder(com.mg.merp.core.model.Folder Folder) {
		this.DiscountFolder = Folder;
	}

	/**
	 * 
	 */

	public com.mg.merp.core.model.Folder getFolder() {
		return this.Folder;
	}

	public void setFolder(com.mg.merp.core.model.Folder Folder_1) {
		this.Folder = Folder_1;
	}

	/**
	 * 
	 */
	@DataItemName("Document.DocType")
	public com.mg.merp.document.model.DocType getDocType() {
		return this.DocType;
	}

	public void setDocType(com.mg.merp.document.model.DocType Typedoc) {
		this.DocType = Typedoc;
	}

	/**
	 * 
	 */

	public com.mg.merp.document.model.DocHead getContract() {
		return this.Contract;
	}

	public void setContract(com.mg.merp.document.model.DocHead Dochead) {
		this.Contract = Dochead;
	}

	/**
	 * 
	 */
	public com.mg.merp.reference.model.Currency getCurrency() {
		return this.Currency;
	}

	public void setCurrency(com.mg.merp.reference.model.Currency Currency) {
		this.Currency = Currency;
	}

	/**
	 * 
	 */
	@DataItemName("Document.StockSrc")
	public com.mg.merp.reference.model.Contractor getSrcStock() {
		return this.SrcStock;
	}

	public void setSrcStock(com.mg.merp.reference.model.Contractor Contractor_3) {
		this.SrcStock = Contractor_3;
	}

	/**
	 * 
	 */
	@DataItemName("Document.ContractType")
	public com.mg.merp.document.model.DocType getContractType() {
		return this.ContractType;
	}

	public void setContractType(com.mg.merp.document.model.DocType Typedoc_1) {
		this.ContractType = Typedoc_1;
	}

	/**
	 * 
	 */
	@DataItemName("Document.To")
	public com.mg.merp.reference.model.Contractor getTo() {
		return this.To;
	}

	public void setTo(com.mg.merp.reference.model.Contractor Contractor_4) {
		this.To = Contractor_4;
	}

	/**
	 * 
	 */
	@DataItemName("Document.DstMol")
	public com.mg.merp.reference.model.Contractor getDstMol() {
		return this.DstMol;
	}

	public void setDstMol(com.mg.merp.reference.model.Contractor Contractor_5) {
		this.DstMol = Contractor_5;
	}

	/**
	 * 
	 */

	public com.mg.merp.document.model.DocHead getBaseDocument() {
		return this.BaseDocument;
	}

	public void setBaseDocument(com.mg.merp.document.model.DocHead Dochead_1) {
		this.BaseDocument = Dochead_1;
	}

	/**
	 * 
	 */

//	public com.mg.merp.lbschedule.model.Schedule getLsSchedule() {
//		return this.LsSchedule;
//	}
//
//	public void setLsSchedule(com.mg.merp.lbschedule.model.Schedule LsSchedule) {
//		this.LsSchedule = LsSchedule;
//	}

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
	@DataItemName("Document.DstStock")
	public com.mg.merp.reference.model.Contractor getDstStock() {
		return this.DstStock;
	}

	public void setDstStock(com.mg.merp.reference.model.Contractor Contractor_6) {
		this.DstStock = Contractor_6;
	}

	/**
	 * 
	 */	
	public com.mg.merp.reference.model.CalcTaxesKind getCalcTaxesKind() {
		return this.CalcTaxesKind;
	}

	public void setCalcTaxesKind(
			com.mg.merp.reference.model.CalcTaxesKind Calctaxeskind) {
		this.CalcTaxesKind = Calctaxeskind;
	}

	/**
	 * 
	 */
	@DataItemName("Document.BaseDocType")
	public com.mg.merp.document.model.DocType getBaseDocType() {
		return this.BaseDocType;
	}

	public void setBaseDocType(com.mg.merp.document.model.DocType Typedoc_2) {
		this.BaseDocType = Typedoc_2;
	}

	/**
	 * 
	 */
	@DataItemName("Document.CurrencyRateType")
	public com.mg.merp.reference.model.CurrencyRateType getCurrencyRateType() {
		return this.CurrencyRateType;
	}

	public void setCurrencyRateType(
			com.mg.merp.reference.model.CurrencyRateType RefCurrencyRateType) {
		this.CurrencyRateType = RefCurrencyRateType;
	}

	/**
	 * 
	 */	
	public com.mg.merp.reference.model.PriceListHead getPriceList() {
		return this.PriceList;
	}

	public void setPriceList(
			com.mg.merp.reference.model.PriceListHead Pricelisthead) {
		this.PriceList = Pricelisthead;
	}

	/**
	 * 
	 */

	public com.mg.merp.document.model.DocSection getDocSection() {
		return this.DocSection;
	}

	public void setDocSection(com.mg.merp.document.model.DocSection Docsection) {
		this.DocSection = Docsection;
	}

	/**
	 * 
	 */
	@DataItemName("Document.DocNumber")
	public java.lang.String getDocNumber() {
		return this.DocNumber;
	}

	public void setDocNumber(java.lang.String Docnumber) {
		this.DocNumber = Docnumber;
	}

	/**
	 * 
	 */
	@DataItemName("Document.DocDate")
	public java.util.Date getDocDate() {
		return this.DocDate;
	}

	public void setDocDate(java.util.Date Docdate) {
		this.DocDate = Docdate;
	}

	/**
	 * 
	 */
	@DataItemName("Document.CurCource")
	public java.math.BigDecimal getCurCource() {
		return this.CurCource;
	}

	public void setCurCource(java.math.BigDecimal Curcource) {
		this.CurCource = Curcource;
	}

	/**
	 * 
	 */
	@DataItemName("Document.SumCur")
	public java.math.BigDecimal getSumCur() {
		return this.SumCur;
	}

	public void setSumCur(java.math.BigDecimal Summacur) {
		this.SumCur = Summacur;
	}

	/**
	 * 
	 */
	@DataItemName("Document.SumNat")
	public java.math.BigDecimal getSumNat() {
		return this.SumNat;
	}

	public void setSumNat(java.math.BigDecimal Summanat) {
		this.SumNat = Summanat;
	}

	/**
	 * 
	 */
	@DataItemName("Document.BaseDocNumber")
	public java.lang.String getBaseDocNumber() {
		return this.BaseDocNumber;
	}

	public void setBaseDocNumber(java.lang.String Basedocnumber) {
		this.BaseDocNumber = Basedocnumber;
	}

	/**
	 * 
	 */
	@DataItemName("Document.BaseDocDate")
	public java.util.Date getBaseDocDate() {
		return this.BaseDocDate;
	}

	public void setBaseDocDate(java.util.Date Basedocdate) {
		this.BaseDocDate = Basedocdate;
	}

	/**
	 * 
	 */
	@DataItemName("Document.ContractNumber")
	public java.lang.String getContractNumber() {
		return this.ContractNumber;
	}

	public void setContractNumber(java.lang.String Contractnumber) {
		this.ContractNumber = Contractnumber;
	}

	/**
	 * 
	 */
	@DataItemName("Document.ContractDate")
	public java.util.Date getContractDate() {
		return this.ContractDate;
	}

	public void setContractDate(java.util.Date Contractdate) {
		this.ContractDate = Contractdate;
	}

	/**
	 * 
	 */
	@DataItemName("Document.Weight")
	public java.math.BigDecimal getWeight() {
		return this.Weight;
	}

	public void setWeight(java.math.BigDecimal Weight) {
		this.Weight = Weight;
	}

	/**
	 * 
	 */
	@DataItemName("Document.Volume")
	public java.math.BigDecimal getVolume() {
		return this.Volume;
	}

	public void setVolume(java.math.BigDecimal Volume) {
		this.Volume = Volume;
	}

	/**
	 * 
	 */

	public java.lang.Short getRequester() {
		return this.Requester;
	}

	public void setRequester(java.lang.Short Requester) {
		this.Requester = Requester;
	}

	/**
	 * 
	 */
	public byte[] getOriginal () {
		return fieldHandler != null ? (byte[]) fieldHandler.readObject(this, "Original", this.original) : null;
	}
	
	public void setOriginal (byte[] original) {
		if (fieldHandler != null)
			fieldHandler.writeObject(this, "Original", this.original, original);
		this.original = original;
	}

	/**
	 * 
	 */

	public java.lang.String getUNID() {
		return this.UNID;
	}

	public void setUNID(java.lang.String Unid) {
		this.UNID = Unid;
	}

	/**
	 * признак корректности сущности, если <code>true</code> то система не автоматически корректировать
	 * сущность при внесении в хранилище
	 * 
	 * @return the isAdjusted
	 */
	public boolean isAdjusted() {
		return isAdjusted;
	}

	/**
	 * @see #isAdjusted()
	 * @param isAdjusted the isAdjusted to set
	 */
	public void setAdjusted(boolean isAdjusted) {
		this.isAdjusted = isAdjusted;
	}

	/**
	 * признак ручной установки номера документа, если <code>true</code> то номер не будет формироваться
	 * автоматически
	 * 
	 * @return the isManualDocNumber
	 */
	public boolean isManualDocNumber() {
		return isManualDocNumber;
	}

	/**
	 * @see #isManualDocNumber()
	 * @param isManualDocNumber the isManualDocNumber to set
	 */
	public void setManualDocNumber(boolean isDocNumberManual) {
		this.isManualDocNumber = isDocNumberManual;
	}

	/**
	 * @return the sysCompany
	 */
	public com.mg.merp.core.model.SysCompany getSysCompany() {
		return sysCompany;
	}

	/**
	 * @param sysCompany the sysCompany to set
	 */
	public void setSysCompany(com.mg.merp.core.model.SysCompany sysCompany) {
		this.sysCompany = sysCompany;
	}

	/**
	 * @return the description
	 */
	@DataItemName("Document.Description")
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}