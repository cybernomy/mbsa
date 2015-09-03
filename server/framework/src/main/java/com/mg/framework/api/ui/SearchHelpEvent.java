/*
 * SearchHelpEvent.java
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
package com.mg.framework.api.ui;

import com.mg.framework.api.orm.PersistentObject;

/**
 * Событие выполнения поиска сущностей
 * 
 * @see com.mg.framework.api.ui.SearchHelpListener
 * 
 * @author Oleg V. Safonov
 * @version $Id: SearchHelpEvent.java,v 1.3 2006/07/04 07:03:13 safonov Exp $
 */
public class SearchHelpEvent extends FormEvent {
	private PersistentObject[] items;
	
	/**
	 * конструктор
	 * 
	 * @param source	источник в котором произошло событие
	 * @param items		найденные сущности
	 */
	public SearchHelpEvent(Form source, PersistentObject[] items) {
		super(source);
		this.items = items;
	}
	
	/**
	 * получить список найденных сущностей
	 * 
	 * @return	найденные сущности
	 */
	public PersistentObject[] getItems() {
		return items;
	}

}
