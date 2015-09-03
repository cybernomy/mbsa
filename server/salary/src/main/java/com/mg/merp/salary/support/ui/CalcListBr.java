/*
 * CalcListBr.java
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
package com.mg.merp.salary.support.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.personnelref.model.PositionFill;
import com.mg.merp.reference.model.NaturalPerson;
import com.mg.merp.salary.CalcListServiceLocal;
import com.mg.merp.salary.model.CalcList;

/**
 * Контроллер браузера "Расчетной ведомости (списка расчетных листков)"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: CalcListBr.java,v 1.3 2007/07/09 08:33:47 sharapov Exp $ 
 */
public class CalcListBr extends DefaultPlainBrowseForm {

	private final String INIT_QUERY_TEXT = "select %s from CalcList cl %s where cl.PayRoll.Id = :payRollId"; //$NON-NLS-1$
	protected List<String> paramsName = new ArrayList<String>();
	protected List<Object> paramsValue = new ArrayList<Object>();	
	
	protected Serializable payRollId;


	public CalcListBr() {
		table = new MaintenanceTableController(uiProperties) {

			/* (non-Javadoc)
			 * @see com.mg.framework.support.ui.widget.MaintenanceTableController#doAdd()
			 */
			@Override
			protected void doAdd() {
				try {
					AddCalcLists();
				}
				catch(Exception e) {
				}
			}
		};
	}


	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
	 */
	@Override
	protected String createQueryText() {
		Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);			
		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
		paramsName.clear();
		paramsValue.clear();	
		paramsName.add("payRollId"); //$NON-NLS-1$
		paramsValue.add((Integer)payRollId);
		return String.format(INIT_QUERY_TEXT, fieldsList, fromList);				
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createModel()
	 */
	@Override
	protected MaintenanceTableModel createModel() {
		return new DefaultMaintenanceEJBQLTableModel() {

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(CalcList.class, "Id", "cl.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(CalcList.class, "PositionFill", "cl.PositionFill.Position.Name", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(NaturalPerson.class, "Surname", "np.Surname", "left join cl.PositionFill.PersonalAccount as pa left join pa.Personnel as p left join p.Person np", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(NaturalPerson.class, "Name", "np.Name", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(NaturalPerson.class, "Patronymic", "np.Patronymic", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(CalcList.class, "PositionFill.BeginDate", "cl.PositionFill.BeginDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(CalcList.class, "PositionFill.EndDate", "cl.PositionFill.EndDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(CalcList.class, "NeedParams", "cl.NeedParams", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(CalcList.class, "IsCalculated", "cl.IsCalculated", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(CalcList.class, "TotalSumma", "cl.TotalSumma", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(CalcList.class, "PositiveSumma", "cl.PositiveSumma", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(CalcList.class, "NegativeSumma", "cl.NegativeSumma", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(CalcList.class, "NeutralSumma", "cl.NeutralSumma", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(CalcList.class, "IsClosed", "cl.IsClosed", false)); //$NON-NLS-1$ //$NON-NLS-2$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
			 */
			@Override
			protected int getPrimaryKeyFieldIndex() {
				return 0;
			}
		};
	}

	/**
	 * Добавить расчетные листки в расчетную ведомость
	 */
	private void AddCalcLists() throws Exception {
		SearchHelp searchHelp = SearchHelpProcessor.createSearch("com.mg.merp.salary.support.ui.PositionFillInStaffListSearchHelp"); //$NON-NLS-1$
		searchHelp.addSearchHelpListener(new SearchHelpListener() {
			public void searchPerformed(SearchHelpEvent event) {
				PositionFill[] positionFills = new PositionFill[event.getItems().length];
				for(int i = 0; i < event.getItems().length; i++)
					positionFills[i] = (PositionFill) event.getItems()[i];
				getCalcListService().addCalcLists(positionFills, (Integer) payRollId);
				table.refresh();
			}

			public void searchCanceled(SearchHelpEvent event) {
				//do nothing
			}
		});
		searchHelp.search();
	}
	
	/**
	 * Обработчик пункта КМ "Рассчитать"
	 * @param event - событие
	 */
	public void onActionCalculate(WidgetEvent event) {
		getCalcListService().calculate(getSelectedPrimaryKeys(), false);
		table.refresh();
	}
	
	/**
	 * Обработчик пункта КМ "Очистить и рассчитать"
	 * @param event - событие
	 */
	public void onActionClearAndCalculate(WidgetEvent event) {
		getCalcListService().calculate(getSelectedPrimaryKeys(), true);
		table.refresh();
	}
	
	/**
	 * Обработчик пункта КМ "Установить признак "Рассчитан и закрыт""
	 * @param event - событие
	 */
	public void onActionSetCalculatedAndClosed(WidgetEvent event) {
		getCalcListService().setClosed(getSelectedPrimaryKeys(), true);
		table.refresh();
	}
	
	/**
	 * Обработчик пункта КМ "Снять признак "Рассчитан и закрыт""
	 * @param event - событие
	 */
	public void onActionUnSetCalculatedAndClosed(WidgetEvent event) {
		getCalcListService().setClosed(getSelectedPrimaryKeys(), false);
		table.refresh();
	}
	
	/**
	 * @param payRollId The payRollId to set.
	 */
	protected void setPayRollId(Serializable payRollId) {
		this.payRollId = payRollId;
	}

	private CalcListServiceLocal getCalcListService() {
		return (CalcListServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CalcListServiceLocal.LOCAL_SERVICE_NAME);
	}
	
	private Serializable[] getSelectedPrimaryKeys() {
		return ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getSelectedPrimaryKeys();
	}

}
