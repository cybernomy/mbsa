/*
 * InventoryForecastLineServiceBean.java
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

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.planning.InventoryForecastLineServiceLocal;
import com.mg.merp.planning.model.InventoryForecastLine;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Строки версий складских запасов"
 *
 * @author leonova
 * @version $Id: InventoryForecastLineServiceBean.java,v 1.5 2007/08/06 14:46:08 safonov Exp $
 */
@Stateless(name = "merp/planning/InventoryForecastLineService")
@PermitAll
public class InventoryForecastLineServiceBean extends AbstractPOJODataBusinessObjectServiceBean<InventoryForecastLine, Integer> implements InventoryForecastLineServiceLocal {

  @Override
  protected void onValidate(ValidationContext context, InventoryForecastLine entity) {
    context.addRule(new MandatoryAttribute(entity, "GenericItem"));
    context.addRule(new MandatoryAttribute(entity, "Warehouse"));
    context.addRule(new MandatoryAttribute(entity, "Measure"));
  }

}
