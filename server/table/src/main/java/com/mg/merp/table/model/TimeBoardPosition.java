/*
 * TimeBoardPosition.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
 * Модель бизнес-компонента "Список сотрудников в табеле"
 * 
 * @author Artem V. Sharapov
 * @version $Id: TimeBoardPosition.java,v 1.5 2008/08/12 14:11:44 sharapov Exp $
 */
public class TimeBoardPosition extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields    

	private java.lang.Integer Id;
	private com.mg.merp.personnelref.model.StaffListUnit StaffListUnit;
	private com.mg.merp.table.model.TimeBoardHead TimeBoardHead;
	private com.mg.merp.personnelref.model.PositionFill PositionFill;
	private com.mg.merp.core.model.SysClient SysClient;
	private java.util.Set<TimeBoardSpec> TimeBoardSpecs;


	// Constructors

	/** default constructor */
	public TimeBoardPosition() {
	}

	/** constructor with id */
	public TimeBoardPosition(java.lang.Integer Id) {
		this.Id = Id;
	}


	// Property accessors
	/**

	 */
	@DataItemName("ID") //$NON-NLS-1$
	public java.lang.Integer getId() {
		return this.Id;
	}

	public void setId(java.lang.Integer Id) {
		this.Id = Id;
	}

	/**

	 */
	public com.mg.merp.personnelref.model.StaffListUnit getStaffListUnit() {
		return this.StaffListUnit;
	}

	public void setStaffListUnit(com.mg.merp.personnelref.model.StaffListUnit PrefStaffListUnit) {
		this.StaffListUnit = PrefStaffListUnit;
	}

	/**

	 */
	public com.mg.merp.table.model.TimeBoardHead getTimeBoardHead() {
		return this.TimeBoardHead;
	}

	public void setTimeBoardHead(com.mg.merp.table.model.TimeBoardHead TabTimeBoardHead) {
		this.TimeBoardHead = TabTimeBoardHead;
	}

	/**

	 */
	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.SysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	/**

	 */
	@DataItemName("Table.TimeBoardPosision.PositionFill") //$NON-NLS-1$
	public com.mg.merp.personnelref.model.PositionFill getPositionFill() {
		return this.PositionFill;
	}

	public void setPositionFill(com.mg.merp.personnelref.model.PositionFill SalPositionFill) {
		this.PositionFill = SalPositionFill;
	}

	/**
	 * @return the timeBoardSpecs
	 */
	public java.util.Set<TimeBoardSpec> getTimeBoardSpecs() {
		return this.TimeBoardSpecs;
	}

	/**
	 * @param timeBoardSpecs the timeBoardSpecs to set
	 */
	public void setTimeBoardSpecs(java.util.Set<TimeBoardSpec> timeBoardSpecs) {
		this.TimeBoardSpecs = timeBoardSpecs;
	}

}