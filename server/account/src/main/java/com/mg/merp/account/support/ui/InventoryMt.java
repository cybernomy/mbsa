/*
 * InventoryMt.java
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
package com.mg.merp.account.support.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.Button;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.account.InvHistoryServiceLocal;
import com.mg.merp.account.InventoryServiceLocal;
import com.mg.merp.account.model.AmortAlgorithmType;
import com.mg.merp.account.model.InvHistory;
import com.mg.merp.account.model.Inventory;

/**
 * Контроллер формы поддержки инвентарной карточки
 * 
 * @author leonova
 * @author Konstantin S. Alikaev
 * @author Artem V. Sharapov
 * @version $Id: InventoryMt.java,v 1.7 2009/02/20 10:34:29 sharapov Exp $
 */
public class InventoryMt extends DefaultMaintenanceForm implements MasterModelListener {

	private MaintenanceTableController history;
	private InvHistoryServiceLocal historyService;
	protected AttributeMap historyProperties = new LocalDataTransferObject();

	public InventoryMt() throws Exception {		
		addMasterModelListener(this);
		
		historyService = (InvHistoryServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/InvHistory");
		history = new MaintenanceTableController(historyProperties);
		history.initController(historyService, new DefaultMaintenanceEJBQLTableModel() {
			private final String INIT_QUERY_TEXT = "select %s from InvHistory ih where ih.Inventory = :inventory";
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();
			
			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("inventory");
				paramsValue.add(getEntity());					
				return String.format(INIT_QUERY_TEXT, fieldsList);		
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
			 */
			@Override
			protected int getPrimaryKeyFieldIndex() {
				return 0;
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(InvHistory.class, "Id", "ih.Id", true));
				result.add(new TableEJBQLFieldDef(InvHistory.class, "ActDate", "ih.ActDate", false));
				result.add(new TableEJBQLFieldDef(InvHistory.class, "Kind", "ih.Kind", false));
				result.add(new TableEJBQLFieldDef(InvHistory.class, "DeltaBalCost", "ih.DeltaBalCost", false));
				result.add(new TableEJBQLFieldDef(InvHistory.class, "DeltaDeprVal", "ih.DeltaDeprVal", false));				
				result.add(new TableEJBQLFieldDef(InvHistory.class, "RevalFactor", "ih.RevalFactor", false));	
				result.add(new TableEJBQLFieldDef(InvHistory.class, "RevalSum", "ih.RevalSum", false));					
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, historyService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});
		addMasterModelListener(history);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */
	public void masterChange(ModelChangeEvent event) {
		historyProperties.put("Inventory", event.getEntity());	
	}

	/**
	 * Обработчик обновления формы 
	 * @param event - событие
	 */
	public void onActionRefresh(WidgetEvent event) {
		Inventory inventory = (Inventory) getEntity();
		InventoryServiceLocal inventoryService = (InventoryServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/Inventory");
		inventoryService.store(inventory);
		setReadOnlyField(inventory.getAlgorithm() == AmortAlgorithmType.BYEXPLPERIOD);
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		super.doOnRun();
		doAdjustFieldsAccess();
	}
	
	protected void doAdjustFieldsAccess() {
		Inventory inventory = (Inventory) getEntity();
		boolean isHistoryExists = false;
		Set<InvHistory> invHistorySet = inventory.getInvHistories();
		if (invHistorySet != null)
			isHistoryExists = !invHistorySet.isEmpty();
		doSetReadOnlyFieldsByHistory(isHistoryExists);
		setReadOnlyField(AmortAlgorithmType.BYEXPLPERIOD.equals(inventory.getAlgorithm()));
	}
	
	protected void doSetReadOnlyFieldsByHistory(boolean isReadOnly) {
		view.getWidget("BeginCost").setReadOnly(isReadOnly);
		view.getWidget("Initialloss").setReadOnly(isReadOnly);
		view.getWidget("BeginLoss").setReadOnly(isReadOnly);
		view.getWidget("BeginLossDate").setReadOnly(isReadOnly);
		view.getWidget("AccKind").setReadOnly(isReadOnly);
		view.getWidget("AccGroup").setReadOnly(isReadOnly);
		((Button) view.getWidget("refreshButton")).setEnabled(!isReadOnly);
	}

	/**
	 * Изменение доступа к полям
	 * 
	 * @param isReadOnly
	 * 				- <code>true</code> - сделать недоступным для изменения, иначе доступным
	 */
	private void setReadOnlyField(boolean isReadOnly) {
		view.getWidget("ExplPeriodY").setReadOnly(!isReadOnly);
		view.getWidget("ExplPeriodM").setReadOnly(!isReadOnly);
		view.getWidget("AmCode").setReadOnly(isReadOnly);
	}
	
}
