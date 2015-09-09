/*
 * CatalogFolder.java
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
 * @version $Id: CatalogFolder.java,v 1.6 2006/10/12 11:52:55 safonov Exp $
 */
@DataItemName("Reference.CatalogFolder")
public class CatalogFolder extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private int Id;

  private com.mg.merp.reference.model.CatalogFolder CatalogFolder;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.baiengine.model.Repository AlgRepository;

  private java.lang.String FName;

  private java.lang.String GroupCode;

  private java.lang.String BarCode;

  private java.lang.String PluCode;

  private java.lang.String FolderTag;

  // Constructors

  /**
   * default constructor
   */
  public CatalogFolder() {
  }

  /**
   * constructor with id
   */
  public CatalogFolder(int Id) {
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

  public com.mg.merp.reference.model.CatalogFolder getCatalogFolder() {
    return this.CatalogFolder;
  }

  public void setCatalogFolder(
      com.mg.merp.reference.model.CatalogFolder CatalogFolder) {
    this.CatalogFolder = CatalogFolder;
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
  @DataItemName("Reference.CatFolder.AlgRepository")
  public com.mg.merp.baiengine.model.Repository getAlgRepository() {
    return this.AlgRepository;
  }

  public void setAlgRepository(
      com.mg.merp.baiengine.model.Repository AlgRepository) {
    this.AlgRepository = AlgRepository;
  }

  /**
   *
   */
  @DataItemName("Reference.CatFolder.Name")
  public java.lang.String getFName() {
    return this.FName;
  }

  public void setFName(java.lang.String FName) {
    this.FName = FName;
  }

  /**
   *
   */
  @DataItemName("Reference.CatFolder.Code")
  public java.lang.String getGroupCode() {
    return this.GroupCode;
  }

  public void setGroupCode(java.lang.String GroupCode) {
    this.GroupCode = GroupCode;
  }

  /**
   *
   */
  @DataItemName("Reference.CatFolder.BarCode")
  public java.lang.String getBarCode() {
    return this.BarCode;
  }

  public void setBarCode(java.lang.String BarCode) {
    this.BarCode = BarCode;
  }

  /**
   *
   */
  @DataItemName("Reference.CatFolder.PluCode")
  public java.lang.String getPluCode() {
    return this.PluCode;
  }

  public void setPluCode(java.lang.String PluCode) {
    this.PluCode = PluCode;
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