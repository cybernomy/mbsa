/*
 * PackingServiceBean.java
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
import com.mg.merp.reference.PackingServiceLocal;
import com.mg.merp.reference.model.Packing;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Каталог / Упаковки"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PackingServiceBean.java,v 1.4 2007/01/16 05:59:55 sharapov Exp $
 */
@Stateless(name = "merp/reference/PackingService") //$NON-NLS-1$
public class PackingServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Packing, Integer> implements PackingServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, Packing entity) {
    context.addRule(new MandatoryStringAttribute(entity, "Measure")); //$NON-NLS-1$
  }

}
