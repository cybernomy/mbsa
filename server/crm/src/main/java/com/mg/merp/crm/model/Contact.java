/*
 * Contact.java
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
 * @version $Id: Contact.java,v 1.8 2008/03/18 12:18:27 alikaev Exp $
 */
@DataItemName("CRM.Contact")
public class Contact extends com.mg.framework.service.PersistentObjectHibernate	implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.crm.model.User Curator;

	private com.mg.merp.reference.model.NaturalPerson Person;

	private com.mg.merp.crm.model.User Responsible;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.crm.model.PersonTitle PersonTitle;

	private com.mg.merp.reference.model.Contractor Contractor;

	private java.lang.Integer Priority;

	private boolean IsDefault;

	private com.mg.merp.crm.model.Position ThePosition;

	private boolean IsRetired;

	private java.lang.String NickName;

	private CrmAddressSource AddressSource;

	private java.lang.String Comments;

	private java.util.Set<Operation> operations;

	private java.util.Set<Offer> offers;

	private java.util.Set<LinkedDocument> linkedDocs;

	private java.util.Set<ContactLink> contactLinks;

	// Constructors

	/** default constructor */
	public Contact() {
	}

	/** constructor with id */
	public Contact(java.lang.Integer Id) {
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
	@DataItemName("CRM.Contact.Curator")
	public com.mg.merp.crm.model.User getCurator() {
		return this.Curator;
	}

	public void setCurator(com.mg.merp.crm.model.User CrmUser) {
		this.Curator = CrmUser;
	}

	/**
	 * 
	 */
	@DataItemName("CRM.Contact.Person")
	public com.mg.merp.reference.model.NaturalPerson getPerson() {
		return this.Person;
	}

	public void setPerson(
			com.mg.merp.reference.model.NaturalPerson RefNaturalPerson) {
		this.Person = RefNaturalPerson;
	}

	/**
	 * 
	 */
	@DataItemName("CRM.Contact.Responsible")
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
	public com.mg.merp.crm.model.PersonTitle getPersonTitle() {
		return this.PersonTitle;
	}

	public void setPersonTitle(com.mg.merp.crm.model.PersonTitle CrmPersonTitle) {
		this.PersonTitle = CrmPersonTitle;
	}

	/**
	 * 
	 */
	@DataItemName("CRM.Contact.Contractor")
	public com.mg.merp.reference.model.Contractor getContractor() {
		return this.Contractor;
	}

	public void setContractor(com.mg.merp.reference.model.Contractor Contractor) {
		this.Contractor = Contractor;
	}

	/**
	 * 
	 */
	@DataItemName("CRM.Contact.Priority")
	public java.lang.Integer getPriority() {
		return this.Priority;
	}

	public void setPriority(java.lang.Integer Priority) {
		this.Priority = Priority;
	}

	/**
	 * 
	 */
	@DataItemName("CRM.Contact.IsDefault")
	public boolean getIsDefault() {
		return this.IsDefault;
	}

	public void setIsDefault(boolean IsDefault) {
		this.IsDefault = IsDefault;
	}

	/**
	 * 
	 */
	@DataItemName("CRM.Contact.ThePosition")
	public com.mg.merp.crm.model.Position getThePosition() {
		return this.ThePosition;
	}

	public void setThePosition(com.mg.merp.crm.model.Position ThePosition) {
		this.ThePosition = ThePosition;
	}

	/**
	 * 
	 */
	@DataItemName("CRM.Contact.IsRetired")
	public boolean getIsRetired() {
		return this.IsRetired;
	}

	public void setIsRetired(boolean IsRetired) {
		this.IsRetired = IsRetired;
	}

	/**
	 * 
	 */
	@DataItemName("CRM.Contact.NickName")
	public java.lang.String getNickName() {
		return this.NickName;
	}

	public void setNickName(java.lang.String Nickname) {
		this.NickName = Nickname;
	}

	/**
	 * 
	 */

	public CrmAddressSource getAddressSource() {
		return this.AddressSource;
	}

	public void setAddressSource(CrmAddressSource AddressSource) {
		this.AddressSource = AddressSource;
	}

	/**
	 * 
	 */
	@DataItemName("CRM.Contact.Comments")
	public java.lang.String getComments() {
		return this.Comments;
	}

	public void setComments(java.lang.String Comments) {
		this.Comments = Comments;
	}

	/**
	 * 
	 */

	public java.util.Set<Operation> getOperations() {
		return this.operations;
	}

	public void setOperations(java.util.Set<Operation> Operations) {
		this.operations = Operations;
	}

	/**
	 * 
	 */

	public java.util.Set<Offer> getOffers() {
		return this.offers;
	}

	public void setOffers(java.util.Set<Offer> Offers) {
		this.offers = Offers;
	}

	/**
	 * 
	 */

	public java.util.Set<LinkedDocument> getLinkedDocs() {
		return this.linkedDocs;
	}

	public void setLinkedDocs(java.util.Set<LinkedDocument> LinkedDocs) {
		this.linkedDocs = LinkedDocs;
	}

	/**
	 * 
	 */

	public java.util.Set<ContactLink> getContactLinks() {
		return this.contactLinks;
	}

	public void setContactLinks(java.util.Set<ContactLink> ContactLinks) {
		this.contactLinks = ContactLinks;
	}

}