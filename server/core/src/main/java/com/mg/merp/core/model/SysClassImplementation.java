/*
 * SysClassImplementation.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.core.model;

import java.io.Serializable;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.metadata.ApplicationLayer;
import com.mg.framework.api.metadata.BusinessServiceImplKind;
import com.mg.framework.service.PersistentObjectHibernate;

/**
 * @author Oleg V. Safonov
 * @version $Id: SysClassImplementation.java,v 1.2 2008/03/03 12:56:35 safonov Exp $
 */
@DataItemName("Core.SysClassImplementation")
public class SysClassImplementation extends PersistentObjectHibernate implements
		Serializable {
	private Integer id;
	private com.mg.merp.core.model.SysClient sysClient;
	private ApplicationLayer applicationLayer;
	private SysClass sysClass;
	private BusinessServiceImplKind kind;
	private String name;
	
	public SysClassImplementation() {	
	}
	
	public SysClassImplementation(Integer id) {
		this.id = id;
	}

	/**
	 * @return Returns the applicationLayer.
	 */
	public ApplicationLayer getApplicationLayer() {
		return applicationLayer;
	}

	/**
	 * @param applicationLayer The applicationLayer to set.
	 */
	public void setApplicationLayer(ApplicationLayer applicationLayer) {
		this.applicationLayer = applicationLayer;
	}

	/**
	 * @return Returns the id.
	 */
	@DataItemName("ID")
	public Integer getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Returns the kind.
	 */
	public BusinessServiceImplKind getKind() {
		return kind;
	}

	/**
	 * @param kind The kind to set.
	 */
	public void setKind(BusinessServiceImplKind kind) {
		this.kind = kind;
	}

	/**
	 * @return Returns the name.
	 */
	@DataItemName("Core.SysClassImplementation.Name")
	public String getName() {
		return name;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the sysClass.
	 */
	public SysClass getSysClass() {
		return sysClass;
	}

	/**
	 * @param sysClass The sysClass to set.
	 */
	public void setSysClass(SysClass sysClass) {
		this.sysClass = sysClass;
	}

	/**
	 * @return Returns the sysClient.
	 */
	public com.mg.merp.core.model.SysClient getSysClient() {
		return sysClient;
	}

	/**
	 * @param sysClient The sysClient to set.
	 */
	public void setSysClient(com.mg.merp.core.model.SysClient sysClient) {
		this.sysClient = sysClient;
	}
}
