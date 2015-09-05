/*
 * PfCodeKindInServiceKind.java
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
package com.mg.merp.personnelref.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: PfCodeKindInServiceKind.java,v 1.2 2005/06/28 10:03:45
 *          pashistova Exp $
 */
public class PfCodeKindInServiceKind extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private int Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.core.model.Folder PfCodeKind;

	private com.mg.merp.personnelref.model.ServiceKind ServiceKind;

	private java.math.BigDecimal Ratio;

	// Constructors

	/** default constructor */
	public PfCodeKindInServiceKind() {
	}

	/** constructor with id */
	public PfCodeKindInServiceKind(int Id) {
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

	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.SysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	/**
	 * 
	 */

	@DataItemName("PersonnelRef.PCKISK.PfCodeKind")
	public com.mg.merp.core.model.Folder getPfCodeKind() {
		return this.PfCodeKind;
	}

	public void setPfCodeKind(com.mg.merp.core.model.Folder Folder) {
		this.PfCodeKind = Folder;
	}

	/**
	 * 
	 */

	public com.mg.merp.personnelref.model.ServiceKind getServiceKind() {
		return this.ServiceKind;
	}

	public void setServiceKind(
			com.mg.merp.personnelref.model.ServiceKind PrefServiceKind) {
		this.ServiceKind = PrefServiceKind;
	}

	/**
	 * 
	 */

	 @DataItemName("PersonnelRef.PCKISK.Ratio") 
	public java.math.BigDecimal getRatio() {
		return this.Ratio;
	}

	public void setRatio(java.math.BigDecimal Ratio) {
		this.Ratio = Ratio;
	}

}