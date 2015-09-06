/*
 * LaborClassServiceBean.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.mfreference.LaborClassServiceLocal;
import com.mg.merp.mfreference.model.LaborClass;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Класс рабочей силы"
 *
 * @author leonova
 * @version $Id: LaborClassServiceBean.java,v 1.5 2008/03/06 10:03:17 sharapov Exp $
 */
@Stateless(name = "merp/mfreference/LaborClassService") //$NON-NLS-1$
public class LaborClassServiceBean extends AbstractPOJODataBusinessObjectServiceBean<LaborClass, Integer> implements LaborClassServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, LaborClass entity) {
    context.addRule(new MandatoryAttribute(entity, "LbrOhAllocationFlag")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "TimeRateFlag")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "LbrOhRateCurrency")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "LbrRateCurrency")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "LbrOhTimeUm")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "LbrTimeUm")); //$NON-NLS-1$
    context.addRule(new MandatoryStringAttribute(entity, "Description")); //$NON-NLS-1$
  }

}
