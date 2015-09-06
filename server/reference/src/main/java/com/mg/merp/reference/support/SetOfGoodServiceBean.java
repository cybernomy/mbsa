/*
 * SetOfGoodServiceBean.java
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
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.reference.SetOfGoodServiceLocal;
import com.mg.merp.reference.model.SetOfGood;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Каталог / Комплект"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: SetOfGoodServiceBean.java,v 1.4 2007/01/16 06:03:38 sharapov Exp $
 */
@Stateless(name = "merp/reference/SetOfGoodService") //$NON-NLS-1$
public class SetOfGoodServiceBean extends AbstractPOJODataBusinessObjectServiceBean<SetOfGood, Integer> implements SetOfGoodServiceLocal {
  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, SetOfGood entity) {
    context.addRule(new MandatoryStringAttribute(entity, "CatalogComponent")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "Quantity")); //$NON-NLS-1$
  }
}
