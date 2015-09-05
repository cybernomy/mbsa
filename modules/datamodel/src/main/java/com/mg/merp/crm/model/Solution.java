/*
 * Solution.java
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
 * @version $Id: Solution.java,v 1.6 2006/11/02 15:45:53 safonov Exp $
 */
public class Solution extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.crm.model.SolutionType SolutionType;

	private com.mg.merp.core.model.Folder Folder;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.crm.model.User Creator;

	private java.lang.String Name;

	private java.lang.String Info;

	private java.lang.Integer Priority;

	private java.util.Date ValidFrom;

	private java.util.Date ValidTo;

	private java.util.Set<LinkProblemSolution> LinkProblemSolutions;

	private java.util.Set<LinkedDocument> LinkedDocs;

	// Constructors

	/** default constructor */
	public Solution() {
	}

	/** constructor with id */
	public Solution(java.lang.Integer Id) {
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
	public com.mg.merp.crm.model.SolutionType getSolutionType() {
		return this.SolutionType;
	}

	public void setSolutionType(
			com.mg.merp.crm.model.SolutionType CrmSolutionType) {
		this.SolutionType = CrmSolutionType;
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
	@DataItemName("CRM.Solution.Creator")
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
	@DataItemName("CRM.Solution.Info")
	public java.lang.String getInfo() {
		return this.Info;
	}

	public void setInfo(java.lang.String Info) {
		this.Info = Info;
	}

	/**
	 * 
	 */

	public java.lang.Integer getPriority() {
		return this.Priority;
	}

	public void setPriority(java.lang.Integer Priority) {
		this.Priority = Priority;
	}

	/**
	 * 
	 */
	@DataItemName("CRM.Solution.ValidFrom")
	public java.util.Date getValidFrom() {
		return this.ValidFrom;
	}

	public void setValidFrom(java.util.Date ValidFrom) {
		this.ValidFrom = ValidFrom;
	}

	/**
	 * 
	 */
	@DataItemName("CRM.Solution.ValidTo")
	public java.util.Date getValidTo() {
		return this.ValidTo;
	}

	public void setValidTo(java.util.Date ValidTo) {
		this.ValidTo = ValidTo;
	}

	/**
	 * 
	 */

	public java.util.Set<LinkProblemSolution> getLinkProblemSolutions() {
		return this.LinkProblemSolutions;
	}

	public void setLinkProblemSolutions(
			java.util.Set<LinkProblemSolution> LinkProblemSolutions) {
		this.LinkProblemSolutions = LinkProblemSolutions;
	}

	/**
	 * 
	 */

	public java.util.Set<LinkedDocument> getLinkedDocs() {
		return this.LinkedDocs;
	}

	public void setLinkedDocs(java.util.Set<LinkedDocument> LinkedDocs) {
		this.LinkedDocs = LinkedDocs;
	}

}