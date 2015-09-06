/*
 * ContractCategoryServiceBean.java
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
package com.mg.merp.contract.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.contract.ContractCategoryServiceLocal;
import com.mg.merp.contract.model.ContractCategory;

import javax.ejb.Stateless;

/**
 * Реализация сервиса бизнес-компонента "Категория договора"
 *
 * @author Konstantin S. Alikaev
 * @version $Id: ContractCategoryServiceBean.java,v 1.1 2007/09/17 12:16:22 alikaev Exp $
 */
@Stateless(name = "merp/contract/ContractCategoryService")
public class ContractCategoryServiceBean extends AbstractPOJODataBusinessObjectServiceBean<ContractCategory, Integer> implements ContractCategoryServiceLocal {

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onValidate(ValidationContext context, ContractCategory entity) {
    context.addRule(new MandatoryStringAttribute(entity, "Code"));
    context.addRule(new MandatoryStringAttribute(entity, "Name"));
  }

}
