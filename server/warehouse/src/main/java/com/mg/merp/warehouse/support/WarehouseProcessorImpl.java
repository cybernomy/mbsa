/* WarehouseProcessorImpl.java
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
package com.mg.merp.warehouse.support;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.BusinessException;
import com.mg.framework.api.math.Constants;
import com.mg.framework.api.math.RoundContext;
import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.baiengine.BusinessAddinEngineLocator;
import com.mg.merp.baiengine.BusinessAddinEvent;
import com.mg.merp.baiengine.BusinessAddinListener;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;
import com.mg.merp.docflow.DocumentSpecItem;
import com.mg.merp.document.GoodsDocumentSpecification;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.document.support.DocumentUtils;
import com.mg.merp.reference.CurrencyServiceLocal;
import com.mg.merp.reference.MeasureConversionServiceLocal;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.CatalogType;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.Country;
import com.mg.merp.reference.model.Currency;
import com.mg.merp.reference.model.CustomsDeclaration;
import com.mg.merp.reference.model.MeasureControl;
import com.mg.merp.reference.model.SetOfGood;
import com.mg.merp.warehouse.GoodsIssueStrategy;
import com.mg.merp.warehouse.WarehouseProcessDocumentLineData;
import com.mg.merp.warehouse.WarehouseProcessListener;
import com.mg.merp.warehouse.WarehouseProcessStrategy;
import com.mg.merp.warehouse.WarehouseProcessor;
import com.mg.merp.warehouse.WarehouseRollbackStrategy;
import com.mg.merp.warehouse.WarehouseServiceLocal;
import com.mg.merp.warehouse.WarehouseTransactionListener;
import com.mg.merp.warehouse.generic.WarehouseProcessFactStrategy;
import com.mg.merp.warehouse.generic.WarehouseProcessPlanStrategy;
import com.mg.merp.warehouse.generic.WarehouseProcessPlanWithdrawalStrategy;
import com.mg.merp.warehouse.generic.WarehouseProcessReservStrategy;
import com.mg.merp.warehouse.generic.WarehouseProcessReservWithdrawalStrategy;
import com.mg.merp.warehouse.generic.WarehouseRollbackAssembleProductStrategy;
import com.mg.merp.warehouse.generic.WarehouseRollbackFactStrategy;
import com.mg.merp.warehouse.generic.WarehouseRollbackPlanStrategy;
import com.mg.merp.warehouse.generic.WarehouseRollbackPlanWithdrawalStrategy;
import com.mg.merp.warehouse.generic.WarehouseRollbackReservStrategy;
import com.mg.merp.warehouse.generic.WarehouseRollbackReservWithdrawalStrategy;
import com.mg.merp.warehouse.model.StockBatch;
import com.mg.merp.warehouse.model.StockBatchHistory;
import com.mg.merp.warehouse.model.StockBatchHistoryKind;
import com.mg.merp.warehouse.model.Warehouse;
import com.mg.merp.warehouse.model.WarehouseTransactionDay;
import com.mg.merp.warehouse.support.ui.CustomsDeclarationNotFoundDlg;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: WarehouseProcessorImpl.java,v 1.9 2008/08/27 09:41:21 sharapov Exp $
 */
public class WarehouseProcessorImpl implements WarehouseProcessor {
	private static WarehouseProcessListener DUMMY_WAREHOUSE_LISTENER = new WarehouseProcessListener() {

		public void aborted() {
			//stub
		}

		public void completed() {
			//stub
		}
		
	};

	private EnumSet<CatalogType> materialTypes = EnumSet.of(CatalogType.GOODS, CatalogType.SET_OF_GOODS, CatalogType.PACKAGE);
	
