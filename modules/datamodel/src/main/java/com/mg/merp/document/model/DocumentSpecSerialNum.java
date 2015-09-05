/*
 * DocumentSpecSerialNum.java
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

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: DocumentSpecSerialNum.java,v 1.1 2005/06/10 06:53:14 safonov
 *          Exp $
 */
public class DocumentSpecSerialNum extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private int Id;

	private com.mg.merp.document.model.DocSpec docSpec;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String SerialNum;

	private java.lang.String Comment;

	// Constructors

	/** default constructor */
	public DocumentSpecSerialNum() {
	}

	/** constructor with id */
	public DocumentSpecSerialNum(int Id) {
		this.Id = Id;
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

	public com.mg.merp.document.model.DocSpec getDocSpec() {
		return this.docSpec;
	}

	public void setDocSpec(com.mg.merp.document.model.DocSpec Docspec) {
		this.docSpec = Docspec;
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
	@DataItemName("Document.DocSpec.SerialNum.SerialNum")
	public java.lang.String getSerialNum() {
		return this.SerialNum;
	}

	public void setSerialNum(java.lang.String SerialNum) {
		this.SerialNum = SerialNum;
	}

	/**
	 * 
	 */
	@DataItemName("Document.DocSpec.SerialNum.Comment")
	public java.lang.String getComment() {
		return this.Comment;
	}

	public void setComment(java.lang.String Comment) {
		this.Comment = Comment;
	}

}