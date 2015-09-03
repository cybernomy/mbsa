/*
 * ConfigurationHelper.java
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
package com.mg.merp.mfreference.support;

import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.document.Configuration;
import com.mg.merp.document.support.ConfigurationImpl;
import com.mg.merp.mfreference.model.ManufactureConfig;

/**
 * Класс помощник конфигурации модуля "Производство"
 * 
 * @author Oleg V. Safonov
 * @version $Id: ConfigurationHelper.java,v 1.1 2007/03/26 10:40:02 safonov Exp $
 */
public class ConfigurationHelper {

	/**
	 * получить конфигурацию модуля
	 * 
	 * @return
	 */
	public static ManufactureConfig getConfiguration() {
		return ServerUtils.getPersistentManager().find(ManufactureConfig.class, ((SysClient) ServerUtils.getCurrentSession().getSystemTenant()).getId());
	}

	/**
	 * получить конфигурацию документов данного модуля
	 * 
	 * @return
	 */
	public static Configuration getDocumentConfiguration() {
		ManufactureConfig cfg = getConfiguration();
		return new ConfigurationImpl(cfg.getBaseCurrency(), cfg.getNatCurrency(), cfg.getCurrencyPrec(), cfg.getCurrencyRateAuthority(), cfg.getCurrencyRateType());
	}

}
