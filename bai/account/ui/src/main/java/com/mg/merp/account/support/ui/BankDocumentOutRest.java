/*
 * BankDocumentOutRest.java
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

import com.mg.merp.reference.support.ui.ContractorSearchForm;

/**
 * Контроллер формы условий отбора "Исходящих банковских документов"
 *
 * @author leonova
 * @version $Id: BankDocumentOutRest.java,v 1.4 2007/11/12 06:51:28 sharapov Exp $
 */
public class BankDocumentOutRest extends BankDocumentRest {

  public BankDocumentOutRest() {
    contractorFromKinds = new String[]{ContractorSearchForm.CONTRACTOR_PARTNER};
    contractorToKinds = new String[]{ContractorSearchForm.CONTRACTOR_PARTNER};
  }

}
