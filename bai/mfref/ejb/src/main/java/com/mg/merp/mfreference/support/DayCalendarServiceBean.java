/*
 * DaycalendarServiceBean.java
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

package com.mg.merp.mfreference.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.mfreference.DayCalendarServiceLocal;
import com.mg.merp.mfreference.model.DayCalendar;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Дневной календарь"
 *
 * @author leonova
 * @version $Id: DayCalendarServiceBean.java,v 1.3 2006/09/04 05:52:43 leonova Exp $
 */
@Stateless(name = "merp/mfreference/DayCalendarService")
public class DayCalendarServiceBean extends AbstractPOJODataBusinessObjectServiceBean<DayCalendar, Integer> implements DayCalendarServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, DayCalendar entity) {
    context.addRule(new MandatoryStringAttribute(entity, "Code"));
    context.addRule(new MandatoryStringAttribute(entity, "DayCalName"));
  }


}
