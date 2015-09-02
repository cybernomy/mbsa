/*
 * DocAction.java
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

/**
 * @author hbm2java
 * @version $Id: DocAction.java,v 1.3 2006/03/01 16:03:02 safonov Exp $
 */
public class DocAction extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.document.model.DocHead docHead;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.docprocess.model.ActionType actionType;

	private DocProcessStage stage;

	private StageState stageState;

	private java.util.Set<DocHeadState> docHeadStates;

	// Constructors

	/** default constructor */
	public DocAction() {
	}

	/** constructor with id */
	public DocAction(java.lang.Integer Id) {
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

	public com.mg.merp.document.model.DocHead getDocHead() {
		return this.docHead;
	}

	public void setDocHead(com.mg.merp.document.model.DocHead Dochead) {
		this.docHead = Dochead;
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

	public com.mg.merp.docprocess.model.ActionType getActionType() {
		return this.actionType;
	}

	public void setActionType(com.mg.merp.docprocess.model.ActionType Actiontype) {
		this.actionType = Actiontype;
	}

	/**
	 * 
	 */

	public DocProcessStage getStage() {
		return this.stage;
	}

	public void setStage(DocProcessStage stage) {
		this.stage = stage;
	}

	/**
	 * 
	 */

	public StageState getStageState() {
		return this.stageState;
	}

	public void setStageState(StageState Stagestate) {
		this.stageState = Stagestate;
	}

	/**
	 * @return Returns the docHeadStates.
	 */
	public java.util.Set<DocHeadState> getDocHeadStates() {
		return docHeadStates;
	}

	/**
	 * @param docHeadStates
	 *            The docHeadStates to set.
	 */
	public void setDocHeadStates(java.util.Set<DocHeadState> setOfDocHeadState) {
		this.docHeadStates = setOfDocHeadState;
	}
}