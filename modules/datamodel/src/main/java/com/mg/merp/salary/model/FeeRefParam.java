/*
 * FeeRefParam.java
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
package com.mg.merp.salary.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: FeeRefParam.java,v 1.8 2006/10/23 08:37:16 leonova Exp $
 */
@DataItemName("Salary.FeeRefParam")
public class FeeRefParam extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.salary.model.FeeRef FeeRef;

	private com.mg.merp.baiengine.model.Repository CalcAlg;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String PCode;

	private java.lang.String PName;

	private java.lang.Integer Priority;

	private FeeParamType ParamType;

	private boolean CalcOnce;

	// Constructors

	/** default constructor */
	public FeeRefParam() {
	}

	/** constructor with id */
	public FeeRefParam(java.lang.Integer Id) {
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

	public com.mg.merp.salary.model.FeeRef getFeeRef() {
		return this.FeeRef;
	}

	public void setFeeRef(com.mg.merp.salary.model.FeeRef SalFeeRef) {
		this.FeeRef = SalFeeRef;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.FeeRefParam.CalcAlg")
	public com.mg.merp.baiengine.model.Repository getCalcAlg() {
		return this.CalcAlg;
	}

	public void setCalcAlg(
			com.mg.merp.baiengine.model.Repository AlgRepository) {
		this.CalcAlg = AlgRepository;
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
	@DataItemName("Salary.BigCode")
	public java.lang.String getPCode() {
		return this.PCode;
	}

	public void setPCode(java.lang.String Pcode) {
		this.PCode = Pcode;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.Name")
	public java.lang.String getPName() {
		return this.PName;
	}

	public void setPName(java.lang.String Pname) {
		this.PName = Pname;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.FeeRefParam.Priority")
	public java.lang.Integer getPriority() {
		return this.Priority;
	}

	public void setPriority(java.lang.Integer Priority) {
		this.Priority = Priority;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.FeeRefParam.ParamType")
	public FeeParamType getParamType() {
		return this.ParamType;
	}

	public void setParamType(FeeParamType ParamType) {
		this.ParamType = ParamType;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.FeeRefParam.CalcOnce")
	public boolean getCalcOnce() {
		return this.CalcOnce;
	}

	public void setCalcOnce(boolean CalcOnce) {
		this.CalcOnce = CalcOnce;
	}

}