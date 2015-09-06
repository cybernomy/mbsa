/*
 * BusinessProccessManagerServiceBean.java
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
package com.mg.merp.bpm.support;

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.generic.AbstractPOJOBusinessObjectStatelessServiceBean;
import com.mg.framework.utils.MiscUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.bpm.BusinessProccessManagerServiceLocal;
import com.mg.merp.bpm.model.Resource;
import com.mg.merp.bpm.model.ResourceGroupLink;

import org.apache.commons.lang.ArrayUtils;
import org.jbpm.JbpmContext;
import org.jbpm.JbpmException;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Менеджер бизнес-процессов"
 *
 * @author Artem V. Sharapov
 * @version $Id: BusinessProccessManagerServiceBean.java,v 1.1 2008/03/11 08:23:20 sharapov Exp $
 */
@Stateless(name = "merp/bpm/BusinessProccessManagerService") //$NON-NLS-1$
public class BusinessProccessManagerServiceBean extends AbstractPOJOBusinessObjectStatelessServiceBean implements BusinessProccessManagerServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.bpm.BusinessProccessManagerServiceLocal#loadTasks()
   */
  public List<TaskInstance> loadTasks() {
    return doLoadTasks();
  }

  /**
   * Загрузить список задач
   *
   * @return список задач
   */
  protected List<TaskInstance> doLoadTasks() {
    List<TaskInstance> taskList;
    JbpmContext context = BPMManagerLocator.locate().getCurrentBpmContext();
    try {
      OrmTemplate tmpl = OrmTemplate.getInstance();
      List<String> actorIds = tmpl.findByCriteria(OrmTemplate.createCriteria(Resource.class, "r") //$NON-NLS-1$
          .setProjection(Projections.property("r.Key")) //$NON-NLS-1$
          .add(Restrictions.eq("r.User.Id", ServerUtils.getUserProfile().getIdentificator()))); //$NON-NLS-1$
      //если не указан пользователь, берем из связей с группами
      if (actorIds.size() == 0) {
        List<String> actorIdsByGroup = tmpl.findByCriteria(OrmTemplate.createCriteria(ResourceGroupLink.class, "l") //$NON-NLS-1$
            .createAlias("l.Resource", "r") //$NON-NLS-1$ //$NON-NLS-2$
            .setProjection(Projections.property("r.Key")) //$NON-NLS-1$
            .add(Restrictions.in("l.Group.Id", (Object[]) ArrayUtils.toObject(ServerUtils.getUserProfile().getGroups())))); //$NON-NLS-1$
        actorIds.addAll(actorIdsByGroup);
      }
      //если так и не нашли ресурс, то ничего не грузим
      if (actorIds.size() == 0) {
        taskList = new ArrayList<TaskInstance>();
      } else {
        for (int i = 0; i < actorIds.size(); i++)
          actorIds.set(i, actorIds.get(i).toLowerCase());
        //загрузим из группового списка taskList = context.getGroupTaskList(actorIds);
        taskList = MiscUtils.convertUncheckedList(TaskInstance.class, context.getGroupTaskList(actorIds));
        //загрузим из индивидуального списка
        for (String actor : actorIds)
          taskList.addAll(MiscUtils.convertUncheckedList(TaskInstance.class, context.getTaskList(actor)));
      }
    } finally {
      context.close();
    }
    return taskList;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.bpm.BusinessProccessManagerServiceLocal#startTask(long)
   */
  public void startTask(long taskId) {
    doStartTask(taskId);
  }

  /**
   * Запустить задачу
   *
   * @param taskId - идентификатор задачи
   */
  protected void doStartTask(long taskId) {
    JbpmContext context = BPMManagerLocator.locate().getCurrentBpmContext();
    try {
      if (taskId != 0)
        getCurrentTaskInstance(context, taskId).start();
    } finally {
      context.close();
    }
  }

  /* (non-Javadoc)
   * @see com.mg.merp.bpm.BusinessProccessManagerServiceLocal#endTask(long)
   */
  public void endTask(long taskId) {
    doEndTask(taskId);
  }

  /**
   * Завершить задачу
   *
   * @param taskId - идентификатор задачи
   */
  protected void doEndTask(long taskId) {
    JbpmContext context = BPMManagerLocator.locate().getCurrentBpmContext();
    try {
      if (taskId != 0)
        getCurrentTaskInstance(context, taskId).end();
    } finally {
      context.close();
    }
  }

  private TaskInstance getCurrentTaskInstance(JbpmContext context, long taskId) {
    return context.loadTaskInstance(taskId);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.bpm.BusinessProccessManagerServiceLocal#loadProcesses()
   */
  public List<ProcessDefinition> loadProcesses() {
    return doLoadProcesses();
  }

  /**
   * Загрузить список бизнес-процессов
   *
   * @return список бизнес-процессов
   */
  protected List<ProcessDefinition> doLoadProcesses() {
    List<ProcessDefinition> processList;
    JbpmContext context = BPMManagerLocator.locate().getCurrentBpmContext();
    try {
      processList = MiscUtils.convertUncheckedList(ProcessDefinition.class, context.getGraphSession().findLatestProcessDefinitions());
    } finally {
      context.close();
    }
    return processList;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.bpm.BusinessProccessManagerServiceLocal#deployProcess(java.io.InputStream)
   */
  public void deployProcess(InputStream inputStream) {
    doDeployProcess(inputStream);
  }

  /**
   * Развернуть бизнес-процесс
   *
   * @param inputStream - поток
   */
  protected void doDeployProcess(InputStream inputStream) {
    JbpmContext context = BPMManagerLocator.locate().getCurrentBpmContext();
    try {
      ZipInputStream zipInputStream = new ZipInputStream(inputStream);
      ProcessDefinition processDefinition = ProcessDefinition.parseParZipInputStream(zipInputStream);
      context.getGraphSession().deployProcessDefinition(processDefinition);
    } finally {
      context.close();
    }
  }

  /* (non-Javadoc)
   * @see com.mg.merp.bpm.BusinessProccessManagerServiceLocal#deleteProcess(long)
   */
  public void deleteProcess(long processId) {
    doDeleteProcess(processId);
  }

  /**
   * Удалить бизнес-процесс
   *
   * @param processId - идентификатор бизнес-процесса
   */
  protected void doDeleteProcess(long processId) {
    JbpmContext context = BPMManagerLocator.locate().getCurrentBpmContext();
    try {
      context.getGraphSession().deleteProcessDefinition(getCurrentProcessDefinition(context, processId));
    } catch (JbpmException e) {
      throw new BusinessException(Messages.getInstance().getMessage(Messages.PROCESS_NOT_FOUND));
    } finally {
      context.close();
    }
  }

  /* (non-Javadoc)
   * @see com.mg.merp.bpm.BusinessProccessManagerServiceLocal#createProcessInstance(long)
   */
  public void createProcessInstance(long processId) {
    doCreateProcessInstance(processId);
  }

  /**
   * Создать экземпляр бизнес-процесса
   *
   * @param processId - идентификатор бизнес-процесса
   */
  protected void doCreateProcessInstance(long processId) {
    JbpmContext context = BPMManagerLocator.locate().getCurrentBpmContext();
    try {
      ProcessInstance processInstance = new ProcessInstance(getCurrentProcessDefinition(context, processId));
      //FIXME, workaround http://jira.jboss.com/jira/browse/JBPM-913
      processInstance.setKey(String.valueOf(processInstance.getId()));
      //
      context.save(processInstance);
    } catch (JbpmException e) {
      throw new BusinessException(Messages.getInstance().getMessage(Messages.PROCESS_NOT_FOUND));
    } finally {
      context.close();
    }
  }

  private ProcessDefinition getCurrentProcessDefinition(JbpmContext context, long processId) {
    return context.getGraphSession().getProcessDefinition(processId);
  }

}