/*
 * FacturaHeadModelInMt.java
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
package com.mg.merp.factura.support.ui;

import com.mg.merp.document.generic.ui.DocumentModelMaintenanceForm;
import com.mg.merp.reference.support.ui.ContractorSearchForm;

/**
 * Конторллер формы поддержки "Образцов входящих счетов фактур"
 * 
 * @author leonova
 * @version $Id: FacturaHeadModelInMt.java,v 1.2 2007/05/22 09:04:23 sharapov Exp $
 */
public class FacturaHeadModelInMt extends DocumentModelMaintenanceForm {
	
	protected String[] contractorFromKinds;
	
	public FacturaHeadModelInMt() {
		super();
		contractorFromKinds = new String[] {ContractorSearchForm.CONTRACTOR_PARTNER};
	}
	
}
