/*
 * DiscountKindBr.java
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
import com.mg.merp.salary.model.DiscountKind;

import java.util.Set;

/**
 * Браузер видов скидок и расходов
 *
 * @author leonova
 * @version $Id: DiscountKindBr.java,v 1.1 2006/08/28 12:43:20 leonova Exp $
 */
public class DiscountKindBr extends DefaultPlainBrowseForm {
  private final String INIT_QUERY_TEXT = "select %s from DiscountKind d";

  @Override
  protected String createQueryText() {
    Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
    String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    return String.format(INIT_QUERY_TEXT, fieldsList);
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
        result.add(new TableEJBQLFieldDef(DiscountKind.class, "Id", "d.Id", true));
        result.add(new TableEJBQLFieldDef(DiscountKind.class, "DCode", "d.DCode", false));
        result.add(new TableEJBQLFieldDef(DiscountKind.class, "DName", "d.DName", false));
        result.add(new TableEJBQLFieldDef(DiscountKind.class, "MinSalaryNumber", "d.MinSalaryNumber", false));
        result.add(new TableEJBQLFieldDef(DiscountKind.class, "FixedSum", "d.FixedSum", false));
        result.add(new TableEJBQLFieldDef(DiscountKind.class, "BeginDate", "d.BeginDate", false));
        result.add(new TableEJBQLFieldDef(DiscountKind.class, "ActionPeriod", "d.ActionPeriod", false));
        result.add(new TableEJBQLFieldDef(DiscountKind.class, "IsAccumulating", "d.IsAccumulating", false));
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

