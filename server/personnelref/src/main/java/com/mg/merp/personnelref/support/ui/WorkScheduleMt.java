/*
 * WorkScheduleMt.java
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
package com.mg.merp.personnelref.support.ui;

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
import com.mg.merp.personnelref.WorkTimeNormServiceLocal;
import com.mg.merp.personnelref.model.WorkTimeNorm;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: WorkScheduleMt.java,v 1.4 2006/10/04 07:51:48 leonova Exp $
 */
public class WorkScheduleMt extends DefaultMaintenanceForm implements MasterModelListener {
  protected AttributeMap workTimeNormProperties = new LocalDataTransferObject();
  private MaintenanceTableController workTimeNormKind;
  private WorkTimeNormServiceLocal workTimeNormService;

  public WorkScheduleMt() throws Exception {
    addMasterModelListener(this);

    workTimeNormService = (WorkTimeNormServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/personnelref/WorkTimeNorm");
    workTimeNormKind = new MaintenanceTableController(workTimeNormProperties);
    workTimeNormKind.initController(workTimeNormService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from WorkTimeNorm wtn %s where wtn.WorkSchedule = :workSchedule";
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("workSchedule");
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
        result.add(new TableEJBQLFieldDef(WorkTimeNorm.class, "Id", "wtn.Id", true));
        result.add(new TableEJBQLFieldDef(WorkTimeNorm.class, "CalcPeriod", "wtn.CalcPeriod.PName", false));
        result.add(new TableEJBQLFieldDef(WorkTimeNorm.class, "WorkDaysNumber", "wtn.WorkDaysNumber", false));
        result.add(new TableEJBQLFieldDef(WorkTimeNorm.class, "WorkHoursNumber", "wtn.WorkHoursNumber", false));
        result.add(new TableEJBQLFieldDef(WorkTimeNorm.class, "WorkDaysAlg", "wda.Code", "left join wtn.WorkDaysAlg as wda", false));
        result.add(new TableEJBQLFieldDef(WorkTimeNorm.class, "WorkHoursAlg", "wha.Code", "left join wtn.WorkHoursAlg as wha", false));
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, workTimeNormService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });

    addMasterModelListener(workTimeNormKind);


  }

  public void masterChange(ModelChangeEvent event) {
    workTimeNormProperties.put("WorkSchedule", event.getEntity());
  }

}
