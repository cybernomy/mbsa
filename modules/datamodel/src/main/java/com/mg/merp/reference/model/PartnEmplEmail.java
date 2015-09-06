/*
 * PartnEmplEmail.java
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


/**
 * @author hbm2java
 * @version $Id: PartnEmplEmail.java,v 1.2 2005/08/11 12:43:58 pashistova Exp $
 */
public class PartnEmplEmail extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private int Id;
  private com.mg.merp.reference.model.PartnerEmpl PartnEmpl;
  private com.mg.merp.core.model.SysClient SysClient;
  private java.lang.String EMail;
  private java.lang.Short IsActive;


  // Constructors

  /**
   * default constructor
   */
  public PartnEmplEmail() {
  }

  /**
   * constructor with id
   */
  public PartnEmplEmail(int Id) {
    this.Id = Id;
  }


  // Property accessors

  /**

   */

  public int getId() {
    return this.Id;
  }

  public void setId(int Id) {
    this.Id = Id;
  }

  /**

   */

  public com.mg.merp.reference.model.PartnerEmpl getPartnEmpl() {
    return this.PartnEmpl;
  }

  public void setPartnEmpl(com.mg.merp.reference.model.PartnerEmpl Partnempl) {
    this.PartnEmpl = Partnempl;
  }

  /**

   */

  public com.mg.merp.core.model.SysClient getSysClient() {
    return this.SysClient;
  }

  public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
    this.SysClient = SysClient;
  }

  /**

   */

  public java.lang.String getEMail() {
    return this.EMail;
  }

  public void setEMail(java.lang.String Email) {
    this.EMail = Email;
  }

  /**

   */

  public java.lang.Short getIsActive() {
    return this.IsActive;
  }

  public void setIsActive(java.lang.Short IsActive) {
    this.IsActive = IsActive;
  }


}