	/**
	 * 
	 * @param oldDocSpec
	 *            спецификация, на основе которой производится перерасчёт
	 * 
	 * @param newDocSpec
	 *            измененная спецификация
	 * 
	 * @param processDate
	 *            дата отработки
	 * @return измененная спецификация
	 */
	private DocSpec recalculateMeasure(DocSpec oldDocSpec, DocSpec newDocSpec, Date processDate) {
		BigDecimal measureFactor = new BigDecimal(1);
		if ((oldDocSpec.getMeasure1().getId() != oldDocSpec.getCatalog().getMeasure1().getId())
				|| (oldDocSpec.getMeasure2() != null && oldDocSpec.getCatalog().getMeasure2() != null && (oldDocSpec.getMeasure2()
						.getId() != oldDocSpec.getCatalog().getMeasure2().getId()))) {
			MeasureConversionServiceLocal convers = (MeasureConversionServiceLocal) ApplicationDictionaryLocator.locate()
			.getBusinessService(MeasureConversionServiceLocal.LOCAL_SERVICE_NAME);
			BigDecimal newVal = convers.conversion(oldDocSpec.getMeasure1(), oldDocSpec.getCatalog().getMeasure1(), oldDocSpec
					.getCatalog(), processDate, newDocSpec.getQuantity());
			measureFactor = (MathUtils.compareToZero(newVal) == 1 ? newDocSpec.getQuantity().divide(newVal) : measureFactor);
			newDocSpec.setQuantity(newVal);
			newDocSpec.setPrice(newDocSpec.getPrice().multiply(measureFactor));

			// на складах в 2х количествах учитываем только "учетно/весовые"
			// для остальных 2е количество всегда = 0
			if (newDocSpec.getQuantity2() != null && newDocSpec.getPrice1() != null
					&& (oldDocSpec.getCatalog().getMeasureControl() == MeasureControl.CATCHWEIGHT)) {

				newDocSpec.setQuantity2(convers.conversion(	oldDocSpec.getMeasure2(),
						oldDocSpec.getCatalog().getMeasure2(),
						oldDocSpec.getCatalog(),
						processDate,
						newDocSpec.getQuantity2()));
				newDocSpec.setPrice1(newDocSpec.getPrice1().multiply(measureFactor));
			}
		}
		return newDocSpec;
	}

	private void doProcessDocSpec(WarehouseProcessDocumentLineData docLineData, WarehouseProcessStrategy processStrategy) {
		if (materialTypes.contains(docLineData.getDocumentSpec().getCatalog().getGoodType())) {
			checkWarehouseTransaction(docLineData);
			
			if (docLineData.getSrcStock() != null || docLineData.getDstStock() != null) {
				processStrategy.process(docLineData);
			} else 
				throw new BusinessException(String.format(Messages.getInstance().getMessage(Messages.SRC_OR_DST_STOCK_NOT_FIND), docLineData.getDocumentSpec().getCatalog().getCode().trim()));
		}
	}

