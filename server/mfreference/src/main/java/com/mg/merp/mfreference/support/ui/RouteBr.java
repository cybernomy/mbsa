/*
 * RouteBr.java
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
package com.mg.merp.mfreference.support.ui;

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.mfreference.model.Route;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author leonova
 * @version $Id: RouteBr.java,v 1.2 2006/09/04 05:51:42 leonova Exp $
 */
public class RouteBr extends DefaultPlainBrowseForm {
  private final String INIT_QUERY_TEXT = "select %s from Route r %s %s";
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();

  public RouteBr() {
    super();
    restrictionFormName = "com/mg/merp/mfreference/resources/RouteRest.mfd.xml";
  }

  @Override
  protected String createQueryText() {
    String whereText = "";
    Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
    String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
    paramsName.clear();
    paramsValue.clear();
    RouteRest restForm = (RouteRest) getRestrictionForm();
    whereText = " where 0 = 0 ".
        concat(DatabaseUtils.formatEJBQLObjectRestriction("r.Catalog", restForm.getCatalogCode(), "catalogCode", paramsName, paramsValue, false)).
        concat(DatabaseUtils.formatEJBQLObjectRestriction("r.Customer", restForm.getCustomer(), "customer", paramsName, paramsValue, false)).
        concat(DatabaseUtils.formatEJBQLObjectRestriction("r.Vendor", restForm.getVendor(), "vendor", paramsName, paramsValue, false)).
        concat(DatabaseUtils.formatEJBQLObjectRestriction("r.SrcWarehouse", restForm.getSrcWarehouse(), "srcWarehouse", paramsName, paramsValue, false)).
        concat(DatabaseUtils.formatEJBQLObjectRestriction("r.DestWarehouse", restForm.getDestWarehouse(), "destWarehouse", paramsName, paramsValue, false)).
        concat(DatabaseUtils.formatEJBQLObjectRestriction("r.SrcType", restForm.getSrcType(), "srcType", paramsName, paramsValue, false)).
        concat(DatabaseUtils.formatEJBQLObjectRestriction("r.DestType", restForm.getDestType(), "destType", paramsName, paramsValue, false)).
//		concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("r.LeadTime", restForm.getLeadTimeFrom(), restForm.getLeadTimeTill(), "leadTimeFrom", "leadTimeTill", paramsName, paramsValue, false)).
    concat(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "r.Id", restForm.getAddinFieldsRestriction(), false));

    return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);

  }

  @Override
  protected MaintenanceTableModel createModel() {
    return new DefaultMaintenanceEJBQLTableModel() {

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(Route.class, "Id", "r.Id", true));
        result.add(new TableEJBQLFieldDef(Route.class, "Catalog.Code", "r.Catalog.Code", false));
        result.add(new TableEJBQLFieldDef(Route.class, "Catalog.FullName", "r.Catalog.FullName", false));
        result.add(new TableEJBQLFieldDef(Route.class, "Customer", "cus.Code", "left join r.Customer as cus", false));
        result.add(new TableEJBQLFieldDef(Route.class, "Vendor", "v.Code", "left join r.Vendor as v", false));
        result.add(new TableEJBQLFieldDef(Route.class, "SrcWarehouse", "sw.Code", "left join r.SrcWarehouse as sw", false));
        result.add(new TableEJBQLFieldDef(Route.class, "DestWarehouse", "dw.Code", "left join r.DestWarehouse as dw", false));
        result.add(new TableEJBQLFieldDef(Route.class, "DestType", "r.DestType", false));
        result.add(new TableEJBQLFieldDef(Route.class, "DestCycle", "r.DestCycle", false));
        result.add(new TableEJBQLFieldDef(Route.class, "SrcRank", "r.SrcRank", false));
        result.add(new TableEJBQLFieldDef(Route.class, "SupplyPercent", "r.SupplyPercent", false));
        result.add(new TableEJBQLFieldDef(Route.class, "SrcType", "r.SrcType", false));
        result.add(new TableEJBQLFieldDef(Route.class, "SrcCycle", "r.SrcCycle", false));
        result.add(new TableEJBQLFieldDef(Route.class, "ReorderMinQty", "r.ReorderMinQty", false));
        result.add(new TableEJBQLFieldDef(Route.class, "QcReceivingDays", "r.QcReceivingDays", false));
        result.add(new TableEJBQLFieldDef(Route.class, "ReorderLotSize", "r.ReorderLotSize", false));
        result.add(new TableEJBQLFieldDef(Route.class, "LeadTime", "r.LeadTime", false));
        result.add(new TableEJBQLFieldDef(Route.class, "SafetyDays", "r.SafetyDays", false));
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);

      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
       */
      @Override
      protected int getPrimaryKeyFieldIndex() {
        return 0;
      }

    };

  }

}
