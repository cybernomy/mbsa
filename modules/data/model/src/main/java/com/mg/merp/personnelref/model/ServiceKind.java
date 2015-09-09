/*
 * ServiceKind.java
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
 * @version $Id: ServiceKind.java,v 1.7 2006/10/12 11:50:17 safonov Exp $
 */
@DataItemName("PersonnelRef.ServiceKind")
public class ServiceKind extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private int Id;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.baiengine.model.Repository BeginServiceAlg;

  private com.mg.merp.baiengine.model.Repository EndServiceAlg;

  private com.mg.merp.baiengine.model.Repository LengthServiceAlg;

  private java.lang.String KName;

  private java.lang.Integer ParentId;

  private java.lang.String KCode;

  private java.lang.String FolderTag;

  private java.util.Set SetOfPrefPfCodeKindInServiceKind;

  // Constructors

  /**
   * default constructor
   */
  public ServiceKind() {
  }

  /**
   * constructor with id
   */
  public ServiceKind(int Id) {
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

  @DataItemName("PersonnelRef.ServiceKind.KName")
  public java.lang.String getKName() {
    return this.KName;
  }

  public void setKName(java.lang.String Kname) {
    this.KName = Kname;
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
  @DataItemName("PersonnelRef.ServiceKind.KCode")
  public java.lang.String getKCode() {
    return this.KCode;
  }

  public void setKCode(java.lang.String Kcode) {
    this.KCode = Kcode;
  }

  /**
   *
   */
  @DataItemName("PersonnelRef.ServiceKind.BeginServiceAlg")
  public com.mg.merp.baiengine.model.Repository getBeginServiceAlg() {
    return this.BeginServiceAlg;
  }

  public void setBeginServiceAlg(
      com.mg.merp.baiengine.model.Repository BeginServiceAlg) {
    this.BeginServiceAlg = BeginServiceAlg;
  }

  /**
   *
   */
  @DataItemName("PersonnelRef.ServiceKind.EndServiceAlg")
  public com.mg.merp.baiengine.model.Repository getEndServiceAlg() {
    return this.EndServiceAlg;
  }

  public void setEndServiceAlg(
      com.mg.merp.baiengine.model.Repository EndServiceAlg) {
    this.EndServiceAlg = EndServiceAlg;
  }

  /**
   *
   */
  @DataItemName("PersonnelRef.ServiceKind.LengthServiceAlg")
  public com.mg.merp.baiengine.model.Repository getLengthServiceAlg() {
    return this.LengthServiceAlg;
  }

  public void setLengthServiceAlg(
      com.mg.merp.baiengine.model.Repository LengthServiceAlg) {
    this.LengthServiceAlg = LengthServiceAlg;
  }

  /**
   *
   */

  public java.lang.String getFolderTag() {
    return this.FolderTag;
  }

  public void setFolderTag(java.lang.String FolderTag) {
    this.FolderTag = FolderTag;
  }

  /**
   *
   */

  public java.util.Set getSetOfPrefPfCodeKindInServiceKind() {
    return this.SetOfPrefPfCodeKindInServiceKind;
  }

  public void setSetOfPrefPfCodeKindInServiceKind(
      java.util.Set SetOfPrefPfCodeKindInServiceKind) {
    this.SetOfPrefPfCodeKindInServiceKind = SetOfPrefPfCodeKindInServiceKind;
  }

}