/*
 * FacturaHeadInRest.java
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

package com.mg.merp.factura.support.ui;

import com.mg.merp.document.generic.ui.GoodsDocumentRest;
import com.mg.merp.reference.support.ui.ContractorSearchForm;

/**
 * @author leonova
 * @version $Id: FacturaHeadInRest.java,v 1.3 2006/12/20 12:30:33 leonova Exp $
 */
public class FacturaHeadInRest extends GoodsDocumentRest {

  public FacturaHeadInRest() {
    contractorFromKinds = new String[]{ContractorSearchForm.CONTRACTOR_PARTNER};
  }
}
