/*
 * MPSLineBr.java
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
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.manufacture.JobServiceLocal;
import com.mg.merp.planning.MPSLineServiceLocal;
import com.mg.merp.planning.model.Mps;
import com.mg.merp.planning.model.MpsLine;
import com.mg.merp.planning.support.Messages;
import com.mg.merp.reference.support.ui.FolderByTypeSearchHelp;

/**
 * @author leonova
 * @version $Id: MPSLineBr.java,v 1.2 2007/07/30 10:36:31 safonov Exp $ 
 */
public class MPSLineBr extends DefaultPlainBrowseForm {
	private final String INIT_QUERY_TEXT = "select %s from MpsLine ml %s where ml.Mps.Id = :mpsId and ml.OutputMpsSequence is null";
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();
	private Integer mpsId;

	@Override
	protected String createQueryText() {
		Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);			
		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
		paramsName.clear();
		paramsValue.clear();
		paramsName.add("mpsId");
		paramsValue.add(mpsId);
		return String.format(INIT_QUERY_TEXT, fieldsList, fromList);				

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
				result.add(new TableEJBQLFieldDef(MpsLine.class, "Id", "ml.Id", true));
				result.add(new TableEJBQLFieldDef(MpsLine.class, "PlanningItem", "ml.PlanningItem", false));
				result.add(new TableEJBQLFieldDef(MpsLine.class, "Measure", "ml.Measure", false));
				result.add(new TableEJBQLFieldDef(MpsLine.class, "ForecastVersion", "fv.Code", "left join ml.ForecastVersion as fv", false));
				result.add(new TableEJBQLFieldDef(MpsLine.class, "InventoryForecast", "ift.InventoryForecastCode", "left join ml.InventoryForecast as ift", false));
				result.add(new TableEJBQLFieldDef(MpsLine.class, "AdjustmentQty", "ml.AdjustmentQty", false));
				result.add(new TableEJBQLFieldDef(MpsLine.class, "BucketOffset", "ml.BucketOffset", false));
				result.add(new TableEJBQLFieldDef(MpsLine.class, "BucketOffsetDate", "ml.BucketOffsetDate", false));				
				result.add(new TableEJBQLFieldDef(MpsLine.class, "DemandFenceDate", "ml.DemandFenceDate", false));
				result.add(new TableEJBQLFieldDef(MpsLine.class, "DemandQty", "ml.DemandQty", false));
				result.add(new TableEJBQLFieldDef(MpsLine.class, "MpsSequence", "ml.MpsSequence", false));
				result.add(new TableEJBQLFieldDef(MpsLine.class, "OutputMpsSequence", "ml.OutputMpsSequence", false));				
				result.add(new TableEJBQLFieldDef(MpsLine.class, "PlannedQty", "ml.PlannedQty", false));
				result.add(new TableEJBQLFieldDef(MpsLine.class, "DependantDemand", "ml.DependantDemand", false));
				result.add(new TableEJBQLFieldDef(MpsLine.class, "LevelCode", "ml.LevelCode", false));
				result.add(new TableEJBQLFieldDef(MpsLine.class, "ProductionDemandQty", "ml.ProductionDemandQty", false));				
				result.add(new TableEJBQLFieldDef(MpsLine.class, "ProductionProfileQty", "ml.ProductionProfileQty", false));
				result.add(new TableEJBQLFieldDef(MpsLine.class, "ProductionQty", "ml.ProductionQty", false));
				result.add(new TableEJBQLFieldDef(MpsLine.class, "PurchaseForecastQty", "ml.PurchaseForecastQty", false));
				result.add(new TableEJBQLFieldDef(MpsLine.class, "PurchaseOrderQty", "ml.PurchaseOrderQty", false));				
				result.add(new TableEJBQLFieldDef(MpsLine.class, "PurchaseQty", "ml.PurchaseQty", false));
				result.add(new TableEJBQLFieldDef(MpsLine.class, "QtyAvailable", "ml.QtyAvailable", false));
				result.add(new TableEJBQLFieldDef(MpsLine.class, "SalesForecastQty", "ml.SalesForecastQty", false));
				result.add(new TableEJBQLFieldDef(MpsLine.class, "SalesOrderQty", "ml.SalesOrderQty", false));				
				result.add(new TableEJBQLFieldDef(MpsLine.class, "SalesQty", "ml.SalesQty", false));
				result.add(new TableEJBQLFieldDef(MpsLine.class, "TransfersInQty", "ml.TransfersInQty", false));
				result.add(new TableEJBQLFieldDef(MpsLine.class, "TransfersOutQty", "ml.TransfersOutQty", false));
				result.add(new TableEJBQLFieldDef(MpsLine.class, "LiveProductionDemand", "ml.LiveProductionDemand", false));				
				result.add(new TableEJBQLFieldDef(MpsLine.class, "SafetyLevelQty", "ml.SafetyLevelQty", false));
				result.add(new TableEJBQLFieldDef(MpsLine.class, "PlanningItem.Catalog", "ml.PlanningItem.Catalog", false));
				result.add(new TableEJBQLFieldDef(MpsLine.class, "BucketEndDate", "ml.BucketEndDate", false));				
				result.add(new TableEJBQLFieldDef(MpsLine.class, "FirmPlanSuggested", "ml.FirmPlanSuggested", false));
				result.add(new TableEJBQLFieldDef(MpsLine.class, "MpsOrdered", "ml.MpsOrdered", false));				
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
	 * показать форму итогов формирования MPS
	 * 
	 * @param mpsId The mpsId to set.
	 */
	public void execute(Integer mpsId) {
		this.mpsId = mpsId;
		Mps mps = ServerUtils.getPersistentManager().find(Mps.class, mpsId);
		setTitle(Messages.getInstance().getMessage(Messages.MPS_LINE_TITLE, new Object[] {mps.getCode().trim()}));
		run();
	}
	
	public void onActionFirm(WidgetEvent event) {
		Serializable[] keys = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
		if (keys.length == 0)
			return;
		int[] ids = new int[keys.length];
		for (int i = 0; i < ids.length; i++)
			ids[i] = (Integer) keys[i];
		((MPSLineServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(MPSLineServiceLocal.SERVICE_NAME))
				.firm(ids);
	}

	public void onActionCreateJob(WidgetEvent event) throws Exception {
		FolderByTypeSearchHelp folderSearchHelper = new FolderByTypeSearchHelp() {

			@Override
			protected short getFolderType() {
				return JobServiceLocal.FOLDER_PART;
			}
			
		};
		folderSearchHelper.addSearchHelpListener(new SearchHelpListener() {

			public void searchCanceled(SearchHelpEvent event) {
			}

			public void searchPerformed(SearchHelpEvent event) {
				((MPSLineServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(MPSLineServiceLocal.SERVICE_NAME))
						.createJob((Integer) event.getItems()[0].getPrimaryKey(), mpsId);
			}
			
		});
		folderSearchHelper.search();
	}

}
