/*
 * Messages.java
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
package com.mg.merp.planning.support;

import com.mg.framework.generic.MessageSourceAccessor;

/**
 * @author Oleg V. Safonov
 * @author Konstantin S. Alikaev
 * @version $Id: Messages.java,v 1.3 2008/02/13 13:20:10 alikaev Exp $
 */
public class Messages extends MessageSourceAccessor {
	private static final String BUNDLE_NAME = "com.mg.merp.planning.resources.messages"; //$NON-NLS-1$
	private static Messages instance;

	//message keys
	public static final String DUPLICATE_GENERIC_ITEM_CATALOG = "DuplicateGenericItemCatalog"; //$NON-NLS-1$
	public static final String CHANGE_PLANNING_DATE = "ChangePlanningDate"; //$NON-NLS-1$
	
	public static final String MPS_LINE_TITLE = "MPSLineTitle"; //$NON-NLS-1$
	public static final String MRP_RECOMMENDATION_TITLE = "MRPRecommendationTitle"; //$NON-NLS-1$
	public static final String MRP_REPORT_TITLE = "MRPReportTitle"; //$NON-NLS-1$
	
	public static final String MPS_REFERENCE = "MPSReference"; //$NON-NLS-1$
	public static final String MPS_CHILD_REFERENCE = "MPSChildReference"; //$NON-NLS-1$
	public static final String SALE_ORDER_REFERENCE = "SaleOrderReference"; //$NON-NLS-1$
	public static final String PURCHASE_FORECAST_ON_PERIOD_REFERENCE = "PurchaseForecastOnPeriodReference"; //$NON-NLS-1$
	public static final String PURCHASE_FORECAST_ON_DATE_REFERENCE = "PurchaseForecastOnDateReference"; //$NON-NLS-1$
	public static final String SALE_FORECAST_ON_PERIOD_REFERENCE = "SaleForecastOnPeriodReference"; //$NON-NLS-1$
	public static final String SALE_FORECAST_ON_DATE_REFERENCE = "SaleForecastOnDateReference"; //$NON-NLS-1$
	public static final String JOB_REFERENCE = "JobReference"; //$NON-NLS-1$
	public static final String JOB_OUTPUT_REFERENCE = "JobOutputReference"; //$NON-NLS-1$
	public static final String IN_BOUND_FIRM_PLANNED_REFERENCE = "InBoundFirmPlannedWarehouseTransferReference"; //$NON-NLS-1$
	public static final String OUT_BOUND_FIRM_PLANNED_REFERENCE = "OutBoundFirmPlannedWarehouseTransferReference"; //$NON-NLS-1$
	public static final String QUANTITY_CURRENT_ON_HAND_REFERENCE = "QuantityCurrentOnHandReference"; //$NON-NLS-1$
	public static final String SUGGESTED_ORDER_NUMBER_REFERENCE = "SuggestedOrderNumberReference"; //$NON-NLS-1$
	
	public static final String PRODUCT_CATALOG_WAREHOUSE_NOT_FOUND = "ProductCatalogWarehouseNotFound"; //$NON-NLS-1$
	public static final String PRODUCT_ROUTE_NOT_FOUND = "ProductRouteNotFound"; //$NON-NLS-1$
	public static final String WAREHOUSE_NOT_SETUP = "WarehouseNotSetup"; //$NON-NLS-1$
	public static final String ITEM_FAMILY_PARENT_CONCUR_WIDTH_CHILD_ITEM = "ItemFamilyParentConcurWithChildItem"; //$NON-NLS-1$

	public static final String PLANNINGFORECAST_CHOOSE_CATALOG_OR_PLANNINGITEM = "PlanningForecastChooseCatalogOrPlanningItem"; //$NON-NLS-1$
	
	public static Messages getInstance() {
		return instance;
	}
	
	static {
		MessageSourceAccessor.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
