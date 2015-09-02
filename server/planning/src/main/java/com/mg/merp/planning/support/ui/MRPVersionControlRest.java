/*
 * MRPVersionControlRest.java
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
package com.mg.merp.planning.support.ui;

import java.util.Date;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultRestrictionForm;
import com.mg.merp.planning.model.ForecastVersion;
import com.mg.merp.planning.model.Mps;

/**
 * Контроллер формы условий отбора
 * 
 * @author leonova
 * @version $Id: MRPVersionControlRest.java,v 1.2 2006/08/25 10:16:52 leonova Exp $ 
 */
public class MRPVersionControlRest extends DefaultRestrictionForm {

	private Mps mpsCode = null;	
	private ForecastVersion forecastVersionCode = null;
	@DataItemName("Planning.Cond.Mrp.StartDateFrom")
	private Date startDateFrom = null;
	@DataItemName("Planning.Cond.Mrp.StartDateTill")
	private Date startDateTill = null;
	@DataItemName("Planning.Cond.Mrp.EndDateFrom")
	private Date endDateFrom = null;
	@DataItemName("Planning.Cond.Mrp.EndDateTill")
	private Date endDateTill = null;
	
	@Override
	protected void doClearRestrictionItem() {
		this.mpsCode = null;
		this.forecastVersionCode = null;
		this.startDateFrom = null;
		this.startDateTill = null;
		this.endDateFrom = null;
		this.endDateTill = null;
	}

	/**
	 * @return Returns the endDateFrom.
	 */
	protected Date getEndDateFrom() {
		return endDateFrom;
	}

	/**
	 * @return Returns the endDateTill.
	 */
	protected Date getEndDateTill() {
		return endDateTill;
	}

	/**
	 * @return Returns the forecastVersionCode.
	 */
	protected ForecastVersion getForecastVersionCode() {
		return forecastVersionCode;
	}

	/**
	 * @return Returns the mpsCode.
	 */
	protected Mps getMpsCode() {
		return mpsCode;
	}

	/**
	 * @return Returns the startDateFrom.
	 */
	protected Date getStartDateFrom() {
		return startDateFrom;
	}

	/**
	 * @return Returns the startDateTill.
	 */
	protected Date getStartDateTill() {
		return startDateTill;
	}



}
