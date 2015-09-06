/*
 * CurrencyRateTypeServiceBean.java
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

package com.mg.merp.reference.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.reference.CurrencyRateAuthorityServiceLocal;
import com.mg.merp.reference.model.CurrencyRateAuthority;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Источники курсов валют"
 *
 * @author leonova
 * @version $Id: CurrencyRateAuthorityServiceBean.java,v 1.4 2006/10/20 05:52:40 leonova Exp $
 */
@Stateless(name = "merp/reference/CurrencyRateAuthorityService")
public class CurrencyRateAuthorityServiceBean extends AbstractPOJODataBusinessObjectServiceBean<CurrencyRateAuthority, Integer> implements CurrencyRateAuthorityServiceLocal {

  @Override
  protected void onValidate(ValidationContext context, CurrencyRateAuthority entity) {
    context.addRule(new MandatoryStringAttribute(entity, "Code"));
    context.addRule(new MandatoryStringAttribute(entity, "Name"));
  }

}
