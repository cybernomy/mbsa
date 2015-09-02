/* AbstractStockBatchCreateStrategy.java
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
package com.mg.merp.warehouse.generic;

import java.util.HashMap;
import java.util.Map;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.MaintenanceFormActionListener;
import com.mg.framework.api.ui.MaintenanceFormEvent;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.merp.baiengine.BusinessAddinEngineLocator;
import com.mg.merp.baiengine.BusinessAddinEvent;
import com.mg.merp.baiengine.BusinessAddinListener;
import com.mg.merp.baiengine.model.Repository;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;
import com.mg.merp.warehouse.BatchPriceStrategy;
import com.mg.merp.warehouse.BinLocationProccessStrategy;
import com.mg.merp.warehouse.SerialNumberProccessStrategy;
import com.mg.merp.warehouse.StockBatchCreateStrategy;
import com.mg.merp.warehouse.WarehouseBatchHistoryServiceLocal;
import com.mg.merp.warehouse.WarehouseBatchServiceLocal;
import com.mg.merp.warehouse.WarehouseProcessDocumentLineData;
import com.mg.merp.warehouse.WarehouseProcessListener;
import com.mg.merp.warehouse.WarehouseProcessStrategy;
import com.mg.merp.warehouse.WarehouseServiceLocal;
import com.mg.merp.warehouse.model.StockBatch;
import com.mg.merp.warehouse.model.StockBatchHistory;
import com.mg.merp.warehouse.model.StockCard;
import com.mg.merp.warehouse.model.Warehouse;
import com.mg.merp.warehouse.support.BatchPriceStrategyBusinessAddin;
import com.mg.merp.warehouse.support.DefaultBatchPriceStrategy;
import com.mg.merp.warehouse.support.DefaultBinLocationProccessStrategy;
import com.mg.merp.warehouse.support.DefaultSerialNumberProccessStrategy;

/**
 * Абстрактный класс-предок стратегий создания складских партий
 * 
 * @author Valentin A. Poroxnenko
 * @author Artem V. Sharapov
 * @version $Id: AbstractStockBatchCreateStrategy.java,v 1.9 2008/06/05 12:42:15 sharapov Exp $
 */
