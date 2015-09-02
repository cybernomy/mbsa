/* AbstractGoodsIssueStrategy.java
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.MathUtils;
import com.mg.merp.warehouse.BinLocationProccessStrategy;
import com.mg.merp.warehouse.GoodsIssueStrategy;
import com.mg.merp.warehouse.NotEnoughDisposalPositionsException;
import com.mg.merp.warehouse.SerialNumberProccessStrategy;
import com.mg.merp.warehouse.WareCardServiceLocal;
import com.mg.merp.warehouse.WarehouseBatchHistoryServiceLocal;
import com.mg.merp.warehouse.WarehouseBatchServiceLocal;
import com.mg.merp.warehouse.WarehouseProcessDocumentLineData;
import com.mg.merp.warehouse.WarehouseProcessListener;
import com.mg.merp.warehouse.WarehouseProcessStrategy;
import com.mg.merp.warehouse.model.StockBatch;
import com.mg.merp.warehouse.model.StockBatchHistory;
import com.mg.merp.warehouse.model.StockBatchHistoryKind;
import com.mg.merp.warehouse.model.StockCard;
import com.mg.merp.warehouse.model.Warehouse;
import com.mg.merp.warehouse.support.DefaultBinLocationProccessStrategy;
import com.mg.merp.warehouse.support.DefaultSerialNumberProccessStrategy;

/**
 * Абстрактный класс-предок для классов-стратегий списания со склада
 * 
 * @author Valentin A. Poroxnenko
 * @author Artem V. Sharapov
 * @version $Id: AbstractGoodsIssueStrategy.java,v 1.4 2008/05/30 12:52:55 sharapov Exp $
 */
public abstract class AbstractGoodsIssueStrategy implements GoodsIssueStrategy {
	protected StockCard stockCard;
	protected WarehouseProcessDocumentLineData docLineData;
	protected Date processOnDate;
	protected List<StockBatch> availableBatches;
	protected BigDecimal availableOnStock;
	private WarehouseProcessListener processListener;
	
	/**
	 * Партии, с которых производится списывание товара 
	 */
	protected List<StockBatch> disposedBatches = new ArrayList<StockBatch>();

	public AbstractGoodsIssueStrategy(WarehouseProcessDocumentLineData docLineData, Date processOnDate, WarehouseProcessStrategy processStrategy,
			WarehouseProcessListener batchProcessListener) {
		this.docLineData = docLineData;
		this.processOnDate = processOnDate;
		WareCardServiceLocal whbServ = (WareCardServiceLocal) ApplicationDictionaryLocator.locate()
				.getBusinessService(WareCardServiceLocal.LOCAL_SERVICE_NAME);
		this.stockCard = whbServ.findStockCard(docLineData.getSrcStock(), docLineData.getSrcMol(), docLineData.getCatalog(), false);
		this.processListener = batchProcessListener;
		doPrepare();
	}

