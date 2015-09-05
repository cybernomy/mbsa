/*
 * CostCategories.java
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
package com.mg.merp.mfreference.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: CostCategories.java,v 1.5 2006/10/25 12:01:19 leonova Exp $
 */
@DataItemName("MfReference.CostCategories")
public class CostCategories extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.baiengine.model.Repository AlgRepository;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String Code;

	private java.lang.String Description;

	private java.lang.Integer ViewOrder;

	private java.lang.Integer CalculationSequence;

	// Constructors

	/** default constructor */
	public CostCategories() {
	}

	/** constructor with id */
	public CostCategories(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */

	public java.lang.Integer getId() {
		return this.Id;
	}

	public void setId(java.lang.Integer Id) {
		this.Id = Id;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.CostCateg.AlgRepos")
	public com.mg.merp.baiengine.model.Repository getAlgRepository() {
		return this.AlgRepository;
	}

	public void setAlgRepository(
			com.mg.merp.baiengine.model.Repository AlgRepository) {
		this.AlgRepository = AlgRepository;
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

	@DataItemName("MfReference.CostCateg.Code")
	public java.lang.String getCode() {
		return this.Code;
	}

	public void setCode(java.lang.String Code) {
		this.Code = Code;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.CostCateg.Descr")
	public java.lang.String getDescription() {
		return this.Description;
	}

	public void setDescription(java.lang.String Description) {
		this.Description = Description;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.CostCateg.ViewOrder")
	public java.lang.Integer getViewOrder() {
		return this.ViewOrder;
	}

	public void setViewOrder(java.lang.Integer ViewOrder) {
		this.ViewOrder = ViewOrder;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.CostCateg.CalcSeq")
	public java.lang.Integer getCalculationSequence() {
		return this.CalculationSequence;
	}

	public void setCalculationSequence(java.lang.Integer CalculationSequence) {
		this.CalculationSequence = CalculationSequence;
	}
}