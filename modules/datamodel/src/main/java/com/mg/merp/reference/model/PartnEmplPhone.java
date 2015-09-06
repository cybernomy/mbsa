/*
 * PartnEmplPhone.java
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
 * @version $Id: PartnEmplPhone.java,v 1.2 2005/08/11 12:43:59 pashistova Exp $
 */
public class PartnEmplPhone extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private int Id;
  private com.mg.merp.reference.model.PhoneKind PhoneKind;
  private com.mg.merp.reference.model.PartnerEmpl PartnEmpl;
  private com.mg.merp.core.model.SysClient SysClient;
  private java.lang.String TownCode;
  private java.lang.String Phone;
  private boolean IsActive;


  // Constructors

  /**
   * default constructor
   */
  public PartnEmplPhone() {
  }

  /**
   * constructor with id
   */
  public PartnEmplPhone(int Id) {
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

  public com.mg.merp.reference.model.PhoneKind getPhoneKind() {
    return this.PhoneKind;
  }

  public void setPhoneKind(com.mg.merp.reference.model.PhoneKind RefPhoneKind) {
    this.PhoneKind = RefPhoneKind;
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

  public java.lang.String getTownCode() {
    return this.TownCode;
  }

  public void setTownCode(java.lang.String Towncode) {
    this.TownCode = Towncode;
  }

  /**

   */

  public java.lang.String getPhone() {
    return this.Phone;
  }

  public void setPhone(java.lang.String Phone) {
    this.Phone = Phone;
  }

  /**

   */

  public boolean getIsActive() {
    return this.IsActive;
  }

  public void setIsActive(boolean IsActive) {
    this.IsActive = IsActive;
  }


}