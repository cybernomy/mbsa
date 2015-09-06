/*
 * PriceListFolder.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.reference.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: PriceListFolder.java,v 1.7 2007/02/05 15:17:15 safonov Exp $
 */
@DataItemName("Reference.PriceListFolder") //$NON-NLS-1$
public class PriceListFolder extends com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.reference.model.PriceListHead PriceListHead;

  private com.mg.merp.reference.model.PriceListFolder Parent;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.lang.String FName;

  private java.lang.String FolderTag;

  // Constructors

  /**
   * default constructor
   */
  public PriceListFolder() {
  }

  /**
   * constructor with id
   */
  public PriceListFolder(java.lang.Integer Id) {
    this.Id = Id;
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

  public com.mg.merp.reference.model.PriceListHead getPriceListHead() {
    return this.PriceListHead;
  }

  public void setPriceListHead(
      com.mg.merp.reference.model.PriceListHead PriceListHead) {
    this.PriceListHead = PriceListHead;
  }

  /**
   *
   */

  public com.mg.merp.reference.model.PriceListFolder getParent() {
    return this.Parent;
  }

  public void setParent(
      com.mg.merp.reference.model.PriceListFolder PriceListFolder) {
    this.Parent = PriceListFolder;
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
  @DataItemName("Reference.PriceListFolder.Name") //$NON-NLS-1$
  public java.lang.String getFName() {
    return this.FName;
  }

  public void setFName(java.lang.String FName) {
    this.FName = FName;
  }

  /**
   *
   */
  @DataItemName("Reference.FolderTag") //$NON-NLS-1$
  public java.lang.String getFolderTag() {
    return this.FolderTag;
  }

  public void setFolderTag(java.lang.String FolderTag) {
    this.FolderTag = FolderTag;
  }
}