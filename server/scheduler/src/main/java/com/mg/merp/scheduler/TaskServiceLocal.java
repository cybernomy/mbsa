/**
 * TaskServiceLocal.java
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
package com.mg.merp.scheduler;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.merp.scheduler.model.Task;

/**
 * Бизнес-компонент "Задачи планировщика"
 * 
 * @author Oleg V. Safonov
 * @version $Id: TaskServiceLocal.java,v 1.1 2008/04/25 10:57:23 safonov Exp $
 */
public interface TaskServiceLocal extends DataBusinessObjectService<Task, Integer> {
	/**
	 * имя сервиса
	 */
	final static String SERVICE_NAME = "merp/schedule/Task"; //$NON-NLS-1$
	/**
	 * тип папки для констант
	 */
	final static short FOLDER_TYPE = 13000;

}
