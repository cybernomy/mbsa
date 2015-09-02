/*
 * DocGroup.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * MMillennium Business Suite Anywhere System.
 *
 */
package com.mg.merp.paymentalloc.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Группы документов"
 * 
 * @author Artem V. Sharapov 
 * @version $Id: DocGroup.java,v 1.5 2007/07/02 06:31:00 sharapov Exp $
 */
@DataItemName("Paymentalloc.DocGroup") //$NON-NLS-1$
public class DocGroup extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String Name;

	private java.util.Set SetOfAccConfig;

	private java.util.Set SetOfAccConfig_1;

	private java.util.Set SetOfAccConfig_2;

	private java.util.Set SetOfAccConfig_3;

	private java.util.Set SetOfAccConfig_4;

	private java.util.Set<DocGroupLink> DocGroupLinks;

	// Constructors

	/** default constructor */
	public DocGroup() {
	}

	/** constructor with id */
	public DocGroup(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("ID") //$NON-NLS-1$
	public java.lang.Integer getId() {
		return this.Id;
	}

	public void setId(java.lang.Integer Id) {
		this.Id = Id;
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
	@DataItemName("PaymentAlloc.Payment.Name") //$NON-NLS-1$
	public java.lang.String getName() {
		return this.Name;
	}

	public void setName(java.lang.String Name) {
		this.Name = Name;
	}

	/**
	 * 
	 */
	public java.util.Set getSetOfAccConfig() {
		return this.SetOfAccConfig;
	}

	public void setSetOfAccConfig(java.util.Set SetOfAccConfig) {
		this.SetOfAccConfig = SetOfAccConfig;
	}

	/**
	 * 
	 */
	public java.util.Set getSetOfAccConfig_1() {
		return this.SetOfAccConfig_1;
	}

	public void setSetOfAccConfig_1(java.util.Set SetOfAccConfig_1) {
		this.SetOfAccConfig_1 = SetOfAccConfig_1;
	}

	/**
	 * 
	 */
	public java.util.Set getSetOfAccConfig_2() {
		return this.SetOfAccConfig_2;
	}

	public void setSetOfAccConfig_2(java.util.Set SetOfAccConfig_2) {
		this.SetOfAccConfig_2 = SetOfAccConfig_2;
	}

	/**
	 * 
	 */
	public java.util.Set getSetOfAccConfig_3() {
		return this.SetOfAccConfig_3;
	}

	public void setSetOfAccConfig_3(java.util.Set SetOfAccConfig_3) {
		this.SetOfAccConfig_3 = SetOfAccConfig_3;
	}

	/**
	 * 
	 */
	public java.util.Set getSetOfAccConfig_4() {
		return this.SetOfAccConfig_4;
	}

	public void setSetOfAccConfig_4(java.util.Set SetOfAccConfig_4) {
		this.SetOfAccConfig_4 = SetOfAccConfig_4;
	}

	/**
	 * @return the docGroupLinks
	 */
	public java.util.Set<DocGroupLink> getDocGroupLinks() {
		return this.DocGroupLinks;
	}

	/**
	 * @param docGroupLinks the docGroupLinks to set
	 */
	public void setDocGroupLinks(java.util.Set<DocGroupLink> docGroupLinks) {
		this.DocGroupLinks = docGroupLinks;
	}
	
}