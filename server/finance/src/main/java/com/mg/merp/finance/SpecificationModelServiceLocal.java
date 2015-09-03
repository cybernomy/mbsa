/*
 * SpecificationModelServiceLocal.java
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
package com.mg.merp.finance;

import com.mg.merp.finance.model.SpecificationModel;

/**
 * Бизнес-компонент "Спецификация образцов финансовых операций"
 * 
 * @author leonova
 * @version $Id: SpecificationModelServiceLocal.java,v 1.2 2007/10/08 14:24:15 safonov Exp $
 */
public interface SpecificationModelServiceLocal
		extends com.mg.framework.api.DataBusinessObjectService<SpecificationModel, Integer> {
	/**
	 * имя сервиса
	 */
	final static String SERVICE_NAME = "merp/finance/SpecificationModel";

}
