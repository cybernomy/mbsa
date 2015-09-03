/*
 * MRPOutputsServiceLocal.java
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

import com.mg.merp.planning.model.MrpOutputs;
import com.mg.merp.planning.model.MrpVersionControl;

/**
 * Бизнес-компонент "Потребности товаров для расчета MRP"
 * 
 * @author Oleg V. Safonov
 * @author leonova
 * @version $Id: MRPOutputsServiceLocal.java,v 1.2 2007/07/30 10:37:51 safonov Exp $
 */
public interface MRPOutputsServiceLocal
		extends com.mg.framework.api.DataBusinessObjectService<MrpOutputs, Integer>
{
	/**
	 * имя сервиса
	 */
	static final String SERVICE_NAME = "merp/planning/MRPOutputs";
	
	/**
	 * очистить потребности товара
	 * 
	 * @param mrpVersion	версия ППМ
	 */
	void clear(MrpVersionControl mrpVersion);

}
