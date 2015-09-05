/*
 * ContractorPhone.java
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
package com.mg.merp.reference.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: ContractorPhone.java,v 1.3 2006/05/29 12:11:19 leonova Exp $
 */
@DataItemName("Reference.ContractorPhone")
public class ContractorPhone extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.reference.model.PhoneKind PhoneKind;

	private com.mg.merp.reference.model.Contractor Contractor;

	private java.lang.String AreaCode;

	private java.lang.String Phone;

	// Constructors

	/** default constructor */
	public ContractorPhone() {
	}

	/** constructor with id */
	public ContractorPhone(java.lang.Integer Id) {
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

	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.SysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	/**
	 * 
	 */
	public com.mg.merp.reference.model.PhoneKind getPhoneKind() {
		return this.PhoneKind;
	}

	public void setPhoneKind(com.mg.merp.reference.model.PhoneKind PhoneKind) {
		this.PhoneKind = PhoneKind;
	}

	/**
	 * 
	 */

	public com.mg.merp.reference.model.Contractor getContractor() {
		return this.Contractor;
	}

	public void setContractor(com.mg.merp.reference.model.Contractor Contractor) {
		this.Contractor = Contractor;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Partner.Phone.AreaCode")
	public java.lang.String getAreaCode() {
		return this.AreaCode;
	}

	public void setAreaCode(java.lang.String AreaCode) {
		this.AreaCode = AreaCode;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Partner.Phone.Number")
	public java.lang.String getPhone() {
		return this.Phone;
	}

	public void setPhone(java.lang.String Phone) {
		this.Phone = Phone;
	}

}