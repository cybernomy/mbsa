/*
 * Contractor.java
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

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: Contractor.java,v 1.4 2006/06/20 08:26:56 leonova Exp $
 */
@DataItemName("Reference.Contractor")
public class Contractor extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private int Id;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.lang.String Code;

  private java.lang.String UpCode;

  private java.lang.String FullName;

  private java.lang.Integer FolderId;

  private short Kind;

  // Constructors

  /**
   * default constructor
   */
  public Contractor() {
  }

  /**
   * constructor with id
   */
  public Contractor(int Id) {
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

  public com.mg.merp.core.model.SysClient getSysClient() {
    return this.SysClient;
  }

  public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
    this.SysClient = SysClient;
  }

  /**
   *
   */
  @DataItemName("Reference.Contractor.Code")
  public java.lang.String getCode() {
    return this.Code;
  }

  public void setCode(java.lang.String Code) {
    this.Code = Code;
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
  @DataItemName("Reference.Contractor.Name")
  public java.lang.String getFullName() {
    return this.FullName;
  }

  public void setFullName(java.lang.String CName) {
    this.FullName = CName;
  }

  /**
   *
   */

  public java.lang.Integer getFolderId() {
    return this.FolderId;
  }

  public void setFolderId(java.lang.Integer Folder) {
    this.FolderId = Folder;
  }

  /**
   *
   */

  public short getKind() {
    return this.Kind;
  }

  public void setKind(short Kind) {
    this.Kind = Kind;
  }

}