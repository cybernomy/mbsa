/*
 * ResourceGroup.java
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
 * @version $Id: ResourceGroup.java,v 1.5 2006/07/12 09:33:30 leonova Exp $
 */
@DataItemName("MfReference.ResourceGroup")
public class ResourceGroup extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.reference.model.Measure Measure;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.mfreference.model.PlanningLevel PlanningLevel;

	private java.lang.String ResourceGroupCode;

	private java.lang.String Description;

	private ResourceGroupType ResourceType;

	private boolean LimitedResourceFlag;

	private java.lang.String Comment;

	// Constructors

	/** default constructor */
	public ResourceGroup() {
	}

	/** constructor with id */
	public ResourceGroup(java.lang.Integer Id) {
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

	@DataItemName("MfReference.ResourGroup.Measure")
	public com.mg.merp.reference.model.Measure getMeasure() {
		return this.Measure;
	}

	public void setMeasure(com.mg.merp.reference.model.Measure Measure) {
		this.Measure = Measure;
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

	@DataItemName("MfReference.ResourGroup.RGCode")
	public java.lang.String getResourceGroupCode() {
		return this.ResourceGroupCode;
	}

	public void setResourceGroupCode(java.lang.String ResourceGroupCode) {
		this.ResourceGroupCode = ResourceGroupCode;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.ResourGroup.Descr")
	public java.lang.String getDescription() {
		return this.Description;
	}

	public void setDescription(java.lang.String Description) {
		this.Description = Description;
	}

	/**
	 * 
	 */

	public ResourceGroupType getResourceType() {
		return this.ResourceType;
	}

	public void setResourceType(ResourceGroupType ResourceType) {
		this.ResourceType = ResourceType;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.ResGroup.LimResFlag")
	public boolean getLimitedResourceFlag() {
		return this.LimitedResourceFlag;
	}

	public void setLimitedResourceFlag(boolean LimitedResourceFlag) {
		this.LimitedResourceFlag = LimitedResourceFlag;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.ResourGroup.Comment")
	public java.lang.String getComment() {
		return this.Comment;
	}

	public void setComment(java.lang.String Comment) {
		this.Comment = Comment;
	}
}