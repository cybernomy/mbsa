/*
 * ItemSpecTaxServiceBean.java
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

package com.mg.merp.lbschedule.support;

import com.mg.framework.api.math.RoundContext;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.utils.MathUtils;
import com.mg.merp.lbschedule.ItemSpecTaxServiceLocal;
import com.mg.merp.lbschedule.ScheduleServiceLocal;
import com.mg.merp.lbschedule.model.ItemSpec;
import com.mg.merp.lbschedule.model.ItemSpecTax;
import com.mg.merp.lbschedule.model.TaxResult;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Налоги позиции спецификации пункта графика исполнения
 * обязательств"
 *
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id: ItemSpecTaxServiceBean.java,v 1.5 2007/04/17 12:50:59 sharapov Exp $
 */
@Stateless(name = "merp/lbschedule/ItemSpecTaxService") //$NON-NLS-1$
public class ItemSpecTaxServiceBean extends com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean<ItemSpecTax, Integer> implements ItemSpecTaxServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.lbschedule.ItemSpecTaxServiceLocal#recomputeTaxes(com.mg.merp.lbschedule.model.ItemSpec, java.math.BigDecimal)
   */
  public TaxResult recomputeTaxes(ItemSpec itemSpec, BigDecimal factor) {
    BigDecimal taxPrice = BigDecimal.ZERO;
    BigDecimal taxSum = BigDecimal.ZERO;

    List<ItemSpecTax> specTaxes = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(ItemSpecTax.class)
        .add(Restrictions.ge("ItemSpec", itemSpec))); //$NON-NLS-1$

    for (ItemSpecTax specTax : specTaxes) {
      specTax.setSumma(MathUtils.round(specTax.getSumma().multiply(factor), new RoundContext(ScheduleServiceLocal.SCHEDULE_SUM_PREC)));
      getPersistentManager().merge(specTax);

      taxPrice = taxPrice.add(specTax.getPrice());
      taxSum = taxSum.add(specTax.getSumma());
    }
    return new TaxResult(taxPrice, taxSum);
  }


}
