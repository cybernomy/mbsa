/*
 * CashDocumentInRest.java
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

package com.mg.merp.account.support.ui;

import com.mg.merp.reference.support.ui.ContractorSearchForm;

/**
 * @author leonova
 * @version $Id: CashDocumentInRest.java,v 1.2 2006/12/03 13:13:16 safonov Exp $
 */
public class CashDocumentInRest extends CashDocumentRest {

  public CashDocumentInRest() {
    contractorFromKinds = new String[]{ContractorSearchForm.CONTRACTOR_PARTNER, ContractorSearchForm.CONTRACTOR_EMPLOYEE};
    contractorToKinds = new String[]{ContractorSearchForm.CONTRACTOR_PARTNER};
  }

}
