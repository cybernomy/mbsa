/*
 * IncludedFeeServiceBean.java
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
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.salary.IncludedFeeServiceLocal;
import com.mg.merp.salary.model.IncludedFee;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Входящие н/у"
 *
 * @author leonova
 * @version $Id: IncludedFeeServiceBean.java,v 1.5 2006/09/29 12:07:24 leonova Exp $
 */
@Stateless(name = "merp/salary/IncludedFeeService")
public class IncludedFeeServiceBean extends AbstractPOJODataBusinessObjectServiceBean<IncludedFee, Integer> implements IncludedFeeServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, IncludedFee entity) {
    context.addRule(new MandatoryAttribute(entity, "IncludedFee"));
  }


}
