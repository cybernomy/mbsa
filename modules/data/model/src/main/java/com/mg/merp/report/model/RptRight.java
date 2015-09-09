/*
 * RptRight.java
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
 * @version $Id: RptRight.java,v 1.3 2005/08/16 09:18:44 safonov Exp $
 */
public class RptRight extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.security.model.Groups SecGroups;

  private com.mg.merp.report.model.RptMain Rpt;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.lang.Integer Rights;

  // Constructors

  /**
   * default constructor
   */
  public RptRight() {
  }

  /**
   * constructor with id
   */
  public RptRight(java.lang.Integer Id) {
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

  public com.mg.merp.security.model.Groups getSecGroups() {
    return this.SecGroups;
  }

  public void setSecGroups(com.mg.merp.security.model.Groups SecGroups) {
    this.SecGroups = SecGroups;
  }

  /**
   *
   */

  public com.mg.merp.report.model.RptMain getRpt() {
    return this.Rpt;
  }

  public void setRpt(com.mg.merp.report.model.RptMain Rptmain) {
    this.Rpt = Rptmain;
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

  public java.lang.Integer getRights() {
    return this.Rights;
  }

  public void setRights(java.lang.Integer Rights) {
    this.Rights = Rights;
  }

}