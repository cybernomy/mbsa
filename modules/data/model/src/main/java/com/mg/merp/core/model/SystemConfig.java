/*
 * SystemConfig.java
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


/**
 * @author hbm2java
 * @version $Id: SystemConfig.java,v 1.1 2005/06/10 06:52:30 safonov Exp $
 */
public class SystemConfig extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private com.mg.merp.core.model.SystemConfigId id;


  // Constructors

  /**
   * default constructor
   */
  public SystemConfig() {
  }

  /**
   * constructor with id
   */
  public SystemConfig(com.mg.merp.core.model.SystemConfigId id) {
    this.id = id;
  }


  // Property accessors

  /**

   */

  public com.mg.merp.core.model.SystemConfigId getId() {
    return this.id;
  }

  public void setId(com.mg.merp.core.model.SystemConfigId id) {
    this.id = id;
  }


}