	/**
	 * Рассчёт цены в документе на основании данных склада
	 * 
	 * @param docSpec
	 *            спецификация к отработке
	 */
	private void doPrepareCalcPrices(DocSpec docSpec, final DocFlowPluginInvokeParams params) {
		if ((docSpec.getCatalog().getGoodType() == CatalogType.GOODS
				|| docSpec.getCatalog().getGoodType() == CatalogType.SET_OF_GOODS || docSpec.getCatalog().getGoodType() == CatalogType.PACKAGE)
				&& ((MathUtils.compareToZero(docSpec.getQuantity()) > -1))) {

			DocSpec workDocSpec = new DocSpec();

			workDocSpec.setSrcMol(docSpec.getSrcMol() != null ? docSpec.getSrcMol() : docSpec.getDocHead().getSrcMol());
			workDocSpec.setSrcStock(docSpec.getSrcStock() != null ? docSpec.getSrcStock() : docSpec.getDocHead().getSrcStock());

			// TODO: сейчас при пустом складе этап проходит.
			// уточнить у консультантов, может нужно генерировать ИС
			if (workDocSpec.getSrcStock() != null) {
				workDocSpec.setId(docSpec.getId());
				workDocSpec.setCatalog(docSpec.getCatalog());
				workDocSpec.setContractor(docSpec.getContractor());
				workDocSpec.setDocHead(docSpec.getDocHead());
				workDocSpec.setId(docSpec.getId());
				workDocSpec.setMeasure1(docSpec.getMeasure1());
				workDocSpec.setMeasure2(docSpec.getMeasure2());

				workDocSpec.setQuantity(docSpec.getQuantity());
				workDocSpec.setQuantity2(docSpec.getQuantity2());
				workDocSpec.setPrice(docSpec.getPrice());
				workDocSpec.setPrice1(docSpec.getPrice1());
				workDocSpec.setSumma(docSpec.getSumma());
				workDocSpec.setSumma1(docSpec.getSumma1());

				workDocSpec = recalculateMeasure(docSpec, workDocSpec, params.getProcessDate());

				WarehouseServiceLocal whServ = (WarehouseServiceLocal) ApplicationDictionaryLocator.locate()
				.getBusinessService(WarehouseServiceLocal.LOCAL_SERVICE_NAME);
				Warehouse wh = whServ.load(workDocSpec.getSrcStock().getId());

				GoodsIssueStrategy strategy = null;

				class DisposalStrategyBAiListener implements BusinessAddinListener<GoodsIssueStrategy> {

					private DocSpec workDocSpec;

					DisposalStrategyBAiListener(DocSpec workDocSpec) {
						this.workDocSpec = workDocSpec;
					}

					public void aborted(BusinessAddinEvent<GoodsIssueStrategy> event) {
						// TODO Auto-generated method stub

					}

					public void completed(BusinessAddinEvent<GoodsIssueStrategy> event) {
						doCalcPricesByWarehouseData(workDocSpec, event.getResult(), params);
					}

				}

				if (wh.getStockPolicy() != null) {
//					switch (wh.getStockPolicy()) {
//					case SPFIFO:
//						strategy = new FIFOLIFODisposalStrategy(true, workDocSpec, params.getProcessDate(), null, null);
//						break;
//					case SPLIFO:
//						strategy = new FIFOLIFODisposalStrategy(false, workDocSpec, params.getProcessDate(), null, null);
//						break;
//					case SPBATCH:
//						strategy = new BatchDisposalStrategy(workDocSpec, params.getProcessDate(), null, null);
//						break;
//					default:
//						break;
//					}
					doCalcPricesByWarehouseData(workDocSpec, strategy, params);
				} else {
					String baiUserDisposalStrategyCode = wh.getDisposalStrategy().getCode().trim();

					BusinessAddinEngineLocator.locate().perform(baiUserDisposalStrategyCode,
							null, new DisposalStrategyBAiListener(workDocSpec));
				}

			}
		}

	}

