/*
 * ManufactureProcessorServiceBean.java
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

package com.mg.merp.manufacture.support;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateful;

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.orm.Criterion;
import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.orm.ResultTransformer;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.MathUtils;
import com.mg.merp.docflow.DocFlowManager;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;
import com.mg.merp.docflow.DocumentSpecItem;
import com.mg.merp.docflow.support.DocFlowHelper;
import com.mg.merp.document.CreateSpecificationInfo;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.support.DocumentUtils;
import com.mg.merp.manufacture.BOMMaterialCostCategoryEmptyException;
import com.mg.merp.manufacture.BOMNotFoundException;
import com.mg.merp.manufacture.InputLaborHeadServiceLocal;
import com.mg.merp.manufacture.InputMachineHeadServiceLocal;
import com.mg.merp.manufacture.InputMaterialHeadServiceLocal;
import com.mg.merp.manufacture.InvalidJobStatusException;
import com.mg.merp.manufacture.JobRouteIsNotControlPointException;
import com.mg.merp.manufacture.JobRouteNotFoundException;
import com.mg.merp.manufacture.JobServiceLocal;
import com.mg.merp.manufacture.ManufactureProcessorServiceLocal;
import com.mg.merp.manufacture.ScrapLaborHeadServiceLocal;
import com.mg.merp.manufacture.ScrapMachineHeadServiceLocal;
import com.mg.merp.manufacture.ScrapMaterialHeadServiceLocal;
import com.mg.merp.manufacture.ScrapProductHeadServiceLocal;
import com.mg.merp.manufacture.TransactionServiceLocal;
import com.mg.merp.manufacture.VarianceDocumentHeadServiceLocal;
import com.mg.merp.manufacture.model.InputDocumentHead;
import com.mg.merp.manufacture.model.InputDocumentSpec;
import com.mg.merp.manufacture.model.Job;
import com.mg.merp.manufacture.model.JobLabor;
import com.mg.merp.manufacture.model.JobMachine;
import com.mg.merp.manufacture.model.JobMaterial;
import com.mg.merp.manufacture.model.JobRoute;
import com.mg.merp.manufacture.model.JobRouteResource;
import com.mg.merp.manufacture.model.JobStatus;
import com.mg.merp.manufacture.model.OutputProductHead;
import com.mg.merp.manufacture.model.ScrapDocumentHead;
import com.mg.merp.manufacture.model.ScrapDocumentSpec;
import com.mg.merp.manufacture.model.Transaction;
import com.mg.merp.manufacture.model.VarianceDocumentHead;
import com.mg.merp.manufacture.model.VarianceType;
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
import com.mg.merp.mfreference.model.WorkCenter;
import com.mg.merp.mfreference.support.ConfigurationHelper;
import com.mg.merp.mfreference.support.MfUtils;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Currency;
import com.mg.merp.reference.model.Measure;

/**
 * Бизнес-компонет "Процессор производства"
 * 
 * @author Oleg V. Safonov
 * @version $Id: ManufactureProcessorServiceBean.java,v 1.2 2007/08/06 12:44:54 safonov Exp $
 */
