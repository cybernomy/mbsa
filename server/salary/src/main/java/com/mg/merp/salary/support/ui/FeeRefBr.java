/*
 * FeeRefBr.java
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
package com.mg.merp.salary.support.ui;

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.salary.model.FeeRef;

import java.util.Set;

/**
 * Браузер начислений удержаний
 *
 * @author leonova
 * @version $Id: FeeRefBr.java,v 1.1 2006/08/31 11:35:59 leonova Exp $
 */
public class FeeRefBr extends DefaultPlainBrowseForm {
  private final String INIT_QUERY_TEXT = "select %s from FeeRef fr %s";

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
        result.add(new TableEJBQLFieldDef(FeeRef.class, "Id", "fr.Id", true));
        result.add(new TableEJBQLFieldDef(FeeRef.class, "FeeType", "fr.FeeType", false));
        result.add(new TableEJBQLFieldDef(FeeRef.class, "CalcListSectionRef", "csr.SName", "left join fr.CalcListSectionRef as csr", false));
        result.add(new TableEJBQLFieldDef(FeeRef.class, "FCode", "fr.FCode", false));
        result.add(new TableEJBQLFieldDef(FeeRef.class, "FName", "fr.FName", false));
        result.add(new TableEJBQLFieldDef(FeeRef.class, "CalcAlg", "ca.Code", "left join fr.CalcAlg as ca", false));
        result.add(new TableEJBQLFieldDef(FeeRef.class, "GnsCode", "gc.Code", "left join fr.GnsCode as gc", false));
        result.add(new TableEJBQLFieldDef(FeeRef.class, "Priority", "fr.Priority", false));
        result.add(new TableEJBQLFieldDef(FeeRef.class, "SumSign", "fr.SumSign", false));
        result.add(new TableEJBQLFieldDef(FeeRef.class, "BeginDate", "fr.BeginDate", false));
        result.add(new TableEJBQLFieldDef(FeeRef.class, "EndDate", "fr.EndDate", false));
        result.add(new TableEJBQLFieldDef(FeeRef.class, "PeriodiCity", "fr.PeriodiCity", false));
        result.add(new TableEJBQLFieldDef(FeeRef.class, "CostsAnl1", "anl1.ACode", "left join fr.CostsAnl1 as anl1", false));
        result.add(new TableEJBQLFieldDef(FeeRef.class, "CostsAnl2", "anl2.ACode", "left join fr.CostsAnl2 as anl2", false));
        result.add(new TableEJBQLFieldDef(FeeRef.class, "CostsAnl3", "anl3.ACode", "left join fr.CostsAnl3 as anl3", false));
        result.add(new TableEJBQLFieldDef(FeeRef.class, "CostsAnl4", "anl4.ACode", "left join fr.CostsAnl4 as anl4", false));
        result.add(new TableEJBQLFieldDef(FeeRef.class, "CostsAnl5", "anl5.ACode", "left join fr.CostsAnl5 as anl5", false));
        result.add(new TableEJBQLFieldDef(FeeRef.class, "RollKind", "rk.Name", "left join fr.RollKind as rk", false));
        result.add(new TableEJBQLFieldDef(FeeRef.class, "IncomeKind", "ik.ICode", "left join fr.IncomeKind as ik", false));
        result.add(new TableEJBQLFieldDef(FeeRef.class, "IsZeroIncluded", "fr.IsZeroIncluded", false));


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
