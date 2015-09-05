/*
 * Repository.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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

/**
 * @author hbm2java
 * @version $Id: Repository.java,v 1.4 2008/12/03 09:01:50 safonov Exp $
 */
@DataItemName("BAi.Repository")
public class Repository extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private int Id;

	private com.mg.merp.core.model.Folder Folder;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String Code;

	private java.lang.String Name;

	private java.lang.String Algorithm;

	private EngineType Engine;

	private java.lang.String implementationName;
	
	private java.util.Date SysVersion;

	// Constructors

	/** default constructor */
	public Repository() {
	}

	/** constructor with id */
	public Repository(int Id) {
		this.Id = Id;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.service.PersistentObjectHibernate#getPrimaryKey()
	 */
	@Override
	public Object getPrimaryKey() {
		return getId();
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

	public com.mg.merp.core.model.Folder getFolder() {
		return this.Folder;
	}

	public void setFolder(com.mg.merp.core.model.Folder Folder) {
		this.Folder = Folder;
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
	@DataItemName("BAi.Code")
	public java.lang.String getCode() {
		return this.Code;
	}

	public void setCode(java.lang.String Code) {
		this.Code = Code;
	}

	/**
	 * 
	 */
	@DataItemName("BAi.BigName")
	public java.lang.String getName() {
		return this.Name;
	}

	public void setName(java.lang.String Name) {
		this.Name = Name;
	}

	/**
	 * 
	 */

	public java.lang.String getAlgorithm() {
		return this.Algorithm;
	}

	public void setAlgorithm(java.lang.String Algorithm) {
		this.Algorithm = Algorithm;
	}

	/**
	 * 
	 */

	public EngineType getEngine() {
		return this.Engine;
	}

	public void setEngine(EngineType Engine) {
		this.Engine = Engine;
	}

	/**
	 * 
	 */
	@DataItemName("BAi.Repository.ImplementationName")
	public java.lang.String getImplementationName() {
		return this.implementationName;
	}

	public void setImplementationName(java.lang.String JavaClassName) {
		this.implementationName = JavaClassName;
	}
	
	/**
	 * 
	 */
	public java.util.Date getSysVersion() {
		return SysVersion;
	}

	public void setSysVersion(java.util.Date version) {
		SysVersion = version;
	}

}