/*
 * BillHeadOutRest.java
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

import com.mg.merp.document.generic.ui.GoodsDocumentRest;
import com.mg.merp.reference.support.ui.ContractorSearchForm;


/**
 * 
 * @author leonova
 * @version $Id: BillHeadOutRest.java,v 1.4 2006/12/20 13:15:50 leonova Exp $
 *
 */
public class BillHeadOutRest extends GoodsDocumentRest {	
	
	public BillHeadOutRest() {
		contractorThroughKinds = new String[] {ContractorSearchForm.CONTRACTOR_PARTNER, ContractorSearchForm.CONTRACTOR_EMPLOYEE};
	}

}
