/*
 * DiscountKindServiceBean.java
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

package com.mg.merp.salary.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.salary.DiscountKindServiceLocal;
import com.mg.merp.salary.model.DiscountKind;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Виды скидок и расходов"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: DiscountKindServiceBean.java,v 1.4 2007/07/18 12:56:13 sharapov Exp $
 */
@Stateless(name = "merp/salary/DiscountKindService") //$NON-NLS-1$
public class DiscountKindServiceBean extends AbstractPOJODataBusinessObjectServiceBean<DiscountKind, Integer> implements DiscountKindServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, DiscountKind entity) {
    context.addRule(new MandatoryAttribute(entity, "DCode")); //$NON-NLS-1$
    context.addRule(new MandatoryStringAttribute(entity, "DName")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "BeginDate")); //$NON-NLS-1$
  }

}
