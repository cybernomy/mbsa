/*
 * ConfigServiceBean.java
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

package com.mg.merp.settlement.support;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.settlement.ConfigServiceLocal;
import com.mg.merp.settlement.model.StlConfig;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Конфигурация модуля <Расчеты с партнерами>"
 *
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id: ConfigServiceBean.java,v 1.1 2007/03/19 14:55:34 sharapov Exp $
 */
@Stateless(name = "merp/settlement/SettlementConfigService") //$NON-NLS-1$
public class ConfigServiceBean extends AbstractPOJODataBusinessObjectServiceBean<StlConfig, Integer> implements ConfigServiceLocal {

}
