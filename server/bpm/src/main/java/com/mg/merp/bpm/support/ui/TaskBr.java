/*
 * TaskBr.java
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
package com.mg.merp.bpm.support.ui;

import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.AbstractForm;
import com.mg.framework.generic.ui.AbstractTableModel;
import com.mg.framework.generic.ui.ColumnsTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableControllerAdapter;
import com.mg.framework.utils.MiscUtils;
import com.mg.merp.bpm.BusinessProccessManagerServiceLocal;

import org.jbpm.taskmgmt.exe.TaskInstance;

import java.util.List;

/**
 * Контроллер формы списка задач для пользователя или группы пользователей
 *
 * @author Oleg V. Safonov
 * @version $Id: TaskBr.java,v 1.3 2008/03/11 08:29:39 sharapov Exp $
 */
public class TaskBr extends AbstractForm {
  private TableControllerAdapter table;
  private List<TaskInstance> taskList;
  private long currentTaskId = 0;
  private BusinessProccessManagerServiceLocal businessProccessManagerService;

  public TaskBr() {
    businessProccessManagerService = (BusinessProccessManagerServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(BusinessProccessManagerServiceLocal.SERVICE_NAME);

    table = new DefaultTableController(new ColumnsTableModel() {

      public int getRowCount() {
        return taskList.size();
      }

      public Object getValueAt(int row, int column) {
        TaskInstance task = taskList.get(row);
        switch (column) {
          case 0:
            return task.getId();
          case 1:
            return task.getName();
          case 2:
            return task.getDescription();
          case 3:
            return task.getPriority();
          case 4:
            return task.getCreate();
          case 5:
            return task.getStart();
          case 6:
            return MiscUtils.getBooleanTextRepresentation(task.isSuspended());
        }
        return null;
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#setSelectedRows(int[])
       */
      @Override
      public void setSelectedRows(int[] rows) {
        if (rows.length == 0) {
          currentTaskId = 0;
          return;
        }
        currentTaskId = taskList.get(rows[0]).getId();
      }

    });
  }

  private void refresh() {
    loadTasks();
    ((AbstractTableModel) table.getModel()).fireModelChange();
  }

  private void loadTasks() {
    taskList = businessProccessManagerService.loadTasks();
  }

  public void execute() {
    loadTasks();
    run();
  }

  public void onActionRefresh(WidgetEvent event) {
    refresh();
  }

  public void onActionStartTask(WidgetEvent event) {
    businessProccessManagerService.startTask(currentTaskId);
    refresh();
  }

  public void onActionEndTask(WidgetEvent event) {
    businessProccessManagerService.endTask(currentTaskId);
    refresh();
  }

}
