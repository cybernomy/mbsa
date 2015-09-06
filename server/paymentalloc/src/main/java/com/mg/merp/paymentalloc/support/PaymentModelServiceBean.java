/*
 * PaymentModelServiceBean.java
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

package com.mg.merp.paymentalloc.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.paymentalloc.PaymentModelServiceLocal;
import com.mg.merp.paymentalloc.model.Payment;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Образцы записей журнала платежей"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PaymentModelServiceBean.java,v 1.5 2007/05/30 12:29:56 sharapov Exp $
 */
@Stateless(name = "merp/paymentalloc/PaymentModelService") //$NON-NLS-1$
public class PaymentModelServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Payment, Integer> implements PaymentModelServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, Payment entity) {
    context.addRule(new MandatoryStringAttribute(entity, "ModelName")); //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onCreate(Payment entity) {
    adjustEntity(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onInitialize(Payment entity) {
    super.onInitialize(entity);
    adjustEntity(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onStore(Payment entity) {
    adjustEntity(entity);
  }

  private void adjustEntity(Payment entity) {
    entity.setIsModel(true);
  }

}
