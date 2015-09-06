/*
 * ProcessInstanceBr.java
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
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableControllerAdapter;
import com.mg.framework.utils.MiscUtils;
import com.mg.merp.bpm.support.BPMManagerLocator;
import com.mg.merp.bpm.support.Messages;

import org.jbpm.JbpmContext;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;

import java.util.List;

/**
 * Контроллер формы списка экземпляров процессов
 *
 * @author Oleg V. Safonov
 * @version $Id: ProcessInstanceBr.java,v 1.1 2007/05/28 13:05:48 safonov Exp $
 */
public class ProcessInstanceBr extends AbstractForm {
  private TableControllerAdapter table;
  private List<ProcessInstance> processInstances;
  private long currentProcessInstanceId = 0;
  private long processDefinitionId;

  public ProcessInstanceBr() {
    table = new DefaultTableController(new ColumnsTableModel() {

      public int getRowCount() {
        return processInstances.size();
      }

      public Object getValueAt(int row, int column) {
        ProcessInstance processInstance = processInstances.get(row);
        switch (column) {
          case 0:
            return processInstance.getId();
          case 1:
            return processInstance.getStart();
          case 2:
            return processInstance.getEnd();
          case 3:
            return MiscUtils.getBooleanTextRepresentation(processInstance.isSuspended());
        }
        return null;
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#setSelectedRows(int[])
       */
      @Override
      public void setSelectedRows(int[] rows) {
        if (rows.length == 0) {
          currentProcessInstanceId = 0;
          return;
        }
        currentProcessInstanceId = processInstances.get(rows[0]).getId();
      }

    });
  }

  private ProcessInstance getCurrentProcessInstance(JbpmContext context) {
    return context.getProcessInstance(currentProcessInstanceId);
  }

  @SuppressWarnings("unchecked")
  private void loadProcessInstances(long processDefinitionId) {
    this.processDefinitionId = processDefinitionId;
    JbpmContext context = BPMManagerLocator.locate().getCurrentBpmContext();
    try {
      processInstances = context.getGraphSession().findProcessInstances(processDefinitionId);
    } finally {
      context.close();
    }
  }

  private void refresh() {
    loadProcessInstances(processDefinitionId);
    ((AbstractTableModel) table.getModel()).fireModelChange();
  }

  public void execute(long processDefinitionId) {
    JbpmContext context = BPMManagerLocator.locate().getCurrentBpmContext();
    try {
      ProcessDefinition processDefinition = context.getGraphSession().loadProcessDefinition(processDefinitionId);
      setTitle(Messages.getInstance().getMessage(Messages.PROCESS_INSTANCES_TITLE, new Object[]{processDefinition.getName(), processDefinition.getVersion()}));
    } finally {
      context.close();
    }
    loadProcessInstances(processDefinitionId);
    run();
  }

  public void onActionStartProcessInstance(WidgetEvent event) {
    JbpmContext context = BPMManagerLocator.locate().getCurrentBpmContext();
    try {
      getCurrentProcessInstance(context).signal();
    } finally {
      context.close();
    }
    refresh();
  }

  public void onActionDeleteProcessInstance(WidgetEvent event) {
    JbpmContext context = BPMManagerLocator.locate().getCurrentBpmContext();
    try {
      context.getGraphSession().deleteProcessInstance(getCurrentProcessInstance(context));
    } finally {
      context.close();
    }
    refresh();
  }

  public void onActionRefresh(WidgetEvent event) {
    refresh();
  }

  public void onActionShowLogs(WidgetEvent event) {
    ProcessLogBr form = (ProcessLogBr) UIProducer.produceForm("com/mg/merp/bpm/resources/ProcessLogBr.mfd.xml");
    form.execute(currentProcessInstanceId);
  }

}
