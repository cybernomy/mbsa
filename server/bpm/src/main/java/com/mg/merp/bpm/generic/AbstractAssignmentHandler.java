/*
 * AbstractAssignmentHandler.java
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

import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.taskmgmt.def.AssignmentHandler;
import org.jbpm.taskmgmt.exe.Assignable;

import com.mg.framework.utils.StringUtils;

/**
 * Базовый класс реализации назначения исполнителей для задач
 * 
 * @author Oleg V. Safonov
 * @version $Id: AbstractAssignmentHandler.java,v 1.1 2007/05/28 13:05:48 safonov Exp $
 */
public abstract class AbstractAssignmentHandler implements AssignmentHandler {
	private ExecutionContext executionContext;
	private Assignable assignable;

	protected abstract void doAssign() throws Exception;
	
	/**
	 * получить контекст выполнения JBPM
	 * 
	 * @return	контекст выполнения
	 */
	protected ExecutionContext getExecutionContext() {
		return executionContext;
	}

	/**
	 * получить объект управления назначениями
	 * 
	 * @return	объект управления назначениями
	 */
	protected Assignable getAssignable() {
		return assignable;
	}

	/**
	 * установить исполнителя задачи
	 * 
	 * @param actorId	код исполнителя
	 */
	protected void setAssignableActorId(String actorId) {
		if (actorId != null)
			assignable.setActorId(actorId);
	}
	
	/**
	 * установить исполнителей задачи
	 * 
	 * @param pooledActors	список исполнителей, для разделения кодов используется символ ','
	 */
	protected void setAssignablePooledActors(String pooledActors) {
		if (pooledActors != null)
			assignable.setPooledActors(StringUtils.split(pooledActors, ",").toArray(new String[0]));
	}
	
	/* (non-Javadoc)
	 * @see org.jbpm.taskmgmt.def.AssignmentHandler#assign(org.jbpm.taskmgmt.exe.Assignable, org.jbpm.graph.exe.ExecutionContext)
	 */
	public void assign(Assignable assignable, ExecutionContext executionContext)
			throws Exception {
		this.executionContext = executionContext;
		this.assignable = assignable;
		doAssign();
	}

}
