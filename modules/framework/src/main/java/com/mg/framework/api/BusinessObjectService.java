/*
 * BusinessObjectService.java
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
package com.mg.framework.api;

import javax.ejb.Remove;

import com.mg.framework.api.metadata.BusinessServiceMetadata;

/**
 * Бизнес компонент
 * 
 * @author Oleg V. Safonov
 * @version $Id: BusinessObjectService.java,v 1.5 2006/09/28 12:24:12 safonov Exp $
 * 
 */
public interface BusinessObjectService {
	
	/**
	 * Получение метаданных бизнес компонента
	 * 
	 * @return	метаданные
	 */
	BusinessServiceMetadata getBusinessServiceMetadata();
	
	/**
	 * Удаляет сервис, используется только для Stateful сервисов
	 *
	 */
	@Remove
	public void destroy();

}
