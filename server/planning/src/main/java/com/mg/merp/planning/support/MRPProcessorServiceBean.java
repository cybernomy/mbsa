/*
 * MRPProcessorServiceBean.java
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

package com.mg.merp.planning.support;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateful;

import com.mg.framework.api.math.RoundContext;
import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.api.orm.Projections;
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
import com.mg.merp.manufacture.model.JobStatus;
import com.mg.merp.manufacture.support.ManufactureUtils;
import com.mg.merp.mfreference.BOMServiceLocal;
import com.mg.merp.mfreference.BucketRange;
import com.mg.merp.mfreference.TimeRange;
import com.mg.merp.mfreference.model.Bom;
import com.mg.merp.mfreference.model.BomMaterial;
import com.mg.merp.mfreference.model.BomRoute;
import com.mg.merp.mfreference.model.PlanningLevel;
import com.mg.merp.mfreference.model.Route;
import com.mg.merp.mfreference.model.RouteSrcType;
import com.mg.merp.mfreference.model.ScheduleDirection;
import com.mg.merp.mfreference.model.WeekCalendar;
import com.mg.merp.mfreference.support.MfUtils;
import com.mg.merp.planning.MRPInputsServiceLocal;
import com.mg.merp.planning.MRPOutputsServiceLocal;
import com.mg.merp.planning.MRPProcessorServiceLocal;
import com.mg.merp.planning.MRPRecommendationServiceLocal;
import com.mg.merp.planning.MRPReportServiceLocal;
import com.mg.merp.planning.MRPShortageServiceLocal;
import com.mg.merp.planning.MRPVersionControlServiceLocal;
import com.mg.merp.planning.ProductCatalogWarehouseNotFoundException;
import com.mg.merp.planning.ProductRouteNotFoundException;
import com.mg.merp.planning.WarehouseNotSetupException;
import com.mg.merp.planning.model.CatalogWarehouse;
import com.mg.merp.planning.model.FirmPlannedOrder;
import com.mg.merp.planning.model.ForecastType;
import com.mg.merp.planning.model.InputOutputFlag;
import com.mg.merp.planning.model.MRPOrderType;
import com.mg.merp.planning.model.MpsLine;
import com.mg.merp.planning.model.MrpInputs;
import com.mg.merp.planning.model.MrpOutputs;
import com.mg.merp.planning.model.MrpRecommendation;
import com.mg.merp.planning.model.MrpReport;
import com.mg.merp.planning.model.MrpShortage;
import com.mg.merp.planning.model.MrpVersionControl;
import com.mg.merp.planning.model.MrpVersionForecast;
import com.mg.merp.planning.model.MrpVersionMps;
import com.mg.merp.planning.model.RecommendType;
import com.mg.merp.planning.model.PlanningForecast;
import com.mg.merp.planning.model.RescheduleFlag;
import com.mg.merp.planning.model.MRPSource;
import com.mg.merp.reference.CurrentStockSituation;
import com.mg.merp.reference.CurrentStockSituationLocator;
import com.mg.merp.reference.MeasureConversionServiceLocal;
import com.mg.merp.reference.StockSituationValues;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Measure;
import com.mg.merp.warehouse.model.OrderSpec;
import com.mg.merp.warehouse.model.Warehouse;

/**
 * Бизнес-компонент "Процессор MRP"
 * 
 * @author Oleg V. Safonov
 * @version $Id: MRPProcessorServiceBean.java,v 1.5 2007/07/30 10:36:48 safonov Exp $
 */
@Stateful(name="merp/planning/MRPProcessorService")
public class MRPProcessorServiceBean extends com.mg.framework.generic.AbstractPOJOBusinessObjectStatelessServiceBean implements MRPProcessorServiceLocal {
	private OrmTemplate ormTemplate;
	private int transferOrderLastNumber;
	private int purchaseOrderLastNumber;
	private int reportSequence;
	private MrpVersionControl mrpVersion;
	private MRPVersionControlServiceLocal mrpVersionService;
	private BOMServiceLocal bomService;
	private MRPInputsServiceLocal mrpInputsService;
	private MRPOutputsServiceLocal mrpOutputsService;
	private MeasureConversionServiceLocal measureConvService;
	private MRPReportServiceLocal mrpReportService;
	private MRPRecommendationServiceLocal mrpRecommendationService;
	private MRPShortageServiceLocal mrpShortageService;
	private List<MrpInputs> mrpInputsList;
	private List<MrpOutputs> mrpOutputsList;
	private List<MrpReport> mrpReportList;
	private List<MrpShortage> mrpShortageList;
	private List<MrpRecommendation> mrpRecommendationList;
	private MRPInventoryList mrpInventoryList;
	private MRPInputList virtualInputs;
	private MRPOutputList virtualOutputs;
	private PersistentManager persistentManager;
	private Date mrpStartDate;
	private Date mrpEndDate;

	class MRPInventoryItem {
		private Catalog catalog;
		private Warehouse warehouse;
//		private short dampingDay;
//		private short orderIntervalDays;
//		private BigDecimal safetyLevel;
		private Route route;  
		private CatalogWarehouse catalogWarehouse;
		private int shelfLife;
	}
	
	class MRPInventoryList {
		private List<MRPInventoryItem> list = new ArrayList<MRPInventoryItem>();
		
		private void catalogWarehouseError(MRPInventoryItem item) {
			throw new ProductCatalogWarehouseNotFoundException(item.catalog, item.warehouse);
		}
		
		private void routeError(MRPInventoryItem item) {
			throw new ProductRouteNotFoundException(item.catalog, item.warehouse);
		}
		
		private void loadFromCatalog(MRPInventoryItem item) {
			item.shelfLife = calculateShelfLife(item.catalog);
		}
		
		private void loadFromCatalogWarehouse(MRPInventoryItem item) {
			item.catalogWarehouse = ormTemplate.findUniqueByCriteria(OrmTemplate.createCriteria(CatalogWarehouse.class)
					.add(Restrictions.eq("Catalog", item.catalog))
					.add(Restrictions.eq("Warehouse", item.warehouse)));
//			item.dampingDay = cw.getMrpDampingDays() != null ? cw.getMrpDampingDays() : 0;
//			item.orderIntervalDays = cw.getOrderIntervalDays() != null ? cw.getOrderIntervalDays() : 0;
//			item.safetyLevel = cw.getSafetyLevel();
			if (item.catalogWarehouse == null)
				catalogWarehouseError(item);
		}
		
		private void loadFromRoute(MRPInventoryItem item) {
			List<Route> routes = ormTemplate.findByCriteria(OrmTemplate.createCriteria(Route.class)
					.add(Restrictions.eq("Catalog", item.catalog))
					.add(Restrictions.eq("DestWarehouse", item.warehouse))
					.addOrder(Order.asc("SrcRank")));
			if (routes.isEmpty())
				routeError(item);
			item.route = routes.get(0);
		}
		
		private void add(Catalog catalog, Warehouse warehouse) {
			MRPInventoryItem item = find(catalog, warehouse);
			if (item != null)
				return;
			
			item = new MRPInventoryItem();
			item.catalog = catalog;
			item.warehouse = warehouse;
			
			loadFromCatalogWarehouse(item);
			loadFromRoute(item);
			loadFromCatalog(item);
			
			list.add(item);
		}
		
		private MRPInventoryItem find(Catalog catalog, Warehouse warehouse) {
			for (MRPInventoryItem item : list)
				if (item.catalog.getId() == catalog.getId() && item.warehouse.getId() == warehouse.getId())
					return item;
			return null;
		}
		
		private List<MRPInventoryItem> list() {
			return list;
		}
		
	}
	
	class MRPInputItem {
		private BigDecimal inputQty;
		private BigDecimal originalInputQty;
		private Date originalInputDate;
		private Date inputDate;
		private Date reportDate;
		private Date orderDate;
		private boolean fixedInput;
		private Date batchDate;
		private boolean suggestedPO;
		@SuppressWarnings("unused")
		private boolean dampedReschdule;
		private MRPSource mrpSource;
		private MRPOrderType mrpOrderType;
		private BigDecimal expiredQty;
		private boolean ignoreForReport;
		private String reference;
		private BigDecimal purchaseQty;

		private MRPInputItem(BigDecimal inputQty, BigDecimal originalInputQty, Date originalInputDate, Date inputDate, Date reportDate, Date orderDate, boolean fixedInput, Date batchDate, boolean suggestedPO, boolean dampedReschdule, MRPSource mrpSource, MRPOrderType mrpOrderType, BigDecimal expiredQty, boolean ignoreForReport, String reference, BigDecimal purchaseQty) {
			super();
			this.inputQty = inputQty;
			this.originalInputQty = originalInputQty;
			this.originalInputDate = originalInputDate;
			this.inputDate = inputDate;
			this.reportDate = reportDate;
			this.orderDate = orderDate;
			this.fixedInput = fixedInput;
			this.batchDate = batchDate;
			this.suggestedPO = suggestedPO;
			this.dampedReschdule = dampedReschdule;
			this.mrpSource = mrpSource;
			this.mrpOrderType = mrpOrderType;
			this.expiredQty = expiredQty;
			this.ignoreForReport = ignoreForReport;
			this.reference = reference;
			this.purchaseQty = purchaseQty;
		}

		private MRPInputItem() {
			super();
		}
	}
	
	class MRPInputList {
		private List<MRPInputItem> list = new ArrayList<MRPInputItem>();
		
		private void clear() {
			list.clear();
		}
		
		private void add(MRPInputItem item) {
			list.add(item);
		}
		
		private List<MRPInputItem> list() {
			return list;
		}
		
		private List<MRPInputItem> listSortedByInputDate() {
			List<MRPInputItem> result = new ArrayList<MRPInputItem>(list);
			Collections.sort(result, new Comparator<MRPInputItem>() {

				public int compare(MRPInputItem o1, MRPInputItem o2) {
					return o1.inputDate.compareTo(o2.inputDate);
				}
				
			});
			return result;
		}

	}
	
