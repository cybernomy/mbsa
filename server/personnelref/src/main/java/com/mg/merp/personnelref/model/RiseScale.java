/*
 * RiseScale.java
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
package com.mg.merp.personnelref.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: RiseScale.java,v 1.4 2006/09/04 13:01:16 leonova Exp $
 */
public class RiseScale extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.personnelref.model.Rise Rise;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.Integer ScaleNumber;

	private java.lang.String SName;

	private java.util.Date BeginDate;

	private java.util.Set SetOfPrefRisePercent;

	// Constructors

	/** default constructor */
	public RiseScale() {
	}

	/** constructor with id */
	public RiseScale(java.lang.Integer Id) {
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

	public com.mg.merp.personnelref.model.Rise getRise() {
		return this.Rise;
	}

	public void setRise(com.mg.merp.personnelref.model.Rise PrefRise) {
		this.Rise = PrefRise;
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
	@DataItemName("PersonnelRef.RiseScale.ScaleNumber")
	public java.lang.Integer getScaleNumber() {
		return this.ScaleNumber;
	}

	public void setScaleNumber(java.lang.Integer ScaleNumber) {
		this.ScaleNumber = ScaleNumber;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.RiseScale.Name")
	public java.lang.String getSName() {
		return this.SName;
	}

	public void setSName(java.lang.String Sname) {
		this.SName = Sname;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.RiseScale.BeginDate")
	public java.util.Date getBeginDate() {
		return this.BeginDate;
	}

	public void setBeginDate(java.util.Date Begindate) {
		this.BeginDate = Begindate;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfPrefRisePercent() {
		return this.SetOfPrefRisePercent;
	}

	public void setSetOfPrefRisePercent(java.util.Set SetOfPrefRisePercent) {
		this.SetOfPrefRisePercent = SetOfPrefRisePercent;
	}

}