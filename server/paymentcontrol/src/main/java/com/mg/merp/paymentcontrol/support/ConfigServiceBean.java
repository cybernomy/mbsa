/*
 * ConfigServiceBean.java
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

package com.mg.merp.paymentcontrol.support;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.paymentcontrol.ConfigServiceLocal;
import com.mg.merp.paymentcontrol.model.PmcConfig;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Конфигурация модуля <Платежный календарь>"
 *
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id: ConfigServiceBean.java,v 1.5 2007/01/13 13:28:40 sharapov Exp $
 */
@Stateless(name = "merp/paymentcontrol/ConfigService") //$NON-NLS-1$
public class ConfigServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PmcConfig, Integer> implements ConfigServiceLocal {

}
