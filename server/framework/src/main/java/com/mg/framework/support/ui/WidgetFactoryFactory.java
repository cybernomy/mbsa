/*
 * WidgetFactoryFactory.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.framework.support.ui;

import com.mg.framework.api.ui.WidgetFactory;
import com.mg.framework.support.ui.ulc.ULCWidgetFactory;

/**
 * ‘абрика фабрики элементов пользовательского интерфейса
 * 
 * @author Oleg V. Safonov
 * @version $Id: WidgetFactoryFactory.java,v 1.2 2006/11/11 10:38:12 safonov Exp $
 */
public class WidgetFactoryFactory {
	private static WidgetFactoryFactory instance = new WidgetFactoryFactory();
	private WidgetFactory defaultFactory = new ULCWidgetFactory();
	
	/**
	 * получить объект одиночку
	 * 
	 * @return	экземпл€р фабрики
	 */
	public static WidgetFactoryFactory getInstance() {
		return instance;
	}
	
	/**
	 * получить фабрику элементов пользовательского интерфейса
	 * 
	 * @return	фабрика элементов пользовательского интерфейса
	 */
	public WidgetFactory getDefaultWidgetFactory() {
		return defaultFactory;
	}
}
