/*
 * CostProcessorServiceBean.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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

package com.mg.merp.manufacture.support;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.MathUtils;
import com.mg.merp.manufacture.CostProcessorServiceLocal;
import com.mg.merp.manufacture.JobServiceLocal;
import com.mg.merp.manufacture.StandartCostNotFoundException;
import com.mg.merp.manufacture.model.Job;
import com.mg.merp.manufacture.model.JobLabor;
import com.mg.merp.manufacture.model.JobMachine;
import com.mg.merp.manufacture.model.JobMaterial;
import com.mg.merp.manufacture.model.JobRoute;
import com.mg.merp.mfreference.BOMServiceLocal;
import com.mg.merp.mfreference.CostDetailLineItem;
import com.mg.merp.mfreference.CostDetailLineServiceLocal;
import com.mg.merp.mfreference.model.Bom;
import com.mg.merp.mfreference.model.BomLabor;
import com.mg.merp.mfreference.model.BomMachine;
import com.mg.merp.mfreference.model.BomMaterial;
import com.mg.merp.mfreference.model.BomRoute;
import com.mg.merp.mfreference.model.CostCategories;
import com.mg.merp.mfreference.model.CostDetail;
import com.mg.merp.mfreference.model.CostDetailLine;
import com.mg.merp.mfreference.model.LaborClass;
import com.mg.merp.mfreference.model.LaborOverheadAllocationFlag;
import com.mg.merp.mfreference.model.MachineOverheadAllocationFlag;
import com.mg.merp.mfreference.model.MachineRecoveryFlag;
import com.mg.merp.mfreference.model.MaterialOverheadAllocationFlag;
import com.mg.merp.mfreference.model.QuantityRateFlag;
import com.mg.merp.mfreference.model.TimeRateFlag;
import com.mg.merp.mfreference.support.ConfigurationHelper;
import com.mg.merp.mfreference.support.MfUtils;
import com.mg.merp.reference.CatalogPriceServiceLocal;
import com.mg.merp.reference.CurrencyServiceLocal;
import com.mg.merp.reference.MeasureConversionServiceLocal;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.CatalogPrice;
import com.mg.merp.reference.model.Currency;
import com.mg.merp.reference.model.CurrencyRateAuthority;
import com.mg.merp.reference.model.CurrencyRateType;
import com.mg.merp.reference.model.Measure;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

/**
 * Бизнес-компонет "Расчет нормативной цены состава изделия"
 *
 * @author Oleg V. Safonov
 * @version $Id: CostProcessorServiceBean.java,v 1.6 2007/08/06 12:44:54 safonov Exp $
 */
@Stateless(name = "merp/manufacture/CostProcessorService")
public class CostProcessorServiceBean extends com.mg.framework.generic.AbstractPOJOBusinessObjectStatelessServiceBean implements CostProcessorServiceLocal {

  private void clearCostDetailLine(CostDetail costDetail) {
    MfUtils.clearCostDetailLine(costDetail);
  }

  @SuppressWarnings("unchecked")
  private List<Bom> loadBOMTargetList() {
    return OrmTemplate.getInstance().findByNamedQuery("Manufacture.CostProcessor.loadBOMTarget");
  }

  @SuppressWarnings("unchecked")
  private List<Job> loadJobTargetList() {
    return OrmTemplate.getInstance().findByNamedQuery("Manufacture.CostProcessor.loadJobTarget");
  }

