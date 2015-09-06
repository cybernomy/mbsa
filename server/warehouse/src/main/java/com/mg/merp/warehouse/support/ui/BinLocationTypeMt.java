/*
 * BinLocationTypeMt.java
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

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.warehouse.BinTypeCapacityByProductServiceLocal;
import com.mg.merp.warehouse.model.BinTypeCapacityByProduct;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: BinLocationTypeMt.java,v 1.5 2007/07/12 09:49:19 poroxnenko Exp $
 */
public class BinLocationTypeMt extends DefaultMaintenanceForm implements MasterModelListener {
  protected AttributeMap binTypeCapacityByProductProperties = new LocalDataTransferObject();
  private MaintenanceTableController binTypeCapacityByProduct;
  private BinTypeCapacityByProductServiceLocal binTypeCapacityByProductService;

  public BinLocationTypeMt() throws Exception {
    setMasterDetail(true);
    addMasterModelListener(this);

    binTypeCapacityByProductService = (BinTypeCapacityByProductServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/warehouse/BinTypeCapacityByProduct");
    binTypeCapacityByProduct = new MaintenanceTableController(binTypeCapacityByProductProperties);
    binTypeCapacityByProduct.initController(binTypeCapacityByProductService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from BinTypeCapacityByProduct btc %s where btc.Type = :binType";
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromsList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("binType");
        paramsValue.add(getEntity());
        return String.format(INIT_QUERY_TEXT, fieldsList, fromsList);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
       */
      @Override
      protected int getPrimaryKeyFieldIndex() {
        return 0;
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(BinTypeCapacityByProduct.class, "Id", "btc.Id", true));
        result.add(new TableEJBQLFieldDef(BinTypeCapacityByProduct.class, "Catalog.Code", "cat.Code", "left join btc.Catalog as cat", true));
        result.add(new TableEJBQLFieldDef(BinTypeCapacityByProduct.class, "Catalog.FullName", "cat.FullName", false));
        result.add(new TableEJBQLFieldDef(BinTypeCapacityByProduct.class, "Measure", "meas.Code", "left join btc.Measure as meas", false));
        result.add(new TableEJBQLFieldDef(BinTypeCapacityByProduct.class, "MaxQuantity", "btc.MaxQuantity", false));
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, binTypeCapacityByProductService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });

    addMasterModelListener(binTypeCapacityByProduct);


  }

  public void masterChange(ModelChangeEvent event) {
    binTypeCapacityByProductProperties.put("Type", event.getEntity());
  }

}
