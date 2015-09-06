/*
 * FeeModelServiceBean.java
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
import com.mg.merp.personnelref.support.DateIntervalRule;
import com.mg.merp.personnelref.support.PersonnelrefUtils;
import com.mg.merp.salary.FeeModelServiceLocal;
import com.mg.merp.salary.model.FeeModel;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Образцы начислений / удержаний"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: FeeModelServiceBean.java,v 1.4 2007/08/22 07:20:34 sharapov Exp $
 */
@Stateless(name = "merp/salary/FeeModelService") //$NON-NLS-1$
public class FeeModelServiceBean extends AbstractPOJODataBusinessObjectServiceBean<FeeModel, Integer> implements FeeModelServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, FeeModel entity) {
    context.addRule(new MandatoryAttribute(entity, "FeeRef")); //$NON-NLS-1$
    context.addRule(new DateIntervalRule(entity));
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onCreate(FeeModel entity) {
    adjust(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onStore(FeeModel entity) {
    adjust(entity);
  }

  private void adjust(FeeModel entity) {
    PersonnelrefUtils.checkDateInterval(entity);
  }

}
