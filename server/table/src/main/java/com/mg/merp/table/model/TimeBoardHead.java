/*
 * TimeBoardHead.java
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
 * Модель бизнес-компонента "Заголовок табеля"
 * 
 * @author Artem V. Sharapov
 * @version $Id: TimeBoardHead.java,v 1.5 2008/08/12 14:11:44 sharapov Exp $
 */
public class TimeBoardHead extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.personnelref.model.CalcPeriod CalcPeriod;

	private java.lang.String BNumber;

	private java.lang.String Comments;

	private java.util.Set<TimeBoardPosition> timeBoardPositions;

	// Constructors

	/** default constructor */
	public TimeBoardHead() {
	}

	/** constructor with id */
	public TimeBoardHead(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("ID") //$NON-NLS-1$
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
	@DataItemName("Table.TBHead.CalcPeriod") //$NON-NLS-1$
	public com.mg.merp.personnelref.model.CalcPeriod getCalcPeriod() {
		return this.CalcPeriod;
	}

	public void setCalcPeriod(com.mg.merp.personnelref.model.CalcPeriod PrefCalcPeriod) {
		this.CalcPeriod = PrefCalcPeriod;
	}

	/**
	 * 
	 */
	@DataItemName("Table.TBHead.BNumber") //$NON-NLS-1$
	public java.lang.String getBNumber() {
		return this.BNumber;
	}

	public void setBNumber(java.lang.String Bnumber) {
		this.BNumber = Bnumber;
	}

	/**
	 * 
	 */
	@DataItemName("Table.TBHead.Comments") //$NON-NLS-1$
	public java.lang.String getComments() {
		return this.Comments;
	}

	public void setComments(java.lang.String Comments) {
		this.Comments = Comments;
	}

	/**
	 * 
	 */
	public java.util.Set<TimeBoardPosition> getTimeBoardPositions() {
		return this.timeBoardPositions;
	}

	public void setTimeBoardPositions(java.util.Set<TimeBoardPosition> timeBoardPositions) {
		this.timeBoardPositions = timeBoardPositions;
	}

}