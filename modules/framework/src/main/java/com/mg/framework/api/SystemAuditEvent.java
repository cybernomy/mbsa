/**
 * SystemAuditEvent.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 *
 */
package com.mg.framework.api;

import java.io.Serializable;
import java.util.Date;

import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.ServerUtils;

/**
 * Событие аудита системы
 * 
 * @author Oleg V. Safonov
 * @version $Id: SystemAuditEvent.java,v 1.2 2008/05/29 08:35:13 safonov Exp $
 */
public class SystemAuditEvent implements Serializable {
	private Integer systemTenantId;
	private Date eventDateTime;
	private String userName;
	private String beanName;
	private String operation;
	private String details;
	
	public SystemAuditEvent(Integer systemTenantId, String userName,
			String beanName, String operation, String details) {
		super();
		this.eventDateTime = DateTimeUtils.nowDate();
		this.systemTenantId = systemTenantId;
		this.userName = userName;
		this.beanName = beanName;
		this.operation = operation;
		this.details = details;
	}

	public SystemAuditEvent(String userName, String beanName, String operation,
			String details) {
		this(ServerUtils.getSystemTenantId(), userName, beanName, operation, details);
	}

	/**
	 * @return the systemTenantId
	 */
	public Integer getSystemTenantId() {
		return systemTenantId;
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
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
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
