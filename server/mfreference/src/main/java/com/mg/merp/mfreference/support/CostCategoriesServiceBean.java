/*
 * CostcategoriesServiceBean.java
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

package com.mg.merp.mfreference.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.mfreference.CostCategoriesServiceLocal;
import com.mg.merp.mfreference.model.CostCategories;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Категории затрат"
 *
 * @author leonova
 * @version $Id: CostCategoriesServiceBean.java,v 1.3 2006/08/24 12:31:25 leonova Exp $
 */
@Stateless(name = "merp/mfreference/CostCategoriesService")
public class CostCategoriesServiceBean extends AbstractPOJODataBusinessObjectServiceBean<CostCategories, Integer> implements CostCategoriesServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, CostCategories entity) {
    context.addRule(new MandatoryStringAttribute(entity, "Code"));
    context.addRule(new MandatoryAttribute(entity, "AlgRepository"));
  }


}
