/*
 * FacturaHeadModelOutMt.java
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
 * Конторллер формы поддержки "Образцов исходящих счетов фактур"
 *
 * @author leonova
 * @version $Id: FacturaHeadModelOutMt.java,v 1.2 2007/05/22 09:08:19 sharapov Exp $
 */
public class FacturaHeadModelOutMt extends DocumentModelMaintenanceForm {

  protected String[] contractorToKinds;

  public FacturaHeadModelOutMt() {
    super();
    contractorToKinds = new String[]{ContractorSearchForm.CONTRACTOR_PARTNER};
  }

}
