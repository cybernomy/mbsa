/*
 * PfCodeInService.java
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
 * @version $Id: PfCodeInService.java,v 1.4 2006/09/06 12:37:20 leonova Exp $
 */
public class PfCodeInService extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.personnelref.model.ServicePfCode PfCode;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.personnelref.model.PersonnelService PersonnelService;

  private java.lang.String Comment;

  // Constructors

  /**
   * default constructor
   */
  public PfCodeInService() {
  }

  /**
   * constructor with id
   */
  public PfCodeInService(int Id) {
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

  public com.mg.merp.personnelref.model.ServicePfCode getPfCode() {
    return this.PfCode;
  }

  public void setPfCode(
      com.mg.merp.personnelref.model.ServicePfCode PrefServicePfcode) {
    this.PfCode = PrefServicePfcode;
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

  public com.mg.merp.personnelref.model.PersonnelService getPersonnelService() {
    return this.PersonnelService;
  }

  public void setPersonnelService(
      com.mg.merp.personnelref.model.PersonnelService PrefPersonnelService) {
    this.PersonnelService = PrefPersonnelService;
  }

  /**
   *
   */
  @DataItemName("PersonnelRef.CodeInService.Comment")
  public java.lang.String getComment() {
    return this.Comment;
  }

  public void setComment(java.lang.String Comment) {
    this.Comment = Comment;
  }

}