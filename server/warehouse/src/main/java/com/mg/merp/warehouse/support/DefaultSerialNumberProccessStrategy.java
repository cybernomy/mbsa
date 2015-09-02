/*
 * DefaultSerialNumberProccessStrategy.java
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
package com.mg.merp.warehouse.support;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mg.framework.api.orm.FlushMode;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.orm.ResultTransformer;
import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.document.DocumentSpecSerialNumServiceLocal;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.document.model.DocumentSpecSerialNum;
import com.mg.merp.warehouse.SerialNumberProccessStrategy;
import com.mg.merp.warehouse.WarehouseProcessDocumentLineData;
import com.mg.merp.warehouse.WarehouseProcessListener;
import com.mg.merp.warehouse.model.SerialNum;
import com.mg.merp.warehouse.model.StockBatch;
import com.mg.merp.warehouse.model.StockBatchHistory;
import com.mg.merp.warehouse.support.ui.InputSerialNumberDlg;
import com.mg.merp.warehouse.support.ui.SelectSerialNumberDlg;
import com.mg.merp.warehouse.support.ui.SerialNumberModelItem;

/**
 * ���������� ����������� ��������� ���������/������ �������� �������
 * 
 * @author Artem V. Sharapov
 * @version $Id: DefaultSerialNumberProccessStrategy.java,v 1.2 2008/07/15 08:24:22 safonov Exp $
 */
public class DefaultSerialNumberProccessStrategy implements SerialNumberProccessStrategy {

