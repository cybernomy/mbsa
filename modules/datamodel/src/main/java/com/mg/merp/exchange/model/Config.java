/*
 * Config.java
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
package com.mg.merp.exchange.model;


/**
 * @author hbm2java
 * @version $Id: Config.java,v 1.2 2006/03/14 11:55:29 safonov Exp $
 */
public class Config extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private com.mg.merp.core.model.SysClient SysClient;
  private java.lang.String SiteCode;


  // Constructors

  /**
   * default constructor
   */
  public Config() {
  }

  /**
   * constructor with id
   */
  public Config(com.mg.merp.core.model.SysClient SysClient) {
    this.SysClient = SysClient;
  }

  public java.lang.String getSiteCode() {
    return SiteCode;
  }

  public void setSiteCode(java.lang.String siteCode) {
    SiteCode = siteCode;
  }

  public com.mg.merp.core.model.SysClient getSysClient() {
    return SysClient;
  }

  public void setSysClient(com.mg.merp.core.model.SysClient sysClient) {
    SysClient = sysClient;
  }


}