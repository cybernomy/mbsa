/*
 * MPSRest.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.planning.support.ui;

import java.util.Date;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultRestrictionForm;
import com.mg.merp.mfreference.model.PlanningLevel;
import com.mg.merp.mfreference.model.WeekCalendar;
import com.mg.merp.planning.model.ForecastVersion;
import com.mg.merp.planning.model.InventoryForecast;

/**
 * Контроллер формы условий отбора ОПП
 * 
 * @author leonova
 * @version $Id: MPSRest.java,v 1.2 2006/08/25 10:17:24 leonova Exp $
 */
public class MPSRest extends DefaultRestrictionForm {

	private PlanningLevel planningLevel = null;
	private InventoryForecast inventoryForecastCode = null;
	private WeekCalendar weekCalCode = null;
	private ForecastVersion forecastVersionCode = null;
	@DataItemName("Planning.Code")
	private String code = "";
	@DataItemName("Planning.Description")	
	private String description = "";
	@DataItemName("Planning.Cond.MPS.DateFrom")
	private Date planningDateFrom = null;
	@DataItemName("Planning.Cond.MPS.DateTill")
	private Date planningDateTill = null;
	
	@Override
	protected void doClearRestrictionItem() {
		this.planningLevel = null;
		this.inventoryForecastCode = null;
		this.weekCalCode = null;
		this.forecastVersionCode = null;
		this.code = "";
		this.description = "";
		this.planningDateFrom = null;
		this.planningDateTill = null;		
	}

	/**
	 * @return Returns the code.
	 */
	protected String getCode() {
		return code;
	}

	/**
	 * @return Returns the description.
	 */
	protected String getDescription() {
		return description;
	}

	/**
	 * @return Returns the forecastVersionCode.
	 */
	protected ForecastVersion getForecastVersionCode() {
		return forecastVersionCode;
	}

	/**
	 * @return Returns the inventoryForecastCode.
	 */
	protected InventoryForecast getInventoryForecastCode() {
		return inventoryForecastCode;
	}

	/**
	 * @return Returns the planningDateFrom.
	 */
	protected Date getPlanningDateFrom() {
		return planningDateFrom;
	}

	/**
	 * @return Returns the planningDateTill.
	 */
	protected Date getPlanningDateTill() {
		return planningDateTill;
	}

	/**
	 * @return Returns the planningLevel.
	 */
	protected PlanningLevel getPlanningLevel() {
		return planningLevel;
	}

	/**
	 * @return Returns the weekCalCode.
	 */
	protected WeekCalendar getWeekCalCode() {
		return weekCalCode;
	}


}
