/*
 * CostDetailLineServiceLocal.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.mfreference;

import java.util.List;

import com.mg.merp.mfreference.model.CostDetail;
import com.mg.merp.mfreference.model.CostDetailLine;

/**
 * Бизнес-компонент "Строки расшифровки стоимости"
 * 
 * @author leonova
 * @version $Id: CostDetailLineServiceLocal.java,v 1.2 2007/07/30 10:25:31 safonov Exp $
 */
public interface CostDetailLineServiceLocal extends com.mg.framework.api.DataBusinessObjectService<CostDetailLine, Integer>
{
	/**
	 * имя сервиса
	 */
	static final String SERVICE_NAME = "merp/mfreference/CostDetailLine";

	/**
	 * расчет себестоимости
	 * 
	 * @param costDetail		расшифровка стоимости
	 * @param groupByCategories	группировать по категориям
	 * @return	список спецификаций себестоимости
	 */
	List<CostDetailLineItem> calculateCost(CostDetail costDetail, boolean groupByCategories);
	
	/**
	 * удаление расшифровок себестоимости
	 * 
	 * @param costDetail	расшифровка стоимости
	 */
	void clear(CostDetail costDetail);

}
