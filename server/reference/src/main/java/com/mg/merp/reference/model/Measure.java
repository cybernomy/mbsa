/*
 * Measure.java
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
package com.mg.merp.reference.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Единицы измерения"
 * 
 * @author hbm2java
 * @version $Id: Measure.java,v 1.4 2007/09/18 05:53:04 alikaev Exp $
 */
@DataItemName("Reference.Measure")
public class Measure extends com.mg.framework.service.PersistentObjectHibernate
		implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String UpCode;

	private java.lang.String Code;

	private java.lang.String FullName;

	private boolean Dividing;

	private java.lang.String UniversalCode;
	
	private java.lang.String InternalCode;

	// Constructors

	/** default constructor */
	public Measure() {
	}

	/** constructor with id */
	public Measure(java.lang.Integer Id) {
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

	public java.lang.String getUpCode() {
		return this.UpCode;
	}

	public void setUpCode(java.lang.String UpCode) {
		this.UpCode = UpCode;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.SmallCode")
	public java.lang.String getCode() {
		return this.Code;
	}

	public void setCode(java.lang.String Code) {
		this.Code = Code;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Name")
	public java.lang.String getFullName() {
		return this.FullName;
	}

	public void setFullName(java.lang.String MName) {
		this.FullName = MName;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Measure.Dividing")
	public boolean getDividing() {
		return this.Dividing;
	}

	public void setDividing(boolean Divide) {
		this.Dividing = Divide;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Measure.UniversalCode")
	public java.lang.String getUniversalCode() {
		return this.UniversalCode;
	}

	public void setUniversalCode(java.lang.String UniversalCode) {
		this.UniversalCode = UniversalCode;
	}

	@DataItemName("Reference.Measure.InternalCode")
	public java.lang.String getInternalCode() {
		return InternalCode;
	}

	public void setInternalCode(java.lang.String internalCode) {
		InternalCode = internalCode;
	}
}