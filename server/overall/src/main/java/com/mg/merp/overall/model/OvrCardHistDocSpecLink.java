/*
 * OvrCardHistDocSpecLink.java
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
 * Модель бизнес-компонента "Cвязь спецификаций документов и лицевых карточек сотрудников"
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: OvrCardHistDocSpecLink.java,v 1.3 2008/06/30 04:15:16 alikaev Exp $
 */
public class OvrCardHistDocSpecLink extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields    

	private java.lang.Integer Id;
	private com.mg.merp.overall.model.OvrCardHist OvrCardHist;
	private com.mg.merp.document.model.DocSpec DocSpec;
	private com.mg.merp.core.model.SysClient SysClient;


	// Constructors

	/** default constructor */
	public OvrCardHistDocSpecLink() {
	}

	/** constructor with id */
	public OvrCardHistDocSpecLink(java.lang.Integer Id) {
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

	public com.mg.merp.document.model.DocSpec getDocSpec () {
		return this.DocSpec;
	}

	public void setDocSpec (com.mg.merp.document.model.DocSpec Docspec) {
		this.DocSpec = Docspec;
	}

	public com.mg.merp.core.model.SysClient getSysClient () {
		return this.SysClient;
	}

	public void setSysClient (com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

}