/*
 * MPSProcessorServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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

package com.mg.merp.planning.support;

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.math.Constants;
import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.orm.ResultTransformer;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.manufacture.TransactionServiceLocal;
import com.mg.merp.manufacture.model.Job;
import com.mg.merp.manufacture.model.JobMaterial;
import com.mg.merp.manufacture.support.ManufactureUtils;
import com.mg.merp.mfreference.BOMServiceLocal;
import com.mg.merp.mfreference.BucketRange;
import com.mg.merp.mfreference.TimeRange;
import com.mg.merp.mfreference.model.Bom;
import com.mg.merp.mfreference.model.BomMaterial;
import com.mg.merp.mfreference.model.BomRoute;
import com.mg.merp.mfreference.model.PlanningLevel;
import com.mg.merp.mfreference.model.ScheduleDirection;
import com.mg.merp.mfreference.support.MfUtils;
import com.mg.merp.planning.CatalogWarehouseServiceLocal;
import com.mg.merp.planning.MPSLineServiceLocal;
import com.mg.merp.planning.MPSProcessorServiceLocal;
import com.mg.merp.planning.MPSServiceLocal;
import com.mg.merp.planning.SafetyLevel;
import com.mg.merp.planning.model.ForecastMethod;
import com.mg.merp.planning.model.ForecastVersion;
import com.mg.merp.planning.model.GenericItem;
import com.mg.merp.planning.model.InventoryForecast;
import com.mg.merp.planning.model.InventoryForecastLine;
import com.mg.merp.planning.model.Mps;
import com.mg.merp.planning.model.MpsLine;
import com.mg.merp.planning.model.PlanningForecast;
import com.mg.merp.reference.CurrentStockSituation;
import com.mg.merp.reference.CurrentStockSituationLocator;
import com.mg.merp.reference.MeasureConversionServiceLocal;
import com.mg.merp.reference.StockSituationValues;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Measure;
import com.mg.merp.warehouse.OrderHeadCusServiceLocal;
import com.mg.merp.warehouse.OrderHeadSupServiceLocal;
import com.mg.merp.warehouse.model.OrderStatus;

import org.apache.commons.lang.builder.HashCodeBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 * Реализация бизнес-компонента "Процессор MPS"
 *
 * @author Oleg V. Safonov
 * @version $Id: MPSProcessorServiceBean.java,v 1.6 2009/03/05 10:51:04 safonov Exp $
 */
@Stateful(name = "merp/planning/MPSProcessorService")
public class MPSProcessorServiceBean extends com.mg.framework.generic.AbstractPOJOBusinessObjectStatelessServiceBean implements MPSProcessorServiceLocal {
  private MPSServiceLocal mpsService;
  private MPSLineServiceLocal mpsLineService;
  private BOMServiceLocal bomService;
  private MeasureConversionServiceLocal measureConversionService;
  private TransactionServiceLocal mfTransactionService;
  private Mps mps;
  private OrmTemplate ormTemplate;
  private List<MpsLine> generatedMPSLines;
  private int mpsSequence;
  private PersistentManager pm = ServerUtils.getPersistentManager();

  private void clearMPS(Mps mps) {
    ormTemplate.bulkUpdateByNamedQuery("Planing.MPSProcessor.clearMPS", "mps", mps);
  }

