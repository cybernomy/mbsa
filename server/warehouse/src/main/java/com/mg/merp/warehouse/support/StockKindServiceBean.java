/*
 * StockkindServiceBean.java
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

package com.mg.merp.warehouse.support;

import javax.ejb.Stateless;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.warehouse.StockKindServiceLocal;
import com.mg.merp.warehouse.model.StockKind;

/**
 * Бизнес-компонент "Вид запаса" 
 * 
 * @author leonova
 * @version $Id: StockKindServiceBean.java,v 1.4 2006/09/14 12:48:21 leonova Exp $
 */
@Stateless(name="merp/warehouse/StockKindService")
public class StockKindServiceBean extends AbstractPOJODataBusinessObjectServiceBean<StockKind, Integer> implements StockKindServiceLocal {


}
