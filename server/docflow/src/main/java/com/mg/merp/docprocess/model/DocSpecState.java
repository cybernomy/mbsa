/*
 * DocSpecState.java
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
package com.mg.merp.docprocess.model;

import java.io.Serializable;

/**
 * @author hbm2java
 * @version $Id: DocSpecState.java,v 1.3 2006/03/01 16:03:02 safonov Exp $
 */
public class DocSpecState extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.docprocess.model.DocHeadState docHeadState;

	private com.mg.merp.document.model.DocSpec docSpec;

	private java.math.BigDecimal readyQuantity1;

	private java.math.BigDecimal readySum;

	private java.lang.Integer Data1;

	private java.math.BigDecimal readyQuantity2;

	private java.lang.Integer Data2;

	private Serializable stateValue;

	// Constructors

	/** default constructor */
	public DocSpecState() {
	}

	/** constructor with id */
	public DocSpecState(java.lang.Integer Id) {
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

	public com.mg.merp.docprocess.model.DocHeadState getDocHeadState() {
		return this.docHeadState;
	}

	public void setDocHeadState(
			com.mg.merp.docprocess.model.DocHeadState Docheadstate) {
		this.docHeadState = Docheadstate;
	}

	/**
	 * 
	 */

	public com.mg.merp.document.model.DocSpec getDocSpec() {
		return this.docSpec;
	}

	public void setDocSpec(com.mg.merp.document.model.DocSpec docSpec) {
		this.docSpec = docSpec;
	}

	/**
	 * 
	 */

	public java.math.BigDecimal getReadyQuantity1() {
		return this.readyQuantity1;
	}

	public void setReadyQuantity1(java.math.BigDecimal Readyquantity) {
		this.readyQuantity1 = Readyquantity;
	}

	/**
	 * 
	 */

	public java.math.BigDecimal getReadySum() {
		return this.readySum;
	}

	public void setReadySum(java.math.BigDecimal Readysumma) {
		this.readySum = Readysumma;
	}

	/**
	 * 
	 */

	public java.lang.Integer getData1() {
		return this.Data1;
	}

	public void setData1(java.lang.Integer Data1) {
		this.Data1 = Data1;
	}

	/**
	 * 
	 */

	public java.math.BigDecimal getReadyQuantity2() {
		return this.readyQuantity2;
	}

	public void setReadyQuantity2(java.math.BigDecimal Readyquantity2) {
		this.readyQuantity2 = Readyquantity2;
	}

	/**
	 * 
	 */

	public java.lang.Integer getData2() {
		return this.Data2;
	}

	public void setData2(java.lang.Integer Data2) {
		this.Data2 = Data2;
	}

	/**
	 * @return Returns the stateValue.
	 */
	public Serializable getStateValue() {
		return stateValue;
	}

	/**
	 * @param stateValue
	 *            The stateValue to set.
	 */
	public void setStateValue(Serializable stateValue) {
		this.stateValue = stateValue;
	}

}