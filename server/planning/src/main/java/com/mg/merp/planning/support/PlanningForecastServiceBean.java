/*
 * PlanningForecastServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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

package com.mg.merp.planning.support;

import com.mg.framework.api.validator.Rule;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.planning.PlanningForecastServiceLocal;
import com.mg.merp.planning.model.ForecastMethod;
import com.mg.merp.planning.model.ForecastType;
import com.mg.merp.planning.model.PlanningForecast;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Строки версий прогнозов"
 *
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: PlanningForecastServiceBean.java,v 1.7 2008/02/13 13:20:10 alikaev Exp $
 */
@Stateless(name = "merp/planning/PlanningForecastService")
@PermitAll
public class PlanningForecastServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PlanningForecast, Integer> implements PlanningForecastServiceLocal {

  @Override
  protected void onInitialize(PlanningForecast entity) {
    entity.setForecastMethod(ForecastMethod.BY_PERIOD);
    entity.setForecastType(ForecastType.SALE);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, final PlanningForecast entity) {
    context.addRule(new MandatoryAttribute(entity, "Measure"));
    context.addRule(new MandatoryAttribute(entity, "PlanningLevel"));
    context.addRule(new Rule() {

      public String getMessage() {
        return Messages.getInstance().getMessage(Messages.PLANNINGFORECAST_CHOOSE_CATALOG_OR_PLANNINGITEM);
      }

      public void validate(ValidationContext context) {
        if ((entity.getCatalog() == null && entity.getPlanningItem() == null) || (entity.getCatalog() != null && entity.getPlanningItem() != null))
          context.getStatus().error(this);
      }
    });
  }

}