	class MRPOutputItem {
		private BigDecimal outputQty;
		private Date outputDate;
		private Date reportDate;
		private MRPSource mrpSource;
		private MRPOrderType mrpOrderType;
		private String reference;

		private MRPOutputItem(BigDecimal outputQty, Date outputDate, Date reportDate, MRPSource mrpSource, MRPOrderType mrpOrderType, String reference) {
			super();
			this.outputQty = outputQty;
			this.outputDate = outputDate;
			this.reportDate = reportDate;
			this.mrpSource = mrpSource;
			this.mrpOrderType = mrpOrderType;
			this.reference = reference;
		}
		
	}
	
	class MRPOutputList {
		private List<MRPOutputItem> list = new ArrayList<MRPOutputItem>();
		
		private void clear() {
			list.clear();
		}
		
		private void add(MRPOutputItem item) {
			list.add(item);
		}

		private List<MRPOutputItem> list() {
			return list;
		}
	}
	
	private void clearMRPDetails(MrpVersionControl mrpVersion) {
		mrpInputsService.clear(mrpVersion);
		mrpOutputsService.clear(mrpVersion);
		mrpRecommendationService.clear(mrpVersion);
		mrpReportService.clear(mrpVersion);
		mrpShortageService.clear(mrpVersion);
	}

	@SuppressWarnings("unchecked")
	private List<MpsLine> loadMPSLine() {
		return ormTemplate.findByNamedQueryAndNamedParam("Planing.MRPProcessor.loadMPSLine", "mrpVersionControl", mrpVersion);		
	}

	class OrderItem {
		private int specId;
		private Date docDate;
		private String docNumber;
		private Date requiredDate;
		private Catalog catalog;
		private Warehouse warehouse;
		private Measure measure;
		private boolean fixedInput;
		private BigDecimal qtyOutstanding;
		
		private OrderItem(int specId, Date docDate, String docNumber, Date requiredDate, Catalog catalog, Warehouse warehouse, Measure measure, boolean fixedInput, BigDecimal qtyOutstanding) {
			super();
			this.specId = specId;
			this.docDate = docDate;
			this.docNumber = docNumber;
			this.requiredDate = requiredDate;
			this.catalog = catalog;
			this.warehouse = warehouse;
			this.measure = measure;
			this.fixedInput = fixedInput;
			this.qtyOutstanding = qtyOutstanding;
		}
		
	}
	
	private List<OrderItem> loadLiveSalesOrders(Date startDate, Date endDate) {
		return ormTemplate.findByNamedQueryAndNamedParam("Planing.MRPProcessor.loadSalesOrder", new String[] {"startDate", "endDate"}, new Object[] {startDate, endDate},
				new ResultTransformer<OrderItem>() {

			public OrderItem transformTuple(Object[] tuple, String[] aliases) {
				return new OrderItem((Integer) tuple[0], (Date) tuple[1], (String) tuple[2], (Date) tuple[3], persistentManager.find(Catalog.class, tuple[4]), persistentManager.find(Warehouse.class, tuple[5]), persistentManager.find(Measure.class, tuple[6]), false, (BigDecimal) tuple[7]);
			}
	
		});
	}

	private List<OrderItem> loadLivePurchaseOrders(Date startDate, Date endDate) {
		return ormTemplate.findByNamedQueryAndNamedParam("Planing.MRPProcessor.loadPurchaseOrder", new String[] {"startDate", "endDate"}, new Object[] {startDate, endDate},
				new ResultTransformer<OrderItem>() {

			public OrderItem transformTuple(Object[] tuple, String[] aliases) {
				return new OrderItem((Integer) tuple[0], (Date) tuple[1], (String) tuple[2], (Date) tuple[3], persistentManager.find(Catalog.class, tuple[4]), persistentManager.find(Warehouse.class, tuple[5]), persistentManager.find(Measure.class, tuple[6]), (Boolean) tuple[7], (BigDecimal) tuple[8]);
			}
	
		});
	}

	private List<MrpVersionForecast> loadSalesForecast(ForecastType forecastType) {
		return ormTemplate.findByCriteria(OrmTemplate.createCriteria(MrpVersionForecast.class)
				.add(Restrictions.eq("ForecastVersion", mrpVersion))
				.add(Restrictions.eq("ForecastType", forecastType)));
	}

	private List<PlanningLevel> loadPlanningLevel() {
		return ormTemplate.findByCriteria(OrmTemplate.createCriteria(MrpVersionMps.class, "mrpmps")
				.createAlias("mrpmps.Mps", "mps")
				.createAlias("mps.PlanningLevel", "pl")
				.add(Restrictions.eq("mrpmps.MrpVersionControl", mrpVersion))
				.setProjection(Projections.distinct(Projections.property("pl"))));
	}
	
	private List<PlanningForecast> loadPlanningForecast(PlanningLevel planningLevel, MrpVersionForecast mrpForecast, ForecastType forecastType) {
		Criteria criteria = OrmTemplate.createCriteria(PlanningForecast.class)
				.add(Restrictions.eq("ForecastType", forecastType))
				.add(Restrictions.between("RequiredDate", mrpVersion.getMrpStartDate(), mrpVersion.getMrpEndDate()))
				.add(Restrictions.gt("ForecastQuantity", BigDecimal.ZERO))
				.add(Restrictions.eq("ForecastVersion", mrpForecast));
		if (planningLevel != null)
			criteria.add(Restrictions.eq("PlanningLevel", planningLevel));
		return ormTemplate.findByCriteria(criteria);
	}
	
	private Date beforeProcessingNewMPS(PlanningLevel planningLevel, short bucketOffset) {
		return MfUtils.determineBucketRange(planningLevel.getId(), bucketOffset).getBucketEnd();
	}
	
	private int calculateShelfLife(Catalog catalog) {
		int shelfLife = catalog.getShelfLife() != null ? catalog.getShelfLife().intValue() : 0;
		switch (catalog.getShelfLifeMeas()) {
		case NONE:
		case HOUR:
			return 0;
		case DAY:
			return shelfLife;
		case MONTH:
			//к нулевой дате прибавим к-во месяцев и возмем разницу между полученным результаттом и нулевой датой
			return (int) DateTimeUtils.getDaysBetween(DateTimeUtils.ZERO_DATE, DateTimeUtils.incMonth(DateTimeUtils.ZERO_DATE, shelfLife));
		case YEAR:
			//к нулевой дате прибавим к-во лет и возмем разницу между полученным результаттом и нулевой датой
			return (int) DateTimeUtils.getDaysBetween(DateTimeUtils.ZERO_DATE, DateTimeUtils.incYear(DateTimeUtils.ZERO_DATE, shelfLife));
		default:
			throw new IllegalArgumentException("Invalid shelflife type");
		}
	}
	
	private Date calculateBatchDate(Date date, int shelfLife) {
		if (shelfLife == 0)
			return null;
		else
			return DateTimeUtils.getDayStart(DateTimeUtils.incDay(date, shelfLife));
	}
	
	private String generateMPSReference(MpsLine mpsLine) {
		return Messages.getInstance().getMessage(Messages.MPS_REFERENCE, new Object[] {mpsLine.getMps().getCode().trim(), mpsLine.getId()});
	}

	private String generateMPSChildReference(MpsLine mpsLine) {
		return Messages.getInstance().getMessage(Messages.MPS_CHILD_REFERENCE, new Object[] {mpsLine.getMps().getCode().trim(), mpsLine.getId(), mpsLine.getPlanningItem().getGenericItemCode().trim()});
	}

	private String generateSaleOrderReference(Date docDate, String docNumber, int specId) {
		return Messages.getInstance().getMessage(Messages.SALE_ORDER_REFERENCE, new Object[] {docDate, docNumber, specId});
	}

	private String generatePurchaseOrderReference(Date docDate, String docNumber, int specId) {
		return Messages.getInstance().getMessage(Messages.SALE_ORDER_REFERENCE, new Object[] {docDate, docNumber, specId});
	}

	private String generatePurchaseProductForecastReference(PlanningForecast forecast) {
		String key = null;
		switch (forecast.getForecastMethod()) {
		case BY_DATE:
			key = Messages.PURCHASE_FORECAST_ON_DATE_REFERENCE;
			break;
		case BY_PERIOD:
			key = Messages.PURCHASE_FORECAST_ON_PERIOD_REFERENCE;
			break;
		}
		return Messages.getInstance().getMessage(key, new Object[] {forecast.getForecastVersion().getCode(), forecast.getId()});		
	}

	private String generateSaleProductForecastReference(PlanningForecast forecast) {
		String key = null;
		switch (forecast.getForecastMethod()) {
		case BY_DATE:
			key = Messages.SALE_FORECAST_ON_DATE_REFERENCE;
			break;
		case BY_PERIOD:
			key = Messages.SALE_FORECAST_ON_PERIOD_REFERENCE;
			break;
		}
		return Messages.getInstance().getMessage(key, new Object[] {forecast.getForecastVersion().getCode(), forecast.getId()});		
	}

	private String generateJobReference(Job job) {
		return Messages.getInstance().getMessage(Messages.JOB_REFERENCE, new Object[] {job.getJobDate(), job.getJobNumber()});
	}
	
	private String generateJobOutputReference(JobMaterial jobMaterial) {
		return Messages.getInstance().getMessage(Messages.JOB_OUTPUT_REFERENCE, new Object[] {jobMaterial.getOper().getJob().getJobDate(), jobMaterial.getOper().getJob().getJobNumber(), jobMaterial.getId()});
	}
	
	private String generateInBoundFirmPlannedWarehouseTransferReference(FirmPlannedOrder order) {
		return Messages.getInstance().getMessage(Messages.IN_BOUND_FIRM_PLANNED_REFERENCE, new Object[] {order.getSourceWarehouse().getCode().trim(), order.getId()});
	}

	private String generateOutBoundFirmPlannedWarehouseTransferReference(FirmPlannedOrder order) {
		return Messages.getInstance().getMessage(Messages.OUT_BOUND_FIRM_PLANNED_REFERENCE, new Object[] {order.getWarehouse().getCode().trim(), order.getId()});
	}

