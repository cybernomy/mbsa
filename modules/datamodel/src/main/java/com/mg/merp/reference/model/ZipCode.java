/*
 * ZipCode.java
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
 * @version $Id: ZipCode.java,v 1.3 2006/05/29 12:45:32 leonova Exp $
 */
@DataItemName("Reference.ZipCode")
public class ZipCode extends com.mg.framework.service.PersistentObjectHibernate
    implements java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.reference.model.District District;

  private com.mg.merp.reference.model.Country Country;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.reference.model.Place Place;

  private com.mg.merp.reference.model.Region Region;

  private java.lang.String Code;

  // Constructors

  /**
   * default constructor
   */
  public ZipCode() {
  }

  /**
   * constructor with id
   */
  public ZipCode(java.lang.Integer Id) {
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

  public com.mg.merp.reference.model.District getDistrict() {
    return this.District;
  }

  public void setDistrict(com.mg.merp.reference.model.District District) {
    this.District = District;
  }

  /**
   *
   */

  public com.mg.merp.reference.model.Country getCountry() {
    return this.Country;
  }

  public void setCountry(com.mg.merp.reference.model.Country Country) {
    this.Country = Country;
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

  public com.mg.merp.reference.model.Place getPlace() {
    return this.Place;
  }

  public void setPlace(com.mg.merp.reference.model.Place Place) {
    this.Place = Place;
  }

  /**
   *
   */

  public com.mg.merp.reference.model.Region getRegion() {
    return this.Region;
  }

  public void setRegion(com.mg.merp.reference.model.Region Region) {
    this.Region = Region;
  }

  /**
   *
   */
  @DataItemName("Reference.Address.ZipCode")
  public java.lang.String getCode() {
    return this.Code;
  }

  public void setCode(java.lang.String Code) {
    this.Code = Code;
  }
}