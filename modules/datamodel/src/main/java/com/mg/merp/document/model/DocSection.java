/*
 * DocSection.java
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
 * @version $Id: DocSection.java,v 1.6 2006/09/15 12:56:11 leonova Exp $
 */
@DataItemName("Document.DocSection")
public class DocSection extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private int Id;

	private com.mg.merp.core.model.SysClass ModelSysClass;

	private com.mg.merp.core.model.SysClass ModelSpecSysClass;

	private com.mg.merp.core.model.SysClass SysClass;

	private com.mg.merp.core.model.SysClass SpecSysClass;

	private java.lang.String DSName;

	private int FolderType;

	private java.lang.Integer ModelFolderType;

	private boolean withTaxes;

	private boolean withSpec;

	private short Direction;

	private boolean dummy;

	private String documentSearchHelp;
	
	private String documentModelSearchHelp;
	
	// Constructors

	/** default constructor */
	public DocSection() {
	}

	/** constructor with id */
	public DocSection(int Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */

	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	/**
	 * 
	 */

	public com.mg.merp.core.model.SysClass getModelSysClass() {
		return this.ModelSysClass;
	}

	public void setModelSysClass(com.mg.merp.core.model.SysClass SysClass) {
		this.ModelSysClass = SysClass;
	}

	/**
	 * 
	 */

	public com.mg.merp.core.model.SysClass getModelSpecSysClass() {
		return this.ModelSpecSysClass;
	}

	public void setModelSpecSysClass(com.mg.merp.core.model.SysClass SysClass_1) {
		this.ModelSpecSysClass = SysClass_1;
	}

	/**
	 * 
	 */

	public com.mg.merp.core.model.SysClass getSysClass() {
		return this.SysClass;
	}

	public void setSysClass(com.mg.merp.core.model.SysClass SysClass_2) {
		this.SysClass = SysClass_2;
	}

	/**
	 * 
	 */

	public com.mg.merp.core.model.SysClass getSpecSysClass() {
		return this.SpecSysClass;
	}

	public void setSpecSysClass(com.mg.merp.core.model.SysClass SysClass_3) {
		this.SpecSysClass = SysClass_3;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Name")
	public java.lang.String getDSName() {
		return this.DSName;
	}

	public void setDSName(java.lang.String Dsname) {
		this.DSName = Dsname;
	}

	/**
	 * 
	 */

	public int getFolderType() {
		return this.FolderType;
	}

	public void setFolderType(int Foldertype) {
		this.FolderType = Foldertype;
	}

	/**
	 * 
	 */

	public java.lang.Integer getModelFolderType() {
		return this.ModelFolderType;
	}

	public void setModelFolderType(java.lang.Integer ModelFoldertype) {
		this.ModelFolderType = ModelFoldertype;
	}

	/**
	 * 
	 */

	public boolean isWithTaxes() {
		return this.withTaxes;
	}

	public void setWithTaxes(boolean WithTaxes) {
		this.withTaxes = WithTaxes;
	}

	/**
	 * 
	 */

	public boolean isWithSpec() {
		return this.withSpec;
	}

	public void setWithSpec(boolean WithSpec) {
		this.withSpec = WithSpec;
	}

	/**
	 * 
	 */

	public short getDirection() {
		return this.Direction;
	}

	public void setDirection(short Direction) {
		this.Direction = Direction;
	}

	/**
	 * 
	 */

	public boolean isDummy() {
		return this.dummy;
	}

	public void setDummy(boolean Dummy) {
		this.dummy = Dummy;
	}

	/**
	 * @return Returns the documentModelSearchHelp.
	 */
	public String getDocumentModelSearchHelp() {
		return documentModelSearchHelp;
	}

	/**
	 * @param documentModelSearchHelp The documentModelSearchHelp to set.
	 */
	public void setDocumentModelSearchHelp(String documentModelSearchHelp) {
		this.documentModelSearchHelp = documentModelSearchHelp;
	}

	/**
	 * @return Returns the documentSearchHelp.
	 */
	public String getDocumentSearchHelp() {
		return documentSearchHelp;
	}

	/**
	 * @param documentSearchHelp The documentSearchHelp to set.
	 */
	public void setDocumentSearchHelp(String documentSearchHelp) {
		this.documentSearchHelp = documentSearchHelp;
	}
}