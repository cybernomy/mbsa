/*
 * DocTypeRights.java
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

package com.mg.merp.document.model;

/**
 * Модель бизнес-компонента "Права на типы документов"
 * 
 * @author Artem V. Sharapov 
 * @version $Id: DocTypeRights.java,v 1.3 2007/11/20 14:55:23 sharapov Exp $
 */
public class DocTypeRights extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields

	private java.lang.Integer id;

	private com.mg.merp.document.model.DocType docType;

	private com.mg.merp.security.model.Groups secGroups;

	private com.mg.merp.core.model.SysClient sysClient;

	private Boolean rights;

	// Constructors

	/** default constructor */
	public DocTypeRights() {
	}

	/** constructor with id */
	public DocTypeRights(java.lang.Integer Id) {
		this.id = Id;
	}

	// Property accessors
	/**
	 * 
	 */
	public java.lang.Integer getId() {
		return this.id;
	}

	public void setId(java.lang.Integer Id) {
		this.id = Id;
	}

	/**
	 * 
	 */
	public com.mg.merp.document.model.DocType getDocType() {
		return this.docType;
	}

	public void setDocType(com.mg.merp.document.model.DocType Typedoc) {
		this.docType = Typedoc;
	}

	/**
	 * 
	 */
	public com.mg.merp.security.model.Groups getSecGroups() {
		return this.secGroups;
	}

	public void setSecGroups(com.mg.merp.security.model.Groups SecGroups) {
		this.secGroups = SecGroups;
	}

	/**
	 * 
	 */
	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.sysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.sysClient = SysClient;
	}

	/**
	 * 
	 */
	public Boolean getRights() {
		return this.rights;
	}

	public void setRights(Boolean Rights) {
		this.rights = Rights;
	}

}