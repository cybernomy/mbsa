/*
 * TurnFeatFlatServiceBean.java
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

import com.mg.merp.finance.TurnFeatFlatServiceLocal;
import com.mg.merp.finance.model.TurnFeature;

import javax.ejb.Stateless;

/**
 * @author leonova
 * @version $Id: TurnFeatFlatServiceBean.java,v 1.3 2006/10/23 12:12:53 leonova Exp $
 */
@Stateless(name = "merp/finance/TurnFeatFlatService")
public class TurnFeatFlatServiceBean extends com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean<TurnFeature, Integer> implements TurnFeatFlatServiceLocal {


}
