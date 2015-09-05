/**
 * DatabaseAuditSetup.java
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

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.service.PersistentObjectHibernate;

/**
 * @author Oleg V. Safonov
 * @version $Id: DatabaseAuditSetup.java,v 1.2 2007/12/14 13:35:23 safonov Exp $
 */
@DataItemName("Core.DatabaseAuditSetup")
public class DatabaseAuditSetup extends PersistentObjectHibernate implements
		Serializable {
	private Integer id;
	private SysClient sysClient;
	private String auditedEntityName;
	private String propertyName;
	private Boolean auditCreate;
	private Boolean auditModify;
	private Boolean auditRemove;
	
	// Constructors

	/** default constructor */
	public DatabaseAuditSetup() {
	}

	/** constructor with id */
	public DatabaseAuditSetup(Integer id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	@DataItemName("ID")
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
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

	/**
	 * @return the propertyName
	 */
	@DataItemName("Core.PropertyName")
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * @param propertyName the propertyName to set
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	/**
	 * @return the auditCreate
	 */
	@DataItemName("Core.DatabaseAuditSetup.AuditCreate")
	public Boolean getAuditCreate() {
		return auditCreate;
	}

	/**
	 * @param auditCreate the auditCreate to set
	 */
	public void setAuditCreate(Boolean auditCreate) {
		this.auditCreate = auditCreate;
	}

	/**
	 * @return the auditModify
	 */
	@DataItemName("Core.DatabaseAuditSetup.AuditModify")
	public Boolean getAuditModify() {
		return auditModify;
	}

	/**
	 * @param auditModify the auditModify to set
	 */
	public void setAuditModify(Boolean auditModify) {
		this.auditModify = auditModify;
	}

	/**
	 * @return the auditRemove
	 */
	@DataItemName("Core.DatabaseAuditSetup.AuditRemove")
	public Boolean getAuditRemove() {
		return auditRemove;
	}

	/**
	 * @param auditRemove the auditRemove to set
	 */
	public void setAuditRemove(Boolean auditRemove) {
		this.auditRemove = auditRemove;
	}

}
