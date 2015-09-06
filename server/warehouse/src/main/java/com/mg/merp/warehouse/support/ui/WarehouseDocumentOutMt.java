/*
 * WarehouseDocumentHeadOutMt.java
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
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.reference.support.ui.ContractorSearchForm;
import com.mg.merp.warehouse.WarehouseDocumentSpecOutServiceLocal;
import com.mg.merp.warehouse.generic.ui.AbstractWarehouseDocumentMaintenaceForm;
import com.mg.merp.warehouse.model.StockDocumentSpec;

import java.util.Set;

/**
 * Контроллер формы поддержки расходных ордеров
 *
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: WarehouseDocumentOutMt.java,v 1.10 2009/02/05 10:03:53 sharapov Exp $
 */
public class WarehouseDocumentOutMt extends AbstractWarehouseDocumentMaintenaceForm {

  public WarehouseDocumentOutMt() throws Exception {
    super();
    contractorThroughKinds = new String[]{ContractorSearchForm.CONTRACTOR_PARTNER, ContractorSearchForm.CONTRACTOR_EMPLOYEE};

    specService = ((WarehouseDocumentSpecOutServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/warehouse/WarehouseDocumentSpecOut"));

    spec.initController(specService, new WarehouseDocSpecMaintenanceEJBQLTableModel() {

      /* (non-Javadoc)
       * @see com.mg.merp.warehouse.support.ui.GoodsDocMaintenanceEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(DocSpec.class, "Price1", "ds.Price1", false));
        result.add(new TableEJBQLFieldDef(DocSpec.class, "Summa1", "ds.Summa1", false));
        result.add(new TableEJBQLFieldDef(StockDocumentSpec.class, "Discount", "ds.Discount", false));
        result.add(new TableEJBQLFieldDef(DocSpec.class, "CountryOfOrigin", "co.CName", "left join ds.CountryOfOrigin co", false));
        result.add(new TableEJBQLFieldDef(DocSpec.class, "CustomsDeclaration", "cd.Number", "left join ds.CustomsDeclaration cd", false));
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
      }

      /* (non-Javadoc)
       * @see com.mg.merp.document.generic.ui.GoodsDocSpecMaintenanceEJBQLTableModel#getDocSpecModelName()
       */
      @Override
      protected String getDocSpecModelName() {
        return StockDocumentSpec.class.getName();
      }

    });

    addMasterModelListener(spec);
  }

}

