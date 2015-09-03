/*
 * StageAction.java
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
package com.mg.merp.docprocess.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Виды этапов документооборота
 * 
 * @author Oleg V. Safonov
 * @version $Id: StageAction.java,v 1.6 2007/03/14 08:15:54 safonov Exp $
 */
@DataItemName("DocFlow.StageAction")
public class StageAction extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private int Id;

	private java.lang.String Name;

	private boolean createDoc;

	// Constructors

	/** default constructor */
	public StageAction() {
	}

	/** constructor with id */
	public StageAction(int Id) {
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
	@DataItemName("DocFlow.StageAction.Name")
	public java.lang.String getName() {
		return this.Name;
	}

	public void setName(java.lang.String Name) {
		this.Name = Name;
	}

	/**
	 * 
	 */
	@DataItemName("DocFlow.StageAction.CreateDoc")
	public boolean isCreateDoc() {
		return this.createDoc;
	}

	public void setCreateDoc(boolean CreatedocFlag) {
		this.createDoc = CreatedocFlag;
	}
}