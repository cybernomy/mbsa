/*
 * StaffListPositionServiceBean.java
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
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.personnelref.StaffListPositionServiceLocal;
import com.mg.merp.personnelref.model.StaffListPosition;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Должности в штатном расписании"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: StaffListPositionServiceBean.java,v 1.4 2007/08/22 06:59:20 sharapov Exp $
 */
@Stateless(name = "merp/personnelref/StaffListPositionService") //$NON-NLS-1$
public class StaffListPositionServiceBean extends AbstractPOJODataBusinessObjectServiceBean<StaffListPosition, Integer> implements StaffListPositionServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, StaffListPosition entity) {
    context.addRule(new MandatoryStringAttribute(entity, "SlPositionUniqueId")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "Position")); //$NON-NLS-1$
    context.addRule(new DateIntervalRule(entity));
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onCreate(StaffListPosition entity) {
    adjust(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onStore(StaffListPosition entity) {
    adjust(entity);
  }

  private void adjust(StaffListPosition entity) {
    PersonnelrefUtils.checkDateInterval(entity);
  }

}