	private void createMRPInputs(int referenceId, String reference, Date requiredDate, BigDecimal quantity,
			MRPOrderType orderType, MRPSource source, boolean fixedInput, Catalog catalog, Warehouse warehouse, Measure measure, boolean addedInInventoryList) {
		if (addedInInventoryList)
			mrpInventoryList.add(catalog, warehouse);
		
		MrpInputs mrpInputs = mrpInputsService.initialize();
		mrpInputs.setMrpVersionControl(mrpVersion);
		mrpInputs.setReferenceId(referenceId);
		mrpInputs.setPpReference(reference);
		mrpInputs.setRequiredDate(DateTimeUtils.getDayStart(requiredDate));
		if (measure != null && catalog.getMeasure1().getId() != measure.getId())
			quantity = measureConvService.conversion(measure, catalog.getMeasure1(), catalog, requiredDate, quantity);
		mrpInputs.setMrpQuantity(quantity);
		mrpInputs.setMrpOrderType(orderType);
		mrpInputs.setMrpSource(source);
		mrpInputs.setFixedInput(fixedInput);
		mrpInputs.setBatchDate(calculateBatchDate(requiredDate, calculateShelfLife(catalog)));
		mrpInputs.setWarehouseId(warehouse.getId());
		mrpInputs.setCatalogId(catalog.getId());
		
		mrpInputsList.add(mrpInputs);
	}
	
	private void createMRPOutputs(int referenceId, String reference, Date requiredDate, BigDecimal quantity,
			MRPOrderType orderType, MRPSource source, Catalog catalog, Warehouse warehouse, Measure measure) {
		if (warehouse == null)
			throw new WarehouseNotSetupException(reference);
		
		mrpInventoryList.add(catalog, warehouse);
		
		MrpOutputs mrpOutputs = mrpOutputsService.initialize();
		mrpOutputs.setMrpVersionControl(mrpVersion);
		mrpOutputs.setReferenceId(referenceId);
		mrpOutputs.setPpReference(reference);
		mrpOutputs.setRequiredDate(DateTimeUtils.getDayStart(requiredDate));
		if (catalog.getMeasure1().getId() != measure.getId())
			quantity = measureConvService.conversion(measure, catalog.getMeasure1(), catalog, requiredDate, quantity);
		mrpOutputs.setMrpQuantity(quantity);
		mrpOutputs.setMrpOrderType(orderType);
		mrpOutputs.setMrpSource(source);
		mrpOutputs.setWarehouseId(warehouse.getId());
		mrpOutputs.setCatalogId(catalog.getId());

		mrpOutputsList.add(mrpOutputs);
	}
	
	private long materialBreakdown(MpsLine mpsLine, Bom bom, WeekCalendar weekCalendar, long numberOfJobs, BigDecimal lotQty, long requiredDate) {
		Date reqDate = MfUtils.tickToDate(requiredDate);
		//long tmpRequiredDate = requiredDate;
		List<BomRoute> bomRoutes = ormTemplate.findByCriteria(OrmTemplate.createCriteria(BomRoute.class)
				.add(Restrictions.eq("Bom", bom))
				.add(Restrictions.le("EffOnDate", reqDate))
				.add(Restrictions.ge("EffOffDate", reqDate))
				.addOrder(Order.desc("OperNum"))); //идем от конечных операций к начальным назад
		for (BomRoute bomRoute : bomRoutes) {
			//Вычисляем время выполнения текущей операции в тиках
			long timeOper = numberOfJobs * bomRoute.getSetupTicks() + lotQty.multiply(new BigDecimal(numberOfJobs * bomRoute.getRunTicks())).longValue();
			//Определяем время окончания текущей операции
			TimeRange timeRange = MfUtils.getTimes(weekCalendar.getId(), requiredDate, timeOper, ScheduleDirection.BACKWARD);
			
			List<BomMaterial> bomMaterials = ormTemplate.findByCriteria(OrmTemplate.createCriteria(BomMaterial.class)
					.add(Restrictions.eq("BomRoute", bomRoute))
					.add(Restrictions.le("EffOnDate", reqDate))
					.add(Restrictions.ge("EffOffDate", reqDate)));
			for (BomMaterial bomMaterial : bomMaterials) {
				BigDecimal materialQty = MfUtils.calculateBOMMaterialQuan(bomMaterial, reqDate, bom.getPlanningLotQty()).multiply(lotQty).multiply(new BigDecimal(numberOfJobs));
				createMRPOutputs(mpsLine.getId(), generateMPSChildReference(mpsLine), MfUtils.tickToDate(timeRange.getStartDateTime()), materialQty,
						MRPOrderType.FIRM_PLANNED, MRPSource.MANUFACTURING, bomMaterial.getCatalog(), bom.getDefSrcStock(), bomMaterial.getMeasure());
			}
			
			timeRange = MfUtils.getTimes(weekCalendar.getId(), timeRange.getStartDateTime(), numberOfJobs * bomRoute.getMoveTicks(), ScheduleDirection.BACKWARD);
			requiredDate = timeRange.getStartDateTime(); //вычитаем время на перемещение между операциями
		}
		return requiredDate;
	}
	
	private void processMPSMaterial(MpsLine mpsLine, BigDecimal fractionToUse) {
		Bom bom = bomService.findCurrentBOM(mpsLine.getPlanningItem().getCatalog().getId());
		if (bom == null)
			return;
		
		BigDecimal plannedQty = mpsLine.getPlannedQty().add(mpsLine.getAdjustmentQty()).multiply(fractionToUse);
		BigDecimal maxLotQty = bom.getMaxLotQty() != null ? bom.getMaxLotQty() : BigDecimal.ZERO;
		if (MathUtils.compareToZero(maxLotQty) == 0)
			maxLotQty = plannedQty; //Infinite maximum size
		long numberOfMaxJobs = plannedQty.divide(maxLotQty).longValue();
		BigDecimal remainder = plannedQty.subtract(maxLotQty.multiply(new BigDecimal(numberOfMaxJobs)));
		BigDecimal oddJobSize = remainder;
		
		BucketRange bucketRange = MfUtils.determineBucketRange(mpsLine.getMps().getPlanningLevel().getId(), mpsLine.getBucketOffset());
		
		//create inputs
		createMRPInputs(mpsLine.getId(), generateMPSReference(mpsLine), bucketRange.getBucketEnd(), plannedQty,
				MRPOrderType.FIRM_PLANNED, MRPSource.MANUFACTURING, true, mpsLine.getPlanningItem().getCatalog(),
				bom.getDefDstStock(), mpsLine.getMeasure(), true);
		
		long currentRequiredDate = MfUtils.dateToTick(DateTimeUtils.incDay(bucketRange.getBucketEnd(), 1)) - 1000; // на конец текущего дня
		currentRequiredDate = materialBreakdown(mpsLine, bom, mpsLine.getMps().getWeekCal(), 1, maxLotQty.multiply(new BigDecimal(numberOfMaxJobs)).add(oddJobSize), currentRequiredDate);
	}
	
	private void processMPSVersions() {
		short planningLevelNum = 0;
		Date planningDate = DateTimeUtils.ZERO_DATE;
		Date lastDateProcessed = DateTimeUtils.ZERO_DATE;
		boolean firstEnter = true;
	    /* здесь и далее подразумеваем, что разные версии MPS имеют разные
	        даты начала планирования (PLANNING_DATE).
	        Упорядочиваем выборку так, чтобы сначала шли записи от версии MPS
	        с небольшим номером уровня планирования, (например с дневными бакетами),
	        потом с бОльшим номером уровня планирования (например с недельными бакетами) */
		for (MpsLine mpsLine : loadMPSLine()) {
			//меняется значение набора полей
			if (planningLevelNum != mpsLine.getMps().getPlanningLevel().getPlanningLevelNum()
					&& planningDate.compareTo(mpsLine.getMps().getPlanningDate()) != 0) {
				planningLevelNum = mpsLine.getMps().getPlanningLevel().getPlanningLevelNum();
				planningDate = mpsLine.getMps().getPlanningDate();
				//При обработке первой записи следующая процедура НЕ должна вызываться
				if (!firstEnter)
					lastDateProcessed = beforeProcessingNewMPS(mpsLine.getMps().getPlanningLevel(), mpsLine.getBucketOffset());
			}
			
			BucketRange bucketRange = MfUtils.determineBucketRange(mpsLine.getMps().getPlanningLevel().getId(), mpsLine.getBucketOffset());
			if (bucketRange.getBucketEnd().compareTo(lastDateProcessed) <= 0) {
				/* This bucket ends before the last bucket processed
				   which means that we do not want to include
				   anything in this bucket */				
			}
			else {
				// Allows for any overlap between plans
				BigDecimal fractionToUse = BigDecimal.ONE;
				if (bucketRange.getBucketStart().compareTo(lastDateProcessed) <= 0) {
					/* This bucket partially overlaps the previous
					   one processed, therefore calculate a fraction of
					   this buckets output to be used */
					
					//Определяем количество дней в бакете
					long daysInBucket = DateTimeUtils.getDaysBetween(bucketRange.getBucketStart(), bucketRange.getBucketEnd()) + 1;
					long daysOverlap = DateTimeUtils.getDaysBetween(lastDateProcessed, bucketRange.getBucketStart()) + 1;
					fractionToUse = new BigDecimal(daysInBucket - daysOverlap).divide(new BigDecimal(daysInBucket));
				}
				processMPSMaterial(mpsLine, fractionToUse);
			}
			
			firstEnter = false;
		}
	}
	
	private void processLiveSalesOrders() {
		for (OrderItem item : loadLiveSalesOrders(mrpVersion.getMrpStartDate(), mrpVersion.getMrpEndDate())) {
			createMRPOutputs(item.specId, generateSaleOrderReference(item.docDate, item.docNumber, item.specId), item.requiredDate, item.qtyOutstanding,
					MRPOrderType.ACTUAL, MRPSource.SALES, item.catalog, item.warehouse, item.measure);
		}
	}
	
