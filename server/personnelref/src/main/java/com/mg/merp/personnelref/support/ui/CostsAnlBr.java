/*
 * CostsAnlBr.java
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
import com.mg.merp.personnelref.model.CostsAnl;

import java.util.Set;

/**
 * Браузер аналитики состава затрат
 *
 * @author leonova
 * @version $Id: CostsAnlBr.java,v 1.1 2006/09/04 13:03:50 leonova Exp $
 */
public class CostsAnlBr extends DefaultPlainBrowseForm {
  private final String INIT_QUERY_TEXT = "select %s from CostsAnl ca %s";

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
        result.add(new TableEJBQLFieldDef(CostsAnl.class, "Id", "ca.Id", true));
        result.add(new TableEJBQLFieldDef(CostsAnl.class, "ACode", "ca.ACode", false));
        result.add(new TableEJBQLFieldDef(CostsAnl.class, "AName", "ca.AName", false));
        result.add(new TableEJBQLFieldDef(CostsAnl.class, "AnlLevel", "ca.AnlLevel", false));
        result.add(new TableEJBQLFieldDef(CostsAnl.class, "Parent", "p.ACode", "left join ca.Parent as p", false));
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

