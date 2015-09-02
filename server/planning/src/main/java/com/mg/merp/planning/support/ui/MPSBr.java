/*
 * MPSBr.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.planning.support.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.planning.MPSLineServiceLocal;
import com.mg.merp.planning.MPSProcessorServiceLocal;
import com.mg.merp.planning.model.Mps;

/**
 * Контроллер браузера бизнес-компонента "ОПП"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: MPSBr.java,v 1.6 2007/07/30 10:36:31 safonov Exp $ 
 */
public class MPSBr extends DefaultPlainBrowseForm {
	private final String INIT_QUERY_TEXT = "select %s from Mps m %s %s"; //$NON-NLS-1$
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();

	public MPSBr() {
		super();
		restrictionFormName = "com/mg/merp/planning/resources/MPSRest.mfd.xml"; //$NON-NLS-1$
	}

	@Override
	protected String createQueryText() {
		Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);			
		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
		MPSRest restForm = (MPSRest) getRestrictionForm();
		paramsName.clear();
		paramsValue.clear();
		StringBuilder whereText = new StringBuilder(" where 0 = 0 ") //$NON-NLS-1$
			.append(DatabaseUtils.formatEJBQLStringRestriction("m.Code", restForm.getCode(), "code", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
			.append(DatabaseUtils.formatEJBQLStringRestriction("m.Description", restForm.getDescription(), "description", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
			.append(DatabaseUtils.formatEJBQLObjectRangeRestriction("m.PlanningDate", restForm.getPlanningDateFrom(), restForm.getPlanningDateTill(), "planningDateFrom", "planningDateTill", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			.append(DatabaseUtils.formatEJBQLObjectRestriction("m.WeekCal", restForm.getWeekCalCode(), "weekCalCod", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
			.append(DatabaseUtils.formatEJBQLObjectRestriction("m.InventoryForecast", restForm.getInventoryForecastCode(), "inventoryForecastCode", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
			.append(DatabaseUtils.formatEJBQLObjectRestriction("m.PlanningLevel", restForm.getPlanningLevel(), "planningLevel", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
			.append(DatabaseUtils.formatEJBQLObjectRestriction("m.ForecastVersion", restForm.getForecastVersionCode(), "forecastVersionCode", paramsName, paramsValue, false))		 //$NON-NLS-1$ //$NON-NLS-2$
			.append(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "m.Id", restForm.getAddinFieldsRestriction(), false)); //$NON-NLS-1$

		return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText.toString());				

	}

	@Override
	protected MaintenanceTableModel createModel() {
		return new DefaultMaintenanceEJBQLTableModel() {

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(Mps.class, "Id", "m.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Mps.class, "Code", "m.Code", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Mps.class, "DemandFenceDate", "m.DemandFenceDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Mps.class, "Description", "m.Description", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Mps.class, "PlanningDate", "m.PlanningDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Mps.class, "LevelProcessedTo", "m.LevelProcessedTo", false));				 //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Mps.class, "Production", "m.Production", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Mps.class, "WarehouseTransfers", "m.WarehouseTransfers", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Mps.class, "ProfileApplied", "m.ProfileApplied", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Mps.class, "PurchasesForecasts", "m.PurchasesForecasts", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Mps.class, "PurchasesLive", "m.PurchasesLive", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Mps.class, "QtyOnHand", "m.QtyOnHand", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Mps.class, "SalesForecasts", "m.SalesForecasts", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Mps.class, "SalesLive", "m.SalesLive", false));				 //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Mps.class, "MpsVersion", "m.MpsVersion", false));				 //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Mps.class, "PlanningLevel", "pl.Description", "left join m.PlanningLevel as pl", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Mps.class, "ForecastVersion", "fv.Code", "left join m.ForecastVersion as fv", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Mps.class, "InventoryForecast", "if.InventoryForecastCode", "left join m.InventoryForecast as if", false));				 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Mps.class, "WeekCal", "wc.Code", "left join m.WeekCal as wc", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

	public void onActionShowMPSLine(WidgetEvent event) throws Exception {
		Serializable[] keys = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
		if (keys.length != 0) {
			MPSLineServiceLocal service = (MPSLineServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(MPSLineServiceLocal.SERVICE_NAME);
			MPSLineBr form = (MPSLineBr)ApplicationDictionaryLocator.locate().getBrowseForm(service, null);
			form.execute((Integer) keys[0]);
		}
	}

	public void onActionGenerateMPS(WidgetEvent event) {
		Serializable[] keys = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
		MPSProcessorServiceLocal processor = (MPSProcessorServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(MPSProcessorServiceLocal.SERVICE_NAME);
		for (Serializable key : keys)
			processor.generateMps((Integer) key);
	}

	public void onActionMPSLevelTransfer(WidgetEvent event) throws Exception {
		final Serializable[] keys = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
		if (keys.length == 0)
			return;
		MPSSearchHelp searchHelp = new MPSSearchHelp();
		searchHelp.addSearchHelpListener(new SearchHelpListener() {

			public void searchCanceled(SearchHelpEvent event) {
			}

			public void searchPerformed(SearchHelpEvent event) {
				((MPSProcessorServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(MPSProcessorServiceLocal.SERVICE_NAME))
						.mpsLevelTransfer((Integer) keys[0], (Integer) event.getItems()[0].getPrimaryKey());
			}
			
		});
		searchHelp.search();
	}

}
