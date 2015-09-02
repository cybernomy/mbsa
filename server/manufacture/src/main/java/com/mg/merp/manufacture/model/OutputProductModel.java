/*
 * OutputProductModel.java
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
 * @version $Id: OutputProductModel.java,v 1.4 2006/04/13 10:14:52 safonov Exp $
 */
public class OutputProductModel extends com.mg.merp.document.model.DocHeadModel
		implements java.io.Serializable {

	// Fields

	private com.mg.merp.reference.model.Contractor Employee;

	private com.mg.merp.mfreference.model.WorkCenter WC;

	private com.mg.merp.mfreference.model.Crew Crew;

	private com.mg.merp.reference.model.Contractor Contractor;

	private com.mg.merp.manufacture.model.JobRoute Oper;

	private com.mg.merp.manufacture.model.Job Job;

	// Constructors

	/** default constructor */
	public OutputProductModel() {
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("Manufacture.OutputProductModel.Employee")
	public com.mg.merp.reference.model.Contractor getEmployee() {
		return this.Employee;
	}

	public void setEmployee(com.mg.merp.reference.model.Contractor Contractor) {
		this.Employee = Contractor;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.OutputProductModel.WC")
	public com.mg.merp.mfreference.model.WorkCenter getWC() {
		return this.WC;
	}

	public void setWC(com.mg.merp.mfreference.model.WorkCenter MfWorkCenter) {
		this.WC = MfWorkCenter;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.OutputProductModel.Crew")
	public com.mg.merp.mfreference.model.Crew getCrew() {
		return this.Crew;
	}

	public void setCrew(com.mg.merp.mfreference.model.Crew MfCrew) {
		this.Crew = MfCrew;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.OutputProductModel.Contractor")
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
	@DataItemName("Manufacture.OutputProductModel.Oper")
	public com.mg.merp.manufacture.model.JobRoute getOper() {
		return this.Oper;
	}

	public void setOper(com.mg.merp.manufacture.model.JobRoute MfJobRoute) {
		this.Oper = MfJobRoute;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.OutputProductModel.Job")
	public com.mg.merp.manufacture.model.Job getJob() {
		return this.Job;
	}

	public void setJob(com.mg.merp.manufacture.model.Job MfJob) {
		this.Job = MfJob;
	}

}