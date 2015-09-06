/*
 * OrderItemParamServiceBean.java
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

package com.mg.merp.humanresources.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.humanresources.OrderItemParamServiceLocal;
import com.mg.merp.humanresources.model.OrderItemParam;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Параметр вида пункта приказа"
 *
 * @author Artem V. Sharapov
 * @version $Id: OrderItemParamServiceBean.java,v 1.5 2007/08/27 13:34:01 sharapov Exp $
 */
@Stateless(name = "merp/humanresources/OrderItemParamService") //$NON-NLS-1$
public class OrderItemParamServiceBean extends AbstractPOJODataBusinessObjectServiceBean<OrderItemParam, Integer> implements OrderItemParamServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onValidate(ValidationContext context, OrderItemParam entity) {
    context.addRule(new MandatoryAttribute(entity, "OrderItem")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "ItemKindParam")); //$NON-NLS-1$
  }

}
