/*
 * MRPInputsServiceLocal.java
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

import com.mg.merp.planning.model.MrpInputs;
import com.mg.merp.planning.model.MrpVersionControl;

/**
 * Бизнес-компонент "Поступление товаров для расчета ППМ"
 * 
 * @author Oleg V. Safonov
 * @author leonova
 * @version $Id: MRPInputsServiceLocal.java,v 1.2 2007/07/30 10:37:51 safonov Exp $
 */
public interface MRPInputsServiceLocal
		extends com.mg.framework.api.DataBusinessObjectService<MrpInputs, Integer>
{
	/**
	 * имя сервиса
	 */
	static final String SERVICE_NAME = "merp/planning/MRPInputs";
	
	/**
	 * очистить поступления товара
	 * 
	 * @param mrpVersion	версия ППМ
	 */
	void clear(MrpVersionControl mrpVersion);
	
}
