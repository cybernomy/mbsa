/*
 * Street.java
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
 * Модель бизнес-компонента "Улицы" (из КЛАДР)
 * 
 * @author Artem V. Sharapov
 * @version $Id: Street.java,v 1.4 2007/07/16 13:16:44 sharapov Exp $
 */
public class Street extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;
	private java.lang.String SName;
	private java.lang.String KType;
	private java.lang.String KCode;
	private java.lang.String PostIndex;
	private java.lang.String GninMb;
	private java.lang.String OcaTd;
	private com.mg.merp.core.model.SysClient SysClient;
	

	// Constructors

	/** default constructor */
	public Street() {
	}

	/** constructor with id */
	public Street(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("ID") //$NON-NLS-1$
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
	@DataItemName("PersonnelRef.Street.Name") //$NON-NLS-1$
	public java.lang.String getSName() {
		return this.SName;
	}

	public void setSName(java.lang.String Sname) {
		this.SName = Sname;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Street.Type") //$NON-NLS-1$
	public java.lang.String getKType() {
		return this.KType;
	}

	public void setKType(java.lang.String Ktype) {
		this.KType = Ktype;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Street.Code") //$NON-NLS-1$
	public java.lang.String getKCode() {
		return this.KCode;
	}

	public void setKCode(java.lang.String Kcode) {
		this.KCode = Kcode;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Street.PostIndex") //$NON-NLS-1$
	public java.lang.String getPostIndex() {
		return this.PostIndex;
	}

	public void setPostIndex(java.lang.String PostIndex) {
		this.PostIndex = PostIndex;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Street.GninMb") //$NON-NLS-1$
	public java.lang.String getGninMb() {
		return this.GninMb;
	}

	public void setGninMb(java.lang.String Gninmb) {
		this.GninMb = Gninmb;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Street.OcaTd") //$NON-NLS-1$
	public java.lang.String getOcaTd() {
		return this.OcaTd;
	}

	public void setOcaTd(java.lang.String Ocatd) {
		this.OcaTd = Ocatd;
	}

}