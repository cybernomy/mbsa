/*
 * FinOperation.java
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
package com.mg.merp.crm.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: Operation.java,v 1.6 2008/03/18 12:20:44 alikaev Exp $
 */
public class Operation extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.crm.model.OperationPriority Priority;

	private com.mg.merp.crm.model.Operation Parent;

	private com.mg.merp.crm.model.User Curator;

	private com.mg.merp.crm.model.User Responsible;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.crm.model.Contact Contact;

	private com.mg.merp.crm.model.Relation Relation;

	private com.mg.merp.crm.model.OperationKind Kind;

	private com.mg.merp.crm.model.OperationPurpose Purpose;

	private java.lang.String Code;

	private boolean IsPlan;

	private java.util.Date CreateDate;

	private java.util.Date PlanDateFrom;

	private java.util.Date PlanDateTill;

	private java.util.Date FactDateFrom;

	private java.util.Date FactDateTill;

	private java.lang.String OperationPlan;

	private java.lang.String OperationResult;

	private java.lang.String OperationNext;

	private OperationStatusKind Status;

	private OperationState State;

	private boolean Notified;

	private java.util.Set<LinkedDocument> linkedDocs;

	private java.util.Set<Offer> offers;

	private java.util.Set<Operation> operations;

	// Constructors

	/** default constructor */
	public Operation() {
	}

	/** constructor with id */
	public Operation(java.lang.Integer Id) {
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
	@DataItemName("CRM.Operation.OpPriority")
	public com.mg.merp.crm.model.OperationPriority getPriority() {
		return this.Priority;
	}

	public void setPriority(
			com.mg.merp.crm.model.OperationPriority CrmOperationPriority) {
		this.Priority = CrmOperationPriority;
	}

	/**
	 * 
	 */

	public com.mg.merp.crm.model.Operation getParent() {
		return this.Parent;
	}

	public void setParent(com.mg.merp.crm.model.Operation CrmOperation) {
		this.Parent = CrmOperation;
	}

	/**
	 * 
	 */
	@DataItemName("CRM.Operation.Curator")
	public com.mg.merp.crm.model.User getCurator() {
		return this.Curator;
	}

	public void setCurator(com.mg.merp.crm.model.User CrmUser) {
		this.Curator = CrmUser;
	}

	/**
	 * 
	 */
	@DataItemName("CRM.Operation.Responsible")
	public com.mg.merp.crm.model.User getResponsible() {
		return this.Responsible;
	}

	public void setResponsible(com.mg.merp.crm.model.User CrmUser_1) {
		this.Responsible = CrmUser_1;
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
	public com.mg.merp.crm.model.Contact getContact() {
		return this.Contact;
	}

	public void setContact(com.mg.merp.crm.model.Contact CrmContact) {
		this.Contact = CrmContact;
	}

	/**
	 * 
	 */
	public com.mg.merp.crm.model.Relation getRelation() {
		return this.Relation;
	}

	public void setRelation(com.mg.merp.crm.model.Relation CrmRelation) {
		this.Relation = CrmRelation;
	}

	/**
	 * 
	 */
	@DataItemName("CRM.Operation.OpKind")
	public com.mg.merp.crm.model.OperationKind getKind() {
		return this.Kind;
	}

	public void setKind(com.mg.merp.crm.model.OperationKind CrmOperationKind) {
		this.Kind = CrmOperationKind;
	}

	/**
	 * 
	 */
	@DataItemName("CRM.Operation.OpPurpose")
	public com.mg.merp.crm.model.OperationPurpose getPurpose() {
		return this.Purpose;
	}

	public void setPurpose(
			com.mg.merp.crm.model.OperationPurpose CrmOperationPurpose) {
		this.Purpose = CrmOperationPurpose;
	}

	/**
	 * 
	 */
	@DataItemName("CRM.BigCode")
	public java.lang.String getCode() {
		return this.Code;
	}

	public void setCode(java.lang.String Code) {
		this.Code = Code;
	}

	/**
	 * 
	 */
	@DataItemName("CRM.Operation.IsPlan")
	public boolean getIsPlan() {
		return this.IsPlan;
	}

	public void setIsPlan(boolean IsPlan) {
		this.IsPlan = IsPlan;
	}

	/**
	 * 
	 */
	@DataItemName("CRM.Operation.CreateDate")
	public java.util.Date getCreateDate() {
		return this.CreateDate;
	}

	public void setCreateDate(java.util.Date CreateDate) {
		this.CreateDate = CreateDate;
	}

	/**
	 * 
	 */
	@DataItemName("CRM.Operation.PlanDateFrom")
	public java.util.Date getPlanDateFrom() {
		return this.PlanDateFrom;
	}

	public void setPlanDateFrom(java.util.Date PlanDateFrom) {
		this.PlanDateFrom = PlanDateFrom;
	}

	/**
	 * 
	 */
	@DataItemName("CRM.Operation.PlanDateTill")
	public java.util.Date getPlanDateTill() {
		return this.PlanDateTill;
	}

	public void setPlanDateTill(java.util.Date PlanDateTill) {
		this.PlanDateTill = PlanDateTill;
	}

	/**
	 * 
	 */
	@DataItemName("CRM.Operation.FactDateFrom")
	public java.util.Date getFactDateFrom() {
		return this.FactDateFrom;
	}

	public void setFactDateFrom(java.util.Date FactDateFrom) {
		this.FactDateFrom = FactDateFrom;
	}

	/**
	 * 
	 */
	@DataItemName("CRM.Operation.FactDateTill")
	public java.util.Date getFactDateTill() {
		return this.FactDateTill;
	}

	public void setFactDateTill(java.util.Date FactDateTill) {
		this.FactDateTill = FactDateTill;
	}

	/**
	 * 
	 */
	@DataItemName("CRM.Operation.OpPlan")
	public java.lang.String getOperationPlan() {
		return this.OperationPlan;
	}

	public void setOperationPlan(java.lang.String OperationPlan) {
		this.OperationPlan = OperationPlan;
	}

	/**
	 * 
	 */
	@DataItemName("CRM.Operation.OpResult")
	public java.lang.String getOperationResult() {
		return this.OperationResult;
	}

	public void setOperationResult(java.lang.String OperationResult) {
		this.OperationResult = OperationResult;
	}

	/**
	 * 
	 */

	public OperationStatusKind getStatus() {
		return this.Status;
	}

	public void setStatus(OperationStatusKind Status) {
		this.Status = Status;
	}

	/**
	 * 
	 */

	public OperationState getState() {
		return this.State;
	}

	public void setState(OperationState State) {
		this.State = State;
	}

	/**
	 * 
	 */

	public boolean getNotified() {
		return this.Notified;
	}

	public void setNotified(boolean Notified) {
		this.Notified = Notified;
	}

	@DataItemName("CRM.Operation.OpNext")
	public java.lang.String getOperationNext() {
		return OperationNext;
	}

	public void setOperationNext(java.lang.String operationNext) {
		OperationNext = operationNext;
	}

	/**
	 * @return linkedDocs
	 */
	public java.util.Set<LinkedDocument> getLinkedDocs() {
		return linkedDocs;
	}

	/**
	 * @param linkedDocs задаваемое linkedDocs
	 */
	public void setLinkedDocs(java.util.Set<LinkedDocument> linkedDocs) {
		this.linkedDocs = linkedDocs;
	}

	/**
	 * @return offers
	 */
	public java.util.Set<Offer> getOffers() {
		return offers;
	}

	/**
	 * @param offers задаваемое offers
	 */
	public void setOffers(java.util.Set<Offer> offers) {
		this.offers = offers;
	}

	/**
	 * @return operations
	 */
	public java.util.Set<Operation> getOperations() {
		return operations;
	}

	/**
	 * @param operations задаваемое operations
	 */
	public void setOperations(java.util.Set<Operation> operations) {
		this.operations = operations;
	}
	
}