/*
 * MRPShortageServiceLocal.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.planning;

import com.mg.merp.planning.model.MrpShortage;
import com.mg.merp.planning.model.MrpVersionControl;

/**
 * Бизнес-компонент "Дефицит товаров по результатом MRP расчета"
 * 
 * @author leonova
 * @version $Id: MRPShortageServiceLocal.java,v 1.2 2007/07/30 10:37:51 safonov Exp $
 */
public interface MRPShortageServiceLocal
		extends com.mg.framework.api.DataBusinessObjectService<MrpShortage, Integer>
{
	/**
	 * имя сервиса
	 */
	static final String SERVICE_NAME = "merp/planning/MRPShortage";
	
	/**
	 * очистить дефицит товаров
	 * 
	 * @param mrpVersion	версия ППМ
	 */
	void clear(MrpVersionControl mrpVersion);
}
