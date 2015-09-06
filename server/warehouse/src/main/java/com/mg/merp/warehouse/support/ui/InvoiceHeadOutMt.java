/*
 * InvoiceHeadOutMt.java
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
import com.mg.merp.reference.support.ui.ContractorSearchForm;
import com.mg.merp.warehouse.InvoiceSpecOutServiceLocal;
import com.mg.merp.warehouse.generic.ui.AbstractWarehouseDocumentMaintenaceForm;
import com.mg.merp.warehouse.model.InvoiceSpec;

import java.util.Set;

/**
 * Контроллер формы поддержки исходящих накладных
 *
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: InvoiceHeadOutMt.java,v 1.8 2008/12/25 10:26:56 safonov Exp $
 */
public class InvoiceHeadOutMt extends AbstractWarehouseDocumentMaintenaceForm {
  protected String[] contractorResponsibleKinds;

  public InvoiceHeadOutMt() throws Exception {
    super();
    contractorThroughKinds = new String[]{ContractorSearchForm.CONTRACTOR_PARTNER, ContractorSearchForm.CONTRACTOR_EMPLOYEE};
    contractorResponsibleKinds = new String[]{ContractorSearchForm.CONTRACTOR_EMPLOYEE, ContractorSearchForm.CONTRACTOR_ORGUNIT};

    specService = ((InvoiceSpecOutServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/warehouse/InvoiceSpecOut"));

    spec.initController(specService, new InvoiceSpecMaintenanceEJBQLTableModel() {

      /* (non-Javadoc)
       * @see com.mg.merp.warehouse.support.ui.InvoiceSpecMaintenanceEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(InvoiceSpec.class, "Discount", "ds.Discount", false));
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
      }

    });

    addMasterModelListener(spec);
  }

}

