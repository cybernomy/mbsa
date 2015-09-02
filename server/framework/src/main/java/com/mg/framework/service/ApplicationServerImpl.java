/**
 * ApplicationServerImpl.java
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
package com.mg.framework.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.TransactionManager;

import clime.messadmin.model.Application;
import clime.messadmin.model.ISessionInfo;
import clime.messadmin.model.Server;
import clime.messadmin.model.Session;

import com.mg.framework.api.ApplicationServer;
import com.mg.framework.api.Logger;
import com.mg.framework.api.UserSessionInfo;
import com.mg.framework.support.UserSessionInfoImpl;
import com.mg.framework.support.ui.ContainerContextFactory;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;

/**
 * Реализация сервиса управления прикладными функциями сервера приложения
 * 
 * @author Oleg V. Safonov
 * @version $Id: ApplicationServerImpl.java,v 1.2 2008/12/08 06:12:27 safonov Exp $
 */
public class ApplicationServerImpl implements ApplicationServer {
	private static Logger logger = ServerUtils.getLogger(ApplicationServerImpl.class);

	private static UserSessionInfo createUserSessionInfo(ISessionInfo sessionInfo, String currentHttpSessionId) {
		String userName = (String) sessionInfo.getGuessedUser();
		boolean isCurrentPrincipal = currentHttpSessionId.equals(sessionInfo.getId());
		Date lastAccessedTime = DateTimeUtils.nowDate();
		Date idleTime = new Date(0L);
		Date ttl = new Date(1000 * sessionInfo.getMaxInactiveInterval());
		if (!isCurrentPrincipal) {
			lastAccessedTime = new Date(sessionInfo.getLastAccessedTime());
			ttl = new Date(sessionInfo.getTTL());				
			idleTime = new Date(sessionInfo.getIdleTime());
		}
		return new UserSessionInfoImpl(
				userName,
				isCurrentPrincipal,
				new Date(sessionInfo.getCreationTime()),
				lastAccessedTime,
				new Date(sessionInfo.getTotalUsedTime()),
				idleTime,
				ttl,
				sessionInfo.getId(),
				sessionInfo.getRemoteHost(),
				sessionInfo.getLastUsedTime(),
				sessionInfo.getMinUsedTime(),
				sessionInfo.getMaxUsedTime(),
				sessionInfo.getHits(),
				0, //TODO
				sessionInfo.getRequestLastLength(),
				sessionInfo.getResponseLastLength(),
				sessionInfo.getRequestTotalLength(),
				sessionInfo.getResponseTotalLength());
	}
	
	private static Application getApplication() {
		return Server.getInstance().getApplication("/localhost/mbsaclient/");
	}
	
	private static String getCurrentHttpSessionId() {
		String currentHttpSessionId = StringUtils.EMPTY_STRING;
		if (ServerUtils.getCurrentSession() != null)
			try {
				currentHttpSessionId = ContainerContextFactory.getInstance().getDefaultContainerContext().getHttpSession().getId();
			} catch (Exception e) {
				logger.info("get current http session failed, ignored", e);
			}
		return currentHttpSessionId;
	}
	
	/**
	 * загрузка активных сессий пользователей, для реализации используется
	 * библиотека <a href="http://messadmin.sourceforge.net/">MessAdmin</a>
	 * 
	 * @return	информация об активных HTTP сессиях
	 */
	@SuppressWarnings("unchecked")
	private Set<UserSessionInfo> loadActiveUser() {
		Set<UserSessionInfo> result = new HashSet<UserSessionInfo>();
		String currentHttpSessionId = getCurrentHttpSessionId();
		Application application = getApplication();
		if (application == null) //такого не должно быть
			return result;
		Set<ISessionInfo> sessionInfos = application.getActiveSessionInfos();
		for (ISessionInfo sessionInfo : sessionInfos) {
			//если есть пользователь, то значит активный
			if (sessionInfo.getGuessedUser() instanceof String)
				result.add(createUserSessionInfo(sessionInfo, currentHttpSessionId));
		}
		return result;
	}

