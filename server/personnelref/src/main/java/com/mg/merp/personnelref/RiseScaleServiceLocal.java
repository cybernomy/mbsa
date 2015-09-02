/*
 * RiseScaleServiceLocal.java
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

import com.mg.merp.personnelref.model.RiseScale;

/**
 * Сервис бизнес-компонента "Шкала надбавки"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: RiseScaleServiceLocal.java,v 1.2 2007/07/09 07:40:01 sharapov Exp $
 */
public interface RiseScaleServiceLocal extends com.mg.framework.api.DataBusinessObjectService<RiseScale, Integer> {
	
	/**
	 * локальное имя сервиса
	 */
	static final String LOCAL_SERVICE_NAME = "merp/personnelref/RiseScale"; //$NON-NLS-1$
	
}
