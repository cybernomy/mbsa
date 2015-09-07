/*
 * TaxRateServiceBean.java
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

package com.mg.merp.salary.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.salary.TaxRateServiceLocal;
import com.mg.merp.salary.model.TaxRate;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Ставки налоговых сеток"
 *
 * @author leonova
 * @version $Id: TaxRateServiceBean.java,v 1.3 2006/09/13 10:48:29 leonova Exp $
 */
@Stateless(name = "merp/salary/TaxRateService")
public class TaxRateServiceBean extends AbstractPOJODataBusinessObjectServiceBean<TaxRate, Integer> implements TaxRateServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, TaxRate entity) {
    context.addRule(new MandatoryStringAttribute(entity, "RNumber"));
  }


}
