/*
 * ScheduleHeadBr.java
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
package com.mg.merp.table.support.ui;

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.table.model.ScheduleHead;

import java.util.Set;

/**
 * Браузер графиков работ в табельном учете
 *
 * @author leonova
 * @version $Id: ScheduleHeadBr.java,v 1.1 2006/08/29 12:48:57 leonova Exp $
 */
public class ScheduleHeadBr extends DefaultPlainBrowseForm {
  private final String INIT_QUERY_TEXT = "select %s from ScheduleHead sh %s";

  @Override
  protected String createQueryText() {
    Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
    String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
    return String.format(INIT_QUERY_TEXT, fieldsList, fromList);
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
        result.add(new TableEJBQLFieldDef(ScheduleHead.class, "Id", "sh.Id", true));
        result.add(new TableEJBQLFieldDef(ScheduleHead.class, "WorkSchedule", "sh.WorkSchedule.SCode", false));
        result.add(new TableEJBQLFieldDef(ScheduleHead.class, "DefaultPatternHead", "ph.Code", "left join sh.DefaultPatternHead as ph", false));
        result.add(new TableEJBQLFieldDef(ScheduleHead.class, "HolidayWorkTimeKind", "tk.Code", "left join sh.HolidayWorkTimeKind as tk", false));
        result.add(new TableEJBQLFieldDef(ScheduleHead.class, "HolidayAccountKind", "sh.HolidayAccountKind", false));
        result.add(new TableEJBQLFieldDef(ScheduleHead.class, "PreHolidayReduction", "sh.PreHolidayReduction", false));
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);

      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText());
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