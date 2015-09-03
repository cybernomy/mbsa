/*
 * PatternHead.java
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
 * Модель бизнес-компонента "Шаблон графика"
 * 
 * @author Artem V. Sharapov
 * @version $Id: PatternHead.java,v 1.7 2008/08/12 14:11:44 sharapov Exp $
 */
public class PatternHead extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private java.lang.String Code;

	private java.lang.String Name;

	private java.lang.Integer Duration;

	private PatternKind PatternKind;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.util.Set<ScheduleHead> ScheduleHeads;

	private java.util.Set<PatternSpec> PatternSpecs;

	// Constructors

	/** default constructor */
	public PatternHead() {
	}

	/** constructor with id */
	public PatternHead(java.lang.Integer Id) {
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
	@DataItemName("Table.BigCode") //$NON-NLS-1$
	public java.lang.String getCode() {
		return this.Code;
	}

	public void setCode(java.lang.String Pcode) {
		this.Code = Pcode;
	}

	/**
	 * 
	 */
	@DataItemName("Table.NameN") //$NON-NLS-1$
	public java.lang.String getName() {
		return this.Name;
	}

	public void setName(java.lang.String Pname) {
		this.Name = Pname;
	}

	/**
	 * 
	 */
	@DataItemName("Table.PatternHead.Duration") //$NON-NLS-1$
	public java.lang.Integer getDuration() {
		return this.Duration;
	}

	public void setDuration(java.lang.Integer Duration) {
		this.Duration = Duration;
		if (PatternKind != null && PatternKind.equals(com.mg.merp.table.model.PatternKind.WEEKLY)) {
			this.Duration = 7;
		}
	}

	/**
	 * 
	 */
	public PatternKind getPatternKind() {
		return this.PatternKind;
	}

	public void setPatternKind(PatternKind PatternKind) {
		this.PatternKind = PatternKind;	
		if (PatternKind != null && PatternKind.equals(com.mg.merp.table.model.PatternKind.WEEKLY)) {
			this.Duration = 7;
		}
	}

	/**
	 * @return the patternSpecs
	 */
	public java.util.Set<PatternSpec> getPatternSpecs() {
		return this.PatternSpecs;
	}

	/**
	 * @param patternSpecs the patternSpecs to set
	 */
	public void setPatternSpecs(java.util.Set<PatternSpec> patternSpecs) {
		this.PatternSpecs = patternSpecs;
	}

	/**
	 * @return the scheduleHeads
	 */
	public java.util.Set<ScheduleHead> getScheduleHeads() {
		return this.ScheduleHeads;
	}

	/**
	 * @param scheduleHeads the scheduleHeads to set
	 */
	public void setScheduleHeads(java.util.Set<ScheduleHead> scheduleHeads) {
		this.ScheduleHeads = scheduleHeads;
	}

}