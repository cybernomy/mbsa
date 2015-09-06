/*
 * TaxHeadServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */

package com.mg.merp.salary.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.salary.TaxHeadServiceLocal;
import com.mg.merp.salary.model.TaxHead;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Налоговые сетки"
 *
 * @author leonova
 * @version $Id: TaxHeadServiceBean.java,v 1.3 2006/09/08 07:10:03 leonova Exp $
 */
@Stateless(name = "merp/salary/TaxHeadService")
public class TaxHeadServiceBean extends AbstractPOJODataBusinessObjectServiceBean<TaxHead, Integer> implements TaxHeadServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, TaxHead entity) {
    context.addRule(new MandatoryStringAttribute(entity, "TCode"));
    context.addRule(new MandatoryStringAttribute(entity, "TName"));
  }


}
