/*
 * TableConfig.java
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
package com.mg.merp.table.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Конфигурация модуля <Табельный учет>"
 * 
 * @author hbm2java
 * @author Artem V. Sharapov
 * @version $Id: TableConfig.java,v 1.3 2007/01/13 13:33:04 sharapov Exp $
 */
public class TableConfig extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields    

	private int sysClientId;
	private com.mg.merp.table.model.TimeKind HolidayTimeKind;
	private com.mg.merp.table.model.TimeKind WorkTimeKind;

	// Constructors

	/** default constructor */
	public TableConfig() {
	}

	/** constructor with id */
	public TableConfig(int sysClientId) {
		this.sysClientId = sysClientId;
	}

	// Property accessors

	/**

	 */
	@DataItemName("TableConfig.TimeKind.HolidayTime")
	public com.mg.merp.table.model.TimeKind getHolidayTimeKind () {
		return this.HolidayTimeKind;
	}

	public void setHolidayTimeKind (com.mg.merp.table.model.TimeKind TabTimeKind) {
		this.HolidayTimeKind = TabTimeKind;
	}

	/**

	 */
	@DataItemName("TableConfig.TimeKind.WorkTime")
	public com.mg.merp.table.model.TimeKind getWorkTimeKind () {
		return this.WorkTimeKind;
	}

	public void setWorkTimeKind (com.mg.merp.table.model.TimeKind TabTimeKind_1) {
		this.WorkTimeKind = TabTimeKind_1;
	}

	/**
	 * @return the sysClientId
	 */
	public int getSysClientId() {
		return sysClientId;
	}

	/**
	 * @param sysClientId the sysClientId to set
	 */
	public void setSysClientId(int sysClientId) {
		this.sysClientId = sysClientId;
	}

}