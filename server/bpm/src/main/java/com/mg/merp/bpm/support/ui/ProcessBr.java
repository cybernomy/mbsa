/*
 * ProcessBr.java
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

import java.io.InputStream;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.jbpm.JbpmContext;
import org.jbpm.graph.def.ProcessDefinition;

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.security.BusinessMethodPermission;
import com.mg.framework.api.ui.FileLoadHandler;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.AbstractForm;
import com.mg.framework.generic.ui.AbstractTableModel;
import com.mg.framework.generic.ui.ColumnsTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableControllerAdapter;
import com.mg.framework.utils.SecurityUtils;
import com.mg.merp.bpm.BusinessProccessManagerServiceLocal;
import com.mg.merp.bpm.support.BPMManagerLocator;
import com.mg.merp.bpm.support.Messages;

/**
 * Контроллер формы списка бизнес-процессов
 * 
 * @author Oleg V. Safonov
 * @version $Id: ProcessBr.java,v 1.4 2008/03/11 08:26:03 sharapov Exp $
 */
public class ProcessBr extends AbstractForm {
	private TableControllerAdapter table;
	private List<ProcessDefinition> processList;
	private long currentProcessDefinitionId = 0;
	private BusinessProccessManagerServiceLocal businessProccessManagerService;

	public ProcessBr() {
		businessProccessManagerService = (BusinessProccessManagerServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(BusinessProccessManagerServiceLocal.SERVICE_NAME);
		
		table = new DefaultTableController(new ColumnsTableModel() {

			public int getRowCount() {
				return processList.size();
			}

			public Object getValueAt(int row, int column) {
				ProcessDefinition processDefinition = processList.get(row);
				switch (column) {
				case 0: return processDefinition.getId();
				case 1: return processDefinition.getName();
				case 2: return processDefinition.getVersion();
				case 3: return processDefinition.getDescription();
				default:
					return null;
				}
			}
			
			/*
			 * (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#setSelectedRows(int[])
			 */
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

	private void loadProcesses() {
		processList = businessProccessManagerService.loadProcesses();
	}

	private ProcessDefinition getCurrentProcessDefinition(JbpmContext context) {
		return context.getGraphSession().getProcessDefinition(currentProcessDefinitionId);
	}

	private void refresh() {
		loadProcesses();
		((AbstractTableModel) table.getModel()).fireModelChange();
	}

	public void execute() {
		loadProcesses();
		run();
	}

	public void onActionDeployProcessArchive(WidgetEvent event) throws Exception {
		SecurityUtils.checkPermission(new BusinessMethodPermission(BusinessProccessManagerServiceLocal.SERVICE_NAME, BusinessProccessManagerServiceLocal.DEPLOY_PROCESS_METHOD));
		
		UIUtils.loadFile(new FileLoadHandler() {

			public void onFailure(int reason, String description) {
				logger.debug("failure uploaded, reason: " + description); //$NON-NLS-1$
			}

			public void onSuccess(InputStream[] ins, String[] filePaths, String[] fileNames) {
				if (ins == null || ins.length == 0)
					return;
				
				businessProccessManagerService.deployProcess(ins[0]);
				refresh();
			}

		});
	}

	public void onActionCreateProcessInstance(WidgetEvent event) {
		businessProccessManagerService.createProcessInstance(currentProcessDefinitionId);
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
		businessProccessManagerService.deleteProcess(currentProcessDefinitionId);
		refresh();
	}

	public void onActionShowProcessVersions(WidgetEvent event) {
		try {
			ProcessVersionBr form = (ProcessVersionBr) UIProducer.produceForm("com/mg/merp/bpm/resources/ProcessVersionBr.mfd.xml");
			form.execute(currentProcessDefinitionId);
		} catch (ObjectNotFoundException e) {
			throw new BusinessException(Messages.getInstance().getMessage(Messages.PROCESS_NOT_FOUND));			
		}
	}

	public void onActionRefresh(WidgetEvent event) {
		refresh();
	}

	public void onActionShowImage(WidgetEvent event) {
		try {
			ProcessImageForm form = (ProcessImageForm) UIProducer.produceForm("com/mg/merp/bpm/resources/ProcessImageForm.mfd.xml");
			form.execute(currentProcessDefinitionId);
		} catch (ObjectNotFoundException e) {
			throw new BusinessException(Messages.getInstance().getMessage(Messages.PROCESS_NOT_FOUND));			
		}
	}

}
