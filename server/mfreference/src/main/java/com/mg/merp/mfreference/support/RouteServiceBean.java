/*
 * RouteServiceBean.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.mfreference.RouteServiceLocal;
import com.mg.merp.mfreference.model.Route;
import com.mg.merp.mfreference.model.RouteDestType;
import com.mg.merp.mfreference.model.RouteSrcType;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Логистические маршруты"
 *
 * @author leonova
 * @version $Id: RouteServiceBean.java,v 1.5 2007/08/23 14:37:51 alikaev Exp $
 */
@Stateless(name = "merp/mfreference/RouteService") //$NON-NLS-1$
public class RouteServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Route, Integer> implements RouteServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, Route entity) {
    context.addRule(new MandatoryAttribute(entity, "Catalog")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "DestWarehouse"));         //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "SrcType")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "DestType")); //$NON-NLS-1$
    if (entity.getDestType() == RouteDestType.SALE)
      context.addRule(new MandatoryAttribute(entity, "Customer")); //$NON-NLS-1$
    if (entity.getSrcType() == RouteSrcType.TRANSFER)
      context.addRule(new MandatoryAttribute(entity, "SrcWarehouse")); //$NON-NLS-1$
    if (entity.getSrcType() == RouteSrcType.PURCHASE)
      context.addRule(new MandatoryAttribute(entity, "Vendor")); //$NON-NLS-1$
  }

}
