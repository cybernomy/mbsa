/*
 * PersonnelPhone.java
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


/**
 * @author hbm2java
 * @version $Id: PersonnelPhone.java,v 1.2 2005/06/28 10:03:43 pashistova Exp $
 */
public class PersonnelPhone extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private java.lang.Integer Id;
  private com.mg.merp.personnelref.model.Personnel Personnel;
  private com.mg.merp.core.model.SysClient SysClient;
  private com.mg.merp.reference.model.PhoneKind PhoneKind;
  private java.lang.String TownCode;
  private java.lang.String Phone;


  // Constructors

  /**
   * default constructor
   */
  public PersonnelPhone() {
  }

  /**
   * constructor with id
   */
  public PersonnelPhone(java.lang.Integer Id) {
    this.Id = Id;
  }


  // Property accessors

  /**

   */

  public java.lang.Integer getId() {
    return this.Id;
  }

  public void setId(java.lang.Integer Id) {
    this.Id = Id;
  }

  /**

   */

  public com.mg.merp.personnelref.model.Personnel getPersonnel() {
    return this.Personnel;
  }

  public void setPersonnel(com.mg.merp.personnelref.model.Personnel PrefPersonnel) {
    this.Personnel = PrefPersonnel;
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

  public com.mg.merp.reference.model.PhoneKind getPhoneKind() {
    return this.PhoneKind;
  }

  public void setPhoneKind(com.mg.merp.reference.model.PhoneKind RefPhoneKind) {
    this.PhoneKind = RefPhoneKind;
  }

  /**

   */

  public java.lang.String getTownCode() {
    return this.TownCode;
  }

  public void setTownCode(java.lang.String TownCode) {
    this.TownCode = TownCode;
  }

  /**

   */

  public java.lang.String getPhone() {
    return this.Phone;
  }

  public void setPhone(java.lang.String Phone) {
    this.Phone = Phone;
  }


}