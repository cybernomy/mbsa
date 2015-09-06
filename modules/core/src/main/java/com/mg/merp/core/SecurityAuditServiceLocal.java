/**
 * SecurityAuditServiceLocal.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.merp.core;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.merp.core.model.SecurityAudit;

/**
 * Бизнес-компонент аудита безопасности
 *
 * @author Oleg V. Safonov
 * @version $Id: SecurityAuditServiceLocal.java,v 1.1 2007/10/19 06:40:17 safonov Exp $
 */
public interface SecurityAuditServiceLocal extends DataBusinessObjectService<SecurityAudit, Long> {
  /**
   * имя сервиса
   */
  final static String SERVICE_NAME = "merp/core/SecurityAudit";
}
