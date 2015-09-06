/*
 * DayCalendarBr.java
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

import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.generic.ui.DefaultMDBrowseForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.mfreference.DayTimeServiceLocal;
import com.mg.merp.mfreference.model.DayCalendar;
import com.mg.merp.mfreference.model.DayTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Браузер дневного календаря
 *
 * @author Oleg V. Safonov
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: DayCalendarBr.java,v 1.5 2006/10/02 08:07:24 leonova Exp $
 */
public class DayCalendarBr extends DefaultMDBrowseForm implements MasterModelListener {
  private final String LOAD_MASTER_EJBQL = "select %s from DayCalendar dc"; //$NON-NLS-1$
  private final String LOAD_DETAIL_EJBQL = "select %s from DayTime dt where dt.DayCal.Id = :dayCal"; //$NON-NLS-1$
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();


  public DayCalendarBr() throws Exception {
    super();
    this.detailService = (DayTimeServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/mfreference/DayTime"); //$NON-NLS-1$
    master.addMasterModelListener(this);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMDBrowseForm#createDetailModel()
   */
  @Override
  protected MaintenanceTableModel createDetailModel() {
    return new DefaultMaintenanceEJBQLTableModel() {

      private String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("dayCal"); //$NON-NLS-1$
        paramsValue.add(getMasterKey());
        return String.format(LOAD_DETAIL_EJBQL, fieldsList);
      }

      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(DayTime.class, "Id", "dt.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(DayTime.class, "StartTick", "dt.StartTick", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(DayTime.class, "Ticks", "dt.Ticks", false));                 //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, detailService);

      }

      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }

      @Override
      protected int getPrimaryKeyFieldIndex() {
        return 0;
      }

    };
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMDBrowseForm#createMasterModel()
   */
  @Override
  protected MaintenanceTableModel createMasterModel() {
    return new DefaultMaintenanceEJBQLTableModel() {

      private String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);

        return String.format(LOAD_MASTER_EJBQL, fieldsList);
      }

      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(DayCalendar.class, "Id", "dc.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(DayCalendar.class, "Code", "dc.Code", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(DayCalendar.class, "DayCalName", "dc.DayCalName", false)); //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, masterService);

      }

      @Override
      protected void doLoad() {
        setQuery(createQueryText());
      }

      @Override
      protected int getPrimaryKeyFieldIndex() {
        return 0;
      }

    };
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
   */
  public void masterChange(ModelChangeEvent event) {
    detailUIProperties.put("DayCal.Id", event.getModelKey()); //$NON-NLS-1$
  }

}
