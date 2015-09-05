/*
 * InvMetal.java
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
package com.mg.merp.account.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: InvMetal.java,v 1.5 2006/06/08 13:04:53 leonova Exp $
 */
public class InvMetal extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private int Id;

	private com.mg.merp.account.model.Metal MetalCode;

	private com.mg.merp.account.model.InvHead InvHead;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.Double Mass;

	// Constructors

	/** default constructor */
	public InvMetal() {
	}

	/** constructor with id */
	public InvMetal(int Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("ID")
	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	/**
	 * 
	 */	
	public com.mg.merp.account.model.Metal getMetalCode() {
		return this.MetalCode;
	}

	public void setMetalCode(com.mg.merp.account.model.Metal Metal) {
		this.MetalCode = Metal;
	}

	/**
	 * 
	 */

	public com.mg.merp.account.model.InvHead getInvHead() {
		return this.InvHead;
	}

	public void setInvHead(com.mg.merp.account.model.InvHead AccInvhead) {
		this.InvHead = AccInvhead;
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
	@DataItemName("Account.InvHead.Mass")
	public java.lang.Double getMass() {
		return this.Mass;
	}

	public void setMass(java.lang.Double Mass) {
		this.Mass = Mass;
	}

}