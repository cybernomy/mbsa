/*
 * OriginalDocument.java
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
package com.mg.merp.reference.model;

import org.hibernate.bytecode.javassist.FieldHandler;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: OriginalDocument.java,v 1.7 2008/02/08 07:45:29 safonov Exp $
 */
@DataItemName("Reference.OriginalDocument")
public class OriginalDocument extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable, org.hibernate.bytecode.javassist.FieldHandled {

	private FieldHandler fieldHandler;

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.core.model.Folder Folder;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String DocNumber;

	private java.util.Date DocDate;

	private java.lang.String DocName;

	private java.util.Date CreateDate;

	private java.lang.String Comments;

	private byte[] Original;

	// Constructors

	/** default constructor */
	public OriginalDocument() {
	}

	/** constructor with id */
	public OriginalDocument(java.lang.Integer Id) {
		this.Id = Id;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.bytecode.javassist.FieldHandled#getFieldHandler()
	 */
	public FieldHandler getFieldHandler() {
		return fieldHandler;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.bytecode.javassist.FieldHandled#setFieldHandler(org.hibernate.bytecode.javassist.FieldHandler)
	 */
	public void setFieldHandler(FieldHandler handler) {
		this.fieldHandler = handler;
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
	@DataItemName("Reference.OrigDoc.DocNumber")
	public java.lang.String getDocNumber() {
		return this.DocNumber;
	}

	public void setDocNumber(java.lang.String DocNumber) {
		this.DocNumber = DocNumber;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.OrigDoc.DocDate")
	public java.util.Date getDocDate() {
		return this.DocDate;
	}

	public void setDocDate(java.util.Date DocDate) {
		this.DocDate = DocDate;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Name")
	public java.lang.String getDocName() {
		return this.DocName;
	}

	public void setDocName(java.lang.String DocName) {
		this.DocName = DocName;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.OrigDoc.CreateDate")
	public java.util.Date getCreateDate() {
		return this.CreateDate;
	}

	public void setCreateDate(java.util.Date CreateDate) {
		this.CreateDate = CreateDate;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.OrigDoc.Comment")
	public java.lang.String getComments() {
		return this.Comments;
	}

	public void setComments(java.lang.String Comments) {
		this.Comments = Comments;
	}

	/**
	 * 
	 */
	public byte[] getOriginal() {
		return fieldHandler != null ? (byte[]) fieldHandler.readObject(this, "Original", this.Original) : null;
	}

	public void setOriginal(byte[] Original) {
		if (fieldHandler != null)
			fieldHandler.writeObject(this, "Original", this.Original, Original);
		this.Original = Original;
	}
}