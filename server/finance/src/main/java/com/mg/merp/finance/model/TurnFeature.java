/*
 * TurnFeature.java
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
package com.mg.merp.finance.model;

import java.io.Serializable;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.service.PersistentObjectHibernate;

/**
 * @author Artem V. Sharapov
 * @version $Id: TurnFeature.java,v 1.12 2009/02/16 07:45:11 sharapov Exp $
 */
public class TurnFeature extends PersistentObjectHibernate implements Serializable {

	// Fields

	private java.lang.Integer id;

	private com.mg.merp.finance.model.Account account;

	private com.mg.merp.finance.model.Account feature;

	private com.mg.merp.core.model.SysClient sysClient;

	private com.mg.merp.finance.model.TurnAccount turnAccount;

	private com.mg.merp.reference.model.Currency curCode;

	private com.mg.merp.finance.model.FinPeriod period;
	
	private java.lang.Integer analytics1;

	private java.lang.Integer analytics2;

	private java.lang.Integer analytics3;

	private java.lang.Integer analytics4;

	private java.lang.Integer analytics5;

	private java.lang.Integer featureAnalytics1;

	private java.lang.Integer featureAnalytics2;

	private java.lang.Integer featureAnalytics3;

	private java.lang.Integer featureAnalytics4;

	private java.lang.Integer featureAnalytics5;

	private java.math.BigDecimal remnBegCur;

	private java.math.BigDecimal remnBegNat;

	private java.math.BigDecimal remnBegCurPlan;

	private java.math.BigDecimal remnBegNatPlan;

	private java.math.BigDecimal incomeNat;

	private java.math.BigDecimal incomeCur;

	private java.math.BigDecimal outcomeNat;

	private java.math.BigDecimal outcomeCur;

	private java.math.BigDecimal remnEndNat;

	private java.math.BigDecimal remnEndCur;

	private java.math.BigDecimal incomeNatPlan;

	private java.math.BigDecimal incomeCurPlan;

	private java.math.BigDecimal outcomeNatPlan;

	private java.math.BigDecimal outcomeCurPlan;

	private java.math.BigDecimal remnEndNatPlan;

	private java.math.BigDecimal remnEndCurPlan;

	// Constructors


	/** default constructor */
	public TurnFeature() {
	}

