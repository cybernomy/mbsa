/*
 * WarehouseDocumentHeadInRest.java
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
 * @author leonova
 * @version $Id: WarehouseDocumentHeadInRest.java,v 1.3 2006/12/20 13:15:51 leonova Exp $
 */
public class WarehouseDocumentHeadInRest extends GoodsDocumentRest {

  public WarehouseDocumentHeadInRest() {
    contractorThroughKinds = new String[]{ContractorSearchForm.CONTRACTOR_PARTNER, ContractorSearchForm.CONTRACTOR_EMPLOYEE};
  }

}
