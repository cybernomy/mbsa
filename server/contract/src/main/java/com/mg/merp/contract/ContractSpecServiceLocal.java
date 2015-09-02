/*
 * ContractSpecServiceLocal.java
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

import com.mg.merp.contract.model.ContractSpec;

/**
 * Бизнес-компонент "Спецификация контракта"
 * 
 * @author leonova
 * @author Artem V Sharapov
 * @version $Id: ContractSpecServiceLocal.java,v 1.2 2008/03/11 08:53:11 sharapov Exp $
 */
public interface ContractSpecServiceLocal extends com.mg.framework.api.DataBusinessObjectService<ContractSpec, Integer> {

	/**
	 * Имя сервиса
	 */
	static final String SERVICE_NAME = "merp/contract/ContractSpec"; //$NON-NLS-1$

	/**
	 * Рассчитать аттрибуты позиции спецификации контракта
	 * @param contractSpec - позиция спецификации контракта
	 */
	void adjust(ContractSpec contractSpec);	

}
