/*
 * InternalActHeadModelMt.java
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
 * Контроллер формы поддержи "Образцов внутренних актов"
 *
 * @author leonova
 * @version $Id: InternalActHeadModelMt.java,v 1.2 2007/05/22 08:19:30 sharapov Exp $
 */
public class InternalActHeadModelMt extends DocumentModelMaintenanceForm {

  protected String[] contractorThroughKinds;

  public InternalActHeadModelMt() {
    super();
    contractorThroughKinds = new String[]{ContractorSearchForm.CONTRACTOR_EMPLOYEE};
  }

}
