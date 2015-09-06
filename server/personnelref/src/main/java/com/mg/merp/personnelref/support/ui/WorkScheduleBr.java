/*
 * WorkScheduleBr.java
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
package com.mg.merp.personnelref.support.ui;

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.personnelref.model.WorkSchedule;

import java.util.Set;

/**
 * Браузер графиков работ
 *
 * @author leonova
 * @version $Id: WorkScheduleBr.java,v 1.1 2006/09/04 13:03:50 leonova Exp $
 */
public class WorkScheduleBr extends DefaultPlainBrowseForm {
  private final String INIT_QUERY_TEXT = "select %s from WorkSchedule ws %s";

  @Override
  protected String createQueryText() {
    Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
    String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    String fromsList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
    return String.format(INIT_QUERY_TEXT, fieldsList, fromsList);

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
        result.add(new TableEJBQLFieldDef(WorkSchedule.class, "Id", "ws.Id", true));
        result.add(new TableEJBQLFieldDef(WorkSchedule.class, "SCode", "ws.SCode", false));
        result.add(new TableEJBQLFieldDef(WorkSchedule.class, "SName", "ws.SName", false));
        result.add(new TableEJBQLFieldDef(WorkSchedule.class, "BeginDate", "ws.BeginDate", false));
        result.add(new TableEJBQLFieldDef(WorkSchedule.class, "EndDate", "ws.EndDate", false));
        result.add(new TableEJBQLFieldDef(WorkSchedule.class, "LeaveSchedule", "ls.SCode", "left join ws.LeaveSchedule as ls", false));
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
