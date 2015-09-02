/*
 * ResourceFamily.java
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
package com.mg.merp.mfreference.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: ResourceFamily.java,v 1.3 2006/07/12 09:33:30 leonova Exp $
 */
public class ResourceFamily extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.mfreference.model.PlanningLevel PlanningLevel;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.mfreference.model.ResourceGroup ChildResourceGroup;

	private com.mg.merp.mfreference.model.ResourceGroup ParentResourceGroup;

	// Constructors

	/** default constructor */
	public ResourceFamily() {
	}

	/** constructor with id */
	public ResourceFamily(java.lang.Integer Id) {
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
	public com.mg.merp.mfreference.model.PlanningLevel getPlanningLevel() {
		return this.PlanningLevel;
	}

	public void setPlanningLevel(
			com.mg.merp.mfreference.model.PlanningLevel PlanningLevel) {
		this.PlanningLevel = PlanningLevel;
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

	@DataItemName("MfReference.ResFam.ChildRG")
	public com.mg.merp.mfreference.model.ResourceGroup getChildResourceGroup() {
		return this.ChildResourceGroup;
	}

	public void setChildResourceGroup(
			com.mg.merp.mfreference.model.ResourceGroup ChildResourceGroup) {
		this.ChildResourceGroup = ChildResourceGroup;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.ResFam.ParentRG")
	public com.mg.merp.mfreference.model.ResourceGroup getParentResourceGroup() {
		return this.ParentResourceGroup;
	}

	public void setParentResourceGroup(
			com.mg.merp.mfreference.model.ResourceGroup ParentResourceGroup) {
		this.ParentResourceGroup = ParentResourceGroup;
	}

}