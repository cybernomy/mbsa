/*
 * OrderHeadRest.java
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
package com.mg.merp.warehouse.support.ui;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.merp.document.generic.ui.GoodsDocumentRest;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.support.ui.ContractorSearchForm;
import com.mg.merp.warehouse.model.OrderStatus;

/**
 * 
 * @author leonova
 * @version $Id: OrderHeadRest.java,v 1.6 2006/12/20 11:59:45 leonova Exp $
 *
 */
public class OrderHeadRest extends GoodsDocumentRest {

	private OrderStatus status = null;
	@DataItemName("Warehouse.OrderHead.Responsible")
	private Contractor responsibleCode = null;	
	protected String[] contractorResponsibleKinds;
	
	public OrderHeadRest() {
		contractorThroughKinds = new String[] {ContractorSearchForm.CONTRACTOR_PARTNER, ContractorSearchForm.CONTRACTOR_EMPLOYEE};
		contractorResponsibleKinds = new String[] {ContractorSearchForm.CONTRACTOR_EMPLOYEE, ContractorSearchForm.CONTRACTOR_ORGUNIT};		
	}

	@Override
	protected void doClearRestrictionItem() {
		super.doClearRestrictionItem();
		this.status = null;
		this.responsibleCode = null;	
	}


	public Contractor getResponsibleCode() {
		return responsibleCode;
	}


	public OrderStatus getStatus() {
		return status;
	}


}
