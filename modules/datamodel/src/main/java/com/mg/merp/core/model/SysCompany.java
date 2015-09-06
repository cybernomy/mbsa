/**
 * SysCompany.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.merp.core.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.service.PersistentObjectHibernate;

import java.io.Serializable;

/**
 * Сущность "Балансовая единица"
 *
 * @author Oleg V. Safonov
 * @version $Id: SysCompany.java,v 1.1 2007/09/20 15:05:36 safonov Exp $
 */
@DataItemName("Core.SysCompany")
public class SysCompany extends PersistentObjectHibernate implements
    Serializable {
  // Fields

  private java.lang.Integer id;

  private com.mg.merp.core.model.SysClient sysClient;

  private java.lang.String code;

  private java.lang.String name;

  private java.lang.String description;

  private boolean isActive;

  // Constructors

  /**
   * default constructor
   */
  public SysCompany() {
  }

  /**
   * constructor with id
   */
  public SysCompany(Integer id) {
    this.id = id;
  }

  // Property accessors

  /**
   * @return the id
   */
  @DataItemName("ID")
  public java.lang.Integer getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(java.lang.Integer id) {
    this.id = id;
  }

  /**
   * @return the sysClient
   */
  public com.mg.merp.core.model.SysClient getSysClient() {
    return sysClient;
  }

  /**
   * @param sysClient the sysClient to set
   */
  public void setSysClient(com.mg.merp.core.model.SysClient sysClient) {
    this.sysClient = sysClient;
  }

  /**
   * @return the code
   */
  @DataItemName("Reference.BigCode")
  public java.lang.String getCode() {
    return code;
  }

  /**
   * @param code the code to set
   */
  public void setCode(java.lang.String code) {
    this.code = code;
  }

  /**
   * @return the name
   */
  @DataItemName("Reference.Name")
  public java.lang.String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(java.lang.String name) {
    this.name = name;
  }

  /**
   * @return the description
   */
  @DataItemName("Core.Description")
  public java.lang.String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(java.lang.String description) {
    this.description = description;
  }

  /**
   * @return the isActive
   */
  @DataItemName("Core.Active")
  public boolean isActive() {
    return isActive;
  }

  /**
   * @param isActive the isActive to set
   */
  public void setActive(boolean isActive) {
    this.isActive = isActive;
  }

}
