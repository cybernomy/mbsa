/*
 * PersonnelService.java
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
 * @version $Id: PersonnelService.java,v 1.4 2006/06/19 07:54:39 leonova Exp $
 */
public class PersonnelService extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private int Id;

	private com.mg.merp.personnelref.model.Personnel Personnel;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.personnelref.model.ServiceKind ServiceKind;

	private java.util.Date BeginDate;

	private java.util.Date EndDate;

	private java.math.BigDecimal LengthService;

	private java.lang.String Comment;

	private boolean IsGoingOn;

	private java.math.BigDecimal Ratio;

	private java.util.Set SetOfPrefPfcodeInService;

	// Constructors

	/** default constructor */
	public PersonnelService() {
	}

	/** constructor with id */
	public PersonnelService(int Id) {
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

	public com.mg.merp.personnelref.model.Personnel getPersonnel() {
		return this.Personnel;
	}

	public void setPersonnel(
			com.mg.merp.personnelref.model.Personnel PrefPersonnel) {
		this.Personnel = PrefPersonnel;
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
	public com.mg.merp.personnelref.model.ServiceKind getServiceKind() {
		return this.ServiceKind;
	}

	public void setServiceKind(
			com.mg.merp.personnelref.model.ServiceKind PrefServiceKind) {
		this.ServiceKind = PrefServiceKind;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Personnel.BeginDate")
	public java.util.Date getBeginDate() {
		return this.BeginDate;
	}

	public void setBeginDate(java.util.Date Begindate) {
		this.BeginDate = Begindate;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Personnel.EndDate")
	public java.util.Date getEndDate() {
		return this.EndDate;
	}

	public void setEndDate(java.util.Date Enddate) {
		this.EndDate = Enddate;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Personnel.LengthService")
	public java.math.BigDecimal getLengthService() {
		return this.LengthService;
	}

	public void setLengthService(java.math.BigDecimal Lengthservice) {
		this.LengthService = Lengthservice;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Personnel.Comment")
	public java.lang.String getComment() {
		return this.Comment;
	}

	public void setComment(java.lang.String Comment) {
		this.Comment = Comment;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Personnel.IsGoingOn")
	public boolean getIsGoingOn() {
		return this.IsGoingOn;
	}

	public void setIsGoingOn(boolean IsGoingon) {
		this.IsGoingOn = IsGoingon;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Personnel.Ratio")
	public java.math.BigDecimal getRatio() {
		return this.Ratio;
	}

	public void setRatio(java.math.BigDecimal Ratio) {
		this.Ratio = Ratio;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfPrefPfcodeInService() {
		return this.SetOfPrefPfcodeInService;
	}

	public void setSetOfPrefPfcodeInService(
			java.util.Set SetOfPrefPfcodeInService) {
		this.SetOfPrefPfcodeInService = SetOfPrefPfcodeInService;
	}

}