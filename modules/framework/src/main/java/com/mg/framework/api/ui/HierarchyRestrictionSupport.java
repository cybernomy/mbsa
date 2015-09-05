/*
 * HierarchyRestrictionSupport.java
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
package com.mg.framework.api.ui;

/**
 * Интерфейс указывающий на поддержку ограничений с иерархической структурой
 * 
 * @author Oleg V. Safonov
 * @version $Id: HierarchyRestrictionSupport.java,v 1.1 2006/08/02 13:51:05 safonov Exp $
 */
public interface HierarchyRestrictionSupport {

	/**
	 * признак отбора сущностей по иерархии
	 * 
	 * @return возвращает <code>true</code> если требуется отбирать сущности по иерархии
	 */
	boolean isUseHierarchy();

}
