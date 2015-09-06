/*
 * PromotionTypeServiceBean.java
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
package com.mg.merp.discount.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.discount.PromotionTypeServiceLocal;
import com.mg.merp.discount.model.PromotionType;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Тип рекламного мероприятия"
 *
 * @author Artem V. Sharapov
 * @version $Id: PromotionTypeServiceBean.java,v 1.3 2007/11/16 10:18:49 sharapov Exp $
 */
@Stateless(name = "merp/discount/PromotionTypeService") //$NON-NLS-1$
public class PromotionTypeServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PromotionType, Integer> implements PromotionTypeServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onValidate(ValidationContext context, PromotionType entity) {
    context.addRule(new MandatoryStringAttribute(entity, "Name")); //$NON-NLS-1$
    context.addRule(new MandatoryStringAttribute(entity, "Code")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "Bai")); //$NON-NLS-1$
  }

}
