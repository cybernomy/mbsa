/*
 * OfferReason.java
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
 * @version $Id: OfferReason.java,v 1.3 2006/06/14 07:51:13 leonova Exp $
 */
@DataItemName("CRM.OfferReason")
public class OfferReason extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String Code;

	private java.lang.String Name;

	private java.util.Set SetOfCrmOffer;

	private java.util.Set SetOfCrmOffer_1;

	// Constructors

	/** default constructor */
	public OfferReason() {
	}

	/** constructor with id */
	public OfferReason(java.lang.Integer Id) {
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

	@DataItemName("CRM.Code")
	public java.lang.String getCode() {
		return this.Code;
	}

	public void setCode(java.lang.String Code) {
		this.Code = Code;
	}

	/**
	 * 
	 */

	@DataItemName("CRM.Name")
	public java.lang.String getName() {
		return this.Name;
	}

	public void setName(java.lang.String Name) {
		this.Name = Name;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfCrmOffer() {
		return this.SetOfCrmOffer;
	}

	public void setSetOfCrmOffer(java.util.Set SetOfCrmOffer) {
		this.SetOfCrmOffer = SetOfCrmOffer;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfCrmOffer_1() {
		return this.SetOfCrmOffer_1;
	}

	public void setSetOfCrmOffer_1(java.util.Set SetOfCrmOffer_1) {
		this.SetOfCrmOffer_1 = SetOfCrmOffer_1;
	}

}