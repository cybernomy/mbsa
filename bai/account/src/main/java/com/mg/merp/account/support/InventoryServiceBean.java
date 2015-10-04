/*
 * InventoryServiceBean.java
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

package com.mg.merp.account.support;

import com.mg.framework.api.math.RoundContext;
import com.mg.framework.api.orm.FlushMode;
import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.orm.ResultTransformer;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.account.AccInventoryMoveParams;
import com.mg.merp.account.AccInventoryRetireParams;
import com.mg.merp.account.AccRevaluateParams;
import com.mg.merp.account.AmRateServiceLocal;
import com.mg.merp.account.AmortizationServiceLocal;
import com.mg.merp.account.EconomicSpecServiceLocal;
import com.mg.merp.account.InvHistoryServiceLocal;
import com.mg.merp.account.InventoryServiceLocal;
import com.mg.merp.account.OperationServiceLocal;
import com.mg.merp.account.RemnAccServiceLocal;
import com.mg.merp.account.RemnDbKtServiceLocal;
import com.mg.merp.account.RemnValServiceLocal;
import com.mg.merp.account.model.AccPlan;
import com.mg.merp.account.model.AmCode;
import com.mg.merp.account.model.Amortization;
import com.mg.merp.account.model.AnlPlan;
import com.mg.merp.account.model.EconomicOper;
import com.mg.merp.account.model.EconomicSpec;
import com.mg.merp.account.model.InvActionKind;
import com.mg.merp.account.model.InvHead;
import com.mg.merp.account.model.InvHistory;
import com.mg.merp.account.model.InvProduction;
import com.mg.merp.account.model.Inventory;
import com.mg.merp.account.model.Period;
import com.mg.merp.account.model.RemnAcc;
import com.mg.merp.account.model.RemnDbKt;
import com.mg.merp.account.model.RemnVal;
import com.mg.merp.core.model.Folder;
import com.mg.merp.document.model.DocType;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Contractor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Данные по видам учета"
 *
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: InventoryServiceBean.java,v 1.8 2009/02/20 10:33:30 sharapov Exp $
 */
@Stateless(name = "merp/account/InventoryService") //$NON-NLS-1$
public class InventoryServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Inventory, Integer> implements InventoryServiceLocal {

  private AmortizationServiceLocal amortizationService = null;

  private OperationServiceLocal operationService = null;

  private EconomicSpecServiceLocal economicSpecService = null;

  private AmRateServiceLocal amRateService = null;

  private InvHistoryServiceLocal invHistoryService = null;

  private RemnValServiceLocal remnValService = null;

  private RemnAccServiceLocal remnAccService = null;

