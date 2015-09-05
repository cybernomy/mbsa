/*
 * TaxGroup.java
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

import java.util.Set;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: TaxGroup.java,v 1.5 2006/09/25 13:56:55 safonov Exp $
 */
@DataItemName("Reference.TaxGroup")
public class TaxGroup extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String Code;

	private java.lang.String TgName;

	private Set<com.mg.merp.reference.model.TaxLink> taxLinks;
	
	// Constructors

	/** default constructor */
	public TaxGroup() {
	}

	/** constructor with id */
	public TaxGroup(java.lang.Integer Id) {
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
	@DataItemName("Reference.Tax.Code")
	public java.lang.String getCode() {
		return this.Code;
	}

	public void setCode(java.lang.String Code) {
		this.Code = Code;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Tax.Name")
	public java.lang.String getTgName() {
		return this.TgName;
	}

	public void setTgName(java.lang.String TgName) {
		this.TgName = TgName;
	}

	/**
	 * @return Returns the taxLinks.
	 */
	public Set<com.mg.merp.reference.model.TaxLink> getTaxLinks() {
		return taxLinks;
	}

	/**
	 * @param taxLinks The taxLinks to set.
	 */
	public void setTaxLinks(Set<com.mg.merp.reference.model.TaxLink> taxLinks) {
		this.taxLinks = taxLinks;
	}

}