/**
 * Task.java
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
package com.mg.merp.scheduler.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.service.PersistentObjectHibernate;
import com.mg.merp.baiengine.model.Repository;
import com.mg.merp.core.model.Folder;
import com.mg.merp.core.model.SysClient;

/**
 * Модель "Задача планировщика"
 * 
 * @author Oleg V. Safonov
 * @version $Id: Task.java,v 1.1 2008/04/25 10:57:23 safonov Exp $
 */
@DataItemName("Scheduler.Task")
public class Task extends PersistentObjectHibernate {
	private Integer id;
	private SysClient sysClient;
	private Folder folder;
	private Repository bAi;
	private String code;
	private String name;
	private String description;
	private String cronExpression;
	private boolean isActive;	

	public Task() {
	}
	
	public Task(Integer id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	@DataItemName("ID")
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the sysClient
	 */
	public SysClient getSysClient() {
		return sysClient;
	}

	/**
	 * @param sysClient the sysClient to set
	 */
	public void setSysClient(SysClient sysClient) {
		this.sysClient = sysClient;
	}

	/**
	 * @return the folder
	 */
	public Folder getFolder() {
		return folder;
	}

	/**
	 * @param folder the folder to set
	 */
	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	/**
	 * @return the bai
	 */
	public Repository getBAi() {
		return bAi;
	}

	/**
	 * @param bai the bai to set
	 */
	public void setBAi(Repository bAi) {
		this.bAi = bAi;
	}

	/**
	 * @return the code
	 */
	@DataItemName("Scheduler.Code")
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	@DataItemName("Scheduler.Name")
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	@DataItemName("Scheduler.Description")
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the cronExpression
	 */
	@DataItemName("Scheduler.Task.CronExpression")
	public String getCronExpression() {
		return cronExpression;
	}

	/**
	 * @param cronExpression the cronExpression to set
	 */
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	/**
	 * @return the isActive
	 */
	@DataItemName("Scheduler.Task.IsActive")
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
