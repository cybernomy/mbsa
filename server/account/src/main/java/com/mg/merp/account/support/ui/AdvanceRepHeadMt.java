/*
 * AdvanceRepHeadMt.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 *  Millennium Business Suite Anywhere System.
 *
 */
package com.mg.merp.account.support.ui;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.MathUtils;
import com.mg.merp.account.AdvanceRepHeadResult;
import com.mg.merp.account.AdvanceRepHeadServiceLocal;
import com.mg.merp.account.AdvanceRepSpecServiceLocal;
import com.mg.merp.account.model.AdvanceRepHead;
import com.mg.merp.account.model.AdvanceRepSpec;
import com.mg.merp.account.support.Messages;
import com.mg.merp.document.generic.ui.GoodsDocSpecMaintenanceEJBQLTableModel;
import com.mg.merp.document.generic.ui.GoodsDocumentMaintenanceForm;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.support.DocumentUtils;
import com.mg.merp.reference.support.ui.ContractorSearchForm;

/**
 * Контроллер формы поддержки авансовых отчетов
 * 
 * @author Julia 'Jetta' Konyashkina
 * @author Konstantin S. Alikaev
 * @version $Id: AdvanceRepHeadMt.java,v 1.12 2008/12/25 10:12:32 safonov Exp $
 */
public class AdvanceRepHeadMt extends GoodsDocumentMaintenanceForm {
	private AdvanceRepHeadServiceLocal service = null;

