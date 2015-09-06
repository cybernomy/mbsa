/*
 * InvoiceHeadInMt.java
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
package com.mg.merp.warehouse.support.ui;

import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.document.generic.ui.GoodsDocumentMaintenanceForm;
import com.mg.merp.reference.support.ui.ContractorSearchForm;
import com.mg.merp.warehouse.InvoiceSpecInServiceLocal;

import java.util.Set;

/**
 * Контроллер формы поддержки входящих накладных
 *
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: InvoiceHeadInMt.java,v 1.8 2008/12/25 10:26:25 safonov Exp $
 */
public class InvoiceHeadInMt extends GoodsDocumentMaintenanceForm {
  protected String[] contractorResponsibleKinds;

  public InvoiceHeadInMt() throws Exception {
    super();
    contractorThroughKinds = new String[]{ContractorSearchForm.CONTRACTOR_PARTNER, ContractorSearchForm.CONTRACTOR_EMPLOYEE};
    contractorResponsibleKinds = new String[]{ContractorSearchForm.CONTRACTOR_EMPLOYEE, ContractorSearchForm.CONTRACTOR_ORGUNIT};
    specService = ((InvoiceSpecInServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/warehouse/InvoiceSpecIn"));

    spec.initController(specService, new InvoiceSpecMaintenanceEJBQLTableModel() {

      /* (non-Javadoc)
       * @see com.mg.merp.warehouse.support.ui.InvoiceSpecMaintenanceEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(super.getDefaultFieldDefsSet(), service);
      }

    });

    addMasterModelListener(spec);
  }

}

