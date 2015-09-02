/*
 * DocTypeDocSectionLink.java
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
package com.mg.merp.document.model;

/**
 * @author hbm2java
 * @version $Id: DocTypeDocSectionLink.java,v 1.3 2006/08/31 09:07:19 safonov Exp $
 */
public class DocTypeDocSectionLink extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private int id;

	private com.mg.merp.document.model.DocSection docSection;

	private com.mg.merp.core.model.SysClient sysClient;

	private com.mg.merp.document.model.DocType docType;

	private DocumentKind kind;

	// Constructors

	/** default constructor */
	public DocTypeDocSectionLink() {
	}

	/** constructor with id */
	public DocTypeDocSectionLink(int Id) {
		this.id = Id;
	}

	// Property accessors
	/**
	 * 
	 */

	public int getId() {
		return this.id;
	}

	public void setId(int Id) {
		this.id = Id;
	}

	/**
	 * 
	 */

	public com.mg.merp.document.model.DocSection getDocSection() {
		return this.docSection;
	}

	public void setDocSection(com.mg.merp.document.model.DocSection Docsection) {
		this.docSection = Docsection;
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

	public com.mg.merp.document.model.DocType getDocType() {
		return this.docType;
	}

	public void setDocType(com.mg.merp.document.model.DocType Typedoc) {
		this.docType = Typedoc;
	}

	/**
	 * 
	 */

	public DocumentKind getKind() {
		return this.kind;
	}

	public void setKind(DocumentKind Kind) {
		this.kind = Kind;
	}

}