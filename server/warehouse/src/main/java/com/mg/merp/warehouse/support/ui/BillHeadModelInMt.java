/*
 * BillHeadModelOutMt.java
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
package com.mg.merp.warehouse.support.ui;

import com.mg.merp.document.generic.ui.DocumentModelMaintenanceForm;
import com.mg.merp.reference.support.ui.ContractorSearchForm;

/**
 * Контроллер формы поддержки "Образцов входящих счетов"
 * 
 * @author leonova
 * @version $Id: BillHeadModelInMt.java,v 1.2 2007/05/22 04:54:03 sharapov Exp $
 */
public class BillHeadModelInMt extends DocumentModelMaintenanceForm {
	
	protected String[] contractorThroughKinds;
	protected String[] contractorResponsibleKinds;
	
	public BillHeadModelInMt() {
		super();
		contractorThroughKinds = new String[] {ContractorSearchForm.CONTRACTOR_PARTNER, ContractorSearchForm.CONTRACTOR_EMPLOYEE};
		contractorResponsibleKinds = new String[] {ContractorSearchForm.CONTRACTOR_EMPLOYEE, ContractorSearchForm.CONTRACTOR_ORGUNIT};
	}

}
