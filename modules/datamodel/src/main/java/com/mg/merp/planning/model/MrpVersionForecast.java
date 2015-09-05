/*
 * MrpVersionForecast.java
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
package com.mg.merp.planning.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: MrpVersionForecast.java,v 1.5 2007/07/30 10:37:30 safonov Exp $
 */
public class MrpVersionForecast extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.planning.model.ForecastVersion ForecastVersion;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.planning.model.MrpVersionControl MrpVersionControl;

	private ForecastType ForecastType;

	// Constructors

	/** default constructor */
	public MrpVersionForecast() {
	}

	/** constructor with id */
	public MrpVersionForecast(java.lang.Integer Id) {
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
	public com.mg.merp.planning.model.ForecastVersion getForecastVersion() {
		return this.ForecastVersion;
	}

	public void setForecastVersion(
			com.mg.merp.planning.model.ForecastVersion ForecastVersion) {
		this.ForecastVersion = ForecastVersion;
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

	public com.mg.merp.planning.model.MrpVersionControl getMrpVersionControl() {
		return this.MrpVersionControl;
	}

	public void setMrpVersionControl(
			com.mg.merp.planning.model.MrpVersionControl MrpVersionControl) {
		this.MrpVersionControl = MrpVersionControl;
	}

	/**
	 * 
	 */

	public ForecastType getForecastType() {
		return this.ForecastType;
	}

	public void setForecastType(ForecastType ForecastType) {
		this.ForecastType = ForecastType;
	}

}