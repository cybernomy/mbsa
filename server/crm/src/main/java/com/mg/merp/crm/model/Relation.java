/*
 * Relation.java
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
 * @version $Id: Relation.java,v 1.7 2006/11/02 15:45:53 safonov Exp $
 */
@DataItemName("CRM.Relation")
public class Relation extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.crm.model.RelationStatus Status;

	private com.mg.merp.crm.model.OwnershipForm OwnershipForm;

	private com.mg.merp.core.model.Folder Folder;

	private com.mg.merp.crm.model.ClientType ClientType;

	private com.mg.merp.crm.model.Market Market;

	private com.mg.merp.crm.model.Relation Parent;

	private com.mg.merp.reference.model.NaturalPerson NaturalPerson;

	private com.mg.merp.crm.model.User Curator;

	private com.mg.merp.crm.model.User Responsible;

	private com.mg.merp.crm.model.ActivityKind ActivityKind;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.crm.model.PersonTitle PersonTitle;

	private com.mg.merp.crm.model.ClientRank ClientRank;

	private com.mg.merp.reference.model.Contractor LegalPerson;

	private com.mg.merp.crm.model.ActivitySphere ActivitySphere;

	private com.mg.merp.crm.model.Branch Branch;

	private com.mg.merp.crm.model.DeliveryKind DeliveryKind;

	private com.mg.merp.crm.model.PaymentCond PaymentCond;

	private java.lang.String Code;

	private java.lang.String Name;

	private boolean IsLegalPerson;

	private java.lang.String Info;

	private java.lang.String NickName;

	private java.lang.String Uin;

	// Constructors

	/** default constructor */
	public Relation() {
	}

	/** constructor with id */
	public Relation(java.lang.Integer Id) {
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
	public com.mg.merp.crm.model.RelationStatus getStatus() {
		return this.Status;
	}

	public void setStatus(com.mg.merp.crm.model.RelationStatus CrmRelationStatus) {
		this.Status = CrmRelationStatus;
	}

	/**
	 * 
	 */
	public com.mg.merp.crm.model.OwnershipForm getOwnershipForm() {
		return this.OwnershipForm;
	}

	public void setOwnershipForm(
			com.mg.merp.crm.model.OwnershipForm CrmOwnershipForm) {
		this.OwnershipForm = CrmOwnershipForm;
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
	public com.mg.merp.crm.model.ClientType getClientType() {
		return this.ClientType;
	}

	public void setClientType(com.mg.merp.crm.model.ClientType CrmClientType) {
		this.ClientType = CrmClientType;
	}

	/**
	 * 
	 */
	public com.mg.merp.crm.model.Market getMarket() {
		return this.Market;
	}

	public void setMarket(com.mg.merp.crm.model.Market CrmMarket) {
		this.Market = CrmMarket;
	}

	/**
	 * 
	 */
	@DataItemName("CRM.Relation.Parent")
	public com.mg.merp.crm.model.Relation getParent() {
		return this.Parent;
	}

	public void setParent(com.mg.merp.crm.model.Relation CrmRelation) {
		this.Parent = CrmRelation;
	}

	/**
	 * 
	 */
	public com.mg.merp.reference.model.NaturalPerson getNaturalPerson() {
		return this.NaturalPerson;
	}

	public void setNaturalPerson(
			com.mg.merp.reference.model.NaturalPerson RefNaturalPerson) {
		this.NaturalPerson = RefNaturalPerson;
	}

	/**
	 * 
	 */
	@DataItemName("CRM.Relation.Curator")
	public com.mg.merp.crm.model.User getCurator() {
		return this.Curator;
	}

	public void setCurator(com.mg.merp.crm.model.User CrmUser) {
		this.Curator = CrmUser;
	}

	/**
	 * 
	 */
	@DataItemName("CRM.Relation.Responsible")
	public com.mg.merp.crm.model.User getResponsible() {
		return this.Responsible;
	}

	public void setResponsible(com.mg.merp.crm.model.User CrmUser_1) {
		this.Responsible = CrmUser_1;
	}

	/**
	 * 
	 */
	public com.mg.merp.crm.model.ActivityKind getActivityKind() {
		return this.ActivityKind;
	}

	public void setActivityKind(
			com.mg.merp.crm.model.ActivityKind CrmActivityKind) {
		this.ActivityKind = CrmActivityKind;
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
	public com.mg.merp.crm.model.PersonTitle getPersonTitle() {
		return this.PersonTitle;
	}

	public void setPersonTitle(com.mg.merp.crm.model.PersonTitle CrmPersonTitle) {
		this.PersonTitle = CrmPersonTitle;
	}

	/**
	 * 
	 */
	public com.mg.merp.crm.model.ClientRank getClientRank() {
		return this.ClientRank;
	}

	public void setClientRank(com.mg.merp.crm.model.ClientRank CrmClientRank) {
		this.ClientRank = CrmClientRank;
	}

	/**
	 * 
	 */
	@DataItemName("CRM.Relation.LegalPerson")
	public com.mg.merp.reference.model.Contractor getLegalPerson() {
		return this.LegalPerson;
	}

	public void setLegalPerson(com.mg.merp.reference.model.Contractor Contractor) {
		this.LegalPerson = Contractor;
	}

	/**
	 * 
	 */
	public com.mg.merp.crm.model.ActivitySphere getActivitySphere() {
		return this.ActivitySphere;
	}

	public void setActivitySphere(
			com.mg.merp.crm.model.ActivitySphere CrmActivitySphere) {
		this.ActivitySphere = CrmActivitySphere;
	}

	/**
	 * 
	 */
	public com.mg.merp.crm.model.Branch getBranch() {
		return this.Branch;
	}

	public void setBranch(com.mg.merp.crm.model.Branch CrmBranch) {
		this.Branch = CrmBranch;
	}

	/**
	 * 
	 */
	public com.mg.merp.crm.model.DeliveryKind getDeliveryKind() {
		return this.DeliveryKind;
	}

	public void setDeliveryKind(
			com.mg.merp.crm.model.DeliveryKind CrmDeliveryKind) {
		this.DeliveryKind = CrmDeliveryKind;
	}

	/**
	 * 
	 */
	public com.mg.merp.crm.model.PaymentCond getPaymentCond() {
		return this.PaymentCond;
	}

	public void setPaymentCond(com.mg.merp.crm.model.PaymentCond CrmPaymentCond) {
		this.PaymentCond = CrmPaymentCond;
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
	@DataItemName("CRM.BigName")
	public java.lang.String getName() {
		return this.Name;
	}

	public void setName(java.lang.String Name) {
		this.Name = Name;
	}

	/**
	 * 
	 */
	@DataItemName("CRM.Relation.IsLegalPerson")
	public boolean getIsLegalPerson() {
		return this.IsLegalPerson;
	}

	public void setIsLegalPerson(boolean IsLegalPerson) {
		this.IsLegalPerson = IsLegalPerson;
	}

	/**
	 * 
	 */
	@DataItemName("CRM.Relation.Info")
	public java.lang.String getInfo() {
		return this.Info;
	}

	public void setInfo(java.lang.String Info) {
		this.Info = Info;
	}

	/**
	 * 
	 */
	@DataItemName("CRM.Relation.NickName")
	public java.lang.String getNickName() {
		return this.NickName;
	}

	public void setNickName(java.lang.String Nickname) {
		this.NickName = Nickname;
	}

	/**
	 * 
	 */

	public java.lang.String getUin() {
		return this.Uin;
	}

	public void setUin(java.lang.String Uin) {
		this.Uin = Uin;
	}

}