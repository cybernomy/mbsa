/*
 * PersonAddressServiceLocal.java
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

import java.util.Date;

import com.mg.merp.reference.model.NaturalPerson;
import com.mg.merp.reference.model.PersonAddress;

/**
 * Сервис бизнес-компонента "Адреса проживания" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PersonAddressServiceLocal.java,v 1.3 2007/09/05 10:42:07 alikaev Exp $
 */
public interface PersonAddressServiceLocal extends com.mg.framework.api.DataBusinessObjectService<PersonAddress, Integer> {

	/**
	 * Локальное имя сервиса
	 */
	static final String LOCAL_SERVICE_NAME = "merp/reference/PersonAddress"; //$NON-NLS-1$
	
	/**
	 * Получить адрес физ.лица на дату
	 * @param naturalPerson - физ.лицо
	 * @param date - дата актуальности адреса
	 * @return адрес физ.лица
	 */
	PersonAddress getActualPersonAdress(NaturalPerson naturalPerson, Date date);
	
	/**
	 * Формирует полный адрес
	 * @param personAddress адрес проживания физического лица
	 */
	void getFullAddress(PersonAddress personAddress); 
	
}
