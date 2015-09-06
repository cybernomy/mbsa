/*
 * SecUserProfile.java
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
 * @version $Id: SecUserProfile.java,v 1.3 2005/07/22 07:06:22 safonov Exp $
 */
public class SecUserProfile extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.security.model.SecUser User;

  private short ProfileType;

  // Constructors

  /**
   * default constructor
   */
  public SecUserProfile() {
  }

  /**
   * constructor with id
   */
  public SecUserProfile(java.lang.Integer Id) {
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

  public com.mg.merp.core.model.SysClient getSysClient() {
    return this.SysClient;
  }

  public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
    this.SysClient = SysClient;
  }

  /**
   *
   */

  public com.mg.merp.security.model.SecUser getUser() {
    return this.User;
  }

  public void setUser(com.mg.merp.security.model.SecUser SecUsers) {
    this.User = SecUsers;
  }

  /**
   *
   */

  public short getProfileType() {
    return this.ProfileType;
  }

  public void setProfileType(short ProfileType) {
    this.ProfileType = ProfileType;
  }
}