  private void updateCostDetailLineSeq(Date actualityDate, List<CostDetailLineItem> ownerCost, List<CostDetailLineItem> itemCost,
                                       Currency srcCurrency, Currency dstCurrency) {
    CurrencyServiceLocal currencyService = null;
    if (srcCurrency.getId() != dstCurrency.getId())
      currencyService = (CurrencyServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CurrencyServiceLocal.LOCAL_SERVICE_NAME);

    CurrencyRateAuthority currencyRateAuthority = ConfigurationHelper.getConfiguration().getCurrencyRateAuthority();
    CurrencyRateType currencyRateType = ConfigurationHelper.getConfiguration().getCurrencyRateType();
    for (CostDetailLineItem item : itemCost) {
      if (item.getCostCategory() == null)
        continue;

      BigDecimal cost = item.getCost();
      if (currencyService != null)
        cost = currencyService.conversion(dstCurrency, srcCurrency, currencyRateAuthority, currencyRateType, actualityDate, cost);

      boolean findFlag = false;
      for (CostDetailLineItem ownerItem : ownerCost)
        if (ownerItem.getCostCategory().getId() == item.getCostCategory().getId()) {
          ownerItem.setCost(ownerItem.getCost().add(cost));
          findFlag = true;
          break;
        }

      //создадим элемент с категорией затрат, т.к. не было в списке
      if (!findFlag)
        ownerCost.add(new CostDetailLineItem(item.getCostCategory(), cost));
    }
  }

  private void createCostDetailLine(BigDecimal quantity, List<CostDetailLineItem> items, CostDetail costDetail, Currency currency) {
    CostDetailLineServiceLocal costDetailLineService = ((CostDetailLineServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CostDetailLineServiceLocal.SERVICE_NAME));
    for (CostDetailLineItem item : items) {
      BigDecimal cost = quantity.multiply(item.getCost());
      //учитываем элементы стоимости только с категориями
      if (item.getCostCategory() == null || MathUtils.compareToZero(cost) == 0)
        continue;

      item.setCost(cost);
      CostDetailLine line = costDetailLineService.initialize();
      line.setCostDetail(costDetail);
      line.setCostCategories(item.getCostCategory());
      line.setSumma(cost);
      line.setCurrency(currency);
      costDetailLineService.create(line);
    }
  }

  private BigDecimal calculateTotalCost(List<CostDetailLineItem> items) {
    BigDecimal totalSum = BigDecimal.ZERO;
    for (CostDetailLineItem item : items)
      totalSum = totalSum.add(item.getCost());

    return totalSum;
  }

  private void performOverheadCost(Date actualityDate, List<CostDetailLineItem> ownerCost, BigDecimal cost, CostDetail costDetail, CostCategories costCategory, Currency currency) {
    List<CostDetailLineItem> ohLabCost = new ArrayList<CostDetailLineItem>();
    ohLabCost.add(new CostDetailLineItem(costCategory, cost));
    createCostDetailLine(BigDecimal.ONE, ohLabCost, costDetail, currency);
    //изменим стоимость операции
    updateCostDetailLineSeq(actualityDate, ownerCost, ohLabCost, currency, ConfigurationHelper.getConfiguration().getBaseCurrency());
  }

  private BigDecimal calculateLaborCost(long runTicksLbr, CostDetail costDetail, Date actualityDate, BigDecimal targetQuan, List<CostDetailLineItem> ownerCost,
            /*BigDecimal operTime,*/ TimeRateFlag timeRateFlag, BigDecimal lbrRate, CostCategories costCategories,
                                        Currency rateCurrency, boolean isLbrOhBackflushFlag, LaborOverheadAllocationFlag laborOhAllocationFlag, BigDecimal lbrOhRate, BigDecimal lbrOhRatio,
                                        CostCategories ohCostCategory, Currency ohRateCurrency) {
    BigDecimal tLabor = BigDecimal.ONE;
    //BigDecimal result = BigDecimal.ZERO;
    switch (timeRateFlag) {
      case RATE:
      case TIME:
        tLabor = MfUtils.tickToTime(runTicksLbr, MfUtils.HOUR);
        break;
      case FIXED:
        tLabor = MfUtils.tickToTime(runTicksLbr, MfUtils.HOUR).divide(targetQuan);
        break;
    }
    //result = operTime.add(tLabor);

    List<CostDetailLineItem> labCost = new ArrayList<CostDetailLineItem>();
    labCost.add(new CostDetailLineItem(costCategories, tLabor.multiply(lbrRate)));
    createCostDetailLine(BigDecimal.ONE, labCost, costDetail, rateCurrency);
    //изменим стоимость операции
    updateCostDetailLineSeq(actualityDate, ownerCost, labCost, rateCurrency, ConfigurationHelper.getConfiguration().getBaseCurrency());

    if (isLbrOhBackflushFlag) {
      //для НР на рабочую силу
      BigDecimal cost = BigDecimal.ZERO;
      switch (laborOhAllocationFlag) {
        case TIME:
          cost = tLabor.multiply(lbrOhRate);
          break;
        case UNIT:
          cost = lbrOhRate;
          break;
        case COST:
          cost = calculateTotalCost(labCost).multiply(lbrOhRatio);
          break;
        case FIXED:
          cost = lbrOhRatio.divide(targetQuan);
          break;
      }
      performOverheadCost(actualityDate, ownerCost, cost, costDetail, ohCostCategory, ohRateCurrency);
    }

    return tLabor;
  }

