/*
 * FeeModelParam.java
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
package com.mg.merp.salary.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Параметр образца начислений/удержаний"
 * 
 * @author Artem V. Sharapov
 * @version $Id: FeeModelParam.java,v 1.4 2007/07/09 08:21:56 sharapov Exp $
 */
public class FeeModelParam extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields    

	private java.lang.Integer Id;
	private com.mg.merp.salary.model.FeeModel FeeModel;
	private com.mg.merp.salary.model.FeeRefParam FeeRefParam;
	private java.lang.String ParamValue;
	private com.mg.merp.core.model.SysClient SysClient;


	// Constructors

	/** default constructor */
	public FeeModelParam() {
	}

	/** constructor with id */
	public FeeModelParam(java.lang.Integer Id) {
		this.Id = Id;
	}

	
	// Property accessors
	/**

	 */
	@DataItemName("ID")
	public java.lang.Integer getId () {
		return this.Id;
	}

	public void setId (java.lang.Integer Id) {
		this.Id = Id;
	}
	
	/**

	 */
	public com.mg.merp.salary.model.FeeModel getFeeModel () {
		return this.FeeModel;
	}

	public void setFeeModel (com.mg.merp.salary.model.FeeModel SalFeeModel) {
		this.FeeModel = SalFeeModel;
	}
	
	/**

	 */
	public com.mg.merp.core.model.SysClient getSysClient () {
		return this.SysClient;
	}

	public void setSysClient (com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}
	
	/**

	 */
	//@DataItemName("Salary.FeeModelParam.FeeRefParam")
	public com.mg.merp.salary.model.FeeRefParam getFeeRefParam () {
		return this.FeeRefParam;
	}

	public void setFeeRefParam (com.mg.merp.salary.model.FeeRefParam SalFeeRefParam) {
		this.FeeRefParam = SalFeeRefParam;
	}
	
	/**

	 */
	@DataItemName("Salary.FeeModelParam.ParamValue")    
	public java.lang.String getParamValue () {
		return this.ParamValue;
	}

	public void setParamValue (java.lang.String ParamValue) {
		this.ParamValue = ParamValue;
	}

}