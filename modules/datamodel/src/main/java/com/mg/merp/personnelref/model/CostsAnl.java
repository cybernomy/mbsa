/*
 * CostsAnl.java
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
 * @version $Id: CostsAnl.java,v 1.5 2006/09/04 13:01:16 leonova Exp $
 */
public class CostsAnl extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.personnelref.model.CostsAnl Parent;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String ACode;

	private java.lang.String AName;

	private short AnlLevel;

	private java.util.Set SetOfPrefCostsAnl;

	// Constructors

	/** default constructor */
	public CostsAnl() {
	}

	/** constructor with id */
	public CostsAnl(java.lang.Integer Id) {
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

	@DataItemName("PersonnelRef.CostsAnl.Parent")
	public com.mg.merp.personnelref.model.CostsAnl getParent() {
		return this.Parent;
	}

	public void setParent(com.mg.merp.personnelref.model.CostsAnl PrefCostsAnl) {
		this.Parent = PrefCostsAnl;
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

	@DataItemName("PersonnelRef.CostsAnl.ACode")
	public java.lang.String getACode() {
		return this.ACode;
	}

	public void setACode(java.lang.String Acode) {
		this.ACode = Acode;
	}

	/**
	 * 
	 */

	@DataItemName("PersonnelRef.CostsAnl.AName")
	public java.lang.String getAName() {
		return this.AName;
	}

	public void setAName(java.lang.String Aname) {
		this.AName = Aname;
	}

	/**
	 * 
	 */

	@DataItemName("PersonnelRef.CostsAnl.AnlLevel")
	public short getAnlLevel() {
		return this.AnlLevel;
	}

	public void setAnlLevel(short Anllevel) {
		this.AnlLevel = Anllevel;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfPrefCostsAnl() {
		return this.SetOfPrefCostsAnl;
	}

	public void setSetOfPrefCostsAnl(java.util.Set SetOfPrefCostsAnl) {
		this.SetOfPrefCostsAnl = SetOfPrefCostsAnl;
	}

}