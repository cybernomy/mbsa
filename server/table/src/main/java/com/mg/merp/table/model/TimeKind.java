/*
 * TimeKind.java
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
package com.mg.merp.table.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: TimeKind.java,v 1.5 2006/08/29 12:50:20 leonova Exp $
 */
public class TimeKind extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String Code;

	private java.lang.String Name;

	private java.lang.Integer Priority;

	private boolean IsWholeDay;

	private java.lang.String MnemoCode;

	private java.lang.Integer FontColor;

	private java.lang.Integer BackGroundColor;

	private java.util.Set SetOfTabScheduleHead;

	private java.util.Set SetOfTabTimeBoardSpec;

	private java.util.Set SetOfTabConfig;

	private java.util.Set SetOfTabConfig_1;

	private java.util.Set SetOfTabPatternSpec;

	private java.util.Set SetOfTabScheduleSpec;

	// Constructors

	/** default constructor */
	public TimeKind() {
	}

	/** constructor with id */
	public TimeKind(java.lang.Integer Id) {
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
	@DataItemName("Table.BigCode")
	public java.lang.String getCode() {
		return this.Code;
	}

	public void setCode(java.lang.String Kcode) {
		this.Code = Kcode;
	}

	/**
	 * 
	 */
	@DataItemName("Table.NameN")
	public java.lang.String getName() {
		return this.Name;
	}

	public void setName(java.lang.String Kname) {
		this.Name = Kname;
	}

	/**
	 * 
	 */
	@DataItemName("Table.TimeKind.Priority")
	public java.lang.Integer getPriority() {
		return this.Priority;
	}

	public void setPriority(java.lang.Integer Priority) {
		this.Priority = Priority;
	}

	/**
	 * 
	 */
	@DataItemName("Table.TimeKind.IsWholeDay")
	public boolean getIsWholeDay() {
		return this.IsWholeDay;
	}

	public void setIsWholeDay(boolean IsWholeDay) {
		this.IsWholeDay = IsWholeDay;
	}

	/**
	 * 
	 */
	@DataItemName("Table.TimeKind.MnemoCode")
	public java.lang.String getMnemoCode() {
		return this.MnemoCode;
	}

	public void setMnemoCode(java.lang.String Mnemocode) {
		this.MnemoCode = Mnemocode;
	}

	/**
	 * 
	 */

	public java.lang.Integer getFontColor() {
		return this.FontColor;
	}

	public void setFontColor(java.lang.Integer FontColor) {
		this.FontColor = FontColor;
	}

	/**
	 * 
	 */

	public java.lang.Integer getBackGroundColor() {
		return this.BackGroundColor;
	}

	public void setBackGroundColor(java.lang.Integer BackgroundColor) {
		this.BackGroundColor = BackgroundColor;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfTabScheduleHead() {
		return this.SetOfTabScheduleHead;
	}

	public void setSetOfTabScheduleHead(java.util.Set SetOfTabScheduleHead) {
		this.SetOfTabScheduleHead = SetOfTabScheduleHead;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfTabTimeBoardSpec() {
		return this.SetOfTabTimeBoardSpec;
	}

	public void setSetOfTabTimeBoardSpec(java.util.Set SetOfTabTimeBoardSpec) {
		this.SetOfTabTimeBoardSpec = SetOfTabTimeBoardSpec;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfTabConfig() {
		return this.SetOfTabConfig;
	}

	public void setSetOfTabConfig(java.util.Set SetOfTabConfig) {
		this.SetOfTabConfig = SetOfTabConfig;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfTabConfig_1() {
		return this.SetOfTabConfig_1;
	}

	public void setSetOfTabConfig_1(java.util.Set SetOfTabConfig_1) {
		this.SetOfTabConfig_1 = SetOfTabConfig_1;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfTabPatternSpec() {
		return this.SetOfTabPatternSpec;
	}

	public void setSetOfTabPatternSpec(java.util.Set SetOfTabPatternSpec) {
		this.SetOfTabPatternSpec = SetOfTabPatternSpec;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfTabScheduleSpec() {
		return this.SetOfTabScheduleSpec;
	}

	public void setSetOfTabScheduleSpec(java.util.Set SetOfTabScheduleSpec) {
		this.SetOfTabScheduleSpec = SetOfTabScheduleSpec;
	}

}