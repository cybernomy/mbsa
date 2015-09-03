/**
 * TaskInterceptorImpl.java
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
package com.mg.merp.scheduler.support;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.Logger;
import com.mg.framework.api.jms.MessageCreator;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.AbstractEntityInterceptor;
import com.mg.framework.utils.JmsUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.scheduler.model.Task;

/**
 * Перехватник действий модели "Задачи планировщика"
 * 
 * @author Oleg V. Safonov
 * @version $Id: TaskInterceptorImpl.java,v 1.1 2008/04/25 10:57:23 safonov Exp $
 */
public class TaskInterceptorImpl extends AbstractEntityInterceptor {
	private static String TASK_INTERCEPTOR = "SchedulerTaskInterceptor";
	private Logger logger = ServerUtils.getLogger(TaskInterceptorImpl.class);

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractEntityInterceptor#getHandledEntities()
	 */
	@Override
	public String[] getHandledEntities() {
		return new String[] {Task.class.getName()};
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractEntityInterceptor#getName()
	 */
	@Override
	public String getName() {
		return TASK_INTERCEPTOR;
	}

	class MessageCreatorImpl implements MessageCreator {
		private String newCode;
		private String oldCode;
		private int sysClientId;

		private MessageCreatorImpl(String newCode, String oldCode,
				int sysClientId) {
			super();
			this.newCode = newCode;
			this.oldCode = oldCode;
			this.sysClientId = sysClientId;
		}

		public Message create(Session session) throws JMSException {
			MapMessage result = session.createMapMessage();
			if (!StringUtils.stringNullOrEmpty(newCode))
				result.setString(TaskListenerBean.NEW_CODE_PARAM, newCode);
			if (!StringUtils.stringNullOrEmpty(oldCode))
				result.setString(TaskListenerBean.OLD_CODE_PARAM, oldCode);
			result.setInt(TaskListenerBean.SYS_CLIENT_PARAM, sysClientId);
			return result;
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractEntityInterceptor#onPostCommitPersist(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	public void onPostCommitPersist(PersistentObject entity) {
		Task task = (Task) entity;
		try {
			JmsUtils.sendObjectMessageToQueue("queue/com/mg/jet/schedulertask", new MessageCreatorImpl(task.getCode(), null, task.getSysClient().getId()));
		} catch (Exception e) {
			logger.error("send create task message failure", e);
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractEntityInterceptor#onPostCommitRemove(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	public void onPostCommitRemove(PersistentObject entity) {
		Task task = (Task) entity;
		try {
			JmsUtils.sendObjectMessageToQueue("queue/com/mg/jet/schedulertask", new MessageCreatorImpl(null, task.getCode(), task.getSysClient().getId()));
		} catch (Exception e) {
			logger.error("send remove task message failure", e);
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractEntityInterceptor#onPostCommitUpdate(com.mg.framework.api.orm.PersistentObject, com.mg.framework.api.AttributeMap)
	 */
	@Override
	public void onPostCommitUpdate(PersistentObject entity,
			AttributeMap oldState) {
		Task task = (Task) entity;
		try {
			JmsUtils.sendObjectMessageToQueue("queue/com/mg/jet/schedulertask", new MessageCreatorImpl(task.getCode(), (String) oldState.get("Code"), task.getSysClient().getId()));
		} catch (Exception e) {
			logger.error("send modify task message failure", e);
		}
	}

}
