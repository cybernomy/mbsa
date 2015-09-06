/*
 * OrderItemRollbackServiceBean.java
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
import com.mg.merp.humanresources.OrderItemRollbackServiceLocal;
import com.mg.merp.humanresources.model.OrderItemRollback;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента ""
 *
 * @author Artem V. Sharapov
 * @version $Id: OrderItemRollbackServiceBean.java,v 1.5 2007/08/27 13:34:01 sharapov Exp $
 */
@Stateless(name = "merp/humanresources/OrderItemRollbackService") //$NON-NLS-1$
public class OrderItemRollbackServiceBean extends AbstractPOJODataBusinessObjectServiceBean<OrderItemRollback, Integer> implements OrderItemRollbackServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onValidate(ValidationContext context, OrderItemRollback entity) {
    context.addRule(new MandatoryAttribute(entity, "OrderItem")); //$NON-NLS-1$
  }

}