  private BigDecimal calculateLaborCost(BomRoute bomRoute, BigDecimal targetQuan, Date actualityDate, List<CostDetailLineItem> ownerCost) {
    List<BomLabor> bomLabors = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(BomLabor.class)
        .add(Restrictions.eq("BomRoute", bomRoute))
        .add(Restrictions.le("EffOnDate", actualityDate))
        .add(Restrictions.ge("EffOffDate", actualityDate)));
    BigDecimal result = BigDecimal.ZERO; //время операции

    for (BomLabor bomLabor : bomLabors) {
      clearCostDetailLine(bomLabor.getStandartCostDetail());
      LaborClass laborClass = bomLabor.getLaborClass();
      result = result.add(calculateLaborCost(bomLabor.getRunTicksLbr(), bomLabor.getStandartCostDetail(), actualityDate, targetQuan, ownerCost, laborClass.getTimeRateFlag(),
          laborClass.getLbrRate(), laborClass.getLbrCostCategory(), laborClass.getLbrRateCurrency(),
          bomLabor.isLbrOhBackflushFlag(), laborClass.getLbrOhAllocationFlag(), laborClass.getLbrOhRate(), laborClass.getLbrOhRatio(),
          laborClass.getLbrOhCostCategory(), laborClass.getLbrOhRateCurrency()));
    }

