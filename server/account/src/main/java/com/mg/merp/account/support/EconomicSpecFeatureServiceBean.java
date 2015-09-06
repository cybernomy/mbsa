/*
 * EconomicSpecFeatureServiceBean.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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

package com.mg.merp.account.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.account.EconomicSpecFeatureServiceLocal;
import com.mg.merp.account.model.EconomicSpecFeature;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Признаки проводок хозяйственных операций"
 *
 * @author leonova
 * @version $Id: EconomicSpecFeatureServiceBean.java,v 1.4 2007/09/06 14:37:19 alikaev Exp $
 */
@Stateless(name = "merp/account/EconomicSpecFeatureService")
public class EconomicSpecFeatureServiceBean extends AbstractPOJODataBusinessObjectServiceBean<EconomicSpecFeature, Integer> implements EconomicSpecFeatureServiceLocal {

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onValidate(ValidationContext context, EconomicSpecFeature entity) {
    context.addRule(new MandatoryStringAttribute(entity, "Code"));
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onCreate(EconomicSpecFeature entity) {
    adjustEconomicSpecFeature(entity);
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onStore(EconomicSpecFeature entity) {
    adjustEconomicSpecFeature(entity);
  }

  /**
   * Заполняем поле "Код в верхнем регистре" и "Наименование"
   */
  private void adjustEconomicSpecFeature(EconomicSpecFeature entity) {
    if (entity.getCode() != null)
      entity.setUpCode(StringUtils.toUpperCase(entity.getCode()));
    else
      entity.setUpCode(StringUtils.EMPTY_STRING);
    if (entity.getName() == null)
      entity.setName(StringUtils.EMPTY_STRING);
  }

}
