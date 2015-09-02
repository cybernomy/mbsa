/* WarhouseProcessFactStrategy.java
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
package com.mg.merp.warehouse.generic;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mg.framework.api.math.Constants;
import com.mg.framework.api.math.RoundContext;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.MathUtils;
import com.mg.merp.baiengine.BusinessAddinEngineLocator;
import com.mg.merp.baiengine.BusinessAddinEvent;
import com.mg.merp.baiengine.BusinessAddinListener;
import com.mg.merp.baiengine.model.Repository;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;
import com.mg.merp.document.GoodsDocumentSpecification;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.support.DocumentUtils;
import com.mg.merp.reference.CurrencyServiceLocal;
import com.mg.merp.reference.model.Currency;
import com.mg.merp.warehouse.GoodsIssueStrategy;
import com.mg.merp.warehouse.StockBatchCreateStrategy;
import com.mg.merp.warehouse.WareCardServiceLocal;
import com.mg.merp.warehouse.WarehouseBatchHistoryServiceLocal;
import com.mg.merp.warehouse.WarehouseProcessDocumentLineData;
import com.mg.merp.warehouse.WarehouseProcessListener;
import com.mg.merp.warehouse.WarehouseProcessStrategy;
import com.mg.merp.warehouse.model.StockBatch;
import com.mg.merp.warehouse.model.StockBatchHistory;
import com.mg.merp.warehouse.model.StockBatchHistoryKind;
import com.mg.merp.warehouse.model.StockCard;
import com.mg.merp.warehouse.model.Warehouse;
import com.mg.merp.warehouse.support.ConfigurationHelper;
import com.mg.merp.warehouse.support.DefaultStockBatchCreateStrategy;

/**
 * Стратегия реализации этапа ДО "Отработка по складу"
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: WarehouseProcessFactStrategy.java,v 1.15 2008/06/05 12:42:15 sharapov Exp $
 */
public class WarehouseProcessFactStrategy implements WarehouseProcessStrategy {

	private DocFlowPluginInvokeParams params;

	private boolean doInteractive;

	private boolean doCalculateCost;
	
	private boolean performedSummBeg = true; 

	private WarehouseProcessListener processListener;

