/*
 * BusinessProccessManagerServiceLocal.java
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
package com.mg.merp.bpm;

import com.mg.framework.api.BusinessObjectService;

import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.taskmgmt.exe.TaskInstance;

import java.io.InputStream;
import java.util.List;

/**
 * Бизнес-компонент "Менеджер бизнес-процессов"
 *
 * @author Artem V. Sharapov
 * @version $Id: BusinessProccessManagerServiceLocal.java,v 1.1 2008/03/11 08:20:32 sharapov Exp $
 */
public interface BusinessProccessManagerServiceLocal extends BusinessObjectService {

  /**
   * Имя сервиса
   */
  static final String SERVICE_NAME = "merp/bpm/BusinessProccessManager"; //$NON-NLS-1$

  /**
   * Имя метода "Загрузить список задач"
   */
  static final String LOAD_TASKS_METHOD = "loadTasks"; //$NON-NLS-1$

  /**
   * Имя метода "Загрузить список бизнес-процессов"
   */
  static final String LOAD_PROCESSES_METHOD = "loadProcesses"; //$NON-NLS-1$

  /**
   * Имя метода "Развернуть бизнес-процесс"
   */
  static final String DEPLOY_PROCESS_METHOD = "deployProcess"; //$NON-NLS-1$

  /**
   * Загрузить список задач
   *
   * @return список задач
   */
  List<TaskInstance> loadTasks();

  /**
   * Запустить задачу
   *
   * @param taskId - идентификатор задачи
   */
  void startTask(long taskId);

  /**
   * Завершить задачу
   *
   * @param taskId - идентификатор задачи
   */
  void endTask(long taskId);

  /**
   * Загрузить список бизнес-процессов
   *
   * @return список бизнес-процессов
   */
  List<ProcessDefinition> loadProcesses();

  /**
   * Развернуть бизнес-процесс
   *
   * @param inputStream - поток
   */
  void deployProcess(InputStream inputStream);

  /**
   * Удалить бизнес-процесс
   *
   * @param processId - идентификатор бизнес-процесса
   */
  void deleteProcess(long processId);

  /**
   * Создать экземпляр бизнес-процесса
   *
   * @param processId - идентификатор бизнес-процесса
   */
  void createProcessInstance(long processId);

}
