/*
 * MRPVersionControlBr.java
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
import com.mg.merp.planning.MRPProcessorServiceLocal;
import com.mg.merp.planning.MRPRecommendationServiceLocal;
import com.mg.merp.planning.MRPReportServiceLocal;
import com.mg.merp.planning.model.MrpVersionControl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Котроллер формы списка версий ППМ
 *
 * @author Oleg V. Safonov
 * @author leonova
 * @version $Id: MRPVersionControlBr.java,v 1.5 2007/08/06 13:56:32 safonov Exp $
 */
public class MRPVersionControlBr extends DefaultPlainBrowseForm {
  private final String INIT_QUERY_TEXT = "select distinct %s from MrpVersionControl m %s %s";
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();

  public MRPVersionControlBr() {
    super();
    restrictionFormName = "com/mg/merp/planning/resources/MRPVersionControlRest.mfd.xml";
  }

  @Override
  protected String createQueryText() {
    //String whereText = "";
    Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
    String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    StringBuilder fromList = new StringBuilder(DatabaseUtils.generateEJBQLFromClause(fieldDefs));
    MRPVersionControlRest restForm = (MRPVersionControlRest) getRestrictionForm();
    paramsName.clear();
    paramsValue.clear();
    StringBuilder whereText = new StringBuilder(" where 0 = 0 ")
        .append(DatabaseUtils.formatEJBQLObjectRangeRestriction("m.MrpStartDate", restForm.getStartDateFrom(), restForm.getStartDateTill(), "startDateFrom", "startDateTill", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRangeRestriction("m.MrpEndDate", restForm.getEndDateFrom(), restForm.getEndDateTill(), "endDateFrom", "endDateTill", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "m.Id", restForm.getAddinFieldsRestriction(), false));
    if (restForm.getMpsCode() != null) {
      whereText.append(DatabaseUtils.formatEJBQLObjectRestriction("mrpmps.Mps", restForm.getMpsCode(), "mps", paramsName, paramsValue, false))
          .append(" and mrpmps.MrpVersionControl = m.Id ");
      fromList.append(", MrpVersionMps mrpmps ");
    }
    if (restForm.getForecastVersionCode() != null) {
      whereText.append(DatabaseUtils.formatEJBQLObjectRestriction("mrpf.ForecastVersion", restForm.getForecastVersionCode(), "forecastVersion", paramsName, paramsValue, false))
          .append(" and mrpf.MrpVersionControl = m.Id ");
      fromList.append(", MrpVersionForecast mrpf ");
    }
    return String.format(INIT_QUERY_TEXT, fieldsList, fromList.toString(), whereText.toString());

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
        result.add(new TableEJBQLFieldDef(MrpVersionControl.class, "Id", "m.Id", true));
        result.add(new TableEJBQLFieldDef(MrpVersionControl.class, "Code", "m.Code", false));
        result.add(new TableEJBQLFieldDef(MrpVersionControl.class, "Description", "m.Description", false));
        result.add(new TableEJBQLFieldDef(MrpVersionControl.class, "MrpVersion", "m.MrpVersion", false));
        result.add(new TableEJBQLFieldDef(MrpVersionControl.class, "MrpSoFlag", "m.MrpSoFlag", false));
        result.add(new TableEJBQLFieldDef(MrpVersionControl.class, "MrpSfFlag", "m.MrpSfFlag", false));
        result.add(new TableEJBQLFieldDef(MrpVersionControl.class, "MrpPoFlag", "m.MrpPoFlag", false));
        result.add(new TableEJBQLFieldDef(MrpVersionControl.class, "MrpPfFlag", "m.MrpPfFlag", false));
        result.add(new TableEJBQLFieldDef(MrpVersionControl.class, "MrpQohFlag", "m.MrpQohFlag", false));
        result.add(new TableEJBQLFieldDef(MrpVersionControl.class, "MrpFirmPlannedOrdersFlag", "m.MrpFirmPlannedOrdersFlag", false));
        result.add(new TableEJBQLFieldDef(MrpVersionControl.class, "MrpJobFlag", "m.MrpJobFlag", false));
        result.add(new TableEJBQLFieldDef(MrpVersionControl.class, "DampingDays", "m.DampingDays", false));
        result.add(new TableEJBQLFieldDef(MrpVersionControl.class, "QcReceivingDays", "m.QcReceivingDays", false));
        result.add(new TableEJBQLFieldDef(MrpVersionControl.class, "RunDate", "m.RunDate", false));
        result.add(new TableEJBQLFieldDef(MrpVersionControl.class, "MrpStartDate", "m.MrpStartDate", false));
        result.add(new TableEJBQLFieldDef(MrpVersionControl.class, "MrpEndDate", "m.MrpEndDate", false));
        result.add(new TableEJBQLFieldDef(MrpVersionControl.class, "MrpSuggestedOrdersFirmed", "m.MrpSuggestedOrdersFirmed", false));
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

  public void onActionShowMRPReport(WidgetEvent event) throws Exception {
    final MRPReportServiceLocal service = (MRPReportServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/planning/MRPReport");
    Serializable[] keys = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
    for (Serializable key : keys) {
      MRPReportBr form = (MRPReportBr) ApplicationDictionaryLocator.locate().getBrowseForm(service, null);
      form.execute(key);
    }
  }

  public void onActionShowMRPRecommendation(WidgetEvent event) throws Exception {
    final MRPRecommendationServiceLocal service = (MRPRecommendationServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/planning/MRPRecommendation");
    Serializable[] keys = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
    for (Serializable key : keys) {
      MRPRecommendationBr form = (MRPRecommendationBr) ApplicationDictionaryLocator.locate().getBrowseForm(service, null);
      form.execute(key);
    }
  }

  public void onActionGenerateMPS(WidgetEvent event) {
    Serializable[] keys = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
    MRPProcessorServiceLocal processor = (MRPProcessorServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(MRPProcessorServiceLocal.SERVICE_NAME);
    for (Serializable key : keys)
      processor.generateMrp((Integer) key);
  }

}

