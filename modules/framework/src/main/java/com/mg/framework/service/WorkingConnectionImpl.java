/*
 * WorkingConnectionImpl.java
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

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.Locale;
import java.util.Map;

import javax.ejb.NoSuchObjectLocalException;
import javax.security.auth.login.LoginException;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.Logger;
import com.mg.framework.api.Session;
import com.mg.framework.api.UserProfile;
import com.mg.framework.api.WorkingConnection;
import com.mg.framework.api.security.InvalidUserNameOrPassword;
import com.mg.framework.support.Messages;
import com.mg.framework.utils.SecurityUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;

/**
 * Реализация рабочего соединения
 * 
 * @author Oleg V. Safonov
 * @version $Id: WorkingConnectionImpl.java,v 1.20 2008/08/28 16:49:25 safonov Exp $
 *
 */
public final class WorkingConnectionImpl
	implements WorkingConnection, Serializable {
	private Logger logger = ServerUtils.getLogger(WorkingConnection.class);
    private SessionPinger sessionPinger;
	private UserProfile userProfile;
	private int databaseEngine = -1;

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public WorkingConnectionImpl(String sessionImpl) throws Exception {
		super();
		Session session = null;
		if (StringUtils.stringNullOrEmpty(sessionImpl))
			session = new com.mg.framework.service.SessionImpl(this);
		else {
			Class<Session> clazz = (Class<Session>) ServerUtils.loadClass(sessionImpl);
			Constructor<Session> constructor = clazz.getConstructor(WorkingConnection.class);
			session = constructor.newInstance(this);
		}
		SessionControlImpl.getSingleton().setCurrentSession(session);
	}

	protected void ping() throws ApplicationException {
	    if (sessionPinger == null)
	        throw new IllegalStateException("Invalid session pinger");

	    try {
		    sessionPinger.ping();
	    }
	    catch (NoSuchObjectLocalException e) {
	        throw new ApplicationException(Messages.getInstance().getMessage(Messages.SESSION_EXPIRED, "Session expired"));
	    }
	}

	public void login(Map<String, Object> params) throws InvalidUserNameOrPassword, LoginException {
		SecurityUtils.authenticate((String) params.get(LOGIN_PARAM), (String) params.get(PASSW_PARAM));
		//TODO здесь должны выполнить проверку специфичных для приложения прав, например не истек ли срок действия логина и т.п.

		userProfile = SecuritySystemLocator.locate().loadUserProfile((String) params.get(LOGIN_PARAM), (Locale) params.get(LOCALE_PARAM), (Locale) params.get(DEFAULT_LOCALE_PARAM));
		
		LicenseControllerLocator.locate().occupyLicense(ServerUtils.getCurrentSession());
	}

	public void logout() {
	    LicenseControllerLocator.locate().freeLicense(ServerUtils.getCurrentSession());
	    try {
			ServerUtils.getPersistentManager().flush();
		} catch (Throwable th) {
			logger.error("logout process, persistent manager flush failed, ignored", th);
		}
    	SecurityUtils.logout();
	    SessionControlImpl.getSingleton().setCurrentSession(null);
	}
	
	public UserProfile getUserProfile() {
		return userProfile;
	}

	@SuppressWarnings("deprecation")
	public synchronized int DBMSEngine() {
		if (databaseEngine == -1) {
			try {
				java.sql.Connection conn = ServerUtils.getConnection();
				String dbName;
				try {
					dbName = conn.getMetaData().getDatabaseProductName().toUpperCase();
				}
				finally {
					conn.close();
				}
				if (dbName.startsWith("INTERBASE") || dbName.startsWith("FIREBIRD"))
					databaseEngine = WorkingConnection.DBMS_INTERBASE;
				else if (dbName.equals("ORACLE"))
					databaseEngine = WorkingConnection.DBMS_ORACLE;
				else
					throw new Exception("Unknown Database");
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return databaseEngine;
	}

}