    return result;
  }

  private BigDecimal calculateLaborCost(JobRoute jobRoute, BigDecimal targetQuan, Date actualityDate, List<CostDetailLineItem> ownerCost) {
    List<JobLabor> jobLabors = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(JobLabor.class)
        .add(Restrictions.eq("Oper", jobRoute))
        .add(Restrictions.le("EffOnDate", actualityDate))
        .add(Restrictions.ge("EffOffDate", actualityDate)));
    BigDecimal result = BigDecimal.ZERO; //время операции

    for (JobLabor jobLabor : jobLabors) {
      clearCostDetailLine(jobLabor.getStdCostDetail());
      result = result.add(calculateLaborCost(jobLabor.getRunTicksLbr(), jobLabor.getStdCostDetail(), actualityDate, targetQuan, ownerCost, jobLabor.getTimeRateFlag(),
          jobLabor.getLbrRate(), jobLabor.getLbrCostCategory(), jobLabor.getLbrRateCurrency(),
          jobLabor.isLbrOhBackflushFlag(), jobLabor.getLbrOhAllocationFlag(), jobLabor.getLbrOhRate(), jobLabor.getLbrOhRatio(),
          jobLabor.getLbrOhCostCategory(), jobLabor.getLbrOhRateCurrency()));
    }

    return result;
  }

  private List<CostDetailLineItem> calculateUnitMaterialCost(Catalog catalog, Date actualityDate, Currency currency,
                                                             Measure measure, CostCategories costCategory) {
    List<CostDetailLineItem> result = new ArrayList<CostDetailLineItem>();
    BOMServiceLocal bomService = (BOMServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(BOMServiceLocal.SERVICE_NAME);
    Bom standartBom = bomService.findStandartBOM(catalog.getId());
    if (standartBom == null) {
      //простой материал, возмем цену из КТУ
      CatalogPrice catalogPrice = ((CatalogPriceServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CatalogPriceServiceLocal.SERVICE_NAME)).findActual(actualityDate, catalog, currency);
      if (catalogPrice == null)
        throw new StandartCostNotFoundException(catalog);

      BigDecimal ratio = BigDecimal.ONE;
      if (catalog.getMeasure1().getId() != measure.getId()) {
        ratio = ((MeasureConversionServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(MeasureConversionServiceLocal.LOCAL_SERVICE_NAME))
            .conversion(measure, catalog.getMeasure1(), catalog, actualityDate, BigDecimal.ONE);
      }
      result.add(new CostDetailLineItem(costCategory, catalogPrice.getPrice().multiply(ratio)));
    } else {
      //материал (полуфабрикат)
      result = ((CostDetailLineServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CostDetailLineServiceLocal.SERVICE_NAME))
          .calculateCost(standartBom.getStandartCostDetail(), costCategory == null);
      if (result.isEmpty())
        throw new IllegalStateException();
      //если не Null то всегда один элемент массива
      if (costCategory != null)
        result.get(0).setCostCategory(costCategory);
    }
    return result;
  }

  private void calculateMaterialCost(BigDecimal operTime, List<CostDetailLineItem> ownerCost, Date actualityDate, Catalog catalog, Measure measure,
                                     QuantityRateFlag quantityRateFlag, BigDecimal targetQuan,
                                     BigDecimal mtlQty, BigDecimal scrapFactor, CostDetail costDetail, Currency currency, boolean isOhBackflush,
                                     MaterialOverheadAllocationFlag ohAllocationFlag, BigDecimal mtlOhRate, BigDecimal mtlOhRatio,
                                     CostCategories costCategory, CostCategories ohCostCategory) {
    clearCostDetailLine(costDetail);

    BigDecimal quan = BigDecimal.ZERO;
    switch (quantityRateFlag) {
      case TIME:
        quan = operTime.multiply(mtlQty).divide(BigDecimal.ONE.subtract(scrapFactor));
        break;
      case UNIT:
        quan = mtlQty.divide(BigDecimal.ONE.subtract(scrapFactor));
        break;
      case FIXED:
        quan = mtlQty.divide(BigDecimal.ONE.subtract(scrapFactor).multiply(targetQuan));
        break;
    }

    List<CostDetailLineItem> mtlCost = calculateUnitMaterialCost(catalog, actualityDate, currency, measure, costCategory);
    createCostDetailLine(quan, mtlCost, costDetail, currency);
    //изменим стоимость операции
    updateCostDetailLineSeq(actualityDate, ownerCost, mtlCost, currency, ConfigurationHelper.getConfiguration().getBaseCurrency());

    if (isOhBackflush) {
      //для НР на материалы
      BigDecimal cost = BigDecimal.ZERO;
      switch (ohAllocationFlag) {
        case UNIT:
          cost = quan.multiply(mtlOhRate);
          break;
        case COST:
          cost = calculateTotalCost(mtlCost).multiply(mtlOhRatio);
          break;
        case FIXED:
          cost = mtlOhRate.divide(targetQuan);
          break;
      }
      performOverheadCost(actualityDate, ownerCost, cost, costDetail, ohCostCategory, currency);
    }
  }

  private void calculateMaterialCost(BomRoute bomRoute, BigDecimal targetQuan, BigDecimal operTime, Date actualityDate, List<CostDetailLineItem> ownerCost) {
    List<BomMaterial> bomMaterials = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(BomMaterial.class)
        .add(Restrictions.eq("BomRoute", bomRoute))
        .add(Restrictions.le("EffOnDate", actualityDate))
        .add(Restrictions.ge("EffOffDate", actualityDate)));

    for (BomMaterial bomMaterial : bomMaterials) {
      calculateMaterialCost(operTime, ownerCost, actualityDate, bomMaterial.getCatalog(), bomMaterial.getMeasure(), bomMaterial.getQuantityRateFlag(), targetQuan, bomMaterial.getMtlQty(),
          bomMaterial.getScrapFactor(), bomMaterial.getStandartCostDetail(), bomMaterial.getCurrency(), bomMaterial.isMtlOhBackflushFlag(),
          bomMaterial.getMtlOhAllocationFlag(), bomMaterial.getMtlOhRate(), bomMaterial.getMtlOhRatio(),
          bomMaterial.getMtlCostCategory(), bomMaterial.getMtlOhCostCategory());
    }
  }

  private void calculateMaterialCost(JobRoute jobRoute, BigDecimal targetQuan, BigDecimal operTime, Date actualityDate, List<CostDetailLineItem> ownerCost) {
    List<JobMaterial> jobMaterials = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(JobMaterial.class)
        .add(Restrictions.eq("Oper", jobRoute))
        .add(Restrictions.le("EffOnDate", actualityDate))
        .add(Restrictions.ge("EffOffDate", actualityDate)));

    for (JobMaterial jobMaterial : jobMaterials) {
      calculateMaterialCost(operTime, ownerCost, actualityDate, jobMaterial.getCatalog(), jobMaterial.getMeasure(), jobMaterial.getQuantityRateFlag(), targetQuan, jobMaterial.getMtlQty(),
          jobMaterial.getScrapFactor(), jobMaterial.getStdCostDetail(), jobMaterial.getCurrency(), jobMaterial.getMtlOhBackflushFlag(),
          jobMaterial.getMtlOhAllocationFlag(), jobMaterial.getMtlOhRate(), jobMaterial.getMtlOhRatio(),
          jobMaterial.getMtlCostCategory(), jobMaterial.getMtlOhCostCategory());
    }
  }

  private void calculateMachineCost(Date actualityDate, List<CostDetailLineItem> ownerCost, MachineRecoveryFlag recoveryFlag, TimeRateFlag timeRateFlag, BigDecimal targetQuan, CostDetail costDetail,
                                    long runTicksMch, BigDecimal mchRate, CostCategories costCategory, Currency currency, boolean isOhBackflush,
                                    MachineOverheadAllocationFlag ohAllocationFlag, BigDecimal mchOhRate, BigDecimal mchOhRatio, CostCategories ohCostCategory, Currency ohCurrency) {
    clearCostDetailLine(costDetail);

    BigDecimal cost = BigDecimal.ZERO, tMach = BigDecimal.ONE;
    if (recoveryFlag != null)
      switch (recoveryFlag) {
        case TIME:
          if (timeRateFlag == null)
            throw new IllegalArgumentException("TimeRateFlag is null");
          switch (timeRateFlag) {
            case TIME:
            case RATE:
              tMach = MfUtils.tickToTime(runTicksMch, MfUtils.HOUR);
              break;
            case FIXED:
              tMach = MfUtils.tickToTime(runTicksMch, MfUtils.HOUR).divide(targetQuan);
              break;
          }
          cost = tMach.multiply(mchRate);
          break;
        case UNIT:
          cost = mchRate;
          break;
        case FIXED:
          cost = mchRate.multiply(targetQuan);
          break;
      }

    List<CostDetailLineItem> mchCost = new ArrayList<CostDetailLineItem>();
    mchCost.add(new CostDetailLineItem(costCategory, cost));
    createCostDetailLine(BigDecimal.ONE, mchCost, costDetail, currency);
    //изменим стоимость операции
    updateCostDetailLineSeq(actualityDate, ownerCost, mchCost, currency, ConfigurationHelper.getConfiguration().getBaseCurrency());

    if (isOhBackflush) {
      //для НР на оборудование
      cost = BigDecimal.ZERO;
      switch (ohAllocationFlag) {
        case TIME:
          cost = tMach.multiply(mchOhRate);
          break;
        case UNIT:
          cost = mchOhRate;
          break;
        case COST:
          cost = calculateTotalCost(mchCost).multiply(mchOhRatio);
          break;
        case FIXED:
          cost = mchOhRate.divide(targetQuan);
          break;
      }
      performOverheadCost(actualityDate, ownerCost, cost, costDetail, ohCostCategory, ohCurrency);
    }
  }

  private void calculateMachineCost(BomRoute bomRoute, BigDecimal targetQuan, BigDecimal operTime, Date actualityDate, List<CostDetailLineItem> ownerCost) {
    List<BomMachine> bomMachines = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(BomMachine.class)
        .add(Restrictions.eq("BomRoute", bomRoute))
        .add(Restrictions.le("EffOnDate", actualityDate))
        .add(Restrictions.ge("EffOffDate", actualityDate)));

    for (BomMachine bomMachine : bomMachines) {
      calculateMachineCost(actualityDate, ownerCost, bomMachine.getMchRecoveryFlag(), bomMachine.getTimeRateFlag(), targetQuan,
          bomMachine.getStandartCostDetail(), bomMachine.getRunTicksMch(), bomMachine.getMchOhRate(), bomMachine.getMchCostCategory(),
          bomMachine.getMchRateCurrency(), bomMachine.isMchOhBackflushFlag(), bomMachine.getMchOhAllocationFlag(),
          bomMachine.getMchOhRate(), bomMachine.getMchOhRatio(), bomMachine.getMchOhCostCategory(), bomMachine.getMchOhRateCurrency());
    }
  }

  private void calculateMachineCost(JobRoute jobRoute, BigDecimal targetQuan, BigDecimal operTime, Date actualityDate, List<CostDetailLineItem> ownerCost) {
    List<JobMachine> jobMachines = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(JobMachine.class)
        .add(Restrictions.eq("Oper", jobRoute))
        .add(Restrictions.le("EffOnDate", actualityDate))
        .add(Restrictions.ge("EffOffDate", actualityDate)));

    for (JobMachine jobMachine : jobMachines) {
      calculateMachineCost(actualityDate, ownerCost, jobMachine.getMchRecoveryFlag(), jobMachine.getTimeRateFlag(), targetQuan,
          jobMachine.getStdCostDetail(), jobMachine.getRunTicksMch(), jobMachine.getMchOhRate(), jobMachine.getMchCostCategory(),
          jobMachine.getMchRateCurrency(), jobMachine.getMchOhBackflushFlag(), jobMachine.getMchOhAllocationFlag(),
          jobMachine.getMchOhRate(), jobMachine.getMchOhRatio(), jobMachine.getMchOhCostCategory(), jobMachine.getMchOhRateCurrency());
    }
  }

  private void calculateRouteCost(Bom bom, BigDecimal targetQuan, Date actualityDate, List<CostDetailLineItem> ownerCost) {
    List<BomRoute> bomRoutes = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(BomRoute.class)
        .add(Restrictions.eq("Bom", bom))
        .add(Restrictions.le("EffOnDate", actualityDate))
        .add(Restrictions.ge("EffOffDate", actualityDate)));
    Currency baseCurrency = ConfigurationHelper.getConfiguration().getBaseCurrency();

    for (BomRoute bomRoute : bomRoutes) {
      clearCostDetailLine(bomRoute.getStandartCostDetail());
      List<CostDetailLineItem> routeCost = new ArrayList<CostDetailLineItem>();
      //вычислим для ресурсов каждой операции
      //первым вычислим для рабочей силы, результаты используются в дальнейших вычислениях
      BigDecimal operTime = calculateLaborCost(bomRoute, targetQuan, actualityDate, routeCost);
      calculateMaterialCost(bomRoute, targetQuan, operTime, actualityDate, routeCost);
      calculateMachineCost(bomRoute, targetQuan, operTime, actualityDate, routeCost);

      //создадим себестоимость для операции
      createCostDetailLine(BigDecimal.ONE, routeCost, bomRoute.getStandartCostDetail(), baseCurrency);

      //изменим стоимость БОМа
      updateCostDetailLineSeq(actualityDate, ownerCost, routeCost, baseCurrency, baseCurrency);
    }
  }

  private void calculateRouteCost(Job job, BigDecimal targetQuan, Date actualityDate, List<CostDetailLineItem> ownerCost) {
    List<JobRoute> jobRoutes = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(JobRoute.class)
        .add(Restrictions.eq("Job", job))
        .add(Restrictions.le("EffOnDate", actualityDate))
        .add(Restrictions.ge("EffOffDate", actualityDate)));
    Currency baseCurrency = ConfigurationHelper.getConfiguration().getBaseCurrency();

    for (JobRoute jobRoute : jobRoutes) {
      clearCostDetailLine(jobRoute.getStdCostDetail());
      List<CostDetailLineItem> routeCost = new ArrayList<CostDetailLineItem>();
      //вычислим для ресурсов каждой операции
      //первым вычислим для рабочей силы, результаты используются в дальнейших вычислениях
      BigDecimal operTime = calculateLaborCost(jobRoute, targetQuan, actualityDate, routeCost);
      calculateMaterialCost(jobRoute, targetQuan, operTime, actualityDate, routeCost);
      calculateMachineCost(jobRoute, targetQuan, operTime, actualityDate, routeCost);

      //создадим себестоимость для операции
      createCostDetailLine(BigDecimal.ONE, routeCost, jobRoute.getStdCostDetail(), baseCurrency);

      //изменим стоимость БОМа
      updateCostDetailLineSeq(actualityDate, ownerCost, routeCost, baseCurrency, baseCurrency);
    }
  }

  protected void internalCalculateBOMStandartCost(Date actualityDate) {
    BOMServiceLocal bomService = (BOMServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(BOMServiceLocal.SERVICE_NAME);
    for (Bom bom : loadBOMTargetList()) {
      clearCostDetailLine(bom.getStandartCostDetail());

      List<CostDetailLineItem> bomCost = new ArrayList<CostDetailLineItem>();
      BigDecimal targetQuan = bom.getPlanningLotQty();
      calculateRouteCost(bom, targetQuan, actualityDate, bomCost);

      //создадим себестоимость для БОМа
      createCostDetailLine(BigDecimal.ONE, bomCost, bom.getStandartCostDetail(), ConfigurationHelper.getConfiguration().getBaseCurrency());

      //проставим отметку о пересчете
      bomService.updateRollupDateTime(bom, DateTimeUtils.nowDate());
    }
  }

  protected void internalCalculateJobStandartCost(Date actualityDate) {
    JobServiceLocal jobService = (JobServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(JobServiceLocal.SERVICE_NAME);
    for (Job job : loadJobTargetList()) {
      clearCostDetailLine(job.getStdCostDetail());

      List<CostDetailLineItem> jobCost = new ArrayList<CostDetailLineItem>();
      BigDecimal targetQuan = job.getQtyReleased();
      calculateRouteCost(job, targetQuan, actualityDate, jobCost);

      //создадим себестоимость для БОМа
      createCostDetailLine(BigDecimal.ONE, jobCost, job.getStdCostDetail(), ConfigurationHelper.getConfiguration().getBaseCurrency());

      //проставим отметку о пересчете
      jobService.updateRollupDateTime(job, DateTimeUtils.nowDate());
    }
  }

  /* (non-Javadoc)
   * @see com.mg.merp.manufacture.CostProcessorServiceLocal#calculateBOMStandartCost(java.util.Date)
   */
  public void calculateBOMStandartCost(Date actualityDate) {
    internalCalculateBOMStandartCost(actualityDate);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.manufacture.CostProcessorServiceLocal#calculateJobStandartCost(java.util.Date)
   */
  public void calculateJobStandartCost(Date actualityDate) {
    internalCalculateJobStandartCost(actualityDate);
  }

}