/*
 * PhasePlanItemServiceBean.java
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
import com.mg.framework.api.orm.FlushMode;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.orm.ResultTransformer;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.MathUtils;
import com.mg.merp.contract.PhasePlanItemServiceLocal;
import com.mg.merp.contract.model.ContractSpec;
import com.mg.merp.contract.model.PhasePlanItem;

import java.math.BigDecimal;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Пункты плана"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PhasePlanItemServiceBean.java,v 1.7 2008/03/13 14:32:01 sharapov Exp $
 */
@Stateless(name = "merp/contract/PhasePlanItemService") //$NON-NLS-1$
public class PhasePlanItemServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PhasePlanItem, Integer> implements PhasePlanItemServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, PhasePlanItem entity) {
    context.addRule(new MandatoryStringAttribute(entity, "ItemNumber")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "BeginActionDate")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "EndActionDate")); //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onStore(PhasePlanItem entity) {
    doAdjust(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.contract.PhasePlanItemServiceLocal#adjust(com.mg.merp.contract.model.PhasePlanItem)
   */
  @PermitAll
  public void adjust(PhasePlanItem phasePlanItem) {
    doAdjust(phasePlanItem);
  }

  /**
   * Рассчитать аттрибуты пункта плана контракта
   *
   * @param phasePlanItem - пункт плана
   */
  protected void doAdjust(PhasePlanItem phasePlanItem) {
    Integer currencyPrec = ConfigurationHelper.getConfiguration().getCurrencyPrec();
    AgregateSumBySpecificationResult planSumResult = getPlanSumBySpecification(phasePlanItem);
    if (planSumResult.getContractSpecCount() != 0)
      phasePlanItem.setPlanSum(planSumResult.getAgregateSum());

    if (phasePlanItem.getPlanSum() != null)
      phasePlanItem.setPlanSum(MathUtils.round(phasePlanItem.getPlanSum(), new RoundContext(currencyPrec == null ? 0 : currencyPrec)));
    else
      phasePlanItem.setPlanSum(BigDecimal.ZERO);
    if (phasePlanItem.getFactSum() == null)
      phasePlanItem.setFactSum(BigDecimal.ZERO);
  }

  /**
   * Получить планируемую сумму для пункта плана
   *
   * @param phasePlanItem - пункт плана
   * @return планируемая сумма для пункта плана
   */
  protected AgregateSumBySpecificationResult getPlanSumBySpecification(PhasePlanItem phasePlanItem) {
    return OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(ContractSpec.class)
        .setProjection(Projections.projectionList(Projections.rowCount(), Projections.sum("Summa"))) //$NON-NLS-1$
        .add(Restrictions.eq("PhaseItemPlan", phasePlanItem)) //$NON-NLS-1$
        .setFlushMode(FlushMode.MANUAL)
        .setResultTransformer(new ResultTransformer<AgregateSumBySpecificationResult>() {

          /* (non-Javadoc)
           * @see com.mg.framework.api.orm.ResultTransformer#transformTuple(java.lang.Object[], java.lang.String[])
           */
          public AgregateSumBySpecificationResult transformTuple(Object[] tuple, String[] aliases) {
            return new AgregateSumBySpecificationResult((Integer) tuple[0], (BigDecimal) tuple[1]);
          }
        }));
  }

}
