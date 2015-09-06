/*
 * InvMetalServiceBean.java
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

package com.mg.merp.account.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.account.InvMetalServiceLocal;
import com.mg.merp.account.model.InvMetal;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Инвентарная картотека"
 *
 * @author leonova
 * @version $Id: InvMetalServiceBean.java,v 1.3 2006/08/24 06:44:08 leonova Exp $
 */
@Stateless(name = "merp/account/InvMetalService")
public class InvMetalServiceBean extends AbstractPOJODataBusinessObjectServiceBean<InvMetal, Integer> implements InvMetalServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, InvMetal entity) {
    context.addRule(new MandatoryAttribute(entity, "MetalCode"));
  }


}
