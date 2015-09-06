/*
 * WorkTimeNormServiceBean.java
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

package com.mg.merp.personnelref.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.personnelref.WorkTimeNormServiceLocal;
import com.mg.merp.personnelref.model.WorkTimeNorm;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Нормы рабочего времени"
 *
 * @author leonova
 * @version $Id: WorkTimeNormServiceBean.java,v 1.4 2006/09/04 13:02:21 leonova Exp $
 */
@Stateless(name = "merp/personnelref/WorkTimeNormService")
public class WorkTimeNormServiceBean extends AbstractPOJODataBusinessObjectServiceBean<WorkTimeNorm, Integer> implements WorkTimeNormServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, WorkTimeNorm entity) {
    context.addRule(new MandatoryAttribute(entity, "CalcPeriod"));
    context.addRule(new MandatoryAttribute(entity, "WorkDaysNumber"));
    context.addRule(new MandatoryAttribute(entity, "WorkHoursNumber"));
  }

}
