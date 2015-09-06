/*
 * InternalInvoiceSpecModelServiceBean.java
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

package com.mg.merp.account.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.account.InternalInvoiceSpecModelServiceLocal;
import com.mg.merp.account.model.InternalInvoiceSpecModel;
import com.mg.merp.reference.model.TimePeriodKind;

import java.math.BigDecimal;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Образцы спецификаций внутренних накладных"
 *
 * @author leonova
 * @version $Id: InternalInvoiceSpecModelServiceBean.java,v 1.4 2007/09/07 08:06:10 alikaev Exp $
 */
@Stateless(name = "merp/account/InternalInvoiceSpecModelService")
public class InternalInvoiceSpecModelServiceBean extends AbstractPOJODataBusinessObjectServiceBean<InternalInvoiceSpecModel, Integer> implements InternalInvoiceSpecModelServiceLocal {

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onValidate(ValidationContext context, InternalInvoiceSpecModel entity) {
    context.addRule(new MandatoryAttribute(entity, "Catalog")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "Measure1")); //$NON-NLS-1$
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onCreate(InternalInvoiceSpecModel entity) {
    adjustOrderSpecModelCus(entity);
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onStore(InternalInvoiceSpecModel entity) {
    adjustOrderSpecModelCus(entity);
  }

  /**
   * Заполняет обязательные поля
   */
  private void adjustOrderSpecModelCus(InternalInvoiceSpecModel entity) {
    if (entity.getShelfLife() == null)
      entity.setShelfLife(new BigDecimal(0));
    if (entity.getShelfLifeMeas() == null)
      entity.setShelfLifeMeas(TimePeriodKind.NONE);
  }

}
