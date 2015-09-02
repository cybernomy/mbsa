/*
 * NormSpecDocSpecLink.java
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
 * Модель бизнес-компонента "Связь спецификации с нормой выдачи"
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: NormSpecDocSpecLink.java,v 1.3 2008/06/30 04:15:16 alikaev Exp $
 */
public class NormSpecDocSpecLink extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields    

	private int Id;
	private com.mg.merp.overall.model.NormSpec OvrNormSpec;
	private com.mg.merp.document.model.DocSpec DocSpec;
	private com.mg.merp.core.model.SysClient SysClient;

	// Constructors

	/** default constructor */
	public NormSpecDocSpecLink() {
	}

	/** constructor with id */
	public NormSpecDocSpecLink(int Id) {
		this.Id = Id;
	}

	// Property accessors

	public int getId () {
		return this.Id;
	}

	public void setId (int Id) {
		this.Id = Id;
	}

	public com.mg.merp.overall.model.NormSpec getOvrNormSpec () {
		return this.OvrNormSpec;
	}

	public void setOvrNormSpec (com.mg.merp.overall.model.NormSpec OvrNormSpec) {
		this.OvrNormSpec = OvrNormSpec;
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