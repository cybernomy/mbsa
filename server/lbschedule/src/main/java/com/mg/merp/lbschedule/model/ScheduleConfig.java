/*
 * ScheduleConfig.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.lbschedule.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Конфигурация модуля <Графики исполнения обязательств>"
 * 
 * @author hbm2java
 * @author Artem V. Sharapov
 * @version $Id: ScheduleConfig.java,v 1.5 2007/01/13 13:19:33 sharapov Exp $
 */
public class ScheduleConfig extends com.mg.framework.service.PersistentObjectHibernate implements
java.io.Serializable {

	// Fields

	private int sysClientId;
	private com.mg.merp.core.model.Folder Folder;

	// Constructors

	/** default constructor */
	public ScheduleConfig() {
	}

	/** constructor with id */
	public ScheduleConfig(int sysClientId) {
		this.sysClientId = sysClientId;
	}
	
	@DataItemName("Lbschedule.FolderModel.Folder")
	public com.mg.merp.core.model.Folder getFolder() {
		return Folder;
	}

	public void setFolder(com.mg.merp.core.model.Folder folder) {
		Folder = folder;
	}

	/**
	 * @return the sysClientId
	 */
	public int getSysClientId() {
		return sysClientId;
	}

	/**
	 * @param sysClientId the sysClientId to set
	 */
	public void setSysClientId(int sysClientId) {
		this.sysClientId = sysClientId;
	}

}