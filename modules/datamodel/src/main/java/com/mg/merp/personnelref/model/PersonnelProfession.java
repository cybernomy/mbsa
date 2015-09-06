/*
 * PersonnelProfession.java
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
 * @version $Id: PersonnelProfession.java,v 1.3 2005/08/11 12:43:53 pashistova Exp $
 */
public class PersonnelProfession extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.personnelref.model.Personnel Personnel;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.personnelref.model.PrefJob Job;

  private boolean IsBasic;

  // Constructors

  /**
   * default constructor
   */
  public PersonnelProfession() {
  }

  /**
   * constructor with id
   */
  public PersonnelProfession(java.lang.Integer Id) {
    this.Id = Id;
  }

  // Property accessors

  /**
   *
   */
  @DataItemName("ID")
  public java.lang.Integer getId() {
    return this.Id;
  }

  public void setId(java.lang.Integer Id) {
    this.Id = Id;
  }

  /**
   *
   */

  public com.mg.merp.personnelref.model.Personnel getPersonnel() {
    return this.Personnel;
  }

  public void setPersonnel(
      com.mg.merp.personnelref.model.Personnel PrefPersonnel) {
    this.Personnel = PrefPersonnel;
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
  public com.mg.merp.personnelref.model.PrefJob getJob() {
    return this.Job;
  }

  public void setJob(com.mg.merp.personnelref.model.PrefJob PrefJob) {
    this.Job = PrefJob;
  }

  /**
   *
   */
  @DataItemName("PersonnelRef.Personnel.IsBasic")
  public boolean getIsBasic() {
    return this.IsBasic;
  }

  public void setIsBasic(boolean IsBasic) {
    this.IsBasic = IsBasic;
  }

}