	@SuppressWarnings("unchecked") //$NON-NLS-1$
	private void doCalcPricesByWarehouseData(DocSpec docSpec, GoodsIssueStrategy strategy, DocFlowPluginInvokeParams params) {
		DocHead docHead = docSpec.getDocHead();
		BigDecimal dispValue = docSpec.getQuantity();
		List<StockBatch> batches = strategy.getBatchesByOrder();
		GoodsDocumentSpecification specServ = DocumentUtils.getGoodsDocumentSpecificationService(docHead.getDocSection());
		PersistentObject po = specServ.load(docSpec.getId());
		PersistentObject nextRecord = specServ.initialize(po.getAllAttributes());

		boolean getNext = true;
		Iterator<StockBatch> it = batches.listIterator();
		RoundContext rcCur = new RoundContext(ConfigurationHelper.getConfiguration().getCurrencyPrec());
		//последняя из вновь созданных записей
		PersistentObject lastRecord = null;
		do {
			StockBatch stockBatch = it.next();
			BigDecimal poQuan1 = docSpec.getQuantity();

			BigDecimal sbEndQuan1 = stockBatch.getEndQuan();

			BigDecimal deltaQuan1 = MathUtils.subtract(poQuan1, sbEndQuan1, Constants.QUANTITY_ROUND_CONTEXT);

			CurrencyServiceLocal curServ = (CurrencyServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CurrencyServiceLocal.LOCAL_SERVICE_NAME);

			Currency batchCurrency = curServ.findByCriteria(Restrictions.eq("UpCode", stockBatch.getCurrencyCode().toUpperCase())).get(0); //$NON-NLS-1$

			BigDecimal priceCur = MathUtils.round(curServ.conversion(docHead.getCurrency(), batchCurrency, 
					docHead.getCurrencyRateAuthority(), docHead.getCurrencyRateType(), 
					params.getProcessDate(), stockBatch.getPriceCur()),rcCur);

			if (MathUtils.compareToZero(deltaQuan1) < 1) {
				getNext = false;
				nextRecord.setAttribute("Quantity", poQuan1); //$NON-NLS-1$
			} else {
				nextRecord.setAttribute("Quantity", sbEndQuan1); //$NON-NLS-1$
				docSpec.setQuantity(deltaQuan1);
			}

			nextRecord.setAttribute("Price1", priceCur); //$NON-NLS-1$
			//сумма рассчитывается автоматически

			// TODO: Вес, объём, скидки

			specServ.store(nextRecord);
			//сохраняем созданную запись
			lastRecord = nextRecord;
			AttributeMap am = po.getAllAttributes();
			am.put("Id", null); //$NON-NLS-1$
			nextRecord = specServ.initialize(am);
		} while (getNext && it.hasNext());
		//Если позиции в партиях кончились, а в документе осталось не рассчитанное количество,
		//то цена проставляется из последней записи.
		BigDecimal lastPortion = MathUtils.subtract(dispValue, strategy.getAvailable(), Constants.QUANTITY_ROUND_CONTEXT);
		if (MathUtils.compareToZero(lastPortion) == 1) {
			lastRecord.setAttribute("Quantity", lastPortion); //$NON-NLS-1$
			specServ.store(lastRecord);
		}
	}

	/**
	 * Откат отработки спецификации документа
	 * 
	 * @param docSpecItem
	 */
	private void doRollbackDocSpec(WarehouseRollbackStrategy strategy, DocFlowPluginInvokeParams params) {
		for (DocumentSpecItem dsi : params.getSpecList()) {
			WarehouseProcessDocumentLineData docLineData = new WarehouseProcessDocumentLineDataImpl(dsi, params.getProcessDate());
			checkWarehouseTransaction(docLineData);
			strategy.rollback(docLineData);
		}
	}

	/**
	 * Откат отработки спецификации документа
	 * 
	 * @param docSpecItem
	 */
	private void doRollbackAssembleProductDocSpec(WarehouseRollbackAssembleProductStrategy strategy, DocFlowPluginInvokeParams params) {
		for (DocumentSpecItem dsi : params.getSpecList())
			strategy.doRollback(dsi);
	}

