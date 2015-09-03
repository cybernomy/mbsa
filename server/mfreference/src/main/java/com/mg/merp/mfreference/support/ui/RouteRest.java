/*
 * RouteRest.java
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
package com.mg.merp.mfreference.support.ui;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultRestrictionForm;
import com.mg.merp.mfreference.model.RouteDestType;
import com.mg.merp.mfreference.model.RouteSrcType;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Contractor;

/**
 * Контроллер формы условий отбора логистических маршрутов
 * 
 * @author leonova
 * @version $Id: RouteRest.java,v 1.2 2006/09/04 05:51:49 leonova Exp $ 
 */
public class RouteRest extends DefaultRestrictionForm {

	private Catalog catalogCode = null;
	private RouteSrcType srcType = null;
	@DataItemName("MfReference.Route.SrcWarehouse")
	private Contractor srcWarehouse = null;
	@DataItemName("MfReference.Route.Vendor")
	private Contractor vendor = null;
	private RouteDestType destType = null;
	@DataItemName("MfReference.Route.Customer")
	private Contractor customer = null;
	@DataItemName("MfReference.Route.DestWarehouse")
	private Contractor destWarehouse = null;
	@DataItemName("MfReference.Cond.Route.LeadTimeFrom")
	private int leadTimeFrom = 0;
	@DataItemName("MfReference.Cond.Route.LeadTimeTill")
	private short leadTimeTill = 0;
	

	@Override
	protected void doClearRestrictionItem() {
		this.catalogCode = null;
		this.customer = null;
		this.vendor = null;
		this.srcWarehouse = null;
		this.destWarehouse = null;
		this.srcType = null;
		this.destType = null;
		this.leadTimeFrom = 0;
		this.leadTimeTill = 0;
	}


	/**
	 * @return Returns the catalogCode.
	 */
	protected Catalog getCatalogCode() {
		return catalogCode;
	}


	/**
	 * @return Returns the customer.
	 */
	protected Contractor getCustomer() {
		return customer;
	}


	/**
	 * @return Returns the destType.
	 */
	protected RouteDestType getDestType() {
		return destType;
	}


	/**
	 * @return Returns the destWarehouse.
	 */
	protected Contractor getDestWarehouse() {
		return destWarehouse;
	}


	/**
	 * @return Returns the leadTimeFrom.
	 */
	protected int getLeadTimeFrom() {
		return leadTimeFrom;
	}


	/**
	 * @return Returns the leadTimeTill.
	 */
	protected short getLeadTimeTill() {
		return leadTimeTill;
	}


	/**
	 * @return Returns the srcType.
	 */
	protected RouteSrcType getSrcType() {
		return srcType;
	}


	/**
	 * @return Returns the srcWarehouse.
	 */
	protected Contractor getSrcWarehouse() {
		return srcWarehouse;
	}


	/**
	 * @return Returns the vendor.
	 */
	protected Contractor getVendor() {
		return vendor;
	}	



}
