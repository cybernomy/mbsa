/*
 * JobStatus.java
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
package com.mg.merp.manufacture.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * Статус ЗНП
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: JobStatus.java,v 1.2 2007/07/30 10:27:49 safonov Exp $
 */
@DataItemName("Manufacture.JobStatus")
public enum JobStatus {
	/**
	 * Новый
	 */
	@EnumConstantText("resource://com.mg.merp.manufacture.resources.dataitemlabels#JobStatus.Created")
	CREATED,
	
	/**
	 * Запущен
	 */
	@EnumConstantText("resource://com.mg.merp.manufacture.resources.dataitemlabels#JobStatus.Running")
	RUNNING,
	
	/**
	 * Остановлен
	 */
	@EnumConstantText("resource://com.mg.merp.manufacture.resources.dataitemlabels#JobStatus.Stopped")
	STOPPED,
	
	/**
	 * Завершён
	 */
	@EnumConstantText("resource://com.mg.merp.manufacture.resources.dataitemlabels#JobStatus.Completed")
	COMPLETED
}
