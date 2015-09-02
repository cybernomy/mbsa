/*
 * HistStatus.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.overall.model;

/**
 * Модель бизнес-компонента ""
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: HistStatus.java,v 1.3 2008/06/30 04:15:16 alikaev Exp $
 */
public class HistStatus extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields    

	private java.lang.Integer Id;
	private com.mg.merp.overall.model.OvrCardHist OvrCardHist;
	private com.mg.merp.core.model.SysClient SysClient;
	private java.lang.Integer DocHeadId;
	private java.lang.Integer DocSpecId;
	private java.lang.String DocType;
	private java.lang.String DocNumber;
	private java.util.Date DocDate;
	private java.lang.Integer OvrHistStatusId;
	private java.util.Date OvrHistStatusDate;

	// Constructors

	/** default constructor */
	public HistStatus() {
	}

	/** constructor with id */
	public HistStatus(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors
	
	public java.lang.Integer getId () {
		return this.Id;
	}

	public void setId (java.lang.Integer Id) {
		this.Id = Id;
	}

	public com.mg.merp.overall.model.OvrCardHist getOvrCardHist () {
		return this.OvrCardHist;
	}

	public void setOvrCardHist (com.mg.merp.overall.model.OvrCardHist OvrCardHist) {
		this.OvrCardHist = OvrCardHist;
	}

	public com.mg.merp.core.model.SysClient getSysClient () {
		return this.SysClient;
	}

	public void setSysClient (com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	public java.lang.Integer getDocHeadId () {
		return this.DocHeadId;
	}

	public void setDocHeadId (java.lang.Integer DocheadId) {
		this.DocHeadId = DocheadId;
	}

	public java.lang.Integer getDocSpecId () {
		return this.DocSpecId;
	}

	public void setDocSpecId (java.lang.Integer DocspecId) {
		this.DocSpecId = DocspecId;
	}

	public java.lang.String getDocType () {
		return this.DocType;
	}

	public void setDocType (java.lang.String Doctype) {
		this.DocType = Doctype;
	}

	public java.lang.String getDocNumber () {
		return this.DocNumber;
	}

	public void setDocNumber (java.lang.String Docnumber) {
		this.DocNumber = Docnumber;
	}

	public java.util.Date getDocDate () {
		return this.DocDate;
	}

	public void setDocDate (java.util.Date Docdate) {
		this.DocDate = Docdate;
	}

	public java.lang.Integer getOvrHistStatusId () {
		return this.OvrHistStatusId;
	}

	public void setOvrHistStatusId (java.lang.Integer OvrHistStatusId) {
		this.OvrHistStatusId = OvrHistStatusId;
	}

	public java.util.Date getOvrHistStatusDate () {
		return this.OvrHistStatusDate;
	}

	public void setOvrHistStatusDate (java.util.Date OvrHistStatusDate) {
		this.OvrHistStatusDate = OvrHistStatusDate;
	}

}