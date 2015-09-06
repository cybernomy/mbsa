/*
 * EconomicSpecModelServiceBean.java
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

package com.mg.merp.account.support;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.account.EconomicSpecModelServiceLocal;
import com.mg.merp.account.model.EconomicSpecModel;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Спецификация образцов хозяйственных операций"
 *
 * @author leonova
 * @version $Id: EconomicSpecModelServiceBean.java,v 1.3 2006/09/13 12:54:44 leonova Exp $
 */
@Stateless(name = "merp/account/EconomicSpecModelService")
public class EconomicSpecModelServiceBean extends AbstractPOJODataBusinessObjectServiceBean<EconomicSpecModel, Integer> implements EconomicSpecModelServiceLocal {


}
