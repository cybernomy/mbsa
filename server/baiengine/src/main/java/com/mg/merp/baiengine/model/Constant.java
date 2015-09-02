/*
 * Constant.java
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
package com.mg.merp.baiengine.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.merp.core.model.Folder;

/**
 * 
 * 
 * @author hbm2java
 * @version $Id: Constant.java,v 1.1 2007/08/17 09:19:21 alikaev Exp $
 */
public class Constant extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private Folder Folder;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String Code;

	private java.lang.String Description;

	private ConstantDataType DataType;

	private java.util.Set<ConstantValue> ConstValues;

	// Constructors

	/** default constructor */
	public Constant() {
	}

	/** constructor with id */
	public Constant(java.lang.Integer Id) {
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



	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.SysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	/**
	 * 
	 */
	@DataItemName("BAi.Code") //$NON-NLS-1$
	public java.lang.String getCode() {
		return this.Code;
	}

	public void setCode(java.lang.String Code) {
		this.Code = Code;
	}

	/**
	 * 
	 */
	@DataItemName("BAi.BigName") //$NON-NLS-1$
	public java.lang.String getDescription() {
		return this.Description;
	}

	public void setDescription(java.lang.String Description) {
		this.Description = Description;
	}

	/**
	 * 
	 */
	public ConstantDataType getDataType() {
		return this.DataType;
	}

	public void setDataType(ConstantDataType Datatype) {
		this.DataType = Datatype;
	}

	public Folder getFolder() {
		return Folder;
	}

	public void setFolder(Folder folder) {
		Folder = folder;
	}

	public java.util.Set<ConstantValue> getConstValues() {
		return ConstValues;
	}

	public void setConstValues(java.util.Set<ConstantValue> constValues) {
		ConstValues = constValues;
	}

}