/*
 * NormSpecLink.java
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

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Связь спецификации нормы выдачи и КТУ"
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: NormSpecLink.java,v 1.3 2008/06/30 04:15:16 alikaev Exp $
 */
public class NormSpecLink extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields    

	private java.lang.Integer Id;
	private com.mg.merp.reference.model.Catalog Catalog;
	private com.mg.merp.overall.model.NormSpec OvrNormSpec;
	private com.mg.merp.core.model.SysClient SysClient;


	// Constructors

	/** default constructor */
	public NormSpecLink() {
	}

	/** constructor with id */
	public NormSpecLink(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors
	
	@DataItemName("ID")
	public java.lang.Integer getId () {
		return this.Id;
	}

	public void setId (java.lang.Integer Id) {
		this.Id = Id;
	}

	public com.mg.merp.reference.model.Catalog getCatalog () {
		return this.Catalog;
	}

	public void setCatalog (com.mg.merp.reference.model.Catalog Catalog) {
		this.Catalog = Catalog;
	}

	public com.mg.merp.overall.model.NormSpec getOvrNormSpec () {
		return this.OvrNormSpec;
	}

	public void setOvrNormSpec (com.mg.merp.overall.model.NormSpec OvrNormSpec) {
		this.OvrNormSpec = OvrNormSpec;
	}

	public com.mg.merp.core.model.SysClient getSysClient () {
		return this.SysClient;
	}

	public void setSysClient (com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

}