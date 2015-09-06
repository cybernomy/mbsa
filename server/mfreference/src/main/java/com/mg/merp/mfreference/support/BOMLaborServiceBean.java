/*
 * BomlaborServiceBean.java
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
import com.mg.merp.mfreference.BOMLaborServiceLocal;
import com.mg.merp.mfreference.generic.AbstractBOMResource;
import com.mg.merp.mfreference.model.BomLabor;
import com.mg.merp.mfreference.model.ManufactureConfig;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Операции / Рабочая сила"
 *
 * @author leonova
 * @version $Id: BOMLaborServiceBean.java,v 1.9 2009/04/29 07:58:36 safonov Exp $
 */
@Stateless(name = "merp/mfreference/BOMLaborService")
public class BOMLaborServiceBean extends AbstractBOMResource<BomLabor> implements BOMLaborServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.mfreference.generic.AbstractBOMResource#onInitialize(com.mg.merp.mfreference.model.BomRouteResource)
   */
  @Override
  protected void onInitialize(BomLabor entity) {
    super.onInitialize(entity);
    ManufactureConfig config = ConfigurationHelper.getConfiguration();
    entity.setRunTimeLbrUm(config.getMainTimeUM());
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, BomLabor entity) {
    super.onValidate(context, entity);
    context.addRule(new MandatoryAttribute(entity, "LaborClass"));
    context.addRule(new MandatoryAttribute(entity, "RunTimeLbrUm"));
  }

}
