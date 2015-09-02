/**
 * EntityAuditEvent.java
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
package com.mg.framework.api.orm;

import java.io.Serializable;
import java.util.Date;

import com.mg.framework.api.Session;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.ServerUtils;

/**
 * Событие аудита хранилища данных
 * 
 * @author Oleg V. Safonov
 * @version $Id: EntityAuditEvent.java,v 1.2 2008/05/29 08:34:09 safonov Exp $
 */
public class EntityAuditEvent implements Serializable {
	private Integer systemTenantId;
	private String entityName;
	private DatabaseAuditType auditType;
	private Date eventDateTime;
	private String userName;
	private String identifier;
	private String identifierPropertyName;
	private String[] names;
	private String[] state;
	private String[] oldState;

	public EntityAuditEvent(String entityName, DatabaseAuditType auditType, String identifier, String identifierPropertyName, String[] names, String[] state,
			String[] oldState) {
		super();
		this.eventDateTime = DateTimeUtils.nowDate();
		this.entityName = entityName;
		this.auditType = auditType;
		this.names = names;
		this.state = state;
		this.oldState = oldState;
		this.identifier = identifier;
		this.identifierPropertyName = identifierPropertyName;
		this.systemTenantId = ServerUtils.getSystemTenantId();
		Session session = ServerUtils.getCurrentSession();
		if (session != null) {
			this.userName = session.getWorkingConnection().getUserProfile().getUserName();
		}
		else {
			this.userName = "system";
		}
	}

	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * @return the identifierPropertyName
	 */
	public String getIdentifierPropertyName() {
		return identifierPropertyName;
	}

	/**
	 * @return the systemTenantId
	 */
	public Integer getSystemTenantId() {
		return systemTenantId;
	}

	/**
	 * @return the entityName
	 */
	public String getEntityName() {
		return entityName;
	}
	/**
	 * @return the names
	 */
	public String[] getNames() {
		return names;
	}
	/**
	 * @return the state
	 */
	public String[] getState() {
		return state;
	}
	/**
	 * @return the oldState
	 */
	public String[] getOldState() {
		return oldState;
	}
	/**
	 * @return the auditType
	 */
	public DatabaseAuditType getAuditType() {
		return auditType;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @return the eventDateTime
	 */
	public Date getEventDateTime() {
		return eventDateTime;
	}

}
