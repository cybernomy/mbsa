/*
 * FamilyMember.java
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
 * @version $Id: FamilyMember.java,v 1.4 2006/05/30 05:40:38 leonova Exp $
 */
@DataItemName("Reference.FamilyMember")
public class FamilyMember extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private int Id;

  private com.mg.merp.reference.model.NaturalPerson NaturalPerson;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.reference.model.FamilyRelation FamilyRelation;

  private java.lang.String FName;

  private java.lang.String Patronymic;

  private java.lang.String Surname;

  private java.util.Date Birthdate;

  // Constructors

  /**
   * default constructor
   */
  public FamilyMember() {
  }

  /**
   * constructor with id
   */
  public FamilyMember(int Id) {
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
  public com.mg.merp.reference.model.FamilyRelation getFamilyRelation() {
    return this.FamilyRelation;
  }

  public void setFamilyRelation(
      com.mg.merp.reference.model.FamilyRelation FamilyRelation) {
    this.FamilyRelation = FamilyRelation;
  }

  /**
   *
   */
  @DataItemName("Reference.NaturalPerson.Name")
  public java.lang.String getFName() {
    return this.FName;
  }

  public void setFName(java.lang.String FName) {
    this.FName = FName;
  }

  /**
   *
   */
  @DataItemName("Reference.NaturalPerson.Patronymic")
  public java.lang.String getPatronymic() {
    return this.Patronymic;
  }

  public void setPatronymic(java.lang.String Patronymic) {
    this.Patronymic = Patronymic;
  }

  /**
   *
   */
  @DataItemName("Reference.NaturalPerson.Surname")
  public java.lang.String getSurname() {
    return this.Surname;
  }

  public void setSurname(java.lang.String Surname) {
    this.Surname = Surname;
  }

  /**
   *
   */
  @DataItemName("Reference.NaturalPerson.BornDate")
  public java.util.Date getBirthdate() {
    return this.Birthdate;
  }

  public void setBirthdate(java.util.Date Birthdate) {
    this.Birthdate = Birthdate;
  }

}