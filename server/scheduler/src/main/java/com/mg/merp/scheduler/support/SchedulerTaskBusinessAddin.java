/**
 * SchedulerTaskBusinessAddin.java
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
package com.mg.merp.scheduler.support;

import java.util.Map;

import org.quartz.JobExecutionContext;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.merp.baiengine.generic.AbstractBusinessAddin;
import com.mg.merp.scheduler.model.Task;

/**
 * Базовая реализация бизнес-расширения для создания задач планировщика, класс наследник должен
 * переопределить метод {@link #doPerform()}. В реализации данного расширения не допускается использование
 * пользовательского интерфейса.
 * 
 * @author Oleg V. Safonov
 * @version $Id: SchedulerTaskBusinessAddin.java,v 1.1 2008/04/25 10:57:23 safonov Exp $
 */
public abstract class SchedulerTaskBusinessAddin extends AbstractBusinessAddin<Void> {
	/**
	 * имя параметра "код задачи"
	 */
	public static final String SCHEDULER_TASK_CODE = "SCHEDULER_TASK_CODE";
	/**
	 * имя параметра "контекст выполнения задачи планировщика"
	 */
	public static final String SCHEDULER_JOB_EXECUTION_CONTEXT = "SCHEDULER_JOB_EXECUTION_CONTEXT";
	private String taskCode;
	private Task schedulerTask;
	private JobExecutionContext context;
	
	/* (non-Javadoc)
	 * @see com.mg.merp.baiengine.generic.AbstractBusinessAddin#extractParams(java.util.Map)
	 */
	@Override
	protected void extractParams(Map<String, ? extends Object> params) {
		taskCode = (String) params.get(SCHEDULER_TASK_CODE);
		context = (JobExecutionContext) params.get(SCHEDULER_JOB_EXECUTION_CONTEXT);
	}

	/**
	 * получить задачу планировщика запустивщую данное бизнес-расширение
	 * 
	 * @return	задача планировщика
	 */
	protected Task getSchedulerTask() {
		if (schedulerTask == null && taskCode != null)
			schedulerTask = OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(Task.class)
					.add(Restrictions.eq("Code", taskCode.toUpperCase())));		
		return schedulerTask;
	}
	
	/**
	 * получить контекст выполнения задачи
	 * 
	 * @return	контекст
	 */
	protected JobExecutionContext getContext() {
		return context;
	}
	
}
