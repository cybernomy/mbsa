/*
 * ScrapDocumentHead.java
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
 * @version $Id: ScrapDocumentHead.java,v 1.6 2008/02/29 12:35:00 safonov Exp $
 */
public class ScrapDocumentHead extends com.mg.merp.document.model.DocHead
		implements java.io.Serializable, org.hibernate.bytecode.javassist.FieldHandled {

	// Fields

	private com.mg.merp.manufacture.model.Job DetectJob;

	private com.mg.merp.reference.model.Contractor CauseContractor;

	private com.mg.merp.qualitycontrol.model.ScrapType ScrapType;

	private com.mg.merp.qualitycontrol.model.ScrapReason ScrapReason;

	private com.mg.merp.reference.model.Contractor CauseEmployee;

	private com.mg.merp.mfreference.model.Crew CauseCrew;

	private com.mg.merp.manufacture.model.Job CauseJob;

	private com.mg.merp.mfreference.model.WorkCenter DetectWC;

	private com.mg.merp.mfreference.model.Crew DetectCrew;

	private com.mg.merp.manufacture.model.JobRoute DetectOper;

	private com.mg.merp.reference.model.Contractor DetectEmployee;

	private com.mg.merp.manufacture.model.JobRoute CauseOper;

	private com.mg.merp.reference.model.Contractor DetectContractor;

	private com.mg.merp.mfreference.model.WorkCenter CauseWC;

	// Constructors

	/** default constructor */
	public ScrapDocumentHead() {
	}

	// Property accessors
	/**
	 * 
	 */
	
	public com.mg.merp.manufacture.model.Job getDetectJob() {
		return this.DetectJob;
	}

	public void setDetectJob(com.mg.merp.manufacture.model.Job MfJob) {
		this.DetectJob = MfJob;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.ScrapDocHead.CauseContractor")
	public com.mg.merp.reference.model.Contractor getCauseContractor() {
		return this.CauseContractor;
	}

	public void setCauseContractor(
			com.mg.merp.reference.model.Contractor Contractor) {
		this.CauseContractor = Contractor;
	}

	@DataItemName("Manufacture.ScrapDocHead.ScrapType")
	public com.mg.merp.qualitycontrol.model.ScrapType getScrapType() {
		return this.ScrapType;
	}

	public void setScrapType(
			com.mg.merp.qualitycontrol.model.ScrapType QcScrapType) {
		this.ScrapType = QcScrapType;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.ScrapDocHead.ScrapReason")
	public com.mg.merp.qualitycontrol.model.ScrapReason getScrapReason() {
		return this.ScrapReason;
	}

	public void setScrapReason(
			com.mg.merp.qualitycontrol.model.ScrapReason QcScrapReason) {
		this.ScrapReason = QcScrapReason;
	}

	/**
	 * 
	 */	
	public com.mg.merp.reference.model.Contractor getCauseEmployee() {
		return this.CauseEmployee;
	}

	public void setCauseEmployee(
			com.mg.merp.reference.model.Contractor Contractor_1) {
		this.CauseEmployee = Contractor_1;
	}

	/**
	 * 
	 */	
	public com.mg.merp.mfreference.model.Crew getCauseCrew() {
		return this.CauseCrew;
	}

	public void setCauseCrew(com.mg.merp.mfreference.model.Crew MfCrew) {
		this.CauseCrew = MfCrew;
	}

	/**
	 * 
	 */	
	public com.mg.merp.manufacture.model.Job getCauseJob() {
		return this.CauseJob;
	}

	public void setCauseJob(com.mg.merp.manufacture.model.Job MfJob_1) {
		this.CauseJob = MfJob_1;
	}

	/**
	 * 
	 */	
	public com.mg.merp.mfreference.model.WorkCenter getDetectWC() {
		return this.DetectWC;
	}

	public void setDetectWC(
			com.mg.merp.mfreference.model.WorkCenter MfWorkCenter) {
		this.DetectWC = MfWorkCenter;
	}

	/**
	 * 
	 */	
	public com.mg.merp.mfreference.model.Crew getDetectCrew() {
		return this.DetectCrew;
	}

	public void setDetectCrew(com.mg.merp.mfreference.model.Crew MfCrew_1) {
		this.DetectCrew = MfCrew_1;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.ScrapDocHead.DetectOper")
	public com.mg.merp.manufacture.model.JobRoute getDetectOper() {
		return this.DetectOper;
	}

	public void setDetectOper(com.mg.merp.manufacture.model.JobRoute MfJobRoute) {
		this.DetectOper = MfJobRoute;
	}

	/**
	 * 
	 */	
	public com.mg.merp.reference.model.Contractor getDetectEmployee() {
		return this.DetectEmployee;
	}

	public void setDetectEmployee(
			com.mg.merp.reference.model.Contractor Contractor_2) {
		this.DetectEmployee = Contractor_2;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.ScrapDocHead.CauseOper")
	public com.mg.merp.manufacture.model.JobRoute getCauseOper() {
		return this.CauseOper;
	}

	public void setCauseOper(com.mg.merp.manufacture.model.JobRoute MfJobRoute_1) {
		this.CauseOper = MfJobRoute_1;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.ScrapDocHead.DetectContractor")
	public com.mg.merp.reference.model.Contractor getDetectContractor() {
		return this.DetectContractor;
	}

	public void setDetectContractor(
			com.mg.merp.reference.model.Contractor Contractor_3) {
		this.DetectContractor = Contractor_3;
	}

	/**
	 * 
	 */	
	public com.mg.merp.mfreference.model.WorkCenter getCauseWC() {
		return this.CauseWC;
	}

	public void setCauseWC(
			com.mg.merp.mfreference.model.WorkCenter MfWorkCenter_1) {
		this.CauseWC = MfWorkCenter_1;
	}

}