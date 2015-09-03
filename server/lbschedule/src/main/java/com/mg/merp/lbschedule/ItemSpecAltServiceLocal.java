/*
 * ItemSpecAltServiceLocal.java
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
package com.mg.merp.lbschedule;

import com.mg.merp.lbschedule.model.ItemSpecAlt;

/**
 * Сервис бизнес-компонента "Возможные замены позиции спецификации пункта графика исполнения обязательств"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ItemSpecAltServiceLocal.java,v 1.2 2007/04/17 12:48:40 sharapov Exp $
 */
public interface ItemSpecAltServiceLocal extends com.mg.framework.api.DataBusinessObjectService<ItemSpecAlt, Integer> {

	/**
	 * Локальное имя сервиса
	 */
	static final String LOCAL_SERVICE_NAME = "merp/lbschedule/ItemSpecAlt"; //$NON-NLS-1$

}
