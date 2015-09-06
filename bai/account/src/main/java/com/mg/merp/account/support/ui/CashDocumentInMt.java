/*
 * CashDocumentInMt.java
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
package com.mg.merp.account.support.ui;

import com.mg.merp.document.generic.ui.DocumentMaintenanceForm;
import com.mg.merp.reference.support.ui.ContractorSearchForm;

/**
 * @author leonova
 * @version $Id: CashDocumentInMt.java,v 1.2 2007/01/13 10:44:41 safonov Exp $
 */
public class CashDocumentInMt extends DocumentMaintenanceForm {

  public CashDocumentInMt() {
    super();
    contractorFromKinds = new String[]{ContractorSearchForm.CONTRACTOR_PARTNER, ContractorSearchForm.CONTRACTOR_EMPLOYEE};
    contractorToKinds = new String[]{ContractorSearchForm.CONTRACTOR_PARTNER};

  }

}
