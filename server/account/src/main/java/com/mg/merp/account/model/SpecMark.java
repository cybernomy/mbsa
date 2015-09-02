/*
 * SpecMark.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.account.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Особые отметки"
 * 
 * @author hbm2java
 * @author Artem V. Sharapov
 * @version $Id: SpecMark.java,v 1.6 2007/01/16 11:42:46 sharapov Exp $
 */
@DataItemName("Account.SpecMark") //$NON-NLS-1$
public class SpecMark extends com.mg.framework.service.PersistentObjectHibernate implements
java.io.Serializable {

	// Fields

	private java.lang.Integer id; 	

	private java.lang.String UpCode;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String Code;

	private java.lang.String SmName;

	// Constructors

	/** default constructor */
	public SpecMark() {
	}

	/** constructor with id */
	public SpecMark(java.lang.Integer Id) {
		this.id=Id;
	}

	// Property accessors
	/**
	 * 
	 */
	public java.lang.String getUpCode() {
		return this.UpCode;
	}

	public void setUpCode(java.lang.String Upcode) {
		this.UpCode = Upcode;
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
	@DataItemName("Account.SpecMark.Code") //$NON-NLS-1$
	public java.lang.String getCode() {
		return this.Code;
	}

	public void setCode(java.lang.String Code) {
		this.Code = Code;
	}

	/**
	 * 
	 */
	@DataItemName("Account.SpecMark.Name") //$NON-NLS-1$
	public java.lang.String getSmName() {
		return this.SmName;
	}

	public void setSmName(java.lang.String Smname) {
		this.SmName = Smname;
	}

	/**
	 * @return Идентификатор
	 */
	@DataItemName("ID") //$NON-NLS-1$
	public java.lang.Integer getId() {
		return this.id;
	}

	/**
	 * @param id - Идентификатор
	 */
	public void setId(java.lang.Integer id) {
		this.id = id;
	}
}