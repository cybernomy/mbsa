/*
 * DocumentSpecPackage.java
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
package com.mg.merp.document.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: DocumentSpecPackage.java,v 1.3 2007/08/10 13:21:31 safonov Exp $
 */
public class DocumentSpecPackage extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private int Id;

	private com.mg.merp.reference.model.Packing Packing;

	private com.mg.merp.document.model.DocSpec docSpec;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.math.BigDecimal Quantity;

	private java.math.BigDecimal Weight;

	private java.math.BigDecimal Volume;

	// Constructors

	/** default constructor */
	public DocumentSpecPackage() {
	}

	/** constructor with id */
	public DocumentSpecPackage(int Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("ID")
	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	/**
	 * 
	 */

	public com.mg.merp.reference.model.Packing getPacking() {
		return this.Packing;
	}

	public void setPacking(com.mg.merp.reference.model.Packing Packing) {
		this.Packing = Packing;
	}

	/**
	 * 
	 */

	public com.mg.merp.document.model.DocSpec getDocSpec() {
		return this.docSpec;
	}

	public void setDocSpec(com.mg.merp.document.model.DocSpec Docspec) {
		this.docSpec = Docspec;
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
	@DataItemName("Reference.DocSpec.Packing.Quantity")
	public java.math.BigDecimal getQuantity() {
		return this.Quantity;
	}

	public void setQuantity(java.math.BigDecimal Quantity) {
		this.Quantity = Quantity;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Packing.Weight")
	public java.math.BigDecimal getWeight() {
		return this.Weight;
	}

	public void setWeight(java.math.BigDecimal Weight) {
		this.Weight = Weight;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Packing.Volume")
	public java.math.BigDecimal getVolume() {
		return this.Volume;
	}

	public void setVolume(java.math.BigDecimal Volume) {
		this.Volume = Volume;
	}

}