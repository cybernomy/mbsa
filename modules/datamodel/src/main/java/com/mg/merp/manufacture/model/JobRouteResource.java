/*
 * JobRouteResource.java
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
package com.mg.merp.manufacture.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: JobRouteResource.java,v 1.5 2006/09/09 09:36:29 leonova Exp $
 */
public class JobRouteResource extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.mfreference.model.ResourceGroup ResourceGroup;

	private com.mg.merp.mfreference.model.CostDetail ActCostDetail;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.mfreference.model.CostDetail StdCostDetail;

	private com.mg.merp.manufacture.model.JobRoute Oper;

	private java.lang.Short ResourceType;

	private java.lang.Integer TimeSequence;

	private java.util.Date EffOnDate;

	private java.util.Date EffOffDate;

	private java.lang.String Comment;

	// Constructors

	/** default constructor */
	public JobRouteResource() {
	}

	/** constructor with id */
	public JobRouteResource(java.lang.Integer Id) {
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
	@DataItemName("Manufacture.JobRouteResource.ResourceGroup")
	public com.mg.merp.mfreference.model.ResourceGroup getResourceGroup() {
		return this.ResourceGroup;
	}

	public void setResourceGroup(
			com.mg.merp.mfreference.model.ResourceGroup MfResourceGroup) {
		this.ResourceGroup = MfResourceGroup;
	}

	/**
	 * 
	 */

	public com.mg.merp.mfreference.model.CostDetail getActCostDetail() {
		return this.ActCostDetail;
	}

	public void setActCostDetail(
			com.mg.merp.mfreference.model.CostDetail MfCostDetail) {
		this.ActCostDetail = MfCostDetail;
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

	public com.mg.merp.mfreference.model.CostDetail getStdCostDetail() {
		return this.StdCostDetail;
	}

	public void setStdCostDetail(
			com.mg.merp.mfreference.model.CostDetail MfCostDetail_1) {
		this.StdCostDetail = MfCostDetail_1;
	}

	/**
	 * 
	 */

	public com.mg.merp.manufacture.model.JobRoute getOper() {
		return this.Oper;
	}

	public void setOper(com.mg.merp.manufacture.model.JobRoute MfJobRoute) {
		this.Oper = MfJobRoute;
	}

	/**
	 * 
	 */

	public java.lang.Short getResourceType() {
		return this.ResourceType;
	}

	public void setResourceType(java.lang.Short ResourceType) {
		this.ResourceType = ResourceType;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobRouteResource.TimeSequence")
	public java.lang.Integer getTimeSequence() {
		return this.TimeSequence;
	}

	public void setTimeSequence(java.lang.Integer TimeSequence) {
		this.TimeSequence = TimeSequence;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobRouteResource.EffOnDate")
	public java.util.Date getEffOnDate() {
		return this.EffOnDate;
	}

	public void setEffOnDate(java.util.Date EffOnDate) {
		this.EffOnDate = EffOnDate;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobRouteResource.EffOffDate")
	public java.util.Date getEffOffDate() {
		return this.EffOffDate;
	}

	public void setEffOffDate(java.util.Date EffOffDate) {
		this.EffOffDate = EffOffDate;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobRouteResource.Comment")
	public java.lang.String getComment() {
		return this.Comment;
	}

	public void setComment(java.lang.String Comment) {
		this.Comment = Comment;
	}
}