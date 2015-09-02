/*
 * CatalogWarehouseBr.java
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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.planning.model.CatalogWarehouse;

/**
 * Браузер складов хранения товаров
 * 
 * @author leonova
 * @version $Id: CatalogWarehouseBr.java,v 1.7 2007/07/30 10:36:31 safonov Exp $
 */
public class CatalogWarehouseBr extends DefaultPlainBrowseForm {
	private final String INIT_QUERY_TEXT = "select %s from CatalogWarehouse cw %s %s";
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();
	
	public CatalogWarehouseBr() {
		restrictionFormName = "com/mg/merp/planning/resources/CatalogWarehouseRest.mfd.xml";		
	}
	
	@Override
	protected String createQueryText() {		
		Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);			
		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
		CatalogWarehouseRest restForm = (CatalogWarehouseRest) getRestrictionForm();
		paramsName.clear();
		paramsValue.clear();
		StringBuilder  whereText = new StringBuilder( " where 0 = 0 ")
				.append(DatabaseUtils.formatEJBQLObjectRestriction("cw.Catalog", restForm.getCatalogCode(), "catalogCode", paramsName, paramsValue, false))
				.append(DatabaseUtils.formatEJBQLObjectRestriction("cw.Warehouse", restForm.getWarehouseCode(), "warehouseCode", paramsName, paramsValue, false))
				.append(DatabaseUtils.formatEJBQLObjectRangeRestriction("cw.MrpDampingDays", restForm.getMrpDampingDaysFrom(), null, "mrpDampingDaysFrom", null, paramsName, paramsValue, false))		
				.append(DatabaseUtils.formatEJBQLObjectRangeRestriction("cw.MrpDampingDays", null, restForm.getMrpDampingDaysTill(), null, "mrpDampingDaysTill", paramsName, paramsValue, false))		
				.append(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "m.Id", restForm.getAddinFieldsRestriction(), false));		
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
				result.add(new TableEJBQLFieldDef(CatalogWarehouse.class, "Id", "cw.Id", true));
				result.add(new TableEJBQLFieldDef(CatalogWarehouse.class, "SafetyLevel", "cw.SafetyLevel", false));
				result.add(new TableEJBQLFieldDef(CatalogWarehouse.class, "MrpDampingDays", "cw.MrpDampingDays", false));
				result.add(new TableEJBQLFieldDef(CatalogWarehouse.class, "DemandFenceDays", "cw.DemandFenceDays", false));
				result.add(new TableEJBQLFieldDef(CatalogWarehouse.class, "OrderIntervalDays", "cw.OrderIntervalDays", false));
				result.add(new TableEJBQLFieldDef(CatalogWarehouse.class, "Catalog.Code", "cw.Catalog.Code", false));
				result.add(new TableEJBQLFieldDef(CatalogWarehouse.class, "Catalog.FullName", "cw.Catalog.FullName", false));
				result.add(new TableEJBQLFieldDef(CatalogWarehouse.class, "Warehouse", "cw.Warehouse.Code", false));				
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

}
