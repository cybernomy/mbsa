/*
 * AnalyticsServiceBean.java
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

package com.mg.merp.finance.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.finance.AnalyticsServiceLocal;
import com.mg.merp.finance.model.Analytics;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Аналитика финансовых счетов"
 *
 * @author leonova
 * @version $Id: AnalyticsServiceBean.java,v 1.3 2006/08/28 12:48:45 leonova Exp $
 */
@Stateless(name = "merp/finance/AnalyticsService")
public class AnalyticsServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Analytics, Integer> implements AnalyticsServiceLocal {

  private void adjustAnalytics(Analytics entity) {
    entity.setUpCode(StringUtils.toUpperCase(entity.getCode()));
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(T)
   */
  @Override
  protected void onCreate(Analytics entity) {
    adjustAnalytics(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(T)
   */
  @Override
  protected void onStore(Analytics entity) {
    adjustAnalytics(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, Analytics entity) {
    context.addRule(new MandatoryStringAttribute(entity, "Code"));
    context.addRule(new MandatoryStringAttribute(entity, "AnlName"));
  }


}
