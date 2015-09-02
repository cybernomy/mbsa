/*
 * InputDocumentHead.java
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
 * @version $Id: InputDocumentHead.java,v 1.5 2008/02/29 12:33:25 safonov Exp $
 */
public class InputDocumentHead extends com.mg.merp.document.model.DocHead
		implements java.io.Serializable, org.hibernate.bytecode.javassist.FieldHandled {

	// Fields

	private com.mg.merp.reference.model.Contractor Employee;

	private com.mg.merp.document.model.DocHead OutputDocHead;

	private com.mg.merp.mfreference.model.WorkCenter WC;

	private com.mg.merp.mfreference.model.Crew Crew;

	private com.mg.merp.reference.model.Contractor Contractor;

	private com.mg.merp.manufacture.model.JobRoute Oper;

	private com.mg.merp.manufacture.model.Job Job;

	private boolean BackFlushFlag;

	// Constructors

	/** default constructor */
	public InputDocumentHead() {
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("Manufacture.InputDocHead.Employee")
	public com.mg.merp.reference.model.Contractor getEmployee() {
		return this.Employee;
	}

	public void setEmployee(com.mg.merp.reference.model.Contractor Contractor) {
		this.Employee = Contractor;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.InputDocHead.OutputDocHead")
	public com.mg.merp.document.model.DocHead getOutputDocHead() {
		return this.OutputDocHead;
	}

	public void setOutputDocHead(com.mg.merp.document.model.DocHead Dochead_1) {
		this.OutputDocHead = Dochead_1;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.InputDocHead.WC")
	public com.mg.merp.mfreference.model.WorkCenter getWC() {
		return this.WC;
	}

	public void setWC(com.mg.merp.mfreference.model.WorkCenter MfWorkCenter) {
		this.WC = MfWorkCenter;
	}

	/**
	 * 
	 */

	@DataItemName("Manufacture.InputDocHead.Crew")
	public com.mg.merp.mfreference.model.Crew getCrew() {
		return this.Crew;
	}

	public void setCrew(com.mg.merp.mfreference.model.Crew MfCrew) {
		this.Crew = MfCrew;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.InputDocHead.Contractor")
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

	@DataItemName("Manufacture.InputDocHead.Oper")
	public com.mg.merp.manufacture.model.JobRoute getOper() {
		return this.Oper;
	}

	public void setOper(com.mg.merp.manufacture.model.JobRoute MfJobRoute) {
		this.Oper = MfJobRoute;
	}

	/**
	 * 
	 */

	@DataItemName("Manufacture.InputDocHead.Job")
	public com.mg.merp.manufacture.model.Job getJob() {
		return this.Job;
	}

	public void setJob(com.mg.merp.manufacture.model.Job MfJob) {
		this.Job = MfJob;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.InputDocHead.BackFlushFlag")
	public boolean getBackFlushFlag() {
		return this.BackFlushFlag;
	}

	public void setBackFlushFlag(boolean BackflushFlag) {
		this.BackFlushFlag = BackflushFlag;
	}

}