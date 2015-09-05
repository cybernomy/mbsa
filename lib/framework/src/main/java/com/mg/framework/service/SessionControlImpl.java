/*
 * SessionControlImpl.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.mg.framework.api.Session;
import com.mg.framework.api.SessionControl;

/**
 * Реализация менеджера сессий
 * 
 * @author Oleg V. Safonov
 * @version $Id: SessionControlImpl.java,v 1.4 2006/09/22 08:42:45 safonov Exp $
 */
public class SessionControlImpl implements SessionControl {
	private static SessionControl singleton = new SessionControlImpl(); 
	private static Map<Integer, Session> sessionMap = Collections.synchronizedMap(new HashMap<Integer, Session>());
	private static ThreadLocal<Session> tl = new ThreadLocal<Session>();

	/**
	 * коструктор
	 */
	private SessionControlImpl() {
		super();
	}
	
	/**
	 * получить экземпляр менеджера
	 * 
	 * @return	менеджер
	 */
	public static SessionControl getSingleton() {
		return singleton;
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.SessionControl#setCurrentSession(com.mg.framework.api.Session)
	 */
	public void setCurrentSession(Session session) {
		tl.set(session);
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.SessionControl#getCurrentSession()
	 */
	public Session getCurrentSession() {
		Session session = tl.get();
		if (session != null)
			return session;
		else
			return null;
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.SessionControl#clear()
	 */
	public void clear() {
		synchronized (sessionMap) {
			for (Session session : sessionMap.values())
				session.destroy();
			sessionMap.clear();
		}
	}
	
}
