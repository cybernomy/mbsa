/*
 * ContractSpecServiceBean.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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

import com.mg.framework.api.math.RoundContext;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.utils.MathUtils;
import com.mg.merp.contract.ContractSpecServiceLocal;
import com.mg.merp.contract.model.ContractSpec;

import java.math.BigDecimal;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Спецификация контракта"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ContractSpecServiceBean.java,v 1.5 2008/03/11 09:49:41 sharapov Exp $
 */
@Stateless(name = "merp/contract/ContractSpecService") //$NON-NLS-1$
public class ContractSpecServiceBean extends AbstractPOJODataBusinessObjectServiceBean<ContractSpec, Integer> implements ContractSpecServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onValidate(ValidationContext context, ContractSpec entity) {
    context.addRule(new MandatoryAttribute(entity, "Catalog")); //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onCreate(ContractSpec entity) {
    doAdjust(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onStore(ContractSpec entity) {
    doAdjust(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.contract.ContractSpecServiceLocal#adjust(com.mg.merp.contract.model.ContractSpec)
   */
  @PermitAll
  public void adjust(ContractSpec contractSpec) {
    doAdjust(contractSpec);
  }

  /**
   * Рассчитать аттрибуты позиции спецификации контракта
   *
   * @param contractSpec - позиция спецификации контракта
   */
  public void doAdjust(ContractSpec contractSpec) {
    Integer currencyPrec = ConfigurationHelper.getConfiguration().getCurrencyPrec();
    if (currencyPrec == null)
      currencyPrec = 0;

    if (contractSpec.getPrice() != null)
      contractSpec.setSumma(MathUtils.multiply(contractSpec.getPrice(), contractSpec.getQuantity(), new RoundContext(currencyPrec)));
    else if (contractSpec.getSumma() != null) {
      if (contractSpec.getQuantity() != null && MathUtils.compareToZero(contractSpec.getQuantity()) != 0)
        contractSpec.setPrice(MathUtils.divide(contractSpec.getSumma(), contractSpec.getQuantity(), new RoundContext(currencyPrec)));
    }

    if (contractSpec.getQuantity() == null)
      contractSpec.setQuantity(BigDecimal.ZERO);
    if (contractSpec.getPrice() == null)
      contractSpec.setPrice(BigDecimal.ZERO);
    if (contractSpec.getSumma() == null)
      contractSpec.setSumma(BigDecimal.ZERO);
  }

}
