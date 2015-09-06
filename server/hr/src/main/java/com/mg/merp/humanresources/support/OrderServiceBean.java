/*
 * OrderServiceBean.java
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
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.humanresources.OrderServiceLocal;
import com.mg.merp.humanresources.model.Order;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Приказы"
 *
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id: OrderServiceBean.java,v 1.6 2007/08/27 13:34:01 sharapov Exp $
 */
@Stateless(name = "merp/humanresources/OrderService") //$NON-NLS-1$
public class OrderServiceBean extends com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean<Order, Integer> implements OrderServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onValidate(ValidationContext context, Order entity) {
    context.addRule(new MandatoryAttribute(entity, "Folder")); //$NON-NLS-1$
    context.addRule(new MandatoryStringAttribute(entity, "OrderNumber")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "OrderDate")); //$NON-NLS-1$
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.hr.OrderServiceLocal#prepareForSign(long)
   */
  public boolean prepareForSign(long orderId) {
    //TODO: implement
    return false;
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.hr.OrderServiceLocal#signOrder(long)
   */
  public boolean signOrder(long orderId) {
    //TODO: implement
    return false;
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.hr.OrderServiceLocal#processOrder(long)
   */
  public boolean processOrder(long orderId) {
    //TODO: implement
    return false;
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.hr.OrderServiceLocal#rollbackOrder(long)
   */
  public boolean rollbackOrder(long orderId) {
    //TODO: implement
    return false;
  }

}
