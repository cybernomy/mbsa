/*
 * PmcPeriod.java
 * 
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.paymentcontrol.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Периоды планирования"
 * 
 * @author Artem V. Sharapov
 * @version $Id: PmcPeriod.java,v 1.5 2007/05/14 05:12:10 sharapov Exp $
 */
public class PmcPeriod extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.paymentcontrol.model.PmcPeriod Parent;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String Name;

	private java.util.Date DateFrom;

	private java.util.Date DateTill;

	private java.lang.Short Kind;

	private java.util.Set<PmcPeriod> PmcPeriods;

	// Constructors

	/** default constructor */
	public PmcPeriod() {
	}

	/** constructor with id */
	public PmcPeriod(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */
	public java.lang.Integer getId() {
		return this.Id;
	}

	public void setId(java.lang.Integer Id) {
		this.Id = Id;
	}

	/**
	 * 
	 */
	public com.mg.merp.paymentcontrol.model.PmcPeriod getParent() {
		return this.Parent;
	}

	public void setParent(com.mg.merp.paymentcontrol.model.PmcPeriod PmcPeriod) {
		this.Parent = PmcPeriod;
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
	@DataItemName("PaymentControl.Payment.Name") //$NON-NLS-1$
	public java.lang.String getName() {
		return this.Name;
	}

	public void setName(java.lang.String Name) {
		this.Name = Name;
	}

	/**
	 * 
	 */
	@DataItemName("PaymentControl.Payment.ActDateFrom") //$NON-NLS-1$
	public java.util.Date getDateFrom() {
		return this.DateFrom;
	}

	public void setDateFrom(java.util.Date Datefrom) {
		this.DateFrom = Datefrom;
	}

	/**
	 * 
	 */
	@DataItemName("PaymentControl.Payment.ActDateTill") //$NON-NLS-1$
	public java.util.Date getDateTill() {
		return this.DateTill;
	}

	public void setDateTill(java.util.Date Datetill) {
		this.DateTill = Datetill;
	}

	/**
	 * 
	 */
	public java.lang.Short getKind() {
		return this.Kind;
	}

	public void setKind(java.lang.Short Kind) {
		this.Kind = Kind;
	}

	public java.util.Set<PmcPeriod> getPmcPeriods() {
		return PmcPeriods;
	}

	public void setPmcPeriods(java.util.Set<PmcPeriod> pmcPeriods) {
		PmcPeriods = pmcPeriods;
	}

}