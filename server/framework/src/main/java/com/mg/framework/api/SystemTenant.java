/*
 * SystemTenant.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
 * A Client is a company or a legal entity. You cannot share data between Clients.
 * Tenant is a synonym for Client.
 * 
 * @author Oleg V. Safonov
 * @version $Id: SystemTenant.java,v 1.2 2009/02/18 12:18:19 safonov Exp $
 */
public interface SystemTenant {

	/**
	 * идентификатор манданта
	 * 
	 * @return	идентификатор
	 */
	int getIdentifier();

	/**
	 * код манданта
	 * 
	 * @return	код
	 */
	String getCode();
	
	/**
	 * имя манданта
	 * 
	 * @return	имя
	 */
	String getName();
	
	/**
	 * описание манданта
	 * 
	 * @return	описание
	 */
	String getDescription();
	
	/**
	 * основной язык манданта
	 * 
	 * @return	язык
	 */
	String getLanguage();
	
}