	public AdvanceRepHeadMt() throws Exception {
		super();
		contractorFromKinds = new String[] {ContractorSearchForm.CONTRACTOR_EMPLOYEE};
		contractorToKinds = new String[] {ContractorSearchForm.CONTRACTOR_PARTNER, ContractorSearchForm.CONTRACTOR_ORGUNIT};

		specService = ((AdvanceRepSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/AdvanceRepSpec")); //$NON-NLS-1$

		spec.initController(specService, new GoodsDocSpecMaintenanceEJBQLTableModel() {			

			/* (non-Javadoc)
			 * @see com.mg.merp.warehouse.support.ui.GoodsDocMaintenanceEJBQLTableModel#getDefaultFieldDefsSet()
			 */			
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = new LinkedHashSet<TableEJBQLFieldDef>();	
				result.add(new TableEJBQLFieldDef(AdvanceRepSpec.class, "Id", "ds.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(AdvanceRepSpec.class, "Catalog.Code", "ds.Catalog.Code", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(AdvanceRepSpec.class, "Catalog.FullName", "ds.Catalog.FullName", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(AdvanceRepSpec.class, "Catalog.Measure1", "meas1.Code", "left join ds.Catalog.Measure1 as meas1", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(AdvanceRepSpec.class, "Quantity", "ds.Quantity", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(AdvanceRepSpec.class, "Catalog.Measure2", "meas2.Code", "left join ds.Catalog.Measure2 as meas2", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(AdvanceRepSpec.class, "Quantity2", "ds.Quantity2", false));				 //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(AdvanceRepSpec.class, "Weight", "ds.Weight", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(AdvanceRepSpec.class, "Volume", "ds.Volume", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(AdvanceRepSpec.class, "Contractor", "contr.Code", "left join ds.Contractor as contr", false));			 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(AdvanceRepSpec.class, "Comment", "ds.Comment", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(AdvanceRepSpec.class, "TaxGroup", "tg.Code", "left join ds.TaxGroup as tg", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(AdvanceRepSpec.class, "Num", "ds.Num", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(AdvanceRepSpec.class, "ExpenseSum", "ds.ExpenseSum", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(AdvanceRepSpec.class, "Summa1", "ds.Summa1", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(AdvanceRepSpec.class, "ExpenseDocDate", "ds.ExpenseDocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(AdvanceRepSpec.class, "ExpenseDocNumber", "ds.ExpenseDocNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(AdvanceRepSpec.class, "ExpenseDocName", "ds.ExpenseDocName", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(AdvanceRepSpec.class, "AccPlan", "ac.Acc", "left join ds.AccPlan as ac", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(AdvanceRepSpec.class, "Anl1", "anl1.Code", "left join ds.Anl1 as anl1", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(AdvanceRepSpec.class, "Anl2", "anl2.Code", "left join ds.Anl2 as anl2", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(AdvanceRepSpec.class, "Anl3", "anl3.Code", "left join ds.Anl3 as anl3", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(AdvanceRepSpec.class, "Anl4", "anl4.Code", "left join ds.Anl4 as anl4", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(AdvanceRepSpec.class, "Anl5", "anl5.Code", "left join ds.Anl5 as anl5", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
			}

			/* (non-Javadoc)
			 * @see com.mg.merp.document.generic.ui.GoodsDocSpecMaintenanceEJBQLTableModel#getDocSpecModelName()
			 */
			@Override
			protected String getDocSpecModelName() {				
				return AdvanceRepSpec.class.getName();
			}

		});

		addMasterModelListener(spec);
	}

	/**
	 * Обработчик просмотра/выбора документа-подтверждения
	 * @param event - событие
	 */
	public void onActionViewConfirmDocument(WidgetEvent event) {
		final AdvanceRepHead advanceRepHead = (AdvanceRepHead) getEntity();
		if(advanceRepHead.getRestDoc() != null) {
			DocumentUtils.viewDocument(advanceRepHead.getRestDoc());
		}
		else if (advanceRepHead.getBalanceOrOverRun())
			viewSearchHelp("com.mg.merp.account.support.ui.CashDocumentInSearchHelp", advanceRepHead); //$NON-NLS-1$
		else 
			viewSearchHelp("com.mg.merp.account.support.ui.CashDocumentOutSearchHelp", advanceRepHead); //$NON-NLS-1$

	}

	/**
	 * Обработчик события обновить суммы
	 * 
	 * @param event - событие
	 */
	public void onActionCalcAll(WidgetEvent event) {
		getAdvanceRepHeadService().adjust((AdvanceRepHead) getEntity());
	}

	/**
	 * Обработчик события расчитать полученные суммы
	 * 
	 * @param event - событие
	 */
	public void onActionCalcRec(WidgetEvent event) {
		AdvanceRepHead advanceRepHead = (AdvanceRepHead) getEntity();
		if (advanceRepHead.getAcc() != null) {
			AdvanceRepHeadResult advanceRepHeadResult = getAdvanceRepHeadService().getReceivedSum(advanceRepHead.getAcc(), advanceRepHead.getFrom(), 
					advanceRepHead.getCurrency(), advanceRepHead.getDocDate());
			if (advanceRepHeadResult != null) {
				advanceRepHead.setReceived1Sum(advanceRepHeadResult.value);
				advanceRepHead.setReceived1Date(advanceRepHeadResult.receiveDate);
			} else {
				advanceRepHead.setReceived1Sum(null);
				advanceRepHead.setReceived1Date(null);			
			}
		} else 
			throw new BusinessException(Messages.getInstance().getMessage(Messages.NOT_CHOOSE_ACCPLAN));
	}

	/**
	 * Обработчик расчитать сумму остатка предыдущего аванса 
	 * 
	 * @param event - событие
	 */
	public void onActionCalcAdv(WidgetEvent event) {
		AdvanceRepHead advanceRepHead = (AdvanceRepHead) getEntity();
		if (advanceRepHead.getAcc() != null) {
			BigDecimal prevAdvanceSum = getAdvanceRepHeadService().getPrevAdvanceSum(advanceRepHead.getAcc(), advanceRepHead.getFrom(), advanceRepHead.getCurrency());
			// если сумма больше нуля то остаток иначе перерасход.
			if (MathUtils.compareToZero(prevAdvanceSum) < 0) {
				advanceRepHead.setPrevAdvanceSum(prevAdvanceSum.negate());
				advanceRepHead.setRestDocKind(false);
			} else {
				advanceRepHead.setPrevAdvanceSum(prevAdvanceSum);
				advanceRepHead.setRestDocKind(true);
			}
		} else 
			throw new BusinessException(Messages.getInstance().getMessage(Messages.NOT_CHOOSE_ACCPLAN));
	}

	/**
	 * По имени запускает форму поиска и устанавливает поля номер, тип, дата документа-подтверждения
	 * 
	 * @param searchHelpName
	 * 					- имя формы поиска
	 * @param advanceRepHead
	 * 					- текущий документ
	 */
	private void viewSearchHelp(String searchHelpName, final AdvanceRepHead advanceRepHead) {
		SearchHelp searchHelp = SearchHelpProcessor.createSearch(searchHelpName); 
		searchHelp.addSearchHelpListener(new SearchHelpListener() {

			public void searchCanceled(SearchHelpEvent event) {					
			}

			public void searchPerformed(SearchHelpEvent event) {
				DocHead docHead = (DocHead) event.getItems()[0];
				if (advanceRepHead.getRestSum() != null && MathUtils.compareToZero(advanceRepHead.getRestSum()) != 0) {
					advanceRepHead.setRestDocType(docHead.getDocType());
					advanceRepHead.setRestDocNumber(docHead.getDocNumber().trim());
					advanceRepHead.setRestDocDate(docHead.getDocDate());
					view.flushModel();	
				}
			}

		});
		searchHelp.search();
	}

	private AdvanceRepHeadServiceLocal getAdvanceRepHeadService() {
		if (service == null)
			return (AdvanceRepHeadServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(AdvanceRepHeadServiceLocal.LOCAL_SERVICE_NAME);
		return service;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.ui.DocumentMaintenanceForm#doSetDependentReadOnly(boolean)
	 */
	@Override
	protected void doSetDependentReadOnly(boolean readOnly) {
		super.doSetDependentReadOnly(readOnly);
		setReadOnlyButton(false);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.ui.GoodsDocumentMaintenanceForm#doOnClone()
	 */
	@Override
	protected void doOnClone() {
		super.doOnClone();
		setReadOnlyButton(true);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.ui.GoodsDocumentMaintenanceForm#doOnView()
	 */
	@Override
	protected void doOnView() {
		super.doOnView();
		setReadOnlyButton(false);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.ui.GoodsDocumentMaintenanceForm#doOnEdit()
	 */
	@Override
	protected void doOnEdit() {
		super.doOnEdit();
		setReadOnlyButton(true);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.ui.GoodsDocumentMaintenanceForm#doOnAdd()
	 */
	@Override
	protected void doOnAdd() {
		super.doOnAdd();
		setReadOnlyButton(true);
	}

	/**
	 * Установка значений кнопок в режим доступно/недоступно
	 * 
	 * @param isReadOnly
	 * 				- <code>true</code> - недоступно, иначе доступно
	 */
	private void setReadOnlyButton(boolean isReadOnly) {
		view.getWidget("CalcAdv").setEnabled(isReadOnly);
		view.getWidget("CalcRec").setEnabled(isReadOnly);
		view.getWidget("CalcAll").setEnabled(isReadOnly);
	}
	
}

