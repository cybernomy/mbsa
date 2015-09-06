/*
 * ContractTypeServiceBean.java
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
import com.mg.merp.contract.ContractTypeServiceLocal;
import com.mg.merp.contract.model.ContractType;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Вид договора"
 *
 * @author Konstantin S. Alikaev
 * @version $Id: ContractTypeServiceBean.java,v 1.1 2007/09/17 12:16:22 alikaev Exp $
 */
@Stateless(name = "merp/contract/ContractTypeService")
public class ContractTypeServiceBean extends AbstractPOJODataBusinessObjectServiceBean<ContractType, Integer> implements ContractTypeServiceLocal {

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onValidate(ValidationContext context, ContractType entity) {
    context.addRule(new MandatoryStringAttribute(entity, "Code"));
    context.addRule(new MandatoryStringAttribute(entity, "Name"));
  }

}
