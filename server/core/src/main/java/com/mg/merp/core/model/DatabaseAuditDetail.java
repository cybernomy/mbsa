/**
 * DatabaseAuditDetail.java
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
 * @version $Id: DatabaseAuditDetail.java,v 1.1 2007/10/19 06:41:13 safonov Exp $
 */
@DataItemName("Core.DatabaseAuditDetail")
public class DatabaseAuditDetail extends PersistentObjectHibernate implements
		Serializable {
	private Long id;
	private DatabaseAudit databaseAudit;
	private String propertyName;
	private String state;
	private String oldState;

	// Constructors

	/** default constructor */
	public DatabaseAuditDetail() {
	}

	/** constructor with id */
	public DatabaseAuditDetail(Long id) {
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
	 * @return the databaseAudit
	 */
	public DatabaseAudit getDatabaseAudit() {
		return databaseAudit;
	}

	/**
	 * @param databaseAudit the databaseAudit to set
	 */
	public void setDatabaseAudit(DatabaseAudit databaseAudit) {
		this.databaseAudit = databaseAudit;
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
	 * @return the state
	 */
	@DataItemName("Core.DatabaseAuditDetail.State")
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the oldState
	 */
	@DataItemName("Core.DatabaseAuditDetail.OldState")
	public String getOldState() {
		return oldState;
	}

	/**
	 * @param oldState the oldState to set
	 */
	public void setOldState(String oldState) {
		this.oldState = oldState;
	}

}
