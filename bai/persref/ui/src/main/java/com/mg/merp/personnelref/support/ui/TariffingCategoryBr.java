/*
 * TariffingCategoryBr.java
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
import com.mg.merp.personnelref.model.TariffingCategory;

import java.util.Set;

/**
 * Категории тарификации должностей
 *
 * @author leonova
 * @version $Id: TariffingCategoryBr.java,v 1.1 2006/09/04 13:03:50 leonova Exp $
 */
public class TariffingCategoryBr extends DefaultPlainBrowseForm {
  private final String INIT_QUERY_TEXT = "select %s from TariffingCategory ct";

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
        result.add(new TableEJBQLFieldDef(TariffingCategory.class, "Id", "ct.Id", true));
        result.add(new TableEJBQLFieldDef(TariffingCategory.class, "CCode", "ct.CCode", false));
        result.add(new TableEJBQLFieldDef(TariffingCategory.class, "CName", "ct.CName", false));
        result.add(new TableEJBQLFieldDef(TariffingCategory.class, "CType", "ct.CType", false));
        result.add(new TableEJBQLFieldDef(TariffingCategory.class, "Priority", "ct.Priority", false));
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