	/**
	 * Проверяем закрыт ли склад и учитывает ли он операционные дни
	 * @param stock
	 */
	private void preProcessingWarehouseTransaction(Contractor warehouse, Date processDate) {
		if (warehouse != null) {
			Warehouse stock = (Warehouse) ServerUtils.getPersistentManager().find(Warehouse.class, warehouse.getId());
			Date dateTillClosed = stock.getClosedDateTill();
			// если склад закрыт для фактического движения то смотрим по какую дату 
			if (stock.getWarehouseTransactionClosed() && dateTillClosed != null)
				if (processDate.compareTo(dateTillClosed) < 1) {
					throw new BusinessException(StringUtils.format(Messages.getInstance().getMessage(Messages.STOCK_CLOSED_DATE_TILL)
							, stock.getCode().trim(), DateFormat.getDateInstance(DateFormat.SHORT).format(dateTillClosed)));					
				}
			// если на складе учитываются операционные дни то проверяем не является данный день закрытым
			if (stock.getCheckTransactionDay()) {
				List<WarehouseTransactionDay> trDayList = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(WarehouseTransactionDay.class)
						.add(Restrictions.eq("Stock", stock)) //$NON-NLS-1$
						.add(Restrictions.eq("ClosedDay", processDate)) //$NON-NLS-1$
						.add(Restrictions.isNotNull("OperationDate")) //$NON-NLS-1$
						.add(Restrictions.isNotNull("UserStockClosed")) //$NON-NLS-1$
						.setMaxResults(1));
				if (!trDayList.isEmpty()) {
					throw new BusinessException(StringUtils.format(Messages.getInstance().getMessage(Messages.STOCK_CLOSED)
							, stock.getCode().trim(), DateFormat.getDateInstance(DateFormat.SHORT).format(processDate)));
				}
			}
		}	
	}
	
	/**
	 * Проверяем разрешено ли фактическое движение по складу - источнику и складу приемнику
	 */
	private void checkWarehouseTransaction(WarehouseProcessDocumentLineData docLineData) {
		Date date = DateTimeUtils.getDayStart(docLineData.getProcessDate());
		preProcessingWarehouseTransaction(docLineData.getSrcStock(), date);
		preProcessingWarehouseTransaction(docLineData.getDstStock(), date);		
	}
	
	class WarehouseProcessTransactionListenerImpl implements WarehouseProcessListener {
		private WarehouseTransactionListener warehouseTransactionListener;
		private ListIterator<DocumentSpecItem> specIterator;
		private WarehouseProcessStrategy strategy;
		private DocFlowPluginInvokeParams params;

		private WarehouseProcessTransactionListenerImpl(WarehouseTransactionListener warehouseTransactionListener
				, ListIterator<DocumentSpecItem> specIterator, DocFlowPluginInvokeParams params) {
			this.warehouseTransactionListener = warehouseTransactionListener;
			this.specIterator = specIterator;
			this.params = params;
		}
		
		public void aborted() {
			warehouseTransactionListener.aborted();
		}

		public void completed() {
			if (specIterator.hasNext())
				doProcessDocSpec(new WarehouseProcessDocumentLineDataImpl(specIterator.next(), params.getProcessDate()), strategy);
			else
				warehouseTransactionListener.completed();
		}
		
	}
	
	public void processWarehouseTransaction(final DocFlowPluginInvokeParams params, boolean doInteractive, boolean doCalculateCost, WarehouseTransactionListener listener) {
		ListIterator<DocumentSpecItem> specIterator = params.getSpecList().listIterator();
		WarehouseProcessTransactionListenerImpl warehouseProcessListener = new WarehouseProcessTransactionListenerImpl(listener, specIterator, params);
		WarehouseProcessStrategy strategy = new WarehouseProcessFactStrategy(params, doInteractive,	doCalculateCost, warehouseProcessListener);
		warehouseProcessListener.strategy = strategy;
		if (specIterator.hasNext())
			doProcessDocSpec(new WarehouseProcessDocumentLineDataImpl(specIterator.next(), params.getProcessDate()), strategy);
		else
			listener.completed();
	}

	public void processWarehousePlanTransaction(DocFlowPluginInvokeParams params) {
		for (DocumentSpecItem dsi : params.getSpecList())
			doProcessDocSpec(new WarehouseProcessDocumentLineDataImpl(dsi, params.getProcessDate()), new WarehouseProcessPlanStrategy(params));
	}

	@SuppressWarnings("unchecked") //$NON-NLS-1$
	public void processDisAssembleProductTransaction(DocFlowPluginInvokeParams params) {
		for (DocumentSpecItem dsi : params.getSpecList()) {
			// если тип товара спецификации комплект, то отрабатываем приход комплектующих и списание комплектов
			if (CatalogType.SET_OF_GOODS.compareTo(dsi.getDocSpec().getCatalog().getGoodType()) == 0) {
				// оприходуем комплектующие
				Set<SetOfGood> components =  dsi.getDocSpec().getCatalog().getSetOfSetOfGood();
				List<Integer> historyIds = new ArrayList<Integer>();
				for (SetOfGood component : components) {					
					WarehouseProcessDocumentLineDataImpl docLineData = new WarehouseProcessDocumentLineDataImpl(dsi, params.getProcessDate());
					docLineData.setSrcMol(null);
					docLineData.setSrcStock(null);
					docLineData.setCatalog(component.getCatalogComponent());
					docLineData.setQuantity1(docLineData.getQuantity1().multiply(component.getQuantity()));
					doProcessDocSpec(docLineData, new WarehouseProcessFactStrategy(params, false, false, DUMMY_WAREHOUSE_LISTENER));
					if (docLineData.getDocumentSpecItem().getData1() != null) {
						historyIds.add(docLineData.getDocumentSpecItem().getData1());
						docLineData.getDocumentSpecItem().setData1(null);
					}
					if (docLineData.getDocumentSpecItem().getData2() != null) {
						historyIds.add(docLineData.getDocumentSpecItem().getData2());
						docLineData.getDocumentSpecItem().setData2(null);
					}
				}
				//применяем новый способ хранения
				//храним ссылки на все истории которые были созданы
				//ВНИМАНИЕ! Данный способ не совместим с предыдущими версиями продукта
				dsi.setStateValue(historyIds.toArray(new Integer[historyIds.size()]));
				dsi.setData1(null);
				dsi.setData2(null);

				// списываем комплекты
				WarehouseProcessDocumentLineDataImpl docLineData = new WarehouseProcessDocumentLineDataImpl(dsi, params.getProcessDate());
				docLineData.setDstMol(null);
				docLineData.setDstStock(null);
				doProcessDocSpec(docLineData, new WarehouseProcessFactStrategy(params, false, false, DUMMY_WAREHOUSE_LISTENER));
			}
		}

	}

	@SuppressWarnings("unchecked") //$NON-NLS-1$
	public void processAssembleProductTransaction(DocFlowPluginInvokeParams params, boolean assembleProductKind) {
		for (DocumentSpecItem dsi : params.getSpecList()) {
			// если тип товара спецификации комплект, то отрабатываем приход комплекта и списание комплектующих
			if (CatalogType.SET_OF_GOODS.compareTo(dsi.getDocSpec().getCatalog().getGoodType()) == 0) {
				// отрабатываем приход комплекта
				WarehouseProcessDocumentLineDataImpl docLineData = new WarehouseProcessDocumentLineDataImpl(dsi, params.getProcessDate());
				docLineData.setSrcMol(null);
				docLineData.setSrcStock(null);
				doProcessDocSpec(docLineData, new WarehouseProcessFactStrategy(params, false, false, DUMMY_WAREHOUSE_LISTENER));
				Integer data1 = dsi.getData1();
				Integer data2 = dsi.getData2();
				dsi.setData1(null);
				dsi.setData2(null);
				// отрабатываем списание комплектующих со склада
				Set<SetOfGood> components =  dsi.getDocSpec().getCatalog().getSetOfSetOfGood();
				List<Integer> historyIds = new ArrayList<Integer>();
				for (SetOfGood component : components){					
					docLineData = new WarehouseProcessDocumentLineDataImpl(dsi, params.getProcessDate());
					docLineData.setDstMol(null);
					docLineData.setDstStock(null);
					docLineData.setCatalog(component.getCatalogComponent());
					docLineData.setQuantity1(docLineData.getQuantity1().multiply(component.getQuantity()));
					doProcessDocSpec(docLineData, new WarehouseProcessFactStrategy(params, false, false, DUMMY_WAREHOUSE_LISTENER));
					if (docLineData.getDocumentSpecItem().getData1() != null) {
						historyIds.add(docLineData.getDocumentSpecItem().getData1());
						docLineData.getDocumentSpecItem().setData1(null);
					}
					if (docLineData.getDocumentSpecItem().getData2() != null) {
						historyIds.add(docLineData.getDocumentSpecItem().getData2());
						docLineData.getDocumentSpecItem().setData2(null);
					}
				}
				//применяем новый способ хранения
				//храним ссылки на все истории которые были созданы
				//ВНИМАНИЕ! Данный способ не совместим с предыдущими версиями продукта
				dsi.setStateValue(historyIds.toArray(new Integer[historyIds.size()]));
				dsi.setData1(data1);
				dsi.setData2(data2);
			}
		}
	}

	public void processWarehousePlanWithdrawalTransaction(DocFlowPluginInvokeParams params) {
		for (DocumentSpecItem dsi : params.getSpecList())
			doProcessDocSpec(new WarehouseProcessDocumentLineDataImpl(dsi, params.getProcessDate()), new WarehouseProcessPlanWithdrawalStrategy(params));
	}

	public void processWarehouseReservTransaction(DocFlowPluginInvokeParams params) {
		for (DocumentSpecItem dsi : params.getSpecList())
			doProcessDocSpec(new WarehouseProcessDocumentLineDataImpl(dsi, params.getProcessDate()), new WarehouseProcessReservStrategy(params));
	}

	public void processWarehouseReservWithdrawalTransaction(DocFlowPluginInvokeParams params) {
		for (DocumentSpecItem dsi : params.getSpecList())
			doProcessDocSpec(new WarehouseProcessDocumentLineDataImpl(dsi, params.getProcessDate()), new WarehouseProcessReservWithdrawalStrategy(params));
	}

	public void processCalcPricesByWarehouseDataTransaction(DocFlowPluginInvokeParams params) {
		for (DocumentSpecItem dsi : params.getSpecList())
			doPrepareCalcPrices(dsi.getDocSpec(), params);
	}

	public void rollbackWarehouseTransaction(DocFlowPluginInvokeParams params) {
		doRollbackDocSpec(new WarehouseRollbackFactStrategy(), params);
	}

	public void rollbackAssembleProductTransaction(DocFlowPluginInvokeParams params) {
		doRollbackAssembleProductDocSpec(new WarehouseRollbackAssembleProductStrategy(), params);
	}

	public void rollbackDisAssembleProductTransaction(DocFlowPluginInvokeParams params) {
		doRollbackAssembleProductDocSpec(new WarehouseRollbackAssembleProductStrategy(), params);		
	}

	public void rollbackWarehousePlanTransaction(DocFlowPluginInvokeParams params) {
		doRollbackDocSpec(new WarehouseRollbackPlanStrategy(), params);
	}

	public void rollbackWarehousePlanWithdrawalTransaction(DocFlowPluginInvokeParams params) {
		doRollbackDocSpec(new WarehouseRollbackPlanWithdrawalStrategy(), params);
	}

	public void rollbackWarehouseReservTransaction(DocFlowPluginInvokeParams params) {
		doRollbackDocSpec(new WarehouseRollbackReservStrategy(), params);
	}

	public void rollbackWarehouseReservWithdrawalTransaction(DocFlowPluginInvokeParams params) {
		doRollbackDocSpec(new WarehouseRollbackReservWithdrawalStrategy(), params);
	}

	public void rollbackCalcPricesByWarehouseDataTransaction(DocFlowPluginInvokeParams params) {
		// stub
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.WarehouseProcessor#proccessCustomsDeclarationByStockBach(com.mg.merp.docflow.DocFlowPluginInvokeParams, com.mg.merp.warehouse.WarehouseProcessListener)
	 */
	public void proccessCustomsDeclarationByStockBach(DocFlowPluginInvokeParams params, final WarehouseProcessListener warehouseProcessListener) {
		Messages messages = Messages.getInstance();
		List<String> messageList = new ArrayList<String>();
		DocHead docHead = params.getDocument();
		List<DocumentSpecItem> documentSpecItems = params.getSpecList();
		for (DocumentSpecItem documentSpecItem : documentSpecItems) {
			DocSpec docSpec = documentSpecItem.getDocSpec();
			Catalog catalog = docSpec.getCatalog();
			StockBatch stockBatch = findStockBatch(docHead, docSpec);
			if(stockBatch != null) {
				CustomsDeclaration oldCustomsDeclaration = docSpec.getCustomsDeclaration();
				Country oldCountryOfOrigin = docSpec.getCountryOfOrigin();
				documentSpecItem.setStateValue(new CustomsDeclarationStateValuesImpl(oldCustomsDeclaration == null ? null : oldCustomsDeclaration.getId(), oldCountryOfOrigin == null ? null : oldCountryOfOrigin.getId()));
				
				if(stockBatch.getCustomsDeclaration() == null)
					messageList.add(MessageFormat.format(messages.getMessage(Messages.CUSTOMS_DECLARATION_NOT_FOUND), catalog.getFullName(), stockBatch.getNumberLot(), stockBatch.getCreateDate()));
				if(stockBatch.getCountryOfOrigin() == null)
					messageList.add(MessageFormat.format(messages.getMessage(Messages.COUNTRY_OF_ORIGIN_NOT_FOUND), catalog.getFullName(), stockBatch.getNumberLot(), stockBatch.getCreateDate()));
				
				docSpec.setCustomsDeclaration(stockBatch.getCustomsDeclaration());
				docSpec.setCountryOfOrigin(stockBatch.getCountryOfOrigin());
			} else
				messageList.add(MessageFormat.format(messages.getMessage(Messages.GOOD_IS_NOT_DISPOSED), catalog.getFullName()));
		} 
		if(!messageList.isEmpty()) {
			CustomsDeclarationNotFoundDlg dialog = (CustomsDeclarationNotFoundDlg) ApplicationDictionaryLocator.locate().getWindow(CustomsDeclarationNotFoundDlg.WINDOW_NAME);
			dialog.addCloseActionListener(new FormActionListener() {

				/* (non-Javadoc)
				 * @see com.mg.framework.api.ui.FormActionListener#actionPerformed(com.mg.framework.api.ui.FormEvent)
				 */
				public void actionPerformed(FormEvent event) {
					warehouseProcessListener.completed();
				}
			});
			dialog.execute(getMessage(messageList));
		} else
			warehouseProcessListener.completed();
	}
	
	protected String getMessage(List<String> messages) {
		StringWriter buf = new StringWriter();
		PrintWriter out = new PrintWriter(buf);
		for (String message : messages)
			out.println(message);
		return buf.toString();
	}
	
	/**
	 * Найти партию c которой был списан товар
	 * @param docHead - документ
	 * @param docSpec - позиция спецификации
	 * @return партия или <code>null</code> если не найдена
	 */
	protected StockBatch findStockBatch(DocHead docHead, DocSpec docSpec) {
		Criteria criteria = OrmTemplate.createCriteria(StockBatchHistory.class, "sbh")
				.setProjection(Projections.property("sbh.StockBatch"))
				.createAlias("sbh.StockBatch", "sb")		
				.createAlias("sb.StockCard", "sc")
				.add(Restrictions.eq("DocSpec", docSpec))
				.add(Restrictions.eq("Kind", StockBatchHistoryKind.OUT));

		if(docHead.getSrcStock() != null)
			criteria.add(Restrictions.eq("sc.Stock", docHead.getSrcStock()));
		else
			criteria.add(Restrictions.isNull("sc.Stock"));
		
		if(docHead.getSrcMol() != null)
			criteria.add(Restrictions.eq("sc.Mol", docHead.getSrcMol()));
		else
			criteria.add(Restrictions.isNull("sc.Mol"));

		List<StockBatch> stockBatchList = OrmTemplate.getInstance().findByCriteria(criteria);
		return stockBatchList.isEmpty() ? null : stockBatchList.get(0);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.WarehouseProcessor#rollbackCustomsDeclarationByStockBach(com.mg.merp.docflow.DocFlowPluginInvokeParams)
	 */
	public void rollbackCustomsDeclarationByStockBach(DocFlowPluginInvokeParams params) {
		// do nothing
	}

}
