/*
 * DocProcessStageRights.java
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
package com.mg.merp.docprocess.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: DocProcessStageRights.java,v 1.2 2005/07/26 12:34:59 safonov
 *          Exp $
 */
public class DocProcessStageRights extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.security.model.Groups SecGroups;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.docprocess.model.DocProcessStage docProcessStage;

	private ActionUserGrant Grants;

	// Constructors

	/** default constructor */
	public DocProcessStageRights() {
	}

	/** constructor with id */
	public DocProcessStageRights(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("ID")
	public java.lang.Integer getId() {
		return this.Id;
	}

	public void setId(java.lang.Integer Id) {
		this.Id = Id;
	}

	/**
	 * 
	 */

	public com.mg.merp.security.model.Groups getSecGroups() {
		return this.SecGroups;
	}

	public void setSecGroups(com.mg.merp.security.model.Groups SecGroups) {
		this.SecGroups = SecGroups;
	}

	/**
	 * 
	 */

	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.SysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	/**
	 * 
	 */

	public com.mg.merp.docprocess.model.DocProcessStage getDocProcessStage() {
		return this.docProcessStage;
	}

	public void setDocProcessStage(
			com.mg.merp.docprocess.model.DocProcessStage Docprocessstage) {
		this.docProcessStage = Docprocessstage;
	}

	/**
	 * 
	 */

	public ActionUserGrant getGrants() {
		return this.Grants;
	}

	public void setGrants(ActionUserGrant Grants) {
		this.Grants = Grants;
	}

}