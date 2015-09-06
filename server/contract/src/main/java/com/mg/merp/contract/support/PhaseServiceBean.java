/*
 * PhaseServiceBean.java
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

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projection;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.contract.PhaseServiceLocal;
import com.mg.merp.contract.model.CalcSumKind;
import com.mg.merp.contract.model.ItemKind;
import com.mg.merp.contract.model.Phase;
import com.mg.merp.contract.model.PhasePlanItem;
import com.mg.merp.contract.model.PlanAndFactSumsByKindResult;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Этапы контракта"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PhaseServiceBean.java,v 1.8 2007/11/22 15:58:59 sharapov Exp $
 */
@Stateless(name = "merp/contract/PhaseService") //$NON-NLS-1$
public class PhaseServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Phase, Integer> implements PhaseServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, Phase entity) {
    context.addRule(new MandatoryStringAttribute(entity, "PhaseNumber")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "BeginActionDate")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "EndActionDate")); //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onInitialize(Phase entity) {
    super.onInitialize(entity);
    adjust(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onCreate(Phase entity) {
    super.onCreate(entity);
    adjust(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onStore(Phase entity) {
    adjust(entity);
    super.onStore(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.contract.PhaseServiceLocal#adjust(com.mg.merp.contract.model.Phase)
   */
  @PermitAll
  public void adjust(Phase entity) {
    doAdjust(entity);
  }

  protected void doAdjust(Phase entity) {
    if (entity.getCalcSumKind() == null)
      entity.setCalcSumKind(CalcSumKind.MANUAL);

    if (entity.getCalcSumKind() == CalcSumKind.PHASESAGGREGATE) {
      BigDecimal[] sums = calculateTotalPhaseSumByKind(entity);
      entity.setShippedPayment(sums[0]);
      entity.setReceivedPayment(sums[1]);
      entity.setShippedGood(sums[2]);
      entity.setReceivedGood(sums[3]);
    }
    entity.setSumCur(calculateSum(entity.getShippedPayment(), entity.getReceivedPayment()));
  }

  /* (non-Javadoc)
   * @see com.mg.merp.contract.PhaseServiceLocal#calculateTotalPhaseSumByKind(com.mg.merp.contract.model.Phase)
   */
  @PermitAll
  public BigDecimal[] calculateTotalPhaseSumByKind(Phase contractPhase) {
    return internalCalculateTotalPhaseSumByKind(contractPhase);
  }

  private BigDecimal[] internalCalculateTotalPhaseSumByKind(Phase contractPhase) {
    Projection projection = Projections.projectionList(Projections.sum("PlanSum"), Projections.groupProperty("Kind")); //$NON-NLS-1$ //$NON-NLS-2$
    List<Object[]> phaseAgregateSums = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(PhasePlanItem.class)
        .setProjection(projection)
        .add(Restrictions.eq("ContractPhase", contractPhase))); //$NON-NLS-1$
    return getSumsByKind(phaseAgregateSums);
  }

  private BigDecimal[] getSumsByKind(List<Object[]> agregateSums) {
    BigDecimal[] sumsByKind = new BigDecimal[]{new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), new BigDecimal(0)};
    for (Object[] agregateSum : agregateSums) {
      ItemKind kind = (ItemKind) agregateSum[1];
      if (kind == ItemKind.SHIPPED)
        sumsByKind[0] = (BigDecimal) agregateSum[0];
      if (kind == ItemKind.RECEIVE)
        sumsByKind[1] = (BigDecimal) agregateSum[0];
      if (kind == ItemKind.SHIPPEDGOOD)
        sumsByKind[2] = (BigDecimal) agregateSum[0];
      if (kind == ItemKind.RECEIVEGOOD)
        sumsByKind[3] = (BigDecimal) agregateSum[0];
    }
    return sumsByKind;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.contract.PhaseServiceLocal#calculateSum(java.math.BigDecimal, java.math.BigDecimal)
   */
  @PermitAll
  public BigDecimal calculateSum(BigDecimal shippedPayment, BigDecimal receivedPayment) {
    if (shippedPayment == null)
      shippedPayment = new BigDecimal(0);
    if (receivedPayment == null)
      receivedPayment = new BigDecimal(0);
    return shippedPayment.max(receivedPayment);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.contract.PhaseServiceLocal#getTotalPlanAndFactSumsByKind(com.mg.merp.contract.model.Phase)
   */
  @PermitAll
  public PlanAndFactSumsByKindResult getTotalPlanAndFactSumsByKind(Phase contractPhase) {
    return doGetTotalPlanAndFactSumsByKind(contractPhase);
  }

  protected PlanAndFactSumsByKindResult doGetTotalPlanAndFactSumsByKind(Phase contractPhase) {
    BigDecimal planShippedPayment = BigDecimal.ZERO;
    BigDecimal planReceivedPayment = BigDecimal.ZERO;
    BigDecimal planShippedGood = BigDecimal.ZERO;
    BigDecimal planReceivedGood = BigDecimal.ZERO;

    BigDecimal factShippedPayment = BigDecimal.ZERO;
    BigDecimal factReceivedPayment = BigDecimal.ZERO;
    BigDecimal factShippedGood = BigDecimal.ZERO;
    BigDecimal factReceivedGood = BigDecimal.ZERO;

    Projection projection = Projections.projectionList(
        Projections.sum("PlanSum"), //$NON-NLS-1$
        Projections.sum("FactSum"), //$NON-NLS-1$
        Projections.groupProperty("Kind")); //$NON-NLS-1$
    List<Object[]> phaseAgregateSums = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(PhasePlanItem.class)
        .setProjection(projection)
        .add(Restrictions.eq("ContractPhase", contractPhase))); //$NON-NLS-1$

    for (Object[] agregateSum : phaseAgregateSums) {
      ItemKind kind = (ItemKind) agregateSum[2];
      if (kind == ItemKind.SHIPPED) {
        planShippedPayment = agregateSum[0] == null ? BigDecimal.ZERO : (BigDecimal) agregateSum[0];
        factShippedPayment = agregateSum[1] == null ? BigDecimal.ZERO : (BigDecimal) agregateSum[1];
        continue;
      }
      if (kind == ItemKind.RECEIVE) {
        planReceivedPayment = agregateSum[0] == null ? BigDecimal.ZERO : (BigDecimal) agregateSum[0];
        factReceivedPayment = agregateSum[1] == null ? BigDecimal.ZERO : (BigDecimal) agregateSum[1];
        continue;
      }
      if (kind == ItemKind.SHIPPEDGOOD) {
        planShippedGood = agregateSum[0] == null ? BigDecimal.ZERO : (BigDecimal) agregateSum[0];
        factShippedGood = agregateSum[1] == null ? BigDecimal.ZERO : (BigDecimal) agregateSum[1];
        continue;
      }
      if (kind == ItemKind.RECEIVEGOOD) {
        planReceivedGood = agregateSum[0] == null ? BigDecimal.ZERO : (BigDecimal) agregateSum[0];
        factReceivedGood = agregateSum[1] == null ? BigDecimal.ZERO : (BigDecimal) agregateSum[1];
      }
    }

    return new PlanAndFactSumsByKindResult(
        planShippedPayment,
        planReceivedPayment,
        planShippedGood,
        planReceivedGood,
        factShippedPayment,
        factReceivedPayment,
        factShippedGood,
        factReceivedGood);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.contract.PhaseServiceLocal#madeAvoid(com.mg.merp.contract.model.Phase)
   */
  public void madeAvoid(Phase contractPhase) {
    if (contractPhase == null)
      return;
    contractPhase.setAvoid(true);
    ServerUtils.getPersistentManager().merge(contractPhase);
  }

}
