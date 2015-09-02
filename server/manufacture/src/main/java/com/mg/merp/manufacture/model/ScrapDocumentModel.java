/*
 * ScrapDocumentModel.java
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
 * @version $Id: ScrapDocumentModel.java,v 1.4 2005/08/17 11:11:38 pashistova
 *          Exp $
 */
public class ScrapDocumentModel extends com.mg.merp.document.model.DocHeadModel
		implements java.io.Serializable {

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
	public ScrapDocumentModel() {
	}

	// Property accessors
	/**
	 * 
	 */

	@DataItemName("Manufacture.ScrapDocumentModel.DetectJob")
	public com.mg.merp.manufacture.model.Job getDetectJob() {
		return this.DetectJob;
	}

	public void setDetectJob(com.mg.merp.manufacture.model.Job MfJob) {
		this.DetectJob = MfJob;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.ScrapDocumentModel.CauseContractor")
	public com.mg.merp.reference.model.Contractor getCauseContractor() {
		return this.CauseContractor;
	}

	public void setCauseContractor(
			com.mg.merp.reference.model.Contractor Contractor) {
		this.CauseContractor = Contractor;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.ScrapDocumentModel.ScrapType")
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
	@DataItemName("Manufacture.ScrapDocumentModel.ScrapReason")
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
	@DataItemName("Manufacture.ScrapDocumentModel.CauseEmployee")
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
	@DataItemName("Manufacture.ScrapDocumentModel.CauseCrew")
	public com.mg.merp.mfreference.model.Crew getCauseCrew() {
		return this.CauseCrew;
	}

	public void setCauseCrew(com.mg.merp.mfreference.model.Crew MfCrew) {
		this.CauseCrew = MfCrew;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.ScrapDocumentModel.CauseJob")
	public com.mg.merp.manufacture.model.Job getCauseJob() {
		return this.CauseJob;
	}

	public void setCauseJob(com.mg.merp.manufacture.model.Job MfJob_1) {
		this.CauseJob = MfJob_1;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.ScrapDocumentModel.DetectWC")
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
	@DataItemName("Manufacture.ScrapDocumentModel.DetectCrew")
	public com.mg.merp.mfreference.model.Crew getDetectCrew() {
		return this.DetectCrew;
	}

	public void setDetectCrew(com.mg.merp.mfreference.model.Crew MfCrew_1) {
		this.DetectCrew = MfCrew_1;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.ScrapDocumentModel.DetectOper")
	public com.mg.merp.manufacture.model.JobRoute getDetectOper() {
		return this.DetectOper;
	}

	public void setDetectOper(com.mg.merp.manufacture.model.JobRoute MfJobRoute) {
		this.DetectOper = MfJobRoute;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.ScrapDocumentModel.DetectEmployee")
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
	@DataItemName("Manufacture.ScrapDocumentModel.CauseOper")
	public com.mg.merp.manufacture.model.JobRoute getCauseOper() {
		return this.CauseOper;
	}

	public void setCauseOper(com.mg.merp.manufacture.model.JobRoute MfJobRoute_1) {
		this.CauseOper = MfJobRoute_1;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.ScrapDocumentModel.DetectContractor")
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
	@DataItemName("Manufacture.ScrapDocumentModel.CauseWC")
	public com.mg.merp.mfreference.model.WorkCenter getCauseWC() {
		return this.CauseWC;
	}

	public void setCauseWC(
			com.mg.merp.mfreference.model.WorkCenter MfWorkCenter_1) {
		this.CauseWC = MfWorkCenter_1;
	}

}