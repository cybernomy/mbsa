/*
 * RegionServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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

package com.mg.merp.reference.support;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.reference.RegionServiceLocal;
import com.mg.merp.reference.model.Region;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Регионы"
 *
 * @author leonova
 * @version $Id: RegionServiceBean.java,v 1.3 2006/08/03 10:36:26 leonova Exp $
 */
@Stateless(name = "merp/reference/RegionService")
public class RegionServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Region, Integer> implements RegionServiceLocal {


}
