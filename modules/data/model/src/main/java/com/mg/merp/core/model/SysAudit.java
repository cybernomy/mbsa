/*
 * SysAudit.java
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
 * @version $Id: SysAudit.java,v 1.3 2007/10/19 06:41:14 safonov Exp $
 */
@DataItemName("Core.SystemAudit")
public class SysAudit extends com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private Long Id;
  private com.mg.merp.core.model.SysClient SysClient;
  private String userName;
  private java.util.Date eventDateTime;
  private java.lang.String auditBean;
  private java.lang.String operation;
  private java.lang.String details;

  // Constructors

  /**
   * default constructor
   */
  public SysAudit() {
  }

  /**
   * constructor with id
   */
  public SysAudit(Long Id) {
    this.Id = Id;
  }

  // Property accessors

  /**
   *
   */
  @DataItemName("ID")
  public Long getId() {
    return this.Id;
  }

  public void setId(Long id) {
    this.Id = id;
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
  @DataItemName("Security.User.Name")
  public String getUserName() {
    return this.userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   *
   */
  @DataItemName("Core.DatabaseAudit.EventDateTime")
  public java.util.Date getEventDateTime() {
    return this.eventDateTime;
  }

  public void setEventDateTime(java.util.Date eventDateTime) {
    this.eventDateTime = eventDateTime;
  }

  /**
   *
   */
  @DataItemName("Core.SecurityAudit.AuditBean")
  public java.lang.String getAuditBean() {
    return this.auditBean;
  }

  public void setAuditBean(java.lang.String auditBean) {
    this.auditBean = auditBean;
  }

  /**
   *
   */
  @DataItemName("Core.SystemAudit.Operation")
  public java.lang.String getOperation() {
    return this.operation;
  }

  public void setOperation(java.lang.String operation) {
    this.operation = operation;
  }

  /**
   *
   */
  @DataItemName("Core.Audit.Details")
  public java.lang.String getDetails() {
    return this.details;
  }

  public void setDetails(java.lang.String details) {
    this.details = details;
  }

}