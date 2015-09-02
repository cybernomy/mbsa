/*
 * Rise.java
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
 * @version $Id: Rise.java,v 1.5 2006/06/19 08:01:38 leonova Exp $
 */
@DataItemName("PersonnelRef.Rise")
public class Rise extends com.mg.framework.service.PersistentObjectHibernate
		implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String RCode;

	private java.lang.String RName;

	// private java.util.Set SetOfPrefRiseScale;

	// Constructors

	/** default constructor */
	public Rise() {
	}

	/** constructor with id */
	public Rise(java.lang.Integer Id) {
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
	@DataItemName("PersonnelRef.Rise.Code")
	public java.lang.String getRCode() {
		return this.RCode;
	}

	public void setRCode(java.lang.String Rcode) {
		this.RCode = Rcode;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Rise.Name")
	public java.lang.String getRName() {
		return this.RName;
	}

	public void setRName(java.lang.String Rname) {
		this.RName = Rname;
	}
	/**
	 * 
	 */
	//    
	// public java.util.Set getSetOfPrefRiseScale () {
	// return this.SetOfPrefRiseScale;
	// }
	//    
	// public void setSetOfPrefRiseScale (java.util.Set SetOfPrefRiseScale) {
	// this.SetOfPrefRiseScale = SetOfPrefRiseScale;
	// }
}