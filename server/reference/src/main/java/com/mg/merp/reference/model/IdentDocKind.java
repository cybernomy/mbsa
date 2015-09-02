/*
 * IdentDocKind.java
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
 * @version $Id: IdentDocKind.java,v 1.5 2006/05/31 06:05:57 leonova Exp $
 */
@DataItemName("Reference.IdentDocKind")
public class IdentDocKind extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private int Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String KCode;

	private java.lang.String KName;

	private java.lang.Integer LSeriesLength;

	private java.lang.Integer RSeriesLength;

	private java.lang.String SeriesDivider;

	private java.lang.Integer NumberLength;

	// Constructors

	/** default constructor */
	public IdentDocKind() {
	}

	/** constructor with id */
	public IdentDocKind(int Id) {
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

	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.SysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.BigCode")
	public java.lang.String getKCode() {
		return this.KCode;
	}

	public void setKCode(java.lang.String KCode) {
		this.KCode = KCode;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Name")
	public java.lang.String getKName() {
		return this.KName;
	}

	public void setKName(java.lang.String KName) {
		this.KName = KName;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.IdentKind.LSeriesLength")
	public java.lang.Integer getLSeriesLength() {
		return this.LSeriesLength;
	}

	public void setLSeriesLength(java.lang.Integer LSeriesLength) {
		this.LSeriesLength = LSeriesLength;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.IdentKind.RSeriesLength")
	public java.lang.Integer getRSeriesLength() {
		return this.RSeriesLength;
	}

	public void setRSeriesLength(java.lang.Integer RSeriesLength) {
		this.RSeriesLength = RSeriesLength;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.IdentKind.SeriesDivider")
	public java.lang.String getSeriesDivider() {
		return this.SeriesDivider;
	}

	public void setSeriesDivider(java.lang.String SeriesDivider) {
		this.SeriesDivider = SeriesDivider;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.IdentKind.NumberLength")
	public java.lang.Integer getNumberLength() {
		return this.NumberLength;
	}

	public void setNumberLength(java.lang.Integer NumberLength) {
		this.NumberLength = NumberLength;
	}

}