@Stateful(name="merp/manufacture/ManufactureProcessorService")
public class ManufactureProcessorServiceBean extends com.mg.framework.generic.AbstractPOJOBusinessObjectStatelessServiceBean
		implements ManufactureProcessorServiceLocal {
	private OrmTemplate ormTemplate = OrmTemplate.getInstance();
	private VarianceVirtualTable varianceVT;

	class VarianceItem {
		private Catalog catalog;
		private CostCategories costCategory;
		private BigDecimal actQuan;
		private BigDecimal stdQuan;
		private BigDecimal actCost;
		private BigDecimal stdCost;
		private Measure measure;
		private int stdCount;
		private int actCount;
	}
	
	class VarianceVirtualTable {
		List<VarianceItem> list = new ArrayList<VarianceItem>();
		
		private void update(Catalog catalog, CostCategories costCategory, BigDecimal actQuan, BigDecimal stdQuan,
				BigDecimal actCost, BigDecimal stdCost, boolean actual, Measure measure) {
			VarianceItem item = find(catalog, costCategory);
			if (item != null) {
				if (actual) {
					item.actCost = item.actCost.multiply(item.actQuan).add(actCost.multiply(actQuan))
							.divide(item.actQuan.add(actQuan));
					item.actQuan = item.actQuan.add(actQuan);
					item.actCount++;
				} else {
					item.stdCost = item.stdCost.multiply(item.stdQuan).add(stdCost.multiply(stdQuan))
							.divide(item.stdQuan.add(stdQuan));
					item.stdQuan = item.stdQuan.add(stdQuan);
					item.stdCount++;
				}
			} else {
				item = new VarianceItem();
				item.catalog = catalog;
				item.costCategory = costCategory;
				item.actQuan = actQuan;
				item.stdQuan = stdQuan;
				item.actCost = actCost;
				item.stdCost = stdCost;
				item.measure = measure;
				if (actual) {
					item.actCount = 1;
					item.stdCount = 0;
				} else {
					item.actCount = 0;
					item.stdCount = 1;					
				}
				
				list.add(item);
			}
		}
		
		private VarianceItem find(Catalog catalog, CostCategories costCategory) {
			for (VarianceItem item : list)
				if (item.catalog.getId() == catalog.getId() && item.costCategory.getId() == costCategory.getId())
					return item;
			return null;
		}
		
		private void clear() {
			list.clear();
		}
		
		private List<VarianceItem> list() {
			return list;
		}
		
	}
	
	private BigDecimal getResourceCost(CostDetail costDetail) {
		List<CostDetailLineItem> lines = ((CostDetailLineServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CostDetailLineServiceLocal.SERVICE_NAME)).calculateCost(costDetail, false);
		assert lines.isEmpty();
		return lines.get(0).getCost();
	}
	
	private void processBOMMaterial(BomRoute bomRoute, Date actualityDate, BigDecimal productQty) {
		List<BomMaterial> materials = ormTemplate.findByCriteria(OrmTemplate.createCriteria(BomMaterial.class)
				.add(Restrictions.eq("BomRoute", bomRoute))
				.add(Restrictions.le("EffOnDate", actualityDate))
				.add(Restrictions.ge("EffOffDate", actualityDate)));
		for (BomMaterial material : materials) {
			if (getLogger().isDebugEnabled())
				getLogger().debug("process BOM material: " + material.getId());
			BigDecimal mtlQty = MfUtils.calculateBOMMaterialQuan(material, actualityDate, bomRoute.getBom().getPlanningLotQty());
			BigDecimal quan = mtlQty.multiply(productQty);
			BigDecimal cost = getResourceCost(material.getStandartCostDetail()).divide(mtlQty);
			
			if (material.getMtlCostCategory() == null)
				throw new BOMMaterialCostCategoryEmptyException(material);
			
			varianceVT.update(material.getCatalog(), material.getMtlCostCategory(), BigDecimal.ZERO, quan,
					BigDecimal.ZERO, cost, false, material.getMeasure());
		}
	}
	
	private void processBOMLabor(BomRoute bomRoute, Date actualityDate, BigDecimal productQty) {
		List<BomLabor> labors = ormTemplate.findByCriteria(OrmTemplate.createCriteria(BomLabor.class)
				.add(Restrictions.eq("BomRoute", bomRoute))
				.add(Restrictions.le("EffOnDate", actualityDate))
				.add(Restrictions.ge("EffOffDate", actualityDate)));
		for (BomLabor labor : labors) {
			if (getLogger().isDebugEnabled())
				getLogger().debug("process BOM labor: " + labor.getId());
			BigDecimal laborTime = BigDecimal.ZERO;
			BigDecimal laborCost = getResourceCost(labor.getStandartCostDetail());
			switch (labor.getLaborClass().getTimeRateFlag()) {
			case TIME:
			case RATE:
				//fixed defect #2484
				if (MathUtils.compareToZero(productQty) != 0) {
					laborTime = MfUtils.tickToTime(labor.getRunTicksLbr(), MfUtils.HOUR).multiply(productQty);
					laborCost = laborCost.multiply(productQty).divide(laborTime);
				} else {
					laborTime = BigDecimal.ZERO;
					laborCost = BigDecimal.ZERO;
				}
				break;
			case FIXED:
				laborTime = MfUtils.tickToTime(labor.getRunTicksLbr(), MfUtils.HOUR);
				break;
			}
			
			if (labor.getLaborClass().getLbrCostCategory() == null)
				throw new BusinessException("Не установлена категория затрат для класса рабочей силы"); //TODO
			
			varianceVT.update(ConfigurationHelper.getConfiguration().getLaborTime(), labor.getLaborClass().getLbrCostCategory(),
					BigDecimal.ZERO, laborTime, BigDecimal.ZERO, laborCost, false, null);
		}
	}
	
	private void processBOMMachine(BomRoute bomRoute, Date actualityDate, BigDecimal productQty) {
		List<BomMachine> machines = ormTemplate.findByCriteria(OrmTemplate.createCriteria(BomMachine.class)
				.add(Restrictions.eq("BomRoute", bomRoute))
				.add(Restrictions.le("EffOnDate", actualityDate))
				.add(Restrictions.ge("EffOffDate", actualityDate)));
		for (BomMachine machine : machines) {
			if (getLogger().isDebugEnabled())
				getLogger().debug("process BOM machine: " + machine.getId());
			BigDecimal machineTime = BigDecimal.ZERO;
			BigDecimal machineCost = getResourceCost(machine.getStandartCostDetail());
			switch (machine.getTimeRateFlag()) {
			case TIME:
			case RATE:
				//fixed defect #2484
				if (MathUtils.compareToZero(productQty) != 0) {
					machineTime = MfUtils.tickToTime(machine.getRunTicksMch(), MfUtils.HOUR).multiply(productQty);
					machineCost = machineCost.multiply(productQty).divide(machineTime);
				} else {
					machineTime = BigDecimal.ZERO;
					machineCost = BigDecimal.ZERO;
				}
				break;
			case FIXED:
				machineTime = MfUtils.tickToTime(machine.getRunTicksMch(), MfUtils.HOUR);
				break;
			}
			
			if (machine.getMchCostCategory() == null)
				throw new BusinessException("Не установлена категория затрат для оборудования в составе изделия"); //TODO
			
			varianceVT.update(ConfigurationHelper.getConfiguration().getMachineTime(), machine.getMchCostCategory(), 
					BigDecimal.ZERO, machineTime, BigDecimal.ZERO, machineCost, false, null);
		}
	}
	
	private void processBOMRoute(Bom bom, Date actualityDate, BigDecimal productQty) {
		List<BomRoute> routes = ormTemplate.findByCriteria(OrmTemplate.createCriteria(BomRoute.class)
				.add(Restrictions.eq("Bom", bom))
				.add(Restrictions.le("EffOnDate", actualityDate))
				.add(Restrictions.ge("EffOffDate", actualityDate)));
		for (BomRoute route : routes) {
			if (getLogger().isDebugEnabled())
				getLogger().debug("process BOM route: " + route.getId());
			processBOMMaterial(route, actualityDate, productQty);
			processBOMLabor(route, actualityDate, productQty);
			processBOMMachine(route, actualityDate, productQty);
		}
	}
	
	private void processMfTransaction(Job job) {
		List<Transaction> transactions = ormTemplate.findByCriteria(OrmTemplate.createCriteria(Transaction.class)
				.add(Restrictions.eq("Job", job)));
		for (Transaction tran : transactions) {
			if (tran.getJobRouteResource() instanceof JobMaterial)
				processMaterialMfTransaction(tran);
			else
				processLaborAndMachineMfTransaction(tran);
		}
	}
	
	private void processMaterialMfTransaction(Transaction transaction) {
		BigDecimal cost = BigDecimal.ZERO;
		JobMaterial jobMaterial = (JobMaterial) transaction.getJobRouteResource();
		if (MathUtils.compareToZero(transaction.getQuantity()) != 0)
			cost = transaction.getSumma().divide(transaction.getQuantity());
		
		varianceVT.update(jobMaterial.getCatalog(), transaction.getCostCategory(), transaction.getQuantity(), BigDecimal.ZERO,
				cost, BigDecimal.ZERO, true, jobMaterial.getMeasure());
	}
	
	private void processLaborAndMachineMfTransaction(Transaction transaction) {
		BigDecimal cost = BigDecimal.ZERO;
		if (MathUtils.compareToZero(transaction.getQuantity()) != 0)
			cost = transaction.getSumma().divide(transaction.getQuantity());

		varianceVT.update(transaction.getDocSpec().getCatalog(), transaction.getCostCategory(), transaction.getQuantity(), BigDecimal.ZERO,
				cost, BigDecimal.ZERO, true, transaction.getDocSpec().getMeasure1());
	}
	
	private CreateSpecificationInfo[] processVariance() {
		List<CreateSpecificationInfo> result = new ArrayList<CreateSpecificationInfo>();
		Catalog laborCatalog = ConfigurationHelper.getConfiguration().getLaborTime();
		Catalog machineCatalog = ConfigurationHelper.getConfiguration().getMachineTime();
		for (VarianceItem item : varianceVT.list()) {
			if (MathUtils.compareToZero(item.stdCost) != 0) {
				BigDecimal quantity = item.actQuan.subtract(item.stdQuan);
				VarianceType varianceType = null;
				if (item.catalog.getId() == laborCatalog.getId())
					varianceType = VarianceType.LABOR_USAGE_VARIANCE;
				else if (item.catalog.getId() == machineCatalog.getId())
					varianceType = VarianceType.MACHINE_USAGE_VARIANCE;
				else
					varianceType = VarianceType.MATERIAL_USAGE_VARIANCE;
				
				result.add(new CreateVarianceSpecificationInfoImpl(
						item.catalog.getId(),
						null,
						item.stdCost,
						quantity,
						null,
						item.measure,
						null,
						item.costCategory,
						varianceType));
			}
			
			BigDecimal quantity = item.actQuan;
			if (MathUtils.compareToZero(quantity) == 0)
				quantity = BigDecimal.ZERO;
			BigDecimal cost = item.actCost.subtract(item.stdCost);
			if (MathUtils.compareToZero(cost) != 0) {
				VarianceType varianceType = null;
				if (item.catalog.getId() == laborCatalog.getId())
					varianceType = VarianceType.LABOR_RATE_VARIANCE;
				else if (item.catalog.getId() == machineCatalog.getId())
					varianceType = VarianceType.MACHINE_RATE_VARIANCE;
				else
					varianceType = VarianceType.MATERIAL_COST_VARIANCE;
				
				result.add(new CreateVarianceSpecificationInfoImpl(
						item.catalog.getId(),
						null,
						cost,
						quantity,
						null,
						item.measure,
						null,
						item.costCategory,
						varianceType));
			}
		}
		return result.toArray(new CreateSpecificationInfo[result.size()]);
	}
	
//	@SuppressWarnings("unchecked")
//	private void processMaterialMfTransaction(Job job) {
//		List<Object[]> items = ormTemplate.findByNamedQueryAndNamedParam("Manufacture.ManufactureProcessor.loadMaterialTransaction", "job", job);
//		for (Object[] item : items) {
//			Transaction tran = (Transaction) item[0];
//			Catalog catalog = (Catalog) item[1];
//			Measure measure = (Measure) item[2];
//			BigDecimal cost = BigDecimal.ZERO;
//			if (MathUtils.compareToZero(tran.getQuantity()) != 0)
//				cost = tran.getSumma().divide(tran.getQuantity());
//			
//			varianceVT.update(catalog, tran.getCostCategory(), tran.getQuantity(), BigDecimal.ZERO,
//					cost, BigDecimal.ZERO, true, measure);
//		}
//	}
//	
//	private void processLaborAndMachineMfTransaction(Job job) {
//		
//	}
	
	/* (non-Javadoc)
	 * @see com.mg.merp.manufacture.ManufactureProcessorServiceLocal#createVarianceDocument(com.mg.merp.manufacture.model.Job)
	 */
	@PermitAll
	public void createVarianceDocument(Job job) {
		Bom standartBom = ((BOMServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(BOMServiceLocal.SERVICE_NAME)).findStandartBOM(job.getCatalog().getId());
		if (standartBom == null)
			throw new BOMNotFoundException(job.getCatalog());
		
		varianceVT = new VarianceVirtualTable();
		try {
			//standart cost
			processBOMRoute(standartBom, DateTimeUtils.getDayStart(job.getEndDate()), job.getQtyComplete());
			
			//actual cost
			processMfTransaction(job);
//			processMaterialMfTransaction(job);
//			processLaborAndMachineMfTransaction(job);
			
			CreateSpecificationInfo[] docSpecs = processVariance();
			if (docSpecs.length != 0) {
				VarianceDocumentHeadServiceLocal docService = (VarianceDocumentHeadServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(VarianceDocumentHeadServiceLocal.SERVICE_NAME);
				VarianceDocumentHead docHead = (VarianceDocumentHead) docService.createByPattern(ConfigurationHelper.getConfiguration().getVarianceDocumentModel(), null);
				docHead.setCurrency(ConfigurationHelper.getConfiguration().getBaseCurrency());
				docHead.setCurrencyRateAuthority(ConfigurationHelper.getConfiguration().getCurrencyRateAuthority());
				docHead.setCurrencyRateType(ConfigurationHelper.getConfiguration().getCurrencyRateType());
				docHead.setCurCource(BigDecimal.ONE);
				docService.create(docHead);
				
				docService.getSpecificationService().bulkCreate(docHead, docSpecs);
			}
		} finally {
			varianceVT.clear();
			varianceVT = null;
		}
	}

	private void checkJobStatus(Job job, EnumSet<JobStatus> jobStatus) throws InvalidJobStatusException {
		((JobServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(JobServiceLocal.SERVICE_NAME))
				.checkStatus(job, jobStatus);
	}
	
	private List<JobRoute> loadJobRoute(Job job) {
		return ormTemplate.findByCriteria(OrmTemplate.createCriteria(JobRoute.class)
				.add(Restrictions.eq("Job", job))
				.addOrder(Order.desc("OperNum")));
	}

	private int tryCheckJobRoute(List<JobRoute> routes, JobRoute jobRoute) {
		int result = -1;
		for (int i = 0; i < routes.size() - 1; i++)
			if (routes.get(i).getId() == jobRoute.getId()) {
				result = i;
				break;
			}
		return result;
	}

	private int checkJobRoute(List<JobRoute> routes, JobRoute jobRoute) {
		int result = tryCheckJobRoute(routes, jobRoute);
		if (result == -1)
			throw new JobRouteNotFoundException();
		if (!jobRoute.getControlPointFlag())
			throw new JobRouteIsNotControlPointException();
		return result;
	}
	
	private int getPrevControlPoint(List<JobRoute> routes, int currentJobOperIndex) {
		int result = -1;
		for (int i = currentJobOperIndex + 1; i < routes.size() - 1; i++)
			if (routes.get(i).getControlPointFlag()) {
				result = i;
				break;
			}
		return result;
	}
	
	private boolean isFinalRoute(List<JobRoute> routes, JobRoute jobRoute) {
		return routes.get(0).getId() == jobRoute.getId(); //проверим нулевой, т.к. выборка была desc
	}
	
	private void internalUpdateJobFromOutputDocument(Job job, JobRoute jobRoute, BigDecimal quantity, boolean isOutputDocument) {
		checkJobStatus(job, EnumSet.of(JobStatus.RUNNING));
		List<JobRoute> routes = loadJobRoute(job);
		int currentJobOperIndex = checkJobRoute(routes, jobRoute);
		int prevControlPoint = getPrevControlPoint(routes, currentJobOperIndex);
		
		for (int i = currentJobOperIndex; i <= prevControlPoint; i++) {
			JobRoute route = routes.get(i);
			if (isOutputDocument)
				route.setQtyComplete(route.getQtyComplete().add(quantity));
			else
				route.setQtyScrapped(route.getQtyScrapped().add(quantity));
		}
		
		if (isFinalRoute(routes, jobRoute)) {
			if (isOutputDocument)
				job.setQtyComplete(job.getQtyComplete().add(quantity));
			else
				job.setQtyScrapped(job.getQtyScrapped().add(quantity));
		}
	}
	
	private BigDecimal createInputLaborDocument(JobRoute jobRoute, BigDecimal productQuan, OutputProductHead outputDocHead) {
		List<CreateManufactureSpecificationInfoImpl> docSpecs = new ArrayList<CreateManufactureSpecificationInfoImpl>();
		List<JobLabor> labors = ormTemplate.findByCriteria(OrmTemplate.createCriteria(JobLabor.class)
				.add(Restrictions.eq("Oper", jobRoute)));
		
		BigDecimal t1 = BigDecimal.ZERO, t2 = BigDecimal.ZERO;
		for (JobLabor labor : labors) {
			switch (labor.getTimeRateFlag()) {
			case TIME:
			case RATE:
				t1 = t1.add(productQuan.multiply(new BigDecimal(labor.getRunTicksLbr())));
				break;
			case FIXED:
				t2 = t2.add(new BigDecimal(labor.getRunTicksLbr()));
				break;
			}
			BigDecimal laborTime = BigDecimal.ZERO, laborCost = BigDecimal.ZERO;
			if (labor.isLbrBackflushFlag()) {
				switch (labor.getTimeRateFlag()) {
				case TIME:
				case RATE:
					laborTime = MfUtils.tickToTime(labor.getRunTicksLbr(), MfUtils.HOUR).multiply(productQuan);
					break;
				case FIXED:
					laborTime = MfUtils.tickToTime(labor.getRunTicksLbr(), MfUtils.HOUR);
					break;
				}
				laborCost = laborTime.multiply(labor.getLbrRate());
				docSpecs.add(new CreateManufactureSpecificationInfoImpl(
						ConfigurationHelper.getConfiguration().getLaborTime().getId(),
						null,
						laborCost.divide(laborTime),
						laborTime,
						null,
						labor,
						labor.getLbrCostCategory(),
						null,
						null));
			}
			//overhead
			if (labor.isLbrOhBackflushFlag()) {
				BigDecimal price = null;
				switch (labor.getLbrOhAllocationFlag()) {
				case TIME:
					price = laborTime.multiply(labor.getLbrOhRate());
					break;
				case UNIT:
					price = productQuan.multiply(labor.getLbrOhRate());
					break;
				case COST:
					price = laborCost.multiply(labor.getLbrOhRatio());
					break;
				case FIXED:
					price = labor.getLbrOhRate();
					break;
				}
				docSpecs.add(new CreateManufactureSpecificationInfoImpl(
						ConfigurationHelper.getConfiguration().getLaborOverhead().getId(),
						null,
						price,
						BigDecimal.ONE,
						null,
						labor,
						labor.getLbrOhCostCategory(),
						null,
						null));
			}
		}
		
		if (!docSpecs.isEmpty()) {
			//create document
			InputLaborHeadServiceLocal docService = (InputLaborHeadServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(InputLaborHeadServiceLocal.SERVICE_NAME);
			InputDocumentHead docHead = (InputDocumentHead) docService.createByPattern(ConfigurationHelper.getConfiguration().getInputLaborModelBf(), null);
			docHead.setDocDate(outputDocHead.getDocDate());
			docHead.setBackFlushFlag(true);
			docHead.setOutputDocHead(outputDocHead);
			docHead.setWC(outputDocHead.getWC());
			docHead.setJob(outputDocHead.getJob());
			docHead.setOper(jobRoute);
			
			docService.create(docHead);
			
			docService.getSpecificationService().bulkCreate(docHead, docSpecs.toArray(new CreateManufactureSpecificationInfoImpl[docSpecs.size()]));
			
			//implicitly execute document process for document
			DocFlowHelper.execute(docHead.getId(), DocFlowManager.DOCFLOW_OWNER, true);
		}
		
		return t1.add(t2);
	}

	private void createInputMachineDocument(JobRoute jobRoute, BigDecimal productQuan, BigDecimal timeOper, OutputProductHead outputDocHead) {
		List<CreateManufactureSpecificationInfoImpl> docSpecs = new ArrayList<CreateManufactureSpecificationInfoImpl>();
		List<JobMachine> machines = ormTemplate.findByCriteria(OrmTemplate.createCriteria(JobMachine.class)
				.add(Restrictions.eq("Oper", jobRoute)));
		
		for (JobMachine machine : machines) {
			BigDecimal machineTime = BigDecimal.ZERO, machineCost = BigDecimal.ZERO;
			if (machine.getMchBackflushFlag()) {
				switch (machine.getTimeRateFlag()) {
				case TIME:
				case RATE:
					machineTime = MfUtils.tickToTime(machine.getRunTicksMch(), MfUtils.HOUR).multiply(productQuan);
					break;
				case FIXED:
					machineTime = MfUtils.tickToTime(machine.getRunTicksMch(), MfUtils.HOUR);
					break;
				}
				switch (machine.getMchRecoveryFlag()) {
				case TIME:
					machineCost = machineTime.multiply(machine.getMchRate());
					break;
				case UNIT:
					machineCost = productQuan.multiply(machine.getMchRate());
					break;
				case FIXED:
					machineCost = machine.getMchRate();
					break;
				}
				docSpecs.add(new CreateManufactureSpecificationInfoImpl(
						ConfigurationHelper.getConfiguration().getMachineTime().getId(),
						null,
						machineCost.divide(machineTime),
						machineTime,
						null,
						machine,
						machine.getMchCostCategory(),
						null,
						null));
			}
			//overhead
			if (machine.getMchOhBackflushFlag()) {
				BigDecimal price = null;
				switch (machine.getMchOhAllocationFlag()) {
				case TIME:
					price = machineCost.multiply(machine.getMchOhRate());
					break;
				case UNIT:
					price = productQuan.multiply(machine.getMchOhRate());
					break;
				case COST:
					price = machineCost.multiply(machine.getMchOhRatio());
					break;
				case FIXED:
					price = machine.getMchOhRate();
					break;
				}
				docSpecs.add(new CreateManufactureSpecificationInfoImpl(
						ConfigurationHelper.getConfiguration().getMachineOverhead().getId(),
						null,
						price,
						BigDecimal.ZERO,
						null,
						machine,
						machine.getMchOhCostCategory(),
						null,
						null));
			}
			
			if (!docSpecs.isEmpty()) {
				//create document
				InputMachineHeadServiceLocal docService = (InputMachineHeadServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(InputMachineHeadServiceLocal.SERVICE_NAME);
				InputDocumentHead docHead = (InputDocumentHead) docService.createByPattern(ConfigurationHelper.getConfiguration().getInputMachineModelBf(), null);
				docHead.setDocDate(outputDocHead.getDocDate());
				docHead.setBackFlushFlag(true);
				docHead.setOutputDocHead(outputDocHead);
				docHead.setWC(outputDocHead.getWC());
				docHead.setJob(outputDocHead.getJob());
				docHead.setOper(jobRoute);
				
				docService.create(docHead);
				
				docService.getSpecificationService().bulkCreate(docHead, docSpecs.toArray(new CreateManufactureSpecificationInfoImpl[docSpecs.size()]));
				
				//implicitly execute document process for document
				DocFlowHelper.execute(docHead.getId(), DocFlowManager.DOCFLOW_OWNER, true);
			}
		}
	}

	private void createInputMaterialDocument(JobRoute jobRoute, BigDecimal productQuan, BigDecimal timeOper, OutputProductHead outputDocHead) {
		List<CreateManufactureSpecificationInfoImpl> docSpecs = new ArrayList<CreateManufactureSpecificationInfoImpl>();
		List<JobMaterial> materials = ormTemplate.findByCriteria(OrmTemplate.createCriteria(JobMaterial.class)
				.add(Restrictions.eq("Oper", jobRoute)));
		
		for (JobMaterial material : materials) {
			/*устанавливаем цену 0, в текущей реализации невозможно получить цену
			  списания без отработки по складу, таким образом вычисляем только количество
			  для корректного получения цены списания следующим этапом должен идти
			  этап "Отработка по складу с простановкой цен", далее необходимо
			  выполнить этап "Расчет НР" который откорректирует суммы НР, т.е.
			  цепочка ДО выглядит следующим образом
			  "Отработка обратного списания" -> "Отработка по складу с простановкой цен" -> "Расчет НР"
			  вынуждаем двигаться именно по такой цепочке, но иначе обойти данную
			  проблему не представляется возможным*/
			BigDecimal materialCost = BigDecimal.ZERO;
			if (material.getMtlBackflushFlag()) {
				BigDecimal quan = BigDecimal.ZERO;
				switch (material.getQuantityRateFlag()) {
				case TIME:
					quan = timeOper.multiply(material.getMtlQty()).divide(BigDecimal.ONE.subtract(material.getScrapFactor()));
					break;
				case UNIT:
					quan = productQuan.multiply(material.getMtlQty()).divide(BigDecimal.ONE.subtract(material.getScrapFactor()));
					break;
				case FIXED:
					quan = material.getMtlQty().divide(BigDecimal.ONE.subtract(material.getScrapFactor()));
					break;
				}
				
				docSpecs.add(new CreateManufactureSpecificationInfoImpl(
						material.getCatalog().getId(),
						null,
						materialCost,
						quan,
						null,
						material,
						material.getMtlCostCategory(),
						material.getMeasure(),
						null));
			}
			//overhead
			if (material.getMtlOhBackflushFlag()) {
				BigDecimal price = null;
				switch (material.getMtlOhAllocationFlag()) {
				case UNIT:
					price = productQuan.multiply(material.getMtlOhRate());
					break;
				case COST:
					price = materialCost.multiply(material.getMtlOhRatio());
					break;
				case FIXED:
					price = material.getMtlOhRate();
					break;
				}
				docSpecs.add(new CreateManufactureSpecificationInfoImpl(
						ConfigurationHelper.getConfiguration().getMaterialOverhead().getId(),
						null,
						price,
						BigDecimal.ONE,
						null,
						material,
						material.getMtlOhCostCategory(),
						null,
						null));
			}
		}
		
		if (!docSpecs.isEmpty()) {
			//create document
			InputMaterialHeadServiceLocal docService = (InputMaterialHeadServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(InputMaterialHeadServiceLocal.SERVICE_NAME);
			InputDocumentHead docHead = (InputDocumentHead) docService.createByPattern(ConfigurationHelper.getConfiguration().getInputMaterialModelBf(), null);
			docHead.setDocDate(outputDocHead.getDocDate());
			docHead.setSrcStock(outputDocHead.getJob().getDefSrcStock());
			docHead.setSrcMol(outputDocHead.getJob().getDefSrcMol());
			docHead.setDstStock(outputDocHead.getJob().getDefDstStock());
			docHead.setDstMol(outputDocHead.getJob().getDefDstMol());
			docHead.setBackFlushFlag(true);
			docHead.setOutputDocHead(outputDocHead);
			docHead.setWC(outputDocHead.getWC());
			docHead.setJob(outputDocHead.getJob());
			docHead.setOper(jobRoute);
			
			docService.create(docHead);
			
			docService.getSpecificationService().bulkCreate(docHead, docSpecs.toArray(new CreateManufactureSpecificationInfoImpl[docSpecs.size()]));
			
			//implicitly execute document process for document
			DocFlowHelper.execute(docHead.getId(), DocFlowManager.DOCFLOW_OWNER, true);
		}
	}

	private void internalPerformBackflush(OutputProductHead docHead, BigDecimal productQuan) {
		List<JobRoute> routes = loadJobRoute(docHead.getJob());
		int currentJobOperIndex = checkJobRoute(routes, docHead.getOper());
		int prevControlPoint = getPrevControlPoint(routes, currentJobOperIndex);
		
		for (int i = currentJobOperIndex; i <= prevControlPoint; i++) {
			JobRoute jobRoute = routes.get(i);
			BigDecimal tOper = createInputLaborDocument(jobRoute, productQuan, docHead); //выполняем первой, в ней готовятся входные данные для следующих
			createInputMaterialDocument(jobRoute, productQuan, tOper, docHead);
			createInputMachineDocument(jobRoute, productQuan, tOper, docHead);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void internalRollbackPerformBackflush(OutputProductHead docHead) {
		List<InputDocumentHead> docs = ormTemplate.findByCriteria(OrmTemplate.createCriteria(InputDocumentHead.class)
				.add(Restrictions.eq("OutputDocHead", docHead))
				.add(Restrictions.eq("BackFlushFlag", true)));
		//удаляем средствами сервиса документов, чтобы были выполнены все проверки
		for (InputDocumentHead inputDoc : docs)
			DocumentUtils.getDocumentService(inputDoc.getDocSection()).erase((PersistentObject) inputDoc);
	}
	
	class ActualCost {
		private CostCategories costCategory;
		private BigDecimal cost;
		
		private ActualCost(CostCategories costCategory, BigDecimal cost) {
			super();
			this.costCategory = costCategory;
			this.cost = cost;
		}
		
	}
	
	private List<ActualCost> calculateActualCost(Criterion criterion) {
		return ormTemplate.findByCriteria(OrmTemplate.createCriteria(Transaction.class)
				.add(null)
				.setProjection(Projections.projectionList(
						Projections.sum("Summa"),
						Projections.property("CostCategory"),
						Projections.groupProperty("CostCategory")))
				.setResultTransformer(new ResultTransformer<ActualCost>() {

					public ActualCost transformTuple(Object[] tuple, String[] aliases) {
						return new ActualCost((CostCategories) tuple[1], (BigDecimal) tuple[0]);
					}
					
				}));
	}
	
	private void modifyWorkCenterCost(WorkCenter workCenter) {
		MfUtils.clearCostDetailLine(workCenter.getActCostDetail());
		Currency currency = ConfigurationHelper.getConfiguration().getBaseCurrency();
		for (ActualCost actualCost : calculateActualCost(Restrictions.eq("WC", workCenter)))
			MfUtils.createCostDetailLine(workCenter.getActCostDetail(), actualCost.costCategory, actualCost.cost, currency);
	}
	
	private void modifyJobCost(Job job) {
		MfUtils.clearCostDetailLine(job.getActWipCostDetail());
		Currency currency = ConfigurationHelper.getConfiguration().getBaseCurrency();
		for (ActualCost actualCost : calculateActualCost(Restrictions.eq("Job", job)))
			MfUtils.createCostDetailLine(job.getActWipCostDetail(), actualCost.costCategory, actualCost.cost, currency);
	}
	
	private void modifyOperCost(JobRoute jobRoute) {
		MfUtils.clearCostDetailLine(jobRoute.getActCostDetail());
		Currency currency = ConfigurationHelper.getConfiguration().getBaseCurrency();
		for (ActualCost actualCost : calculateActualCost(Restrictions.eq("Oper", jobRoute)))
			MfUtils.createCostDetailLine(jobRoute.getActCostDetail(), actualCost.costCategory, actualCost.cost, currency);
	}
	
	private void modifyResourceCost(JobRoute jobRoute, DocHead docHead) {
		List<JobRouteResource> inputResources = ormTemplate.findByCriteria(OrmTemplate.createCriteria(InputDocumentSpec.class, "ds")
				.add(Restrictions.eq("ds.DocHead", docHead))
				.setProjection(Projections.property("ds.JobRouteResource")));
		List<JobRouteResource> outputResources = ormTemplate.findByCriteria(OrmTemplate.createCriteria(ScrapDocumentSpec.class, "ds")
				.add(Restrictions.eq("ds.DocHead", docHead))
				.setProjection(Projections.property("ds.JobRouteResource")));
		Currency currency = ConfigurationHelper.getConfiguration().getBaseCurrency();
		inputResources.addAll(outputResources);
		for (JobRouteResource jobRouteResource : inputResources) {
			MfUtils.clearCostDetailLine(jobRouteResource.getActCostDetail());
			for (ActualCost actualCost : calculateActualCost(Restrictions.eq("JobRouteResource", jobRouteResource)))
				MfUtils.createCostDetailLine(jobRoute.getActCostDetail(), actualCost.costCategory, actualCost.cost, currency);
		}
	}
	
	private void modifyActualCost(Job job, JobRoute jobRoute, WorkCenter workCenter, DocHead docHead) {
		modifyWorkCenterCost(workCenter);
		modifyJobCost(job);
		modifyOperCost(jobRoute);
		modifyResourceCost(jobRoute, docHead);
	}
	
	private void internalPostToWorkInProgress(DocHead docHead, List<DocumentSpecItem> specItems) {
		if (specItems.isEmpty())
			return;
		
		TransactionServiceLocal tranService = (TransactionServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(TransactionServiceLocal.SERVICE_NAME);
		
		for (DocumentSpecItem specItem : specItems) {
			Transaction tran = tranService.initialize();
			
			tran.setDocSpec(specItem.getDocSpec());
			tran.setQuantity(specItem.getPerformedQuantity1());
			tran.setSumma(specItem.getPerformedSum());
			
			switch (docHead.getDocSection().getId()) {
			case InputLaborHeadServiceLocal.DOCSECTION:
			case InputMachineHeadServiceLocal.DOCSECTION:
			case InputMaterialHeadServiceLocal.DOCSECTION:
				InputDocumentHead inputDocHead = (InputDocumentHead) docHead;
				InputDocumentSpec inputDocSpec = (InputDocumentSpec) specItem.getDocSpec();
				
				tran.setBackFlushFlag(inputDocHead.getBackFlushFlag());
				tran.setContractor(inputDocHead.getContractor());
				tran.setWC(inputDocHead.getWC());
				tran.setJob(inputDocHead.getJob());
				tran.setOper(inputDocHead.getOper());
				tran.setCrew(inputDocHead.getCrew());
				tran.setEmployee(inputDocHead.getEmployee());
				tran.setJobRouteResource(inputDocSpec.getJobRouteResource());
				tran.setCostCategory(inputDocSpec.getCostCategory());
				break;
			case ScrapLaborHeadServiceLocal.DOCSECTION:
			case ScrapMachineHeadServiceLocal.DOCSECTION:
			case ScrapMaterialHeadServiceLocal.DOCSECTION:
			case ScrapProductHeadServiceLocal.DOCSECTION:
				ScrapDocumentHead scrapDocHead = (ScrapDocumentHead) docHead;
				ScrapDocumentSpec scrapDocSpec = (ScrapDocumentSpec) specItem.getDocSpec();
				
				tran.setBackFlushFlag(false);
				tran.setContractor(scrapDocHead.getDetectContractor());
				tran.setWC(scrapDocHead.getDetectWC());
				tran.setJob(scrapDocHead.getDetectJob());
				tran.setOper(scrapDocHead.getDetectOper());
				tran.setCrew(scrapDocHead.getDetectCrew());
				tran.setEmployee(scrapDocHead.getDetectEmployee());
				tran.setJobRouteResource(scrapDocSpec.getJobRouteResource());
				tran.setCostCategory(scrapDocSpec.getCostCategory());
				break;
			default:
				continue;//продолжим цикл, но впринципе не должны сюда попадать
			}
			
			tranService.create(tran);
			specItem.setData1(tran.getId());
			
			modifyActualCost(tran.getJob(), tran.getOper(), tran.getWC(), docHead);
		}
	}
	
	private void internalRollbackPostToWorkInProgress(DocHead docHead, List<DocumentSpecItem> specItems) {
		Job job = null;
		JobRoute jobRoute = null;
		WorkCenter workCenter = null;
		switch (docHead.getDocSection().getId()) {
		case InputLaborHeadServiceLocal.DOCSECTION:
		case InputMachineHeadServiceLocal.DOCSECTION:
		case InputMaterialHeadServiceLocal.DOCSECTION:
			InputDocumentHead inputDocHead = (InputDocumentHead) docHead;
			job = inputDocHead.getJob();
			jobRoute = inputDocHead.getOper();
			workCenter = inputDocHead.getWC();
			break;
		case ScrapLaborHeadServiceLocal.DOCSECTION:
		case ScrapMachineHeadServiceLocal.DOCSECTION:
		case ScrapMaterialHeadServiceLocal.DOCSECTION:
		case ScrapProductHeadServiceLocal.DOCSECTION:
			ScrapDocumentHead scrapDocHead = (ScrapDocumentHead) docHead;
			job = scrapDocHead.getDetectJob();
			jobRoute = scrapDocHead.getDetectOper();
			workCenter = scrapDocHead.getDetectWC();
			break;
		default:
			return;
		}
		
		TransactionServiceLocal tranService = (TransactionServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(TransactionServiceLocal.SERVICE_NAME);
		
		for (DocumentSpecItem specItem : specItems)
			tranService.erase(specItem.getData1());
		
		//modify actual cost after clear mf_transaction
		modifyActualCost(job, jobRoute, workCenter, docHead);
	}
	
	private BigDecimal createScrapLaborDocument(JobRoute jobRoute, BigDecimal productQuan, ScrapDocumentHead scrapDocHead, Date processDate) {
		List<CreateManufactureSpecificationInfoImpl> docSpecs = new ArrayList<CreateManufactureSpecificationInfoImpl>();
		List<JobLabor> labors = ormTemplate.findByCriteria(OrmTemplate.createCriteria(JobLabor.class)
				.add(Restrictions.eq("Oper", jobRoute)));
		
		BigDecimal t1 = BigDecimal.ZERO, t2 = BigDecimal.ZERO;
		for (JobLabor labor : labors) {
			switch (labor.getTimeRateFlag()) {
			case TIME:
			case RATE:
				t1 = t1.add(productQuan.multiply(new BigDecimal(labor.getRunTicksLbr())));
				break;
			case FIXED:
				t2 = t2.add(new BigDecimal(labor.getRunTicksLbr()));
				break;
			}
			BigDecimal laborTime = BigDecimal.ZERO, laborCost = BigDecimal.ZERO;
			switch (labor.getTimeRateFlag()) {
			case TIME:
			case RATE:
				laborTime = MfUtils.tickToTime(labor.getRunTicksLbr(), MfUtils.HOUR).multiply(productQuan);
				break;
			case FIXED:
				laborTime = MfUtils.tickToTime(labor.getRunTicksLbr(), MfUtils.HOUR);
				break;
			}
			laborCost = laborTime.multiply(labor.getLbrRate());
			docSpecs.add(new CreateManufactureSpecificationInfoImpl(
					ConfigurationHelper.getConfiguration().getLaborTime().getId(),
					null,
					laborCost.divide(laborTime),
					laborTime,
					null,
					labor,
					labor.getLbrCostCategory(),
					null,
					null));
		}
		
		if (!docSpecs.isEmpty()) {
			//create document
			ScrapLaborHeadServiceLocal docService = (ScrapLaborHeadServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(ScrapLaborHeadServiceLocal.SERVICE_NAME);
			ScrapDocumentHead docHead = (ScrapDocumentHead) docService.createByPattern(ConfigurationHelper.getConfiguration().getScrapLaborModel(), null);
			docHead.setDocDate(processDate);
			docHead.setBaseDocument(scrapDocHead);
			docHead.setBaseDocType(scrapDocHead.getDocType());
			docHead.setBaseDocNumber(scrapDocHead.getDocNumber());
			docHead.setBaseDocDate(scrapDocHead.getBaseDocDate());
			docHead.setDetectWC(scrapDocHead.getDetectWC());
			docHead.setDetectJob(scrapDocHead.getDetectJob());
			docHead.setDetectOper(jobRoute);
			
			docService.create(docHead);

			docService.getSpecificationService().bulkCreate(docHead, docSpecs.toArray(new CreateManufactureSpecificationInfoImpl[docSpecs.size()]));
		}
		
		return t1.add(t2);
	}

	private void createScrapMachineDocument(JobRoute jobRoute, BigDecimal productQuan, BigDecimal timeOper, ScrapDocumentHead scrapDocHead, Date processDate) {
		List<CreateManufactureSpecificationInfoImpl> docSpecs = new ArrayList<CreateManufactureSpecificationInfoImpl>();
		List<JobMachine> machines = ormTemplate.findByCriteria(OrmTemplate.createCriteria(JobMachine.class)
				.add(Restrictions.eq("Oper", jobRoute)));
		
		for (JobMachine machine : machines) {
			BigDecimal machineTime = BigDecimal.ZERO, machineCost = BigDecimal.ZERO;
			switch (machine.getTimeRateFlag()) {
			case TIME:
			case RATE:
				machineTime = MfUtils.tickToTime(machine.getRunTicksMch(), MfUtils.HOUR).multiply(productQuan);
				break;
			case FIXED:
				machineTime = MfUtils.tickToTime(machine.getRunTicksMch(), MfUtils.HOUR);
				break;
			}
			switch (machine.getMchRecoveryFlag()) {
			case TIME:
				machineCost = machineTime.multiply(machine.getMchRate());
				break;
			case UNIT:
				machineCost = productQuan.multiply(machine.getMchRate());
				break;
			case FIXED:
				machineCost = machine.getMchRate();
				break;
			}
			docSpecs.add(new CreateManufactureSpecificationInfoImpl(
					ConfigurationHelper.getConfiguration().getMachineTime().getId(),
					null,
					machineCost.divide(machineTime),
					machineTime,
					null,
					machine,
					machine.getMchCostCategory(),
					null,
					null));
			
			if (!docSpecs.isEmpty()) {
				//create document
				ScrapMachineHeadServiceLocal docService = (ScrapMachineHeadServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(ScrapMachineHeadServiceLocal.SERVICE_NAME);
				ScrapDocumentHead docHead = (ScrapDocumentHead) docService.createByPattern(ConfigurationHelper.getConfiguration().getScrapMachineModel(), null);
				docHead.setDocDate(processDate);
				docHead.setBaseDocument(scrapDocHead);
				docHead.setBaseDocType(scrapDocHead.getDocType());
				docHead.setBaseDocNumber(scrapDocHead.getBaseDocNumber());
				docHead.setBaseDocDate(scrapDocHead.getBaseDocDate());
				docHead.setDetectWC(scrapDocHead.getDetectWC());
				docHead.setDetectJob(scrapDocHead.getDetectJob());
				docHead.setDetectOper(jobRoute);

				docService.create(docHead);
				
				docService.getSpecificationService().bulkCreate(docHead, docSpecs.toArray(new CreateManufactureSpecificationInfoImpl[docSpecs.size()]));
			}
		}
	}

	private void createScrapMaterialDocument(JobRoute jobRoute, BigDecimal productQuan, BigDecimal timeOper, ScrapDocumentHead outputDocHead, Date processDate) {
		List<CreateManufactureSpecificationInfoImpl> docSpecs = new ArrayList<CreateManufactureSpecificationInfoImpl>();
		List<JobMaterial> materials = ormTemplate.findByCriteria(OrmTemplate.createCriteria(JobMaterial.class)
				.add(Restrictions.eq("Oper", jobRoute)));
		
		for (JobMaterial material : materials) {
			/*устанавливаем цену 0, в текущей реализации невозможно получить цену
			  списания без отработки по складу, таким образом вычисляем только количество
			  для корректного получения цены списания следующим этапом должен идти
			  этап "Отработка по складу с простановкой цен", далее необходимо
			  выполнить этап "Расчет НР" который откорректирует суммы НР, т.е.
			  цепочка ДО выглядит следующим образом
			  "Отработка обратного списания" -> "Отработка по складу с простановкой цен" -> "Расчет НР"
			  вынуждаем двигаться именно по такой цепочке, но иначе обойти данную
			  проблему не представляется возможным*/
			BigDecimal materialCost = BigDecimal.ZERO;
				BigDecimal quan = BigDecimal.ZERO;
				switch (material.getQuantityRateFlag()) {
				case TIME:
					quan = timeOper.multiply(material.getMtlQty()).divide(BigDecimal.ONE.subtract(material.getScrapFactor()));
					break;
				case UNIT:
					quan = productQuan.multiply(material.getMtlQty()).divide(BigDecimal.ONE.subtract(material.getScrapFactor()));
					break;
				case FIXED:
					quan = material.getMtlQty().divide(BigDecimal.ONE.subtract(material.getScrapFactor()));
					break;
				}
				
				docSpecs.add(new CreateManufactureSpecificationInfoImpl(
						material.getCatalog().getId(),
						null,
						materialCost,
						quan,
						null,
						material,
						material.getMtlCostCategory(),
						material.getMeasure(),
						null));
		}
		
		if (!docSpecs.isEmpty()) {
			//create document
			ScrapMaterialHeadServiceLocal docService = (ScrapMaterialHeadServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(ScrapMaterialHeadServiceLocal.SERVICE_NAME);
			ScrapDocumentHead docHead = (ScrapDocumentHead) docService.createByPattern(ConfigurationHelper.getConfiguration().getScrapMaterialModel(), null);
			docHead.setDocDate(processDate);
			docHead.setSrcStock(outputDocHead.getDetectJob().getDefSrcStock());
			docHead.setSrcMol(outputDocHead.getDetectJob().getDefSrcMol());
			docHead.setDstStock(outputDocHead.getDetectJob().getDefDstStock());
			docHead.setDstMol(outputDocHead.getDetectJob().getDefDstMol());
			docHead.setBaseDocument(outputDocHead);
			docHead.setBaseDocNumber(outputDocHead.getDocNumber());
			docHead.setBaseDocDate(outputDocHead.getDocDate());
			docHead.setBaseDocDate(outputDocHead.getDocDate());
			docHead.setDetectWC(outputDocHead.getDetectWC());
			docHead.setDetectJob(outputDocHead.getDetectJob());
			docHead.setDetectOper(jobRoute);

			docService.create(docHead);
			
			docService.getSpecificationService().bulkCreate(docHead, docSpecs.toArray(new CreateManufactureSpecificationInfoImpl[docSpecs.size()]));
		}
	}

	private void internalPerformScrap(ScrapDocumentHead docHead, BigDecimal quantity, Date processDate) {
		List<JobRoute> routes = loadJobRoute(docHead.getDetectJob());
		int currentJobOperIndex = tryCheckJobRoute(routes, docHead.getDetectOper());
		
		for (int i = currentJobOperIndex; i <= routes.size() - 1 && i >= 0; i++) {
			JobRoute jobRoute = routes.get(i);
			BigDecimal tOper = createScrapLaborDocument(jobRoute, quantity, docHead, processDate); //выполняем первой, в ней готовятся входные данные для следующих
			createScrapMaterialDocument(jobRoute, quantity, tOper, docHead, processDate);
			createScrapMachineDocument(jobRoute, quantity, tOper, docHead, processDate);
		}		
	}
	
	@SuppressWarnings("unchecked")
	private void internalRollbackPerformScrap(ScrapDocumentHead docHead) {
		List<ScrapDocumentHead> docs = ormTemplate.findByCriteria(OrmTemplate.createCriteria(ScrapDocumentHead.class)
				.add(Restrictions.eq("BaseDocument", docHead)));
		//удаляем средствами сервиса документов, чтобы были выполнены все проверки
		for (ScrapDocumentHead scrapDoc : docs)
			DocumentUtils.getDocumentService(scrapDoc.getDocSection()).erase((PersistentObject) scrapDoc);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.manufacture.ManufactureProcessorServiceLocal#rollbackUpdateJobFromOutputDocument(com.mg.merp.docflow.DocFlowPluginInvokeParams)
	 */
	@PermitAll
	public void rollbackUpdateJobFromOutputDocument(DocFlowPluginInvokeParams params) {
		OutputProductHead docHead = (OutputProductHead) params.getDocument();
		assert params.getSpecList().isEmpty();
		internalUpdateJobFromOutputDocument(docHead.getJob(), docHead.getOper(), params.getSpecList().get(0).getPerformedQuantity1().negate(), true);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.manufacture.ManufactureProcessorServiceLocal#updateJobFromOutputDocument(com.mg.merp.docflow.DocFlowPluginInvokeParams)
	 */
	@PermitAll
	public void updateJobFromOutputDocument(DocFlowPluginInvokeParams params) {
		OutputProductHead docHead = (OutputProductHead) params.getDocument();
		assert params.getSpecList().isEmpty();
		internalUpdateJobFromOutputDocument(docHead.getJob(), docHead.getOper(), params.getSpecList().get(0).getPerformedQuantity1(), true);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.manufacture.ManufactureProcessorServiceLocal#rollbackUpdateJobFromScrapDocument(com.mg.merp.docflow.DocFlowPluginInvokeParams)
	 */
	@PermitAll
	public void rollbackUpdateJobFromScrapDocument(DocFlowPluginInvokeParams params) {
		ScrapDocumentHead docHead = (ScrapDocumentHead) params.getDocument();
		assert params.getSpecList().isEmpty();
		internalUpdateJobFromOutputDocument(docHead.getDetectJob(), docHead.getDetectOper(), params.getSpecList().get(0).getPerformedQuantity1().negate(), false);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.manufacture.ManufactureProcessorServiceLocal#updateJobFromScrapDocument(com.mg.merp.docflow.DocFlowPluginInvokeParams)
	 */
	@PermitAll
	public void updateJobFromScrapDocument(DocFlowPluginInvokeParams params) {
		ScrapDocumentHead docHead = (ScrapDocumentHead) params.getDocument();
		assert params.getSpecList().isEmpty();
		internalUpdateJobFromOutputDocument(docHead.getDetectJob(), docHead.getDetectOper(), params.getSpecList().get(0).getPerformedQuantity1(), false);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.manufacture.ManufactureProcessorServiceLocal#performBackflush(com.mg.merp.docflow.DocFlowPluginInvokeParams)
	 */
	@PermitAll
	public void performBackflush(DocFlowPluginInvokeParams params) {
		OutputProductHead docHead = (OutputProductHead) params.getDocument();
		assert params.getSpecList().isEmpty();
		internalPerformBackflush(docHead, params.getSpecList().get(0).getPerformedQuantity1());
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.manufacture.ManufactureProcessorServiceLocal#rollbackPerformBackflush(com.mg.merp.docflow.DocFlowPluginInvokeParams)
	 */
	@PermitAll
	public void rollbackPerformBackflush(DocFlowPluginInvokeParams params) {
		internalRollbackPerformBackflush((OutputProductHead) params.getDocument());
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.manufacture.ManufactureProcessorServiceLocal#postToWorkInProgress(com.mg.merp.docflow.DocFlowPluginInvokeParams)
	 */
	@PermitAll
	public void postToWorkInProgress(DocFlowPluginInvokeParams params) {
		internalPostToWorkInProgress(params.getDocument(), params.getSpecList());
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.manufacture.ManufactureProcessorServiceLocal#rollbackPostToWorkInProgress(com.mg.merp.docflow.DocFlowPluginInvokeParams)
	 */
	@PermitAll
	public void rollbackPostToWorkInProgress(DocFlowPluginInvokeParams params) {
		internalRollbackPostToWorkInProgress(params.getDocument(), params.getSpecList());
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.manufacture.ManufactureProcessorServiceLocal#performScrap(com.mg.merp.docflow.DocFlowPluginInvokeParams)
	 */
	@PermitAll
	public void performScrap(DocFlowPluginInvokeParams params) {
		ScrapDocumentHead scrapDocHead = (ScrapDocumentHead) params.getDocument();
		assert params.getSpecList().isEmpty();
		internalPerformScrap(scrapDocHead, params.getSpecList().get(0).getPerformedQuantity1(), params.getProcessDate());
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.manufacture.ManufactureProcessorServiceLocal#rollbackPerformScrap(com.mg.merp.docflow.DocFlowPluginInvokeParams)
	 */
	@PermitAll
	public void rollbackPerformScrap(DocFlowPluginInvokeParams params) {
		internalRollbackPerformScrap((ScrapDocumentHead) params.getDocument());
	}

}