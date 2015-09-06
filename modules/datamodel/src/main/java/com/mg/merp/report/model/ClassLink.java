/*
 * ClassLink.java
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
package com.mg.merp.report.model;

/**
 * @author hbm2java
 * @version $Id: ClassLink.java,v 1.3 2005/08/16 09:18:44 safonov Exp $
 */
public class ClassLink extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.core.model.SysClass SysClass;

  private com.mg.merp.report.model.RptMain report;

  private com.mg.merp.core.model.SysClient SysClient;

  // Constructors

  /**
   * default constructor
   */
  public ClassLink() {
  }

  /**
   * constructor with id
   */
  public ClassLink(java.lang.Integer Id) {
    this.Id = Id;
  }

  // Property accessors

  /**
   *
   */

  public java.lang.Integer getId() {
    return this.Id;
  }

  public void setId(java.lang.Integer Id) {
    this.Id = Id;
  }

  /**
   *
   */

  public com.mg.merp.core.model.SysClass getSysClass() {
    return this.SysClass;
  }

  public void setSysClass(com.mg.merp.core.model.SysClass SysClass) {
    this.SysClass = SysClass;
  }

  /**
   *
   */

  public com.mg.merp.report.model.RptMain getReport() {
    return this.report;
  }

  public void setReport(com.mg.merp.report.model.RptMain Rptmain) {
    this.report = Rptmain;
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

}