/*
 * Specification.java
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
 * @version $Id: Specification.java,v 1.7 2006/10/30 13:52:24 leonova Exp $
 */
public class Specification extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.finance.model.TurnFeature DstTurnFeat;

	private com.mg.merp.finance.model.TurnAccount DstTurnAcc;

	private com.mg.merp.finance.model.Specification Parent;

	private com.mg.merp.finance.model.FinOperation FinOper;

	private com.mg.merp.finance.model.TurnAccount SrcTurnAcc;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.finance.model.Account DstAcc;

	private com.mg.merp.finance.model.Account SrcAcc;

	private com.mg.merp.finance.model.TurnFeature SrcTurnFeat;

	private java.lang.Integer SrcAnl1;

	private java.lang.Integer SrcAnl2;

	private java.lang.Integer SrcAnl3;

	private java.lang.Integer SrcAnl4;

	private java.lang.Integer SrcAnl5;

	private java.lang.Integer DstAnl1;

	private java.lang.Integer DstAnl2;

	private java.lang.Integer DstAnl3;

	private java.lang.Integer DstAnl4;

	private java.lang.Integer DstAnl5;

	private java.math.BigDecimal SumNat;

	private java.math.BigDecimal SumCur;

	private boolean Planned;

	// Constructors

	/** default constructor */
	public Specification() {
	}

	/** constructor with id */
	public Specification(java.lang.Integer Id) {
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

	public com.mg.merp.finance.model.TurnFeature getDstTurnFeat() {
		return this.DstTurnFeat;
	}

	public void setDstTurnFeat(com.mg.merp.finance.model.TurnFeature Finturnfeat) {
		this.DstTurnFeat = Finturnfeat;
	}

	/**
	 * 
	 */

	public com.mg.merp.finance.model.TurnAccount getDstTurnAcc() {
		return this.DstTurnAcc;
	}

	public void setDstTurnAcc(com.mg.merp.finance.model.TurnAccount Finturnacc) {
		this.DstTurnAcc = Finturnacc;
	}

	/**
	 * 
	 */

	public com.mg.merp.finance.model.Specification getParent() {
		return this.Parent;
	}

	public void setParent(com.mg.merp.finance.model.Specification Finspec) {
		this.Parent = Finspec;
	}

	/**
	 * 
	 */

	public com.mg.merp.finance.model.FinOperation getFinOper() {
		return this.FinOper;
	}

	public void setFinOper(com.mg.merp.finance.model.FinOperation Finoper) {
		this.FinOper = Finoper;
	}

	/**
	 * 
	 */

	public com.mg.merp.finance.model.TurnAccount getSrcTurnAcc() {
		return this.SrcTurnAcc;
	}

	public void setSrcTurnAcc(com.mg.merp.finance.model.TurnAccount Finturnacc_1) {
		this.SrcTurnAcc = Finturnacc_1;
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
	@DataItemName("Finance.Oper.DstAcc")
	public com.mg.merp.finance.model.Account getDstAcc() {
		return this.DstAcc;
	}

	public void setDstAcc(com.mg.merp.finance.model.Account Finaccount) {
		this.DstAcc = Finaccount;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Oper.SrcAcc")
	public com.mg.merp.finance.model.Account getSrcAcc() {
		return this.SrcAcc;
	}

	public void setSrcAcc(com.mg.merp.finance.model.Account Finaccount_1) {
		this.SrcAcc = Finaccount_1;
	}

	/**
	 * 
	 */

	public com.mg.merp.finance.model.TurnFeature getSrcTurnFeat() {
		return this.SrcTurnFeat;
	}

	public void setSrcTurnFeat(
			com.mg.merp.finance.model.TurnFeature Finturnfeat_1) {
		this.SrcTurnFeat = Finturnfeat_1;
	}

	/**
	 * 
	 */

	public java.lang.Integer getSrcAnl1() {
		return this.SrcAnl1;
	}

	public void setSrcAnl1(java.lang.Integer SrcAnl1) {
		this.SrcAnl1 = SrcAnl1;
	}

	/**
	 * 
	 */

	public java.lang.Integer getSrcAnl2() {
		return this.SrcAnl2;
	}

	public void setSrcAnl2(java.lang.Integer SrcAnl2) {
		this.SrcAnl2 = SrcAnl2;
	}

	/**
	 * 
	 */

	public java.lang.Integer getSrcAnl3() {
		return this.SrcAnl3;
	}

	public void setSrcAnl3(java.lang.Integer SrcAnl3) {
		this.SrcAnl3 = SrcAnl3;
	}

	/**
	 * 
	 */

	public java.lang.Integer getSrcAnl4() {
		return this.SrcAnl4;
	}

	public void setSrcAnl4(java.lang.Integer SrcAnl4) {
		this.SrcAnl4 = SrcAnl4;
	}

	/**
	 * 
	 */

	public java.lang.Integer getSrcAnl5() {
		return this.SrcAnl5;
	}

	public void setSrcAnl5(java.lang.Integer SrcAnl5) {
		this.SrcAnl5 = SrcAnl5;
	}

	/**
	 * 
	 */

	public java.lang.Integer getDstAnl1() {
		return this.DstAnl1;
	}

	public void setDstAnl1(java.lang.Integer DstAnl1) {
		this.DstAnl1 = DstAnl1;
	}

	/**
	 * 
	 */

	public java.lang.Integer getDstAnl2() {
		return this.DstAnl2;
	}

	public void setDstAnl2(java.lang.Integer DstAnl2) {
		this.DstAnl2 = DstAnl2;
	}

	/**
	 * 
	 */

	public java.lang.Integer getDstAnl3() {
		return this.DstAnl3;
	}

	public void setDstAnl3(java.lang.Integer DstAnl3) {
		this.DstAnl3 = DstAnl3;
	}

	/**
	 * 
	 */

	public java.lang.Integer getDstAnl4() {
		return this.DstAnl4;
	}

	public void setDstAnl4(java.lang.Integer DstAnl4) {
		this.DstAnl4 = DstAnl4;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Oper.DstAnl5")
	public java.lang.Integer getDstAnl5() {
		return this.DstAnl5;
	}

	public void setDstAnl5(java.lang.Integer DstAnl5) {
		this.DstAnl5 = DstAnl5;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Oper.SumNat")
	public java.math.BigDecimal getSumNat() {
		return this.SumNat;
	}

	public void setSumNat(java.math.BigDecimal Sumnat) {
		this.SumNat = Sumnat;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Oper.SumCur")
	public java.math.BigDecimal getSumCur() {
		return this.SumCur;
	}

	public void setSumCur(java.math.BigDecimal Sumcur) {
		this.SumCur = Sumcur;
	}

	/**
	 * 
	 */

	public boolean getPlanned() {
		return this.Planned;
	}

	public void setPlanned(boolean Planned) {
		this.Planned = Planned;
	}
}