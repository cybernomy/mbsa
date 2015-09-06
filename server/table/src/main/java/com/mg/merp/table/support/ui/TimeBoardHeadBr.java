/*
 * TimeBoardHeadBr.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.table.support.ui;

import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.table.TimeBoardPositionServiceLocal;
import com.mg.merp.table.model.TimeBoardHead;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер браузера заголовков табелей
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: TimeBoardHeadBr.java,v 1.6 2008/08/12 14:38:08 sharapov Exp $
 */
public class TimeBoardHeadBr extends DefaultPlainBrowseForm {

  private final String INIT_QUERY_TEXT = "select %s from TimeBoardHead tbh %s"; //$NON-NLS-1$
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();

  public TimeBoardHeadBr() {
    super();
    restrictionFormName = "com/mg/merp/table/resources/TimeBoardHeadRest.mfd.xml"; //$NON-NLS-1$
  }

  @Override
  protected String createQueryText() {
    String whereText = ""; //$NON-NLS-1$
    Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
    String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    paramsName.clear();
    paramsValue.clear();
    TimeBoardHeadRest restForm = (TimeBoardHeadRest) getRestrictionForm();
    whereText = " where 0 = 0 ".concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("tbh.CalcPeriod.BeginDate", restForm.getBeginDate(), restForm.getEndDate(), "beginDate", "endDate", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("tbh.CalcPeriod.EndDate", restForm.getBeginDate(), restForm.getEndDate(), "beginDate", "endDate", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        concat(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "tbh.Id", restForm.getAddinFieldsRestriction(), false)); //$NON-NLS-1$
    return String.format(INIT_QUERY_TEXT, fieldsList, whereText);
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
        result.add(new TableEJBQLFieldDef(TimeBoardHead.class, "Id", "tbh.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TimeBoardHead.class, "BNumber", "tbh.BNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TimeBoardHead.class, "CalcPeriod", "tbh.CalcPeriod.PName", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TimeBoardHead.class, "Comments", "tbh.Comments", false)); //$NON-NLS-1$ //$NON-NLS-2$
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

  public void onActionShowPosition(WidgetEvent event) throws Exception {
    final TimeBoardPositionServiceLocal service = (TimeBoardPositionServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/table/TimeBoardPosition"); //$NON-NLS-1$
    TimeBoardPositionBr form = (TimeBoardPositionBr) ApplicationDictionaryLocator.locate().getBrowseForm(service, null);
    Serializable[] keys = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
    for (Serializable key : keys) {
      form.setTimeBoardHeadId(key);
      form.run();
      break;
    }
  }

  public void onActionShowTable(WidgetEvent event) throws Exception {
    TimeBoardSpecBr form = (TimeBoardSpecBr) ApplicationDictionaryLocator.locate().getWindow(TimeBoardSpecBr.WINDOW_NAME);
    Serializable[] keys = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
    for (Serializable key : keys) {
      form.setTableHeadId(key);
      form.run();
      break;
    }
  }

}