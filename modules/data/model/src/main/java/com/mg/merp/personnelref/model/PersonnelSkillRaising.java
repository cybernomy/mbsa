/*
 * PersonnelSkillRaising.java
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
 * @version $Id: PersonnelSkillRaising.java,v 1.2 2005/06/28 10:03:44 pashistova Exp $
 */
public class PersonnelSkillRaising extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.humanresources.model.Order Order;

  private com.mg.merp.personnelref.model.SkillRaisingKind SkillRaisingKind;

  private com.mg.merp.personnelref.model.Personnel Personnel;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.reference.model.OriginalDocument CertificateDocument;

  private java.util.Date StudyBeginDate;

  private java.util.Date StudyEndDate;

  private java.lang.String InstitutionName;

  private java.lang.String InstitutionAddress;

  // Constructors

  /**
   * default constructor
   */
  public PersonnelSkillRaising() {
  }

  /**
   * constructor with id
   */
  public PersonnelSkillRaising(java.lang.Integer Id) {
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

  public com.mg.merp.humanresources.model.Order getOrder() {
    return this.Order;
  }

  public void setOrder(com.mg.merp.humanresources.model.Order HrOrder) {
    this.Order = HrOrder;
  }

  /**
   *
   */
  @DataItemName("PersonnelRef.Personnel.SkillRaising")
  public com.mg.merp.personnelref.model.SkillRaisingKind getSkillRaisingKind() {
    return this.SkillRaisingKind;
  }

  public void setSkillRaisingKind(
      com.mg.merp.personnelref.model.SkillRaisingKind PrefSkillRaisingKind) {
    this.SkillRaisingKind = PrefSkillRaisingKind;
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
  @DataItemName("PersonnelRef.Personnel.OriginalDocument")
  public com.mg.merp.reference.model.OriginalDocument getCertificateDocument() {
    return this.CertificateDocument;
  }

  public void setCertificateDocument(
      com.mg.merp.reference.model.OriginalDocument RefOriginalDocument) {
    this.CertificateDocument = RefOriginalDocument;
  }

  /**
   *
   */
  @DataItemName("PersonnelRef.Personnel.StudyBeginDate")
  public java.util.Date getStudyBeginDate() {
    return this.StudyBeginDate;
  }

  public void setStudyBeginDate(java.util.Date StudyBegindate) {
    this.StudyBeginDate = StudyBegindate;
  }

  /**
   *
   */
  @DataItemName("PersonnelRef.Personnel.StudyEndDate")
  public java.util.Date getStudyEndDate() {
    return this.StudyEndDate;
  }

  public void setStudyEndDate(java.util.Date StudyEnddate) {
    this.StudyEndDate = StudyEnddate;
  }

  /**
   *
   */
  @DataItemName("PersonnelRef.Personnel.SkillRaising.InstitutionName")
  public java.lang.String getInstitutionName() {
    return this.InstitutionName;
  }

  public void setInstitutionName(java.lang.String InstitutionName) {
    this.InstitutionName = InstitutionName;
  }

  /**
   *
   */
  @DataItemName("PersonnelRef.Personnel.InstitutionAddress")
  public java.lang.String getInstitutionAddress() {
    return this.InstitutionAddress;
  }

  public void setInstitutionAddress(java.lang.String InstitutionAddress) {
    this.InstitutionAddress = InstitutionAddress;
  }

}