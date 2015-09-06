/*
 * IncomeKindServiceBean.java
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
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.salary.IncomeKindServiceLocal;
import com.mg.merp.salary.model.IncomeKind;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Виды доходов"
 *
 * @author leonova
 * @version $Id: IncomeKindServiceBean.java,v 1.3 2006/08/31 11:37:58 leonova Exp $
 */
@Stateless(name = "merp/salary/IncomeKindService")
public class IncomeKindServiceBean extends AbstractPOJODataBusinessObjectServiceBean<IncomeKind, Integer> implements IncomeKindServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, IncomeKind entity) {
    context.addRule(new MandatoryStringAttribute(entity, "ICode"));
    context.addRule(new MandatoryAttribute(entity, "BeginDate"));
  }


}
