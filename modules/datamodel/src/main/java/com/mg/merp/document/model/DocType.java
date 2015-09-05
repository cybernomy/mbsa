/*
 * DocType.java
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
 * @version $Id: DocType.java,v 1.7 2006/10/20 07:51:55 leonova Exp $
 */
@DataItemName("Document.DocTypeModel")
public class DocType extends com.mg.framework.service.PersistentObjectHibernate
		implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.report.model.RptMain Report;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.baiengine.model.Repository NumberingAlgorithm;

	private java.lang.String UpCode;

	private java.lang.String Code;

	private java.lang.String Name;

	private byte[] DocProcessGraph;

	private boolean SolidDocProcess;

	private java.util.Set<com.mg.merp.document.model.DocTypeRights> SetOfDocTypeRights;

	private java.util.Set<com.mg.merp.document.model.DocTypeDocSectionLink> SetOfDocTypeDocSectionLink;

	// Constructors

	/** default constructor */
	public DocType() {
	}

	/** constructor with id */
	public DocType(java.lang.Integer Id) {
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
	@DataItemName("Document.DocType.Report")
	public com.mg.merp.report.model.RptMain getReport() {
		return this.Report;
	}

	public void setReport(com.mg.merp.report.model.RptMain Rptmain) {
		this.Report = Rptmain;
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
	@DataItemName("Document.DocType.NumberingAlgorithm")
	public com.mg.merp.baiengine.model.Repository getNumberingAlgorithm() {
		return this.NumberingAlgorithm;
	}

	public void setNumberingAlgorithm(
			com.mg.merp.baiengine.model.Repository AlgRepository) {
		this.NumberingAlgorithm = AlgRepository;
	}

	/**
	 * 
	 */

	public java.lang.String getUpCode() {
		return this.UpCode;
	}

	public void setUpCode(java.lang.String Upcode) {
		this.UpCode = Upcode;
	}

	/**
	 * 
	 */
	@DataItemName("Document.DocType.Code")
	public java.lang.String getCode() {
		return this.Code;
	}

	public void setCode(java.lang.String Code) {
		this.Code = Code;
	}

	/**
	 * 
	 */
	@DataItemName("Document.DocType.Name")
	public java.lang.String getName() {
		return this.Name;
	}

	public void setName(java.lang.String name) {
		this.Name = name;
	}

	/**
	 * 
	 */

	public byte[] getDocProcessGraph() {
		return this.DocProcessGraph;
	}

	public void setDocProcessGraph(byte[] DocProcessGraph) {
		this.DocProcessGraph = DocProcessGraph;
	}

	/**
	 * 
	 */
	@DataItemName("Document.DocType.SolidDocProcess")
	public boolean getSolidDocProcess() {
		return this.SolidDocProcess;
	}

	public void setSolidDocProcess(boolean SolidDocProcess) {
		this.SolidDocProcess = SolidDocProcess;
	}

	/**
	 * 
	 */

	public java.util.Set<com.mg.merp.document.model.DocTypeRights> getSetOfDocTypeRights() {
		return this.SetOfDocTypeRights;
	}

	public void setSetOfDocTypeRights(
			java.util.Set<com.mg.merp.document.model.DocTypeRights> SetOfDoctypeRights) {
		this.SetOfDocTypeRights = SetOfDoctypeRights;
	}

	/**
	 * 
	 */

	public java.util.Set<com.mg.merp.document.model.DocTypeDocSectionLink> getSetOfDocTypeDocSectionLink() {
		return this.SetOfDocTypeDocSectionLink;
	}

	public void setSetOfDocTypeDocSectionLink(
			java.util.Set<com.mg.merp.document.model.DocTypeDocSectionLink> SetOfDoctypeDocsectionLink) {
		this.SetOfDocTypeDocSectionLink = SetOfDoctypeDocsectionLink;
	}
}