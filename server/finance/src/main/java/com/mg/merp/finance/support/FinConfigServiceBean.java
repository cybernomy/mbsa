/*
 * FinConfigServiceBean.java
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

package com.mg.merp.finance.support;

import javax.ejb.Stateless;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.finance.FinConfigServiceLocal;
import com.mg.merp.finance.model.FinConfig;

/**
 * Реализация бизнес-компонента "Конфигурация модуля <Фин.учет>"
 * 
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id: FinConfigServiceBean.java,v 1.6 2008/01/31 09:06:12 safonov Exp $
 */
@Stateless(name="merp/finance/FinConfigService") //$NON-NLS-1$
public class FinConfigServiceBean extends AbstractPOJODataBusinessObjectServiceBean<FinConfig, Integer> implements FinConfigServiceLocal {

}
