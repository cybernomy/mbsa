/*
 * ItemFamilyBr.java
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
import com.mg.merp.planning.model.ItemFamily;

import java.util.Set;

/**
 * Браузер семейств обобщенных товаров
 *
 * @author leonova
 * @version $Id: ItemFamilyBr.java,v 1.1 2006/08/25 10:17:13 leonova Exp $
 */
public class ItemFamilyBr extends DefaultPlainBrowseForm {
  private final String INIT_QUERY_TEXT = "select %s from ItemFamily if %s";

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
        result.add(new TableEJBQLFieldDef(ItemFamily.class, "Id", "if.Id", true));
        result.add(new TableEJBQLFieldDef(ItemFamily.class, "ParentGenericItem", "pgi.GenericItemCode", "left join if.ParentGenericItem as pgi", false));
        result.add(new TableEJBQLFieldDef(ItemFamily.class, "PlanningLevel", "pl.PlanningLevelNum", "left join if.PlanningLevel as pl", false));
        result.add(new TableEJBQLFieldDef(ItemFamily.class, "ChildGenericItem", "cgi.GenericItemCode", "left join if.ChildGenericItem as cgi", false));
        result.add(new TableEJBQLFieldDef(ItemFamily.class, "EffOnDate", "if.EffOnDate", false));
        result.add(new TableEJBQLFieldDef(ItemFamily.class, "EffOffDate", "if.EffOffDate", false));
        result.add(new TableEJBQLFieldDef(ItemFamily.class, "AllocationPercent", "if.AllocationPercent", false));
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
