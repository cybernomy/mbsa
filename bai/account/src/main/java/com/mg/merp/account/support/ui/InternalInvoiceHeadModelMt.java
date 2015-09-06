/*
 * AdvanceRepHeadMt.java
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

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.generic.ui.DefaultCompoundMaintenanceForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.account.InternalInvoiceSpecModelServiceLocal;
import com.mg.merp.document.model.DocSpecModel;
import com.mg.merp.reference.support.ui.ContractorSearchForm;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы поддержи "Образцов внутренних накладных"
 *
 * @author leonova
 * @version $Id: InternalInvoiceHeadModelMt.java,v 1.7 2008/03/20 13:43:21 alikaev Exp $
 */
public class InternalInvoiceHeadModelMt extends DefaultCompoundMaintenanceForm implements MasterModelListener {

  protected AttributeMap specProperties = new LocalDataTransferObject();
  protected String[] contractorThroughKinds;
  private MaintenanceTableController spec;
  private InternalInvoiceSpecModelServiceLocal specService;

  public InternalInvoiceHeadModelMt() throws Exception {
    super();

    contractorThroughKinds = new String[]{ContractorSearchForm.CONTRACTOR_EMPLOYEE};

    specService = (InternalInvoiceSpecModelServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/InternalInvoiceSpecModel"); //$NON-NLS-1$
    spec = new MaintenanceTableController(specProperties);
    spec.initController(specService, new DefaultMaintenanceEJBQLTableModel() {
      private String INIT_QUERY_TEXT = "select %s from DocSpecModel dsm %s where dsm.DocHeadModel = :docHeadModel"; //$NON-NLS-1$
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      /* (non-Javadoc)
       * @see com.mg.merp.warehouse.support.ui.GoodsDocMaintenanceEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(DocSpecModel.class, "Id", "dsm.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(DocSpecModel.class, "Catalog.Code", "dsm.Catalog.Code", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(DocSpecModel.class, "Catalog.FullName", "dsm.Catalog.FullName", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(DocSpecModel.class, "Catalog.Measure1", "meas1.Code", "left join dsm.Catalog.Measure1 as meas1", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(DocSpecModel.class, "Catalog.Measure2", "meas2.Code", "left join dsm.Catalog.Measure2 as meas2", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(DocSpecModel.class, "Price", "dsm.Price", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(DocSpecModel.class, "Summa", "dsm.Summa", false));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(DocSpecModel.class, "BestBefore", "dsm.BestBefore", false));                                         //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(DocSpecModel.class, "Weight", "dsm.Weight", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(DocSpecModel.class, "Volume", "dsm.Volume", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(DocSpecModel.class, "Catalog.Articul", "dsm.Catalog.Articul", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(DocSpecModel.class, "Comment", "dsm.Comment", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(DocSpecModel.class, "Contractor", "contr.Code", "left join dsm.Contractor as contr", false));             //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(DocSpecModel.class, "SrcStock", "ss.Code", "left join dsm.SrcStock as ss", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(DocSpecModel.class, "SrcMol", "sm.Code", "left join dsm.SrcMol as sm", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(DocSpecModel.class, "DstStock", "dst.Code", "left join dsm.DstStock as dst", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(DocSpecModel.class, "DstMol", "dm.Code", "left join dsm.DstMol as dm", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(DocSpecModel.class, "TaxGroup", "tg.Code", "left join dsm.TaxGroup as tg", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, specService);
      }

      /* (non-Javadoc)
       * @see com.mg.merp.warehouse.support.ui.GoodsDocMaintenanceEJBQLTableModel#createQueryText()
       */
      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("docHeadModel"); //$NON-NLS-1$
        paramsValue.add(getEntity());
        return String.format(INIT_QUERY_TEXT, fieldsList, fromList);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }

      /* (non-Javadoc)
       * @see com.mg.merp.warehouse.support.ui.GoodsDocMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
       */
      @Override
      protected int getPrimaryKeyFieldIndex() {
        return 0;
      }
    });
    addMasterModelListener(spec);

    addMasterModelListener(this);
  }

	/* (non-Javadoc)
     * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */

  public void masterChange(ModelChangeEvent event) {
    specProperties.put("DocHeadModel", event.getEntity());     //$NON-NLS-1$
  }

}
