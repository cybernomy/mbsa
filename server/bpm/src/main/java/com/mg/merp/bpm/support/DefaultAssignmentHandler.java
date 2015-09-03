/*
 * DefaultAssignmentHandler.java
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
package com.mg.merp.bpm.support;

import com.mg.merp.bpm.generic.AbstractAssignmentHandler;

/**
 * Стандартная реализация назначения исполнителей для задач, для установки исполнителя используется {@link #actorId},
 * для установки пула исполнителей используется {@link #pooledActors}, {@link #pooledActors} строка
 * содержащая список исполнителей разделенных символом ','
 * 
 * @author Oleg V. Safonov
 * @version $Id: DefaultAssignmentHandler.java,v 1.1 2007/05/28 13:05:48 safonov Exp $
 */
public class DefaultAssignmentHandler extends AbstractAssignmentHandler {
	private String actorId;
	private String pooledActors;

	
	/* (non-Javadoc)
	 * @see com.mg.merp.bpm.generic.AbstractAssignmentHandler#doAssign()
	 */
	@Override
	protected void doAssign() throws Exception {
		setAssignableActorId(actorId);
		setAssignablePooledActors(pooledActors);
	}

	/**
	 * @return the actorId
	 */
	public String getActorId() {
		return actorId;
	}

	/**
	 * @param actorId the actorId to set
	 */
	public void setActorId(String actorId) {
		this.actorId = actorId;
	}

	/**
	 * @return the pooledActors
	 */
	public String getPooledActors() {
		return pooledActors;
	}

	/**
	 * @param pooledActors the pooledActors to set
	 */
	public void setPooledActors(String resourceKeys) {
		this.pooledActors = resourceKeys;
	}

}
