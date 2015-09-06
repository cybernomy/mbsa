/*
 * ForecastVersionBr.java
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

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.planning.model.ForecastVersion;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Браузер версий прогнозов
 *
 * @author leonova
 * @version $Id: ForecastVersionBr.java,v 1.2 2006/08/25 10:16:38 leonova Exp $
 */
public class ForecastVersionBr extends DefaultPlainBrowseForm {
  private final String INIT_QUERY_TEXT = "select  %s from ForecastVersion fv";
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();

  public ForecastVersionBr() {
    super();
//		restrictionFormName = "com/mg/merp/planning/resources/ForecastVersionRest.mfd.xml";
  }

  @Override
  protected String createQueryText() {
    String whereText = "";
    String fromList = "";
    Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
    String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    paramsName.clear();
    paramsValue.clear();
    //	ForecastVersionRest restForm = (ForecastVersionRest) getRestrictionForm();
/*		whereText = " where 0 = 0 ".
                concat(DatabaseUtils.formatEJBQLStringRestriction("fv.Code", restForm.getCode(), "code", paramsName, paramsValue, false)).
		concat(DatabaseUtils.formatEJBQLStringRestriction("fv.Description", restForm.getDescription(), "description", paramsName, paramsValue, false));		
		//concat(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "fv.Id", restForm.getAddinFieldsRestriction(), false));
	/*	if (restForm.getPlanningItemCode() != null || restForm.getCatalogCode() != null ||
				restForm.getForecastMethod() != null || restForm.getForecastType() != null ||
				restForm.getWarehouseCode() != null || restForm.getBucketStartDate() != null ||
				restForm.getPlanningLevelCode() != null || restForm.getBucketStartDate() != null ||
				restForm.getBucketEndDate() != null || !restForm.getBucketOffset().equals("")) {
			whereText = whereText.concat(DatabaseUtils.formatEJBQLObjectRestriction("pf.Catalog", restForm.getCatalogCode(), "catalogCode", paramsName, paramsValue, false)).
			concat(DatabaseUtils.formatEJBQLObjectRestriction("pf.GenericItem", restForm.getPlanningItemCode(), "planningItemCode", paramsName, paramsValue, false)).
			concat(DatabaseUtils.formatEJBQLObjectRestriction("pf.ForecastMethod", restForm.getForecastMethod(), "forecastMethod", paramsName, paramsValue, false)).
			concat(DatabaseUtils.formatEJBQLObjectRestriction("pf.ForecastType", restForm.getForecastType(), "forecastType", paramsName, paramsValue, false)).
			concat(DatabaseUtils.formatEJBQLObjectRestriction("pf.Contractor", restForm.getWarehouseCode(), "warehouseCode", paramsName, paramsValue, false)).
			concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("pf.RequiredDate", restForm.getBucketStartDate(), restForm.getBucketEndDate(), "bucketStartDate", "bucketEndDate", paramsName, paramsValue, false)).
			concat(DatabaseUtils.formatEJBQLObjectRestriction("pf.PlanningLevel", restForm.getPlanningLevelCode(), "planningLevelCode", paramsName, paramsValue, false)).
			concat(DatabaseUtils.formatEJBQLObjectRestriction("pf.BucketStartDate", restForm.getBucketStartDate(), "bucketStartDate", paramsName, paramsValue, false)).
			concat(DatabaseUtils.formatEJBQLObjectRestriction("pf.BucketEndDate", restForm.getBucketEndDate(), "bucketEndDate", paramsName, paramsValue, false)).			
			concat(DatabaseUtils.formatEJBQLObjectRestriction("pf.BucketOffset", restForm.getBucketOffset(), "bucketOffset", paramsName, paramsValue, false)).
			concat(" and pf.ForecastVersion = fv.Id ");
			fromList = ", PlanningForecast pf";
		}*/
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
        result.add(new TableEJBQLFieldDef(ForecastVersion.class, "Id", "fv.Id", true));
        result.add(new TableEJBQLFieldDef(ForecastVersion.class, "Code", "fv.Code", false));
        result.add(new TableEJBQLFieldDef(ForecastVersion.class, "Description", "fv.Description", false));
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