	/**
	 * Подготовка предварительных данных для стратегии списания
	 */
	private void doPrepare() {
		if (stockCard == null)
			throw new NotEnoughDisposalPositionsException(docLineData.getCatalog().getCode().trim());
		WarehouseBatchServiceLocal whbServ = (WarehouseBatchServiceLocal) ApplicationDictionaryLocator
				.locate().getBusinessService(WarehouseBatchServiceLocal.LOCAL_SERVICE_NAME);
		availableBatches = whbServ.findByCriteria(Restrictions.conjunction(
				Restrictions.eq("StockCard", stockCard),
				Restrictions.le("CreateDate", processOnDate),
				Restrictions.gt("EndQuan", BigDecimal.ZERO)));
		availableOnStock = BigDecimal.ZERO;
		Iterator<StockBatch> it = availableBatches.listIterator();
		while (it.hasNext()	&& (availableOnStock.compareTo(docLineData.getQuantity1())) < 0) {
			availableOnStock = availableOnStock.add(it.next().getEndQuan());
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.GoodsIssueStrategy#notifyComplete()
	 */
	public void notifyComplete() {
		doExtraProccessing();
		doOnComplete();
	}
	
	/**
	 * Выполнить дополнительную обработку
	 */
	private void doExtraProccessing() {
		ExtraProccessingListener extraProccessingListener = new ExtraProccessingListener(processListener);
		if (docLineData.getDocumentSpec().getCatalog().getUseSerialNum() && MathUtils.compareToZero(docLineData.getQuantity1()) == 1) { // и НЕ storno!
			SerialNumberProccessStrategy serialNumberProccessStrategy = new DefaultSerialNumberProccessStrategy();
			serialNumberProccessStrategy.proccessOnIssue(docLineData, disposedBatches, extraProccessingListener);
		} else if (processListener != null)
			extraProccessingListener.completed();
	}
	
	/**
	 * Слушатель дополнительной обработки
	 */
	private class ExtraProccessingListener implements WarehouseProcessListener {

		private WarehouseProcessListener proccessingListener;
		
		public ExtraProccessingListener(WarehouseProcessListener proccessingListener) {
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
			proccessBinLocation(docLineData, disposedBatches, proccessingListener);
		}
	}
	
	/**
	 * Выполнть обработку секций хранения при списании товара со склада
	 * @param docLineData - данные по спецификации для отработки
	 * @param stockBatches - список партий подлежащих списанию
	 * @param listener - слушатель
	 */
	private void proccessBinLocation(final WarehouseProcessDocumentLineData docLineData, List<StockBatch> stockBatches, WarehouseProcessListener listener) {
		Warehouse warehouse = docLineData.getSrcStock();
		if(warehouse == null || !warehouse.getUseBinLocation())
			listener.completed();
		else {
			BinLocationProccessStrategy binLocationProccessStrategy = new DefaultBinLocationProccessStrategy();
			binLocationProccessStrategy.proccessOnIssue(docLineData, stockBatches, listener);
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.GoodsIssueStrategy#notifyCancel()
	 */
	public void notifyCancel() {
		doOnCancel();
		if (processListener != null)
			processListener.aborted();
	}

	/**
	 * реализация завершения отпуска
	 */
	protected void doOnComplete() {
	}

	/**
	 * реализация отмены отпуска
	 */
	protected void doOnCancel() {
	}

	/**
	 * Метод, изменяющий количество позиций в КСУ при списании
	 */
	protected void changeWareCard() {
		stockCard.setQuantity(stockCard.getQuantity().subtract(docLineData.getQuantity1()));
		BigDecimal quan2 = docLineData.getQuantity2();
		if (quan2 == null)
			quan2 = BigDecimal.ZERO;
		if (stockCard.getQuantity2() != null)
			stockCard.setQuantity2(stockCard.getQuantity2().subtract(quan2));
		WareCardServiceLocal wcServ = (WareCardServiceLocal) ApplicationDictionaryLocator.locate()
				.getBusinessService(WareCardServiceLocal.LOCAL_SERVICE_NAME);
		wcServ.store(stockCard);
	}

	/**
	 * Метод, создающий историю партии
	 * 
	 * @param sb 
	 * 			Складская партия
	 * 
	 * @param quan1 
	 * 			позиций в первой ЕИ
	 * 
	 * @param quan2 
	 * 			позиций во второй ЕИ
	 * 
	 * @param prevHist 
	 * 			предыдущая история
	 * 
	 * @return созданная история партии
	 */
	protected StockBatchHistory createHistory(StockBatch sb, BigDecimal quan1,
			BigDecimal quan2, StockBatchHistory prevHist) {
		// history
		StockBatchHistory history = new StockBatchHistory();
		history.setDocHead(docLineData.getDocumentSpec().getDocHead());
		history.setDocSpec(docLineData.getDocumentSpec());
		history.setStockBatch(sb);
		history.setDateTime(DateTimeUtils.nowTimestamp());
		// расход
		history.setKind(StockBatchHistoryKind.OUT);
		history.setQuantity(quan1);
		history.setProcessDate(processOnDate);
		history.setQuantity2(quan2);
		history.setStockBatchHistory(prevHist);

		WarehouseBatchHistoryServiceLocal sbhServ = (WarehouseBatchHistoryServiceLocal) ApplicationDictionaryLocator.locate()
				.getBusinessService(WarehouseBatchHistoryServiceLocal.LOCAL_SERVICE_NAME);
		Integer prevId = sbhServ.create(history);
		return sbhServ.load(prevId);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.GoodsIssueStrategy#doIssue()
	 */
	public void doIssue(){
		if (availableOnStock.compareTo(docLineData.getQuantity1()) == -1)
			throw new NotEnoughDisposalPositionsException(docLineData.getCatalog().getCode().trim());
		internalIssue();
	}
	
	/**
	 * Реализация отпуска со склада со склада
	 */
	protected abstract void internalIssue();
	
	/**
	 * получить объект сравнения порядка партий в списке
	 * 
	 * @return объект сравнения порядка партий в списке
	 */
	protected abstract Comparator<StockBatch> getComparator();
	
	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.GoodsIssueStrategy#getBatchesByOrder()
	 */
	public List<StockBatch> getBatchesByOrder() {
		Collections.sort(availableBatches, getComparator());
		return availableBatches;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.GoodsIssueStrategy#getAvailable()
	 */
	public BigDecimal getAvailable() {
		return availableOnStock;
	}

}
