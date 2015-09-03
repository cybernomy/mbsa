/*
 * AdvanceRepSpec.java
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
package com.mg.merp.account.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Объектная модель бизнес-компонента "Спецификации авансовых отчетов"
 *  
 * @author hbm2java
 * @version $Id: AdvanceRepSpec.java,v 1.6 2008/03/27 11:56:22 alikaev Exp $
 */
public class AdvanceRepSpec extends com.mg.merp.document.model.DocSpec implements java.io.Serializable {

	// Fields

	private com.mg.merp.account.model.AnlPlan Anl2;

	private com.mg.merp.account.model.AccPlan AccPlan;

	private com.mg.merp.account.model.AnlPlan Anl4;

	private com.mg.merp.account.model.AnlPlan Anl3;

	private com.mg.merp.account.model.AnlPlan Anl1;

	private com.mg.merp.account.model.AnlPlan Anl5;

	private java.lang.Integer Num;

	private java.lang.String ExpenseDocNumber;

	private java.util.Date ExpenseDocDate;

	private java.lang.String ExpenseDocName;

	private java.math.BigDecimal ExpenseSum;

	private java.math.BigDecimal ConsideredSum;

	// Constructors

	/** default constructor */
	public AdvanceRepSpec() {
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("Account.Adv.Anl2")
	public com.mg.merp.account.model.AnlPlan getAnl2() {
		return this.Anl2;
	}

	public void setAnl2(com.mg.merp.account.model.AnlPlan Anl2) {
		this.Anl2 = Anl2;
	}

	@DataItemName("Account.AdvanceRepSpec.AccPlan")
	public com.mg.merp.account.model.AccPlan getAccPlan() {
		return AccPlan;
	}

	public void setAccPlan(com.mg.merp.account.model.AccPlan accPlan) {
		AccPlan = accPlan;
	}

	/**
	 * 
	 */
	@DataItemName("Account.Adv.Anl4")
	public com.mg.merp.account.model.AnlPlan getAnl4() {
		return this.Anl4;
	}

	public void setAnl4(com.mg.merp.account.model.AnlPlan Anl4) {
		this.Anl4 = Anl4;
	}

	/**
	 * 
	 */
	@DataItemName("Account.Adv.Anl3")
	public com.mg.merp.account.model.AnlPlan getAnl3() {
		return this.Anl3;
	}

	public void setAnl3(com.mg.merp.account.model.AnlPlan Anl3) {
		this.Anl3 = Anl3;
	}

	/**
	 * 
	 */
	@DataItemName("Account.Adv.Anl1")
	public com.mg.merp.account.model.AnlPlan getAnl1() {
		return this.Anl1;
	}

	public void setAnl1(com.mg.merp.account.model.AnlPlan Anl1) {
		this.Anl1 = Anl1;
	}

	/**
	 * 
	 */
	@DataItemName("Account.Adv.Anl5")
	public com.mg.merp.account.model.AnlPlan getAnl5() {
		return this.Anl5;
	}

	public void setAnl5(com.mg.merp.account.model.AnlPlan Anl5) {
		this.Anl5 = Anl5;
	}

	/**
	 * 
	 */
	@DataItemName("Account.Adv.Num")
	public java.lang.Integer getNum() {
		return this.Num;
	}

	public void setNum(java.lang.Integer Num) {
		this.Num = Num;
	}

	/**
	 * 
	 */
	@DataItemName("Account.Adv.ExpenseDocNumber")
	public java.lang.String getExpenseDocNumber() {
		return this.ExpenseDocNumber;
	}

	public void setExpenseDocNumber(java.lang.String ExpenseDocNumber) {
		this.ExpenseDocNumber = ExpenseDocNumber;
	}

	/**
	 * 
	 */
	@DataItemName("Account.Adv.ExpenseDocDate")
	public java.util.Date getExpenseDocDate() {
		return this.ExpenseDocDate;
	}

	public void setExpenseDocDate(java.util.Date ExpenseDocDate) {
		this.ExpenseDocDate = ExpenseDocDate;
	}

	/**
	 * 
	 */
	@DataItemName("Account.Adv.ExpenseDocName")
	public java.lang.String getExpenseDocName() {
		return this.ExpenseDocName;
	}

	public void setExpenseDocName(java.lang.String ExpenseDocName) {
		this.ExpenseDocName = ExpenseDocName;
	}

	/**
	 * 
	 */
	@DataItemName("Account.Adv.ExpenseSum")
	public java.math.BigDecimal getExpenseSum() {
		return this.ExpenseSum;
	}

	public void setExpenseSum(java.math.BigDecimal ExpenseSum) {
		this.ExpenseSum = ExpenseSum;
	}
	
	@DataItemName("Account.Adv.ConsideredSum")
	public java.math.BigDecimal getConsideredSum() {
		return ConsideredSum;
	}

	public void setConsideredSum(java.math.BigDecimal consideredSum) {
		ConsideredSum = consideredSum;
	}

}