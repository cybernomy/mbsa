/*
 * InvProduction.java
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
package com.mg.merp.account.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.utils.DataUtils;


/**
 * @author hbm2java
 * @version $Id: InvProduction.java,v 1.6 2008/04/28 10:13:27 alikaev Exp $
 */
public class InvProduction extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private java.lang.Integer Id;
  private com.mg.merp.account.model.InvHead AccInvHead;
  private com.mg.merp.core.model.SysClient SysClient;
  private java.lang.Integer AMonth;
  private java.lang.Double Production;
  private com.mg.merp.reference.model.MonthOfYear QMonth;
  private java.lang.Integer QYear;


  // Constructors

  /**
   * default constructor
   */
  public InvProduction() {
  }

  /**
   * constructor with id
   */
  public InvProduction(java.lang.Integer Id) {
    this.Id = Id;
  }


  // Property accessors

  /**

   */
  @DataItemName("ID")
  public java.lang.Integer getId() {
    return this.Id;
  }

  public void setId(java.lang.Integer Id) {
    this.Id = Id;
  }

  /**

   */

  public com.mg.merp.account.model.InvHead getAccInvHead() {
    return this.AccInvHead;
  }

  public void setAccInvHead(com.mg.merp.account.model.InvHead AccInvhead) {
    this.AccInvHead = AccInvhead;
  }

  /**

   */

  public com.mg.merp.core.model.SysClient getSysClient() {
    return this.SysClient;
  }

  public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
    this.SysClient = SysClient;
  }

  /**

   */
  @DataItemName("Account.InvHead.AMonth")
  public java.lang.Integer getAMonth() {
    return this.AMonth;
  }

  public void setAMonth(java.lang.Integer Amonth) {
    this.AMonth = Amonth;
  }

  /**

   */
  @DataItemName("Account.InvHead.Production")
  public java.lang.Double getProduction() {
    return this.Production;
  }


  public void setProduction(java.lang.Double Production) {
    this.Production = Production;
  }


  public com.mg.merp.reference.model.MonthOfYear getQMonth() {
    return this.QMonth;
  }

  public void setQMonth(com.mg.merp.reference.model.MonthOfYear month) {
    this.QMonth = month;
    if (QYear != null) {
      this.AMonth = QYear * 12 + DataUtils.convertEnumToOrdinal(month) + 1;
    }
  }

  @DataItemName("Account.InvHead.QYear")
  public java.lang.Integer getQYear() {
    if (getAMonth() != null) {
      int countMonth = getAMonth();
      return countMonth /= 12;
    } else {
      return this.QYear;
    }
  }

  public void setQYear(java.lang.Integer year) {
    this.QYear = year;
    if (QMonth != null) {
      this.AMonth = year * 12 + DataUtils.convertEnumToOrdinal(QMonth);
    }
  }


}