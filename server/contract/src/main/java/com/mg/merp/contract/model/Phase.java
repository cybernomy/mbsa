/*
 * Phase.java
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
package com.mg.merp.contract.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Этапы контракта"
 * 
 * @author Artem V. Sharapov 
 * @version $Id: Phase.java,v 1.8 2007/11/22 15:55:11 sharapov Exp $
 */
public class Phase extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.reference.model.Contractor Consignee;

	private com.mg.merp.reference.model.Contractor Shipper;

	private com.mg.merp.document.model.DocHead DocHead;

	private com.mg.merp.reference.model.Contractor Company;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.reference.model.Contractor Contractor;

	private com.mg.merp.reference.model.Contractor Responsible;

	private com.mg.merp.reference.model.PartnerEmpl ContractorResponsible;

	private com.mg.merp.reference.model.BankAccount BankReq;

	private com.mg.merp.reference.model.BankAccount ConsigneeBankReq;

	private com.mg.merp.reference.model.BankAccount ContractorBankReq;

	private com.mg.merp.reference.model.BankAccount ShipperBankReq;

	private java.lang.String phaseNumber;

	private java.util.Date BeginActionDate;

	private java.util.Date EndActionDate;

	private java.lang.String Description;

	private java.lang.String Comments;

	private java.math.BigDecimal SumCur;

	private java.math.BigDecimal SumNat;

	private CalcSumKind CalcSumKind;

	private java.math.BigDecimal Margin;

	private boolean Avoid;

	private java.math.BigDecimal ShippedPayment;

	private java.math.BigDecimal ReceivedPayment;

	private java.math.BigDecimal ShippedGood;

	private java.math.BigDecimal ReceivedGood;

	private java.math.BigDecimal FactShippedPayment;

	private java.math.BigDecimal FactReceivedPayment;

	private java.math.BigDecimal FactShippedGood;

	private java.math.BigDecimal FactReceivedGood;

	private java.math.BigDecimal ItemShippedPayment;

	private java.math.BigDecimal ItemReceivedPayment;

	private java.math.BigDecimal ItemShippedGood;

	private java.math.BigDecimal ItemReceivedGood;

	private java.lang.Short ContractorBankDays;

	private java.lang.Short BankDays;

	private java.math.BigDecimal PennyRate;

	private java.math.BigDecimal ContractorPennyRate;

	private com.mg.merp.document.model.DocType docType;

	private java.lang.String docNumber;

	private java.util.Date docDate;

	private com.mg.merp.document.model.DocHead document;


	/** default constructor */
	public Phase() {
	}

	/** constructor with id */
	public Phase(java.lang.Integer Id) {
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
	@DataItemName("Contract.Consignee") //$NON-NLS-1$
	public com.mg.merp.reference.model.Contractor getConsignee() {
		return this.Consignee;
	}

	public void setConsignee(com.mg.merp.reference.model.Contractor Contractor) {
		this.Consignee = Contractor;
	}

	/**
	 * 
	 */
	@DataItemName("Contract.Shipper") //$NON-NLS-1$
	public com.mg.merp.reference.model.Contractor getShipper() {
		return this.Shipper;
	}

	public void setShipper(com.mg.merp.reference.model.Contractor Contractor_1) {
		this.Shipper = Contractor_1;
	}

	/**
	 * 
	 */
	public com.mg.merp.document.model.DocHead getDocHead() {
		return this.DocHead;
	}

	public void setDocHead(com.mg.merp.document.model.DocHead Dochead) {
		this.DocHead = Dochead;
	}

	/**
	 * 
	 */
	@DataItemName("Contract.Phase.Company") //$NON-NLS-1$
	public com.mg.merp.reference.model.Contractor getCompany() {
		return this.Company;
	}

	public void setCompany(com.mg.merp.reference.model.Contractor Contractor_2) {
		this.Company = Contractor_2;
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
	@DataItemName("Contract.Phase.Contractor") //$NON-NLS-1$
	public com.mg.merp.reference.model.Contractor getContractor() {
		return this.Contractor;
	}

	public void setContractor(com.mg.merp.reference.model.Contractor Contractor_3) {
		this.Contractor = Contractor_3;
	}

	/**
	 * 
	 */
	@DataItemName("Contract.Phase.Responsible") //$NON-NLS-1$
	public com.mg.merp.reference.model.Contractor getResponsible() {
		return this.Responsible;
	}

	public void setResponsible(com.mg.merp.reference.model.Contractor Contractor_4) {
		this.Responsible = Contractor_4;
	}

	/**
	 * 
	 */
	@DataItemName("Contract.Phase.ContrResponsible") //$NON-NLS-1$
	public com.mg.merp.reference.model.PartnerEmpl getContractorResponsible() {
		return this.ContractorResponsible;
	}

	public void setContractorResponsible(com.mg.merp.reference.model.PartnerEmpl Partnempl) {
		this.ContractorResponsible = Partnempl;
	}

	/**
	 * 
	 */
	@DataItemName("Contract.Phase.BankReq") //$NON-NLS-1$
	public com.mg.merp.reference.model.BankAccount getBankReq() {
		return this.BankReq;
	}

	public void setBankReq(com.mg.merp.reference.model.BankAccount BankReq) {
		this.BankReq = BankReq;
	}

	/**
	 * 
	 */
	@DataItemName("Contract.ConsigneeBankReq") //$NON-NLS-1$
	public com.mg.merp.reference.model.BankAccount getConsigneeBankReq() {
		return this.ConsigneeBankReq;
	}

	public void setConsigneeBankReq(com.mg.merp.reference.model.BankAccount ConsigneeBankReq) {
		this.ConsigneeBankReq = ConsigneeBankReq;
	}

	/**
	 * 
	 */
	@DataItemName("Contract.Phase.ContrBankReq") //$NON-NLS-1$
	public com.mg.merp.reference.model.BankAccount getContractorBankReq() {
		return this.ContractorBankReq;
	}

	public void setContractorBankReq(com.mg.merp.reference.model.BankAccount ContractorBankReq) {
		this.ContractorBankReq = ContractorBankReq;
	}

	/**
	 * 
	 */
	@DataItemName("Contract.ShipperBankReq") //$NON-NLS-1$
	public com.mg.merp.reference.model.BankAccount getShipperBankReq() {
		return this.ShipperBankReq;
	}

	public void setShipperBankReq(com.mg.merp.reference.model.BankAccount ShipperBankReq) {
		this.ShipperBankReq = ShipperBankReq;
	}

	/**
	 * 
	 */
	@DataItemName("Contract.Phase.Number") //$NON-NLS-1$
	public java.lang.String getPhaseNumber() {
		return this.phaseNumber;
	}

	public void setPhaseNumber(java.lang.String phaseNumber) {
		this.phaseNumber = phaseNumber;
	}

	/**
	 * 
	 */
	@DataItemName("Contract.Phase.BeginActionDate") //$NON-NLS-1$
	public java.util.Date getBeginActionDate() {
		return this.BeginActionDate;
	}

	public void setBeginActionDate(java.util.Date BeginactionDate) {
		this.BeginActionDate = BeginactionDate;
	}

	/**
	 * 
	 */
	@DataItemName("Contract.Phase.EndActionDate") //$NON-NLS-1$
	public java.util.Date getEndActionDate() {
		return this.EndActionDate;
	}

	public void setEndActionDate(java.util.Date EndactionDate) {
		this.EndActionDate = EndactionDate;
	}

	/**
	 * 
	 */
	@DataItemName("Contract.Phase.Description") //$NON-NLS-1$
	public java.lang.String getDescription() {
		return this.Description;
	}

	public void setDescription(java.lang.String Description) {
		this.Description = Description;
	}

	/**
	 * 
	 */
	@DataItemName("Contract.Phase.Comments") //$NON-NLS-1$
	public java.lang.String getComments() {
		return this.Comments;
	}

	public void setComments(java.lang.String Comments) {
		this.Comments = Comments;
	}

	/**
	 * 
	 */
	@DataItemName("Contract.Phase.SumNat") //$NON-NLS-1$
	public java.math.BigDecimal getSumCur() {
		return this.SumCur;
	}

	public void setSumCur(java.math.BigDecimal Sumcur) {
		this.SumCur = Sumcur;
	}

	/**
	 * 
	 */
	public java.math.BigDecimal getSumNat() {
		return this.SumNat;
	}

	public void setSumNat(java.math.BigDecimal Sumnat) {
		this.SumNat = Sumnat;
	}

	/**
	 * 
	 */
	public CalcSumKind getCalcSumKind() {
		return this.CalcSumKind;
	}

	public void setCalcSumKind(CalcSumKind Calcsumkind) {
		this.CalcSumKind = Calcsumkind;
	}

	/**
	 * 
	 */
	public java.math.BigDecimal getMargin() {
		return this.Margin;
	}

	public void setMargin(java.math.BigDecimal Margin) {
		this.Margin = Margin;
	}

	/**
	 * 
	 */
	public boolean getAvoid() {
		return this.Avoid;
	}

	public void setAvoid(boolean Avoid) {
		this.Avoid = Avoid;
	}

	/**
	 * 
	 */
	@DataItemName("Contract.ShippedPayment") //$NON-NLS-1$
	public java.math.BigDecimal getShippedPayment() {
		return this.ShippedPayment;
	}

	public void setShippedPayment(java.math.BigDecimal Shippedpayment) {
		this.ShippedPayment = Shippedpayment;
	}

	/**
	 * 
	 */
	@DataItemName("Contract.ReceivedPayment") //$NON-NLS-1$
	public java.math.BigDecimal getReceivedPayment() {
		return this.ReceivedPayment;
	}

	public void setReceivedPayment(java.math.BigDecimal Receivedpayment) {
		this.ReceivedPayment = Receivedpayment;
	}

	/**
	 * 
	 */
	@DataItemName("Contract.ShippedGood") //$NON-NLS-1$
	public java.math.BigDecimal getShippedGood() {
		return this.ShippedGood;
	}

	public void setShippedGood(java.math.BigDecimal Shippedgood) {
		this.ShippedGood = Shippedgood;
	}

	/**
	 * 
	 */
	@DataItemName("Contract.ReceivedGood") //$NON-NLS-1$
	public java.math.BigDecimal getReceivedGood() {
		return this.ReceivedGood;
	}

	public void setReceivedGood(java.math.BigDecimal Receivedgood) {
		this.ReceivedGood = Receivedgood;
	}

	/**
	 * 
	 */
	@DataItemName("Contract.FactShippedPayment") //$NON-NLS-1$
	public java.math.BigDecimal getFactShippedPayment() {
		return this.FactShippedPayment;
	}

	public void setFactShippedPayment(java.math.BigDecimal Factshippedpayment) {
		this.FactShippedPayment = Factshippedpayment;
	}

	/**
	 * 
	 */
	@DataItemName("Contract.FactReceivedPayment") //$NON-NLS-1$
	public java.math.BigDecimal getFactReceivedPayment() {
		return this.FactReceivedPayment;
	}

	public void setFactReceivedPayment(java.math.BigDecimal Factreceivedpayment) {
		this.FactReceivedPayment = Factreceivedpayment;
	}

	/**
	 * 
	 */
	@DataItemName("Contract.FactShippedGood") //$NON-NLS-1$
	public java.math.BigDecimal getFactShippedGood() {
		return this.FactShippedGood;
	}

	public void setFactShippedGood(java.math.BigDecimal Factshippedgood) {
		this.FactShippedGood = Factshippedgood;
	}

	/**
	 * 
	 */
	@DataItemName("Contract.FactReceivedGood") //$NON-NLS-1$
	public java.math.BigDecimal getFactReceivedGood() {
		return this.FactReceivedGood;
	}

	public void setFactReceivedGood(java.math.BigDecimal Factreceivedgood) {
		this.FactReceivedGood = Factreceivedgood;
	}

	/**
	 * 
	 */
	@DataItemName("Contract.PhaseShippedPayment") //$NON-NLS-1$
	public java.math.BigDecimal getItemShippedPayment() {
		return this.ItemShippedPayment;
	}

	public void setItemShippedPayment(java.math.BigDecimal Itemshippedpayment) {
		this.ItemShippedPayment = Itemshippedpayment;
	}

	/**
	 * 
	 */
	@DataItemName("Contract.PhaseReceivedPayment") //$NON-NLS-1$
	public java.math.BigDecimal getItemReceivedPayment() {
		return this.ItemReceivedPayment;
	}

	public void setItemReceivedPayment(java.math.BigDecimal Itemreceivedpayment) {
		this.ItemReceivedPayment = Itemreceivedpayment;
	}

	/**
	 * 
	 */
	@DataItemName("Contract.PhaseShippedGood") //$NON-NLS-1$
	public java.math.BigDecimal getItemShippedGood() {
		return this.ItemShippedGood;
	}

	public void setItemShippedGood(java.math.BigDecimal Itemshippedgood) {
		this.ItemShippedGood = Itemshippedgood;
	}

	/**
	 * 
	 */
	@DataItemName("Contract.PhaseReceivedGood") //$NON-NLS-1$
	public java.math.BigDecimal getItemReceivedGood() {
		return this.ItemReceivedGood;
	}

	public void setItemReceivedGood(java.math.BigDecimal Itemreceivedgood) {
		this.ItemReceivedGood = Itemreceivedgood;
	}

	/**
	 * 
	 */
	@DataItemName("Contract.Phase.ContractorBankDays") //$NON-NLS-1$
	public java.lang.Short getContractorBankDays() {
		return this.ContractorBankDays;
	}

	public void setContractorBankDays(java.lang.Short Contractorbankdays) {
		this.ContractorBankDays = Contractorbankdays;
	}

	/**
	 * 
	 */
	@DataItemName("Contract.Phase.BankDays") //$NON-NLS-1$
	public java.lang.Short getBankDays() {
		return this.BankDays;
	}

	public void setBankDays(java.lang.Short Bankdays) {
		this.BankDays = Bankdays;
	}

	/**
	 * 
	 */
	@DataItemName("Contract.Phase.PennyRate") //$NON-NLS-1$
	public java.math.BigDecimal getPennyRate() {
		return this.PennyRate;
	}

	public void setPennyRate(java.math.BigDecimal PennyRate) {
		this.PennyRate = PennyRate;
	}

	/**
	 * 
	 */
	@DataItemName("Contract.Phase.ContractorPennyRate") //$NON-NLS-1$
	public java.math.BigDecimal getContractorPennyRate() {
		return this.ContractorPennyRate;
	}

	public void setContractorPennyRate(java.math.BigDecimal ContractorpennyRate) {
		this.ContractorPennyRate = ContractorpennyRate;
	}

	/**
	 * @return the docType
	 */
	@DataItemName("Document.DocType") //$NON-NLS-1$
	public com.mg.merp.document.model.DocType getDocType() {
		return this.docType;
	}

	/**
	 * @param docType the docType to set
	 */
	public void setDocType(com.mg.merp.document.model.DocType docType) {
		this.docType = docType;
	}

	/**
	 * @return the docNumber
	 */
	@DataItemName("Document.DocNumber") //$NON-NLS-1$
	public java.lang.String getDocNumber() {
		return this.docNumber;
	}

	/**
	 * @param docNumber the docNumber to set
	 */
	public void setDocNumber(java.lang.String docNumber) {
		this.docNumber = docNumber;
	}

	/**
	 * @return the docDate
	 */
	@DataItemName("Document.DocDate") //$NON-NLS-1$
	public java.util.Date getDocDate() {
		return this.docDate;
	}

	/**
	 * @param docDate the docDate to set
	 */
	public void setDocDate(java.util.Date docDate) {
		this.docDate = docDate;
	}

	/**
	 * @return the document
	 */
	public com.mg.merp.document.model.DocHead getDocument() {
		return this.document;
	}

	/**
	 * @param document the document to set
	 */
	public void setDocument(com.mg.merp.document.model.DocHead document) {
		this.document = document;
	}

}