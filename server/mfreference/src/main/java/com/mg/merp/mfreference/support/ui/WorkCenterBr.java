/*
 * WorkCenterBr.java
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
package com.mg.merp.mfreference.support.ui;

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.mfreference.model.WorkCenter;

import java.util.Set;

/**
 * Браузер рабочих центров
 *
 * @author leonova
 * @version $Id: WorkCenterBr.java,v 1.2 2007/07/30 10:59:52 safonov Exp $
 */
public class WorkCenterBr extends DefaultPlainBrowseForm {
  private final String INIT_QUERY_TEXT = "select %s from WorkCenter wc %s";

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
        result.add(new TableEJBQLFieldDef(WorkCenter.class, "Id", "wc.Id", true));
        result.add(new TableEJBQLFieldDef(WorkCenter.class, "WcCode", "wc.WcCode", false));
        result.add(new TableEJBQLFieldDef(WorkCenter.class, "WcName", "wc.WcName", false));
        result.add(new TableEJBQLFieldDef(WorkCenter.class, "Efficiency", "wc.Efficiency", false));
        result.add(new TableEJBQLFieldDef(WorkCenter.class, "Utilization", "wc.Utilization", false));
        result.add(new TableEJBQLFieldDef(WorkCenter.class, "CapacityPlan", "wc.CapacityPlan", false));
        result.add(new TableEJBQLFieldDef(WorkCenter.class, "CapacitySchedule", "wc.CapacitySchedule", false));
        result.add(new TableEJBQLFieldDef(WorkCenter.class, "MchFlag", "wc.MchFlag", false));
        result.add(new TableEJBQLFieldDef(WorkCenter.class, "MchNumber", "wc.MchNumber", false));
        result.add(new TableEJBQLFieldDef(WorkCenter.class, "LbrFlag", "wc.LbrFlag", false));
        result.add(new TableEJBQLFieldDef(WorkCenter.class, "LbrNumber", "wc.LbrNumber", false));
        result.add(new TableEJBQLFieldDef(WorkCenter.class, "SchTolerance", "wc.SchTolerance", false));
        result.add(new TableEJBQLFieldDef(WorkCenter.class, "QueueTicks", "wc.QueueTicks", false));
        result.add(new TableEJBQLFieldDef(WorkCenter.class, "OutsideFlag", "wc.OutsideFlag", false));
        result.add(new TableEJBQLFieldDef(WorkCenter.class, "Contractor", "contr.Code", "left join wc.Contractor as contr", false));
        result.add(new TableEJBQLFieldDef(WorkCenter.class, "MchBackflushFlag", "wc.MchBackflushFlag", false));
        result.add(new TableEJBQLFieldDef(WorkCenter.class, "LbrBackflushFlag", "wc.LbrBackflushFlag", false));
        result.add(new TableEJBQLFieldDef(WorkCenter.class, "MchBaseOhFlag", "wc.MchBaseOhFlag", false));
        result.add(new TableEJBQLFieldDef(WorkCenter.class, "LbrBaseOhFlag", "wc.LbrBaseOhFlag", false));
        result.add(new TableEJBQLFieldDef(WorkCenter.class, "MtlBaseOhFlag", "wc.MtlBaseOhFlag", false));
        result.add(new TableEJBQLFieldDef(WorkCenter.class, "ControlPointFlag", "wc.ControlPointFlag", false));
        result.add(new TableEJBQLFieldDef(WorkCenter.class, "Comment", "wc.Comment", false));
//				result.add(new TableEJBQLFieldDef(WorkCenter.class, "WipLbrTotal", "wc.WipLbrTotal", false));
//				result.add(new TableEJBQLFieldDef(WorkCenter.class, "WipMtlTotal", "wc.WipMtlTotal", false));
//				result.add(new TableEJBQLFieldDef(WorkCenter.class, "WipFohTotal", "wc.WipFohTotal", false));
//				result.add(new TableEJBQLFieldDef(WorkCenter.class, "WipVohTotal", "wc.WipVohTotal", false));
//				result.add(new TableEJBQLFieldDef(WorkCenter.class, "WipOutTotal", "wc.WipOutTotal", false));
        result.add(new TableEJBQLFieldDef(WorkCenter.class, "LbrSetupAvgRate", "wc.LbrSetupAvgRate", false));
        result.add(new TableEJBQLFieldDef(WorkCenter.class, "LbrRunAvgRate", "wc.LbrRunAvgRate", false));
        result.add(new TableEJBQLFieldDef(WorkCenter.class, "Cell", "cl.CellName", "left join wc.Cell as cl", false));
        result.add(new TableEJBQLFieldDef(WorkCenter.class, "WeekCal", "cal.Code", "left join wc.WeekCal as cal", false));
        result.add(new TableEJBQLFieldDef(WorkCenter.class, "ResourceGroup", "r.ResourceGroupCode", "left join wc.ResourceGroup as r", false));
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
