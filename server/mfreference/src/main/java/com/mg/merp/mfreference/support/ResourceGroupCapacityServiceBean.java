/*
 * ResourceGroupCapacityServiceBean.java
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

package com.mg.merp.mfreference.support;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.mfreference.ResourceGroupCapacityServiceLocal;
import com.mg.merp.mfreference.model.ResourceGroupCapacity;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Емкость групп ресурсов"
 *
 * @author leonova
 * @version $Id: ResourceGroupCapacityServiceBean.java,v 1.3 2006/08/24 12:31:25 leonova Exp $
 */
@Stateless(name = "merp/mfreference/ResourceGroupCapacityService")
public class ResourceGroupCapacityServiceBean extends AbstractPOJODataBusinessObjectServiceBean<ResourceGroupCapacity, Integer> implements ResourceGroupCapacityServiceLocal {


}
