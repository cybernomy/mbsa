/*
 * RemnAnl.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
 * Модель бизнес-компонента "Остатки и обороты по аналитическим счетам бух. учета"
 * 
 * @author hbm2java
 * @author Artem V. Sharapov
 * @version $Id: RemnAnl.java,v 1.6 2006/12/26 09:53:31 sharapov Exp $
 */
public class RemnAnl extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {
	// Fields    
	private java.lang.Integer Id;
	private com.mg.merp.account.model.AccPlan AccPlan;
	private com.mg.merp.account.model.Period Period;
	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.account.model.AnlPlan AnlPlan1;
	private com.mg.merp.account.model.AnlPlan AnlPlan4;
	private com.mg.merp.account.model.AnlPlan AnlPlan2;
	private com.mg.merp.account.model.AnlPlan AnlPlan3;
	private com.mg.merp.account.model.AnlPlan AnlPlan5;

	private java.math.BigDecimal RemnBeginNatDb;
	private java.math.BigDecimal RemnBeginNatKt;
	private java.math.BigDecimal RemnBeginCurDb;
	private java.math.BigDecimal RemnBeginCurKt;
	private java.math.BigDecimal remnEndNatDb;
	private java.math.BigDecimal remnEndNatKt;
	private java.math.BigDecimal remnEndCurKt;
	private java.math.BigDecimal remnEndCurDb;
	private java.math.BigDecimal turnNatKt;
	private java.math.BigDecimal turnNatDb;
	private java.math.BigDecimal turnCurDb;
	private java.math.BigDecimal turnCurKt;

	// Constructors
	/** default constructor */
	public RemnAnl() {
	}

