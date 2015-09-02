/*
 * PatternSpec.java
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

package com.mg.merp.table.model;

/**
 * Модель бизнес-компонента "Спецификация шаблона графика"
 * 
 * @author Artem V. Sharapov
 * @version $Id: PatternSpec.java,v 1.3 2008/08/12 14:11:44 sharapov Exp $
 */
public class PatternSpec extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields    

	private java.lang.Integer Id;
	private com.mg.merp.table.model.TimeKind TimeKind;
	private com.mg.merp.core.model.SysClient SysClient;
	private com.mg.merp.table.model.PatternHead PatternHead;
	private java.lang.Integer DayNumber;
	private java.math.BigDecimal HoursQuantity;


	// Constructors

	/** default constructor */
	public PatternSpec() {
	}

	/** constructor with id */
	public PatternSpec(java.lang.Integer Id) {
		this.Id = Id;
	}


	// Property accessors
	/**

	 */
	public java.lang.Integer getId () {
		return this.Id;
	}

	public void setId (java.lang.Integer Id) {
		this.Id = Id;
	}

	/**

	 */
	public com.mg.merp.table.model.TimeKind getTimeKind () {
		return this.TimeKind;
	}

	public void setTimeKind (com.mg.merp.table.model.TimeKind TabTimeKind) {
		this.TimeKind = TabTimeKind;
	}

	/**

	 */
	public com.mg.merp.core.model.SysClient getSysClient () {
		return this.SysClient;
	}

	public void setSysClient (com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	/**

	 */
	public com.mg.merp.table.model.PatternHead getPatternHead () {
		return this.PatternHead;
	}

	public void setPatternHead (com.mg.merp.table.model.PatternHead TabPatternHead) {
		this.PatternHead = TabPatternHead;
	}

	/**

	 */
	public java.lang.Integer getDayNumber () {
		return this.DayNumber;
	}

	public void setDayNumber (java.lang.Integer DayNumber) {
		this.DayNumber = DayNumber;
	}

	/**

	 */
	public java.math.BigDecimal getHoursQuantity () {
		return this.HoursQuantity;
	}

	public void setHoursQuantity (java.math.BigDecimal HoursQuantity) {
		this.HoursQuantity = HoursQuantity;
	}

}