/*
 * WarehouseConfigServiceBean.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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

package com.mg.merp.warehouse.support;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.warehouse.WarehouseConfigServiceLocal;
import com.mg.merp.warehouse.model.WarehouseConfig;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Конфигурация модуля <Склады, снабжение, сбыт>"
 *
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id: WarehouseConfigServiceBean.java,v 1.6 2007/01/13 13:35:22 sharapov Exp $
 */
@Stateless(name = "merp/warehouse/WarehouseConfigService") //$NON-NLS-1$
public class WarehouseConfigServiceBean extends AbstractPOJODataBusinessObjectServiceBean<WarehouseConfig, Integer> implements WarehouseConfigServiceLocal {

//	public Config load() throws ApplicationException {
//		//return ((WarehouseConfigDomainImpl) getDomain()).load();
//		return null;
//	}
//
//	public void store(Config conf) throws ApplicationException {
//		//((WarehouseConfigDomainImpl) getDomain()).store(conf);
//	}

}
