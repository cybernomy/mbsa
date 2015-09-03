/*
 * ScrapHeadRest.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.manufacture.support.ui;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.merp.manufacture.model.Job;
import com.mg.merp.manufacture.model.JobRoute;
import com.mg.merp.mfreference.model.Crew;
import com.mg.merp.mfreference.model.WorkCenter;
import com.mg.merp.qualitycontrol.model.ScrapReason;
import com.mg.merp.qualitycontrol.model.ScrapType;
import com.mg.merp.reference.model.Contractor;

/**
 * Контроллер формы условий отбора актов на списание потерь 
 * 
 * @author leonova
 * @version $Id: ScrapHeadRest.java,v 1.3 2007/07/31 06:31:06 safonov Exp $ 
 */
public class ScrapHeadRest extends ManufactureDocumentRest {
	private Crew causeCrew = null;
	@DataItemName("Manufacture.ScrapDocHead.CauseOper")
	private JobRoute causeOper = null;
	private WorkCenter causeWorkCenter = null;
	private Job CauseJob = null; //названо по JavaBean NC, чтобы работал SearchHelp
	@DataItemName("Manufacture.InputDocHead.Contractor")
	private Contractor causeContractor = null;
	@DataItemName("Manufacture.InputDocHead.Employee")
	private Contractor causeEmployee = null;	
	private Crew detectCrew = null;
	@DataItemName("Manufacture.ScrapDocHead.DetectOper")
	private JobRoute detectOper = null;
	private WorkCenter detectWorkCenter = null;
	private Job DetectJob = null; //названо по JavaBean NC, чтобы работал SearchHelp
	@DataItemName("Manufacture.InputDocHead.Contractor")
	private Contractor detectContractor = null;
	@DataItemName("Manufacture.InputDocHead.Employee")
	private Contractor detectEmployee = null;
	private ScrapReason scrapReason = null;
	private ScrapType scrapType = null;	
	

	@Override
	protected void doClearRestrictionItem() {
		super.doClearRestrictionItem();
		this.causeCrew = null;
		this.causeOper = null;
		this.causeWorkCenter = null;
		this.CauseJob = null;
		this.causeContractor = null;
		this.causeEmployee = null;
		this.detectCrew = null;
		this.detectOper = null;
		this.detectWorkCenter = null;
		this.DetectJob = null;
		this.detectContractor = null;
		this.detectEmployee = null;	
		this.scrapReason = null;
		this.scrapType = null;
	}


	/**
	 * @return Returns the causeContractor.
	 */
	protected Contractor getCauseContractor() {
		return causeContractor;
	}


	/**
	 * @return Returns the causeCrew.
	 */
	protected Crew getCauseCrew() {
		return causeCrew;
	}


	/**
	 * @return Returns the causeEmployee.
	 */
	protected Contractor getCauseEmployee() {
		return causeEmployee;
	}


	/**
	 * @return Returns the causeJob.
	 */
	protected Job getCauseJob() {
		return CauseJob;
	}


	/**
	 * @return Returns the causeOper.
	 */
	protected JobRoute getCauseOper() {
		return causeOper;
	}


	/**
	 * @return Returns the causeWorkCenter.
	 */
	protected WorkCenter getCauseWorkCenter() {
		return causeWorkCenter;
	}


	/**
	 * @return Returns the detectContractor.
	 */
	protected Contractor getDetectContractor() {
		return detectContractor;
	}


	/**
	 * @return Returns the detectCrew.
	 */
	protected Crew getDetectCrew() {
		return detectCrew;
	}


	/**
	 * @return Returns the detectEmployee.
	 */
	protected Contractor getDetectEmployee() {
		return detectEmployee;
	}


	/**
	 * @return Returns the detectJob.
	 */
	protected Job getDetectJob() {
		return DetectJob;
	}


	/**
	 * @return Returns the detectOper.
	 */
	protected JobRoute getDetectOper() {
		return detectOper;
	}


	/**
	 * @return Returns the detectWorkCenter.
	 */
	protected WorkCenter getDetectWorkCenter() {
		return detectWorkCenter;
	}


	/**
	 * @return Returns the scrapReason.
	 */
	protected ScrapReason getScrapReason() {
		return scrapReason;
	}


	/**
	 * @return Returns the scrapType.
	 */
	protected ScrapType getScrapType() {
		return scrapType;
	}


}
