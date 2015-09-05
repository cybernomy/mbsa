/*
 * Account.java
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
package com.mg.merp.finance.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: Account.java,v 1.7 2006/11/02 15:56:35 safonov Exp $
 */
@DataItemName("Finance.Account")
public class Account extends com.mg.framework.service.PersistentObjectHibernate
		implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.core.model.SysClass Anl4Class;

	private com.mg.merp.core.model.Folder Folder;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.core.model.SysClass Anl3Class;

	private com.mg.merp.core.model.SysClass Anl5Class;

	private com.mg.merp.core.model.SysClass Anl2Class;

	private com.mg.merp.core.model.SysClass Anl1Class;

	private com.mg.merp.reference.model.Currency CurrencyCode;

	private java.lang.String Code;

	private java.lang.String UpCode;

	private java.lang.String AccName;

	private short Kind;

	private boolean Anl1Kind;

	private boolean Anl2Kind;

	private boolean Anl3Kind;

	private boolean Anl4Kind;

	private boolean Anl5Kind;

	private java.util.Set<com.mg.merp.finance.model.Analytics> SetOfFinAnl;

	// Constructors

	/** default constructor */
	public Account() {
	}

	/** constructor with id */
	public Account(java.lang.Integer Id) {
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
	@DataItemName("Finance.Acc.SysClass4")
	public com.mg.merp.core.model.SysClass getAnl4Class() {
		return this.Anl4Class;
	}

	public void setAnl4Class(com.mg.merp.core.model.SysClass SysClass) {
		this.Anl4Class = SysClass;
	}

	/**
	 * 
	 */

	public com.mg.merp.core.model.Folder getFolder() {
		return this.Folder;
	}

	public void setFolder(com.mg.merp.core.model.Folder Folder) {
		this.Folder = Folder;
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
	@DataItemName("Finance.Acc.SysClass3")
	public com.mg.merp.core.model.SysClass getAnl3Class() {
		return this.Anl3Class;
	}

	public void setAnl3Class(com.mg.merp.core.model.SysClass SysClass_1) {
		this.Anl3Class = SysClass_1;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Acc.SysClass5")
	public com.mg.merp.core.model.SysClass getAnl5Class() {
		return this.Anl5Class;
	}

	public void setAnl5Class(com.mg.merp.core.model.SysClass SysClass_2) {
		this.Anl5Class = SysClass_2;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Acc.SysClass2")
	public com.mg.merp.core.model.SysClass getAnl2Class() {
		return this.Anl2Class;
	}

	public void setAnl2Class(com.mg.merp.core.model.SysClass SysClass_3) {
		this.Anl2Class = SysClass_3;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Acc.SysClass1")
	public com.mg.merp.core.model.SysClass getAnl1Class() {
		return this.Anl1Class;
	}

	public void setAnl1Class(com.mg.merp.core.model.SysClass SysClass_4) {
		this.Anl1Class = SysClass_4;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Acc.CurrencyCode")
	public com.mg.merp.reference.model.Currency getCurrencyCode() {
		return this.CurrencyCode;
	}

	public void setCurrencyCode(com.mg.merp.reference.model.Currency Currency) {
		this.CurrencyCode = Currency;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Acc.Code")
	public java.lang.String getCode() {
		return this.Code;
	}

	public void setCode(java.lang.String Code) {
		this.Code = Code;
	}

	/**
	 * 
	 */

	public java.lang.String getUpCode() {
		return this.UpCode;
	}

	public void setUpCode(java.lang.String Upcode) {
		this.UpCode = Upcode;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Acc.Accname")
	public java.lang.String getAccName() {
		return this.AccName;
	}

	public void setAccName(java.lang.String Accname) {
		this.AccName = Accname;
	}

	/**
	 * 
	 */

	public short getKind() {
		return this.Kind;
	}

	public void setKind(short Kind) {
		this.Kind = Kind;
	}

	public java.util.Set<com.mg.merp.finance.model.Analytics> getSetOfFinAnl() {
		return this.SetOfFinAnl;
	}

	public void setSetOfFinAnl(
			java.util.Set<com.mg.merp.finance.model.Analytics> SetOfFinAnl) {
		this.SetOfFinAnl = SetOfFinAnl;
	}

	public String getAmountAnl() {
		int[] amount = new int[5];
		java.util.Set<com.mg.merp.finance.model.Analytics> list = getSetOfFinAnl();
		if (list != null)
			for (com.mg.merp.finance.model.Analytics anlPlan : list)
				amount[anlPlan.getAnlLevel() - 1] += 1;
		return String.valueOf(amount[0]) + ";" + String.valueOf(amount[1])
				+ ";" + String.valueOf(amount[2]) + ";"
				+ String.valueOf(amount[3]) + ";" + String.valueOf(amount[4])
				+ ";";
	}

	public void setAmountAnl(String amountAnl) {
		// read only property
	}
	
	@DataItemName("Finance.Account.AnlBusinessService")
	public boolean isAnl1Kind() {
		return Anl1Kind;
	}

	public void setAnl1Kind(boolean anl1BusinessService) {
		Anl1Kind = anl1BusinessService;
	}
	
	@DataItemName("Finance.Account.AnlBusinessService")
	public boolean isAnl2Kind() {
		return Anl2Kind;
	}

	public void setAnl2Kind(boolean anl2BusinessService) {
		Anl2Kind = anl2BusinessService;
	}
	
	@DataItemName("Finance.Account.AnlBusinessService")
	public boolean isAnl3Kind() {
		return Anl3Kind;
	}

	public void setAnl3Kind(boolean anl3BusinessService) {
		Anl3Kind = anl3BusinessService;
	}

	@DataItemName("Finance.Account.AnlBusinessService")
	public boolean isAnl4Kind() {
		return Anl4Kind;
	}

	public void setAnl4Kind(boolean anl4BusinessService) {
		Anl4Kind = anl4BusinessService;
	}

	@DataItemName("Finance.Account.AnlBusinessService")
	public boolean isAnl5Kind() {
		return Anl5Kind;
	}

	public void setAnl5Kind(boolean anl5BusinessService) {
		Anl5Kind = anl5BusinessService;
	}
}