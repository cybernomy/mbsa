/*
 * ConfidentialData.java
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
 * @version $Id: ConfidentialData.java,v 1.2 2006/03/29 13:06:23 safonov Exp $
 */
public class ConfidentialData extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private int Id;

	private com.mg.merp.reference.model.Partner Partner;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String DName;

	private java.io.Serializable ConfData;

	// Constructors

	/** default constructor */
	public ConfidentialData() {
	}

	/** constructor with id */
	public ConfidentialData(int Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */

	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	/**
	 * 
	 */

	public com.mg.merp.reference.model.Partner getPartner() {
		return this.Partner;
	}

	public void setPartner(com.mg.merp.reference.model.Partner Partner) {
		this.Partner = Partner;
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
	@DataItemName("Reference.Partner.CofidData.DName")
	public java.lang.String getDName() {
		return this.DName;
	}

	public void setDName(java.lang.String DName) {
		this.DName = DName;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Partner.CofidData.ConfData")
	public java.io.Serializable getConfData() {
		return this.ConfData;
	}

	public void setConfData(java.io.Serializable ConfData) {
		this.ConfData = ConfData;
	}

}