/**
 * BAiJob.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.merp.scheduler.support;

import com.mg.framework.utils.SecurityUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.baiengine.BusinessAddinEngineLocator;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.HashMap;
import java.util.Map;

/**
 * Запуск BAi планировщиком
 *
 * @author Oleg V. Safonov
 * @version $Id: BAiJob.java,v 1.2 2008/08/28 13:40:35 safonov Exp $
 */
public class BAiJob implements Job {
  /**
   * код BAi
   */
  public static final String BAI_CODE = "MG_BAI_CODE";
  /**
   * код задачи планировщика
   */
  public static final String SCHEDULER_TASK_CODE = SchedulerTaskBusinessAddin.SCHEDULER_TASK_CODE;
  /**
   * имя учетной записи под которой будет выполняться задание
   */
  public static final String SCHEDULER_USER_NAME = "SCHEDULER_USER_NAME";
  /**
   * пароль учетной записи под которой будет выполняться задание
   */
  public static final String SCHEDULER_PASSWORD = "SCHEDULER_PASSWORD";
  /**
   * мандант для которого будет выполняться задание
   */
  public static final String SCHEDULER_JOB_TENANT = "SCHEDULER_JOB_TENANT";

  /* (non-Javadoc)
   * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
   */
  public void execute(JobExecutionContext context) throws JobExecutionException {
    try {
      ServerUtils.addSystemAuditEvent("scheduler", "start job", "job name " + context.getJobDetail().getName());

      JobDataMap dataMap = context.getJobDetail().getJobDataMap();

      //login
      ServerUtils.createWorkingConnection(null).login(SecurityUtils.createAuthenticateParams(dataMap.getString(SCHEDULER_USER_NAME), dataMap.getString(SCHEDULER_PASSWORD), dataMap.getString(SCHEDULER_JOB_TENANT)));

      String baiId = (String) dataMap.get(BAI_CODE);
      Map<String, Object> params = new HashMap<String, Object>();
      params.put(SchedulerTaskBusinessAddin.SCHEDULER_TASK_CODE, dataMap.get(SCHEDULER_TASK_CODE));
      params.put(SchedulerTaskBusinessAddin.SCHEDULER_JOB_EXECUTION_CONTEXT, context);
      BusinessAddinEngineLocator.locate().perform(baiId, params, null);

      ServerUtils.addSystemAuditEvent("scheduler", "job completed", "job name " + context.getJobDetail().getName());
    } catch (Exception e) {
      ServerUtils.addSystemAuditEvent("scheduler", "job execution failed with exception: " + e.toString(), "job name " + context.getJobDetail().getName());
      throw new JobExecutionException(e);
    } finally {
      //logout
      ServerUtils.getWorkingConnection().logout();
    }
  }

}
