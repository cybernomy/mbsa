/*
 * SysClass.java
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
package com.mg.merp.core.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: SysClass.java,v 1.7 2008/03/03 12:56:35 safonov Exp $
 */
@DataItemName("Core.SysClass")
public class SysClass extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.core.model.SysModule SysModule;

  private java.lang.String BeanName;

  private java.lang.String Description;

  private java.util.Set<SysMethod> methods;

  // Constructors

  /**
   * default constructor
   */
  public SysClass() {
  }

  /**
   * constructor with id
   */
  public SysClass(java.lang.Integer Id) {
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

  public com.mg.merp.core.model.SysModule getSysModule() {
    return this.SysModule;
  }

  public void setSysModule(com.mg.merp.core.model.SysModule SysModule) {
    this.SysModule = SysModule;
  }

  /**
   *
   */
  @DataItemName("Core.BeanName")
  public java.lang.String getBeanName() {
    return this.BeanName;
  }

  public void setBeanName(java.lang.String BeanName) {
    this.BeanName = BeanName;
  }

  /**
   *
   */
  @DataItemName("Core.Description")
  public java.lang.String getDescription() {
    return this.Description;
  }

  public void setDescription(java.lang.String Description) {
    this.Description = Description;
  }

  /**
   *
   */

  public java.util.Set<SysMethod> getMethods() {
    return this.methods;
  }

  public void setMethods(java.util.Set<SysMethod> methods) {
    this.methods = methods;
  }
}