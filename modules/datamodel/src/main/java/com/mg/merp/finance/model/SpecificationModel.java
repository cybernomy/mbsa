/*
 * SpecificationModel.java
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
 * @version $Id: SpecificationModel.java,v 1.4 2005/08/02 12:48:23 pashistova
 *          Exp $
 */
public class SpecificationModel extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.baiengine.model.Repository Alg;

	private com.mg.merp.finance.model.SpecificationModel Parent;

	private com.mg.merp.finance.model.OperationModel FinOper;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.finance.model.Account DstAcc;

	private com.mg.merp.finance.model.Account SrcAcc;

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

	private java.lang.String Formula;

	private boolean Planned;

	// Constructors

	/** default constructor */
	public SpecificationModel() {
	}

	/** constructor with id */
	public SpecificationModel(java.lang.Integer Id) {
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
	@DataItemName("Finance.OperM.Alg")
	public com.mg.merp.baiengine.model.Repository getAlg() {
		return this.Alg;
	}

	public void setAlg(
			com.mg.merp.baiengine.model.Repository AlgRepository) {
		this.Alg = AlgRepository;
	}

	/**
	 * 
	 */

	public com.mg.merp.finance.model.SpecificationModel getParent() {
		return this.Parent;
	}

	public void setParent(
			com.mg.merp.finance.model.SpecificationModel Finspecmodel) {
		this.Parent = Finspecmodel;
	}

	/**
	 * 
	 */

	public com.mg.merp.finance.model.OperationModel getFinOper() {
		return this.FinOper;
	}

	public void setFinOper(com.mg.merp.finance.model.OperationModel Finopermodel) {
		this.FinOper = Finopermodel;
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
	@DataItemName("Finance.Oper.SrcAnl1")
	public java.lang.Integer getSrcAnl1() {
		return this.SrcAnl1;
	}

	public void setSrcAnl1(java.lang.Integer SrcAnl1) {
		this.SrcAnl1 = SrcAnl1;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Oper.SrcAnl2")
	public java.lang.Integer getSrcAnl2() {
		return this.SrcAnl2;
	}

	public void setSrcAnl2(java.lang.Integer SrcAnl2) {
		this.SrcAnl2 = SrcAnl2;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Oper.SrcAnl3")
	public java.lang.Integer getSrcAnl3() {
		return this.SrcAnl3;
	}

	public void setSrcAnl3(java.lang.Integer SrcAnl3) {
		this.SrcAnl3 = SrcAnl3;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Oper.SrcAnl4")
	public java.lang.Integer getSrcAnl4() {
		return this.SrcAnl4;
	}

	public void setSrcAnl4(java.lang.Integer SrcAnl4) {
		this.SrcAnl4 = SrcAnl4;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Oper.SrcAnl5")
	public java.lang.Integer getSrcAnl5() {
		return this.SrcAnl5;
	}

	public void setSrcAnl5(java.lang.Integer SrcAnl5) {
		this.SrcAnl5 = SrcAnl5;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Oper.DstAnl1")
	public java.lang.Integer getDstAnl1() {
		return this.DstAnl1;
	}

	public void setDstAnl1(java.lang.Integer DstAnl1) {
		this.DstAnl1 = DstAnl1;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Oper.DstAnl2")
	public java.lang.Integer getDstAnl2() {
		return this.DstAnl2;
	}

	public void setDstAnl2(java.lang.Integer DstAnl2) {
		this.DstAnl2 = DstAnl2;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Oper.DstAnl3")
	public java.lang.Integer getDstAnl3() {
		return this.DstAnl3;
	}

	public void setDstAnl3(java.lang.Integer DstAnl3) {
		this.DstAnl3 = DstAnl3;
	}

	/**
	 * 
	 */
	@DataItemName("Finance.Oper.DstAnl4")
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
	@DataItemName("Finance.OperM.Formula")
	public java.lang.String getFormula() {
		return this.Formula;
	}

	public void setFormula(java.lang.String Formula) {
		this.Formula = Formula;
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