/*
 * ContractorElectronicAddress.java
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
 * @version $Id: ContractorElectronicAddress.java,v 1.1 2005/06/10 06:51:44 safonov Exp $
 */
@DataItemName("Reference.ContractorElectronicAddress")
public class ContractorElectronicAddress extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.reference.model.Contractor Contractor;

  private com.mg.merp.reference.model.ElectronicAddressKind EaddressKind;

  private ProtokolKind Protocol;

  private java.lang.String Address;

  private boolean IsActive;

  // Constructors

  /**
   * default constructor
   */
  public ContractorElectronicAddress() {
  }

  /**
   * constructor with id
   */
  public ContractorElectronicAddress(java.lang.Integer Id) {
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

  public com.mg.merp.core.model.SysClient getSysClient() {
    return this.SysClient;
  }

  public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
    this.SysClient = SysClient;
  }

  /**
   *
   */

  public com.mg.merp.reference.model.Contractor getContractor() {
    return this.Contractor;
  }

  public void setContractor(com.mg.merp.reference.model.Contractor Contractor) {
    this.Contractor = Contractor;
  }

  /**
   *
   */
  public com.mg.merp.reference.model.ElectronicAddressKind getEaddressKind() {
    return this.EaddressKind;
  }

  public void setEaddressKind(
      com.mg.merp.reference.model.ElectronicAddressKind EaddressKind) {
    this.EaddressKind = EaddressKind;
  }

  /**
   *
   */

  public ProtokolKind getProtocol() {
    return this.Protocol;
  }

  public void setProtocol(ProtokolKind Protocol) {
    this.Protocol = Protocol;
  }

  /**
   *
   */
  @DataItemName("Reference.Partner.Email.Address")
  public java.lang.String getAddress() {
    return this.Address;
  }

  public void setAddress(java.lang.String Address) {
    this.Address = Address;
  }

  /**
   *
   */
  @DataItemName("Reference.Partner.Email.IsActive")
  public boolean getIsActive() {
    return this.IsActive;
  }

  public void setIsActive(boolean IsActive) {
    this.IsActive = IsActive;
  }

}