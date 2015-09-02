/*
 * UserProfile.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.framework.api;

import java.net.URL;
import java.util.Locale;

/**
 * ѕрофайл пользовател€
 * 
 * @author Oleg V. Safonov
 * @version $Id: UserProfile.java,v 1.7 2007/04/13 14:09:57 safonov Exp $
 */
public interface UserProfile {
	
	/**
	 * Ћокаль пользовател€
	 * 
	 * @return локаль
	 */
	Locale getLocale();
	
	/**
	 * идентификатор пользовател€
	 * 
	 * @return идентификатор
	 */
	int getIdentificator();
	
	/**
	 * логин пользовател€, им€ с которым пользователь произвел вход в систему
	 * 
	 * @return логин
	 */
	String getUserName();
	
	/**
	 * получить список идентификаторов ролей в которые входит пользователь
	 * 
	 * @return	идентификаторы ролей
	 */
	int[] getGroups();
	
	/**
	 * получить список идентификаторов ролей в которые входит пользователь представленный строкой
	 * 
	 * @return	идентификаторы ролей
	 */
	String getGroupsCommaText();
	
	/**
	 * получить список прикладных модулей системы используемых пользователем
	 * 
	 * @return	список модулей
	 */
	String[] getPermittableSubsystems();
	
	/**
	 * получить главное меню пользовател€
	 * 
	 * @return	главное меню
	 */
	URL getGlobalMenu();
}
