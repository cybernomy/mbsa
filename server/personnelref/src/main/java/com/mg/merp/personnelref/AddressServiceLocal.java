/*
 * AddressServiceLocal.java
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
package com.mg.merp.personnelref;

import com.mg.merp.personnelref.model.Address;

/**
 * Сервис бизнес-компонента "Адрес проживания сотрудников" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: AddressServiceLocal.java,v 1.2 2007/07/16 13:12:39 sharapov Exp $
 */
public interface AddressServiceLocal extends com.mg.framework.api.DataBusinessObjectService<Address, Integer> {

	/**
	 * Имя сервиса
	 */
	static final String LOCAL_SERVICE_NAME = "merp/personnelref/Address"; //$NON-NLS-1$
	
	static final String ADDRESS_SEPARATOR = ","; //$NON-NLS-1$

	/**
	 * Сформировать полный адрес
	 * @param address - адрес проживания
	 */
	void adjustFullAddress(Address address);

}
