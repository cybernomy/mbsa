/*
 * OutputProductHead.java
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
 * @version $Id: OutputProductHead.java,v 1.6 2008/02/29 12:34:30 safonov Exp $
 */
public class OutputProductHead extends com.mg.merp.document.model.DocHead
		implements java.io.Serializable, org.hibernate.bytecode.javassist.FieldHandled {

	// Fields

	private com.mg.merp.reference.model.Contractor Employee;

	private com.mg.merp.mfreference.model.WorkCenter WC;

	private com.mg.merp.mfreference.model.Crew Crew;

	private com.mg.merp.reference.model.Contractor Contractor;

	private com.mg.merp.manufacture.model.JobRoute Oper;

	private com.mg.merp.manufacture.model.Job Job;

	// Constructors

	/** default constructor */
	public OutputProductHead() {
	}

	// Property accessors

	@DataItemName("Manufacture.OutputProductHead.Employee")
	public com.mg.merp.reference.model.Contractor getEmployee() {
		return this.Employee;
	}

	public void setEmployee(com.mg.merp.reference.model.Contractor Contractor) {
		this.Employee = Contractor;
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
	@DataItemName("Manufacture.OutputProductHead.Contractor")
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
	@DataItemName("Manufacture.OutputProductHead.Oper")
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

}