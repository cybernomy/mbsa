/*
 * Contractor.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.reference;


/**
 * Базовый интерфейс бизнес-компонентов "Контрагенты"
 * 
 * @author Oleg V. Safonov
 * @version $Id: Contractor.java,v 1.2 2007/08/16 14:14:04 safonov Exp $
 */
public interface Contractor<T extends com.mg.merp.reference.model.Contractor> extends com.mg.framework.api.DataBusinessObjectService<T, Integer> {
	
	/**
	 * поиск контрагента по коду
	 * 
	 * @param code
	 * @return
	 */
	T findFromCode(String code);

}
