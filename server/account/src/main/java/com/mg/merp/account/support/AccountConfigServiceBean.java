/*
 * AccountConfigServiceBean.java
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

package com.mg.merp.account.support;

import javax.ejb.Stateless;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.account.AccountConfigServiceLocal;
import com.mg.merp.account.model.AccConfig;

/**
 * Реализация бизнес-компонента "Конфигурация модуля <Бух.учет>"
 * 
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id: AccountConfigServiceBean.java,v 1.5 2007/01/13 13:11:17 sharapov Exp $
 */
@Stateless(name="merp/account/AccountConfigService") //$NON-NLS-1$
public class AccountConfigServiceBean extends AbstractPOJODataBusinessObjectServiceBean<AccConfig, Integer> implements AccountConfigServiceLocal {

}
