/*
 * SecUser.java
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

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: SecUser.java,v 1.8 2007/06/01 12:11:31 safonov Exp $
 */
@DataItemName("Security.User")
public class SecUser extends com.mg.framework.service.PersistentObjectHibernate
    implements java.io.Serializable {

  // Fields

  private int Id;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.lang.String Name;

  private java.lang.String FullName;

  private java.lang.String Passw;

  private boolean ActiveFlag;

  private boolean SmartCardOnly;

  private java.lang.String Email;

  private java.lang.String Mobile;

  private java.lang.String Pager;

  private boolean UseRemoteProfile;

  private java.lang.Short UserType;

  //private java.util.Set<SecUserProfile> secUserProfiles;

  private java.util.Set<LinkUsersGroups> secLinkUsersGroups;

  // Constructors

  /**
   * default constructor
   */
  public SecUser() {
  }

  /**
   * constructor with id
   */
  public SecUser(int Id) {
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
  @DataItemName("Security.User.Name")
  public java.lang.String getName() {
    return this.Name;
  }

  public void setName(java.lang.String Name) {
    this.Name = Name;
  }

  /**
   *
   */
  @DataItemName("Security.User.FullName")
  public java.lang.String getFullName() {
    return this.FullName;
  }

  public void setFullName(java.lang.String Fullname) {
    this.FullName = Fullname;
  }

  /**
   *
   */
  @DataItemName("Security.User.Passw")
  public java.lang.String getPassw() {
    return this.Passw;
  }

  public void setPassw(java.lang.String Passw) {
    this.Passw = Passw;
  }

  /**
   *
   */

  public boolean getActiveFlag() {
    return this.ActiveFlag;
  }

  public void setActiveFlag(boolean ActiveFlag) {
    this.ActiveFlag = ActiveFlag;
  }

  /**
   *
   */
  @DataItemName("Security.User.SmartCardOnly")
  public boolean getSmartCardOnly() {
    return this.SmartCardOnly;
  }

  public void setSmartCardOnly(boolean SmartCardOnly) {
    this.SmartCardOnly = SmartCardOnly;
  }

  /**
   *
   */
  @DataItemName("Security.User.Email")
  public java.lang.String getEmail() {
    return this.Email;
  }

  public void setEmail(java.lang.String Email) {
    this.Email = Email;
  }

  /**
   *
   */
  @DataItemName("Security.User.Mobile")
  public java.lang.String getMobile() {
    return this.Mobile;
  }

  public void setMobile(java.lang.String Mobile) {
    this.Mobile = Mobile;
  }

  /**
   *
   */
  @DataItemName("Security.User.Pager")
  public java.lang.String getPager() {
    return this.Pager;
  }

  public void setPager(java.lang.String Pager) {
    this.Pager = Pager;
  }

  /**
   *
   */
  @DataItemName("Security.User.UseRemoteProfile")
  public boolean getUseRemoteProfile() {
    return this.UseRemoteProfile;
  }

  public void setUseRemoteProfile(boolean UseRemoteProfile) {
    this.UseRemoteProfile = UseRemoteProfile;
  }

  /**
   *
   */

  public java.lang.Short getUserType() {
    return this.UserType;
  }

  public void setUserType(java.lang.Short UserType) {
    this.UserType = UserType;
  }

  /**
   *
   */

//	public java.util.Set<SecUserProfile> getSecUserProfiles() {
//		return this.secUserProfiles;
//	}
//
//	public void setSecUserProfiles(java.util.Set<SecUserProfile> SetOfSecUserProfile) {
//		this.secUserProfiles = SetOfSecUserProfile;
//	}

  /**
   *
   */

  public java.util.Set<LinkUsersGroups> getSecLinkUsersGroups() {
    return this.secLinkUsersGroups;
  }

  public void setSecLinkUsersGroups(java.util.Set<LinkUsersGroups> SetOfSecLinkUsersGroups) {
    this.secLinkUsersGroups = SetOfSecLinkUsersGroups;
  }
}