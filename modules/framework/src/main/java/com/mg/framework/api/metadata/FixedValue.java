/*
 * FixedValue.java
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
package com.mg.framework.api.metadata;


/**
 * Фиксированное значение
 * 
 * @author Oleg V. Safonov
 * @version $Id: FixedValue.java,v 1.2 2006/09/30 11:41:00 safonov Exp $
 */
public interface FixedValue<T> {
	/**
	 * Тип значения
	 */
    public enum Kind {
    	/**
    	 * Интервал значений
    	 */
    	INTERVAL,
    	/**
    	 * Единичное значение
    	 */
    	SINGLE
    }
    
    /**
     * получить тип фиксированного значения
     * 
     * @return	тип
     */
    Kind getKind();
    
    /**
     * Получить пояснение
     * 
     * @return
     */
    String getExplanatory();
    
    /**
     * получить минимальное значение, если тип {@link Kind#INTERVAL INTERVAL}
     * 
     * @return	минимальное значение
     */
    T getMinValue();
    
    /**
     * получить максимальное значение, если тип {@link Kind#INTERVAL INTERVAL}
     * 
     * @return	максимальное значение
     */
    T getMaxValue();
    
    /**
     * получить значение, если тип {@link Kind#SINGLE SINGLE}
     * 
     * @return	значение
     */
    T getValue();
}
