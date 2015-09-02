/*
 * VarianceDocumentModel.java
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
 * @version $Id: VarianceDocumentModel.java,v 1.3 2005/07/13 13:52:46 safonov
 *          Exp $
 */
public class VarianceDocumentModel extends
		com.mg.merp.document.model.DocHeadModel implements java.io.Serializable {

	// Fields

	private com.mg.merp.mfreference.model.WorkCenter workCenter;

	private com.mg.merp.manufacture.model.Job job;

	// Constructors

	/** default constructor */
	public VarianceDocumentModel() {
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("Manufacture.VarDocModel.WC")
	public com.mg.merp.mfreference.model.WorkCenter getWC() {
		return this.workCenter;
	}

	public void setWC(com.mg.merp.mfreference.model.WorkCenter MfWorkCenter) {
		this.workCenter = MfWorkCenter;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.VarDocModel.Job")
	public com.mg.merp.manufacture.model.Job getJob() {
		return this.job;
	}

	public void setJob(com.mg.merp.manufacture.model.Job MfJob) {
		this.job = MfJob;
	}

}