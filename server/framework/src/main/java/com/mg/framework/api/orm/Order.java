/*
 * Order.java
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
package com.mg.framework.api.orm;

import java.io.Serializable;

/**
 * Фабрика создания сортировки запроса
 * 
 * @author Oleg V. Safonov
 * @version $Id: Order.java,v 1.2 2006/12/12 13:54:52 safonov Exp $
 */
public class Order implements Serializable {
	private String propertyName;
	private boolean ascending;
	private boolean ignoreCase;
	
	private Order(String propertyName, boolean ascending) {
		super();
		this.propertyName = propertyName;
		this.ascending = ascending;
	}

	/**
	 * Ascending order
	 * 
	 * @param propertyName
	 * @return
	 */
    public static Order asc(String propertyName) {
        return new Order(propertyName, true);
    }
    
    /**
     * Descending order
     * 
     * @param propertyName
     * @return
     */
    public static Order desc(String propertyName) {
        return new Order(propertyName, false);
    }

    public Order ignoreCase() {
    	ignoreCase = true;
    	return this;
    }

	/**
	 * @return the ascending
	 */
	public boolean isAscending() {
		return ascending;
	}

	/**
	 * @return the ignoreCase
	 */
	public boolean isIgnoreCase() {
		return ignoreCase;
	}

	/**
	 * @return the propertyName
	 */
	public String getPropertyName() {
		return propertyName;
	}
    
}