  private MeasureConversionServiceLocal getMeasureConversionService() {
    if (measureConversionService == null)
      measureConversionService = ((MeasureConversionServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(MeasureConversionServiceLocal.LOCAL_SERVICE_NAME));
    return measureConversionService;
  }

  private TransactionServiceLocal getMfTransactionService() {
    if (mfTransactionService == null)
      mfTransactionService = ((TransactionServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(TransactionServiceLocal.SERVICE_NAME));
    return mfTransactionService;
  }

  private PlanningRange calculateMPSPlanningRange(Mps mps) {
    PlanningRange result = new PlanningRange();
    PlanningLevel planningLevel = mps.getPlanningLevel();
    //начальная дата уже есть в MPS, она должна быть выровнена по дате начала бакета
    result.startPlanningDate = mps.getPlanningDate();
    result.startBucket = MfUtils.determineBucketOffset(planningLevel.getId(), result.startPlanningDate);
    //если не нашли бакет, то делать здесь уже нечего
    if (result.startBucket == -1)
      throw new BusinessException(StringUtils.format("Не найден бакет уровня планирования для даты dd/mm/yyyy", result.startPlanningDate));

    result.endBucket = (short) (result.startBucket + planningLevel.getTimeFence() - 1);
    BucketRange bucketRange = MfUtils.determineBucketRange(planningLevel.getId(), result.endBucket);
    result.endPlanningDate = bucketRange.getBucketEnd();
    //если не нашли бакет, то делать здесь уже нечего
    if (result.endPlanningDate.getTime() == 0)
      throw new BusinessException(StringUtils.format("Не найден бакет уровня планирования с номером %d", result.endBucket));
    return result;
  }

  private GenericItem findPlanningItem(Catalog catalog) {
    return ormTemplate.findUniqueByCriteria(OrmTemplate.createCriteria(GenericItem.class)
        .add(Restrictions.eq("Catalog", catalog))
        .add(Restrictions.eq("PlanningItemFlag", true)));
  }

  private MpsLine initMPSLineItem(Mps mps, GenericItem genericItem, short bucketOffset, int outputMPSSequence) {
    //увеличим только при добавлении строки MPS
    mpsSequence++;
    MpsLine result = mpsLineService.initialize();
    result.setMps(mps);
    result.setPlanningItem(genericItem);
    result.setBucketOffset(bucketOffset);
    BucketRange bucketRange = MfUtils.determineBucketRange(mps.getPlanningLevel().getId(), bucketOffset);
    result.setBucketOffsetDate(bucketRange.getBucketStart());
    result.setDemandFenceDate(mps.getDemandFenceDate());
    result.setMpsSequence(mpsSequence);
    result.setOutputMpsSequence(outputMPSSequence == 0 ? null : outputMPSSequence);
    return result;
  }

  private MpsLine prepareMPSLine(Mps mps, GenericItem genericItem, short bucketOffset) {
    return prepareMPSLine(mps, genericItem, bucketOffset, 0);
  }

  private MpsLine prepareMPSLine(Mps mps, GenericItem genericItem, short bucketOffset, int outputMPSSequence) {
//		Collections.sort(generatedMPSLines, new Comparator<MpsLine>() {
//
//			public int compare(MpsLine o1, MpsLine o2) {
//				return 0;
//			}
//
//		});
    MpsLine findedMPSLine = null;
    //ищем в локальном списке
    //TODO быстрый поиск
    for (MpsLine mpsLine : generatedMPSLines) {
      if (/*mpsLine.getMps().getId() == mps.getId()
                    && */mpsLine.getPlanningItem().getId() == genericItem.getId()
          && mpsLine.getBucketOffset() == bucketOffset
          && (outputMPSSequence == 0 || mpsLine.getOutputMpsSequence() != null && mpsLine.getOutputMpsSequence() == outputMPSSequence)) {
        findedMPSLine = mpsLine;
        break;
      }
    }
    //ищем в хранилище
    if (findedMPSLine == null) {
//			Criteria criteria = OrmTemplate.createCriteria(MpsLine.class)
//					.add(Restrictions.eq("Mps", mps))
//					.add(Restrictions.eq("GenericItem", genericItem))
//					.add(Restrictions.eq("BucketOffset", bucketOffset));
//			if (outputMPSSequence != 0)
//				criteria.add(Restrictions.eq("OutputMpsSequence", outputMPSSequence));
//			findedMPSLine = ormTemplate.findUniqueByCriteria(criteria);
//			//если не нашли то создадим
//			if (findedMPSLine == null) {
      findedMPSLine = initMPSLineItem(mps, genericItem, bucketOffset, outputMPSSequence);
//			}
      generatedMPSLines.add(findedMPSLine);
    }

    return findedMPSLine;
  }

  private List<ProductDemandItem> loadDemandFromOrder(Date planningStartDate, Date planningEndDate, int docSection) {
    List<ProductDemandItem> fromSpecs = ormTemplate.findByNamedQueryAndNamedParam("Planing.MPSProcessor.loadDemandFromOrder",
        new String[]{"docSectionId", "status", "startDate", "endDate"},
        new Object[]{docSection, OrderStatus.ORDERED, planningStartDate, planningEndDate},
        new ResultTransformer<ProductDemandItem>() {

          public ProductDemandItem transformTuple(Object[] tuple, String[] aliases) {
            GenericItem gi = pm.find(GenericItem.class, tuple[0]);
            Catalog catalog = pm.find(Catalog.class, tuple[1]);
            Date requiredDate = (Date) tuple[2];
            BigDecimal quan = (BigDecimal) tuple[3];
            if (gi.getMeasure().getId() != catalog.getMeasure1().getId())
              quan = getMeasureConversionService()
                  .conversion(catalog.getMeasure1(), gi.getMeasure(), catalog, requiredDate, quan);

            return new ProductDemandItem(gi, catalog, gi.getMeasure(), requiredDate, quan, (short) 0, null);
          }

        });
//		List<ProductDemandItem> fromSpecs = ormTemplate.findByCriteria(OrmTemplate.createCriteria(OrderSpec.class, "os")
//				.createAlias("os.DocHead", "dh")
//				.setProjection(Projections.projectionList(
//						Projections.property("os.RequiredDate"),
//						Projections.property("os.Catalog"),
//						Projections.property("os.Measure1"),
//						Projections.sum("os.QtyOutstanding"),
//						Projections.groupProperty("os.RequiredDate"),
//						Projections.groupProperty("os.Catalog"),
//						Projections.groupProperty("os.Measure1")))
//				.add(Restrictions.eq("dh.DocSection.Id", docSection))
//				.add(Restrictions.eq("dh.Status", OrderStatus.ORDERED))
//				.add(Restrictions.eq("os.Status", OrderStatus.ORDERED))
//				.add(Restrictions.eq("os.ClosedForPlanning", false))
//				.add(Restrictions.between("os.RequiredDate", planningStartDate, planningEndDate))
//				.add(Restrictions.gt("os.QtyOutstanding", BigDecimal.ZERO))
//				.setResultTransformer(new ResultTransformer<Object>() {
//
//					public Object transformTuple(Object[] tuple, String[] aliases) {
//						return new ProductDemandItem(null, (Catalog) tuple[1], (Measure) tuple[2], (Date) tuple[0], (BigDecimal) tuple[3], (short) 0, null);
//					}
//
//				}));
//		List<ProductDemandItem> result = new ArrayList<ProductDemandItem>();
//		for (ProductDemandItem productDemandItem : fromSpecs) {
//			GenericItem genericItem = findPlanningItem(productDemandItem.catalog);
//			if (genericItem != null) {
//				productDemandItem.genericItem = genericItem;
//				if (genericItem.getMeasure().getId() != productDemandItem.measure.getId()) {
//					productDemandItem.quan = getMeasureConversionService()
//							.conversion(productDemandItem.measure, genericItem.getMeasure(), productDemandItem.catalog, productDemandItem.requiredDate, productDemandItem.quan);
//					//планируем в ЕИ обощенного товара
//					productDemandItem.measure = genericItem.getMeasure();
//				}
//				result.add(productDemandItem);
//			}
//		}
    return fromSpecs;
//		ormTemplate.findByCriteria(OrmTemplate.createCriteria(GenericItem.class, "gi")
//				.createAlias("gi.Catalog", "c")
//				.createAlias("os.DocHead", "dh")
//				.add(Restrictions.eq("dh.DocSection.Id", docSection)));
  }

  private void processOrders(Date planningStartDate, Date planningEndDate, short docSection) {
    for (ProductDemandItem productDemandItem : loadDemandFromOrder(planningStartDate, planningEndDate, docSection)) {
      productDemandItem.bucketOffset = MfUtils.determineBucketOffset(mps.getPlanningLevel().getId(), productDemandItem.requiredDate);
      BucketRange bucketRange = MfUtils.determineBucketRange(mps.getPlanningLevel().getId(), productDemandItem.bucketOffset);
      MpsLine mpsLine = prepareMPSLine(mps, productDemandItem.genericItem, productDemandItem.bucketOffset);
      mpsLine.setBucketOffsetDate(bucketRange.getBucketStart());
      mpsLine.setLevelCode(productDemandItem.genericItem.getLowLevelCode());
      mpsLine.setMeasure(productDemandItem.measure);
      switch (docSection) {
        case OrderHeadCusServiceLocal.DOCSECTION:
          mpsLine.setSalesOrderQty(mpsLine.getSalesOrderQty().add(productDemandItem.quan));
          break;
        case OrderHeadSupServiceLocal.DOCSECTION:
          mpsLine.setPurchaseOrderQty(mpsLine.getPurchaseOrderQty().add(productDemandItem.quan));
          break;
        default:
          throw new IllegalArgumentException("Invalid document section");
      }
      mpsLine.updateDemandQuantities();
    }
  }

//	private List<ProductDemandItem> prepareProductDemands(List<ProductDemandItem> productDemands) {
//		List<ProductDemandItem> result = new ArrayList<ProductDemandItem>();
//		for (ProductDemandItem productDemandItem : productDemands) {
//			GenericItem genericItem = findPlanningItem(productDemandItem.catalog);
//			if (genericItem != null) {
//				productDemandItem.genericItem = genericItem;
//				if (genericItem.getMeasure().getId() != productDemandItem.measure.getId()) {
//					productDemandItem.quan = getMeasureConversionService()
//							.conversion(productDemandItem.measure, genericItem.getMeasure(), productDemandItem.catalog, productDemandItem.requiredDate, productDemandItem.quan);
//					//планируем в ЕИ обощенного товара
//					productDemandItem.measure = genericItem.getMeasure();
//				}
//				result.add(productDemandItem);
//			}
//		}
//		return result;
//	}

  /**
   * до граничной даты прогнозы вообще надо игнорировать, т.е. берется максимум из прогнозов и
   * заказов клиентов.
   */
  private boolean checkDemandFenceDate(ForecastMethod forecastMethod, Date requiredDate, short demandFenceDays, short bucketOffset) {
    Date fenceDate = DateTimeUtils.incDay(mps.getDemandFenceDate(), demandFenceDays);
    switch (forecastMethod) {
      case BY_PERIOD:
        short pdfBucketOffset = MfUtils.determineBucketOffset(mps.getPlanningLevel().getId(), fenceDate);
        if (pdfBucketOffset != -1 && bucketOffset >= pdfBucketOffset)
          return true;
        break;
      case BY_DATE:
        if (requiredDate.compareTo(fenceDate) >= 0)
          return true;
        break;
    }
    return false;
  }

  private List<ProductDemandItem> loadDemandFromSalesForecast(PlanningRange planningRange) {
//		List<ProductDemandItem> fromForecast = ormTemplate.findByCriteria(OrmTemplate.createCriteria(PlanningForecast.class, "pf")
//				.setProjection(Projections.projectionList(
//						Projections.property("pf.RequiredDate"),
//						Projections.property("pf.Catalog"),
//						Projections.property("pf.Measure"),
//						Projections.property("pf.ForecastMethod"),
//						Projections.sum("pf.ForecastQuantity")))
//				.add(Restrictions.eq("pf.PlanningLevel", mps.getPlanningLevel()))
//				.add(Restrictions.eq("pf.ForecastType", ForecastType.SALE))
//				.add(Restrictions.or(
//						Restrictions.and(
//								Restrictions.eq("pf.ForecastMethod", ForecastMethod.BY_DATE),
//								Restrictions.between("pf.RequiredDate", planningRange.startPlanningDate, planningRange.endPlanningDate)),
//						Restrictions.and(
//								Restrictions.eq("pf.ForecastMethod", ForecastMethod.BY_PERIOD),
//								Restrictions.between("pf.BucketOffset", planningRange.startBucket, planningRange.endBucket))))
//				.add(Restrictions.gt("pf.ForecastQuantity", BigDecimal.ZERO))
//				.add(Restrictions.eq("pf.ForecastVersion", mps.getForecastVersion()))
//				.setResultTransformer(new ResultTransformer<ProductDemandItem>() {
//
//					public ProductDemandItem transformTuple(Object[] tuple, String[] aliases) {
//						//return new ProductDemandItem();
//						return null;
//					}
//
//				}));
//		fromForecast = prepareProductDemands(fromForecast);
    //загружаем прогнозы у которых есть ссылка на КТУ (CATALOG_ID is not null)
    List<ProductDemandItem> fromForecast = ormTemplate.findByNamedQueryAndNamedParam("Planing.MPSProcessor.loadDemandFromSalesForecastByCatalog",
        new String[]{"planningLevel", "startDate", "endDate", "startBucket", "endBucket", "forecastVersion"},
        new Object[]{mps.getPlanningLevel(), planningRange.startPlanningDate, planningRange.endPlanningDate, planningRange.startBucket, planningRange.endBucket, mps.getForecastVersion()},
        new ResultTransformer<ProductDemandItem>() {

          public ProductDemandItem transformTuple(Object[] tuple, String[] aliases) {
            GenericItem gi = pm.find(GenericItem.class, tuple[0]);
            PlanningForecast pf = pm.find(PlanningForecast.class, tuple[1]);
            Date requiredDate = pf.getRequiredDate();
            BigDecimal quan = (BigDecimal) tuple[2];
            if (!checkDemandFenceDate(pf.getForecastMethod(), requiredDate, gi.getDemandFenceDays(), pf.getBucketOffset()))
              return null;
            if (gi.getMeasure().getId() != pf.getMeasure().getId())
              quan = getMeasureConversionService()
                  .conversion(pf.getMeasure(), gi.getMeasure(), gi.getCatalog(), requiredDate, quan);
            return new ProductDemandItem(gi, gi.getCatalog(), gi.getMeasure(), requiredDate, quan, pf.getBucketOffset(), pf.getForecastVersion());
          }

        });
    //загружаем прогнозы у которых есть ссылка на обобщенные продукты (PLANNING_ITEM_ID is not null)
    //TODO проверяются только планируемые обобщенные продукты (planning_item_flag = 1),
    //необходимо также проверять и виртуальные продукты, развернуть их по иерархии до реальных продуктов }
    fromForecast.addAll(ormTemplate.findByNamedQueryAndNamedParam("Planing.MPSProcessor.loadDemandFromSalesForecastByGenericItem",
        new String[]{"planningLevel", "startDate", "endDate", "startBucket", "endBucket", "forecastVersion"},
        new Object[]{mps.getPlanningLevel(), planningRange.startPlanningDate, planningRange.endPlanningDate, planningRange.startBucket, planningRange.endBucket, mps.getForecastVersion()},
        new ResultTransformer<ProductDemandItem>() {

          public ProductDemandItem transformTuple(Object[] tuple, String[] aliases) {
            PlanningForecast pf = pm.find(PlanningForecast.class, tuple[0]);
            GenericItem gi = pf.getPlanningItem();
            Date requiredDate = pf.getRequiredDate();
            BigDecimal quan = (BigDecimal) tuple[1];
            if (!checkDemandFenceDate(pf.getForecastMethod(), requiredDate, gi.getDemandFenceDays(), pf.getBucketOffset()))
              return null;
            if (gi.getMeasure().getId() != pf.getMeasure().getId())
              quan = getMeasureConversionService()
                  .conversion(pf.getMeasure(), gi.getMeasure(), gi.getCatalog(), requiredDate, quan);
            return new ProductDemandItem(gi, gi.getCatalog(), gi.getMeasure(), requiredDate, quan, pf.getBucketOffset(), pf.getForecastVersion());
          }

        }));

    //удаляем пустые записи, они не удовлетворяют граничной дате
    List<ProductDemandItem> result = new ArrayList<ProductDemandItem>();
    for (ProductDemandItem productDemandItem : fromForecast)
      if (productDemandItem != null)
        result.add(productDemandItem);
    return result;
  }

  private void processSalesForecasts(PlanningRange planningRange) {
    for (ProductDemandItem productDemandItem : loadDemandFromSalesForecast(planningRange)) {
      if (productDemandItem.bucketOffset == 0)
        productDemandItem.bucketOffset = MfUtils.determineBucketOffset(mps.getPlanningLevel().getId(), productDemandItem.requiredDate);
      BucketRange bucketRange = MfUtils.determineBucketRange(mps.getPlanningLevel().getId(), productDemandItem.bucketOffset);

      MpsLine mpsLine = prepareMPSLine(mps, productDemandItem.genericItem, productDemandItem.bucketOffset);
      mpsLine.setBucketOffsetDate(bucketRange.getBucketStart());
      mpsLine.setLevelCode(productDemandItem.genericItem.getLowLevelCode());
      mpsLine.setMeasure(productDemandItem.measure);
      mpsLine.setSalesForecastQty(mpsLine.getSalesForecastQty().add(productDemandItem.quan));
      mpsLine.setForecastVersion(productDemandItem.forecastVersion);
      mpsLine.updateDemandQuantities();
    }
  }

  private List<ProductDemandItem> loadCurrentQuantityOnHandFromWarehouse(Date planningStartDate) {
    List<GenericItem> giList = ormTemplate.findByCriteria(OrmTemplate.createCriteria(GenericItem.class)
        .add(Restrictions.eq("PlanningItemFlag", true)));
    List<ProductDemandItem> result = new ArrayList<ProductDemandItem>();
    CurrentStockSituation currentStockSituation = CurrentStockSituationLocator.locate();
    for (GenericItem genericItem : giList) {
      BigDecimal quan = BigDecimal.ZERO;
      List<StockSituationValues> stockSituationValuesList = currentStockSituation.getSituation(genericItem.getCatalog());
      if (stockSituationValuesList == null)
        continue;
      for (StockSituationValues stockSituationValues : stockSituationValuesList)
        quan = quan.add(stockSituationValues.getLocated1());


      if (genericItem.getMeasure().getId() != genericItem.getCatalog().getMeasure1().getId())
        quan = getMeasureConversionService()
            .conversion(genericItem.getCatalog().getMeasure1(), genericItem.getMeasure(), genericItem.getCatalog(), planningStartDate, quan);
      result.add(new ProductDemandItem(genericItem, genericItem.getCatalog(), genericItem.getMeasure(), planningStartDate, quan, (short) 0, null));
    }
    return result;
  }

  private void processCurrentQuantityOnHand(Date planningStartDate, short startBucket) {
    for (ProductDemandItem productDemandItem : loadCurrentQuantityOnHandFromWarehouse(planningStartDate)) {
      MpsLine mpsLine = prepareMPSLine(mps, productDemandItem.genericItem, startBucket);
      mpsLine.setLevelCode(productDemandItem.genericItem.getLowLevelCode());
      mpsLine.setMeasure(productDemandItem.genericItem.getMeasure());
      mpsLine.setQtyAvailable(mpsLine.getQtyAvailable().add(productDemandItem.quan));
    }
  }

  private List<ProductDemandItem> loadProductionOutputs(Date planningStartDate, Date planningEndDate) {
    return ormTemplate.findByNamedQueryAndNamedParam("Planing.MPSProcessor.loadProductionOutputs",
        new String[]{"startDate", "endDate"},
        new Object[]{planningStartDate, planningEndDate},
        new ResultTransformer<ProductDemandItem>() {

          public ProductDemandItem transformTuple(Object[] tuple, String[] aliases) {
            Job job = pm.find(Job.class, tuple[0]);
            GenericItem gi = pm.find(GenericItem.class, tuple[1]);
            BigDecimal quan = (BigDecimal) tuple[2];
            Date requiredDate = job.getEndDate();
            if (gi.getMeasure().getId() != gi.getCatalog().getMeasure1().getId())
              quan = getMeasureConversionService()
                  .conversion(gi.getCatalog().getMeasure1(), gi.getMeasure(), gi.getCatalog(), requiredDate, quan);
            return new ProductDemandItem(gi, gi.getCatalog(), gi.getMeasure(), requiredDate, quan, (short) 0, null);
          }

        });
  }

  private void processLiveProductionOutputs(Date planningStartDate, Date planningEndDate) {
    for (ProductDemandItem productDemandItem : loadProductionOutputs(planningStartDate, planningEndDate)) {
      productDemandItem.bucketOffset = MfUtils.determineBucketOffset(mps.getPlanningLevel().getId(), productDemandItem.requiredDate);
      BucketRange bucketRange = MfUtils.determineBucketRange(mps.getPlanningLevel().getId(), productDemandItem.bucketOffset);
      MpsLine mpsLine = prepareMPSLine(mps, productDemandItem.genericItem, productDemandItem.bucketOffset);
      mpsLine.setBucketOffsetDate(bucketRange.getBucketStart());
      mpsLine.setLevelCode(productDemandItem.genericItem.getLowLevelCode());
      mpsLine.setMeasure(productDemandItem.measure);
      mpsLine.setProductionQty(mpsLine.getProductionQty().add(productDemandItem.quan));
    }
  }

  private List<ProductDemandItem> loadProductionInputs(Date planningStartDate, Date planningEndDate) {
    return ormTemplate.findByNamedQueryAndNamedParam("Planing.MPSProcessor.loadProductionInputs",
        new String[]{"startDate", "endDate"},
        new Object[]{planningStartDate, planningEndDate},
        new ResultTransformer<ProductDemandItem>() {

          public ProductDemandItem transformTuple(Object[] tuple, String[] aliases) {
            JobMaterial jm = pm.find(JobMaterial.class, tuple[0]);
            GenericItem gi = pm.find(GenericItem.class, tuple[1]);
            BigDecimal quan = ManufactureUtils.calculateJobMaterialQuan(jm).divide((BigDecimal) tuple[2]).subtract(getMfTransactionService().getQuantityByResource(jm.getId()));
            Date requiredDate = jm.getOper().getJob().getStartDate();
            if (gi.getMeasure().getId() != jm.getMeasure().getId())
              quan = getMeasureConversionService()
                  .conversion(jm.getMeasure(), gi.getMeasure(), gi.getCatalog(), requiredDate, quan);
            return new ProductDemandItem(gi, gi.getCatalog(), gi.getMeasure(), requiredDate, quan, (short) 0, null);
          }

        });
  }

  private void processLiveProductionInputs(Date planningStartDate, Date planningEndDate) {
    for (ProductDemandItem productDemandItem : loadProductionInputs(planningStartDate, planningEndDate)) {
      productDemandItem.bucketOffset = MfUtils.determineBucketOffset(mps.getPlanningLevel().getId(), productDemandItem.requiredDate);
      BucketRange bucketRange = MfUtils.determineBucketRange(mps.getPlanningLevel().getId(), productDemandItem.bucketOffset);
      MpsLine mpsLine = prepareMPSLine(mps, productDemandItem.genericItem, productDemandItem.bucketOffset);
      mpsLine.setBucketOffsetDate(bucketRange.getBucketStart());
      mpsLine.setLevelCode(productDemandItem.genericItem.getLowLevelCode());
      mpsLine.setMeasure(productDemandItem.measure);
      mpsLine.setLiveProductionDemand(mpsLine.getLiveProductionDemand().add(productDemandItem.quan));
    }
  }

  private List<ProductDemandItem> loadQOHForecasts(final Date planningStartDate, Date planningEndDate, InventoryForecast inventoryForecast) {
    return ormTemplate.findByCriteria(OrmTemplate.createCriteria(InventoryForecastLine.class, "ifl")
        .createAlias("ifl.GenericItem", "gi")
        .add(Restrictions.eq("ifl.InventoryForecast", inventoryForecast))
        .add(Restrictions.le("ifl.EffOnDate", planningStartDate))
        .add(Restrictions.ge("ifl.EffOffDate", planningEndDate))
        .add(Restrictions.eq("gi.PlanningItemFlag", true))
        .setResultTransformer(new ResultTransformer<ProductDemandItem>() {

          public ProductDemandItem transformTuple(Object[] tuple, String[] aliases) {
            InventoryForecastLine ifl = (InventoryForecastLine) tuple[0];
            GenericItem gi = ifl.getGenericItem();
            BigDecimal quan = ifl.getQtyOnHand();
            if (ifl.getMeasure().getId() != gi.getMeasure().getId())
              quan = getMeasureConversionService()
                  .conversion(ifl.getMeasure(), gi.getMeasure(), gi.getCatalog(), planningStartDate, quan);
            return new ProductDemandItem(gi, gi.getCatalog(), gi.getMeasure(), null, quan, (short) 0, null);
          }

        }));
  }

  private void processQOHForecasts(Date planningStartDate, Date planningEndDate, InventoryForecast inventoryForecast) {
    for (ProductDemandItem productDemandItem : loadQOHForecasts(planningStartDate, planningEndDate, inventoryForecast)) {
      productDemandItem.bucketOffset = MfUtils.determineBucketOffset(mps.getPlanningLevel().getId(), planningStartDate);
      BucketRange bucketRange = MfUtils.determineBucketRange(mps.getPlanningLevel().getId(), productDemandItem.bucketOffset);
      int generatedCount = generatedMPSLines.size();
      MpsLine mpsLine = prepareMPSLine(mps, productDemandItem.genericItem, productDemandItem.bucketOffset);
      //проверим было ли добавление
      if (generatedCount == generatedMPSLines.size())
        mpsLine.setQtyAvailable(mpsLine.getQtyAvailable().add(productDemandItem.quan));
      else {
        mpsLine.setInventoryForecast(inventoryForecast);
        mpsLine.setBucketOffsetDate(bucketRange.getBucketStart());
        mpsLine.setLevelCode(productDemandItem.genericItem.getLowLevelCode());
        mpsLine.setMeasure(productDemandItem.measure);
        mpsLine.setQtyAvailable(productDemandItem.quan);
      }

    }
  }

  private void processSafetyLevel(Date planningStartDate, short startBucket) {
    CatalogWarehouseServiceLocal catalogWarehouseService = (CatalogWarehouseServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CatalogWarehouseServiceLocal.SERVICE_NAME);
    for (SafetyLevel safetyLevel : catalogWarehouseService.getSafetyLevel()) {
      //только для планируемых материалов
      if (safetyLevel.getGenericItem() == null || !safetyLevel.getGenericItem().getPlanningItemFlag())
        continue;

      int lineCount = generatedMPSLines.size();
      MpsLine mpsLine = prepareMPSLine(mps, safetyLevel.getGenericItem(), startBucket);
      //таким способом проверяем добавляли запись в список сгенерированных
      if (lineCount != generatedMPSLines.size()) {
        mpsLine.setBucketOffsetDate(planningStartDate);
        mpsLine.setLevelCode(safetyLevel.getGenericItem().getLowLevelCode());
        mpsLine.setMeasure(safetyLevel.getGenericItem().getMeasure());
      }
      BigDecimal quan = safetyLevel.getQuantity();
      if (safetyLevel.getCatalog().getMeasure1().getId() != safetyLevel.getGenericItem().getMeasure().getId())
        quan = getMeasureConversionService().conversion(safetyLevel.getCatalog().getMeasure1(), safetyLevel.getGenericItem().getMeasure(), safetyLevel.getCatalog(), planningStartDate, quan);
      mpsLine.setSafetyLevelQty(quan);
    }
  }

  private void processDependantDemand(short lowLevelCode) {
    Map<ProductionDemandKey, ProductionDemandEntry> productionDemand = new HashMap<ProductionDemandKey, ProductionDemandEntry>();
    //группируем по BucketOffset, PlanningItem, Measure. Вычисляем потребность в производстве
    for (MpsLine mpsLine : generatedMPSLines) {
      if (MathUtils.compareToZero(mpsLine.getProductionDemandQty()) > 0 && mpsLine.getLevelCode() == lowLevelCode) {
        ProductionDemandKey key = new ProductionDemandKey(mpsLine.getBucketOffset(), mpsLine.getPlanningItem().getId(), mpsLine.getMeasure().getId());
        ProductionDemandEntry entry = productionDemand.get(key);
        if (entry == null)
          productionDemand.put(key, new ProductionDemandEntry(mpsLine.getProductionDemandQty(), mpsLine));
        else
          entry.productionDemandQty.add(mpsLine.getProductionDemandQty());
      }
    }

    for (ProductionDemandEntry entry : productionDemand.values()) {
      MpsLine findedMPSLine = null;
      for (MpsLine mpsLine : generatedMPSLines) {
        if (MathUtils.compareToZero(mpsLine.getProductionDemandQty()) <= 0
            && mpsLine.getPlanningItem().getId() == entry.mpsLine.getPlanningItem().getId()
            && mpsLine.getBucketOffset() == entry.mpsLine.getBucketOffset()) {
          findedMPSLine = mpsLine;
          break;
        }
      }

      if (findedMPSLine == null) {
        findedMPSLine = initMPSLineItem(mps, entry.mpsLine.getPlanningItem(), entry.mpsLine.getBucketOffset(), 0);
        findedMPSLine.setMeasure(entry.mpsLine.getPlanningItem().getMeasure());
        findedMPSLine.setLevelCode(lowLevelCode);
        generatedMPSLines.add(findedMPSLine);
      }

      findedMPSLine.setDependantDemand(findedMPSLine.getDependantDemand().add(entry.productionDemandQty));
    }
  }

  private void calculateProductionPlan(short lowLevelCode) {
    //сортируем по PlanningItem, BucketOffset desc
    Collections.sort(generatedMPSLines, new Comparator<MpsLine>() {

      public int compare(MpsLine o1, MpsLine o2) {
        if (o1.getPlanningItem().getId() < o2.getPlanningItem().getId())
          return 1;
        else if (o1.getPlanningItem().getId() > o2.getPlanningItem().getId())
          return -1;
        else {
          if (o1.getBucketOffset() < o2.getBucketOffset())
            return 1;
          else if (o1.getBucketOffset() > o2.getBucketOffset())
            return -1;
          else
            return 0;
        }
      }

    });

    BigDecimal availableOnBeginBucket = BigDecimal.ZERO;
    GenericItem currentPlanningItem = null;
    for (MpsLine mpsLine : generatedMPSLines) {
      if (MathUtils.compareToZero(mpsLine.getProductionDemandQty()) <= 0
          && mpsLine.getLevelCode() == lowLevelCode) {
        if (currentPlanningItem == null || currentPlanningItem.getId() != mpsLine.getPlanningItem().getId()) {
          currentPlanningItem = mpsLine.getPlanningItem();
          availableOnBeginBucket = BigDecimal.ZERO;
        }

        BigDecimal plannedQty = mpsLine.calculatePlannedQuantity(availableOnBeginBucket);

        Bom bom = bomService.findCurrentBOM(currentPlanningItem.getCatalog().getId());
        if (bom != null) {
          BigDecimal MaxQty = bom.getMaxLotQty(), MinQty = bom.getMinLotQty(), Increment = bom.getLotIncrementQty(), Remainder, RemInc;
          if (!(MathUtils.compareToZeroOrNull(MaxQty) == 0
              || MathUtils.compareToZeroOrNull(MinQty) == 0
              || MathUtils.compareToZeroOrNull(Increment) == 0)) {
            //если не совпадают ЕИ планирования и БОМа
            if (bom.getCatalog().getMeasure1().getId() != mpsLine.getMeasure().getId()) {
              MaxQty = getMeasureConversionService().conversion(bom.getCatalog().getMeasure1(), mpsLine.getMeasure(), bom.getCatalog(), mpsLine.getBucketOffsetDate(), MaxQty);
              MinQty = getMeasureConversionService().conversion(bom.getCatalog().getMeasure1(), mpsLine.getMeasure(), bom.getCatalog(), mpsLine.getBucketOffsetDate(), MinQty);
              Increment = getMeasureConversionService().conversion(bom.getCatalog().getMeasure1(), mpsLine.getMeasure(), bom.getCatalog(), mpsLine.getBucketOffsetDate(), Increment);
            }

            long NumOfMax = plannedQty.divide(MaxQty).longValue();
            Remainder = plannedQty.subtract(MaxQty.multiply(new BigDecimal(NumOfMax)));
            plannedQty = MaxQty.multiply(new BigDecimal(NumOfMax));
            if (MathUtils.compareToZero(Remainder) != 0) {
              if (Remainder.compareTo(MinQty) == -1)
                plannedQty = plannedQty.add(MinQty);
              else {
                long NumOfInc = Remainder.subtract(MinQty).divide(Increment).longValue();
                RemInc = Remainder.subtract(MinQty).subtract(Increment.multiply(new BigDecimal(NumOfInc)));
                if (MathUtils.compareToZero(RemInc) != 0)
                  plannedQty = plannedQty.add(MinQty).add(Increment.multiply(new BigDecimal(NumOfInc))).add(Increment);
                else
                  plannedQty = plannedQty.add(Remainder);
              }
            }
          }
        }
        availableOnBeginBucket = mpsLine.calculateAvailableQuantityOnEndBucket(plannedQty, availableOnBeginBucket);

        if (MathUtils.compareToZero(plannedQty) < 0)
          plannedQty = BigDecimal.ZERO;
        mpsLine.setPlannedQty(plannedQty);
      }
    }
  }

  private long mpsMaterialBreakdown(Bom bom, MpsLine mpsLine, long numberOfJobs, BigDecimal lotQty, long requiredDate) {
    long reqDate = requiredDate;
    List<BomRoute> routeList = ormTemplate.findByCriteria(OrmTemplate.createCriteria(BomRoute.class)
        .add(Restrictions.eq("Bom", bom))
        .add(Restrictions.le("EffOnDate", MfUtils.tickToDate(requiredDate)))
        .add(Restrictions.ge("EffOffDate", MfUtils.tickToDate(requiredDate)))
        .addOrder(Order.desc("OperNum"))); //идем от конечных операций к начальным назад
    for (BomRoute bomRoute : routeList) {
      //Вычисляем время выполнения текущей операции в тиках
      long timeOper = numberOfJobs * bomRoute.getSetupTicks() + lotQty.longValue() * numberOfJobs * bomRoute.getRunTicks();

      //Определяем время окончания текущей операции
      TimeRange timeRange = MfUtils.getTimes(mps.getWeekCal().getId(), reqDate, timeOper, ScheduleDirection.BACKWARD);
      long operStartDate = timeRange.getStartDateTime();

      List<BomMaterial> materialList = ormTemplate.findByCriteria(OrmTemplate.createCriteria(BomMaterial.class)
          .add(Restrictions.eq("BomRoute", bomRoute))
          .add(Restrictions.le("EffOnDate", MfUtils.tickToDate(operStartDate)))
          .add(Restrictions.ge("EffOffDate", MfUtils.tickToDate(operStartDate))));
      for (BomMaterial bomMaterial : materialList) {
        GenericItem planningItem = findPlanningItem(bomMaterial.getCatalog());
        if (planningItem == null)
          continue;

        BigDecimal MaterialQty = lotQty.multiply(new BigDecimal(numberOfJobs)).multiply(MfUtils.calculateBOMMaterialQuan(bomMaterial, MfUtils.tickToDate(requiredDate), bom.getPlanningLotQty()));

        short bucketOffset = MfUtils.determineBucketOffset(mps.getPlanningLevel().getId(), MfUtils.tickToDate(operStartDate));
        if (bucketOffset == -1)
          throw new BusinessException("Не найден бакет уровня планирования для даты dd/mm/yyyy");
        BucketRange bucketRange = MfUtils.determineBucketRange(mps.getPlanningLevel().getId(), bucketOffset);
        MpsLine mpsl = prepareMPSLine(mps, planningItem, bucketOffset, mpsLine.getMpsSequence());
        mpsl.setBucketOffset(bucketOffset);
        mpsl.setBucketOffsetDate(bucketRange.getBucketStart());
        mpsl.setLevelCode(planningItem.getLowLevelCode());
        if (bomMaterial.getMeasure().getId() != planningItem.getMeasure().getId())
          MaterialQty = getMeasureConversionService().conversion(bomMaterial.getMeasure(), planningItem.getMeasure(), planningItem.getCatalog(), bucketRange.getBucketStart(), MaterialQty);
        mpsl.setProductionDemandQty(mpsl.getProductionDemandQty().add(MaterialQty));
        mpsl.setMeasure(planningItem.getMeasure());
      }
      timeRange = MfUtils.getTimes(mps.getWeekCal().getId(), operStartDate, bomRoute.getMoveTicks() * numberOfJobs, ScheduleDirection.BACKWARD);
      reqDate = timeRange.getStartDateTime();//вычитаем время на перемещение между операциями
    }
    return reqDate;
  }

  private void calculateDependantDemand(short lowLevelCode) {
    for (MpsLine mpsLine : new ArrayList<MpsLine>(generatedMPSLines)) { //работаем с копией, т.к. дальше происходит изменения списка generatedMPSLines
      if (mpsLine.getLevelCode() == lowLevelCode
          && MathUtils.compareToZero(mpsLine.getPlannedQty()) > 0) {
        BucketRange bucketRange = MfUtils.determineBucketRange(mps.getPlanningLevel().getId(), mpsLine.getBucketOffset());
        BigDecimal PlannedQty = mpsLine.getPlannedQty(), MaxLotQty, Remainder, OddJobSize;
        PlannedQty = getMeasureConversionService().conversion(mpsLine.getMeasure(), mpsLine.getPlanningItem().getCatalog().getMeasure1(), mpsLine.getPlanningItem().getCatalog(), bucketRange.getBucketStart(), PlannedQty);

        Bom bom = bomService.findCurrentBOM(mpsLine.getPlanningItem().getCatalog().getId());
        if (bom == null)
          continue;

        MaxLotQty = bom.getMaxLotQty();
        if (MathUtils.compareToZero(MaxLotQty) == 0)
          MaxLotQty = PlannedQty; //Infinite maximum size
        long NumberOfMaxJobs = PlannedQty.divide(MaxLotQty).longValue();
        Remainder = PlannedQty.subtract(MaxLotQty.multiply(new BigDecimal(NumberOfMaxJobs)));
        OddJobSize = Remainder;

        long CurrentRequiredDate = MfUtils.dateToTick(DateTimeUtils.incDay(bucketRange.getBucketEnd(), 1)) - 1000; //на конец текущего дня
        mpsMaterialBreakdown(bom, mpsLine, 1, MaxLotQty.multiply(new BigDecimal(NumberOfMaxJobs)).add(OddJobSize), CurrentRequiredDate);
      }
    }
  }

  private void persistGeneratedMPSLines() {
    PersistentManager pm = ServerUtils.getPersistentManager();
    for (MpsLine mpsLine : generatedMPSLines) {
      if (!mpsLine.isEmpty()) {
        if (pm.contains(mpsLine))
          pm.merge(mpsLine);
        else
          pm.persist(mpsLine);
      }
    }
  }

  private RollUpMPSResult rollUpMPS(Mps mpsDst, MpsLine srcMpsLine, BigDecimal quan, Date countDay, int mpsSequence) {
    BigDecimal roundedQty = quan;
    if (srcMpsLine.getPlanningItem().getCatalog() != null) {
      Bom bom = bomService.findCurrentBOM(srcMpsLine.getPlanningItem().getCatalog().getId());
      if (bom != null) {
        BigDecimal maxQty = bom.getMaxLotQty();
        BigDecimal minQty = bom.getMinLotQty();
        BigDecimal increment = bom.getLotIncrementQty();

        if (!(MathUtils.compareToZeroOrNull(maxQty) == 0
            || MathUtils.compareToZeroOrNull(minQty) == 0
            || MathUtils.compareToZeroOrNull(increment) == 0)) {
          if (bom.getCatalog().getMeasure1().getId() != srcMpsLine.getPlanningItem().getMeasure().getId()) {
            maxQty = getMeasureConversionService().conversion(bom.getCatalog().getMeasure1(), srcMpsLine.getPlanningItem().getMeasure(), bom.getCatalog(), srcMpsLine.getBucketOffsetDate(), maxQty);
            minQty = getMeasureConversionService().conversion(bom.getCatalog().getMeasure1(), srcMpsLine.getPlanningItem().getMeasure(), bom.getCatalog(), srcMpsLine.getBucketOffsetDate(), minQty);
            increment = getMeasureConversionService().conversion(bom.getCatalog().getMeasure1(), srcMpsLine.getPlanningItem().getMeasure(), bom.getCatalog(), srcMpsLine.getBucketOffsetDate(), increment);
          }

          long numOfMax = roundedQty.divide(maxQty).longValue();
          roundedQty = maxQty.multiply(new BigDecimal(numOfMax));
          BigDecimal remainder = roundedQty.add(roundedQty);

          if (MathUtils.compareToZero(remainder) != 0) {
            if (remainder.compareTo(minQty) < 0) {
              roundedQty = roundedQty.add(minQty);
            } else {
              long numOfInc = remainder.subtract(minQty).divide(increment).longValue();
              BigDecimal remInc = remainder.subtract(minQty).subtract(increment.multiply(new BigDecimal(numOfInc)));
              if (MathUtils.compareToZero(remInc) != 0)
                roundedQty = roundedQty.add(minQty).add(increment.multiply(new BigDecimal(numOfInc))).add(increment);
              else
                roundedQty = roundedQty.add(remainder);

            }
          }
        }
      }
    }

    short bucket = MfUtils.determineBucketOffset(mpsDst.getPlanningLevel().getId(), countDay);
    MpsLine mpsLine = null;
    for (MpsLine line : generatedMPSLines) {
      if (MathUtils.compareToZero(line.getProductionDemandQty()) <= 0
          && line.getPlanningItem().getId() == srcMpsLine.getPlanningItem().getId()
          && line.getBucketOffset() == bucket) {
        mpsLine = line;
        break;
      }
    }
    if (mpsLine == null) {
      mpsLine = mpsLineService.initialize();
      mpsLine.setPlanningItem(srcMpsLine.getPlanningItem());
      mpsLine.setLevelCode(srcMpsLine.getPlanningItem().getLowLevelCode());
      mpsLine.setMps(mpsDst);
      mpsLine.setBucketOffset(bucket);
      BucketRange bucketRange = MfUtils.determineBucketRange(mpsDst.getPlanningLevel().getId(), bucket);
      mpsLine.setBucketOffsetDate(bucketRange.getBucketStart());
      mpsLine.setDemandFenceDate(srcMpsLine.getDemandFenceDate());
      mpsLine.setMpsSequence(mpsSequence);
      mpsLine.setOutputMpsSequence(null);
      mpsLine.setMeasure(srcMpsLine.getMeasure());
      mpsLine.setPlannedQty(roundedQty);

      generatedMPSLines.add(mpsLine);
      //увеличим только при добавлении строки MPS
      return new RollUpMPSResult(roundedQty, mpsSequence + 1);
    } else {
      mpsLine.setPlannedQty(mpsLine.getPlannedQty().add(roundedQty));
      return new RollUpMPSResult(roundedQty, mpsSequence);
    }
  }

  protected void internalMpsLevelTransfer(int mpsSrcId, int mpsDstId) {
    generatedMPSLines = new ArrayList<MpsLine>();
    Mps mpsSrc = mpsService.load(mpsSrcId);
    Mps mpsDst = mpsService.load(mpsDstId);

    PlanningRange planningRange = calculateMPSPlanningRange(mpsDst);
    clearMPS(mpsDst);

    List<MpsLine> lines = ormTemplate.findByCriteria(OrmTemplate.createCriteria(MpsLine.class)
        .add(Restrictions.eq("Mps", mpsSrc))
        .add(Restrictions.or(
            Restrictions.gt("PlannedQty", BigDecimal.ZERO),
            Restrictions.gt("AdjustmentQty", BigDecimal.ZERO)))
        .addOrder(Order.asc("BucketOffset")));
    BucketRange bucketRange = null;
    int mpsDstSequence = 1;
    boolean firstLine = true;
    long numberOfDays = 1;
    int currentBucketOffset = 1;
    for (MpsLine mpsLine : lines) {
      //для первой записи в выборке мы тоже должны попасть внутрь этого if-а
      if (firstLine || currentBucketOffset != mpsLine.getBucketOffset()) {
        bucketRange = MfUtils.determineBucketRange(mpsSrc.getPlanningLevel().getId(), mpsLine.getBucketOffset());
        //NumberOfDays = EndDay - StartDay + 1
        numberOfDays = DateTimeUtils.getDaysBetween(bucketRange.getBucketStart(), bucketRange.getBucketEnd()) + 1;
        currentBucketOffset = mpsLine.getBucketOffset();
        firstLine = false;
      }

      BigDecimal remainingQty = mpsLine.getPlannedQty().add(mpsLine.getAdjustmentQty());
      // if PlanStartDay <= EndDay and PlanEndDay >= StartDay then
      // Обрабатываем, только если периоды имеют непустое пересечение
      int c1 = planningRange.startPlanningDate.compareTo(bucketRange.getBucketEnd());
      int c2 = planningRange.endPlanningDate.compareTo(bucketRange.getBucketStart());
      if (c1 <= 0 && c2 >= 0) {
        long remainingNumberOfDays = numberOfDays;
        //PlanStartDay > StartDay
        if (planningRange.startPlanningDate.compareTo(bucketRange.getBucketStart()) > 0)
          remainingNumberOfDays = numberOfDays - DateTimeUtils.getDaysBetween(planningRange.startPlanningDate, bucketRange.getBucketStart());

        BigDecimal srcQty = MathUtils.divide(remainingQty, new BigDecimal(remainingNumberOfDays), Constants.QUANTITY_ROUND_CONTEXT);
        Date countDay = bucketRange.getBucketStart();
        //while CountDay <= EndDay
        while (MathUtils.compareToZero(remainingQty) > 0 && countDay.compareTo(bucketRange.getBucketEnd()) <= 0) {
          //if CountDay >= PlanStartDay and CountDay <= PlanEndDay then
          if (countDay.compareTo(planningRange.startPlanningDate) >= 0 && countDay.compareTo(planningRange.endPlanningDate) <= 0) {
            RollUpMPSResult rollUpMPSResult = rollUpMPS(mpsDst, mpsLine, srcQty, countDay, mpsDstSequence);
            mpsDstSequence = rollUpMPSResult.mpsSequence; //возможно изменилась
            remainingNumberOfDays -= 1;
            remainingQty = remainingQty.subtract(rollUpMPSResult.quan);
            if (remainingNumberOfDays > 0)
              srcQty = MathUtils.divide(remainingQty, new BigDecimal(remainingNumberOfDays), Constants.QUANTITY_ROUND_CONTEXT);
          }
          countDay = DateTimeUtils.incDay(countDay, 1);
        }
      }
    }
    persistGeneratedMPSLines();
  }

  protected void internalGenerateMps() {
    generatedMPSLines = new ArrayList<MpsLine>();
    mpsSequence = 0;

    //вычислим диапазон планирования
    PlanningRange planningRange = calculateMPSPlanningRange(mps);

    //обработаем заказы клиентов
    if (mps.getSalesLive())
      processOrders(planningRange.startPlanningDate, planningRange.endPlanningDate, OrderHeadCusServiceLocal.DOCSECTION);

    //обработаем прогнозы продаж
    if (mps.getSalesForecasts())
      processSalesForecasts(planningRange);

    //обработаем заказы поставщиков
    if (mps.getPurchasesLive())
      processOrders(planningRange.startPlanningDate, planningRange.endPlanningDate, OrderHeadSupServiceLocal.DOCSECTION);

    //обработаем текущие складские запасы
    if (mps.getQtyOnHand())
      processCurrentQuantityOnHand(planningRange.startPlanningDate, planningRange.startBucket);

    //обработаем запущенные в производство ЗНП
    if (mps.getProduction()) {
      processLiveProductionOutputs(planningRange.startPlanningDate, planningRange.endPlanningDate);
      processLiveProductionInputs(planningRange.startPlanningDate, planningRange.endPlanningDate);
    }

    //обработаем прогнозируемых остатков товаров на складах предприятия
    if (mps.getInventoryForecast() != null)
      processQOHForecasts(planningRange.startPlanningDate, planningRange.endPlanningDate, mps.getInventoryForecast());

    //обработаем страховой запас
    processSafetyLevel(planningRange.startPlanningDate, planningRange.startBucket);

    //главный цикл по LLC
    short lowLevelCode = 1;
    while (lowLevelCode <= mps.getLevelProcessedTo()) {
      if (lowLevelCode > 1)
        processDependantDemand(lowLevelCode);
      calculateProductionPlan(lowLevelCode);
      calculateDependantDemand(lowLevelCode);

      lowLevelCode++;
    }

    //сохранение сформированных строк
    persistGeneratedMPSLines();
    //отметка о расчете в MPS
    mps.setLastRunDateTime(DateTimeUtils.nowDate());
    mps.setMpsVersion(mps.getMpsVersion() + 1);
    mpsService.store(mps);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.planning.MPSProcessorServiceLocal#generateMps(int)
   */
  @Remove
  public void generateMps(int mpsId) {
    ServerUtils.setTransactionTimeout(86400);//неизвестно сколько будет идти расчет MPS, ставит таймаут на сутки
    ormTemplate = OrmTemplate.getInstance();
    mpsService = (MPSServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(MPSServiceLocal.SERVICE_NAME);
    mpsLineService = (MPSLineServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(MPSLineServiceLocal.SERVICE_NAME);
    bomService = (BOMServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(BOMServiceLocal.SERVICE_NAME);
    mps = mpsService.load(mpsId);
    clearMPS(mps);
    internalGenerateMps();
  }

  /* (non-Javadoc)
   * @see com.mg.merp.planning.MPSProcessorServiceLocal#mpsLevelTransfer(int, int)
   */
  @Remove
  public void mpsLevelTransfer(int mpsSrcId, int mpsDstId) {
    if (mpsSrcId == mpsDstId)
      return;

    ormTemplate = OrmTemplate.getInstance();
    mpsService = (MPSServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(MPSServiceLocal.SERVICE_NAME);
    mpsLineService = (MPSLineServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(MPSLineServiceLocal.SERVICE_NAME);
    bomService = (BOMServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(BOMServiceLocal.SERVICE_NAME);
    internalMpsLevelTransfer(mpsSrcId, mpsDstId);
  }

  class PlanningRange {
    Date startPlanningDate;
    Date endPlanningDate;
    short startBucket;
    short endBucket;
  }

  class ProductDemandItem {
    GenericItem genericItem;
    Measure measure;
    Date requiredDate;
    BigDecimal quan;
    short bucketOffset;
    ForecastVersion forecastVersion;
    Catalog catalog;

    private ProductDemandItem(GenericItem genericItem, Catalog catalog, Measure measure, Date requiredDate, BigDecimal quan, short bucketOffset, ForecastVersion forecastVersion) {
      super();
      this.genericItem = genericItem;
      this.catalog = catalog;
      this.measure = measure;
      this.requiredDate = requiredDate;
      this.quan = quan;
      this.bucketOffset = bucketOffset;
      this.forecastVersion = forecastVersion;
    }

  }

  class ProductionDemandKey {
    short bucketOffset;
    int planningItemId;
    int measureId;

    private ProductionDemandKey(short bucketOffset, int planningItemId, int measureId) {
      super();
      this.bucketOffset = bucketOffset;
      this.planningItemId = planningItemId;
      this.measureId = measureId;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
      return HashCodeBuilder.reflectionHashCode(this);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
      ProductionDemandKey key = (ProductionDemandKey) obj;
      return this.bucketOffset == key.bucketOffset && this.planningItemId == key.planningItemId && this.measureId == key.measureId;
    }
  }

  class ProductionDemandEntry {
    BigDecimal productionDemandQty;
    MpsLine mpsLine;

    private ProductionDemandEntry(BigDecimal productionDemandQty, MpsLine mpsLine) {
      super();
      this.productionDemandQty = productionDemandQty;
      this.mpsLine = mpsLine;
    }
  }

  class RollUpMPSResult {
    BigDecimal quan;
    int mpsSequence;

    private RollUpMPSResult(BigDecimal quan, int mpsSequence) {
      super();
      this.quan = quan;
      this.mpsSequence = mpsSequence;
    }

  }
}