	public WarehouseProcessFactStrategy(DocFlowPluginInvokeParams params, boolean doInteractive, boolean doCalculateCost,
		WarehouseProcessListener batchProcessListener) {
		this.params = params;
		this.doInteractive = doInteractive;
		this.doCalculateCost = doCalculateCost;
		this.processListener = batchProcessListener;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.WarehouseProcessStrategy#process(com.mg.merp.warehouse.WarehouseProcessDocumentLineData)
	 */
	public void process(WarehouseProcessDocumentLineData docLineData) {
		doProcess(docLineData);
	}

	class ReceiptListenerImpl implements WarehouseProcessListener {
		private WarehouseProcessListener processListener;
		private WarehouseProcessDocumentLineData docLineData;
		private WarehouseProcessFactStrategy warehouseProcessFactStrategy;

		public ReceiptListenerImpl(WarehouseProcessFactStrategy warehouseProcessFactStrategy, WarehouseProcessDocumentLineData docLineData, WarehouseProcessListener batchProcessListener) {
			this.warehouseProcessFactStrategy = warehouseProcessFactStrategy;
			this.processListener = batchProcessListener;
			this.docLineData = docLineData;
		}
		
		public void completed() {
			//восстановим слушатель
			this.warehouseProcessFactStrategy.processListener = this.processListener;
			
			//Receipt
			if (docLineData.getDstStock() != null) {
				boolean storno = (MathUtils.compareToZero(docLineData.getQuantity1()) == -1);
				
				if (storno)
					doProcessIssue(docLineData);
				else
					doProcessReceipt(docLineData);
			} else
				processListener.completed();
		}

		/* (non-Javadoc)
		 * @see com.mg.merp.warehouse.WarehouseProcessListener#cancel()
		 */
		public void aborted() {
			processListener.aborted();
		}
		
	}
	
	protected void doProcess(WarehouseProcessDocumentLineData docLineData) {
		//создадим новый слушатель для дальнейшей обработки
		processListener = new ReceiptListenerImpl(this, docLineData, processListener);
		//Issue
		if (docLineData.getSrcStock() != null) {
			boolean storno = (MathUtils.compareToZero(docLineData.getQuantity1()) == -1);
			
			if (storno)
				doProcessReceipt(docLineData);
			else
				doProcessIssue(docLineData);
		} else
			processListener.completed();
	}

	protected void doProcessReceipt(WarehouseProcessDocumentLineData docLineData) {
		StockCard sc = fillStockCard(docLineData);
		// Заглушка. Пока непонятно как и где хранить стратегию
		// запрос # 3906
		createStockBatch(sc, docLineData, new DefaultStockBatchCreateStrategy(this, processListener, doInteractive), params);
	}

	class StockDisposalStrategyBusinessAddinListener implements BusinessAddinListener<GoodsIssueStrategy>{
		private WarehouseProcessDocumentLineData docLineData;
		private List<StockBatch> batches;

		StockDisposalStrategyBusinessAddinListener(WarehouseProcessDocumentLineData docLineData, List<StockBatch> batches) {
			this.docLineData = docLineData;
			this.batches = batches;
		}
		
		public void aborted(BusinessAddinEvent<GoodsIssueStrategy> event) {
			event.getResult().notifyCancel();
		}

		public void completed(BusinessAddinEvent<GoodsIssueStrategy> event) {
			event.getResult().doIssue();
			if (doCalculateCost)
				doCalculateCost(docLineData, batches);
			//уведомляем о завершении транзакции
			event.getResult().notifyComplete();
		}
	
	}

	protected void doProcessIssue(WarehouseProcessDocumentLineData docLineData) {
		GoodsIssueStrategy strategy = null;
		Warehouse wh = docLineData.getSrcStock();		
		Repository disposalStrategyBAi = wh.getDisposalStrategy();
		
		if (disposalStrategyBAi == null){
			switch (wh.getStockPolicy()) {
			case SPFIFO:
				strategy = new FIFOLIFODisposalStrategy(true, docLineData, params.getProcessDate(), this, processListener);
				break;
			case SPLIFO:
				strategy = new FIFOLIFODisposalStrategy(false, docLineData, params.getProcessDate(), this, processListener);
				break;
			case SPBATCH:
				strategy = new BatchDisposalStrategy(docLineData, params.getProcessDate(), this, processListener);
				break;
			default:
				throw new IllegalStateException("Invalid stock policy");
			}
			strategy.doIssue();
			if (doCalculateCost)
				doCalculateCost(docLineData, strategy.getBatchesByOrder());
		} else {
			BusinessAddinEngineLocator.locate().perform(disposalStrategyBAi.getCode().trim(), null, new StockDisposalStrategyBusinessAddinListener(docLineData, strategy.getBatchesByOrder()));		
		}		
	}

	@SuppressWarnings("unchecked")
	private void doCalculateCost(WarehouseProcessDocumentLineData docLineData, List<StockBatch> batches) {
		WarehouseBatchHistoryServiceLocal whbhServ = (WarehouseBatchHistoryServiceLocal) ApplicationDictionaryLocator.locate()
				.getBusinessService(WarehouseBatchHistoryServiceLocal.LOCAL_SERVICE_NAME);
		CurrencyServiceLocal curServ = (CurrencyServiceLocal) ApplicationDictionaryLocator.locate()
				.getBusinessService(CurrencyServiceLocal.LOCAL_SERVICE_NAME);
		
		RoundContext rc = new RoundContext(ConfigurationHelper.getConfiguration().getCurrencyPrec());
		
		DocHead docHead = docLineData.getDocumentSpec().getDocHead();
		GoodsDocumentSpecification specServ = DocumentUtils.getGoodsDocumentSpecificationService(docHead.getDocSection());
		PersistentObject po = specServ.load(docLineData.getDocumentSpec().getId());
		
		Map<String, Object> mae = new HashMap<String, Object>();
		mae.put("DocSpec", po);
		mae.put("Kind", StockBatchHistoryKind.OUT);
		
		BigDecimal sum = BigDecimal.ZERO;
		
		boolean getNext = true;
		Iterator<StockBatch> it = batches.listIterator();
		do {
			StockBatch stockBatch = it.next();
			mae.put("StockBatch", stockBatch);
			List<StockBatchHistory> ls = whbhServ.findByCriteria(Restrictions.allEq(mae));
			if (!ls.isEmpty()){
				StockBatchHistory history = ls.get(0);
			
				Currency batchCurrency = curServ.findByCriteria(Restrictions.eq("UpCode", stockBatch.getCurrencyCode().toUpperCase())).get(0);
				BigDecimal priceCur = curServ.conversion(docHead.getCurrency(), batchCurrency, 
						docHead.getCurrencyRateAuthority(), docHead.getCurrencyRateType(), params.getProcessDate(),
						stockBatch.getPriceCur());
			
				sum = sum.add(MathUtils.multiply(history.getQuantity(), priceCur, rc));
			} else
				getNext = false;
		} while (getNext && it.hasNext());
		sum = MathUtils.round(sum, rc);
		
		BigDecimal price1 = MathUtils.divide(sum, docLineData.getQuantity1(), rc);
		
		po.setAttribute("Price1", price1);
		//сумма проставляется автоматически
		specServ.store(po);
		//пересчёт параметра "выполнен на сумму"
		if (performedSummBeg) {
			performedSummBeg = false;
			params.setPerformedSum(sum);
		} else			
			params.setPerformedSum(params.getPerformedSum().add(sum));
	}

	/**
	 * Создание КСУ
	 * 
	 * @param docSpec
	 *            спецификация документа
	 * @return КСУ
	 */
	private StockCard fillStockCard(WarehouseProcessDocumentLineData docLineData) {
		WareCardServiceLocal service = (WareCardServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(
			WareCardServiceLocal.LOCAL_SERVICE_NAME);
		StockCard stockCard = service.findStockCard(docLineData.getDstStock(), docLineData.getDstMol(), docLineData.getCatalog(), false);
		if (stockCard == null) {
			// КСУ отсутствует
			stockCard = service.initialize();
			stockCard.setCatalog(docLineData.getCatalog());
			stockCard.setMol(docLineData.getDstMol());

			stockCard.setQuantity(docLineData.getQuantity1());
			stockCard.setQuantity2(docLineData.getQuantity2());

			stockCard.setStock(docLineData.getDstStock());

			service.create(stockCard);
		} else {
			stockCard.setQuantity(MathUtils.addNullable(stockCard.getQuantity(), docLineData.getQuantity1(), Constants.QUANTITY_ROUND_CONTEXT_EXT));
			stockCard.setQuantity2(MathUtils.addNullable(stockCard.getQuantity2(), docLineData.getQuantity2(), Constants.QUANTITY_ROUND_CONTEXT_EXT));

			service.store(stockCard);
		}
		return stockCard;
	}

	/**
	 * Создание партии
	 * 
	 * @param stockCard
	 *            КСУ
	 * @param docSpec
	 *            спецификация документа
	 * @param sbCrtStrategy
	 *            стратегия формирования партии
	 * @param params
	 *            параметры этапа "отработка по-складу"
	 */
	private void createStockBatch(StockCard stockCard, WarehouseProcessDocumentLineData docLineData, StockBatchCreateStrategy sbCrtStrategy,
		DocFlowPluginInvokeParams params) {
		sbCrtStrategy.createStockBatch(docLineData, stockCard, params);
	}

}
