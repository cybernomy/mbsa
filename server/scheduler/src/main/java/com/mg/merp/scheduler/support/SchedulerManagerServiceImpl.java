/**
 * SchedulerManagerServiceImpl.java
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

import com.mg.framework.api.Logger;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.baiengine.model.Repository;
import com.mg.merp.scheduler.model.Task;

import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import java.text.ParseException;
import java.util.List;
import java.util.Properties;

import javax.transaction.TransactionManager;

/**
 * Реализация менеджера планировщика
 *
 * @author Oleg V. Safonov
 * @version $Id: SchedulerManagerServiceImpl.java,v 1.2 2008/08/28 13:32:54 safonov Exp $
 */
public class SchedulerManagerServiceImpl {
  private final static String SCHEDULER_GROUP = "mbsa";
  private Logger logger = ServerUtils.getLogger(SchedulerManagerServiceImpl.class);
  private Scheduler scheduler;
  private String schedulerUserName;
  private String schedulerPassword;

  private String generateJobId(Task task) {
    return new StringBuilder("SysClient_").append(task.getSysClient().getId()).append("_").append(task.getCode().trim()).toString();
  }

  private List<Task> loadTasks() {
    return OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(Task.class)
        .add(Restrictions.eq("Active", true)));
  }

  private Task findByCode(Integer sysClientId, String taskCode) {
    return OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(Task.class)
        .add(Restrictions.eq("Code", taskCode.toUpperCase()))
        .add(Restrictions.eq("SysClient.Id", sysClientId)));
  }

  private void doRegisterTask(Task task) {
    if (task == null || !task.isActive())
      return;

    logger.info("register scheduler task " + task.getCode());
    Repository bai = task.getBAi();
    if (bai == null) {
      logger.info("bai is null, task " + task.getCode());
      return;
    }

    String jobId = generateJobId(task);
    JobDetail job = new JobDetail(jobId, SCHEDULER_GROUP, BAiJob.class);
    JobDataMap map = job.getJobDataMap();
    map.put(BAiJob.SCHEDULER_TASK_CODE, task.getCode());
    map.put(BAiJob.BAI_CODE, bai.getCode());
    map.put(BAiJob.SCHEDULER_USER_NAME, schedulerUserName);
    map.put(BAiJob.SCHEDULER_PASSWORD, schedulerPassword);
    map.put(BAiJob.SCHEDULER_JOB_TENANT, task.getSysClient().getCode());

    Trigger trigger;
    try {
      trigger = new CronTrigger(jobId, SCHEDULER_GROUP, task.getCronExpression());
    } catch (ParseException e) {
      logger.warn("invalid cron expression " + task.getCronExpression(), e);
      return;
    }

    try {
      scheduler.scheduleJob(job, trigger);
    } catch (SchedulerException e) {
      logger.error("schedule job failed, task " + task.getCode(), e);
    }
  }

  private void doUnregisterTask(Task task) {
    if (task == null)
      return;

    logger.info("unregister scheduler task " + task.getCode());
    String jobId = generateJobId(task);
    try {
      scheduler.deleteJob(jobId, SCHEDULER_GROUP);
    } catch (SchedulerException e) {
      logger.error("delete job failed, task " + task.getCode(), e);
    }
  }

  /**
   * старт менеджера
   *
   * @param properties свойства запуска
   * @throws Exception при любых ошибках
   */
  public void start(Properties properties) throws Exception {
    logger.debug("start scheduler");
    SchedulerFactory schedulerFactory = new StdSchedulerFactory(properties);
    scheduler = schedulerFactory.getScheduler();
    scheduler.start();
  }

  /**
   * остановка менеджера
   *
   * @throws Exception при любых ошибках
   */
  public void stop() throws Exception {
    logger.debug("stop scheduler");
    scheduler.shutdown();
  }

  /**
   * регистрация задачи планировщика в планировщик
   *
   * @param sysClientId мандант
   * @param taskCode    код задачи
   */
  public void registerTask(Integer sysClientId, String taskCode) {
    Task task = findByCode(sysClientId, taskCode);
    if (task != null)
      doRegisterTask(task);
  }

  /**
   * удаление задачи планировщика из планировщика
   *
   * @param sysClientId мандант
   * @param taskCode    код задачи
   */
  public void unregisterTask(Integer sysClientId, String taskCode) {
    Task task = findByCode(sysClientId, taskCode);
    if (task != null)
      doUnregisterTask(task);
  }

  /**
   * загрузка задач планировщика в планировщик
   */
  public void scheduleTasks() {
    logger.info("schedule tasks");

    TransactionManager tm = ServerUtils.getTransactionManager();
    boolean startTran;
    try {
      startTran = tm.getTransaction() == null;
      if (startTran)
        tm.begin();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    try {
      List<Task> tasks = loadTasks();
      for (Task task : tasks) {
        if (task == null)
          continue;
        try {
          doRegisterTask(task);
        } catch (Exception e) {
          logger.error("register schedule task failure " + task.getCode(), e);
        }
      }
    } finally {
      if (startTran)
        try {
          tm.commit();
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
    }
  }

  /**
   * получить планировщик
   *
   * @return планировщик
   */
  public Scheduler getScheduler() {
    return scheduler;
  }

  public void setSchedulerPassword(String schedulerPassword) {
    this.schedulerPassword = schedulerPassword;
  }

  public void setSchedulerUserName(String schedulerUserName) {
    this.schedulerUserName = schedulerUserName;
  }

}
