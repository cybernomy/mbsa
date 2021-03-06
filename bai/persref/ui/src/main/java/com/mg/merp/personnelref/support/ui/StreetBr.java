/*
 * StreetBr.java
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
import com.mg.merp.personnelref.model.Street;

import java.util.Set;

/**
 * Браузер улиц
 *
 * @author leonova
 * @version $Id: StreetBr.java,v 1.1 2006/09/06 12:48:51 leonova Exp $
 */
public class StreetBr extends DefaultPlainBrowseForm {
  private final String INIT_QUERY_TEXT = "select %s from Street s";

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
        result.add(new TableEJBQLFieldDef(Street.class, "Id", "s.Id", true));
        result.add(new TableEJBQLFieldDef(Street.class, "KCode", "s.KCode", false));
        result.add(new TableEJBQLFieldDef(Street.class, "SName", "s.SName", false));
        result.add(new TableEJBQLFieldDef(Street.class, "KType", "s.KType", false));
        result.add(new TableEJBQLFieldDef(Street.class, "PostIndex", "s.PostIndex", false));
        result.add(new TableEJBQLFieldDef(Street.class, "GninMb", "s.GninMb", false));
        result.add(new TableEJBQLFieldDef(Street.class, "OcaTd", "s.OcaTd", false));
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
