/**
 * SecurityAudit.java
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
import com.mg.framework.api.security.SecurityAuditType;
import com.mg.framework.service.PersistentObjectHibernate;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Oleg V. Safonov
 * @version $Id: SecurityAudit.java,v 1.1 2007/10/19 06:41:14 safonov Exp $
 */
@DataItemName("Core.SecurityAudit")
public class SecurityAudit extends PersistentObjectHibernate implements
    Serializable {
  private Long id;
  private SysClient sysClient;
  private String userName;
  private Date eventDateTime;
  private SecurityAuditType auditType;
  private String auditBean;
  private String details;

  // Constructors

  /**
   * default constructor
   */
  public SecurityAudit() {
  }

  /**
   * constructor with id
   */
  public SecurityAudit(Long id) {
    this.id = id;
  }

  /**
   * @return the id
   */
  @DataItemName("ID")
  public Long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @return the sysClient
   */
  public SysClient getSysClient() {
    return sysClient;
  }

  /**
   * @param sysClient the sysClient to set
   */
  public void setSysClient(SysClient sysClient) {
    this.sysClient = sysClient;
  }

  /**
   * @return the userName
   */
  @DataItemName("Security.User.Name")
  public String getUserName() {
    return userName;
  }

  /**
   * @param userName the userName to set
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   * @return the eventDateTime
   */
  @DataItemName("Core.DatabaseAudit.EventDateTime")
  public Date getEventDateTime() {
    return eventDateTime;
  }

  /**
   * @param eventDateTime the eventDateTime to set
   */
  public void setEventDateTime(Date eventDateTime) {
    this.eventDateTime = eventDateTime;
  }

  /**
   * @return the auditType
   */
  public SecurityAuditType getAuditType() {
    return auditType;
  }

  /**
   * @param auditType the auditType to set
   */
  public void setAuditType(SecurityAuditType auditType) {
    this.auditType = auditType;
  }

  /**
   * @return the auditBean
   */
  @DataItemName("Core.SecurityAudit.AuditBean")
  public String getAuditBean() {
    return auditBean;
  }

  /**
   * @param auditBean the auditBean to set
   */
  public void setAuditBean(String auditBean) {
    this.auditBean = auditBean;
  }

  /**
   * @return the details
   */
  @DataItemName("Core.Audit.Details")
  public String getDetails() {
    return details;
  }

  /**
   * @param details the details to set
   */
  public void setDetails(String details) {
    this.details = details;
  }

}
