/*
 * PhasePlanItemServiceLocal.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.contract;

import com.mg.merp.contract.model.PhasePlanItem;

/**
 * Бизнес-компонент "Пункты плана"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PhasePlanItemServiceLocal.java,v 1.2 2008/03/11 08:54:50 sharapov Exp $
 */
public interface PhasePlanItemServiceLocal extends com.mg.framework.api.DataBusinessObjectService<PhasePlanItem, Integer> {

	/**
	 * Имя сервиса
	 */
	static final String SERVICE_NAME = "merp/contract/PhasePlanItem"; //$NON-NLS-1$

	/**
	 * Рассчитать аттрибуты пункта плана контракта
	 * @param phasePlanItem - пункт плана
	 */
	void adjust(PhasePlanItem phasePlanItem);

}