  private RemnDbKtServiceLocal remnDbKtService = null;

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, Inventory entity) {
    context.addRule(new MandatoryAttribute(entity, "BeginCost")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "AccKind")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "AccDb")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "AccKt")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "AccPlan"));         //$NON-NLS-1$
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onStore(Inventory entity) {
    doAdjust(entity);
  }

  protected void doAdjust(Inventory entity) {
    RoundContext roundContext = new RoundContext(ConfigurationHelper.getConfiguration().getCurrencyPrec());
    entity.setBalanceCost(entity.getBeginCost());
    entity.setEndCost(MathUtils.subtractNullable(entity.getBeginCost(), MathUtils.addNullable(entity.getInitialloss(), entity.getBeginLoss(), roundContext), roundContext));
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onCreate(Inventory entity) {
    InvHead invHead = entity.getInvHead();
    if (invHead != null) {
      entity.setFolder(invHead.getFolder());
      entity.setGroupNum(invHead.getGroupNum());
      entity.setCardNum(invHead.getCardNum());
      entity.setObjNum(invHead.getObjNum());
      entity.setOkof(invHead.getOkof());
      entity.setInvLocation(invHead.getInvLocation());
      entity.setManufacturer(invHead.getManufacturer());
      entity.setModel(invHead.getModel());
      entity.setSerialNum(invHead.getSerialNum());
      entity.setPasspNum(invHead.getPasspNum());
      entity.setInOperDocNum(invHead.getInOperDocNum());
      entity.setInOperDate(invHead.getInOperDate());
      entity.setOutOperDate(invHead.getOutOperDate());
      entity.setOutOperDocNum(invHead.getOutOperDocNum());
      entity.setIsComplex(invHead.isComplex());
      entity.setIsCommon(invHead.isCommon());
      entity.setComment(invHead.getComment());
      entity.setCatalog(invHead.getCatalog());
      entity.setContractor(invHead.getContractor());
      entity.setIncomeDate(invHead.getIncomeDate());
      entity.setIncomeDocNum(invHead.getIncomeDocNum());
      entity.setInvName(invHead.getInvName());
    }
    doAdjust(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.account.InventoryServiceLocal#calcAmortization(com.mg.merp.account.model.Inventory, short, java.lang.Integer)
   */
  @PermitAll
  public Integer calcAmortization(List<Inventory> inventories, Short month, Integer batch) {
    return doCalcAmortization(inventories, month, batch);
  }

  /**
   * Начисление амортизации
   *
   * @param inventory - данные по видам учета
   * @param aMonth    - месяц начисления амортизации в абсолютном исчислении (год*12 + месяц)
   * @param batch     - служебное поле
   */
  protected Integer doCalcAmortization(List<Inventory> inventories, Short month, Integer batch) {
    Long accAmortizationBatchGen = (Long) DatabaseUtils.getSequenceNextValue("ACC_AMORTIZATION_BATCH_GEN"); //$NON-NLS-1$
    batch = accAmortizationBatchGen.intValue();
    for (Inventory inventory : inventories) {
      List<InvHistory> invHistories = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(InvHistory.class)
          .add(Restrictions.eq("Inventory", inventory)) //$NON-NLS-1$
          .add(Restrictions.eq("Kind", InvActionKind.RETIRE)) //$NON-NLS-1$
          .setFlushMode(FlushMode.MANUAL));
      if (invHistories.isEmpty() && inventory.getAlgorithm() != null) {
        switch (inventory.getAlgorithm()) {
          case LINEAR:
            calcAmortizationLinear(inventory, month, batch);
            break;
          case BYENDCOST:
            calcAmortizationDeprVal(inventory, month, batch);
            break;
          case BYEXPLPERIOD:
            calcAmortizationPeriod(inventory, month, batch);
            break;
          case BYPRODUCTION:
            calcAmortizationProduction(inventory, month, batch);
            break;
        }
      }
    }
    return batch;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.account.InventoryServiceLocal#calcAmortizationDeprVal(com.mg.merp.account.model.Inventory, short, java.lang.Integer)
   */
  public void calcAmortizationDeprVal(Inventory inventory, Short month, Integer batch) {
    doCalcAmortizationDeprVal(inventory, month, batch);
  }

  /**
   * Расчет амортизации пропорционально остаточной стоимости
   *
   * @param inventory - данные по видам учета
   * @param aMonth    - месяц начисления амортизации в абсолютном исчислении (год*12 + месяц)
   * @param batch     - служебное поле
   */
  protected void doCalcAmortizationDeprVal(Inventory inventory, Short month, Integer batch) {
    RoundContext roundContext = new RoundContext(ConfigurationHelper.getConfiguration().getCurrencyPrec());
    BigDecimal endCost = inventory.getEndCost();
    if (endCost != null) {
      Date begLossDate = inventory.getBeginLossDate();
      Date inDate = inventory.getInOperDate();
      Float factor = inventory.getFactor();
      BigDecimal amRate = BigDecimal.ZERO;
      AmCode amCode = inventory.getAmCode();
      BigDecimal amValue = BigDecimal.ZERO;
      BigDecimal bmValue = inventory.getBalanceCost();

      if (begLossDate != null)
        inDate = begLossDate;

      Date startYear = DateTimeUtils.startOfTheYear(DateTimeUtils.nowDate());
      BigDecimal deltaDeprValSum = OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(InvHistory.class)
          .setProjection(Projections.sum("DeltaDeprVal")) //$NON-NLS-1$
          .setFlushMode(FlushMode.MANUAL)
          .add(Restrictions.eq("Inventory", inventory)) //$NON-NLS-1$
          .add(Restrictions.eq("Kind", InvActionKind.REVAL)) //$NON-NLS-1$
          .add(Restrictions.ge("ActDate", startYear))); //$NON-NLS-1$
      endCost = MathUtils.subtractNullable(endCost, deltaDeprValSum, roundContext);

      NumMonthsResult result = getNumMonths(inventory, month, inDate, inventory.getOutOperDate());
      short months = result.months;
      short numMonths = result.numMonths;
      while (months > 0) {
        numMonths++;
        amRate = getAmRateService().getAmortRate(amCode, numMonths);
        if (amRate.compareTo(BigDecimal.ZERO) == 0)
          amRate = inventory.getYearAmortRate();
        amValue = MathUtils.addNullable(amValue, MathUtils.divide(MathUtils.multiply(amRate, endCost, roundContext), new BigDecimal(1200), roundContext), roundContext);
        months--;
      }
      BigDecimal sumTotal = (factor != null && factor != 0.) ? MathUtils.multiply(amValue, new BigDecimal(factor), roundContext) : amValue;
      if (sumTotal.compareTo(endCost) > 0)
        sumTotal = endCost;
      createAmortization(inventory, month, batch, factor, amCode != null ? amCode.getId() : null, bmValue, endCost, result.months, amRate, amValue, sumTotal);
    }
  }

  /* (non-Javadoc)
   * @see com.mg.merp.account.InventoryServiceLocal#calcAmortizationLinear(com.mg.merp.account.model.Inventory, short, java.lang.Integer)
   */
  @PermitAll
  public void calcAmortizationLinear(Inventory inventory, Short month, Integer batch) {
    doCalcAmortizationLinear(inventory, month, batch);
  }

  /**
   * Расчет амортизации линейным методом
   *
   * @param inventory - данные по видам учета
   * @param aMonth    - месяц начисления амортизации в абсолютном исчислении (год*12 + месяц)
   * @param batch     - служебное поле
   */
  protected void doCalcAmortizationLinear(Inventory inventory, Short month, Integer batch) {
    RoundContext roundContext = new RoundContext(ConfigurationHelper.getConfiguration().getCurrencyPrec());
    BigDecimal endCost = inventory.getEndCost();
    if (endCost != null) {
      Date begLossDate = inventory.getBeginLossDate();
      Date inDate = inventory.getInOperDate();
      Float factor = inventory.getFactor();
      BigDecimal amRate = BigDecimal.ZERO;
      AmCode amCode = inventory.getAmCode();
      BigDecimal amValue = BigDecimal.ZERO;
      BigDecimal bmValue = inventory.getBalanceCost();

      if (begLossDate != null)
        inDate = begLossDate;
      NumMonthsResult result = getNumMonths(inventory, month, inDate, inventory.getOutOperDate());
      short months = result.months;
      short numMonths = result.numMonths;
      while (months > 0) {
        numMonths++;
        amRate = getAmRateService().getAmortRate(amCode, numMonths);
        if (amRate.compareTo(BigDecimal.ZERO) == 0)
          amRate = inventory.getYearAmortRate();
        amValue = MathUtils.addNullable(amValue, MathUtils.divide(MathUtils.multiply(amRate, bmValue, roundContext), new BigDecimal(1200), roundContext), roundContext);
        months--;
      }
      BigDecimal sumTotal = (factor != null && factor != 0.) ? MathUtils.multiply(amValue, new BigDecimal(factor), roundContext) : amValue;
      if (sumTotal.compareTo(endCost) > 0)
        sumTotal = endCost;
      createAmortization(inventory, month, batch, factor, amCode != null ? amCode.getId() : null, bmValue, endCost, result.months, amRate, amValue, sumTotal);
    }
  }

  /* (non-Javadoc)
   * @see com.mg.merp.account.InventoryServiceLocal#calcAmortizationPeriod(com.mg.merp.account.model.Inventory, short, java.lang.Integer)
   */
  public void calcAmortizationPeriod(Inventory inventory, Short month, Integer batch) {
    doCalcAmortizationPeriod(inventory, month, batch);
  }

  /**
   * Расчет амортизации пропорционально периоду эксплуатации
   *
   * @param inventory - данные по видам учета
   * @param aMonth    - месяц начисления амортизации в абсолютном исчислении (год*12 + месяц)
   * @param batch     - служебное поле
   */
  protected void doCalcAmortizationPeriod(Inventory inventory, Short month, Integer batch) {
    RoundContext roundContext = new RoundContext(ConfigurationHelper.getConfiguration().getCurrencyPrec());
    BigDecimal endCost = inventory.getEndCost();
    if (endCost != null) {
      Date begLossDate = inventory.getBeginLossDate();
      Date inDate = inventory.getInOperDate();
      Float factor = inventory.getFactor();
      BigDecimal amRate = BigDecimal.ZERO;
      AmCode amCode = inventory.getAmCode();
      BigDecimal amValue = BigDecimal.ZERO;
      BigDecimal bmValue = inventory.getBalanceCost();
      Float explPeriodY = inventory.getExplPeriodY() != null ? inventory.getExplPeriodY() : 0;
      Float explPeriodM = inventory.getExplPeriodM() != null ? inventory.getExplPeriodM() : 0;
      Float cardAmRateF = (explPeriodY != 0 && explPeriodM != 0) ? 1200 / (12 * explPeriodY + explPeriodM) : 1200;
      BigDecimal cardAmRate = cardAmRateF != 0 ? MathUtils.round(new BigDecimal(cardAmRateF), roundContext) : BigDecimal.ZERO;
      if (begLossDate != null)
        inDate = begLossDate;
      NumMonthsResult result = getNumMonths(inventory, month, inDate, inventory.getOutOperDate());
      short months = result.months;
      while (months > 0) {
        amValue = MathUtils.addNullable(amValue, MathUtils.divide(MathUtils.multiply(cardAmRate, bmValue, roundContext), new BigDecimal(1200), roundContext), roundContext);
        months--;
      }
      BigDecimal sumTotal = (factor != null && factor != 0.) ? MathUtils.multiply(amValue, new BigDecimal(factor), roundContext) : amValue;
      if (sumTotal.compareTo(endCost) > 0)
        sumTotal = endCost;
      createAmortization(inventory, month, batch, factor, amCode != null ? amCode.getId() : null, bmValue, endCost, result.months, amRate, null, null, explPeriodY, explPeriodM, null, amValue, null, sumTotal);
    }
  }

  /* (non-Javadoc)
   * @see com.mg.merp.account.InventoryServiceLocal#calcAmortizationProduction(com.mg.merp.account.model.Inventory, short, java.lang.Integer)
   */
  public void calcAmortizationProduction(Inventory inventory, Short month, Integer batch) {
    doCalcAmortizationProduction(inventory, month, batch);
  }

  /**
   * Расчет амортизации по продукции
   *
   * @param inventory - данные по видам учета
   * @param aMonth    - месяц начисления амортизации в абсолютном исчислении (год*12 + месяц)
   * @param batch     - служебное поле
   */
  protected void doCalcAmortizationProduction(Inventory inventory, Short month, Integer batch) {
    RoundContext roundContext = new RoundContext(ConfigurationHelper.getConfiguration().getCurrencyPrec());
    BigDecimal endCost = inventory.getEndCost();
    if (endCost != null) {
      Date begLossDate = inventory.getBeginLossDate();
      Date inDate = inventory.getInOperDate();
      Float factor = inventory.getFactor();
      AmCode amCode = inventory.getAmCode();
      BigDecimal bmValue = inventory.getBalanceCost();
      BigDecimal totalVol = BigDecimal.ZERO;
      BigDecimal vol = BigDecimal.ZERO;
      BigDecimal factVol = BigDecimal.ZERO;

      if (begLossDate != null)
        inDate = begLossDate;
      NumMonthsResult result = getNumMonths(inventory, month, inDate, inventory.getOutOperDate());
      short months = result.months;
      short numMonths = result.numMonths;

      while (months > 0) {
        numMonths++;
        vol = getAmRateService().getVolumeProd(amCode, numMonths);
        totalVol = MathUtils.addNullable(totalVol, vol, roundContext);
        months--;
      }
      if (result.months > 0)
        vol = MathUtils.divide(totalVol, new BigDecimal(result.months), roundContext);
      List<InvProduction> invProductions = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(InvProduction.class)
          .add(Restrictions.eq("AccInvHead", inventory.getInvHead())) //$NON-NLS-1$
          .add(Restrictions.eq("AMonth", month.intValue())) //$NON-NLS-1$
          .setFlushMode(FlushMode.MANUAL)
          .setMaxResults(1));
      if (!invProductions.isEmpty())
        factVol = new BigDecimal(invProductions.get(0).getProduction());
      BigDecimal sumProduction = MathUtils.compareToZero(vol) != 0 ? MathUtils.divide(MathUtils.multiply(bmValue, factVol, roundContext), vol, roundContext) : BigDecimal.ZERO;
      BigDecimal sumTotal = (factor != null && factor != 0.) ? MathUtils.multiply(sumProduction, new BigDecimal(factor), roundContext) : sumProduction;
      if (sumTotal.compareTo(endCost) > 0)
        sumTotal = endCost;
      createAmortization(inventory, month, batch, factor, amCode != null ? amCode.getId() : null, bmValue, endCost, result.months, null, vol.floatValue(), factVol.floatValue(), null, null, sumProduction, null, null, sumTotal);
    }
  }

  /* (non-Javadoc)
   * @see com.mg.merp.account.InventoryServiceLocal#revaluate(java.util.List, com.mg.merp.account.AccRevaluateParams)
   */
  @PermitAll
  public void revaluate(List<Inventory> inventories, AccRevaluateParams accRevaluateParams) {
    doRevaluate(inventories, accRevaluateParams);
  }

  /**
   * Переоценка/дооценка
   *
   * @param inventories        - данные по видам учета
   * @param accRevaluateParams - параметры для проведения переоценки
   */
  protected void doRevaluate(List<Inventory> inventories, AccRevaluateParams accRevaluateParams) {
    RoundContext roundContext = new RoundContext(ConfigurationHelper.getConfiguration().getCurrencyPrec());
    PersistentManager pm = ServerUtils.getPersistentManager();
    for (Inventory inventory : inventories) {
      BigDecimal oldAmount = inventory.getBalanceCost();
      BigDecimal oldAmort = inventory.getAmort();
      BigDecimal beginloss = inventory.getBeginLoss();
      BigDecimal initialloss = inventory.getInitialloss();
      BigDecimal newAmount = BigDecimal.ZERO;
      BigDecimal newAmort = null;
      BigDecimal newBeginLoss = null;
      BigDecimal newInitiallos = null;
      String comment;
      BigDecimal factor = BigDecimal.ZERO;
      switch (accRevaluateParams.getKind()) {
        case 0:
          factor = accRevaluateParams.getValue();
          newAmount = MathUtils.multiply(factor, oldAmount, roundContext);
          break;
        case 1:
          newAmount = accRevaluateParams.getValue();
          factor = MathUtils.divide(newAmount, oldAmount, roundContext);
          break;
        case 2:
          newAmount = MathUtils.addNullable(oldAmount, accRevaluateParams.getValue(), roundContext);
          factor = MathUtils.divide(newAmount, oldAmount, roundContext);
          break;
      }
      if (!accRevaluateParams.isOverestimation()) {
        newAmort = MathUtils.multiply(oldAmort, factor, roundContext);
        newBeginLoss = MathUtils.multiply(beginloss, factor, roundContext);
        newInitiallos = MathUtils.multiply(initialloss, factor, roundContext);
        comment = Messages.getInstance().getMessage(Messages.COMMENT_REVALUATE_OVERVALUE);
        inventory.setBalanceCost(newAmount);
        inventory.setBeginLoss(newBeginLoss);
        inventory.setInitialloss(newInitiallos);
        inventory.setAmort(newAmort);
        inventory.setEndCost(MathUtils.subtractNullable(newAmount, MathUtils.addNullable(newBeginLoss, MathUtils.addNullable(newInitiallos, newAmort, roundContext), roundContext), roundContext));
      } else {
        comment = Messages.getInstance().getMessage(Messages.COMMENT_REVALUATE_OVERESTIMATION);
        inventory.setBalanceCost(newAmount);
        inventory.setEndCost(MathUtils.subtractNullable(newAmount, MathUtils.addNullable(beginloss, MathUtils.addNullable(initialloss, oldAmort, roundContext), roundContext), roundContext));
      }
      EconomicSpec es;
      EconomicOper eo = createEconomicOper(accRevaluateParams.getRevalDate(), accRevaluateParams.getFolder(), comment, accRevaluateParams.getDocType(),
          accRevaluateParams.getDocNumber(), accRevaluateParams.getDocDate(), accRevaluateParams.getBaseDocType(), accRevaluateParams.getDocNumber(),
          accRevaluateParams.getBaseDocDate(), inventory.getContractor(), inventory.getContractor());
      if (newAmount != null && oldAmount != null && newAmount.compareTo(oldAmount) > -1) {
        es = createEconomicSpec(eo, inventory.getAccPlan(), inventory.getAnl1(), inventory.getAnl2(), inventory.getAnl3(), inventory.getAnl4(), inventory.getAnl5(), accRevaluateParams.getAccPlan(),
            accRevaluateParams.getAnl1(), accRevaluateParams.getAnl2(), accRevaluateParams.getAnl3(),
            accRevaluateParams.getAnl4(), accRevaluateParams.getAnl5(), inventory.getCatalog(), BigDecimal.ZERO, MathUtils.subtractNullable(newAmount, oldAmount, roundContext));
        if (!accRevaluateParams.isOverestimation()) {
          // :NewAmort+:newbeginloss+:newinitialloss-:OldAmort-:beginloss-:initialloss
          BigDecimal sumNat = MathUtils.addNullable(newAmort, MathUtils.addNullable(newBeginLoss, newInitiallos, roundContext), roundContext);
          sumNat = MathUtils.subtractNullable(sumNat, MathUtils.addNullable(oldAmort, MathUtils.addNullable(beginloss, initialloss, roundContext), roundContext), roundContext);
          createEconomicSpec(eo, accRevaluateParams.getAccPlan(), accRevaluateParams.getAnl1(), accRevaluateParams.getAnl2(), accRevaluateParams.getAnl3(), accRevaluateParams.getAnl4(), accRevaluateParams.getAnl5(),
              inventory.getAccKt(), inventory.getAnlKt1(), inventory.getAnlKt2(), inventory.getAnlKt3(), inventory.getAnlKt4(), inventory.getAnlKt5(),
              inventory.getCatalog(), BigDecimal.ZERO, sumNat);
          es = null;
        }
      } else {
        es = createEconomicSpec(eo, accRevaluateParams.getAccPlan(), accRevaluateParams.getAnl1(), accRevaluateParams.getAnl2(), accRevaluateParams.getAnl3(), accRevaluateParams.getAnl4(), accRevaluateParams.getAnl5(),
            inventory.getAccPlan(), inventory.getAnl1(), inventory.getAnl2(), inventory.getAnl3(), inventory.getAnl4(), inventory.getAnl5(), inventory.getCatalog(), BigDecimal.ZERO, MathUtils.subtractNullable(oldAmount, newAmount, roundContext));
        if (!accRevaluateParams.isOverestimation()) {
          //:OldAmort+:beginloss+:initialloss-:NewAmort-:newbeginloss-:newinitialloss
          BigDecimal sumNat = MathUtils.addNullable(newAmort, MathUtils.addNullable(newBeginLoss, newInitiallos, roundContext), roundContext);
          sumNat = MathUtils.subtractNullable(MathUtils.addNullable(oldAmort, MathUtils.addNullable(beginloss, initialloss, roundContext), roundContext), sumNat, roundContext);
          createEconomicSpec(eo, inventory.getAccKt(), inventory.getAnlKt1(), inventory.getAnlKt2(), inventory.getAnlKt3(), inventory.getAnlKt4(), inventory.getAnlKt5(),
              accRevaluateParams.getAccPlan(), accRevaluateParams.getAnl1(), accRevaluateParams.getAnl2(), accRevaluateParams.getAnl3(), accRevaluateParams.getAnl4(), accRevaluateParams.getAnl5(),
              inventory.getCatalog(), BigDecimal.ZERO, sumNat);
          es = null;
        }
      }
      pm.flush();
      AccountTurnoverUpdater.execute(eo, false);
      createInvHistory(inventory, eo, es, accRevaluateParams.isOverestimation() ? InvActionKind.OVERESTIMATION : InvActionKind.REVAL,
          MathUtils.subtractNullable(newAmount, oldAmount, roundContext), MathUtils.subtractNullable(newAmort, oldAmort, roundContext),
          MathUtils.subtractNullable(newBeginLoss, beginloss, roundContext), MathUtils.subtractNullable(newInitiallos, initialloss, roundContext),
          factor, newAmount, accRevaluateParams.getRevalDate());
    }
  }

  /* (non-Javadoc)
   * @see com.mg.merp.account.InventoryServiceLocal#rollback(java.util.List)
   */
  @PermitAll
  public void rollback(List<Inventory> inventories) {
    doRollback(inventories);
  }

  /**
   * Отменить последнее действие
   *
   * @param inventories - данные по видам учета
   */
  protected void doRollback(List<Inventory> inventories) {
    OrmTemplate ormTemplate = OrmTemplate.getInstance();
    for (Inventory inventory : inventories) {
      List<InvHistory> invHistories = ormTemplate.findByCriteria(OrmTemplate.createCriteria(InvHistory.class)
          .addOrder(Order.desc("Id")) //$NON-NLS-1$
          .add(Restrictions.eq("Inventory", inventory)) //$NON-NLS-1$
          .setFlushMode(FlushMode.MANUAL)
          .setMaxResults(1));
      if (!invHistories.isEmpty()) {
        InvHistory invHistory = invHistories.get(0);
        getInvHistoryService().rollbackInvHistory(invHistory);
      }
    }
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.account.InventoryServiceLocal#moveInventory(java.util.List, com.mg.merp.account.AccInventoryMoveParams)
   */
  @PermitAll
  public void moveInventory(List<Inventory> inventories, AccInventoryMoveParams params) {
    doMoveInventory(inventories, params);
  }

  /**
   * Перемещение
   *
   * @param inventories - инвентарные карточки
   * @param params      - параметры для проведения операции перемещения инвентарных карточек
   */
  protected void doMoveInventory(List<Inventory> inventories, AccInventoryMoveParams params) {
    Contractor contr = params.getContractor();
    EconomicOper eo;
    EconomicSpec es;
    Messages messages = Messages.getInstance();
    PersistentManager pm = ServerUtils.getPersistentManager();
    for (Inventory inventory : inventories) {
      if (contr != null)
        eo = createEconomicOper(params.getRevalDate(), params.getFolder(), messages.getMessage(Messages.COMMENT_MOVE_INVENTORY), params.getDocType(), params.getDocNumber(), params.getDocDate(), null, null, null, inventory.getContractor(), contr);
      else
        eo = createEconomicOper(params.getRevalDate(), params.getFolder(), messages.getMessage(Messages.COMMENT_MOVE_INVENTORY), params.getDocType(), params.getDocNumber(), params.getDocDate(), null, null, null, null, null);
      if (params.getAccPlan() != null) {
        es = createEconomicSpec(eo, params.getAccPlan(), params.getAnl1(), params.getAnl2(), params.getAnl3(), params.getAnl4(), params.getAnl5(),
            inventory.getAccPlan(), inventory.getAnl1(), inventory.getAnl2(), inventory.getAnl3(), inventory.getAnl4(), inventory.getAnl5(),
            inventory.getCatalog(), BigDecimal.ONE, inventory.getBalanceCost());
        if (params.getAccKt() != null)
          createEconomicSpec(eo, inventory.getAccKt(), inventory.getAnlKt1(), inventory.getAnlKt2(), inventory.getAnlKt3(), inventory.getAnlKt4(), inventory.getAnlKt5(),
              params.getAccKt(), params.getAnlKt1(), params.getAnlKt2(), params.getAnlKt3(), params.getAnlKt4(), params.getAnlKt5(),
              inventory.getCatalog(), BigDecimal.ONE, inventory.getAmort());
      } else
        es = createEconomicSpec(eo, inventory.getAccPlan(), inventory.getAnl1(), inventory.getAnl2(), inventory.getAnl3(), inventory.getAnl4(), inventory.getAnl5(),
            inventory.getAccPlan(), inventory.getAnl1(), inventory.getAnl2(), inventory.getAnl3(), inventory.getAnl4(), inventory.getAnl5(),
            inventory.getCatalog(), BigDecimal.ONE, inventory.getBalanceCost());
      pm.flush();
      AccountTurnoverUpdater.execute(eo, false);
      if (contr != null)
        inventory.setContractor(contr);
      if (params.getAccPlan() != null) {
        inventory.setAccPlan(params.getAccPlan());
        inventory.setAnl1(params.getAnl1());
        inventory.setAnl2(params.getAnl2());
        inventory.setAnl3(params.getAnl3());
        inventory.setAnl4(params.getAnl4());
        inventory.setAnl5(params.getAnl5());
      }
      String inOperDocNum = null;
      Date inOperDate = null;
      if (StringUtils.stringNullOrEmpty(params.getInOperDocNum())) {
        inOperDate = inventory.getInOperDate();
        inOperDocNum = inventory.getInOperDocNum();
      }
      Integer invLocationId = inventory.getInvLocation() != null ? inventory.getInvLocation().getId() : null;
      createInvHistory(inventory, eo, es, null, InvActionKind.MOVE, null, null, null, null, null, null, params.getRevalDate(),
          params.getInvLocation() != null ? invLocationId : null, inOperDocNum, inOperDate);
    }
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.account.InventoryServiceLocal#retire(java.util.List, com.mg.merp.account.AccInventoryRetireParams)
   */
  @PermitAll
  public void retire(List<Inventory> inventories, AccInventoryRetireParams params) {
    doRetire(inventories, params);
  }

  /**
   * Списание
   *
   * @param inventories - инвентарные карточки
   * @param params      - параметры для проведения операции списания инвентарных карточек
   */
  protected void doRetire(List<Inventory> inventories, AccInventoryRetireParams params) {
    RoundContext roundContext = new RoundContext(ConfigurationHelper.getConfiguration().getCurrencyPrec());
    Messages messages = Messages.getInstance();
    PersistentManager pm = ServerUtils.getPersistentManager();
    for (Inventory inventory : inventories) {
      EconomicOper eo = createEconomicOper(params.getRevalDate(), params.getFolder(), messages.getMessage(Messages.RETIRE_COMMENT_ECONOMIC_OPER), params.getDocType(), params.getDocNumber(), params.getDocDate(), params.getBaseDocType(), params.getBaseDocNumber(), params.getBaseDocDate(), inventory.getContractor(), null);
      // write off balance cost
      EconomicSpec es = createEconomicSpec(eo, params.getAccPlan(), params.getAnl1(), params.getAnl2(), params.getAnl3(), params.getAnl4(), params.getAnl5(), inventory.getAccPlan(), inventory.getAnl1(), inventory.getAnl2(), inventory.getAnl3(), inventory.getAnl4(), inventory.getAnl5(), inventory.getCatalog(), BigDecimal.ONE, inventory.getBalanceCost());
      // write off depreciation
      createEconomicSpec(eo, inventory.getAccKt(), inventory.getAnlKt1(), inventory.getAnlKt2(), inventory.getAnlKt3(), inventory.getAnlKt4(), inventory.getAnlKt5(), params.getAccPlan(), params.getAnl1(), params.getAnl2(), params.getAnl3(), params.getAnl4(), params.getAnl5(), inventory.getCatalog(), BigDecimal.ONE, MathUtils.addNullable(inventory.getAmort(), inventory.getBeginLoss(), roundContext));
      pm.flush();
      AccountTurnoverUpdater.execute(eo, false);
      inventory.setOutOperDocNum(params.getDocNumber());
      inventory.setOutOperDate(params.getDocDate());
      createInvHistory(inventory, eo, es, InvActionKind.RETIRE, null, null, null, null, null, null, params.getRevalDate());
    }
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.account.InventoryServiceLocal#freeze(java.util.List, java.util.Date)
   */
  @PermitAll
  public void freeze(List<Inventory> inventories, Date freezeDate) {
    doFreeze(inventories, freezeDate);
  }

  /**
   * Консервация
   *
   * @param inventories - инвентарные карточки
   * @param freezeDate  - дата по которую осуществляется консервация инвентарных карточек
   */
  protected void doFreeze(List<Inventory> inventories, Date freezeDate) {
    int absMonth = DateTimeUtils.getYear(freezeDate) * 12 + DateTimeUtils.getMonth(freezeDate);
    for (Inventory inventory : inventories) {
      Amortization amortization = createAmortization(inventory, (short) absMonth, null, null, null, null, null, (short) 0, null, null, null);
      createInvHistory(inventory, null, null, amortization, InvActionKind.FREEZ, null, null, null, null, null, null, DateTimeUtils.nowDate(), null, null, null);
    }
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.account.InventoryServiceLocal#makeRemains(java.util.List, com.mg.merp.account.model.Period)
   */
  @PermitAll
  public void makeRemains(List<Inventory> inventories, Period period) {
    doMakeRemains(inventories, period);
  }

  /**
   * Формирование остатков
   *
   * @param inventories - инвентарные карточки
   * @param period      - учетный период
   */
  protected void doMakeRemains(List<Inventory> inventories, Period period) {
    for (Inventory inventory : inventories) {
      Catalog catalog = inventory.getCatalog();
      AccPlan accPlan = inventory.getAccPlan();
      Contractor contractor = inventory.getContractor();
      AnlPlan anl1 = inventory.getAnl1();
      AnlPlan anl2 = inventory.getAnl2();
      AnlPlan anl3 = inventory.getAnl3();
      AnlPlan anl4 = inventory.getAnl4();
      AnlPlan anl5 = inventory.getAnl5();
      BigDecimal balCost = inventory.getBalanceCost();
      adjustRemnVal(accPlan, anl1, anl2, anl3, anl4, anl5, catalog, contractor, period, balCost);
      adjustRemnAcc(accPlan, period, balCost);
      adjustRemnDbKt(accPlan, anl1, anl2, anl3, anl4, anl5, contractor, period, balCost);
    }
  }

  /**
   * Формирование "Остатки и обороты по счетам бух. учета"
   *
   * @param accPlan - счет
   * @param period  - период
   * @param summa   - сумма
   */
  private void adjustRemnAcc(AccPlan accPlan, Period period, BigDecimal summa) {
    RoundContext roundContext = new RoundContext(ConfigurationHelper.getConfiguration().getCurrencyPrec());
    Integer remnAccId = OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(RemnAcc.class)
        .setProjection(Projections.max("Id")) //$NON-NLS-1$
        .add(Restrictions.eq("AccPlan", accPlan)) //$NON-NLS-1$
        .add(Restrictions.eq("Period", period))); //$NON-NLS-1$
    if (remnAccId == null) {
      RemnAcc remnAcc = getRemnAccService().initialize();
      remnAcc.setAccPlan(accPlan);
      remnAcc.setPeriod(period);
      remnAcc.setRemnBeginNatDb(summa);
      remnAcc.setRemnBeginNatKt(BigDecimal.ZERO);
      remnAcc.setRemnBeginCurDb(BigDecimal.ZERO);
      remnAcc.setRemnBeginCurKt(BigDecimal.ZERO);
      getPersistentManager().persist(remnAcc);
    } else {
      RemnAcc remnAcc = getRemnAccService().load(remnAccId);
      remnAcc.setRemnBeginNatDb(MathUtils.addNullable(remnAcc.getRemnBeginNatDb(), summa, roundContext));
      getPersistentManager().merge(remnAcc);
    }

  }

  /**
   * Формирование "Остатки и обороты по ТМЦ бух. учета"
   *
   * @param accPlan    - счет
   * @param anl1       - аналитика 1-го уровня
   * @param anl2       - аналитика 2-го уровня
   * @param anl3       - аналитика 3-го уровня
   * @param anl4       - аналитика 4-го уровня
   * @param anl5       - аналитика 5-го уровня
   * @param catalog    - товар
   * @param contractor - контрагент
   * @param period     - период
   * @param summa      - сумма
   */
  private void adjustRemnVal(AccPlan accPlan, AnlPlan anl1, AnlPlan anl2, AnlPlan anl3, AnlPlan anl4, AnlPlan anl5, Catalog catalog, Contractor contractor, Period period, BigDecimal summa) {
    RoundContext roundContext = new RoundContext(ConfigurationHelper.getConfiguration().getCurrencyPrec());
    Integer remnValId = OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(RemnVal.class)
        .setProjection(Projections.max("Id")) //$NON-NLS-1$
        .add(Restrictions.eq("AccPlan", accPlan)) //$NON-NLS-1$
        .add(Restrictions.eq("Period", period)) //$NON-NLS-1$
        .add(Restrictions.eq("Catalog", catalog)) //$NON-NLS-1$
        .add(contractor != null ? Restrictions.eq("Contractor", contractor) : Restrictions.isNull("Contractor")) //$NON-NLS-1$ //$NON-NLS-2$
        .add(anl1 != null ? Restrictions.eq("AnlPlan1", anl1) : Restrictions.isNull("AnlPlan1")) //$NON-NLS-1$ //$NON-NLS-2$
        .add(anl2 != null ? Restrictions.eq("AnlPlan2", anl2) : Restrictions.isNull("AnlPlan2")) //$NON-NLS-1$ //$NON-NLS-2$
        .add(anl3 != null ? Restrictions.eq("AnlPlan3", anl3) : Restrictions.isNull("AnlPlan3")) //$NON-NLS-1$ //$NON-NLS-2$
        .add(anl4 != null ? Restrictions.eq("AnlPlan4", anl4) : Restrictions.isNull("AnlPlan4")) //$NON-NLS-1$ //$NON-NLS-2$
        .add(anl5 != null ? Restrictions.eq("AnlPlan5", anl5) : Restrictions.isNull("AnlPlan5")) //$NON-NLS-1$ //$NON-NLS-2$
        .setFlushMode(FlushMode.MANUAL));
    if (remnValId == null) {
      RemnVal remnVal = getRemnValService().initialize();
      remnVal.setAccPlan(accPlan);
      remnVal.setAnlPlan1(anl1);
      remnVal.setAnlPlan1(anl2);
      remnVal.setAnlPlan1(anl3);
      remnVal.setAnlPlan1(anl4);
      remnVal.setAnlPlan1(anl5);
      remnVal.setCatalog(catalog);
      remnVal.setPeriod(period);
      remnVal.setBeginQuan(BigDecimal.ONE);
      remnVal.setRemnBeginNat(summa);
      remnVal.setContractor(contractor);
      getPersistentManager().persist(remnVal);
    } else {
      RemnVal remnVal = getRemnValService().load(remnValId);
      remnVal.setBeginQuan(MathUtils.addNullable(remnVal.getBeginQuan(), BigDecimal.ONE, roundContext));
      remnVal.setRemnBeginNat(MathUtils.addNullable(remnVal.getRemnBeginNat(), summa, roundContext));
      getPersistentManager().merge(remnVal);
    }
  }

  /**
   * Формирование "Ведомость расчетов с контрагентами"
   *
   * @param accPlan    - счет
   * @param anl1       - аналитика 1-го уровня
   * @param anl2       - аналитика 2-го уровня
   * @param anl3       - аналитика 3-го уровня
   * @param anl4       - аналитика 4-го уровня
   * @param anl5       - аналитика 5-го уровня
   * @param contractor - контрагент
   * @param period     - период
   * @param summa      - сумма
   */
  private void adjustRemnDbKt(AccPlan accPlan, AnlPlan anl1, AnlPlan anl2, AnlPlan anl3, AnlPlan anl4, AnlPlan anl5, Contractor contractor, Period period, BigDecimal summa) {
    RoundContext roundContext = new RoundContext(ConfigurationHelper.getConfiguration().getCurrencyPrec());
    Integer remnDbKtId = OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(RemnDbKt.class)
        .setProjection(Projections.max("Id")) //$NON-NLS-1$
        .add(Restrictions.eq("AccPlan", accPlan)) //$NON-NLS-1$
        .add(Restrictions.eq("Period", period)) //$NON-NLS-1$
        .add(contractor != null ? Restrictions.eq("Contractor", contractor) : Restrictions.isNull("Contractor")) //$NON-NLS-1$ //$NON-NLS-2$
        .add(anl1 != null ? Restrictions.eq("AnlPlan1", anl1) : Restrictions.isNull("AnlPlan1")) //$NON-NLS-1$ //$NON-NLS-2$
        .add(anl2 != null ? Restrictions.eq("AnlPlan2", anl2) : Restrictions.isNull("AnlPlan2")) //$NON-NLS-1$ //$NON-NLS-2$
        .add(anl3 != null ? Restrictions.eq("AnlPlan3", anl3) : Restrictions.isNull("AnlPlan3")) //$NON-NLS-1$ //$NON-NLS-2$
        .add(anl4 != null ? Restrictions.eq("AnlPlan4", anl4) : Restrictions.isNull("AnlPlan4")) //$NON-NLS-1$ //$NON-NLS-2$
        .add(anl5 != null ? Restrictions.eq("AnlPlan5", anl5) : Restrictions.isNull("AnlPlan5")) //$NON-NLS-1$ //$NON-NLS-2$
        .setFlushMode(FlushMode.MANUAL));
    if (remnDbKtId == null) {
      RemnDbKt remnVal = getRemnDbKtService().initialize();
      remnVal.setAccPlan(accPlan);
      remnVal.setAnlPlan1(anl1);
      remnVal.setAnlPlan1(anl2);
      remnVal.setAnlPlan1(anl3);
      remnVal.setAnlPlan1(anl4);
      remnVal.setAnlPlan1(anl5);
      remnVal.setPeriod(period);
      remnVal.setRemnBeginNatDb(summa);
      remnVal.setRemnBeginNatKt(BigDecimal.ZERO);
      remnVal.setContractor(contractor);
      getPersistentManager().persist(remnVal);
    } else {
      RemnDbKt remnVal = getRemnDbKtService().load(remnDbKtId);
      remnVal.setRemnBeginNatDb(MathUtils.addNullable(remnVal.getRemnBeginNatDb(), summa, roundContext));
      getPersistentManager().merge(remnVal);
    }
  }

  /**
   * Расчет количества месяцев начисления амортизации
   *
   * @param inventory - данные по видам учета
   * @param aMonth    - месяц начисления амортизации в абсолютном исчислении (год*12 + месяц)
   * @param inDate    - акт ввода в эксплуатацию - дата
   * @param outDate   - акт выбытия из эксплуатации - дата
   */
  protected NumMonthsResult getNumMonths(Inventory inventory, Short month, Date inDate, Date outDate) {
    if (inDate != null) {
      Short absInMonth = (short) (12 * DateTimeUtils.getYear(inDate) + DateTimeUtils.getMonth(inDate));
      if (absInMonth >= month)
        return new NumMonthsResult((short) 0, (short) 0);
      Object[] result = OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(Amortization.class)
          .add(Restrictions.eq("Inventory", inventory)) //$NON-NLS-1$
          .setProjection(Projections.projectionList(Projections.max("IMonth"), Projections.sum("NMonths"))) //$NON-NLS-1$ //$NON-NLS-2$
          .setFlushMode(FlushMode.MANUAL)
          .setResultTransformer(new ResultTransformer<Object[]>() {
            /* (non-Javadoc)
             * @see com.mg.framework.api.orm.ResultTransformer#transformTuple(java.lang.Object[], java.lang.String[])
             */
            public Object[] transformTuple(Object[] tuple, String[] aliases) {
              return tuple;
            }
          }));
      Short lastMonth = (Short) result[0];
      Short numMonths = result[1] != null ? (Short) result[1] : (short) 0;
      if (lastMonth == null || lastMonth == (short) 0 || absInMonth > lastMonth)
        lastMonth = absInMonth;
      Short months = 0;
      if (outDate != null)
        months = DateTimeUtils.getDayOfMonth(outDate) <= 15 ? (short) (12 * DateTimeUtils.getYear(outDate) + DateTimeUtils.getMonth(outDate) - 1) :
            (short) (12 * DateTimeUtils.getYear(outDate) + DateTimeUtils.getMonth(outDate));
      if (month >= lastMonth)
        months = (short) (month - lastMonth);
      return new NumMonthsResult(months, numMonths);
    }
    return new NumMonthsResult((short) 0, (short) 0);
  }

  /**
   * Возвращает бизнес-компонент "Ведомость начисления амортизации"
   */
  private AmortizationServiceLocal getAmortizationService() {
    if (amortizationService == null)
      amortizationService = (AmortizationServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/Amortization"); //$NON-NLS-1$
    return amortizationService;
  }

  /**
   * Возвращает бизнес-компонент "Нормы амортизации"
   */
  private AmRateServiceLocal getAmRateService() {
    if (amRateService == null)
      amRateService = (AmRateServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/AmRate"); //$NON-NLS-1$
    return amRateService;
  }

  /**
   * Создание ведомости начисления амортизации
   *
   * @param inventory   - данные по видам учета
   * @param month       - месяц начисления амортизации в абсолютном исчислении (год*12 + месяц)
   * @param batch       - номер партии
   * @param factor      - поправочный коэффициент
   * @param amCodeId    - идентификатор шифра амортизации
   * @param balanceCost - балансовая стоимость
   * @param endCost     - остаточная стоимость
   * @param months      - число месяцев, за которое была начислена амортизация
   * @param amRate      - норма амортизации
   * @param productEst  - предполагаемый объем продукции
   * @param productFact - фактический объем продукции
   * @param explPeriodY - период эксплуатации, лет
   * @param explPeriodM - период эксплуатации, месяцев
   * @param sumProduct  - сумма, начисленная по произведенной продукции
   * @param sumPeriod   - сумма, начисленная по периоду
   * @param sumRate     - сумма, начисленная амортизацией
   * @param sumTotal    - итоговая сумма начисленной амортизации
   */
  private Amortization createAmortization(Inventory inventory, Short month, Integer batch, Float factor, Integer amCodeId, BigDecimal balanceCost,
                                          BigDecimal endCost, Short months, BigDecimal amRate, Float productEst, Float productFact, Float explPeriodY, Float explPeriodM,
                                          BigDecimal sumProduct, BigDecimal sumPeriod, BigDecimal sumRate, BigDecimal sumTotal) {
    Amortization amortization = getAmortizationService().initialize();
    amortization.setInventory(inventory);
    amortization.setIMonth(month);
    amortization.setBatch(batch);
    amortization.setFactor(factor);
    amortization.setAmCodeId(amCodeId);
    amortization.setBalanceCost(balanceCost);
    amortization.setEndCost(endCost);
    amortization.setNMonths(months);
    amortization.setAmRate(amRate);
    amortization.setExplPeriodY(explPeriodY);
    amortization.setExplPeriodM(explPeriodM);
    amortization.setProductEst(productEst);
    amortization.setProductFact(productFact);
    amortization.setSumPeriod(sumPeriod);
    amortization.setSumProduct(sumProduct);
    amortization.setSumRate(sumRate);
    amortization.setSumTotal(sumTotal);
    getAmortizationService().create(amortization);
    return amortization;
  }

  /**
   * Создание ведомости начисления амортизации
   *
   * @param inventory   - данные по видам учета
   * @param month       - месяц начисления амортизации в абсолютном исчислении (год*12 + месяц)
   * @param batch       - номер партии
   * @param factor      - поправочный коэффициент
   * @param amCodeId    - идентификатор шифра амортизации
   * @param balanceCost - балансовая стоимость
   * @param endCost     - остаточная стоимость
   * @param months      - число месяцев, за которое была начислена амортизация
   * @param amRate      - норма амортизации
   * @param sumRate     - сумма, начисленная амортизацией
   * @param sumTotal    - итоговая сумма начисленной амортизации
   */
  private Amortization createAmortization(Inventory inventory, Short month, Integer batch, Float factor, Integer amCodeId, BigDecimal balanceCost, BigDecimal endCost, Short months, BigDecimal amRate, BigDecimal sumRate, BigDecimal sumTotal) {
    return createAmortization(inventory, month, batch, factor, amCodeId, balanceCost, endCost, months, amRate, null, null, null, null, null, null, sumRate, sumTotal);
  }

  /**
   * Создание хоз. операции
   *
   * @param keepDate      - дата хоз. операции
   * @param folder        - папка приемник
   * @param comment       - комментарий
   * @param docType       - тип документ
   * @param docNumber     - номер документа
   * @param docDate       - дата документа
   * @param baseDocType   - тип документа-основания
   * @param baseDocNumber - номер документа-основания
   * @param baseDocDate   - дата документа- основания
   * @param from          - от кого
   * @param to            - кому
   */
  private EconomicOper createEconomicOper(Date keepDate, Folder folder, String comment, DocType docType, String docNumber, Date docDate, DocType baseDocType, String baseDocNumber, Date baseDocDate, Contractor from, Contractor to) {
    EconomicOper economicOper = getOperationService().initialize();
    economicOper.setKeepDate(keepDate);
    economicOper.setFolder(folder);
    economicOper.setComment(comment);
    economicOper.setConfirmDocType(docType);
    economicOper.setConfirmDocNumber(docNumber);
    economicOper.setConfirmDocDate(docDate);
    economicOper.setBaseDocType(baseDocType);
    economicOper.setBaseDocNumber(baseDocNumber);
    economicOper.setBaseDocDate(baseDocDate);
    economicOper.setFrom(from);
    economicOper.setTo(to);
    getOperationService().create(economicOper);
    return economicOper;
  }

  /**
   * Создание проводки хоз. операции
   *
   * @param eo       - хоз. операция
   * @param accDb    - счет дебет
   * @param anlDb1   - аналитика 1-го уровня счета-дебета
   * @param anlDb2   - аналитика 2-го уровня счета-дебета
   * @param anlDb3   - аналитика 3-го уровня счета-дебета
   * @param anlDb4   - аналитика 4-го уровня счета-дебета
   * @param anlDb5   - аналитика 5-го уровня счета-дебета
   * @param accKt    - счет-кредит
   * @param anlKt1   - аналитика 1-го уровня счета-кредита
   * @param anlKt2   - аналитика 2-го уровня счета-кредита
   * @param anlKt3   - аналитика 3-го уровня счета-кредита
   * @param anlKt4   - аналитика 4-го уровня счета-кредита
   * @param anlKt5   - аналитика 5-го уровня счета-кредита
   * @param catalog  - товар
   * @param quatity  - количество
   * @param summaNat - сумма в НДЕ
   */
  protected EconomicSpec createEconomicSpec(EconomicOper eo, AccPlan accDb, AnlPlan anlDb1, AnlPlan anlDb2, AnlPlan anlDb3, AnlPlan anlDb4, AnlPlan anlDb5,
                                            AccPlan accKt, AnlPlan anlKt1, AnlPlan anlKt2, AnlPlan anlKt3, AnlPlan anlKt4, AnlPlan anlKt5, Catalog catalog, BigDecimal quatity, BigDecimal summaNat) {
    EconomicSpec es = getEconomicSpecService().initialize();
    es.setBulkOperation(true);
    es.setEconomicOper(eo);
    es.setAccDb(accDb);
    es.setAnlDb1(anlDb1);
    es.setAnlDb2(anlDb2);
    es.setAnlDb3(anlDb3);
    es.setAnlDb4(anlDb4);
    es.setAnlDb5(anlDb5);
    es.setAccKt(accKt);
    es.setAnlKt1(anlKt1);
    es.setAnlKt2(anlKt2);
    es.setAnlKt3(anlKt3);
    es.setAnlKt4(anlKt4);
    es.setAnlKt5(anlKt5);
    es.setCatalog(catalog);
    es.setQuantity(quatity);
    es.setSummaNat(summaNat);
    getEconomicSpecService().create(es);
    return es;
  }

  /**
   * Создание истории действия, произведенных над инвентарной карточкой
   *
   * @param inventory        - данные по видам учета
   * @param eo               - хоз. операция
   * @param es               - проводка хоз. операции
   * @param kind             - тип записи истории
   * @param deltaBalCost     - изменение балансовой стоимости
   * @param deltaDeprVal     - изменение остаточной стоимости, при переоценке - изменение
   *                         начисленной амортизации
   * @param deltaBeginLoss   - при переоценке - изменение начального износа
   * @param deltaInitialLoss - при переоценке - изменение износа при создании карточки
   * @param revalFactor      - коэффициент переоценки
   * @param revalSum         - сумма переоценки
   * @param actDate          - дата произведенного действия
   */
  private void createInvHistory(Inventory inventory, EconomicOper eo, EconomicSpec es, InvActionKind kind, BigDecimal deltaBalCost, BigDecimal deltaDeprVal, BigDecimal deltaBeginLoss, BigDecimal deltaInitialLoss, BigDecimal revalFactor, BigDecimal revalSum, Date actDate) {
    createInvHistory(inventory, eo, es, null, kind, deltaBalCost, deltaDeprVal, deltaBeginLoss, deltaInitialLoss, revalFactor, revalSum, actDate, null, null, null);
  }

  /**
   * Создание истории действия, произведенных над инвентарной карточкой
   *
   * @param inventory        - данные по видам учета
   * @param eo               - хоз. операция
   * @param es               - проводка хоз. операции
   * @param kind             - тип записи истории
   * @param deltaBalCost     - изменение балансовой стоимости
   * @param deltaDeprVal     - изменение остаточной стоимости, при переоценке - изменение
   *                         начисленной амортизации
   * @param deltaBeginLoss   - при переоценке - изменение начального износа
   * @param deltaInitialLoss - при переоценке - изменение износа при создании карточки
   * @param revalFactor      - коэффициент переоценки
   * @param revalSum         - сумма переоценки
   * @param actDate          - дата произведенного действия
   * @param oldInvLocationId - прежний идентификатор местонахождения
   * @param oldInOperDocNum  - номер акта ввода в эксплуатацию
   * @param oldInOperDate    - дата акта ввода в эксплуатацию
   */
  private void createInvHistory(Inventory inventory, EconomicOper eo, EconomicSpec es, Amortization accAmort, InvActionKind kind, BigDecimal deltaBalCost, BigDecimal deltaDeprVal, BigDecimal deltaBeginLoss, BigDecimal deltaInitialLoss, BigDecimal revalFactor, BigDecimal revalSum, Date actDate, Integer oldInvLocationId, String oldInOperDocNum, Date oldInOperDate) {
    InvHistory invHistory = getInvHistoryService().initialize();
    invHistory.setInventory(inventory);
    invHistory.setAccAmortization(accAmort);
    invHistory.setEconomicOper(eo);
    invHistory.setEconomicSpec(es);
    invHistory.setKind(kind);
    invHistory.setDeltaBalCost(deltaBalCost);
    invHistory.setDeltaDeprVal(deltaDeprVal);
    invHistory.setDeltaBeginLoss(deltaBeginLoss);
    invHistory.setDeltaInitialLoss(deltaInitialLoss);
    invHistory.setRevalFactor(revalFactor);
    invHistory.setRevalSum(revalSum);
    invHistory.setActDate(actDate);
    invHistory.setOldInvLocationId(oldInvLocationId);
    invHistory.setOldInOperDocNum(oldInOperDocNum);
    invHistory.setOldInOperDate(oldInOperDate);
    getInvHistoryService().create(invHistory);
  }

  /**
   * Возвращает бизнес-компонент "Спецификация хоз. операции"
   */
  private EconomicSpecServiceLocal getEconomicSpecService() {
    if (economicSpecService == null)
      economicSpecService = (EconomicSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/EconomicSpec"); //$NON-NLS-1$
    return economicSpecService;
  }

  /**
   * Возвращет бизнес-компонент "Хоз. операция"
   */
  private OperationServiceLocal getOperationService() {
    if (operationService == null)
      operationService = (OperationServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/Operation"); //$NON-NLS-1$
    return operationService;
  }

  /**
   * Возвращает бизнес-компонент "История инвентраной карточки"
   */
  private InvHistoryServiceLocal getInvHistoryService() {
    if (invHistoryService == null)
      invHistoryService = (InvHistoryServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/InvHistory"); //$NON-NLS-1$
    return invHistoryService;
  }

  /**
   * Возвращает бизнес-компонент "Остатки и обороты по ТМЦ бух. учета"
   */
  private RemnValServiceLocal getRemnValService() {
    if (remnValService == null)
      remnValService = (RemnValServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/RemnVal"); //$NON-NLS-1$
    return remnValService;
  }

  /**
   * Возвращает бизнес-компонент "Остатки и обороты по счетам бух. учета"
   */
  private RemnAccServiceLocal getRemnAccService() {
    if (remnAccService == null)
      remnAccService = (RemnAccServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/RemnAcc"); //$NON-NLS-1$
    return remnAccService;
  }

  /**
   * Возвращает бизнес-компонент "Ведомость расчетов с контрагентами"
   */
  private RemnDbKtServiceLocal getRemnDbKtService() {
    if (remnDbKtService == null)
      remnDbKtService = (RemnDbKtServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/RemnDbKt"); //$NON-NLS-1$
    return remnDbKtService;
  }

  /**
   * Класс результат выполнения функции {@link #getNumMonths()}
   */
  private class NumMonthsResult {
    private short months;
    private short numMonths;

    public NumMonthsResult(short months, short numMonths) {
      this.months = months;
      this.numMonths = numMonths;
    }
  }

}