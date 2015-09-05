/*
 * MeasureConversion.java
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
package com.mg.merp.reference.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: MeasureConversion.java,v 1.4 2006/10/12 11:52:55 safonov Exp $
 */
@DataItemName("Reference.MeasureConversion")
public class MeasureConversion extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private int Id;

	private com.mg.merp.reference.model.Catalog Catalog;

	private com.mg.merp.reference.model.Measure MeasureFrom;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.reference.model.Measure MeasureTo;

	private com.mg.merp.baiengine.model.Repository AlgRepository;

	// Constructors

	/** default constructor */
	public MeasureConversion() {
	}

	/** constructor with id */
	public MeasureConversion(int Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("ID")
	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	/**
	 * 
	 */
	public com.mg.merp.reference.model.Catalog getCatalog() {
		return this.Catalog;
	}

	public void setCatalog(com.mg.merp.reference.model.Catalog Catalog) {
		this.Catalog = Catalog;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.MConvert.MeasureFrom")
	public com.mg.merp.reference.model.Measure getMeasureFrom() {
		return this.MeasureFrom;
	}

	public void setMeasureFrom(com.mg.merp.reference.model.Measure MeasureFrom) {
		this.MeasureFrom = MeasureFrom;
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
	@DataItemName("Reference.MConvert.MeasureTo")
	public com.mg.merp.reference.model.Measure getMeasureTo() {
		return this.MeasureTo;
	}

	public void setMeasureTo(com.mg.merp.reference.model.Measure MeasureTo) {
		this.MeasureTo = MeasureTo;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.MConvert.AlgRepository")
	public com.mg.merp.baiengine.model.Repository getAlgRepository() {
		return this.AlgRepository;
	}

	public void setAlgRepository(
			com.mg.merp.baiengine.model.Repository AlgRepository) {
		this.AlgRepository = AlgRepository;
	}

}