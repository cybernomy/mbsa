/*
 * GenericItemBr.java
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

import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.planning.GenericItemServiceLocal;
import com.mg.merp.planning.model.GenericItem;

import org.apache.commons.lang.BooleanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Браузер обобщенных товаров
 *
 * @author leonova
 * @version $Id: GenericItemBr.java,v 1.4 2009/02/06 09:43:11 safonov Exp $
 */
public class GenericItemBr extends DefaultPlainBrowseForm {
  private final String INIT_QUERY_TEXT = "select %s from GenericItem gi %s %s";
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();


  public GenericItemBr() {
    super();
    restrictionFormName = "com/mg/merp/planning/resources/GenericItemRest.mfd.xml";
  }

  @Override
  protected String createQueryText() {
    Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
    String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
    GenericItemRest restForm = (GenericItemRest) getRestrictionForm();
    paramsName.clear();
    paramsValue.clear();
    StringBuilder whereText = new StringBuilder(" where 0 = 0 ")
        .append(DatabaseUtils.formatEJBQLStringRestriction("gi.GenericItemCode", restForm.getGenericItemCode(), "genericItemCode", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLStringRestriction("gi.GenericItemName", restForm.getGenericItemName(), "genericItemName", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRangeRestriction("gi.PlanningShelfLife", restForm.getPlanningShelfLifeFrom(), restForm.getPlanningShelfLifeTill(), "planningShelfLifeFrom", "planningShelfLifeTill", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "gi.Id", restForm.getAddinFieldsRestriction(), false));
    if (restForm.getPlanningItemFlag() != 0) {
      whereText = whereText.append(DatabaseUtils.formatEJBQLObjectRestriction("gi.PlanningItemFlag", BooleanUtils.toBoolean(restForm.getPlanningItemFlag(), 2, 1), "planningItemFlag", paramsName, paramsValue, false));
    }
    return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText.toString());

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
        result.add(new TableEJBQLFieldDef(GenericItem.class, "Id", "gi.Id", true));
        result.add(new TableEJBQLFieldDef(GenericItem.class, "GenericItemCode", "gi.GenericItemCode", false));
        result.add(new TableEJBQLFieldDef(GenericItem.class, "GenericItemName", "gi.GenericItemName", false));
        result.add(new TableEJBQLFieldDef(GenericItem.class, "PlanningItemFlag", "gi.PlanningItemFlag", false));
        result.add(new TableEJBQLFieldDef(GenericItem.class, "PlanningShelfLife", "gi.PlanningShelfLife", false));
        result.add(new TableEJBQLFieldDef(GenericItem.class, "DemandFenceDays", "gi.DemandFenceDays", false));
        result.add(new TableEJBQLFieldDef(GenericItem.class, "DaysStockCover", "gi.DaysStockCover", false));
        result.add(new TableEJBQLFieldDef(GenericItem.class, "LowLevelCode", "gi.LowLevelCode", false));
        result.add(new TableEJBQLFieldDef(GenericItem.class, "Measure", "gi.Measure.Code", false));
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

  /**
   * обработчик расчета кодов нижнего уровня
   */
  public void onActionBuildLowLevelCodes(WidgetEvent event) {
    ((GenericItemServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(GenericItemServiceLocal.SERVICE_NAME)).buildLowLevelCodes();
    table.refresh();
  }

}
