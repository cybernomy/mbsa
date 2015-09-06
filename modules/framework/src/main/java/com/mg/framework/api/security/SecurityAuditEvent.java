/**
 * SecurityAuditEvent.java
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

import com.mg.framework.utils.DateTimeUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * Событие аудита безопасности
 *
 * @author Oleg V. Safonov
 * @version $Id: SecurityAuditEvent.java,v 1.2 2008/05/29 08:34:42 safonov Exp $
 */
public class SecurityAuditEvent implements Serializable {
  private Integer systemTenantId;
  private SecurityAuditType auditType;
  private Date eventDateTime;
  private String userName;
  private String beanName;
  private String details;

  public SecurityAuditEvent(Integer systemTenantId, SecurityAuditType auditType,
                            String userName, String beanName, String details) {
    super();
    this.eventDateTime = DateTimeUtils.nowDate();
    this.systemTenantId = systemTenantId;
    this.auditType = auditType;
    this.userName = userName;
    this.beanName = beanName;
    this.details = details;
  }

  /**
   * @return the systemTenantId
   */
  public Integer getSystemTenantId() {
    return systemTenantId;
  }

  /**
   * @return the auditType
   */
  public SecurityAuditType getAuditType() {
    return auditType;
  }

  /**
   * @return the userName
   */
  public String getUserName() {
    return userName;
  }

  /**
   * @return the beanName
   */
  public String getBeanName() {
    return beanName;
  }

  /**
   * @return the details
   */
  public String getDetails() {
    return details;
  }

  /**
   * @return the eventDateTime
   */
  public Date getEventDateTime() {
    return eventDateTime;
  }

}
