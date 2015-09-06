/*
 * TaxServiceBean.java
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
import com.mg.framework.utils.StringUtils;
import com.mg.merp.reference.TaxServiceLocal;
import com.mg.merp.reference.model.Tax;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Налоги"
 *
 * @author leonova
 * @version $Id: TaxServiceBean.java,v 1.5 2006/09/15 12:53:03 leonova Exp $
 */
@Stateless(name = "merp/reference/TaxService")
public class TaxServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Tax, Integer> implements TaxServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, Tax entity) {
    context.addRule(new MandatoryStringAttribute(entity, "TName"));
    context.addRule(new MandatoryStringAttribute(entity, "Code"));
    context.addRule(new MandatoryAttribute(entity, "TaxType"));
    context.addRule(new MandatoryAttribute(entity, "TaxForm"));
    context.addRule(new MandatoryAttribute(entity, "InverseRate"));
    context.addRule(new MandatoryAttribute(entity, "DirectRate"));
    context.addRule(new MandatoryAttribute(entity, "ActiveDate"));
    context.addRule(new MandatoryAttribute(entity, "DeactivateDate"));
  }

  private void adjustTax(Tax entity) {
    entity.setUpCode(StringUtils.toUpperCase(entity.getCode()));
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(T)
   */
  @Override
  protected void onCreate(Tax entity) {
    adjustTax(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(T)
   */
  @Override
  protected void onStore(Tax entity) {
    adjustTax(entity);
  }

}