	/** constructor with id */
	public TurnFeature(java.lang.Integer Id) {
		this.id = Id;
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("ID")
	public java.lang.Integer getId() {
		return this.id;
	}

	public void setId(java.lang.Integer Id) {
		this.id = Id;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.TurnAccount.Feature")
	public com.mg.merp.finance.model.Account getFeature() {
		return this.feature;
	}

	public void setFeature(com.mg.merp.finance.model.Account Finaccount) {
		this.feature = Finaccount;
	}

	/**
	 * 
	 */

	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.sysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.sysClient = SysClient;
	}

	/**
	 * 
	 */

	public com.mg.merp.finance.model.TurnAccount getTurnAccount() {
		return this.turnAccount;
	}

	public void setTurnAccount(com.mg.merp.finance.model.TurnAccount Finturnacc) {
		this.turnAccount = Finturnacc;
	}

	/**
	 * 
	 */

	public com.mg.merp.reference.model.Currency getCurCode() {
		return this.curCode;
	}

	public void setCurCode(com.mg.merp.reference.model.Currency Currency) {
		this.curCode = Currency;
	}

	/**
	 * 
	 */

	public com.mg.merp.finance.model.FinPeriod getPeriod() {
		return this.period;
	}

	public void setPeriod(com.mg.merp.finance.model.FinPeriod Finperiod) {
		this.period = Finperiod;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.TurnAcc.RemnBegCur")
	public java.math.BigDecimal getRemnBegCur() {
		return this.remnBegCur;
	}

	public void setRemnBegCur(java.math.BigDecimal Remnbegcur) {
		this.remnBegCur = Remnbegcur;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.TurnAcc.RemnBegNat")
	public java.math.BigDecimal getRemnBegNat() {
		return this.remnBegNat;
	}

	public void setRemnBegNat(java.math.BigDecimal Remnbegnat) {
		this.remnBegNat = Remnbegnat;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.TurnAcc.RemnBegCurPlan")
	public java.math.BigDecimal getRemnBegCurPlan() {
		return this.remnBegCurPlan;
	}

	public void setRemnBegCurPlan(java.math.BigDecimal Remnbegcurplan) {
		this.remnBegCurPlan = Remnbegcurplan;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.TurnAcc.RemnBegNatPlan")
	public java.math.BigDecimal getRemnBegNatPlan() {
		return this.remnBegNatPlan;
	}

	public void setRemnBegNatPlan(java.math.BigDecimal Remnbegnatplan) {
		this.remnBegNatPlan = Remnbegnatplan;
	}

	/**
	 * @return Returns the account.
	 */
	public com.mg.merp.finance.model.Account getAccount() {
		return account;
	}

	/**
	 * @param account
	 *            The account to set.
	 */
	public void setAccount(com.mg.merp.finance.model.Account account) {
		this.account = account;
	}

	/**
	 * @return Returns the incomeCur.
	 */
	@DataItemName("Finance.TurnAcc.IncomeCur")
	public java.math.BigDecimal getIncomeCur() {
		return incomeCur;
	}

	/**
	 * @param incomeCur
	 *            The incomeCur to set.
	 */
	public void setIncomeCur(java.math.BigDecimal incomeCur) {
		this.incomeCur = incomeCur;
	}

	/**
	 * @return Returns the incomeCurPlan.
	 */
	@DataItemName("Finance.TurnAcc.IncomeCurPlan")
	public java.math.BigDecimal getIncomeCurPlan() {
		return incomeCurPlan;
	}

	/**
	 * @param incomeCurPlan
	 *            The incomeCurPlan to set.
	 */
	public void setIncomeCurPlan(java.math.BigDecimal incomeCurPlan) {
		this.incomeCurPlan = incomeCurPlan;
	}

	/**
	 * @return Returns the incomeNat.
	 */
	@DataItemName("Finance.TurnAcc.IncomeNat")
	public java.math.BigDecimal getIncomeNat() {
		return incomeNat;
	}

	/**
	 * @param incomeNat
	 *            The incomeNat to set.
	 */
	public void setIncomeNat(java.math.BigDecimal incomeNat) {
		this.incomeNat = incomeNat;
	}

	/**
	 * @return Returns the incomeNatPlan.
	 */
	@DataItemName("Finance.TurnAcc.IncomeNatPlan")
	public java.math.BigDecimal getIncomeNatPlan() {
		return incomeNatPlan;
	}

	/**
	 * @param incomeNatPlan
	 *            The incomeNatPlan to set.
	 */
	public void setIncomeNatPlan(java.math.BigDecimal incomeNatPlan) {
		this.incomeNatPlan = incomeNatPlan;
	}

	/**
	 * @return Returns the outcomeCur.
	 */
	@DataItemName("Finance.TurnAcc.OutcomeCur")
	public java.math.BigDecimal getOutcomeCur() {
		return outcomeCur;
	}

	/**
	 * @param outcomeCur
	 *            The outcomeCur to set.
	 */
	public void setOutcomeCur(java.math.BigDecimal outcomeCur) {
		this.outcomeCur = outcomeCur;
	}

	/**
	 * @return Returns the outcomeCurPlan.
	 */
	@DataItemName("Finance.TurnAcc.OutcomeCurPlan")
	public java.math.BigDecimal getOutcomeCurPlan() {
		return outcomeCurPlan;
	}

	/**
	 * @param outcomeCurPlan
	 *            The outcomeCurPlan to set.
	 */
	public void setOutcomeCurPlan(java.math.BigDecimal outcomeCurPlan) {
		this.outcomeCurPlan = outcomeCurPlan;
	}

	/**
	 * @return Returns the outcomeNat.
	 */
	@DataItemName("Finance.TurnAcc.OutcomeNat")
	public java.math.BigDecimal getOutcomeNat() {
		return outcomeNat;
	}

	/**
	 * @param outcomeNat
	 *            The outcomeNat to set.
	 */
	public void setOutcomeNat(java.math.BigDecimal outcomeNat) {
		this.outcomeNat = outcomeNat;
	}

	/**
	 * @return Returns the outcomeNatPlan.
	 */
	@DataItemName("Finance.TurnAcc.OutcomeNatPlan")
	public java.math.BigDecimal getOutcomeNatPlan() {
		return outcomeNatPlan;
	}

	/**
	 * @param outcomeNatPlan
	 *            The outcomeNatPlan to set.
	 */
	public void setOutcomeNatPlan(java.math.BigDecimal outcomeNatPlan) {
		this.outcomeNatPlan = outcomeNatPlan;
	}

	/**
	 * @return Returns the remnEndCur.
	 */
	@DataItemName("Finance.TurnAcc.RemnEndCur")
	public java.math.BigDecimal getRemnEndCur() {
		return remnEndCur;
	}

	/**
	 * @param remnEndCur
	 *            The remnEndCur to set.
	 */
	public void setRemnEndCur(java.math.BigDecimal remnEndCur) {
		this.remnEndCur = remnEndCur;
	}

	/**
	 * @return Returns the remnEndCurPlan.
	 */
	@DataItemName("Finance.TurnAcc.RemnEndCurPlan")
	public java.math.BigDecimal getRemnEndCurPlan() {
		return remnEndCurPlan;
	}

	/**
	 * @param remnEndCurPlan
	 *            The remnEndCurPlan to set.
	 */
	public void setRemnEndCurPlan(java.math.BigDecimal remnEndCurPlan) {
		this.remnEndCurPlan = remnEndCurPlan;
	}

	/**
	 * @return Returns the remnEndNat.
	 */
	@DataItemName("Finance.TurnAcc.RemnEndNat")
	public java.math.BigDecimal getRemnEndNat() {
		return remnEndNat;
	}

	/**
	 * @param remnEndNat
	 *            The remnEndNat to set.
	 */
	public void setRemnEndNat(java.math.BigDecimal remnEndNat) {
		this.remnEndNat = remnEndNat;
	}

	/**
	 * @return Returns the remnEndNatPlan.
	 */
	@DataItemName("Finance.TurnAcc.RemnEndNatPlan")
	public java.math.BigDecimal getRemnEndNatPlan() {
		return remnEndNatPlan;
	}

	/**
	 * @param remnEndNatPlan
	 *            The remnEndNatPlan to set.
	 */
	public void setRemnEndNatPlan(java.math.BigDecimal remnEndNatPlan) {
		this.remnEndNatPlan = remnEndNatPlan;
	}
	
	@DataItemName("Finance.Analytics1")
	public java.lang.Integer getAnalytics1() {
		return analytics1;
	}

	public void setAnalytics1(java.lang.Integer analytics1) {
		this.analytics1 = analytics1;
	}

	@DataItemName("Finance.Analytics2")
	public java.lang.Integer getAnalytics2() {
		return analytics2;
	}

	public void setAnalytics2(java.lang.Integer analytics2) {
		this.analytics2 = analytics2;
	}

	@DataItemName("Finance.Analytics3")
	public java.lang.Integer getAnalytics3() {
		return analytics3;
	}

	public void setAnalytics3(java.lang.Integer analytics3) {
		this.analytics3 = analytics3;
	}

	@DataItemName("Finance.Analytics4")
	public java.lang.Integer getAnalytics4() {
		return analytics4;
	}

	public void setAnalytics4(java.lang.Integer analytics4) {
		this.analytics4 = analytics4;
	}

	@DataItemName("Finance.Analytics5")
	public java.lang.Integer getAnalytics5() {
		return analytics5;
	}

	public void setAnalytics5(java.lang.Integer analytics5) {
		this.analytics5 = analytics5;
	}

	@DataItemName("Finance.FeatAnalytics1")
	public java.lang.Integer getFeatureAnalytics1() {
		return featureAnalytics1;
	}

	public void setFeatureAnalytics1(java.lang.Integer featureAnalytics1) {
		this.featureAnalytics1 = featureAnalytics1;
	}

	@DataItemName("Finance.FeatAnalytics2")
	public java.lang.Integer getFeatureAnalytics2() {
		return featureAnalytics2;
	}

	public void setFeatureAnalytics2(java.lang.Integer featureAnalytics2) {
		this.featureAnalytics2 = featureAnalytics2;
	}

	@DataItemName("Finance.FeatAnalytics3")
	public java.lang.Integer getFeatureAnalytics3() {
		return featureAnalytics3;
	}

	public void setFeatureAnalytics3(java.lang.Integer featureAnalytics3) {
		this.featureAnalytics3 = featureAnalytics3;
	}

	@DataItemName("Finance.FeatAnalytics4")
	public java.lang.Integer getFeatureAnalytics4() {
		return featureAnalytics4;
	}

	public void setFeatureAnalytics4(java.lang.Integer featureAnalytics4) {
		this.featureAnalytics4 = featureAnalytics4;
	}

	@DataItemName("Finance.FeatAnalytics5")
	public java.lang.Integer getFeatureAnalytics5() {
		return featureAnalytics5;
	}

	public void setFeatureAnalytics5(java.lang.Integer featureAnalytics5) {
		this.featureAnalytics5 = featureAnalytics5;
	}

	@DataItemName("Finance.TurnAcc.IncomeCurDiff")
	public java.math.BigDecimal getIncomeCurDiff() {
		return incomeCur.subtract(incomeCurPlan);
	}


	@DataItemName("Finance.TurnAcc.IncomeNatDiff")	
	public java.math.BigDecimal getIncomeNatDiff() {
		return incomeNat.subtract(incomeNatPlan);
	}

	@DataItemName("Finance.TurnAcc.OutcomeCurDiff")		
	public java.math.BigDecimal getOutcomeCurDiff() {
		return outcomeCur.subtract(outcomeCurPlan);
	}

	@DataItemName("Finance.TurnAcc.OutcomeNatDiff")		
	public java.math.BigDecimal getOutcomeNatDiff() {
		return outcomeNat.subtract(outcomeNatPlan);
	}

	@DataItemName("Finance.TurnAcc.RemnBegCurDiff")
	public java.math.BigDecimal getRemnBegCurDiff() {
		return remnBegCur.subtract(remnBegCurPlan);
	}

	@DataItemName("Finance.TurnAcc.RemnBegNatDiff")
	public java.math.BigDecimal getRemnBegNatDiff() {
		return remnBegNat.subtract(remnBegNatPlan);
	}

	@DataItemName("Finance.TurnAcc.RemnEndCurDiff")	
	public java.math.BigDecimal getRemnEndCurDiff() {
		return remnEndCur.subtract(remnEndCurPlan);
	}

	@DataItemName("Finance.TurnAcc.RemnEndNatDiff")	
	public java.math.BigDecimal getRemnEndNatDiff() {
		return remnEndNat.subtract(remnEndNatPlan);
	}
		
}