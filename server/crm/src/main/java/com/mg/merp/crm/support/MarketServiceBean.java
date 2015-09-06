/*
 * MarketServiceBean.java
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

package com.mg.merp.crm.support;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.crm.MarketServiceLocal;
import com.mg.merp.crm.model.Market;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Рынки"
 *
 * @author leonova
 * @version $Id: MarketServiceBean.java,v 1.6 2006/10/13 12:08:10 leonova Exp $
 */
@Stateless(name = "merp/crm/MarketService")
public class MarketServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Market, Integer> implements MarketServiceLocal {

  private void checkSetWeight(Market entity) {
    if (entity.getWeight() != null) {
      if (entity.getWeight() < 0 || entity.getWeight() > 100)
        throw new ApplicationException(Messages.getInstance().getMessage(Messages.CHECK_WEIGHT));
    }
  }

  @Override
  protected void onCreate(Market entity) {
    checkSetWeight(entity);
  }

  @Override
  protected void onStore(Market entity) {
    checkSetWeight(entity);
  }

  @Override
  protected void onValidate(ValidationContext context, Market entity) {
    context.addRule(new MandatoryStringAttribute(entity, "Code"));

  }

}
