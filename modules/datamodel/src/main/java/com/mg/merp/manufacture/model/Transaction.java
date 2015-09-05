/*
 * Transaction.java
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
package com.mg.merp.manufacture.model;

/**
 * @author hbm2java
 * @version $Id: Transaction.java,v 1.3 2007/07/30 10:27:49 safonov Exp $
 */
public class Transaction extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.manufacture.model.JobRouteResource JobRouteResource;

	private com.mg.merp.document.model.DocSpec DocSpec;

	private com.mg.merp.reference.model.Contractor Employee;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.mfreference.model.CostCategories CostCategory;

	private com.mg.merp.mfreference.model.WorkCenter WC;

	private com.mg.merp.mfreference.model.Crew Crew;

	private com.mg.merp.reference.model.Contractor Contractor;

	private com.mg.merp.manufacture.model.JobRoute Oper;

	private com.mg.merp.manufacture.model.Job Job;

	private boolean BackFlushFlag;

	private java.math.BigDecimal Quantity;

	private java.math.BigDecimal Summa;

	// Constructors

	/** default constructor */
	public Transaction() {
	}

	/** constructor with id */
	public Transaction(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */

	public java.lang.Integer getId() {
		return this.Id;
	}

	public void setId(java.lang.Integer Id) {
		this.Id = Id;
	}

	/**
	 * 
	 */

	public com.mg.merp.manufacture.model.JobRouteResource getJobRouteResource() {
		return this.JobRouteResource;
	}

	public void setJobRouteResource(
			com.mg.merp.manufacture.model.JobRouteResource MfJobRouteResource) {
		this.JobRouteResource = MfJobRouteResource;
	}

	/**
	 * 
	 */

	public com.mg.merp.document.model.DocSpec getDocSpec() {
		return this.DocSpec;
	}

	public void setDocSpec(com.mg.merp.document.model.DocSpec Docspec) {
		this.DocSpec = Docspec;
	}

	/**
	 * 
	 */

	public com.mg.merp.reference.model.Contractor getEmployee() {
		return this.Employee;
	}

	public void setEmployee(com.mg.merp.reference.model.Contractor Contractor) {
		this.Employee = Contractor;
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

	public com.mg.merp.mfreference.model.CostCategories getCostCategory() {
		return this.CostCategory;
	}

	public void setCostCategory(
			com.mg.merp.mfreference.model.CostCategories MfCostCategories) {
		this.CostCategory = MfCostCategories;
	}

	/**
	 * 
	 */

	public com.mg.merp.mfreference.model.WorkCenter getWC() {
		return this.WC;
	}

	public void setWC(com.mg.merp.mfreference.model.WorkCenter MfWorkCenter) {
		this.WC = MfWorkCenter;
	}

	/**
	 * 
	 */

	public com.mg.merp.mfreference.model.Crew getCrew() {
		return this.Crew;
	}

	public void setCrew(com.mg.merp.mfreference.model.Crew MfCrew) {
		this.Crew = MfCrew;
	}

	/**
	 * 
	 */

	public com.mg.merp.reference.model.Contractor getContractor() {
		return this.Contractor;
	}

	public void setContractor(
			com.mg.merp.reference.model.Contractor Contractor_1) {
		this.Contractor = Contractor_1;
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

	public com.mg.merp.manufacture.model.Job getJob() {
		return this.Job;
	}

	public void setJob(com.mg.merp.manufacture.model.Job MfJob) {
		this.Job = MfJob;
	}

	/**
	 * 
	 */

	public boolean getBackFlushFlag() {
		return this.BackFlushFlag;
	}

	public void setBackFlushFlag(boolean BackflushFlag) {
		this.BackFlushFlag = BackflushFlag;
	}

	/**
	 * 
	 */

	public java.math.BigDecimal getQuantity() {
		return this.Quantity;
	}

	public void setQuantity(java.math.BigDecimal Quantity) {
		this.Quantity = Quantity;
	}

	/**
	 * 
	 */

	public java.math.BigDecimal getSumma() {
		return this.Summa;
	}

	public void setSumma(java.math.BigDecimal Summa) {
		this.Summa = Summa;
	}

}