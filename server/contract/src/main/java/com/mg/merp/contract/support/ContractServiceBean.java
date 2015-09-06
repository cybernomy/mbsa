/*
 * ContractServiceBean.java
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
import com.mg.merp.contract.ContractServiceLocal;
import com.mg.merp.contract.model.CalcSumKind;
import com.mg.merp.contract.model.Contract;
import com.mg.merp.contract.model.ItemKind;
import com.mg.merp.contract.model.Phase;
import com.mg.merp.contract.model.PhaseFactItem;
import com.mg.merp.contract.model.PhasePlanItem;
import com.mg.merp.document.Configuration;
import com.mg.merp.document.DocumentPattern;
import com.mg.merp.document.generic.DocumentServiceBean;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Контракт"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ContractServiceBean.java,v 1.12 2007/07/12 11:01:34 safonov Exp $
 */
@Stateless(name = "merp/contract/ContractService") //$NON-NLS-1$
public class ContractServiceBean extends DocumentServiceBean<Contract, Integer, DocumentPattern> implements ContractServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.DocumentServiceBean#onInitialize(com.mg.merp.document.model.DocHead)
   */
  @Override
  protected void onInitialize(Contract entity) {
    super.onInitialize(entity);
    entity.setCalcSumKind(CalcSumKind.MANUAL);
  }

  @Override
  protected int getDocSectionIdentifier() {
    return ContractServiceLocal.DOCSECTION;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.DocumentServiceBean#doGetConfiguration()
   */
  @Override
  protected Configuration doGetConfiguration() {
    return ConfigurationHelper.getDocumentConfiguration();
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.contract.ContractServiceLocal#FindByParams(com.mg.merp.document.model.DocType, java.util.Date, java.lang.String)
   */
  @PermitAll
  public DocHead findByParams(DocType contractType, Date contractDate, String contractNumber) {
    List<DocHead> contractDocHeads = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(Contract.class)
        .add(Restrictions.eq("DocDate", contractDate)) //$NON-NLS-1$
        .add(Restrictions.eq("DocType", contractType)) //$NON-NLS-1$
        .add(Restrictions.eq("DocNumber", contractNumber))); //$NON-NLS-1$

    if (contractDocHeads != null && !contractDocHeads.isEmpty())
      return contractDocHeads.get(0);
    else
      return null;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.contract.ContractServiceLocal#calculateRestSum(java.math.BigDecimal, java.math.BigDecimal, java.math.BigDecimal, java.math.BigDecimal)
   */
  @PermitAll
  public BigDecimal calculateRestSum(BigDecimal shippedPaymentSum, BigDecimal receivedGoodSum, BigDecimal receivedPaymentSum, BigDecimal shippedGoodSum) {
    BigDecimal restSum = new BigDecimal(0);
    if (shippedPaymentSum == null)
      shippedPaymentSum = new BigDecimal(0);
    if (receivedGoodSum == null)
      receivedGoodSum = new BigDecimal(0);
    if (receivedPaymentSum == null)
      receivedPaymentSum = new BigDecimal(0);
    if (shippedGoodSum == null)
      shippedGoodSum = new BigDecimal(0);
    restSum = receivedPaymentSum.add(receivedGoodSum).subtract(shippedPaymentSum).subtract(shippedGoodSum);
    return restSum;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.contract.ContractServiceLocal#calculateTotalPlanSum(com.mg.merp.document.model.DocHead)
   */
  @PermitAll
  public BigDecimal[] calculateTotalPlanSum(DocHead contract) {
    if (contract == null)
      return null;
    Projection projection = Projections.projectionList(Projections.sum("PlanSum"), Projections.groupProperty("Kind")); //$NON-NLS-1$ //$NON-NLS-2$
    List<Object[]> planAgregateSums = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(PhasePlanItem.class)
        .setProjection(projection)
        .createAlias("ContractPhase", "p") //$NON-NLS-1$ //$NON-NLS-2$
        .add(Restrictions.eq("p.DocHead", contract))); //$NON-NLS-1$

    return getSumsByKind(planAgregateSums);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.contract.ContractServiceLocal#calculateTotalFactSum(com.mg.merp.document.model.DocHead)
   */
  @PermitAll
  public BigDecimal[] calculateTotalFactSum(DocHead contract) {
    if (contract == null)
      return null;
    Projection projection = Projections.projectionList(Projections.sum("FactSum"), Projections.groupProperty("Kind")); //$NON-NLS-1$ //$NON-NLS-2$
    List<Object[]> factAgregateSums = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(PhaseFactItem.class)
        .setProjection(projection)
        .add(Restrictions.eq("DocHead", contract))); //$NON-NLS-1$

    return getSumsByKind(factAgregateSums);
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
   * @see com.mg.merp.contract.ContractServiceLocal#calculateTotalContractSum(com.mg.merp.document.model.DocHead)
   */
  @PermitAll
  public BigDecimal[] calculateTotalContractSum(DocHead contract) {
    BigDecimal[] conractSums = {new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), new BigDecimal(0)};
    if (contract == null)
      return conractSums;
    Projection projection = Projections.projectionList(
        Projections.sum("ShippedPayment"), //$NON-NLS-1$
        Projections.sum("ReceivedPayment"), //$NON-NLS-1$
        Projections.sum("ShippedGood"), //$NON-NLS-1$
        Projections.sum("ReceivedGood")); //$NON-NLS-1$
    List<Object[]> agregateSums = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(Phase.class)
        .setProjection(projection)
        .add(Restrictions.eq("Avoid", false)) //$NON-NLS-1$
        .add(Restrictions.eq("DocHead", contract))); //$NON-NLS-1$

    if (agregateSums != null && !agregateSums.isEmpty()) {
      Object[] sums = agregateSums.get(0);
      conractSums[0] = sums[0] == null ? BigDecimal.ZERO : (BigDecimal) sums[0];
      conractSums[1] = sums[1] == null ? BigDecimal.ZERO : (BigDecimal) sums[1];
      conractSums[2] = sums[2] == null ? BigDecimal.ZERO : (BigDecimal) sums[2];
      conractSums[3] = sums[3] == null ? BigDecimal.ZERO : (BigDecimal) sums[3];
    }
    return conractSums;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.DocumentServiceBean#doAdjust(com.mg.merp.document.model.DocHead)
   */
  @Override
  protected void doAdjust(Contract entity) {
    super.doAdjust(entity);

    if (entity.getCalcSumKind() == null)
      entity.setCalcSumKind(CalcSumKind.MANUAL);

    if (entity.getCalcSumKind() == CalcSumKind.PHASESAGGREGATE) {
      BigDecimal[] contractSums = calculateTotalContractSum(entity);
      entity.setShippedPayment(contractSums[0]);
      entity.setReceivedPayment(contractSums[1]);
      entity.setShippedGood(contractSums[2]);
      entity.setReceivedGood(contractSums[3]);
    }
  }

}
