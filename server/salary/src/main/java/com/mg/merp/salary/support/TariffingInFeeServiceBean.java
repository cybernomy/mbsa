/*
 * TariffingInFeeServiceBean.java
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
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.salary.TariffingInFeeServiceLocal;
import com.mg.merp.salary.model.TariffingInFee;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Используемые тарифы"
 *
 * @author leonova
 * @version $Id: TariffingInFeeServiceBean.java,v 1.3 2006/08/31 11:37:58 leonova Exp $
 */
@Stateless(name = "merp/salary/TariffingInFeeService")
public class TariffingInFeeServiceBean extends AbstractPOJODataBusinessObjectServiceBean<TariffingInFee, Integer> implements TariffingInFeeServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, TariffingInFee entity) {
    context.addRule(new MandatoryAttribute(entity, "TariffingCategory"));
  }


}
