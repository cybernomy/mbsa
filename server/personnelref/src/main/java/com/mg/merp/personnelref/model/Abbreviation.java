/*
 * Abbreviation.java
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
 * @version $Id: Abbreviation.java,v 1.4 2006/09/04 13:01:16 leonova Exp $
 */
public class Abbreviation extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private int Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.Integer ALevel;

	private java.lang.String ScName;

	private java.lang.String SocrName;

	private java.lang.String KodTSt;

	// Constructors

	/** default constructor */
	public Abbreviation() {
	}

	/** constructor with id */
	public Abbreviation(int Id) {
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

	@DataItemName("PersonnelRef.Abbreviation.ALevel")
	public java.lang.Integer getALevel() {
		return this.ALevel;
	}

	public void setALevel(java.lang.Integer Alevel) {
		this.ALevel = Alevel;
	}

	/**
	 * 
	 */

	@DataItemName("PersonnelRef.Abbreviation.ScName")
	public java.lang.String getScName() {
		return this.ScName;
	}

	public void setScName(java.lang.String Scname) {
		this.ScName = Scname;
	}

	/**
	 * 
	 */

	@DataItemName("PersonnelRef.Abbreviation.SocrName")
	public java.lang.String getSocrName() {
		return this.SocrName;
	}

	public void setSocrName(java.lang.String Socrname) {
		this.SocrName = Socrname;
	}

	/**
	 * 
	 */

	public java.lang.String getKodTSt() {
		return this.KodTSt;
	}

	public void setKodTSt(java.lang.String KodTSt) {
		this.KodTSt = KodTSt;
	}

}