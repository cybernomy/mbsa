/*
 * DayTime.java
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
package com.mg.merp.mfreference.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: DayTime.java,v 1.4 2006/09/04 05:54:46 leonova Exp $
 */
public class DayTime extends com.mg.framework.service.PersistentObjectHibernate
		implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.mfreference.model.DayCalendar DayCal;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.Long startTick;

	private java.lang.Long ticks;

	// Constructors

	/** default constructor */
	public DayTime() {
	}

	/** constructor with id */
	public DayTime(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("ID")
	public java.lang.Integer getId() {
		return this.Id;
	}

	public void setId(java.lang.Integer Id) {
		this.Id = Id;
	}

	/**
	 * 
	 */

	public com.mg.merp.mfreference.model.DayCalendar getDayCal() {
		return this.DayCal;
	}

	public void setDayCal(com.mg.merp.mfreference.model.DayCalendar DayCal) {
		this.DayCal = DayCal;
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
	@DataItemName("MfReference.DayTime.StartTick")
	public java.lang.Long getStartTick() {
		return this.startTick;
	}

	public void setStartTick(java.lang.Long StartTick) {
		this.startTick = StartTick;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.DayTime.Ticks")
	public java.lang.Long getTicks() {
		return this.ticks;
	}

	public void setTicks(java.lang.Long Ticks) {
		this.ticks = Ticks;
	}

}