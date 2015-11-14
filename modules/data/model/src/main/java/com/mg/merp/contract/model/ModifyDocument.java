/*
 * ModifyDocument.java
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
package com.mg.merp.contract.model;

import org.hibernate.bytecode.internal.javassist.FieldHandler;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Изменения контракта"
 *
 * @author Artem V. Sharapov
 * @version $Id: ModifyDocument.java,v 1.11 2008/02/29 12:27:35 safonov Exp $
 */
public class ModifyDocument extends com.mg.framework.service.PersistentObjectHibernate
    implements java.io.Serializable, org.hibernate.bytecode.internal.javassist.FieldHandled {

  private FieldHandler fieldHandler;

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.document.model.DocHead DocHead;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.document.model.DocType docType;

  private java.lang.String docNumber;

  private java.util.Date docDate;

  private java.lang.String ModifyDesc;

  private java.lang.String Comment;

  private byte[] original;

  private com.mg.merp.document.model.DocHead document;

  // Constructors

  /**
   * default constructor
   */
  public ModifyDocument() {
  }

  /**
   * constructor with id
   */
  public ModifyDocument(java.lang.Integer Id) {
    this.Id = Id;
  }

  /* (non-Javadoc)
   * @see org.hibernate.bytecode.internal.javassist.FieldHandled#getFieldHandler()
   */
  public FieldHandler getFieldHandler() {
    return fieldHandler;
  }

  /* (non-Javadoc)
   * @see org.hibernate.bytecode.internal.javassist.FieldHandled#setFieldHandler(org.hibernate.bytecode.javassist.FieldHandler)
   */
  public void setFieldHandler(FieldHandler handler) {
    this.fieldHandler = handler;
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

  /**
   *
   */
  public com.mg.merp.document.model.DocHead getDocHead() {
    return this.DocHead;
  }

  public void setDocHead(com.mg.merp.document.model.DocHead DocHead) {
    this.DocHead = DocHead;
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
  @DataItemName("Contract.Modif.DocType") //$NON-NLS-1$
  public com.mg.merp.document.model.DocType getDocType() {
    return this.docType;
  }

  public void setDocType(com.mg.merp.document.model.DocType Doctype) {
    this.docType = Doctype;
  }

  /**
   *
   */
  @DataItemName("Contract.Modif.DocNumber") //$NON-NLS-1$
  public java.lang.String getDocNumber() {
    return this.docNumber;
  }

  public void setDocNumber(java.lang.String Docnumber) {
    this.docNumber = Docnumber;
  }

  /**
   *
   */
  @DataItemName("Contract.Modif.DocDate") //$NON-NLS-1$
  public java.util.Date getDocDate() {
    return this.docDate;
  }

  public void setDocDate(java.util.Date Docdate) {
    this.docDate = Docdate;
  }

  /**
   *
   */
  @DataItemName("Contract.Modif.ModifyDesc") //$NON-NLS-1$
  public java.lang.String getModifyDesc() {
    return this.ModifyDesc;
  }

  public void setModifyDesc(java.lang.String Modifydesc) {
    this.ModifyDesc = Modifydesc;
  }

  /**
   *
   */
  @DataItemName("Contract.Modif.Comment") //$NON-NLS-1$
  public java.lang.String getComment() {
    return this.Comment;
  }

  public void setComment(java.lang.String Comment) {
    this.Comment = Comment;
  }

  /**
   * @return the original
   */
  public byte[] getOriginal() {
    return fieldHandler != null ? (byte[]) fieldHandler.readObject(this, "Original", this.original) : null;
  }

  /**
   * @param original the original to set
   */
  public void setOriginal(byte[] original) {
    if (fieldHandler != null)
      fieldHandler.writeObject(this, "Original", this.original, original);
    this.original = original;
  }

  /**
   * @return the document
   */
  public com.mg.merp.document.model.DocHead getDocument() {
    return this.document;
  }

  /**
   * @param document the document to set
   */
  public void setDocument(com.mg.merp.document.model.DocHead document) {
    this.document = document;
  }

}