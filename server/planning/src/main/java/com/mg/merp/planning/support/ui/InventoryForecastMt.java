/*
 * InventoryForecastMt.java
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
package com.mg.merp.planning.support.ui;

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
import com.mg.merp.planning.InventoryForecastLineServiceLocal;
import com.mg.merp.planning.model.InventoryForecastLine;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * Контроллер формы поддержки прогнозов складских запасов
 *
 * @author leonova
 * @version $Id: InventoryForecastMt.java,v 1.6 2007/09/06 04:46:46 alikaev Exp $
 */
public class InventoryForecastMt extends DefaultMaintenanceForm implements MasterModelListener {

  protected AttributeMap invForecastLineProperties = new LocalDataTransferObject();
  private MaintenanceTableController invForecastLine;
  private InventoryForecastLineServiceLocal invForecastLineService;

  public InventoryForecastMt() throws Exception {
    addMasterModelListener(this);
    setMasterDetail(true);
    invForecastLineService = (InventoryForecastLineServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/planning/InventoryForecastLine");
    invForecastLine = new MaintenanceTableController(invForecastLineProperties);
    invForecastLine.initController(invForecastLineService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from InventoryForecastLine ifl %s where ifl.InventoryForecast = :invforecast";
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("invforecast");
        paramsValue.add(getEntity());
        return String.format(INIT_QUERY_TEXT, fieldsList, fromList);
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
        result.add(new TableEJBQLFieldDef(InventoryForecastLine.class, "Id", "ifl.Id", true));
        result.add(new TableEJBQLFieldDef(InventoryForecastLine.class, "GenericItem", "gi.GenericItemCode", "left join ifl.GenericItem as gi", false));
        result.add(new TableEJBQLFieldDef(InventoryForecastLine.class, "Warehouse", "w.Code", "left join ifl.Warehouse as w", false));
        result.add(new TableEJBQLFieldDef(InventoryForecastLine.class, "Measure", "m.Code", "left join ifl.Measure as m", false));
        result.add(new TableEJBQLFieldDef(InventoryForecastLine.class, "EffOnDate", "ifl.EffOnDate", false));
        result.add(new TableEJBQLFieldDef(InventoryForecastLine.class, "EffOffDate", "ifl.EffOffDate", false));
        result.add(new TableEJBQLFieldDef(InventoryForecastLine.class, "QtyOnHand", "ifl.QtyOnHand", false));
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, invForecastLineService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });

    addMasterModelListener(invForecastLine);

  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
   */
  public void masterChange(ModelChangeEvent event) {
    invForecastLineProperties.put("InventoryForecast", event.getEntity());
  }

}