	private void planningProductForecast(PlanningForecast forecast, Date requiredDate) {
		switch (forecast.getForecastType()) {
		case PURCHASE:
			createMRPInputs(forecast.getId(), generatePurchaseProductForecastReference(forecast), requiredDate,
					forecast.getForecastQuantity(), MRPOrderType.FORECAST, MRPSource.PURCHASES, false,
					forecast.getPlanningItem().getCatalog(), (Warehouse) forecast.getContractor(), forecast.getMeasure(), true);
			break;
		case SALE:
			createMRPOutputs(forecast.getId(), generateSaleProductForecastReference(forecast), requiredDate, forecast.getForecastQuantity(),
					MRPOrderType.FORECAST, MRPSource.SALES, forecast.getPlanningItem().getCatalog(),
					(Warehouse) forecast.getContractor(), forecast.getMeasure());
			break;
		}
	}

	private void processProductFamilies(PlanningForecast forecast, Date requiredDate) {
		//TODO
		//В прогнозы пока будем вводить только 
		//обобщенные товары с PP_GENERIC_ITEM.PLANNING_ITEM_FLAG = true
	}

	private void genericProductForecast(PlanningForecast forecast, Date requiredDate) {
		if (forecast.getPlanningItem().getPlanningItemFlag())
			planningProductForecast(forecast, requiredDate);
		else
			processProductFamilies(forecast, requiredDate);
	}
	
	private void catalogForecast(PlanningForecast forecast, Date requiredDate) {
		switch (forecast.getForecastType()) {
		case PURCHASE:
			createMRPInputs(forecast.getId(), generatePurchaseProductForecastReference(forecast), requiredDate,
					forecast.getForecastQuantity(), MRPOrderType.FORECAST, MRPSource.PURCHASES, false,
					forecast.getCatalog(), (Warehouse) forecast.getContractor(), forecast.getMeasure(), true);
			break;
		case SALE:
			createMRPOutputs(forecast.getId(), generateSaleProductForecastReference(forecast), requiredDate, forecast.getForecastQuantity(),
					MRPOrderType.FORECAST, MRPSource.SALES, forecast.getCatalog(),
					(Warehouse) forecast.getContractor(), forecast.getMeasure());
			break;
		}
	}
	
	private void processPlanningForecasts(PlanningForecast forecast) {
		Date requiredDate = null;
		switch (forecast.getForecastMethod()) {
		case BY_PERIOD:
			//Period Forecasts
			BucketRange bucketRange = MfUtils.determineBucketRange(forecast.getPlanningLevel().getId(), forecast.getBucketOffset());
			switch (forecast.getForecastType()) {
			case PURCHASE:
				//If this is a purchase forecast load it up at the start of the bucket
				requiredDate = bucketRange.getBucketStart();
				break;
			case SALE:
				//If this is a sales forecast load it up at the end of the bucket
				requiredDate = bucketRange.getBucketEnd();
				break;
			default:
				throw new IllegalStateException("Illegal forecast type");
			}
			break;
		case BY_DATE:
			//Date Forecasts
			requiredDate = forecast.getRequiredDate();
			break;
		default:
			throw new IllegalStateException("Illegal forecast method");
		}
		
		if (forecast.getPlanningItem() != null)
			genericProductForecast(forecast, requiredDate);
		else
			catalogForecast(forecast, requiredDate);
	}
	
	private void processSalesForecastVersion(PlanningLevel planningLevel, MrpVersionForecast mrpForecast) {
		for (PlanningForecast forecast : loadPlanningForecast(planningLevel, mrpForecast, ForecastType.SALE))
			processPlanningForecasts(forecast);
	}

	private void processPurchaseForecastVersion(PlanningLevel planningLevel, MrpVersionForecast mrpForecast) {
		for (PlanningForecast forecast : loadPlanningForecast(planningLevel, mrpForecast, ForecastType.PURCHASE))
			processPlanningForecasts(forecast);
	}

	private void processSalesForecasts() {
//		int linksCount = ormTemplate.findUniqueByCriteria(OrmTemplate.createCriteria(MrpVersionMps.class)
//				.add(Restrictions.eq("MrpVersionControl", mrpVersion))
//				.setProjection(Projections.count("Id")));
		List<PlanningLevel> planningLevels = loadPlanningLevel();
		if (planningLevels.isEmpty()) {
			// это означает, что в данной версии MRP расчета мы не хотим учитывать производственные
			// планы (MPS), или что у нас вообще нет производства
			List<MrpVersionForecast> forecasts = loadSalesForecast(ForecastType.SALE);
			for (MrpVersionForecast forecast : forecasts)
				processSalesForecastVersion(null, forecast);
		} else {
			List<MrpVersionForecast> forecasts = loadSalesForecast(ForecastType.SALE);
			for (PlanningLevel level : planningLevels)
				for (MrpVersionForecast forecast : forecasts)
					processSalesForecastVersion(level, forecast);
		}
	}
	
	private void processPurchaseForecasts() {
		List<PlanningLevel> planningLevels = loadPlanningLevel();
		if (planningLevels.isEmpty()) {
			// это означает, что в данной версии MRP расчета мы не хотим учитывать производственные
			// планы (MPS), или что у нас вообще нет производства
			List<MrpVersionForecast> forecasts = loadSalesForecast(ForecastType.PURCHASE);
			for (MrpVersionForecast forecast : forecasts)
				processPurchaseForecastVersion(null, forecast);			
		} else {
			List<MrpVersionForecast> forecasts = loadSalesForecast(ForecastType.PURCHASE);
			for (PlanningLevel level : planningLevels)
				for (MrpVersionForecast forecast : forecasts)
					processPurchaseForecastVersion(level, forecast);			
		}
	}
	
	private void processLivePurchaseOrders() {
		for (OrderItem item : loadLivePurchaseOrders(mrpVersion.getMrpStartDate(), mrpVersion.getMrpEndDate())) {
			createMRPInputs(item.specId, generatePurchaseOrderReference(item.docDate, item.docNumber, item.specId), item.requiredDate, item.qtyOutstanding,
					MRPOrderType.ACTUAL, MRPSource.PURCHASES, item.fixedInput, item.catalog, item.warehouse, item.measure, true);
		}
	}
	
	private void processLiveJobOutputs() {
		List<Job> jobs = ormTemplate.findByCriteria(OrmTemplate.createCriteria(Job.class)
				.add(Restrictions.between("EndDate", mrpVersion.getMrpStartDate(), mrpVersion.getMrpEndDate()))
				.add(Restrictions.eq("JobStatus", JobStatus.RUNNING)));
		for (Job job : jobs) {
			BigDecimal outstandingJobQuantity = job.getQtyReleased().subtract(job.getQtyComplete());
			if (MathUtils.compareToZero(outstandingJobQuantity) > 0) {
				createMRPInputs(job.getId(), generateJobReference(job), job.getEndDate(), outstandingJobQuantity, MRPOrderType.ACTUAL, MRPSource.JOBS, true,
						job.getCatalog(), (Warehouse) job.getDefDstStock(), null, true);
			}
		}
	}

