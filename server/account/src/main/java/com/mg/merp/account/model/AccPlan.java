/*
 * AccPlan.java
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
package com.mg.merp.account.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: AccPlan.java,v 1.7 2008/03/13 06:19:46 alikaev Exp $
 */
@DataItemName("Account.AccPlan")
public class AccPlan extends com.mg.framework.service.PersistentObjectHibernate	implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.core.model.Folder Folder;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.reference.model.Currency Currency;

	private java.lang.String UpAcc;

	private java.lang.String Acc;

	private java.lang.String AccName;

	private boolean IsAnl;

	private boolean IsBal;

	private boolean IsWork;

	private AnlForm anlForm;

	private AccType AccType;

	private java.util.Set<com.mg.merp.account.model.AnlPlan> anlPlans;
	
	private boolean isMaterialAcc;
	
	private boolean isRealisationAcc;

	// Constructors

	/** default constructor */
	public AccPlan() {
	}

	/** constructor with id */
	public AccPlan(java.lang.Integer Id) {
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
	public com.mg.merp.reference.model.Currency getCurrency() {
		return this.Currency;
	}

	public void setCurrency(com.mg.merp.reference.model.Currency Currency) {
		this.Currency = Currency;
	}

	/**
	 * 
	 */

	public java.lang.String getUpAcc() {
		return this.UpAcc;
	}

	public void setUpAcc(java.lang.String UpAcc) {
		this.UpAcc = UpAcc;
	}

	/**
	 * 
	 */
	@DataItemName("Account.BigCode")
	public java.lang.String getAcc() {
		return this.Acc;
	}

	public void setAcc(java.lang.String Acc) {
		this.Acc = Acc;
	}

	/**
	 * 
	 */
	@DataItemName("Account.Name")
	public java.lang.String getAccName() {
		return this.AccName;
	}

	public void setAccName(java.lang.String AccName) {
		this.AccName = AccName;
	}

	/**
	 * 
	 */

	public boolean getIsAnl() {
		return this.IsAnl;
	}

	public void setIsAnl(boolean IsAnl) {
		this.IsAnl = IsAnl;
	}

	/**
	 * 
	 */
	@DataItemName("Account.Plan.IsBal")
	public boolean getIsBal() {
		return this.IsBal;
	}

	public void setIsBal(boolean IsBal) {
		this.IsBal = IsBal;
	}

	/**
	 * 
	 */
	@DataItemName("Account.Plan.IsWork")
	public boolean getIsWork() {
		return this.IsWork;
	}

	public void setIsWork(boolean IsWork) {
		this.IsWork = IsWork;
	}

	/**
	 * 
	 */

	public AnlForm getAnlForm() {
		return this.anlForm;
	}

	public void setAnlForm(AnlForm anlForm) {
		this.anlForm = anlForm;
	}

	/**
	 * 
	 */

	public AccType getAccType() {
		return this.AccType;
	}

	public void setAccType(AccType AccType) {
		this.AccType = AccType;
	}

	public java.util.Set<com.mg.merp.account.model.AnlPlan> getAnlPlans() {
		return this.anlPlans;
	}

	public void setAnlPlans(java.util.Set<com.mg.merp.account.model.AnlPlan> anlPlans) {
		this.anlPlans = anlPlans;
	}

	public String getAmountAnl() {
		int[] amount = new int[5];
		java.util.Set<com.mg.merp.account.model.AnlPlan> list = getAnlPlans();
		if (list == null)
			return "";
		for (com.mg.merp.account.model.AnlPlan anlPlan : list)
			amount[anlPlan.getAnlLevel() - 1] += 1;
		return String.valueOf(amount[0]) + ";" + String.valueOf(amount[1])
				+ ";" + String.valueOf(amount[2]) + ";"
				+ String.valueOf(amount[3]) + ";" + String.valueOf(amount[4])
				+ ";";
	}

	public void setAmountAnl(String amountAnl) {
		// read only property
	}

	public boolean isMaterialAcc() {
		AnlForm anlForm = this.getAnlForm();
		return anlForm != null && (anlForm == AnlForm.BASEMEANS || anlForm == AnlForm.CALCCOST || anlForm == AnlForm.BATCHCALC || 
				anlForm == AnlForm.AVERAGECOST || anlForm == AnlForm.FIFO || anlForm == AnlForm.LIFO || 
				anlForm == AnlForm.MBP);	
	}

	public boolean isRealisationAcc() {
		return this.getAnlForm() != null && this.getAnlForm() == AnlForm.REALISARION;
	}
	
}