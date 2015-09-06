/*
 * CashDocumentModelOutMt.java
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
package com.mg.merp.account.support.ui;

import com.mg.merp.document.generic.ui.DocumentModelMaintenanceForm;
import com.mg.merp.reference.support.ui.ContractorSearchForm;

/**
 * Контроллер формы поддержки "Образцов расходных кассовых ордеров"
 *
 * @author leonova
 * @version $Id: CashDocumentModelOutMt.java,v 1.2 2007/05/22 08:05:45 sharapov Exp $
 */
public class CashDocumentModelOutMt extends DocumentModelMaintenanceForm {

  protected String[] contractorToKinds;
  protected String[] contractorFromKinds;

  public CashDocumentModelOutMt() {
    super();
    contractorToKinds = new String[]{ContractorSearchForm.CONTRACTOR_PARTNER, ContractorSearchForm.CONTRACTOR_EMPLOYEE};
    contractorFromKinds = new String[]{ContractorSearchForm.CONTRACTOR_PARTNER};
  }

}
