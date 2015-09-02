/*
 * BOMRouteResourceServiceBean.java
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

package com.mg.merp.mfreference.support;

import javax.ejb.Stateless;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.mfreference.BOMRouteResourceServiceLocal;
import com.mg.merp.mfreference.model.BomRouteResource;

/**
 * Бизнес-компонент "Ресурсы" 
 * 
 * @author leonova
 * @version $Id: BOMRouteResourceServiceBean.java,v 1.3 2006/09/07 10:57:04 leonova Exp $
 */
@Stateless(name="merp/mfreference/BOMRouteResourceService")
public class BOMRouteResourceServiceBean extends AbstractPOJODataBusinessObjectServiceBean<BomRouteResource, Integer> implements BOMRouteResourceServiceLocal {



}
