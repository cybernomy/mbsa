/*
 * SysModule.java
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
package com.mg.merp.core.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.merp.security.model.ModuleAccess;

/**
 * @author hbm2java
 * @version $Id: SysModule.java,v 1.3 2007/03/07 10:24:16 safonov Exp $
 */
@DataItemName("Core.Subsystem")
public class SysModule extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private int Id;

  private java.lang.String Name;

  private java.lang.String Description;

  private java.lang.Short MajorVersion;

  private java.lang.Short MinorVersion;

  private java.lang.Short Release;

  private java.util.Set<ModuleAccess> permissions;

  private java.util.Set<SysClass> beans;

  // Constructors

  /**
   * default constructor
   */
  public SysModule() {
  }

  /**
   * constructor with id
   */
  public SysModule(int Id) {
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
  @DataItemName("Core.Subsystem.Name")
  public java.lang.String getName() {
    return this.Name;
  }

  public void setName(java.lang.String Name) {
    this.Name = Name;
  }

  /**
   *
   */
  @DataItemName("Core.Subsystem.Description")
  public java.lang.String getDescription() {
    return this.Description;
  }

  public void setDescription(java.lang.String Description) {
    this.Description = Description;
  }

  /**
   *
   */
  @DataItemName("Core.Subsystem.MajorVersion")
  public java.lang.Short getMajorVersion() {
    return this.MajorVersion;
  }

  public void setMajorVersion(java.lang.Short MajorVersion) {
    this.MajorVersion = MajorVersion;
  }

  /**
   *
   */
  @DataItemName("Core.Subsystem.MinorVersion")
  public java.lang.Short getMinorVersion() {
    return this.MinorVersion;
  }

  public void setMinorVersion(java.lang.Short MinorVersion) {
    this.MinorVersion = MinorVersion;
  }

  /**
   *
   */
  @DataItemName("Core.Subsystem.Release")
  public java.lang.Short getRelease() {
    return this.Release;
  }

  public void setRelease(java.lang.Short Release) {
    this.Release = Release;
  }

  /**
   *
   */

  public java.util.Set<ModuleAccess> getPermissions() {
    return this.permissions;
  }

  public void setPermissions(java.util.Set<ModuleAccess> SetOfSecModuleAccess) {
    this.permissions = SetOfSecModuleAccess;
  }

  /**
   *
   */

  public java.util.Set<SysClass> getBeans() {
    return this.beans;
  }

  public void setBeans(java.util.Set<SysClass> SetOfSysClass) {
    this.beans = SetOfSysClass;
  }

}