/*
 * SessionControl.java
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
package com.mg.framework.api;

/**
 * Менеджер сессий
 * 
 * @author Oleg V. Safonov
 * @version $Id: SessionControl.java,v 1.3 2006/09/22 08:42:17 safonov Exp $
 */
public interface SessionControl {
	
	/**
	 * установить текущую сессию, текущий поток будет ассоциирован с сессией, если передан <code>null</code>
	 * то ассоциация будет снята 
	 * 
	 * @param session	сессия
	 */
	void setCurrentSession(Session session);
	
	/**
	 * получить текущую сессию
	 * 
	 * @return	текущая сессия или <code>null</code> если текущий поток не ассоциирован с сессией
	 */
	Session getCurrentSession();
	
	/**
	 * очистить менеджер сессий
	 *
	 */
	void clear();

}
