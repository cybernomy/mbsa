/*
 * Resource.java
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
package com.mg.merp.bpm.model;

import java.io.Serializable;
import java.util.Set;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.service.PersistentObjectHibernate;
import com.mg.merp.security.model.SecUser;

/**
 * @author Oleg V. Safonov
 * @version $Id: Resource.java,v 1.1 2007/05/28 13:05:48 safonov Exp $
 */
@DataItemName ("BPM.Resource")
public class Resource extends PersistentObjectHibernate implements Serializable {
	private int id;

	private com.mg.merp.core.model.SysClient sysClient;

	private java.lang.String name;

	private java.lang.String key;
	
	private SecUser user;
	
	private Set<ResourceGroupLink> groupLinks;

	public Resource() {
	}
	
	public Resource(int id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	@DataItemName ("ID")
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the key
	 */
	@DataItemName ("BPM.Resource.Key")
	public java.lang.String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(java.lang.String key) {
		this.key = key;
	}

	/**
	 * @return the name
	 */
	@DataItemName ("BPM.Resource.Name")
	public java.lang.String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	/**
	 * @return the sysClient
	 */
	public com.mg.merp.core.model.SysClient getSysClient() {
		return sysClient;
	}

	/**
	 * @param sysClient the sysClient to set
	 */
	public void setSysClient(com.mg.merp.core.model.SysClient sysClient) {
		this.sysClient = sysClient;
	}

	/**
	 * @return the user
	 */
	public SecUser getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(SecUser user) {
		this.user = user;
	}

	/**
	 * @return the groupLinks
	 */
	public Set<ResourceGroupLink> getGroupLinks() {
		return groupLinks;
	}

	/**
	 * @param groupLinks the groupLinks to set
	 */
	public void setGroupLinks(Set<ResourceGroupLink> groupLinks) {
		this.groupLinks = groupLinks;
	}

}