public abstract class AbstractStockBatchCreateStrategy implements
StockBatchCreateStrategy {
	protected WarehouseProcessListener listener;
	protected boolean doInteractive;

	public AbstractStockBatchCreateStrategy(WarehouseProcessStrategy processStrategy,
			WarehouseProcessListener listener, boolean doInteractive) {
		this.listener = listener;
		this.doInteractive = doInteractive;
	}

	/**
	 * Создание партии
	 * 
	 * @param docSpec
	 *            спецификация документа
	 * @param stockCard
	 *            КСУ
	 * @param params
	 *            параметры этапа "отработка по складу"
	 * @param strategy
	 *            стратегия рассчёта цены партии
	 * @return складская партия
	 */
	protected abstract StockBatch doFillStockBatch(WarehouseProcessDocumentLineData docLineData,
			StockCard stockCard, DocFlowPluginInvokeParams params,
			BatchPriceStrategy strategy);

	/**
	 * Создание истории партии
	 * 
	 * @param docSpec
	 *            спецификация документа
	 * @param stockBatch
	 *            КСУ
	 * @param params
	 *            параметры этапа "отработка по складу"
	 * @return история партии
	 */
	protected abstract StockBatchHistory doFillStockBatchHistory(WarehouseProcessDocumentLineData docLineData,
			StockBatch stockBatch, DocFlowPluginInvokeParams params);

	// TODO: на данный момент bai просто возвращает экземпляр класса
	// стратегии,
	// возможно, стратегия будет формироваться динамически, тогда КСУ и
	// спецификацию
	// надо будет передавать в bai.
	class CalcPriceBAiListener implements BusinessAddinListener<BatchPriceStrategy> {
		private WarehouseProcessDocumentLineData docLineData;
		private StockCard stockCard;
		private DocFlowPluginInvokeParams params;

		CalcPriceBAiListener(WarehouseProcessDocumentLineData docLineData, StockCard stockCard,
				DocFlowPluginInvokeParams params) {
			this.docLineData = docLineData;
			this.stockCard = stockCard;
			this.params = params;
		}

		public void aborted(BusinessAddinEvent<BatchPriceStrategy> event) {
			listener.aborted();
		}

		public void completed(BusinessAddinEvent<BatchPriceStrategy> event) {
			makeBatchAndHistory(docLineData, stockCard, params, event.getResult());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mg.merp.warehouse.StockBatchCreateStrategy#findStockBatch(com.mg.merp.document.model.DocSpec,
	 *      com.mg.merp.warehouse.model.StockCard)
	 */
	public void createStockBatch(WarehouseProcessDocumentLineData docLineData, StockCard stockCard,
			DocFlowPluginInvokeParams params){

		WarehouseServiceLocal whServ = (WarehouseServiceLocal) ApplicationDictionaryLocator.locate()
		.getBusinessService(WarehouseServiceLocal.LOCAL_SERVICE_NAME);

		Repository repository = whServ.load(stockCard.getStock().getId()).getBatchPriceBAi();

		String baiPriceCode = repository != null ? repository.getCode().trim() : null;

		if (baiPriceCode != null) {
			Map<String, Object> baiParams = new HashMap<String, Object>();
			baiParams.put(BatchPriceStrategyBusinessAddin.DOC_LINE_DATA_PARAM, docLineData);
			baiParams.put(BatchPriceStrategyBusinessAddin.STOCK_CARD_PARAM, stockCard);
			BusinessAddinEngineLocator.locate().perform(baiPriceCode, baiParams, new CalcPriceBAiListener(docLineData, stockCard, params));
		}
		else 
			makeBatchAndHistory(docLineData, stockCard, params, new DefaultBatchPriceStrategy());
	}

	/**
	 * Создание истории и партии
	 * 
	 * @param docSpec
	 *            спецификация документа
	 * @param stockBatch
	 *            КСУ
	 * @param params
	 *            параметры этапа "отработка по складу"
	 */
	private void makeBatchAndHistory(final WarehouseProcessDocumentLineData docLineData, StockCard stockCard,
			DocFlowPluginInvokeParams params, BatchPriceStrategy strategy) {
		WarehouseBatchHistoryServiceLocal sbhServ = (WarehouseBatchHistoryServiceLocal) ApplicationDictionaryLocator.locate()
		.getBusinessService(WarehouseBatchHistoryServiceLocal.LOCAL_SERVICE_NAME);
		final StockBatch stockBatch = doFillStockBatch(docLineData, stockCard, params, strategy);
		docLineData.getDocumentSpecItem().setData1(sbhServ.create(doFillStockBatchHistory(docLineData, stockBatch, params)));

		//при создании в интерактивном режиме необходимо показать партию пользователю
		if (!doInteractive) {
			doExtraProccessing(docLineData, stockBatch);
		}
		else {
			WarehouseBatchServiceLocal sbServ = (WarehouseBatchServiceLocal) ApplicationDictionaryLocator.locate()
			.getBusinessService(WarehouseBatchServiceLocal.LOCAL_SERVICE_NAME);
			MaintenanceHelper.edit(sbServ, (PersistentObject) stockBatch, null, new MaintenanceFormActionListener() {

				public void canceled(MaintenanceFormEvent event) {
					listener.aborted();
				}

				public void performed(MaintenanceFormEvent event) {
					doExtraProccessing(docLineData, stockBatch);
				}
			});
		}
	}

	/**
	 * Выполнить дополнительную обработку
	 * @param docLineData - данные по спецификации
	 * @param stockBatch - партия
	 */
	private void doExtraProccessing(final WarehouseProcessDocumentLineData docLineData, final StockBatch stockBatch) {
		ExtraProccessingListener extraProccessingListener = new ExtraProccessingListener(docLineData, stockBatch, listener);
		if (docLineData.getCatalog().getUseSerialNum()) {
			SerialNumberProccessStrategy serialNumberProccessStrategy = new DefaultSerialNumberProccessStrategy();
			serialNumberProccessStrategy.proccessOnReceipt(docLineData, stockBatch, extraProccessingListener);
		} else
			extraProccessingListener.completed();
	}
	
	/**
	 * Выполнить обработку секций хранения
	 * @param docLineData - данные по спецификации
	 * @param stockBatch - партия
	 * @param listener - слушатель
	 */
	private void proccessBinLocation(final WarehouseProcessDocumentLineData docLineData, final StockBatch stockBatch, WarehouseProcessListener listener) {
		Warehouse warehouse = docLineData.getDstStock();
		if(warehouse == null || !warehouse.getUseBinLocation())
			listener.completed();
		else {
			BinLocationProccessStrategy binLocationProccessStrategy = new DefaultBinLocationProccessStrategy();
			binLocationProccessStrategy.proccessOnReceipt(docLineData, stockBatch, listener);
		}
	}
	
	private class ExtraProccessingListener implements WarehouseProcessListener {

		private WarehouseProcessListener proccessingListener;
		private WarehouseProcessDocumentLineData docLineData;
		private StockBatch stockBatch;
		
		public ExtraProccessingListener(WarehouseProcessDocumentLineData docLineData, StockBatch stockBatch, WarehouseProcessListener proccessingListener) {
			this.docLineData = docLineData;
			this.stockBatch = stockBatch;
			this.proccessingListener = proccessingListener;
		}
		
		/* (non-Javadoc)
		 * @see com.mg.merp.warehouse.WarehouseProcessListener#aborted()
		 */
		public void aborted() {
			proccessingListener.aborted();
		}

		/* (non-Javadoc)
		 * @see com.mg.merp.warehouse.WarehouseProcessListener#completed()
		 */
		public void completed() {
			proccessBinLocation(docLineData, stockBatch, listener);
		}
	}
		
}
