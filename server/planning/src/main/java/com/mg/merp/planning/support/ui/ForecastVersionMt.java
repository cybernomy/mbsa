/*
 * ForecastVersionMt.java
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
import com.mg.merp.planning.PlanningForecastServiceLocal;
import com.mg.merp.planning.model.PlanningForecast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы поддержки версий прогнозов
 *
 * @author leonova
 * @version $Id: ForecastVersionMt.java,v 1.6 2007/07/30 10:36:31 safonov Exp $
 */
public class ForecastVersionMt extends DefaultMaintenanceForm implements MasterModelListener {
  protected AttributeMap lineForecastProperties = new LocalDataTransferObject();
  private MaintenanceTableController lineForecast;
  private PlanningForecastServiceLocal lineForecastService;

  public ForecastVersionMt() throws Exception {
    setMasterDetail(true);
    lineForecastService = (PlanningForecastServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/planning/PlanningForecast");
    lineForecast = new MaintenanceTableController(lineForecastProperties);
    lineForecast.initController(lineForecastService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from PlanningForecast pf %s where pf.ForecastVersion = :forecastversion";
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("forecastversion");
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
        result.add(new TableEJBQLFieldDef(PlanningForecast.class, "Id", "pf.Id", true));
        result.add(new TableEJBQLFieldDef(PlanningForecast.class, "PlanningItem", "gi.GenericItemCode", "left join pf.PlanningItem as gi", false));
        result.add(new TableEJBQLFieldDef(PlanningForecast.class, "PlanningLevel", "pf.PlanningLevel.PlanningLevelNum", false));
        result.add(new TableEJBQLFieldDef(PlanningForecast.class, "Measure", "pf.Measure.Code", false));
        result.add(new TableEJBQLFieldDef(PlanningForecast.class, "Catalog", "cat.Code", "left join pf.Catalog as cat", false));
        result.add(new TableEJBQLFieldDef(PlanningForecast.class, "Contractor", "contr.Code", "left join pf.Contractor as contr", false));
        result.add(new TableEJBQLFieldDef(PlanningForecast.class, "ForecastType", "pf.ForecastType", false));
        result.add(new TableEJBQLFieldDef(PlanningForecast.class, "ForecastMethod", "pf.ForecastMethod", false));
        result.add(new TableEJBQLFieldDef(PlanningForecast.class, "ForecastQuantity", "pf.ForecastQuantity", false));
        result.add(new TableEJBQLFieldDef(PlanningForecast.class, "BucketOffset", "pf.BucketOffset", false));
        result.add(new TableEJBQLFieldDef(PlanningForecast.class, "RequiredDate", "pf.RequiredDate", false));
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, lineForecastService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });

    addMasterModelListener(lineForecast);

    addMasterModelListener(this);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
   */
  public void masterChange(ModelChangeEvent event) {
    lineForecastProperties.put("ForecastVersion", event.getEntity());
  }

}
