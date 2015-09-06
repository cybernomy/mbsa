/*
 * TurnAccountServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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

package com.mg.merp.finance.support;

import com.mg.merp.finance.TurnAccountServiceLocal;
import com.mg.merp.finance.model.TurnAccount;

import javax.ejb.Stateless;


/**
 * @author Oleg V. Safonov
 * @version $Id: TurnAccountServiceBean.java,v 1.3 2006/10/23 12:08:15 leonova Exp $
 */
@Stateless(name = "merp/finance/TurnAccountService")
public class TurnAccountServiceBean extends com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean<TurnAccount, Integer> implements TurnAccountServiceLocal {


}
