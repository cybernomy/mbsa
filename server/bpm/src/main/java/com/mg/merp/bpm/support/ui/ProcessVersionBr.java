/*
 * ProcessVersionBr.java
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
import com.mg.merp.bpm.support.BPMManagerLocator;
import com.mg.merp.bpm.support.Messages;

import org.jbpm.JbpmContext;
import org.jbpm.graph.def.ProcessDefinition;

import java.util.List;

/**
 * Контроллер формы списка версий процессов
 *
 * @author Oleg V. Safonov
 * @version $Id: ProcessVersionBr.java,v 1.1 2007/05/28 13:05:48 safonov Exp $
 */
public class ProcessVersionBr extends AbstractForm {
  private TableControllerAdapter table;
  private List<ProcessDefinition> processList;
  private long processDefinitionId;
  private long currentProcessDefinitionId = 0;

  public ProcessVersionBr() {
    table = new DefaultTableController(new ColumnsTableModel() {

      public int getRowCount() {
        // TODO Auto-generated method stub
        return processList.size();
      }

      public Object getValueAt(int row, int column) {
        ProcessDefinition processDefinition = processList.get(row);
        switch (column) {
          case 0:
            return processDefinition.getId();
          case 1:
            return processDefinition.getDescription();
          case 2:
            return processDefinition.getVersion();
          default:
            return null;
        }
      }

      @Override
      public void setSelectedRows(int[] rows) {
        if (rows.length == 0) {
          currentProcessDefinitionId = 0;
          return;
        }
        currentProcessDefinitionId = processList.get(rows[0]).getId();
      }

    });
  }

  @SuppressWarnings("unchecked")
  private void loadProcessDefinitionVersions(long processDefinitionId) {
    JbpmContext context = BPMManagerLocator.locate().getCurrentBpmContext();
    try {
      String name = context.getGraphSession().loadProcessDefinition(processDefinitionId).getName();
      processList = context.getGraphSession().findAllProcessDefinitionVersions(name);
    } finally {
      context.close();
    }
  }

  private void refresh() {
    loadProcessDefinitionVersions(processDefinitionId);
    ((AbstractTableModel) table.getModel()).fireModelChange();
  }

  private ProcessDefinition getCurrentProcessDefinition(JbpmContext context) {
    return context.getGraphSession().getProcessDefinition(currentProcessDefinitionId);
  }

  public void execute(long processDefinitionId) {
    this.processDefinitionId = processDefinitionId;
    JbpmContext context = BPMManagerLocator.locate().getCurrentBpmContext();
    try {
      ProcessDefinition processDefinition = context.getGraphSession().loadProcessDefinition(processDefinitionId);
      setTitle(Messages.getInstance().getMessage(Messages.PROCESS_VERSIONS_TITLE, new Object[]{processDefinition.getName()}));
    } finally {
      context.close();
    }
    loadProcessDefinitionVersions(processDefinitionId);
    run();
  }

  public void onActionRefresh(WidgetEvent event) {
    refresh();
  }

  public void onActionShowProcessInstances(WidgetEvent event) {
    JbpmContext context = BPMManagerLocator.locate().getCurrentBpmContext();
    try {
      ProcessDefinition pd = getCurrentProcessDefinition(context);
      if (pd == null)
        return;

      ProcessInstanceBr form = (ProcessInstanceBr) UIProducer.produceForm("com/mg/merp/bpm/resources/ProcessInstanceBr.mfd.xml");
      form.execute(pd.getId());
    } finally {
      context.close();
    }
  }

  public void onActionDeleteProcess(WidgetEvent event) {
    JbpmContext context = BPMManagerLocator.locate().getCurrentBpmContext();
    try {
      context.getGraphSession().deleteProcessDefinition(getCurrentProcessDefinition(context));
    } finally {
      context.close();
    }
    refresh();
  }

}