	/** constructor with id */
	public RemnAnl(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors
	@DataItemName("ID") //$NON-NLS-1$
	public java.lang.Integer getId () {
		return this.Id;
	}

	public void setId (java.lang.Integer Id) {
		this.Id = Id;
	}

	public com.mg.merp.account.model.AccPlan getAccPlan () {
		return this.AccPlan;
	}

	public void setAccPlan (com.mg.merp.account.model.AccPlan Accplan) {
		this.AccPlan = Accplan;
	}

	@DataItemName("Account.RemnAnl.AnlPlan4") //$NON-NLS-1$
	public com.mg.merp.account.model.AnlPlan getAnlPlan4 () {
		return this.AnlPlan4;
	}

	public void setAnlPlan4 (com.mg.merp.account.model.AnlPlan Anlplan4) {
		this.AnlPlan4 = Anlplan4;
	}

	@DataItemName("Account.RemnAnl.AnlPlan1") //$NON-NLS-1$
	public com.mg.merp.account.model.AnlPlan getAnlPlan1 () {
		return this.AnlPlan1;
	}

	public void setAnlPlan1 (com.mg.merp.account.model.AnlPlan Anlplan1) {
		this.AnlPlan1 = Anlplan1;
	}

	public com.mg.merp.core.model.SysClient getSysClient () {
		return this.SysClient;
	}

	public void setSysClient (com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	@DataItemName("Account.RemnAnl.AnlPlan2") //$NON-NLS-1$
	public com.mg.merp.account.model.AnlPlan getAnlPlan2 () {
		return this.AnlPlan2;
	}

	public void setAnlPlan2 (com.mg.merp.account.model.AnlPlan Anlplan2) {
		this.AnlPlan2 = Anlplan2;
	}

	@DataItemName("Account.RemnAnl.AnlPlan3") //$NON-NLS-1$
	public com.mg.merp.account.model.AnlPlan getAnlPlan3 () {
		return this.AnlPlan3;
	}

	public void setAnlPlan3 (com.mg.merp.account.model.AnlPlan Anlplan3) {
		this.AnlPlan3 = Anlplan3;
	}

	@DataItemName("Account.RemnAnl.AnlPlan5") //$NON-NLS-1$
	public com.mg.merp.account.model.AnlPlan getAnlPlan5 () {
		return this.AnlPlan5;
	}

	public void setAnlPlan5 (com.mg.merp.account.model.AnlPlan Anlplan5) {
		this.AnlPlan5 = Anlplan5;
	}


	public com.mg.merp.account.model.Period getPeriod () {
		return this.Period;
	}

	public void setPeriod (com.mg.merp.account.model.Period Period) {
		this.Period = Period;
	}

	@DataItemName("Account.RemnAnl.RemnBeginNatDb") //$NON-NLS-1$
	public java.math.BigDecimal getRemnBeginNatDb () {
		return this.RemnBeginNatDb;
	}

	public void setRemnBeginNatDb (java.math.BigDecimal Remnbeginnatdb) {
		this.RemnBeginNatDb = Remnbeginnatdb;
	}

	@DataItemName("Account.RemnAnl.RemnBeginNatKt") //$NON-NLS-1$
	public java.math.BigDecimal getRemnBeginNatKt () {
		return this.RemnBeginNatKt;
	}

	public void setRemnBeginNatKt (java.math.BigDecimal Remnbeginnatkt) {
		this.RemnBeginNatKt = Remnbeginnatkt;
	}

	@DataItemName("Account.RemnAnl.RemnBeginCurDb") //$NON-NLS-1$
	public java.math.BigDecimal getRemnBeginCurDb () {
		return this.RemnBeginCurDb;
	}

	public void setRemnBeginCurDb (java.math.BigDecimal Remnbegincurdb) {
		this.RemnBeginCurDb = Remnbegincurdb;
	}

	@DataItemName("Account.RemnAnl.RemnBeginCurKt") //$NON-NLS-1$
	public java.math.BigDecimal getRemnBeginCurKt () {
		return this.RemnBeginCurKt;
	}

	public void setRemnBeginCurKt (java.math.BigDecimal Remnbegincurkt) {
		this.RemnBeginCurKt = Remnbegincurkt;
	}

	@DataItemName("Account.RemnAnl.RemnEndCurDb") //$NON-NLS-1$
	public java.math.BigDecimal getRemnEndCurDb() {
		return remnEndCurDb;
	}

	public void setRemnEndCurDb(java.math.BigDecimal remnEndCurDb) {
		this.remnEndCurDb = remnEndCurDb;
	}

	@DataItemName("Account.RemnAnl.RemnEndCurKt") //$NON-NLS-1$
	public java.math.BigDecimal getRemnEndCurKt() {
		return remnEndCurKt;
	}

	public void setRemnEndCurKt(java.math.BigDecimal remnEndCurKt) {
		this.remnEndCurKt = remnEndCurKt;
	}

	@DataItemName("Account.RemnAnl.RemnEndNatDb") //$NON-NLS-1$
	public java.math.BigDecimal getRemnEndNatDb() {
		return remnEndNatDb;
	}

	public void setRemnEndNatDb(java.math.BigDecimal remnEndNatDb) {
		this.remnEndNatDb = remnEndNatDb;
	}

	@DataItemName("Account.RemnAnl.RemnEndNatKt") //$NON-NLS-1$
	public java.math.BigDecimal getRemnEndNatKt() {
		return remnEndNatKt;
	}

	public void setRemnEndNatKt(java.math.BigDecimal remnEndNatKt) {
		this.remnEndNatKt = remnEndNatKt;
	}

	@DataItemName("Account.RemnAnl.TurnCurDb") //$NON-NLS-1$
	public java.math.BigDecimal getTurnCurDb() {
		return turnCurDb;
	}

	public void setTurnCurDb(java.math.BigDecimal turnCurDb) {
		this.turnCurDb = turnCurDb;
	}

	@DataItemName("Account.RemnAnl.TurnCurKt") //$NON-NLS-1$
	public java.math.BigDecimal getTurnCurKt() {
		return turnCurKt;
	}

	public void setTurnCurKt(java.math.BigDecimal turnCurKt) {
		this.turnCurKt = turnCurKt;
	}

	@DataItemName("Account.RemnAnl.TurnNatDb") //$NON-NLS-1$
	public java.math.BigDecimal getTurnNatDb() {
		return turnNatDb;
	}

	public void setTurnNatDb(java.math.BigDecimal turnNatDb) {
		this.turnNatDb = turnNatDb;
	}

	@DataItemName("Account.RemnAnl.TurnNatKt") //$NON-NLS-1$
	public java.math.BigDecimal getTurnNatKt() {
		return turnNatKt;
	}

	public void setTurnNatKt(java.math.BigDecimal turnNatKt) {
		this.turnNatKt = turnNatKt;
	}
}