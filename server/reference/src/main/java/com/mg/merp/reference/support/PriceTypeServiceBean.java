/*
 * PriceTypeServiceBean.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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

package com.mg.merp.reference.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.reference.PriceTypeServiceLocal;
import com.mg.merp.reference.model.PriceType;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Типы цен"
 *
 * @author leonova
 * @version $Id: PriceTypeServiceBean.java,v 1.4 2006/10/20 07:20:57 leonova Exp $
 */
@Stateless(name = "merp/reference/PriceTypeService")
public class PriceTypeServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PriceType, Integer> implements PriceTypeServiceLocal {

  @Override
  protected void onValidate(ValidationContext context, PriceType entity) {
    context.addRule(new MandatoryStringAttribute(entity, "Code"));
    context.addRule(new MandatoryStringAttribute(entity, "PName"));
  }

}
