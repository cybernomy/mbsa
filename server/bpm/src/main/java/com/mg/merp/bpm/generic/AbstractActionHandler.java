/*
 * AbstractActionHandler.java
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
package com.mg.merp.bpm.generic;

import org.jbpm.context.exe.ContextInstance;
import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.taskmgmt.exe.TaskMgmtInstance;

/**
 * Базовый класс обработки действий JBPM
 * 
 * @author Oleg V. Safonov
 * @version $Id: AbstractActionHandler.java,v 1.1 2007/05/28 13:05:48 safonov Exp $
 */
public abstract class AbstractActionHandler implements ActionHandler {
	private ExecutionContext executionContext;

	/**
	 * необходимо реализовать в классе обработчике выполняющем специфичные действия
	 * бизнес-логики
	 * 
	 * @throws Exception
	 */
	protected abstract void doExecute() throws Exception;

	/**
	 * получить контекст выполнения JBPM
	 * 
	 * @return	контекст выполнения
	 */
	protected ExecutionContext getExecutionContext() {
		return executionContext;
	}
	
	/**
	 * получить контекст экземпляра процесса
	 * @see org.jbpm.context.exe.ContextInstance
	 * 
	 * @return	контекст экземпляра
	 */
	protected ContextInstance getContextInstance() {
		return executionContext.getContextInstance();
	}
	
	/**
	 * получить менеджер управления задачами процесса
	 * @see org.jbpm.context.exe.TaskMgmtInstance
	 * 
	 * @return	менеджер управления задачами
	 */
	protected TaskMgmtInstance getTaskMgmtInstance() {
		return executionContext.getTaskMgmtInstance();
	}
	
	/* (non-Javadoc)
	 * @see org.jbpm.graph.def.ActionHandler#execute(org.jbpm.graph.exe.ExecutionContext)
	 */
	public final void execute(ExecutionContext executionContext) throws Exception {
		this.executionContext = executionContext;
		doExecute();
	}

}
