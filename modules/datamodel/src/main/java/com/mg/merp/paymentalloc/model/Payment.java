/*
 * Payment.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.paymentalloc.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Запись журнала платежей"
 * 
 * @author Artem V. Sharapov
 * @version $Id: Payment.java,v 1.7 2007/06/04 05:18:36 sharapov Exp $
 */
public class Payment extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.reference.model.Contractor FromUnit;
	private com.mg.merp.reference.model.Contractor ToUnit;

	private com.mg.merp.reference.model.Contractor ContractorFrom;
	private com.mg.merp.reference.model.Contractor ContractorTo;
	
	private com.mg.merp.document.model.DocHead DocHead;
	private com.mg.merp.document.model.DocType DocType;
	private java.lang.String DocNumber;
	private java.util.Date DocDate;
	
	private com.mg.merp.document.model.DocHead BaseDoc;
	private com.mg.merp.document.model.DocType BaseDocType;
	private java.lang.String BaseDocNumber;
	private java.util.Date BaseDocDate;

	private com.mg.merp.document.model.DocHead Contract;
	private com.mg.merp.document.model.DocType ContractType;
	private java.lang.String ContractNumber;
	private java.util.Date ContractDate;

	private com.mg.merp.reference.model.Currency CurCode;
	private com.mg.merp.reference.model.CurrencyRateType CurRateType;
	private com.mg.merp.reference.model.CurrencyRateAuthority CurRateAuthority;
		
	private java.math.BigDecimal CurRate;
	
	private java.math.BigDecimal SumCur;
	private java.math.BigDecimal SumNat;

	private java.math.BigDecimal AllocSumCur;
	private java.math.BigDecimal AllocSumNat;

	private boolean Planned;
	private java.util.Date PDate;
	private java.lang.String Name;

	private java.lang.String Description;
	private java.lang.String Comments;

	private boolean IsModel;
	private java.lang.String ModelName;
	private com.mg.merp.core.model.Folder DestFolder;
	
	private com.mg.merp.core.model.Folder Folder;
	
	private com.mg.merp.core.model.SysClient SysClient;
	private java.util.Set<TransactHead> transactHeads;

	// Constructors

	/** default constructor */
	public Payment() {
	}

	/** constructor with id */
	public Payment(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("ID") //$NON-NLS-1$
	public java.lang.Integer getId() {
		return this.Id;
	}

	public void setId(java.lang.Integer Id) {
		this.Id = Id;
	}

	/**
	 * 
	 */
	public com.mg.merp.reference.model.Contractor getFromUnit() {
		return this.FromUnit;
	}

	public void setFromUnit(com.mg.merp.reference.model.Contractor Contractor) {
		this.FromUnit = Contractor;
	}

	/**
	 * 
	 */
	@DataItemName("PaymentAlloc.Payment.ContractorFrom") //$NON-NLS-1$
	public com.mg.merp.reference.model.Contractor getContractorFrom() {
		return this.ContractorFrom;
	}

	public void setContractorFrom(
			com.mg.merp.reference.model.Contractor Contractor_1) {
		this.ContractorFrom = Contractor_1;
	}

	/**
	 * 
	 */
	public com.mg.merp.document.model.DocHead getBaseDoc() {
		return this.BaseDoc;
	}

	public void setBaseDoc(com.mg.merp.document.model.DocHead Dochead) {
		this.BaseDoc = Dochead;
	}

	/**
	 * 
	 */
	@DataItemName("PaymentAlloc.Payment.ContractorTo") //$NON-NLS-1$
	public com.mg.merp.reference.model.Contractor getContractorTo() {
		return this.ContractorTo;
	}

	public void setContractorTo(
			com.mg.merp.reference.model.Contractor Contractor_2) {
		this.ContractorTo = Contractor_2;
	}

	/**
	 * 
	 */	
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
	public com.mg.merp.document.model.DocHead getDocHead() {
		return this.DocHead;
	}

	public void setDocHead(com.mg.merp.document.model.DocHead Dochead_1) {
		this.DocHead = Dochead_1;
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
	public com.mg.merp.reference.model.CurrencyRateType getCurRateType() {
		return this.CurRateType;
	}

	public void setCurRateType(com.mg.merp.reference.model.CurrencyRateType RefCurrencyRateType) {
		this.CurRateType = RefCurrencyRateType;
	}

	/**
	 * 
	 */
	public com.mg.merp.document.model.DocHead getContract() {
		return this.Contract;
	}

	public void setContract(com.mg.merp.document.model.DocHead Dochead_2) {
		this.Contract = Dochead_2;
	}

	/**
	 * 
	 */	
	public com.mg.merp.reference.model.Currency getCurCode() {
		return this.CurCode;
	}

	public void setCurCode(com.mg.merp.reference.model.Currency Currency) {
		this.CurCode = Currency;
	}

	/**
	 * 
	 */
	public com.mg.merp.reference.model.Contractor getToUnit() {
		return this.ToUnit;
	}

	public void setToUnit(com.mg.merp.reference.model.Contractor Contractor_3) {
		this.ToUnit = Contractor_3;
	}

	/**
	 * 
	 */
	@DataItemName("PaymentAlloc.Payment.Planned") //$NON-NLS-1$
	public boolean getPlanned() {
		return this.Planned;
	}

	public void setPlanned(boolean Planned) {
		this.Planned = Planned;
	}

	/**
	 * 
	 */
	@DataItemName("PaymentAlloc.Payment.PDate") //$NON-NLS-1$
	public java.util.Date getPDate() {
		return this.PDate;
	}

	public void setPDate(java.util.Date Pdate) {
		this.PDate = Pdate;
	}

	/**
	 * 
	 */
	@DataItemName("PaymentAlloc.Payment.Name") //$NON-NLS-1$
	public java.lang.String getName() {
		return this.Name;
	}

	public void setName(java.lang.String Name) {
		this.Name = Name;
	}

	/**
	 * 
	 */
	@DataItemName("PaymentAlloc.Payment.CurRate") //$NON-NLS-1$
	public java.math.BigDecimal getCurRate() {
		return this.CurRate;
	}

	public void setCurRate(java.math.BigDecimal Currate) {
		this.CurRate = Currate;
	}

	/**
	 * 
	 */
	@DataItemName("PaymentAlloc.Payment.SumCur") //$NON-NLS-1$
	public java.math.BigDecimal getSumCur() {
		return this.SumCur;
	}

	public void setSumCur(java.math.BigDecimal Sumcur) {
		this.SumCur = Sumcur;
	}

	/**
	 * 
	 */
	@DataItemName("PaymentAlloc.Payment.SumNat") //$NON-NLS-1$
	public java.math.BigDecimal getSumNat() {
		return this.SumNat;
	}

	public void setSumNat(java.math.BigDecimal Sumnat) {
		this.SumNat = Sumnat;
	}

	/**
	 * 
	 */
	public java.math.BigDecimal getAllocSumCur() {
		return this.AllocSumCur;
	}

	public void setAllocSumCur(java.math.BigDecimal Allocsumcur) {
		this.AllocSumCur = Allocsumcur;
	}

	/**
	 * 
	 */
	public java.math.BigDecimal getAllocSumNat() {
		return this.AllocSumNat;
	}

	public void setAllocSumNat(java.math.BigDecimal Allocsumnat) {
		this.AllocSumNat = Allocsumnat;
	}

	/**
	 * 
	 */
	@DataItemName("PaymentAlloc.Payment.Number") //$NON-NLS-1$
	public java.lang.String getDocNumber() {
		return this.DocNumber;
	}

	public void setDocNumber(java.lang.String Docnumber) {
		this.DocNumber = Docnumber;
	}

	/**
	 * 
	 */
	@DataItemName("PaymentAlloc.Payment.DocDate") //$NON-NLS-1$
	public java.util.Date getDocDate() {
		return this.DocDate;
	}

	public void setDocDate(java.util.Date Docdate) {
		this.DocDate = Docdate;
	}

	/**
	 * 
	 */
	@DataItemName("PaymentAlloc.Payment.BaseDocNumber") //$NON-NLS-1$
	public java.lang.String getBaseDocNumber() {
		return this.BaseDocNumber;
	}

	public void setBaseDocNumber(java.lang.String Basedocnumber) {
		this.BaseDocNumber = Basedocnumber;
	}

	/**
	 * 
	 */
	@DataItemName("PaymentAlloc.Payment.BaseDocDate") //$NON-NLS-1$
	public java.util.Date getBaseDocDate() {
		return this.BaseDocDate;
	}

	public void setBaseDocDate(java.util.Date Basedocdate) {
		this.BaseDocDate = Basedocdate;
	}

	/**
	 * 
	 */
	@DataItemName("PaymentAlloc.Payment.ContractNumber") //$NON-NLS-1$
	public java.lang.String getContractNumber() {
		return this.ContractNumber;
	}

	public void setContractNumber(java.lang.String Contractnumber) {
		this.ContractNumber = Contractnumber;
	}

	/**
	 * 
	 */
	@DataItemName("PaymentAlloc.Payment.ContractDate") //$NON-NLS-1$
	public java.util.Date getContractDate() {
		return this.ContractDate;
	}

	public void setContractDate(java.util.Date Contractdate) {
		this.ContractDate = Contractdate;
	}

	/**
	 * 
	 */
	@DataItemName("PaymentAlloc.Payment.Description") //$NON-NLS-1$
	public java.lang.String getDescription() {
		return this.Description;
	}

	public void setDescription(java.lang.String Description) {
		this.Description = Description;
	}

	/**
	 * 
	 */
	@DataItemName("PaymentAlloc.Payment.Comments") //$NON-NLS-1$
	public java.lang.String getComments() {
		return this.Comments;
	}

	public void setComments(java.lang.String Comments) {
		this.Comments = Comments;
	}

	@DataItemName("PaymentAlloc.PaymentModel.DestFolderId") //$NON-NLS-1$
	public com.mg.merp.core.model.Folder getDestFolder() {
		return this.DestFolder;
	}

	public void setDestFolder(com.mg.merp.core.model.Folder destFolder) {
		this.DestFolder = destFolder;
	}

	/**
	 * 
	 */
	@DataItemName("PaymentAlloc.PaymentModel.ModelName") //$NON-NLS-1$
	public java.lang.String getModelName() {
		return this.ModelName;
	}

	public void setModelName(java.lang.String Modelname) {
		this.ModelName = Modelname;
	}

	public java.util.Set<TransactHead> getTransactHeads() {
		return this.transactHeads;
	}

	public void setTransactHeads(java.util.Set<TransactHead> transactHeads) {
		this.transactHeads = transactHeads;
	}

	/**
	 * Получть тип документа
	 * @return тип документа
	 */
	@DataItemName("PaymentAlloc.Payment.DocType") //$NON-NLS-1$
	public com.mg.merp.document.model.DocType getDocType() {
		return this.DocType;
	}

	public void setDocType(com.mg.merp.document.model.DocType docType) {
		this.DocType = docType;
	}

	/**
	 * Получть тип документа-основания
	 * @return тип документа-основания
	 */
	@DataItemName("PaymentAlloc.Payment.BaseDocType") //$NON-NLS-1$
	public com.mg.merp.document.model.DocType getBaseDocType() {
		return this.BaseDocType;
	}

	public void setBaseDocType(com.mg.merp.document.model.DocType baseDocType) {
		this.BaseDocType = baseDocType;
	}
	
	/**
	 * Получть тип контракта
	 * @return тип контракта
	 */
	@DataItemName("PaymentAlloc.Payment.ContractType") //$NON-NLS-1$
	public com.mg.merp.document.model.DocType getContractType() {
		return this.ContractType;
	}

	public void setContractType(com.mg.merp.document.model.DocType contractType) {
		this.ContractType = contractType;
	}

	public boolean getIsModel() {
		return this.IsModel;
	}

	public void setIsModel(boolean isModel) {
		this.IsModel = isModel;
	}

}