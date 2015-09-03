/*
 * FirmPlannedOrderBr.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.planning.support.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.planning.FirmPlannedOrderServiceLocal;
import com.mg.merp.planning.model.FirmPlannedOrder;
import com.mg.merp.planning.model.RecommendType;

/**
 * Контроллер форма браузера "Подтвержденных заказов"
 * 
 * @author leonova
 * @version $Id: FirmPlannedOrderBr.java,v 1.4 2007/08/06 14:28:13 safonov Exp $ 
 */
public class FirmPlannedOrderBr extends DefaultPlainBrowseForm {
	private final String INIT_QUERY_TEXT = "select  %s from FirmPlannedOrder fpo %s %s";
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();
	
	public FirmPlannedOrderBr() {
		super();
		restrictionFormName = "com/mg/merp/planning/resources/FirmPlannedOrderRest.mfd.xml";
	}

	@Override
	protected String createQueryText() {
		//String whereText = "";
		Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);		
		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
		paramsName.clear();
		paramsValue.clear();
		FirmPlannedOrderRest restForm = (FirmPlannedOrderRest) getRestrictionForm();		
		StringBuilder whereText = new StringBuilder(" where 0 = 0 ").
				append(DatabaseUtils.formatEJBQLObjectRestriction("fpo.Warehouse", restForm.getWarehouseCode(), "warehouseCode", paramsName, paramsValue, false)).
				append(DatabaseUtils.formatEJBQLObjectRestriction("fpo.SourceWarehouse", restForm.getSourceWarehouseCode(), "sourceWarehouseCode", paramsName, paramsValue, false)).
				append(DatabaseUtils.formatEJBQLObjectRestriction("fpo.MrpVersionControl", restForm.getMrpVersionControlCode(), "mrpVersionControlCode", paramsName, paramsValue, false)).
				append(DatabaseUtils.formatEJBQLObjectRestriction("fpo.Vendor", restForm.getVendorCode(), "vendorCode", paramsName, paramsValue, false)).
				append(DatabaseUtils.formatEJBQLObjectRestriction("fpo.Catalog", restForm.getCatalogCode(), "catalogCode", paramsName, paramsValue, false)).
				append(DatabaseUtils.formatEJBQLObjectRangeRestriction("fpo.OrderDate", restForm.getOrderDateFrom(), restForm.getOrderDateTill(), "orderDateFrom", "orderDateTill", paramsName, paramsValue, false)).
				append(DatabaseUtils.formatEJBQLObjectRangeRestriction("fpo.RequiredDate", restForm.getRequiredDateFrom(), restForm.getRequiredDateTill(), "requiredDateFrom", "requiredDateTill", paramsName, paramsValue, false)).		
				append(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "fpo.Id", restForm.getAddinFieldsRestriction(), false));
		
		if (restForm.getPurchaseOrTransfer() != 0)
			whereText.append(DatabaseUtils.formatEJBQLObjectRestriction("fpo.PurchaseOrTransfer", RecommendType.values()[restForm.getPurchaseOrTransfer() - 1], "purchaseOrTransfer", paramsName, paramsValue, false));
		
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
				result.add(new TableEJBQLFieldDef(FirmPlannedOrder.class, "Id", "fpo.Id", true));
				result.add(new TableEJBQLFieldDef(FirmPlannedOrder.class, "RequiredDate", "fpo.RequiredDate", false));
				result.add(new TableEJBQLFieldDef(FirmPlannedOrder.class, "OrderQty", "fpo.OrderQty", false));
				result.add(new TableEJBQLFieldDef(FirmPlannedOrder.class, "OrderDate", "fpo.OrderDate", false));
				result.add(new TableEJBQLFieldDef(FirmPlannedOrder.class, "FixedInput", "fpo.FixedInput", false));
				result.add(new TableEJBQLFieldDef(FirmPlannedOrder.class, "RequisitionFlag", "fpo.RequisitionFlag", false));
				result.add(new TableEJBQLFieldDef(FirmPlannedOrder.class, "PurchaseOrTransfer", "fpo.PurchaseOrTransfer", false));
				result.add(new TableEJBQLFieldDef(FirmPlannedOrder.class, "Measure", "meas.Code", "left join fpo.Measure as meas", false));
				result.add(new TableEJBQLFieldDef(FirmPlannedOrder.class, "Catalog.Code", "cat.Code", "left join fpo.Catalog as cat", true));
				result.add(new TableEJBQLFieldDef(FirmPlannedOrder.class, "Catalog.FullName", "cat.FullName", false));
				result.add(new TableEJBQLFieldDef(FirmPlannedOrder.class, "Warehouse", "w.Code", "left join fpo.Warehouse as w", false));
				result.add(new TableEJBQLFieldDef(FirmPlannedOrder.class, "Vendor", "v.Code", "left join fpo.Vendor as v", false));				
				result.add(new TableEJBQLFieldDef(FirmPlannedOrder.class, "SourceWarehouse", "sw.Code", "left join fpo.SourceWarehouse as sw", false));	
				result.add(new TableEJBQLFieldDef(FirmPlannedOrder.class, "MrpVersionControl.MrpVersion", "mvc.MrpVersion", "left join fpo.MrpVersionControl as mvc", true));				
				result.add(new TableEJBQLFieldDef(FirmPlannedOrder.class, "MrpVersionControl.Code", "mvc.Code", false));
				result.add(new TableEJBQLFieldDef(FirmPlannedOrder.class, "MrpVersionControl.RunDate", "mvc.RunDate", false));
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
	 * обработчик создания заказов
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void onActionCreateOrder(WidgetEvent event) throws Exception {
		MRPVersionControlSearchHelp searchHelp = (MRPVersionControlSearchHelp) SearchHelpProcessor.createSearch("com.mg.merp.planning.support.ui.MRPVersionControlSearchHelp");
		searchHelp.addSearchHelpListener(new SearchHelpListener() {

			public void searchCanceled(SearchHelpEvent event) {
			}

			public void searchPerformed(SearchHelpEvent event) {
				((FirmPlannedOrderServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(FirmPlannedOrderServiceLocal.SERVICE_NAME))
						.createByMrpRecommendation((Integer) event.getItems()[0].getPrimaryKey());
				table.refresh();
			}
			
		});
		searchHelp.search();
	}

}