	private void processLiveJobRequirements() {
		List<JobMaterial> jobMaterials = ormTemplate.findByCriteria(OrmTemplate.createCriteria(JobMaterial.class, "jm")
				.createAlias("jm.Oper", "jr")
				.createAlias("jr.Job", "j")
				.add(Restrictions.between("j.StartDate", mrpVersion.getMrpStartDate(), mrpVersion.getMrpEndDate()))
				.add(Restrictions.eq("j.JobStatus", JobStatus.RUNNING)));
		TransactionServiceLocal transactionService = (TransactionServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(TransactionServiceLocal.SERVICE_NAME);
		
		for (JobMaterial jobMaterial : jobMaterials) {
			BigDecimal qtyMaterialRequired = jobMaterial.getOper().getJob().getQtyReleased() //количество продукции по ЗНП
					.multiply(ManufactureUtils.calculateJobMaterialQuan(jobMaterial)) //применяемость
					.subtract(transactionService.getQuantityByResource(jobMaterial.getId())); //фактически списано
			
			/* do not allow to go negative (ex., over-issued job,etc)
			   since used as source of MRP_OUTPUTS(MRP_QUANTITY), which,
			   in turn,  becomes the source of MRP_REPORT(QTY_AVAILABLE) */
			if (MathUtils.compareToZero(qtyMaterialRequired) < 0)
				qtyMaterialRequired = BigDecimal.ZERO;
			
			createMRPOutputs(jobMaterial.getId(), generateJobOutputReference(jobMaterial), jobMaterial.getOper().getJob().getStartDate(), qtyMaterialRequired, MRPOrderType.ACTUAL,
					MRPSource.JOBS, jobMaterial.getCatalog(), (Warehouse) jobMaterial.getOper().getJob().getDefSrcStock(), jobMaterial.getMeasure());
		}
	}

	private void processInBoundFirmPlannedWarehouseTransfers(Warehouse warehouse) {
		List<FirmPlannedOrder> orders = ormTemplate.findByCriteria(OrmTemplate.createCriteria(FirmPlannedOrder.class)
				.add(Restrictions.eq("MrpVersionControl", mrpVersion))
				.add(Restrictions.eq("Warehouse", warehouse))
				.add(Restrictions.eq("PurchaseOrTransfer", RecommendType.TRANSFER))
				.add(Restrictions.between("RequiredDate", mrpVersion.getMrpStartDate(), mrpVersion.getMrpEndDate())));
		for (FirmPlannedOrder order : orders)
			if (MathUtils.compareToZero(order.getOrderQty()) > 0) {
				createMRPInputs(order.getId(), generateInBoundFirmPlannedWarehouseTransferReference(order), order.getRequiredDate(), order.getOrderQty(), MRPOrderType.FIRM_PLANNED,
						MRPSource.TRANSFERS, order.getFixedInput(), order.getCatalog(), (Warehouse) order.getWarehouse(), order.getMeasure(), true);
			}
	}

	private void processOutBoundFirmPlannedWarehouseTransfers(Warehouse warehouse) {
		List<FirmPlannedOrder> orders = ormTemplate.findByCriteria(OrmTemplate.createCriteria(FirmPlannedOrder.class)
				.add(Restrictions.eq("MrpVersionControl", mrpVersion))
				.add(Restrictions.eq("SourceWarehouse", warehouse))
				.add(Restrictions.eq("PurchaseOrTransfer", RecommendType.TRANSFER))
				.add(Restrictions.between("RequiredDate", mrpVersion.getMrpStartDate(), mrpVersion.getMrpEndDate())));
		for (FirmPlannedOrder order : orders)
			if (MathUtils.compareToZero(order.getOrderQty()) > 0)
				createMRPOutputs(order.getId(), generateOutBoundFirmPlannedWarehouseTransferReference(order), order.getRequiredDate(),
						order.getOrderQty(), MRPOrderType.FIRM_PLANNED, MRPSource.TRANSFERS, order.getCatalog(),
						(Warehouse) order.getSourceWarehouse(), order.getMeasure());
		
	}

	private void processFirmPlannedWarehouseTransfers() {
		List<Warehouse> warehouses = ormTemplate.findByCriteria(OrmTemplate.createCriteria(Warehouse.class));
		for (Warehouse warehouse : warehouses) {
			processInBoundFirmPlannedWarehouseTransfers(warehouse);
			processOutBoundFirmPlannedWarehouseTransfers(warehouse);
		}
	}

	private void processCurrentQtyOnHand() {
		CurrentStockSituation stockSituation = CurrentStockSituationLocator.locate();
		for (MRPInventoryItem item : mrpInventoryList.list()) {
			StockSituationValues values = stockSituation.getSituation(item.warehouse, item.catalog, false);
			if (values != null)
				createMRPInputs(0, Messages.getInstance().getMessage(Messages.QUANTITY_CURRENT_ON_HAND_REFERENCE), mrpVersion.getRunDate(),
						values.getAvailable1(), MRPOrderType.ACTUAL, MRPSource.INVENTORY, true, item.catalog, item.warehouse, item.catalog.getMeasure1(), false);
		}
	}

	private void mrpBuild() {
		processMRPInventoryStream();
	}

	private void processMRPInventoryStream() {
		//создаем таблицы в памяти для дальнейшей обработки
		virtualInputs = new MRPInputList();
		virtualOutputs = new MRPOutputList();
		try {
			for (MRPInventoryItem item : mrpInventoryList.list()) {
				processPart(item);
				availableSetup(item);
			}
		} finally {
			virtualInputs.clear();
			virtualInputs = null;
			virtualOutputs.clear();
			virtualOutputs = null;
		}
	}
	
	private Date getSendOrderToProviderDate(Integer id) {
		return ormTemplate.findUniqueByCriteria(OrmTemplate.createCriteria(OrderSpec.class, "os")
				.createAlias("os.DocHead", "oh")
				.add(Restrictions.eq("os.Id", id))
				.setProjection(Projections.property("oh.DocDate")));
	}
	
	private void loadVirtualTables(MRPInventoryItem inventoryItem) {
		//handle inputs
		virtualInputs.clear();
		for (MrpInputs inputs : mrpInputsList) {
			if (inputs.getCatalogId() == inventoryItem.catalog.getId()
					&& inputs.getWarehouseId() == inventoryItem.warehouse.getId()) {
				Date orderDate = null;
				if (MRPOrderType.ACTUAL.equals(inputs.getMrpOrderType())
						&& MRPSource.PURCHASES.equals(inputs.getMrpSource()))
					orderDate = getSendOrderToProviderDate(inputs.getReferenceId());
				MRPInputItem item = new MRPInputItem(
						inputs.getMrpQuantity(),
						inputs.getMrpQuantity(),
						inputs.getRequiredDate(),
						inputs.getRequiredDate(),
						inputs.getRequiredDate(),
						orderDate,
						inputs.getFixedInput(),
						inputs.getBatchDate(),
						false,
						false,
						inputs.getMrpSource(),
						inputs.getMrpOrderType(),
						BigDecimal.ZERO,
						false,
						inputs.getPpReference(),
						null);
				
				if (item.inputDate.compareTo(mrpVersion.getRunDate()) < 0)
					item.inputDate = mrpVersion.getRunDate();
				
				if (item.inputDate.compareTo(mrpStartDate) < 0)
					mrpStartDate = item.inputDate;
				
				if (inputs.getRequiredDate().compareTo(mrpEndDate) > 0)
					mrpEndDate = inputs.getRequiredDate();
				
				virtualInputs.add(item);
			}
		}
		
		//handle outputs
		virtualOutputs.clear();
		for (MrpOutputs outputs : mrpOutputsList) {
			if (outputs.getCatalogId() == inventoryItem.catalog.getId()
					&& outputs.getWarehouseId() == inventoryItem.warehouse.getId()) {
				MRPOutputItem item = new MRPOutputItem(
						outputs.getMrpQuantity(),
						outputs.getRequiredDate(),
						outputs.getRequiredDate(),
						outputs.getMrpSource(),
						outputs.getMrpOrderType(),
						outputs.getPpReference());
				
				if (item.outputDate.compareTo(mrpVersion.getRunDate()) < 0)
					item.outputDate = mrpVersion.getRunDate();
				
				if (item.outputDate.compareTo(mrpStartDate) < 0)
					mrpStartDate = item.outputDate;
				
				if (outputs.getRequiredDate().compareTo(mrpEndDate) > 0)
					mrpEndDate = outputs.getRequiredDate();
				
				virtualOutputs.add(item);
			}	
		}
	}
	
	private BigDecimal getTotalInputs(Date day) {
		BigDecimal result = BigDecimal.ZERO;
		for (MRPInputItem item : virtualInputs.list())
			if (item.inputDate.compareTo(day) <= 0 && MathUtils.compareToZero(item.inputQty) > 0)
				result = result.add(item.inputQty);
		return result;
	}

	private BigDecimal getTotalOuputs(Date day) {
		BigDecimal result = BigDecimal.ZERO;
		for (MRPOutputItem item : virtualOutputs.list())
			if (item.outputDate.compareTo(day) == 0 && MathUtils.compareToZero(item.outputQty) > 0)
				result = result.add(item.outputQty);
		return result;
	}
	
	private void increaseAvailable(MRPInventoryItem inventoryItem, Date reqDate, BigDecimal quantity) {
		BigDecimal safetyLevel = inventoryItem.catalogWarehouse.getSafetyLevel();
		BigDecimal qtyReqD = safetyLevel.subtract(quantity);
		if (MathUtils.compareToZero(qtyReqD) == 0)
			return;
		
		// Before running off and creating 'Suggested Purchase Orders'
		// we first check to see if there are any Inputs at some point
		// in the future that we can move back to this point in time
		// Пытаемся перепланировать будущие НЕЗАФИКСИРОВАННЫЕ поступления на нужную дату
		for (MRPInputItem item : virtualInputs.listSortedByInputDate()) {
			if (item.inputDate.compareTo(reqDate) > 0 && MathUtils.compareToZero(item.inputQty) > 0 && !item.fixedInput) {
				if (MathUtils.compareToZero(qtyReqD) <= 0)
					break;
				
				BigDecimal qtyTmp = null;
				int changeInDate = (int) DateTimeUtils.getDaysBetween(item.inputDate, reqDate);
				if (changeInDate <= inventoryItem.catalogWarehouse.getMrpDampingDays()
						|| reqDate.compareTo(mrpVersion.getRunDate()) < 0) {
					MRPInputItem newItem = new MRPInputItem();
					// We will assume that this input can be used for to meet
					// this demand but flag it because it's inside the damping
					// window and therefore we don't actually need to re-schedule it.
					// Also no point in re-scheduling if the input is in arrears
					newItem.dampedReschdule = true;
					if (qtyReqD.compareTo(newItem.inputQty) > 0) {
						qtyReqD = qtyReqD.subtract(newItem.inputQty);
						qtyTmp = newItem.inputQty;
						newItem.inputQty = BigDecimal.ZERO;
					} else {
						newItem.inputQty = newItem.inputQty.subtract(qtyReqD);
						qtyTmp = qtyReqD;
						qtyReqD = BigDecimal.ZERO;
					}
					
					newItem.inputQty = qtyTmp;
					newItem.inputDate = reqDate;
					newItem.reportDate = item.inputDate;
					newItem.originalInputQty = qtyTmp;
					newItem.originalInputDate = reqDate;
					newItem.batchDate = calculateBatchDate(reqDate, inventoryItem.shelfLife);
					newItem.reference = StringUtils.EMPTY_STRING;
					newItem.suggestedPO = false;
					newItem.dampedReschdule = false;
					newItem.expiredQty = BigDecimal.ZERO;
					newItem.ignoreForReport = true;
					// As this is a special case we need to reduce the available
					//  input quantity now rather than when it is will actually
					//  consumed
					virtualInputs.add(newItem);
				} else {
					item.inputDate = reqDate;
					item.reportDate = item.inputDate;
					// Reschedule the input, note also that we have to move back
					// the expiry date of this input
					if (item.batchDate != null)
						item.batchDate = DateTimeUtils.incDay(item.batchDate, changeInDate);
					if (qtyReqD.compareTo(item.inputQty) > 0)
						qtyReqD = qtyReqD.subtract(item.inputQty);
					else
						qtyReqD = BigDecimal.ZERO;
				}
			}
		}
		
		if (MathUtils.compareToZero(qtyReqD) > 0 && reqDate.compareTo(mrpVersion.getRunDate()) >= 0) {
			//Создание предложений о закупке
			int suggestedOrderNumber;
			if (!lookForAnEarlierSuggestedOrder(inventoryItem, reqDate, qtyReqD)) {
				if (RouteSrcType.TRANSFER.equals(inventoryItem.route.getSrcType()))
					suggestedOrderNumber = ++transferOrderLastNumber;
				else
					suggestedOrderNumber = ++purchaseOrderLastNumber;
				qtyReqD = roundOrderQuantity(inventoryItem, qtyReqD);
				BigDecimal roundedPOQty = calculatePurchaseUnits(inventoryItem, qtyReqD);
				
				MRPInputItem newItem = new MRPInputItem();
				newItem.inputQty = qtyReqD;
				newItem.purchaseQty = roundedPOQty;
				newItem.inputDate = reqDate;
				newItem.reportDate = newItem.inputDate;
				newItem.originalInputQty = qtyReqD;
				newItem.originalInputDate = reqDate;
				newItem.batchDate = calculateBatchDate(reqDate, inventoryItem.shelfLife);
				newItem.reference = Messages.getInstance().getMessage(Messages.SUGGESTED_ORDER_NUMBER_REFERENCE, new Object[] {suggestedOrderNumber});
				newItem.suggestedPO = true;
				newItem.dampedReschdule = false;
				newItem.expiredQty = BigDecimal.ZERO;
				newItem.ignoreForReport = false;
				newItem.fixedInput = false;
				
				virtualInputs.add(newItem);
			}
		}
	}
	
	private BigDecimal roundOrderQuantity(MRPInventoryItem inventoryItem, BigDecimal quantity) {
		BigDecimal result = quantity;
		BigDecimal reorderLotSize = inventoryItem.route.getReorderLotSize(), reorderMinQty = inventoryItem.route.getReorderMinQty();
		
		if (quantity.compareTo(reorderMinQty) < 0)
			result = reorderMinQty; //Increase qty to Minimum Reorder qty
		
		if (MathUtils.compareToZero(reorderLotSize) > 0) {
			BigDecimal lotSize = MathUtils.round(result.divide(reorderLotSize), new RoundContext(0)).multiply(reorderLotSize);
			if (lotSize.compareTo(result) != 0)
				result = lotSize.add(reorderLotSize);
		}
			
		
		return result;
	}
	
	private BigDecimal calculatePurchaseUnits(MRPInventoryItem inventoryItem, BigDecimal quantity) {
		//QtyPurchaseUnits := преобразовать QtyInvUnits в ЕИ для закупки;
		//Если у нас в системе нигде не задается ЕИ для товара для закупок, то просто присваиваем
		//OVS, пока не задается
		return quantity;
	}
	
	private boolean lookForAnEarlierSuggestedOrder(MRPInventoryItem inventoryItem, Date date, BigDecimal quantity) {
		Date limitDate = null;
		if (inventoryItem.shelfLife != 0 && inventoryItem.catalogWarehouse.getOrderIntervalDays() > inventoryItem.shelfLife)
			/* If the shelf life is greater than the Order Interval,
    		   use the shelf life as the search limit as there is no
    		   point in ordering stock for the required date if it's
    		   going to expire before we can use it */
			limitDate = DateTimeUtils.incDay(date, 1 - inventoryItem.shelfLife);
		else
			limitDate = DateTimeUtils.incDay(date, 1 - inventoryItem.catalogWarehouse.getOrderIntervalDays());
		
		List<MRPInputItem> list = virtualInputs.listSortedByInputDate();
		boolean finded = false;
		for (MRPInputItem item : list)
			if (item.suggestedPO && item.inputDate.compareTo(limitDate) > 0)
				finded = true;
		
		if (finded) {
			MRPInputItem item = list.get(list.size() - 1); //descending order
			BigDecimal roundedQty = roundOrderQuantity(inventoryItem, item.originalInputQty.add(quantity));
			BigDecimal roundedPOQty = calculatePurchaseUnits(inventoryItem, roundedQty);
			BigDecimal changeInOrderQty = roundedQty.subtract(item.originalInputQty);
			
			item.inputQty = item.inputQty.add(changeInOrderQty);
			item.originalInputQty = roundedQty;
			item.purchaseQty = roundedPOQty;
		}
		
		return finded;
	}
	
	private void updateInputs(MRPInventoryItem inventoryItem, Date inputDate, BigDecimal quantity) {
		for (MRPInputItem item : virtualInputs.listSortedByInputDate()) {
			if (item.inputDate.compareTo(inputDate) <= 0) {
				if (MathUtils.compareToZero(quantity) <= 0)
					break;
				if (quantity.compareTo(item.inputQty) < 0) {
					item.inputQty = item.inputQty.subtract(quantity);
					quantity = BigDecimal.ZERO;
				} else {
					quantity = quantity.subtract(item.inputQty);
					item.inputQty = BigDecimal.ZERO;
				}
			}
		}
	}
	
	private void updateExpiredInputs(Date expiryDate) {
		for (MRPInputItem item : virtualInputs.list()) {
			// здесь подразумевается, что если у товара бесконечное время хранения,
			// то BATCH_DATE is null и эти записи НЕ должны попадать в выборку
			if (item.batchDate != null && item.batchDate.compareTo(expiryDate) <= 0
					&& MathUtils.compareToZero(item.inputQty) > 0) {
				item.expiredQty = item.inputQty;
				item.inputQty = BigDecimal.ZERO;
			}
		}
	}
	
	private void recommendedCancellationsOrWarnings(MRPInventoryItem inventoryItem, MRPInputItem inputItem) {
		MrpRecommendation recommendItem = mrpRecommendationService.initialize();
		recommendItem.setMrpVersionControl(mrpVersion);
		recommendItem.setRequiredDate(inputItem.reportDate);
		recommendItem.setFirmPlanSuggestedOrder(false);
		recommendItem.setMrpArrearsFlag(false);
		recommendItem.setMrpOrdered(false);
		recommendItem.setCatalog(inventoryItem.catalog);
		recommendItem.setMeasure(inventoryItem.catalog.getMeasure1());
		recommendItem.setVendor(null);
		recommendItem.setWarehouse(inventoryItem.warehouse);
		recommendItem.setPpReference(inputItem.reference);
		recommendItem.setManualEntry(false);
		recommendItem.setMrpRescheduleFlag(RescheduleFlag.RESCHEDULE);
		recommendItem.setMrpSource(inputItem.mrpSource);
		recommendItem.setOriginalDate(inputItem.originalInputDate);
		recommendItem.setOriginalQuantity(inputItem.inputQty);
		if (inputItem.fixedInput) {
			recommendItem.setMrpRescheduleFlag(RescheduleFlag.WARNING);
			recommendItem.setMrpQuantity(inputItem.inputQty);
		} else {
			recommendItem.setMrpRescheduleFlag(RescheduleFlag.CANCEL);
			recommendItem.setMrpQuantity(BigDecimal.ZERO);
		}
		recommendItem.setOrderDate(null);
		
		mrpRecommendationList.add(recommendItem);
		
		MrpReport mrpReport = mrpReportService.initialize();
		mrpReport.setMrpVersionControl(mrpVersion);
		mrpReport.setRequiredDate(inputItem.reportDate);
		mrpReport.setMrpOrderType(inputItem.mrpOrderType);
		mrpReport.setPpReference(inputItem.reference);
		mrpReport.setQtyAvailable(BigDecimal.ZERO);
		mrpReport.setMrpSource(inputItem.mrpSource);
		mrpReport.setMrpInputOutputFlag(InputOutputFlag.INPUT_DRIVEN);
		mrpReport.setFixedInput(inputItem.fixedInput);
		mrpReport.setOrderDate(null);
		mrpReport.setMrpArrearsFlag(false);
		mrpReport.setWarehouse(inventoryItem.warehouse);
		mrpReport.setCatalog(inventoryItem.catalog);
		mrpReport.setOriginalDate(inputItem.originalInputDate);
		mrpReport.setOriginalQuantity(inputItem.originalInputQty);
		mrpReport.setSequence(reportSequence++);
		mrpReport.setMrpQuantity(inputItem.fixedInput ? inputItem.originalInputQty : BigDecimal.ZERO);
		mrpReport.setMrpRescheduleFlag(inputItem.fixedInput ? RescheduleFlag.WARNING : RescheduleFlag.CANCEL);

		mrpReportList.add(mrpReport);
	}
	
	private void suggestedPOReport(MRPInventoryItem inventoryItem, MRPInputItem inputItem) {
		Date orderDate = DateTimeUtils.incDay(inputItem.originalInputDate,
				- inventoryItem.route.getQcReceivingDays()
				- inventoryItem.route.getLeadTime()
				- inventoryItem.route.getSafetyDays());
		
		MrpRecommendation recommendItem = mrpRecommendationService.initialize();
		recommendItem.setMrpVersionControl(mrpVersion);
		recommendItem.setRequiredDate(inputItem.inputDate);
		recommendItem.setMrpQuantity(inputItem.originalInputQty);
		recommendItem.setPurchaseLeadTime(inventoryItem.route.getLeadTime());
		recommendItem.setFirmPlanSuggestedOrder(false);
		recommendItem.setMrpOrdered(false);
		recommendItem.setCatalog(inventoryItem.catalog);
		recommendItem.setWarehouse(inventoryItem.warehouse);
		recommendItem.setPpReference(inputItem.reference);
		recommendItem.setManualEntry(false);
		recommendItem.setMrpRescheduleFlag(RescheduleFlag.SUGGESTED);
		recommendItem.setOriginalDate(inputItem.inputDate);
		recommendItem.setBatchDate(inputItem.batchDate);
		recommendItem.setOrderQty(inputItem.purchaseQty);
		recommendItem.setMrpArrearsFlag(recommendItem.getRequiredDate().compareTo(mrpVersion.getRunDate()) < 0);
		recommendItem.setOrderDate(orderDate);
		if (RouteSrcType.TRANSFER.equals(inventoryItem.route.getSrcType())) {
			recommendItem.setMrpSource(MRPSource.TRANSFERS);
			recommendItem.setSourceWarehouse(inventoryItem.route.getSrcWarehouse());
			recommendItem.setPurchaseOrTransfer(RecommendType.TRANSFER);
			recommendItem.setVendor(null);
			recommendItem.setMeasure(inventoryItem.catalog.getMeasure1());
		} else {
			//товар является закупаемым
			recommendItem.setMrpSource(MRPSource.PURCHASES);
			recommendItem.setSourceWarehouse(null);
			recommendItem.setPurchaseOrTransfer(RecommendType.PURCHASE);
			recommendItem.setVendor(inventoryItem.route.getVendor());
			recommendItem.setMeasure(inventoryItem.catalog.getMeasure1());
		}

		mrpRecommendationList.add(recommendItem);
		
		MrpReport mrpReport = mrpReportService.initialize();
		mrpReport.setMrpVersionControl(mrpVersion);
		mrpReport.setRequiredDate(inputItem.originalInputDate);
		mrpReport.setMrpOrderType(MRPOrderType.RESCHEDULE_SUGGESTED);
		mrpReport.setPpReference(inputItem.reference);
		mrpReport.setQtyAvailable(BigDecimal.ZERO);
		mrpReport.setMrpQuantity(inputItem.originalInputQty);
		mrpReport.setMrpInputOutputFlag(InputOutputFlag.INPUT_DRIVEN);
		mrpReport.setFixedInput(false);
		mrpReport.setOrderDate(orderDate);
		mrpReport.setWarehouse(inventoryItem.warehouse);
		mrpReport.setCatalog(inventoryItem.catalog);
		mrpReport.setOriginalDate(inputItem.originalInputDate);
		mrpReport.setOriginalQuantity(inputItem.originalInputQty);
		mrpReport.setSequence(reportSequence++);
		mrpReport.setMrpRescheduleFlag(RescheduleFlag.SUGGESTED);
		mrpReport.setMrpSource(RouteSrcType.TRANSFER.equals(inventoryItem.route.getSrcType())
				? MRPSource.TRANSFERS : MRPSource.PURCHASES);
		mrpReport.setMrpArrearsFlag(inputItem.originalInputDate.compareTo(mrpVersion.getRunDate()) < 0);

		mrpReportList.add(mrpReport);
	}
	
	private void recommendedRescheduling(MRPInventoryItem inventoryItem, MRPInputItem inputItem) {
		MrpRecommendation recommendItem = mrpRecommendationService.initialize();
		recommendItem.setMrpVersionControl(mrpVersion);
		recommendItem.setRequiredDate(inputItem.inputDate);
		recommendItem.setMrpQuantity(inputItem.originalInputQty);
		recommendItem.setFirmPlanSuggestedOrder(false);
		recommendItem.setMrpOrdered(false);
		recommendItem.setCatalog(inventoryItem.catalog);
		recommendItem.setWarehouse(inventoryItem.warehouse);
		recommendItem.setMeasure(inventoryItem.catalog.getMeasure1());
		recommendItem.setPpReference(inputItem.reference);
		recommendItem.setManualEntry(false);
		recommendItem.setMrpRescheduleFlag(RescheduleFlag.RESCHEDULE);
		recommendItem.setMrpSource(inputItem.mrpSource);
		recommendItem.setOriginalDate(inputItem.originalInputDate);
		recommendItem.setMrpArrearsFlag(recommendItem.getRequiredDate().compareTo(mrpVersion.getRunDate()) < 0);
		
		mrpRecommendationList.add(recommendItem);
		
		MrpReport mrpReport = mrpReportService.initialize();
		mrpReport.setMrpVersionControl(mrpVersion);
		mrpReport.setRequiredDate(inputItem.reportDate);
		mrpReport.setMrpOrderType(inputItem.mrpOrderType);
		mrpReport.setPpReference(inputItem.reference);
		mrpReport.setQtyAvailable(BigDecimal.ZERO);
		mrpReport.setMrpQuantity(inputItem.originalInputQty);
		mrpReport.setMrpSource(inputItem.mrpSource);
		mrpReport.setMrpInputOutputFlag(InputOutputFlag.INPUT_DRIVEN);
		mrpReport.setFixedInput(false);
		mrpReport.setWarehouse(inventoryItem.warehouse);
		mrpReport.setCatalog(inventoryItem.catalog);
		mrpReport.setOriginalDate(inputItem.originalInputDate);
		mrpReport.setOriginalQuantity(inputItem.originalInputQty);
		mrpReport.setSequence(reportSequence++);
		mrpReport.setMrpRescheduleFlag(RescheduleFlag.RESCHEDULE);
		mrpReport.setOrderDate(DateTimeUtils.incDay(inputItem.originalInputDate,
				- inventoryItem.route.getQcReceivingDays()
				- inventoryItem.route.getLeadTime()
				- inventoryItem.route.getSafetyDays()));
		mrpReport.setMrpArrearsFlag(inputItem.reportDate.compareTo(mrpVersion.getRunDate()) < 0);

		mrpReportList.add(mrpReport);
	}
	
	private void recordInput(MRPInventoryItem inventoryItem, MRPInputItem inputItem) {
		MrpReport mrpReport = mrpReportService.initialize();
		mrpReport.setMrpVersionControl(mrpVersion);
		mrpReport.setRequiredDate(inputItem.reportDate);
		mrpReport.setMrpOrderType(inputItem.mrpOrderType);
		mrpReport.setPpReference(inputItem.reference);
		mrpReport.setQtyAvailable(BigDecimal.ZERO);
		mrpReport.setMrpQuantity(inputItem.originalInputQty);
		mrpReport.setMrpSource(inputItem.mrpSource);
		mrpReport.setMrpInputOutputFlag(InputOutputFlag.INPUT_DRIVEN);
		mrpReport.setFixedInput(inputItem.fixedInput);
		mrpReport.setWarehouse(inventoryItem.warehouse);
		mrpReport.setCatalog(inventoryItem.catalog);
		mrpReport.setOriginalDate(inputItem.originalInputDate);
		mrpReport.setOriginalQuantity(inputItem.originalInputQty);
		mrpReport.setSequence(reportSequence++);
		mrpReport.setMrpRescheduleFlag(null);
		if (MRPOrderType.ACTUAL.equals(mrpReport.getMrpOrderType())
				&& MRPSource.INVENTORY.equals(mrpReport.getMrpSource()))
			mrpReport.setOrderDate(null);
		else {
			if (MRPOrderType.ACTUAL.equals(mrpReport.getMrpOrderType()))
				mrpReport.setOrderDate(inputItem.orderDate);
			else {
				if (RouteSrcType.MANUFACTURE.equals(inventoryItem.route.getSrcType()))
					mrpReport.setOrderDate(inputItem.reportDate);
				else
					mrpReport.setOrderDate(DateTimeUtils.incDay(inputItem.reportDate,
							- inventoryItem.route.getQcReceivingDays()
							- inventoryItem.route.getLeadTime()
							- inventoryItem.route.getSafetyDays()));
			}
		}
		mrpReport.setMrpArrearsFlag(inputItem.reportDate.compareTo(mrpVersion.getRunDate()) < 0);
		
		mrpReportList.add(mrpReport);
	}
	
	private BigDecimal updateInputQtyProcessed(BigDecimal qtyProcessed) {
		//QtyProcessed := FInputVT.FieldValues['ORIGINAL_INPUT_QTY'] - FInputVT.FieldValues['INPUT_QTY'] + FInputVT.FieldValues['EXPIRED_QTY'];
		//if CompareFloatWithZero(QtyProcessed, 0) > 0 then ;
		//UpdateInputQtyProcessed(QtyProcessed); { пусть пока будет просто заглушкой с пустым телом }
		//TODO
		return qtyProcessed;
	}
	
	private void reportOnInputs(MRPInventoryItem inventoryItem, BigDecimal maxCancellations) {
		BigDecimal qtyProcessed = BigDecimal.ZERO;
		List<MRPInputItem> list = virtualInputs.listSortedByInputDate();
		//descending
		for (int i = list.size() - 1; i >= 0; i--) {
			MRPInputItem item = list.get(i);
			if (item.ignoreForReport)
				continue;
			
			if (MathUtils.compareToZero(item.inputQty) > 0
					&& item.inputQty.compareTo(item.originalInputQty) == 0
					&& (item.suggestedPO || MRPSource.PURCHASES.equals(item.mrpSource)
							|| MRPSource.TRANSFERS.equals(item.mrpSource) && MRPOrderType.ACTUAL.equals(item.mrpOrderType))) {
				if (maxCancellations.compareTo(item.inputQty) >= 0) {
					recommendedCancellationsOrWarnings(inventoryItem, item);
					maxCancellations = maxCancellations.subtract(item.inputQty );
				} else
					maxCancellations = BigDecimal.ZERO;
			} else if (item.suggestedPO && !RouteSrcType.MANUFACTURE.equals(inventoryItem.route.getSrcType())) {
				//Записываем предложения на закупку				
				suggestedPOReport(inventoryItem, item);
			} else if (item.originalInputDate != null && item.originalInputDate.compareTo(item.inputDate) != 0
					&& item.fixedInput && !RouteSrcType.MANUFACTURE.equals(inventoryItem.route.getSrcType())) {
				//Записываем рекомендации на перепланирование
				recommendedRescheduling(inventoryItem, item);
			} else
				recordInput(inventoryItem, item);
			
			qtyProcessed = updateInputQtyProcessed(qtyProcessed);
		}
	}
	
	private void reportExpiredQtys(MRPInventoryItem inventoryItem) {
		//Записываем информацию о просроченных количествах
		for (MRPInputItem item : virtualInputs.list()) {
			if (!item.ignoreForReport && MathUtils.compareToZero(item.expiredQty) > 0) {
				MrpReport mrpReport = mrpReportService.initialize();
				
				mrpReport.setMrpVersionControl(mrpVersion);
				if (item.batchDate != null)
					mrpReport.setRequiredDate(item.batchDate);
				mrpReport.setMrpOrderType(item.mrpOrderType);
				mrpReport.setPpReference(item.reference);
				mrpReport.setQtyAvailable(BigDecimal.ZERO);
				mrpReport.setMrpQuantity(item.expiredQty.negate());
				mrpReport.setMrpSource(item.mrpSource);
				mrpReport.setMrpInputOutputFlag(InputOutputFlag.OUTPUT_DRIVEN);
				mrpReport.setFixedInput(false);
				mrpReport.setOrderDate(null);
				mrpReport.setMrpArrearsFlag(false);
				mrpReport.setWarehouse(inventoryItem.warehouse);
				mrpReport.setCatalog(inventoryItem.catalog);
				mrpReport.setOriginalDate(item.reportDate);
				mrpReport.setOriginalQuantity(item.originalInputQty);
				mrpReport.setSequence(reportSequence++);
				mrpReport.setMrpRescheduleFlag(RescheduleFlag.UNUSED_EXPIRED);
				
				mrpReportList.add(mrpReport);
			}
		}
	}
	
	private void recordOutputRecords(MRPInventoryItem inventoryItem) {
		for (MRPOutputItem item : virtualOutputs.list()) {
			MrpReport mrpReport = mrpReportService.initialize();
			
			mrpReport.setMrpVersionControl(mrpVersion);
			mrpReport.setRequiredDate(item.reportDate);
			mrpReport.setMrpOrderType(item.mrpOrderType);
			mrpReport.setPpReference(item.reference);
			mrpReport.setQtyAvailable(BigDecimal.ZERO);
			mrpReport.setMrpQuantity(item.outputQty.negate());
			mrpReport.setMrpSource(item.mrpSource);
			mrpReport.setMrpInputOutputFlag(InputOutputFlag.OUTPUT_DRIVEN);
			mrpReport.setFixedInput(false);
			mrpReport.setOrderDate(null);
			mrpReport.setWarehouse(inventoryItem.warehouse);
			mrpReport.setCatalog(inventoryItem.catalog);
			mrpReport.setOriginalDate(item.reportDate);
			mrpReport.setOriginalQuantity(item.outputQty.negate());
			mrpReport.setSequence(reportSequence++);
			mrpReport.setMrpRescheduleFlag(null);
			mrpReport.setMrpArrearsFlag(item.reportDate.compareTo(mrpVersion.getRunDate()) < 0);
			
			mrpReportList.add(mrpReport);
		}
	}
	
	private void processPart(MRPInventoryItem inventoryItem) {
		loadVirtualTables(inventoryItem);
		Date day = DateTimeUtils.getDayStart(mrpStartDate), endDay = DateTimeUtils.getDayStart(mrpEndDate);
		BigDecimal qtyInput = BigDecimal.ZERO, qtyOutput  = BigDecimal.ZERO, available = BigDecimal.ZERO;
		BigDecimal safetyLevel = inventoryItem.catalogWarehouse.getSafetyLevel();
		if (safetyLevel == null)
			safetyLevel = BigDecimal.ZERO;
		
		while (day.compareTo(endDay) <= 0) {
			qtyInput = getTotalInputs(day);
			qtyOutput = getTotalOuputs(day);
			available = qtyInput.subtract(qtyOutput);
				
			if (available.compareTo(inventoryItem.catalogWarehouse.getSafetyLevel()) < 0
					&& !RouteSrcType.MANUFACTURE.equals(inventoryItem.route.getSrcType()))
				increaseAvailable(inventoryItem, day, available); //создание предложений на закупку в таблице в памяти
			
			if (MathUtils.compareToZero(qtyOutput) > 0)
				updateInputs(inventoryItem, day, qtyOutput);
			
			updateExpiredInputs(day);
			day = DateTimeUtils.incDay(day, 1);
		}

		//Get the total of all input quantities available before today that have yet to be consumed by outputs
		qtyInput = getTotalInputs(day);
		//Get the total of all output quantities required for today
		qtyOutput = getTotalOuputs(day);
		available = qtyInput.subtract(qtyOutput);
		
		BigDecimal maxCancellations = available.subtract(safetyLevel);
		if (MathUtils.compareToZero(maxCancellations) < 0)
			maxCancellations = BigDecimal.ZERO;
		
		/*The above mechanism merely determines where and when
		  suggested po's should be raised or where orders should be
		  rescheduled.
		  The results of the above analysis will now be included
		  on the standard MRP Reports */
		reportOnInputs(inventoryItem, maxCancellations);
		reportExpiredQtys(inventoryItem);
		recordOutputRecords(inventoryItem);
	}

	private void createSalesShortage(MRPInventoryItem inventoryItem, BigDecimal qtyAvailable, Date reqDate) {
		if (MathUtils.compareToZero(qtyAvailable) < 0 && RouteSrcType.MANUFACTURE.equals(inventoryItem.route.getSrcType())) {
			MrpShortage shortage = mrpShortageService.initialize();
			shortage.setMrpVersionControl(mrpVersion);
			shortage.setRequiredDate(reqDate);
			shortage.setShortageQty(qtyAvailable.negate());
			shortage.setWarehouseId(inventoryItem.warehouse.getId());
			shortage.setCatalogId(inventoryItem.catalog.getId());
			
			mrpShortageList.add(shortage);
		}
	}
	
	private void availableSetup(MRPInventoryItem inventoryItem) {
		List<MrpReport> list = new ArrayList<MrpReport>(mrpReportList);
		Collections.sort(list, new Comparator<MrpReport>() {

			public int compare(MrpReport o1, MrpReport o2) {
				int result = o1.getRequiredDate().compareTo(o2.getRequiredDate());
				if (result == 0) {
					if (o1.getMrpInputOutputFlag().ordinal() < o2.getMrpInputOutputFlag().ordinal())
						result = -1;
					else if (o1.getMrpInputOutputFlag().ordinal() > o2.getMrpInputOutputFlag().ordinal())
						result = 1;
					else {
						if (o1.getSequence() < o2.getSequence())
							result = -1;
						else if (o1.getSequence() > o2.getSequence())
							result = 1;
					}
				}
				return result;
			}
			
		});
		BigDecimal qtyAvailable = BigDecimal.ZERO;
		Date reqDate = DateTimeUtils.ZERO_DATE;
		for (MrpReport mrpReport : list) {
			if (mrpReport.getCatalog().getId() == inventoryItem.catalog.getId()
					&& mrpReport.getWarehouse().getId() == inventoryItem.warehouse.getId()) {
				qtyAvailable = qtyAvailable.add(mrpReport.getMrpQuantity());
				mrpReport.setQtyAvailable(qtyAvailable);
				if (reqDate.compareTo(mrpReport.getRequiredDate()) != 0) {
					reqDate = mrpReport.getRequiredDate();
					createSalesShortage(inventoryItem, qtyAvailable, reqDate);
				}
			}
		}
	}

	private void persistGeneratedResults() {
		for (MrpInputs inputs : mrpInputsList)
			mrpInputsService.create(inputs);
		
		for (MrpOutputs outputs : mrpOutputsList)
			mrpOutputsService.create(outputs);
		
		for (MrpReport mrpReport : mrpReportList)
			mrpReportService.create(mrpReport);
		
		for (MrpRecommendation mrpRecommendation : mrpRecommendationList)
			mrpRecommendationService.create(mrpRecommendation);
		
		for (MrpShortage shortage : mrpShortageList)
			mrpShortageService.create(shortage);
	}
	
	public void internalGenerateMrp(MrpVersionControl mrpVersion) {
		this.mrpVersion = mrpVersion;
		transferOrderLastNumber = 0;
		purchaseOrderLastNumber = 0;
		reportSequence = 1;
		mrpStartDate = new Date(mrpVersion.getMrpStartDate().getTime());
		mrpEndDate  = new Date(mrpVersion.getMrpEndDate().getTime());
		
		clearMRPDetails(mrpVersion);
		
		processMPSVersions();
		
		if (mrpVersion.getMrpSoFlag())
			processLiveSalesOrders();
		
		if (mrpVersion.getMrpSfFlag())
			processSalesForecasts();
		
		if (mrpVersion.getMrpPfFlag())
			processPurchaseForecasts();
		
		if (mrpVersion.getMrpPoFlag())
			processLivePurchaseOrders();
		
		if (mrpVersion.getMrpJobFlag()) {
			processLiveJobOutputs();
			processLiveJobRequirements();
		}
		
		if (mrpVersion.getMrpFirmPlannedOrdersFlag())
			processFirmPlannedWarehouseTransfers();
		
		if (mrpVersion.getMrpQohFlag())
			processCurrentQtyOnHand();
		
		mrpBuild();
		
		//изменяем данные на заголовке
		mrpVersion.setMrpVersion(mrpVersion.getMrpVersion() != null ? mrpVersion.getMrpVersion() + 1 : 1);
		mrpVersionService.store(mrpVersion);
		
		persistGeneratedResults();
	}
	
	/* (non-Javadoc)
	 * @see com.mg.merp.planning.MRPProcessorServiceLocal#generateMrp(int)
	 */
	public void generateMrp(int mrpVersionId) {
		ServerUtils.setTransactionTimeout(86400);//неизвестно сколько будет идти расчет MRP, ставит таймаут на сутки
		ormTemplate = OrmTemplate.getInstance();
		persistentManager = ServerUtils.getPersistentManager();
		mrpVersionService = (MRPVersionControlServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(MRPVersionControlServiceLocal.SERVICE_NAME);
		bomService = (BOMServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(BOMServiceLocal.SERVICE_NAME);
		mrpInputsService = (MRPInputsServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(MRPInputsServiceLocal.SERVICE_NAME);
		mrpOutputsService = (MRPOutputsServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(MRPOutputsServiceLocal.SERVICE_NAME);
		measureConvService = (MeasureConversionServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(MeasureConversionServiceLocal.LOCAL_SERVICE_NAME);
		mrpReportService = (MRPReportServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(MRPReportServiceLocal.SERVICE_NAME);
		mrpRecommendationService = (MRPRecommendationServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(MRPRecommendationServiceLocal.SERVICE_NAME);
		mrpShortageService = (MRPShortageServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(MRPShortageServiceLocal.SERVICE_NAME);
		mrpInputsList = new ArrayList<MrpInputs>();
		mrpOutputsList = new ArrayList<MrpOutputs>();
		mrpReportList = new ArrayList<MrpReport>();
		mrpShortageList = new ArrayList<MrpShortage>();
		mrpRecommendationList = new ArrayList<MrpRecommendation>();
		mrpInventoryList = new MRPInventoryList();
		
		internalGenerateMrp(mrpVersionService.load(mrpVersionId));
	}
}