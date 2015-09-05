/*
 * ResourceGroupLink.java
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

import com.mg.framework.service.PersistentObjectHibernate;

/**
 * @author Oleg V. Safonov
 * @version $Id: ResourceGroupLink.java,v 1.1 2007/05/28 13:05:48 safonov Exp $
 */
public class ResourceGroupLink extends PersistentObjectHibernate implements
		Serializable {
	 private int id;
	 private com.mg.merp.security.model.Groups group;
	 private Resource resource;
	 
	 public ResourceGroupLink() {
	 }
	 
	 public ResourceGroupLink(int id) {
		 this.id = id;
	 }

	/**
	 * @return the group
	 */
	public com.mg.merp.security.model.Groups getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(com.mg.merp.security.model.Groups group) {
		this.group = group;
	}

	/**
	 * @return the id
	 */
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
	 * @return the resource
	 */
	public Resource getResource() {
		return resource;
	}

	/**
	 * @param resource the resource to set
	 */
	public void setResource(Resource resource) {
		this.resource = resource;
	}

}
