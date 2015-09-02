/*
 * PaymentMt.java
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
package com.mg.merp.paymentalloc.support.ui;

import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.support.DocumentUtils;
import com.mg.merp.paymentalloc.PaymentServiceLocal;
import com.mg.merp.paymentalloc.SelectionRowListener;
import com.mg.merp.paymentalloc.TransactHeadServiceLocal;
import com.mg.merp.paymentalloc.TransactSpecServiceLocal;
import com.mg.merp.paymentalloc.model.DocGroup;
import com.mg.merp.paymentalloc.model.Payment;
import com.mg.merp.paymentalloc.model.TransactHead;

/**
 * Контрллер формы поддержки "Записи журнала платежей"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PaymentMt.java,v 1.8 2008/03/04 11:26:59 alikaev Exp $
 */
public class PaymentMt extends DefaultMaintenanceForm implements MasterModelListener {

	private DefaultTableController documentsHead;
	private TransactHeadServiceLocal documentsHeadService = (TransactHeadServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(TransactHeadServiceLocal.LOCAL_SERVICE_NAME);

	private DefaultTableController specDoc;
	private TransactSpecServiceLocal specDocService = (TransactSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(TransactSpecServiceLocal.LOCAL_SERVICE_NAME);

	private DefaultTableController taxDoc;

	private DocGroup docGroup;

	public PaymentMt() throws Exception {
		documentsHead = new DefaultTableController(new TransactHeadTableModel(new SelectionRowListener() {

			public void selectedRowChange(Integer primaryKey) {
				((TransactHeadSpecTableModel) specDoc.getModel()).refershTable(documentsHeadService.load(primaryKey));
				((TransactHeadSpecTaxTableModel) taxDoc.getModel()).resetTable();
			}
		}));

		specDoc = new DefaultTableController(new TransactHeadSpecTableModel(new SelectionRowListener() {

			public void selectedRowChange(Integer primaryKey) {
				((TransactHeadSpecTaxTableModel) taxDoc.getModel()).refreshTable(specDocService.load(primaryKey));
			}
		}));

		taxDoc = new DefaultTableController(new TransactHeadSpecTaxTableModel()); 
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */
	public void masterChange(ModelChangeEvent event) {

	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		super.doOnRun();
		refreshAllTables();
	}

	/**
	 * Обработчик кнопки "Расчитать курс"
	 * @param event - событие
	 */
	public void onActionChooseCurrencyRate(WidgetEvent event) {
		getPaymentService().computeCurRateAndSumNat((Payment) getEntity());
		view.flushModel();
	}

	/**
	 * Обработчик кнопки "Обновить"
	 * @param event - событие
	 */
	public void onActionRefresh(WidgetEvent event) {
		refreshAllTables();
	}

	/**
	 * Обработчик пункта КМ "Просмотреть документ"
	 * @param event - событие
	 */
	public void onActionViewDocHead(WidgetEvent event) {
		if(getEntity().getAttribute("Id") != null) { //$NON-NLS-1$
			TransactHead transactHead = documentsHeadService.load(((TransactHeadTableModel) documentsHead.getModel()).getSelectedPrimaryKey());
			if(transactHead != null) 
				viewDocument(transactHead.getDocHead());
		}
	}

	@SuppressWarnings("unchecked") //$NON-NLS-1$
	private void viewDocument(DocHead docHead) {
		if(docHead != null) 
			MaintenanceHelper.view(DocumentUtils.getDocumentService(docHead.getDocSection()), docHead.getId(), null, null);
	}

	private void refreshAllTables() {
		if(getEntity().getAttribute("Id") != null) { //$NON-NLS-1$
			((TransactHeadTableModel) documentsHead.getModel()).refershTable((Payment) getEntity(), docGroup);
			((TransactHeadSpecTableModel) specDoc.getModel()).resetTable();
			((TransactHeadSpecTaxTableModel) taxDoc.getModel()).resetTable();
		}
	}

	private PaymentServiceLocal getPaymentService() {
		return (PaymentServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PaymentServiceLocal.LOCAL_SERVICE_NAME);
	}

	/**
	 * Обработчик события просмотра документа-основания
	 * @param event - событие
	 */
	public void onActionViewBaseDocument(WidgetEvent event) {
		DocumentUtils.viewDocument(((Payment) getEntity()).getBaseDoc());
	}

	/**
	 * Обработчик просмотра/выбора контракта
	 * @param event - событие
	 */
	public void onActionViewOrChooseContract(WidgetEvent event) {
		final Payment docHead = (Payment) getEntity();
		if(docHead.getContract() != null)
			DocumentUtils.viewDocument(docHead.getContract());
		else
			DocumentUtils.chooseContract(new SearchHelpListener() {

				/* (non-Javadoc)
				 * @see com.mg.framework.api.ui.SearchHelpListener#searchCanceled(com.mg.framework.api.ui.SearchHelpEvent)
				 */
				public void searchCanceled(SearchHelpEvent event) {
					// do nothing
				}

				/* (non-Javadoc)
				 * @see com.mg.framework.api.ui.SearchHelpListener#searchPerformed(com.mg.framework.api.ui.SearchHelpEvent)
				 */
				public void searchPerformed(SearchHelpEvent event) {
					DocHead contract = (DocHead) event.getItems()[0];
					docHead.setContractType(contract.getDocType());
					docHead.setContractNumber(contract.getDocNumber().trim());
					docHead.setContractDate(contract.getDocDate());
					view.flushModel();
				}
			});
	}

}