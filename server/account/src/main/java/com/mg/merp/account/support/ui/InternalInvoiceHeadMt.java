/*
 * AdvanceRepHeadMt.java
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
package com.mg.merp.account.support.ui;

import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.account.InternalInvoiceSpecServiceLocal;
import com.mg.merp.account.model.InternalInvoiceSpec;
import com.mg.merp.document.generic.ui.GoodsDocSpecMaintenanceEJBQLTableModel;
import com.mg.merp.document.generic.ui.GoodsDocumentMaintenanceForm;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.reference.support.ui.ContractorSearchForm;

import java.util.Set;

/**
 * Контроллер формы поддержки внутренних накладных
 *
 * @author leonova
 * @version $Id: InternalInvoiceHeadMt.java,v 1.8 2009/02/05 09:18:28 sharapov Exp $
 */
public class InternalInvoiceHeadMt extends GoodsDocumentMaintenanceForm {

  public InternalInvoiceHeadMt() throws Exception {
    super();
    contractorThroughKinds = new String[]{ContractorSearchForm.CONTRACTOR_EMPLOYEE};

    specService = ((InternalInvoiceSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/InternalInvoiceSpec"));

    spec.initController(specService, new GoodsDocSpecMaintenanceEJBQLTableModel() {

      /* (non-Javadoc)
       * @see com.mg.merp.warehouse.support.ui.GoodsDocMaintenanceEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(DocSpec.class, "Price", "ds.Price", false));
        result.add(new TableEJBQLFieldDef(DocSpec.class, "Summa", "ds.Summa", false));
        result.add(new TableEJBQLFieldDef(DocSpec.class, "SrcStock", "ss.Code", "left join ds.SrcStock as ss", false));
        result.add(new TableEJBQLFieldDef(DocSpec.class, "SrcMol", "sm.Code", "left join ds.SrcMol as sm", false));
        result.add(new TableEJBQLFieldDef(DocSpec.class, "DstStock", "dst.Code", "left join ds.DstStock as dst", false));
        result.add(new TableEJBQLFieldDef(DocSpec.class, "DstMol", "dm.Code", "left join ds.DstMol as dm", false));
        result.add(new TableEJBQLFieldDef(DocSpec.class, "BestBefore", "ds.BestBefore", false));
        result.add(new TableEJBQLFieldDef(InternalInvoiceSpec.class, "RequestQuan", "ds.RequestQuan", false));
        result.add(new TableEJBQLFieldDef(InternalInvoiceSpec.class, "RequestSumma", "ds.RequestSumma", false));
        result.add(new TableEJBQLFieldDef(DocSpec.class, "CountryOfOrigin", "co.CName", "left join ds.CountryOfOrigin co", false));
        result.add(new TableEJBQLFieldDef(DocSpec.class, "CustomsDeclaration", "cd.Number", "left join ds.CustomsDeclaration cd", false));
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
      }

      /* (non-Javadoc)
       * @see com.mg.merp.document.generic.ui.GoodsDocSpecMaintenanceEJBQLTableModel#getDocSpecModelName()
       */
      @Override
      protected String getDocSpecModelName() {
        return InternalInvoiceSpec.class.getName();
      }

    });

    addMasterModelListener(spec);
  }

}

