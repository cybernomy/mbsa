/*
 * NaturalPersonHist.java
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
 * @version $Id: NaturalPersonHist.java,v 1.3 2006/05/30 04:37:34 leonova Exp $
 */
@DataItemName("Reference.NaturalPersonHist")
public class NaturalPersonHist extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.reference.model.NaturalPerson NaturalPerson;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.util.Date ActDate;

  private java.lang.String Surname;

  private java.lang.String Name;

  private java.lang.String Patronymic;

  private java.lang.String Inn;

  // Constructors

  /**
   * default constructor
   */
  public NaturalPersonHist() {
  }

  /**
   * constructor with id
   */
  public NaturalPersonHist(java.lang.Integer Id) {
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
  @DataItemName("Reference.PersonHist.ActDate")
  public java.util.Date getActDate() {
    return this.ActDate;
  }

  public void setActDate(java.util.Date ActDate) {
    this.ActDate = ActDate;
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
  @DataItemName("Reference.NaturalPerson.Name")
  public java.lang.String getName() {
    return this.Name;
  }

  public void setName(java.lang.String Name) {
    this.Name = Name;
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
  @DataItemName("Reference.PersonHist.INN")
  public java.lang.String getInn() {
    return this.Inn;
  }

  public void setInn(java.lang.String Inn) {
    this.Inn = Inn;
  }

}