/*
 * SecUserProfileItem.java
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
package com.mg.merp.security.model;

/**
 * @author hbm2java
 * @version $Id: SecUserProfileItem.java,v 1.2 2005/06/28 10:04:09 pashistova Exp $
 */
public class SecUserProfileItem extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.security.model.SecUserProfile Profile;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.lang.String Code;

  private java.lang.Integer CheckSum;

  private byte[] Data;

  // Constructors

  /**
   * default constructor
   */
  public SecUserProfileItem() {
  }

  /**
   * constructor with id
   */
  public SecUserProfileItem(java.lang.Integer Id) {
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

  public com.mg.merp.security.model.SecUserProfile getProfile() {
    return this.Profile;
  }

  public void setProfile(
      com.mg.merp.security.model.SecUserProfile SecUserProfile) {
    this.Profile = SecUserProfile;
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

  public java.lang.String getCode() {
    return this.Code;
  }

  public void setCode(java.lang.String Code) {
    this.Code = Code;
  }

  /**
   *
   */

  public java.lang.Integer getCheckSum() {
    return this.CheckSum;
  }

  public void setCheckSum(java.lang.Integer CheckSum) {
    this.CheckSum = CheckSum;
  }

  /**
   *
   */

  public byte[] getData() {
    return this.Data;
  }

  public void setData(byte[] Data) {
    this.Data = Data;
  }

}