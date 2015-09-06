/**
 * SecurityAuditType.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.framework.api.security;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * Тип аудита безопасности
 *
 * @author Oleg V. Safonov
 * @version $Id: SecurityAuditType.java,v 1.1 2007/10/19 06:31:34 safonov Exp $
 */
@DataItemName("Core.SecurityAuditType")
public enum SecurityAuditType {
  /**
   * ошибка
   */
  @EnumConstantText("resource://com.mg.merp.core.resources.dataitemlabels#SecurityAuditType.Error")
  ERROR,
  /**
   * предупреждение
   */
  @EnumConstantText("resource://com.mg.merp.core.resources.dataitemlabels#SecurityAuditType.Warning")
  WARNING,
  /**
   * информация
   */
  @EnumConstantText("resource://com.mg.merp.core.resources.dataitemlabels#SecurityAuditType.Information")
  INFORMATION
}
