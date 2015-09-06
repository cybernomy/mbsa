/*
 * WorkScheduleServiceBean.java
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

package com.mg.merp.personnelref.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.personnelref.WorkScheduleServiceLocal;
import com.mg.merp.personnelref.model.WorkSchedule;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Графики работ"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: WorkScheduleServiceBean.java,v 1.5 2007/08/22 07:08:01 sharapov Exp $
 */
@Stateless(name = "merp/personnelref/WorkScheduleService") //$NON-NLS-1$
public class WorkScheduleServiceBean extends AbstractPOJODataBusinessObjectServiceBean<WorkSchedule, Integer> implements WorkScheduleServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, WorkSchedule entity) {
    context.addRule(new MandatoryStringAttribute(entity, "SCode")); //$NON-NLS-1$
    context.addRule(new DateIntervalRule(entity));
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onCreate(WorkSchedule entity) {
    adjust(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onStore(WorkSchedule entity) {
    adjust(entity);
  }

  private void adjust(WorkSchedule entity) {
    PersonnelrefUtils.checkDateInterval(entity);
  }

}
