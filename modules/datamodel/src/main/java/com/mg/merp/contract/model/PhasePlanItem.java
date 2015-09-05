/*
 * PhasePlanItem.java
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
 * Модель бизнес-компонента "Пункт плана контракта"
 * 
 * @author Artem V. Sharapov 
 * @version $Id: PhasePlanItem.java,v 1.7 2007/11/22 15:55:11 sharapov Exp $
 */
public class PhasePlanItem extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.contract.model.Phase ContractPhase;

	private com.mg.merp.reference.model.Contractor From;

	private com.mg.merp.reference.model.Contractor To;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String itemNumber;

	private ItemKind Kind;

	private java.util.Date BeginActionDate;

	private java.util.Date EndActionDate;

	private java.math.BigDecimal PlanSum;

	private java.math.BigDecimal FactSum;

	private boolean Avoid;
	
	private com.mg.merp.document.model.DocType docType;
	
	private java.lang.String docNumber;
	
	private java.util.Date docDate;
	
	private com.mg.merp.document.model.DocHead document;

	
	/** default constructor */
	public PhasePlanItem() {
	}

	/** constructor with id */
	public PhasePlanItem(java.lang.Integer Id) {
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
	public com.mg.merp.contract.model.Phase getContractPhase() {
		return this.ContractPhase;
	}

	public void setContractPhase(com.mg.merp.contract.model.Phase Contractphase) {
		this.ContractPhase = Contractphase;
	}

	/**
	 * 
	 */
	@DataItemName("Contract.ItemPan.From") //$NON-NLS-1$
	public com.mg.merp.reference.model.Contractor getFrom() {
		return this.From;
	}

	public void setFrom(com.mg.merp.reference.model.Contractor Contractor) {
		this.From = Contractor;
	}

	/**
	 * 
	 */
	@DataItemName("Contract.ItemPan.To") //$NON-NLS-1$
	public com.mg.merp.reference.model.Contractor getTo() {
		return this.To;
	}

	public void setTo(com.mg.merp.reference.model.Contractor Contractor_1) {
		this.To = Contractor_1;
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
	@DataItemName("Document.DocNumber") //$NON-NLS-1$
	public java.lang.String getDocNumber() {
		return this.docNumber;
	}

	public void setDocNumber(java.lang.String Docnumber) {
		this.docNumber = Docnumber;
	}

	/**
	 * 
	 */
	public ItemKind getKind() {
		return this.Kind;
	}

	public void setKind(ItemKind Kind) {
		this.Kind = Kind;
	}

	/**
	 * 
	 */
	@DataItemName("Contract.ItemnPlan.BeginActionDate") //$NON-NLS-1$
	public java.util.Date getBeginActionDate() {
		return this.BeginActionDate;
	}

	public void setBeginActionDate(java.util.Date BeginactionDate) {
		this.BeginActionDate = BeginactionDate;
	}

	/**
	 * 
	 */
	@DataItemName("Contract.ItemnPlan.EndActionDate") //$NON-NLS-1$
	public java.util.Date getEndActionDate() {
		return this.EndActionDate;
	}

	public void setEndActionDate(java.util.Date EndactionDate) {
		this.EndActionDate = EndactionDate;
	}

	/**
	 * 
	 */
	@DataItemName("Contract.ItemnPlan.PlanSum") //$NON-NLS-1$
	public java.math.BigDecimal getPlanSum() {
		return this.PlanSum;
	}

	public void setPlanSum(java.math.BigDecimal Plansum) {
		this.PlanSum = Plansum;
	}

	/**
	 * 
	 */
	@DataItemName("Contract.ItemnPlan.FactSum") //$NON-NLS-1$
	public java.math.BigDecimal getFactSum() {
		return this.FactSum;
	}

	public void setFactSum(java.math.BigDecimal Factsum) {
		this.FactSum = Factsum;
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

	/**
	 * @return the itemNumber
	 */
	@DataItemName("Contract.ItemnPlan.Number") //$NON-NLS-1$
	public java.lang.String getItemNumber() {
		return this.itemNumber;
	}

	/**
	 * @param itemNumber the itemNumber to set
	 */
	public void setItemNumber(java.lang.String itemNumber) {
		this.itemNumber = itemNumber;
	}

}