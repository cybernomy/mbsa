/*
 * FolderRights.java
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

/**
 * @author hbm2java
 * @version $Id: FolderRights.java,v 1.4 2008/01/15 12:22:42 safonov Exp $
 */
public class FolderRights extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.security.model.Groups SecGroups;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.Short FolderPart;

	private java.lang.Integer FolderId;

	private boolean permission;

	// Constructors

	/** default constructor */
	public FolderRights() {
	}

	/** constructor with id */
	public FolderRights(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */

	public java.lang.Integer getId() {
		return this.Id;
	}

	public void setId(java.lang.Integer Id) {
		this.Id = Id;
	}

	/**
	 * 
	 */

	public com.mg.merp.security.model.Groups getSecGroups() {
		return this.SecGroups;
	}

	public void setSecGroups(com.mg.merp.security.model.Groups SecGroups) {
		this.SecGroups = SecGroups;
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

	public java.lang.Short getFolderPart() {
		return this.FolderPart;
	}

	public void setFolderPart(java.lang.Short FolderPart) {
		this.FolderPart = FolderPart;
	}

	/**
	 * 
	 */

	public java.lang.Integer getFolderId() {
		return this.FolderId;
	}

	public void setFolderId(java.lang.Integer FolderId) {
		this.FolderId = FolderId;
	}

	/**
	 * 
	 */

	public boolean isPermission() {
		return this.permission;
	}

	public void setPermission(boolean Rights) {
		this.permission = Rights;
	}

}