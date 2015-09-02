/*
 * ClientContextFactory.java
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
package com.mg.framework.support.ui;

import com.mg.framework.api.ui.ClientContext;
import com.mg.framework.support.ui.ulc.ULCClientContext;

/**
 * ‘абрика среды пользовательского интерфейса
 * 
 * @author Oleg V. Safonov
 * @version $Id: ClientContextFactory.java,v 1.1 2006/11/11 10:36:20 safonov Exp $
 */
public class ClientContextFactory {
	private static ClientContextFactory instance = new ClientContextFactory();
	private ClientContext defaultClientContext = new ULCClientContext();
	
	/**
	 * получить объект-одиночку
	 * 
	 * @return	экземпл€р фабрики
	 */
	public static ClientContextFactory getInstance() {
		return instance;
	}
	
	/**
	 * получить среду пользовательского интерфейса
	 * 
	 * @return	среда пользовательского интерфейса
	 */
	public ClientContext getDefaultClientContext() {
		return defaultClientContext;
	}
	
}
