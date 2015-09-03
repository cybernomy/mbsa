/*
 * OvrCardDocumentLink.java
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
 * Модель бизнес-компонента "Документы, отработанные в лицевых карточках сотрудников"
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: OvrCardDocumentLink.java,v 1.3 2008/06/30 04:15:16 alikaev Exp $
 */
public class OvrCardDocumentLink extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields    

	private int Id;
	private com.mg.merp.overall.model.NormHead OvrNormHead;
	private com.mg.merp.overall.model.OvrCard OvrCard;
	private com.mg.merp.document.model.DocHead DocHead;
	private com.mg.merp.core.model.SysClient SysClient;
	private java.lang.Integer RemoveTypeId;

	// Constructors

	/** default constructor */
	public OvrCardDocumentLink() {
	}

	/** constructor with id */
	public OvrCardDocumentLink(int Id) {
		this.Id = Id;
	}

	// Property accessors

	public int getId () {
		return this.Id;
	}

	public void setId (int Id) {
		this.Id = Id;
	}

	public com.mg.merp.overall.model.NormHead getOvrNormHead () {
		return this.OvrNormHead;
	}

	public void setOvrNormHead (com.mg.merp.overall.model.NormHead OvrNormHead) {
		this.OvrNormHead = OvrNormHead;
	}

	public com.mg.merp.overall.model.OvrCard getOvrCard () {
		return this.OvrCard;
	}

	public void setOvrCard (com.mg.merp.overall.model.OvrCard OvrCard) {
		this.OvrCard = OvrCard;
	}

	public com.mg.merp.document.model.DocHead getDocHead () {
		return this.DocHead;
	}

	public void setDocHead (com.mg.merp.document.model.DocHead Dochead) {
		this.DocHead = Dochead;
	}

	public com.mg.merp.core.model.SysClient getSysClient () {
		return this.SysClient;
	}

	public void setSysClient (com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	public java.lang.Integer getRemoveTypeId () {
		return this.RemoveTypeId;
	}

	public void setRemoveTypeId (java.lang.Integer RemoveTypeId) {
		this.RemoveTypeId = RemoveTypeId;
	}

}