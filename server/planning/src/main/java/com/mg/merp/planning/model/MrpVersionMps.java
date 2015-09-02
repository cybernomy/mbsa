/*
 * MrpVersionMps.java
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
package com.mg.merp.planning.model;

import com.mg.framework.api.annotations.DataItemName;


/**
 * @author hbm2java
 * @version $Id: MrpVersionMps.java,v 1.6 2007/02/05 16:27:13 safonov Exp $
 */
public class MrpVersionMps extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.planning.model.MrpVersionControl MrpVersionControl;

	private com.mg.merp.planning.model.Mps Mps;

	// Constructors

	/** default constructor */
	public MrpVersionMps() {
	}

	/** constructor with id */
	public MrpVersionMps(java.lang.Integer Id) {
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

	public com.mg.merp.planning.model.MrpVersionControl getMrpVersionControl() {
		return this.MrpVersionControl;
	}

	public void setMrpVersionControl(
			com.mg.merp.planning.model.MrpVersionControl MrpVersionControl) {
		this.MrpVersionControl = MrpVersionControl;
	}

	/**
	 * 
	 */
	public com.mg.merp.planning.model.Mps getMps() {
		return this.Mps;
	}

	public void setMps(com.mg.merp.planning.model.Mps Mps) {
		this.Mps = Mps;
	}

}