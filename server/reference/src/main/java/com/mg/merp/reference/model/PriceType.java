/*
 * PriceType.java
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
 * @version $Id: PriceType.java,v 1.4 2006/05/29 12:47:56 leonova Exp $
 */
@DataItemName("Reference.PriceType")
public class PriceType extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String Code;

	private java.lang.String PName;

	private boolean UseRelativePercent;

	private java.math.BigDecimal RelativePercent;

	private boolean RoundInGreater;

	private java.lang.String Formula;

	// Constructors

	/** default constructor */
	public PriceType() {
	}

	/** constructor with id */
	public PriceType(java.lang.Integer Id) {
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

	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.SysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.PriceType.Code")
	public java.lang.String getCode() {
		return this.Code;
	}

	public void setCode(java.lang.String Code) {
		this.Code = Code;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.PriceType.Name")
	public java.lang.String getPName() {
		return this.PName;
	}

	public void setPName(java.lang.String PName) {
		this.PName = PName;
	}

	/**
	 * 
	 */

	public boolean getUseRelativePercent() {
		return this.UseRelativePercent;
	}

	public void setUseRelativePercent(boolean UseRelativePercent) {
		this.UseRelativePercent = UseRelativePercent;
	}

	/**
	 * 
	 */

	public java.math.BigDecimal getRelativePercent() {
		return this.RelativePercent;
	}

	public void setRelativePercent(java.math.BigDecimal RelativePercent) {
		this.RelativePercent = RelativePercent;
	}

	/**
	 * 
	 */

	public boolean getRoundInGreater() {
		return this.RoundInGreater;
	}

	public void setRoundInGreater(boolean RoundInGreater) {
		this.RoundInGreater = RoundInGreater;
	}

	/**
	 * 
	 */

	public java.lang.String getFormula() {
		return this.Formula;
	}

	public void setFormula(java.lang.String Formula) {
		this.Formula = Formula;
	}

}