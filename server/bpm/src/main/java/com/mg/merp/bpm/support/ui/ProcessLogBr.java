/*
 * ProcessLogBr.java
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jbpm.JbpmContext;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.Token;
import org.jbpm.logging.log.ProcessLog;

import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.AbstractForm;
import com.mg.framework.generic.ui.AbstractTableModel;
import com.mg.framework.generic.ui.ColumnsTableModel;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableControllerAdapter;
import com.mg.merp.bpm.support.BPMManagerLocator;
import com.mg.merp.bpm.support.Messages;

/**
 * Контроллер формы списка лога действий над процессом
 * 
 * @author Oleg V. Safonov
 * @version $Id: ProcessLogBr.java,v 1.1 2007/05/28 13:05:48 safonov Exp $
 */
public class ProcessLogBr extends AbstractForm {
	protected TableControllerAdapter table;
	private List<LogItem> logList = new ArrayList<LogItem>();
	private long processInstanceId;

	public ProcessLogBr() {
		table = new DefaultTableController(new ColumnsTableModel() {

			public int getRowCount() {
				return logList.size();
			}

			public Object getValueAt(int row, int column) {
				LogItem logItem = logList.get(row);
				switch (column) {
				case 0: return logItem.id;
				case 1: return logItem.name;
				//case 2: return logItem.fullName;
				case 2: return logItem.date;
				case 3: return logItem.start;
				case 4: return logItem.end;
				default:
					return null;
				}
			}

//			@Override
//			public void setSelectedRows(int[] rows) {
//				if (rows.length == 0) {
//					currentProcessDefinitionId = 0;
//					return;
//				}
//				currentProcessDefinitionId = processList.get(rows[0]).getId();
//			}
			
		});
	}
	
	class LogItem {
		long id;
		String name;
		int version;
		String fullName;
		Date date;
		Date start;
		Date end;
		
		private LogItem(long id, String name, /*String fullName,*/ Date date, Date start, Date end) {
			super();
			this.id = id;
			this.name = name;
			//this.fullName = fullName;
			this.date = date;
			this.start = start;
			this.end = end;
		}

	}
	
	@SuppressWarnings("unchecked")
	private void loadProcessLogs(long processInstanceId) {
		JbpmContext context = BPMManagerLocator.locate().getCurrentBpmContext();
		try {
			Map<Token, List<ProcessLog>> maps = context.getLoggingSession().findLogsByProcessInstance(processInstanceId);
			logList.clear();
			for (Token token : maps.keySet()) {
				for (ProcessLog processLog : maps.get(token)) {
					logList.add(new LogItem(token.getId(), token.getNode().getName(), /*token.getFullName(),*/ processLog.getDate(), token.getStart(), token.getEnd()));
				}
			}
		} finally {
			context.close();
		}		
	}
	
	public void execute(long processInstanceId) {
		this.processInstanceId = processInstanceId;
		JbpmContext context = BPMManagerLocator.locate().getCurrentBpmContext();
		try {
			ProcessDefinition processDefinition = context.getGraphSession().loadProcessInstance(processInstanceId).getProcessDefinition();
			setTitle(Messages.getInstance().getMessage(Messages.PROCESS_LOGS_TITLE, new Object[] {processDefinition.getName(), processDefinition.getVersion()}));
		} finally {
			context.close();
		}
		loadProcessLogs(processInstanceId);
		run();
	}
	
	public void onActionRefresh(WidgetEvent event) {
		loadProcessLogs(processInstanceId);
		((AbstractTableModel) table.getModel()).fireModelChange();
	}

}
