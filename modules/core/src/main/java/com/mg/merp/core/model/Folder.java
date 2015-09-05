/*
 * Folder.java
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
package com.mg.merp.core.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: Folder.java,v 1.6 2006/11/02 15:44:39 safonov Exp $
 */
@DataItemName("Reference.Folder")
public class Folder extends com.mg.framework.service.PersistentObjectHibernate
		implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.core.model.Folder Folder;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String FName;

	private java.lang.Short FolderType;

	private java.lang.Short Data;

	private java.lang.String FolderTag;

	// Constructors

	/** default constructor */
	public Folder() {
	}

	/** constructor with id */
	public Folder(java.lang.Integer Id) {
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
	@DataItemName("Reference.Folder.Name")
	public java.lang.String getFName() {
		return this.FName;
	}

	public void setFName(java.lang.String FName) {
		this.FName = FName;
	}

	/**
	 * 
	 */

	public java.lang.Short getFolderType() {
		return this.FolderType;
	}

	public void setFolderType(java.lang.Short FolderType) {
		this.FolderType = FolderType;
	}

	/**
	 * 
	 */

	public java.lang.Short getData() {
		return this.Data;
	}

	public void setData(java.lang.Short Data) {
		this.Data = Data;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.FolderTag")
	public java.lang.String getFolderTag() {
		return this.FolderTag;
	}

	public void setFolderTag(java.lang.String FolderTag) {
		this.FolderTag = FolderTag;
	}

}