	private final String INPUT_DIALOG_NAME = "com.mg.merp.warehouse.InputSerialNumberDlg"; //$NON-NLS-1$
	private final String SELECT_DIALOG_NAME = "com.mg.merp.warehouse.SelectSerialNumberDlg"; //$NON-NLS-1$
	private final String DELETE_DOC_SPEC_SERIAL_NUMBERS_QUERY_NAME = "Warehouse.DefaultSerialNumberProccessStrategy.deleteDocumentLineSerialNumbers"; //$NON-NLS-1$
	private DocumentSpecSerialNumServiceLocal documentSpecSerialNumberService = (DocumentSpecSerialNumServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(DocumentSpecSerialNumServiceLocal.SERVICE_NAME);

	private PersistentManager persistentManager = ServerUtils.getPersistentManager();
	private OrmTemplate ormTemplate = OrmTemplate.getInstance();


	public DefaultSerialNumberProccessStrategy() {
	}
	
	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.SerialNumberProccessStrategy#proccessOnReceipt(com.mg.merp.warehouse.WarehouseProcessDocumentLineData, com.mg.merp.warehouse.model.StockBatch, com.mg.merp.warehouse.WarehouseProcessListener)
	 */
	public void proccessOnReceipt(WarehouseProcessDocumentLineData docLineData, StockBatch stockBatch, WarehouseProcessListener processListener) {
		doProccessOnReceipt(docLineData, stockBatch, processListener);
	}

	/**
	 * ��������� ��������� �������� ������� ��� ������������ �� �����
	 * @param docSpec - ������� ������������
	 * @param stockBatch - ������
	 * @param processListener - ��������� ���������� ����������
	 */
	protected void doProccessOnReceipt(WarehouseProcessDocumentLineData docLineData, final StockBatch stockBatch, final WarehouseProcessListener processListener) {
		final DocSpec docSpec = docLineData.getDocumentSpec();
		// ���� ��� ������ � ������� ������������ �� ������� ������������� �������� �������, �� ��������� ��������� 
		if(!docSpec.getCatalog().getUseSerialNum())
			processListener.completed();
		else {
			final List<String> avalibleSerialNumberList = getDocSpecSerialNumberList(docSpec);
			Integer necessarySerialNumbersQuantity = 0;
			if(docSpec.getQuantity() != null)
				necessarySerialNumbersQuantity = docSpec.getQuantity().intValue();
			necessarySerialNumbersQuantity = necessarySerialNumbersQuantity - avalibleSerialNumberList.size(); 

			// ���� ��� ������� ������������ ������������ �� ��� �������� ������, �� ��������� ������ ����� �������� �������
			if(necessarySerialNumbersQuantity > 0) {
				final InputSerialNumberDlg inputSerialNumberDialog = (InputSerialNumberDlg) ApplicationDictionaryLocator.locate().getWindow(INPUT_DIALOG_NAME);
				inputSerialNumberDialog.addOkActionListener(new FormActionListener() {

					/* (non-Javadoc)
					 * @see com.mg.framework.api.ui.FormActionListener#actionPerformed(com.mg.framework.api.ui.FormEvent)
					 */
					public void actionPerformed(FormEvent event) {
						List<String> numberList = inputSerialNumberDialog.getSerialNumbers();
						numberList.addAll(avalibleSerialNumberList);
						createSerialNumbers(initializeSerialNumbers(numberList, stockBatch, docSpec.getId()));
						processListener.completed();
					}
				});
				inputSerialNumberDialog.addCancelActionListener(new FormActionListener() {

					/* (non-Javadoc)
					 * @see com.mg.framework.api.ui.FormActionListener#actionPerformed(com.mg.framework.api.ui.FormEvent)
					 */
					public void actionPerformed(FormEvent event) {
						processListener.aborted();
					}
				});
				inputSerialNumberDialog.execute(necessarySerialNumbersQuantity, docSpec.getCatalog().getCode(), docSpec.getCatalog().getFullName(), stockBatch.getNumberLot(), stockBatch.getVendorLot());
			} else { // ���� ��� ������� ������������ ������������ ��� �������� ������, �� ������� ������ �������� �������, ������������� ������� ������������
				createSerialNumbers(initializeSerialNumbers(avalibleSerialNumberList, stockBatch, docSpec.getId()));
				processListener.completed();
			}
		}
	}

	/**
	 * ���������������� ������ �������� �������
	 * @param numberList - ������ �������� �������
	 * @param stockBatch - ��������� ������
	 * @param incomeDocSpecId - ������������ ������� ������������ ��������� �������
	 * @return ������ �������� �������
	 */
	private List<SerialNum> initializeSerialNumbers(List<String> numberList, StockBatch stockBatch, Integer incomeDocSpecId) {
		List<SerialNum> serialNumbers = new ArrayList<SerialNum>();
		for (String number : numberList) {
			SerialNum serialNumber = new SerialNum();
			serialNumber.setBatch(stockBatch);
			serialNumber.setSerialNum(number);
			serialNumber.setInComeDocSpecId(incomeDocSpecId);

			serialNumbers.add(serialNumber);
		}
		return serialNumbers;
	}

	/**
	 * ������� �������� ������
	 * @param serialNumbers - ������ �������� �������
	 */
	private void createSerialNumbers(List<SerialNum> serialNumbers) {
		for (SerialNum serialNumber : serialNumbers)
			persistentManager.persist(serialNumber);
	}

	/**
	 * �������� ������ �������� �������, ������������� ������� ������������
	 * @param docSpec - ������� ������������
	 * @return ������ �������� �������, ������������� ������� ������������
	 */
	private List<String> getDocSpecSerialNumberList(DocSpec docSpec) {
		return ormTemplate.findByCriteria(OrmTemplate.createCriteria(DocumentSpecSerialNum.class)
				.setProjection(Projections.property("SerialNum")) //$NON-NLS-1$
				.add(Restrictions.eq("DocSpec", docSpec)) //$NON-NLS-1$
				.setFlushMode(FlushMode.MANUAL));
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.SerialNumberProccessStrategy#proccessOnIssue(com.mg.merp.warehouse.WarehouseProcessDocumentLineData, java.util.List, com.mg.merp.warehouse.WarehouseProcessListener)
	 */
	public void proccessOnIssue(WarehouseProcessDocumentLineData docLineData, List<StockBatch> stockBatches, WarehouseProcessListener processListener) {
		doProccessOnIssue(docLineData, stockBatches, processListener);
	}

	/**
	 * ��������� ��������� �������� ������� ��� �������� ������ � ������
	 * @param docLineData - ������ �� ������������ ��� ���������
	 * @param stockBatches - ������ ������ ���������� ��������
	 * @param processListener - ��������� ���������� ����������
	 */
	protected void doProccessOnIssue(WarehouseProcessDocumentLineData docLineData, List<StockBatch> stockBatches, final WarehouseProcessListener processListener) {
		final DocSpec docSpec = docLineData.getDocumentSpec();
		// ���� ��� ������ � ������� ������������ �� ������� ������������� �������� �������, �� ��������� ��������� 
		if(!docSpec.getCatalog().getUseSerialNum() || docSpec.getQuantity() == null || MathUtils.compareToZero(docSpec.getQuantity()) == 0)
			processListener.completed();
		else {
			ormTemplate.bulkUpdateByNamedQuery(DELETE_DOC_SPEC_SERIAL_NUMBERS_QUERY_NAME, "docSpec", docSpec); //$NON-NLS-1$
			iterateOverStockBatches(stockBatches.iterator(), docSpec, processListener);
		}
	}

	/**
	 * ��������� ������ �� ������� � ��������� �������� �������
	 * @param stockBatchesIterator - �������� ������
	 * @param docSpec - ������� ������������ ���������
	 * @param processListener - ��������� ���������� ����������
	 */
	private void iterateOverStockBatches(final Iterator<StockBatch> stockBatchesIterator, final DocSpec docSpec, final WarehouseProcessListener processListener) {
		if(!stockBatchesIterator.hasNext())
			processListener.completed();
		else {
			final StockBatch stockBatch = stockBatchesIterator.next();
			if(MathUtils.compareToZero(stockBatch.getEndQuan()) == 0) {
				copySerialNumbersFromWarehouseToDocSpec(getWarehouseSerialNumbersList(stockBatch), docSpec, stockBatch);
				iterateOverStockBatches(stockBatchesIterator, docSpec, processListener);
			} else {
				final SelectSerialNumberDlg selectSerialNumberDialog = (SelectSerialNumberDlg) ApplicationDictionaryLocator.locate().getWindow(SELECT_DIALOG_NAME);
				selectSerialNumberDialog.addOkActionListener(new FormActionListener() {

					/* (non-Javadoc)
					 * @see com.mg.framework.api.ui.FormActionListener#actionPerformed(com.mg.framework.api.ui.FormEvent)
					 */
					public void actionPerformed(FormEvent event) {
						copySerialNumbersFromWarehouseToDocSpec(selectSerialNumberDialog.getSelectedSerialNumbers(), docSpec, stockBatch);
						iterateOverStockBatches(stockBatchesIterator, docSpec, processListener);
					}
				});
				selectSerialNumberDialog.addCancelActionListener(new FormActionListener() {

					/* (non-Javadoc)
					 * @see com.mg.framework.api.ui.FormActionListener#actionPerformed(com.mg.framework.api.ui.FormEvent)
					 */
					public void actionPerformed(FormEvent event) {
						processListener.aborted();
					}
				});
				Integer necessarySerialNumbersQuantity = stockBatch.getEndQuan().subtract(stockBatch.getBeginQuan()).intValue();
				selectSerialNumberDialog.execute(Math.abs(necessarySerialNumbersQuantity), getWarehouseSerialNumberModelItemList(stockBatch), docSpec.getCatalog().getCode(), docSpec.getCatalog().getFullName(), stockBatch.getNumberLot(), stockBatch.getVendorLot());
			}
		}
	}

	/**
	 * ��������� ����������� ���.������� �� ������ � ������� ������������ ���������
	 * @param numberList - ������ �������
	 * @param docSpec - ������� ������������ ���������
	 */
	private void copySerialNumbersFromWarehouseToDocSpec(List<String> numberList, DocSpec docSpec, StockBatch stockBatch) {
		List<DocumentSpecSerialNum> documentSpecSerialNumberList = new ArrayList<DocumentSpecSerialNum>();
		for (String number : numberList)
			documentSpecSerialNumberList.add(initializeDocumentSpecSerialNumber(docSpec, number));
		createDocumentSpecSerialNumbers(documentSpecSerialNumberList);
		
		ormTemplate.bulkUpdateByNamedQuery("Warehouse.DefaultSerialNumberProccessStrategy.updateWarehouseSerialNumbers", //$NON-NLS-1$
				new String[] {"docSpecId", "numbers", "stockBatch"}, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				new Object[] {docSpec.getId(), numberList, stockBatch});
	}

	/**
	 * ���������������� ���.����� ������� ������������ ���������
	 * @param docSpec - ������� ������������ ���������
	 * @param number - �����
	 * @param documentSpecSerialNumberService - ������ ���.������ ������� ������������ ���������
	 * @return ���.����� ������� ������������ ���������
	 */
	private DocumentSpecSerialNum initializeDocumentSpecSerialNumber(DocSpec docSpec, String number) {
		DocumentSpecSerialNum documentSpecSerialNumber = documentSpecSerialNumberService.initialize();
		documentSpecSerialNumber.setDocSpec(docSpec);
		documentSpecSerialNumber.setSerialNum(number);
		return documentSpecSerialNumber;
	}

	/**
	 * ������� ���.������ ������� ������������ ���������
	 * @param documentSpecSerialNumberList - ������ ���.������� ������� ������������ ���������
	 * @param documentSpecSerialNumberService - ������ ���.������ ������� ������������ ���������
	 */
	private void createDocumentSpecSerialNumbers(List<DocumentSpecSerialNum> documentSpecSerialNumberList) {
		for (DocumentSpecSerialNum documentSpecSerialNumber : documentSpecSerialNumberList)
			documentSpecSerialNumberService.create(documentSpecSerialNumber);
		persistentManager.flush();
	}

	/**
	 * �������� ������ ��������� �������� ������� ������ ��� ����������� � ������� ������
	 * @param stockBatch - ������
	 * @return ������ ��������� �������� ������� ������
	 */
	private List<SerialNumberModelItem> getWarehouseSerialNumberModelItemList(StockBatch stockBatch) {
		return ormTemplate.findByCriteria(OrmTemplate.createCriteria(SerialNum.class)
				.setProjection(Projections.property("SerialNum")) //$NON-NLS-1$
				.add(Restrictions.eq("Batch", stockBatch)) //$NON-NLS-1$
				.setFlushMode(FlushMode.MANUAL)
				.setResultTransformer(new ResultTransformer<SerialNumberModelItem>() {

					/* (non-Javadoc)
					 * @see com.mg.framework.api.orm.ResultTransformer#transformTuple(java.lang.Object[], java.lang.String[])
					 */
					public SerialNumberModelItem transformTuple(Object[] tuple, String[] aliases) {
						return new SerialNumberModelItem((String) tuple[0]);
					}
				}));
	}

	/**
	 * �������� ������ ��������� �������� ������� ������
	 * @param stockBatch - ������
	 * @return ������ ��������� �������� ������� ������
	 */
	private List<String> getWarehouseSerialNumbersList(StockBatch stockBatch) {
		return ormTemplate.findByCriteria(OrmTemplate.createCriteria(SerialNum.class)
				.setProjection(Projections.property("SerialNum")) //$NON-NLS-1$
				.add(Restrictions.eq("Batch", stockBatch)) //$NON-NLS-1$
				.setFlushMode(FlushMode.MANUAL));
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.SerialNumberProccessStrategy#rollbackOnReceipt(com.mg.merp.warehouse.model.StockBatchHistory)
	 */
	public void rollbackOnReceipt(StockBatchHistory history) {
		doRollbackOnReceipt(history);
	}

	/**
	 * ��������� ����� ��������� �������� ������� ��� ������������ ������ �� �����
	 * @param history - �������
	 */
	protected void doRollbackOnReceipt(StockBatchHistory history) {
		ormTemplate.bulkUpdateByNamedQuery("Warehouse.DefaultSerialNumberProccessStrategy.deleteReceiptWarehouseSerialNumbers", //$NON-NLS-1$
				new String[] {"stockBatch", "docSpecId"}, //$NON-NLS-1$ //$NON-NLS-2$
				new Object[] {history.getStockBatch(), history.getDocSpec().getId()});
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.SerialNumberProccessStrategy#rollbackOnIssue(com.mg.merp.warehouse.model.StockBatchHistory)
	 */
	public void rollbackOnIssue(StockBatchHistory history) {
		doRollbackOnIssue(history);
	}

	/**
	 * ��������� ����� ��������� �������� ������� ��� �������� ������ �� ������
	 * @param history - �������
	 */
	protected void doRollbackOnIssue(StockBatchHistory history) {
		ormTemplate.bulkUpdateByNamedQuery("Warehouse.DefaultSerialNumberProccessStrategy.updateIssueWarehouseSerialNumbers", //$NON-NLS-1$
				new String[] {"stockBatch", "docSpecId"}, //$NON-NLS-1$ //$NON-NLS-2$
				new Object[] {history.getStockBatch(), history.getDocSpec().getId()});
	}

}
