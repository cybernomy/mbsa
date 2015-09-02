/**
 * DatabaseAudit.java
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
package com.mg.merp.core.model;

import java.io.Serializable;
import java.util.Date;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.orm.DatabaseAuditType;
import com.mg.framework.service.PersistentObjectHibernate;

/**
 * @author Oleg V. Safonov
 * @version $Id: DatabaseAudit.java,v 1.2 2007/12/14 13:35:23 safonov Exp $
 */
@DataItemName("Core.DatabaseAudit")
public class DatabaseAudit extends PersistentObjectHibernate implements
		Serializable {
	private Long id;
	private SysClient sysClient;
	private String userName;
	private Date eventDateTime;
	private DatabaseAuditType auditType;
	private String auditedEntityName;
	
	// Constructors

	/** default constructor */
	public DatabaseAudit() {
	}

	/** constructor with id */
	public DatabaseAudit(Long id) {
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
	@DataItemName ("Security.User.Name")
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
	public DatabaseAuditType getAuditType() {
		return auditType;
	}

	/**
	 * @param auditType the auditType to set
	 */
	public void setAuditType(DatabaseAuditType auditType) {
		this.auditType = auditType;
	}

	/**
	 * @return the entityName
	 */
	@DataItemName("Core.EntityName")
	public String getAuditedEntityName() {
		return auditedEntityName;
	}

	/**
	 * @param entityName the entityName to set
	 */
	public void setAuditedEntityName(String auditedEntityName) {
		this.auditedEntityName = auditedEntityName;
	}

}
