/*
 * RCCPHeaderBr.java
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
import com.mg.merp.planning.model.RccpHeader;

import java.util.Set;

/**
 * Браузер УППМ
 *
 * @author leonova
 * @version $Id: RCCPHeaderBr.java,v 1.1 2006/08/25 10:17:13 leonova Exp $
 */
public class RCCPHeaderBr extends DefaultPlainBrowseForm {
  private final String INIT_QUERY_TEXT = "select %s from RccpHeader r";

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
        result.add(new TableEJBQLFieldDef(RccpHeader.class, "Id", "r.Id", true));
        result.add(new TableEJBQLFieldDef(RccpHeader.class, "Name", "r.Name", false));
        result.add(new TableEJBQLFieldDef(RccpHeader.class, "Mps", "r.Mps.Code", false));
        result.add(new TableEJBQLFieldDef(RccpHeader.class, "WeekCal", "r.WeekCal.Code", false));
        result.add(new TableEJBQLFieldDef(RccpHeader.class, "LastRunDateTime", "r.LastRunDateTime", false));
        result.add(new TableEJBQLFieldDef(RccpHeader.class, "RccpVersion", "r.RccpVersion", false));
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
