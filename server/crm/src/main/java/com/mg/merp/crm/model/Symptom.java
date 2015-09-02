/*
 * Symptom.java
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
package com.mg.merp.crm.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: Symptom.java,v 1.7 2006/11/02 15:45:53 safonov Exp $
 */
public class Symptom extends com.mg.framework.service.PersistentObjectHibernate
		implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.core.model.Folder Folder;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.crm.model.User Creator;

	private java.lang.String Name;

	private java.lang.String Info;
	
	private java.util.Set<LinkSymptomProblem> linkSymptomProblems;

	// Constructors

	/** default constructor */
	public Symptom() {
	}

	/** constructor with id */
	public Symptom(java.lang.Integer Id) {
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

	public com.mg.merp.core.model.Folder getFolder() {
		return this.Folder;
	}

	public void setFolder(com.mg.merp.core.model.Folder Folder) {
		this.Folder = Folder;
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
	@DataItemName("CRM.Symptom.Creator")
	public com.mg.merp.crm.model.User getCreator() {
		return this.Creator;
	}

	public void setCreator(com.mg.merp.crm.model.User CrmUser) {
		this.Creator = CrmUser;
	}

	/**
	 * 
	 */
	@DataItemName("CRM.BigName")
	public java.lang.String getName() {
		return this.Name;
	}

	public void setName(java.lang.String Name) {
		this.Name = Name;
	}

	/**
	 * 
	 */
	@DataItemName("CRM.Symptom.Info")
	public java.lang.String getInfo() {
		return this.Info;
	}

	public void setInfo(java.lang.String Info) {
		this.Info = Info;
	}

	/**
	 * @return Returns the linkSymptomProblems.
	 */
	protected java.util.Set<LinkSymptomProblem> getLinkSymptomProblems() {
		return linkSymptomProblems;
	}

	/**
	 * @param linkSymptomProblems The linkSymptomProblems to set.
	 */
	protected void setLinkSymptomProblems(java.util.Set<LinkSymptomProblem> linkSymptomProblems) {
		this.linkSymptomProblems = linkSymptomProblems;
	}



}