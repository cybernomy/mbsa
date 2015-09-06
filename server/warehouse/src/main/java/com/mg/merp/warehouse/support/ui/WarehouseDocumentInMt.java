/*
 * WarehouseDocumentInMt.java
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
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.reference.support.ui.ContractorSearchForm;
import com.mg.merp.warehouse.WarehouseDocumentSpecInServiceLocal;
import com.mg.merp.warehouse.model.StockDocumentSpec;

import java.util.Set;

/**
 * Контроллер формы поддержки приходных ордеров
 *
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: WarehouseDocumentInMt.java,v 1.8 2009/02/05 10:03:53 sharapov Exp $
 */
public class WarehouseDocumentInMt extends GoodsDocumentMaintenanceForm {

  public WarehouseDocumentInMt() throws Exception {
    super();
    contractorThroughKinds = new String[]{ContractorSearchForm.CONTRACTOR_PARTNER, ContractorSearchForm.CONTRACTOR_EMPLOYEE};

    specService = ((WarehouseDocumentSpecInServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/warehouse/WarehouseDocumentSpecIn"));

    spec.initController(specService, new WarehouseDocSpecMaintenanceEJBQLTableModel() {

      /* (non-Javadoc)
       * @see com.mg.merp.warehouse.support.ui.GoodsDocMaintenanceEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(DocSpec.class, "Price1", "ds.Price1", false));
        result.add(new TableEJBQLFieldDef(DocSpec.class, "Summa1", "ds.Summa1", false));
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
