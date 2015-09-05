/*
 * Kladr.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 *
 */

package com.mg.merp.personnelref.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Классификатор адресов (КЛАДР)"
 * 
 * @author Artem V. Sharapov
 * @version $Id: Kladr.java,v 1.3 2007/07/16 13:16:44 sharapov Exp $
 */
public class Kladr extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields    

	private java.lang.Integer Id;
	private java.lang.String KName;
	private java.lang.String KType;
	private java.lang.String KCode;
	private java.lang.String PostIndex;
	private java.lang.String GnInMb;
	private java.lang.String Ocatd;
	private java.lang.String SysCod;
	private com.mg.merp.core.model.SysClient SysClient;
	

	// Constructors

	/** default constructor */
	public Kladr() {
	}

	/** constructor with id */
	public Kladr(java.lang.Integer Id) {
		this.Id = Id;
	}


	// Property accessors
	/**

	 */
	@DataItemName("ID") //$NON-NLS-1$
	public java.lang.Integer getId() {
		return this.Id;
	}

	public void setId(java.lang.Integer Id) {
		this.Id = Id;
	}
	
	/**

	 */
	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.SysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}
	
	/**

	 */
	@DataItemName("PersonnelRef.Kladr.Name") //$NON-NLS-1$
	public java.lang.String getKName() {
		return this.KName;
	}

	public void setKName(java.lang.String Kname) {
		this.KName = Kname;
	}
	
	/**

	 */
	@DataItemName("PersonnelRef.Kladr.Type") //$NON-NLS-1$
	public java.lang.String getKType() {
		return this.KType;
	}

	public void setKType(java.lang.String Ktype) {
		this.KType = Ktype;
	}
	
	/**

	 */
	@DataItemName("PersonnelRef.Kladr.Code") //$NON-NLS-1$
	public java.lang.String getKCode() {
		return this.KCode;
	}

	public void setKCode(java.lang.String Kcode) {
		this.KCode = Kcode;
	}
	
	/**

	 */
	@DataItemName("PersonnelRef.Kladr.PostIndex") //$NON-NLS-1$
	public java.lang.String getPostIndex() {
		return this.PostIndex;
	}

	public void setPostIndex(java.lang.String PostIndex) {
		this.PostIndex = PostIndex;
	}
	
	/**

	 */
	@DataItemName("PersonnelRef.Kladr.GnInMb") //$NON-NLS-1$
	public java.lang.String getGnInMb() {
		return this.GnInMb;
	}

	public void setGnInMb(java.lang.String Gninmb) {
		this.GnInMb = Gninmb;
	}
	
	/**

	 */
	@DataItemName("PersonnelRef.Kladr.Ocatd") //$NON-NLS-1$
	public java.lang.String getOcatd() {
		return this.Ocatd;
	}

	public void setOcatd(java.lang.String Ocatd) {
		this.Ocatd = Ocatd;
	}
	
	/**

	 */
	@DataItemName("PersonnelRef.Kladr.SysCod") //$NON-NLS-1$
	public java.lang.String getSysCod() {
		return this.SysCod;
	}

	public void setSysCod(java.lang.String Syscod) {
		this.SysCod = Syscod;
	}

}