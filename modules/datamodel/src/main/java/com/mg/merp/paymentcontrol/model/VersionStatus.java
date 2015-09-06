/*
 * VersionStatus.java
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
package com.mg.merp.paymentcontrol.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: VersionStatus.java,v 1.4 2007/02/06 09:28:31 safonov Exp $
 */
public class VersionStatus extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer id;

  private com.mg.merp.core.model.SysClient sysClient;

  private com.mg.merp.security.model.SecUser creator;

  private com.mg.merp.paymentcontrol.model.Version version;

  private VersionStatusKind kind;

  private java.util.Date dateTill;

  private java.util.Date dateFrom;

  private java.util.Date createDate;

  private java.util.Set<Liability> liabilities;

  private java.util.Set<Execution> executions;

  // Constructors

  /**
   * default constructor
   */
  public VersionStatus() {
  }

  /**
   * constructor with id
   */
  public VersionStatus(java.lang.Integer Id) {
    this.id = Id;
  }

  // Property accessors

  /**
   *
   */
  @DataItemName("ID")
  public java.lang.Integer getId() {
    return this.id;
  }

  public void setId(java.lang.Integer Id) {
    this.id = Id;
  }

  /**
   *
   */

  public com.mg.merp.core.model.SysClient getSysClient() {
    return this.sysClient;
  }

  public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
    this.sysClient = SysClient;
  }

  /**
   *
   */
  @DataItemName("PaymentControl.Version.Creator")
  public com.mg.merp.security.model.SecUser getCreator() {
    return this.creator;
  }

  public void setCreator(com.mg.merp.security.model.SecUser SecUsers) {
    this.creator = SecUsers;
  }

  /**
   *
   */

  public com.mg.merp.paymentcontrol.model.Version getVersion() {
    return this.version;
  }

  public void setVersion(com.mg.merp.paymentcontrol.model.Version PmcVersion) {
    this.version = PmcVersion;
  }

  /**
   *
   */

  public VersionStatusKind getKind() {
    return this.kind;
  }

  public void setKind(VersionStatusKind Kind) {
    this.kind = Kind;
  }

  /**
   *
   */
  @DataItemName("PaymentControl.VersionStatus.DateTill")
  public java.util.Date getDateTill() {
    return this.dateTill;
  }

  public void setDateTill(java.util.Date Datetill) {
    this.dateTill = Datetill;
  }

  /**
   *
   */
  @DataItemName("PaymentControl.VersionStatus.DateFrom")
  public java.util.Date getDateFrom() {
    return this.dateFrom;
  }

  public void setDateFrom(java.util.Date Datefrom) {
    this.dateFrom = Datefrom;
  }

  /**
   *
   */
  @DataItemName("PaymentControl.Version.CreateDate")
  public java.util.Date getCreateDate() {
    return this.createDate;
  }

  public void setCreateDate(java.util.Date Createdate) {
    this.createDate = Createdate;
  }

  /**
   *
   */

  public java.util.Set<Liability> getLiabilities() {
    return this.liabilities;
  }

  public void setLiabilities(java.util.Set<Liability> SetOfPmcLiability) {
    this.liabilities = SetOfPmcLiability;
  }

  /**
   *
   */

  public java.util.Set<Execution> getExecutions() {
    return this.executions;
  }

  public void setExecutions(java.util.Set<Execution> SetOfPmcExecution) {
    this.executions = SetOfPmcExecution;
  }

}