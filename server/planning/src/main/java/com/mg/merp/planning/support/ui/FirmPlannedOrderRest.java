/*
 * FirmPlannedOrderRest.java
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

import java.util.Date;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultRestrictionForm;
import com.mg.merp.planning.model.MrpVersionControl;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Contractor;

/**
 * Контроллер формы условий отбора подтвержденных заказов
 * 
 * @author leonova
 * @version $Id: FirmPlannedOrderRest.java,v 1.2 2006/08/25 10:18:05 leonova Exp $ 
 */
public class FirmPlannedOrderRest extends DefaultRestrictionForm {

	@DataItemName("Planning.Cond.FirmPlannedOrder.RequiredDateFrom")
	private Date requiredDateFrom = null;
	@DataItemName("Planning.Cond.FirmPlannedOrder.RequiredDateTill")
	private Date requiredDateTill = null;
	@DataItemName("Planning.Cond.FirmPlannedOrder.OrderDateFrom")
	private Date orderDateFrom = null;
	@DataItemName("Planning.Cond.FirmPlannedOrder.OrderDateTill")
	private Date orderDateTill = null;
	@DataItemName("Planning.FirmPlanOrder.AllocOrderType")
	private int purchaseOrTransfer = 0;
	@DataItemName("Planning.FirmPlanOrder.SourceWarehouse")
	private Contractor sourceWarehouseCode = null;
	@DataItemName("Planning.FirmPlanOrder.Warehouse")
	private Contractor warehouseCode = null;
	@DataItemName("Planning.FirmPlanOrder.Vendor")
	private Contractor vendorCode = null;
	@DataItemName("Planning.FirmPlanOrder.Catalog")
	private Catalog catalogCode = null;
	@DataItemName("Planning.FirmPlanOrder.MrpVersionControl")
	private MrpVersionControl mrpVersionControlCode = null;
	

	@Override
	protected void doClearRestrictionItem() {
		this.requiredDateFrom = null;
		this.requiredDateTill = null;
		this.orderDateFrom = null;
		this.orderDateTill = null;
		this.purchaseOrTransfer = 0;
		this.warehouseCode = null;
		this.sourceWarehouseCode = null;
		this.catalogCode = null;
		this.vendorCode = null;
		this.mrpVersionControlCode = null;
	}


	/**
	 * @return Returns the catalogCode.
	 */
	protected Catalog getCatalogCode() {
		return catalogCode;
	}


	/**
	 * @return Returns the mrpVersionControlCode.
	 */
	protected MrpVersionControl getMrpVersionControlCode() {
		return mrpVersionControlCode;
	}


	/**
	 * @return Returns the orderDateFrom.
	 */
	protected Date getOrderDateFrom() {
		return orderDateFrom;
	}


	/**
	 * @return Returns the orderDateTill.
	 */
	protected Date getOrderDateTill() {
		return orderDateTill;
	}


	/**
	 * @return Returns the purchaseOrTransfer.
	 */
	protected int getPurchaseOrTransfer() {
		return purchaseOrTransfer;
	}


	/**
	 * @return Returns the requiredDateFrom.
	 */
	protected Date getRequiredDateFrom() {
		return requiredDateFrom;
	}


	/**
	 * @return Returns the requiredDateTill.
	 */
	protected Date getRequiredDateTill() {
		return requiredDateTill;
	}


	/**
	 * @return Returns the sourceWarehouseCode.
	 */
	protected Contractor getSourceWarehouseCode() {
		return sourceWarehouseCode;
	}


	/**
	 * @return Returns the vendorCode.
	 */
	protected Contractor getVendorCode() {
		return vendorCode;
	}


	/**
	 * @return Returns the warehouseCode.
	 */
	protected Contractor getWarehouseCode() {
		return warehouseCode;
	}



}
