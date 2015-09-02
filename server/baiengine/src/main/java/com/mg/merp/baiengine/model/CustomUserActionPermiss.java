/*
 * CustomUserActionPermiss.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.merp.baiengine.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: CustomUserActionPermiss.java,v 1.3 2007/11/15 13:13:51 safonov Exp $
 */
@DataItemName("BAi.CustomUserActionPermiss")
public class CustomUserActionPermiss extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer id;
	private com.mg.merp.baiengine.model.CustomUserAction customUserAction;
	private com.mg.merp.core.model.SysClient sysClient;
	private com.mg.merp.security.model.Groups secGroup;

	// Constructors

	/** default constructor */
	public CustomUserActionPermiss() {
	}

	/** constructor with id */
	public CustomUserActionPermiss(java.lang.Integer Id) {
		this.id = Id;
	}

	// Property accessors
	/**
	 * 
	 */

	public java.lang.Integer getId() {
		return this.id;
	}

	public void setId(java.lang.Integer Id) {
		this.id = Id;
	}

	/**
	 * 
	 */

	public com.mg.merp.baiengine.model.CustomUserAction getCustomUserAction() {
		return this.customUserAction;
	}

	public void setCustomUserAction(
			com.mg.merp.baiengine.model.CustomUserAction customUserAction) {
		this.customUserAction = customUserAction;
	}

	/**
	 * 
	 */

	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.sysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.sysClient = SysClient;
	}

	/**
	 * 
	 */

	public com.mg.merp.security.model.Groups getSecGroup() {
		return this.secGroup;
	}

	public void setSecGroup(com.mg.merp.security.model.Groups SecGroups) {
		this.secGroup = SecGroups;
	}

}