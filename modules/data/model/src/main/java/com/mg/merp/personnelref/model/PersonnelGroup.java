/*
 * PersonnelGroup.java
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
package com.mg.merp.personnelref.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: PersonnelGroup.java,v 1.5 2006/10/20 08:33:23 leonova Exp $
 */
public class PersonnelGroup extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.personnelref.model.PersonnelGroupType GroupType;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.lang.String FldName;

  private java.lang.Integer ParentId;

  private java.lang.String FolderTag;

  // Constructors

  /**
   * default constructor
   */
  public PersonnelGroup() {
  }

  /**
   * constructor with id
   */
  public PersonnelGroup(java.lang.Integer Id) {
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

  @DataItemName("PersonnelRef.PersonnelGroup.GroupType")
  public com.mg.merp.personnelref.model.PersonnelGroupType getGroupType() {
    return this.GroupType;
  }

  public void setGroupType(
      com.mg.merp.personnelref.model.PersonnelGroupType PrefPersonnelGroupType) {
    this.GroupType = PrefPersonnelGroupType;
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

  @DataItemName("PersonnelRef.PersonnelGroup.FldName")
  public java.lang.String getFldName() {
    return this.FldName;
  }

  public void setFldName(java.lang.String Fldname) {
    this.FldName = Fldname;
  }

  /**
   *
   */

  public java.lang.Integer getParentId() {
    return this.ParentId;
  }

  public void setParentId(java.lang.Integer ParentId) {
    this.ParentId = ParentId;
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