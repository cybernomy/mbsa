/*
 * PersonPhone.java
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

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: PersonPhone.java,v 1.4 2006/05/30 05:44:14 leonova Exp $
 */
@DataItemName("Reference.PersonPhone")
public class PersonPhone extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.reference.model.NaturalPerson NaturalPerson;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.reference.model.PhoneKind PhoneKind;

  private java.lang.String Phone;

  private java.lang.String AreaCode;

  // Constructors

  /**
   * default constructor
   */
  public PersonPhone() {
  }

  /**
   * constructor with id
   */
  public PersonPhone(java.lang.Integer Id) {
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

  public com.mg.merp.reference.model.NaturalPerson getNaturalPerson() {
    return this.NaturalPerson;
  }

  public void setNaturalPerson(
      com.mg.merp.reference.model.NaturalPerson NaturalPerson) {
    this.NaturalPerson = NaturalPerson;
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
  public com.mg.merp.reference.model.PhoneKind getPhoneKind() {
    return this.PhoneKind;
  }

  public void setPhoneKind(com.mg.merp.reference.model.PhoneKind PhoneKind) {
    this.PhoneKind = PhoneKind;
  }

  /**
   *
   */
  @DataItemName("Reference.Person.Phone.Phone")
  public java.lang.String getPhone() {
    return this.Phone;
  }

  public void setPhone(java.lang.String Phone) {
    this.Phone = Phone;
  }

  /**
   *
   */
  @DataItemName("Reference.Person.Phone.AreaCode")
  public java.lang.String getAreaCode() {
    return this.AreaCode;
  }

  public void setAreaCode(java.lang.String AreaCode) {
    this.AreaCode = AreaCode;
  }

}