	protected UserSessionInfo doGetUserSessionInfo(String httpSessionId) throws Exception {
		String currentHttpSessionId = getCurrentHttpSessionId();
		Application application = getApplication();
		if (application == null) //такого не должно быть
			return null;
		
		Session session = application.getSession(httpSessionId);
		if (session == null)
			return null;
		
		return createUserSessionInfo(session.getSessionInfo(), currentHttpSessionId);
	}

	protected Set<UserSessionInfo> doGetUserSessionInfos() {
		/*Set<UserSessionInfo> result = new HashSet<UserSessionInfo>();
		//load all users
		List<String> usersName = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria("com.mg.merp.security.model.SecUser")
				.setProjection(Projections.property("Name")));
		
		//remove spaces
		String[] userNamesArr = usersName.toArray(new String[usersName.size()]);
		usersName.clear();
		for (String name : userNamesArr)
			usersName.add(name.trim());
		
		//remove active users
		result = loadActiveUser();
		for (UserSessionInfo activeUser : result)
			usersName.remove(activeUser.getUserName());
		
		//add inactive users
		for (String userName : usersName)
			result.add(new UserSessionInfoImpl(userName));
		return result;*/
		return loadActiveUser();
	}
	
	protected void doSendAdminMessage(String[] sessionIds, String message) {
		AdminMessageSender.getInstance().sendMessage(sessionIds, message);
	}
	
	protected void doInvalidateUserSessions(final String[] sessionIds) {
		final String currentHttpSessionId = getCurrentHttpSessionId();
		final Application application = getApplication();
		if (application == null) //такого не должно быть
			return;

		//используем поток, т.к. вызов может происходить из приложения администратора, тогда
		//текущая сессия приложения будет администратора и для нее будет выполнена команда logout,
		//однако http сессия будет та, которую и собирались завершить и в рамках веб сервера именно
		//она будет завершена
		new Thread(new Runnable() {

			public void run() {
				for (String httpSessionId : sessionIds) {
					//prevent NPE and self-destruction
					if (httpSessionId == null || currentHttpSessionId.equals(httpSessionId))
						continue;
					
					Session session = application.getSession(httpSessionId);
					if (session == null)
						continue;
					
					try {
						session.getSessionInfo().invalidate();
					} catch (IllegalStateException e) {
						logger.info("session already invalidated, id: " + httpSessionId, e);
					}
				}
			}
			
		}).start();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ApplicationServer#getUserSessionInfos()
	 */
	public Set<UserSessionInfo> loadUserSessionInfos() throws Exception {
		TransactionManager tm = ServerUtils.getTransactionManager();
		boolean startTran = tm.getTransaction() == null;
		if (startTran) {
			logger.debug("Start transaction");
			tm.begin();
		}
		
		try {
			return doGetUserSessionInfos();
		} finally {
			if (startTran) {
				logger.debug("Commit transaction");
				tm.commit();
			}
		}		
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ApplicationServer#loadUserSessionInfo(java.lang.String)
	 */
	public UserSessionInfo loadUserSessionInfo(String httpSessionId)
			throws Exception {
		return doGetUserSessionInfo(httpSessionId);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ApplicationServer#sendAdminMessage(java.lang.String, java.lang.String)
	 */
	public void sendAdminMessage(String sessionIds, String message) throws Exception {
		if (sessionIds == null)
			throw new IllegalArgumentException("sessionIds is null");

		doSendAdminMessage(sessionIds.split(","), message);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ApplicationServer#sendAdminMessage(java.lang.String[], java.lang.String)
	 */
	public void sendAdminMessage(String[] sessionIds, String message) throws Exception {
		if (sessionIds == null)
			throw new IllegalArgumentException("sessionIds is null");
		
		doSendAdminMessage(sessionIds, message);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ApplicationServer#invalidateUserSessions(java.lang.String[])
	 */
	public void invalidateUserSessions(String[] sessionIds) throws Exception {
		if (sessionIds == null)
			throw new IllegalArgumentException("sessionIds is null");
		
		doInvalidateUserSessions(sessionIds);
	}

}
