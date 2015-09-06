/*
 * SecuritySystemServiceMBean.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.security.support.jboss;

import com.mg.framework.api.security.SecuritySystem;

import org.jboss.system.ServiceMBean;

/**
 * MBean для системы безопасности функционирующий в среде JBoss
 *
 * @author Oleg V. Safonov
 * @version $Id$
 */
public interface SecuritySystemServiceMBean extends SecuritySystem, ServiceMBean {

  /**
   * обновить права из хранилища
   */
  void refreshPermissions();

}
