/*
 * BommachineServiceBean.java
 *
 * Copyright (c) 1998 - 2009 BusinessTechnology, Ltd.
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
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.mfreference.BOMMachineServiceLocal;
import com.mg.merp.mfreference.generic.AbstractBOMResource;
import com.mg.merp.mfreference.model.BomMachine;
import com.mg.merp.mfreference.model.ManufactureConfig;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Операции / Оборудование"
 *
 * @author leonova
 * @version $Id: BOMMachineServiceBean.java,v 1.8 2009/04/29 08:01:57 safonov Exp $
 */
@Stateless(name = "merp/mfreference/BOMMachineService")
public class BOMMachineServiceBean extends AbstractBOMResource<BomMachine> implements BOMMachineServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.mfreference.generic.AbstractBOMResource#onInitialize(com.mg.merp.mfreference.model.BomRouteResource)
   */
  @Override
  protected void onInitialize(BomMachine entity) {
    super.onInitialize(entity);
    ManufactureConfig config = ConfigurationHelper.getConfiguration();
    entity.setRunTimeMchUm(config.getMainTimeUM());
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, BomMachine entity) {
    super.onValidate(context, entity);
    context.addRule(new MandatoryAttribute(entity, "MchOhRateCurrency"));
    context.addRule(new MandatoryAttribute(entity, "MchRateCurrency"));
    context.addRule(new MandatoryAttribute(entity, "RunTimeMchUm"));
  }

}
