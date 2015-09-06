/*
 * ZipCodeBr.java
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
package com.mg.merp.reference.support.ui;

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.reference.model.ZipCode;

import java.util.Set;

/**
 * Браузер почтовых индексов
 *
 * @author leonova
 * @version $Id: ZipCodeBr.java,v 1.2 2006/08/11 07:18:52 leonova Exp $
 */
public class ZipCodeBr extends DefaultPlainBrowseForm {
  private final String INIT_QUERY_TEXT = "select %s from ZipCode zc %s";

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
        result.add(new TableEJBQLFieldDef(ZipCode.class, "Id", "zc.Id", true));
        result.add(new TableEJBQLFieldDef(ZipCode.class, "Code", "zc.Code", false));
        result.add(new TableEJBQLFieldDef(ZipCode.class, "Country", "c.CCode", "left join zc.Country as c", false));
        result.add(new TableEJBQLFieldDef(ZipCode.class, "Region", "r.Name", "left join zc.Region as r", false));
        result.add(new TableEJBQLFieldDef(ZipCode.class, "District", "d.Name", "left join zc.District as d", false));
        result.add(new TableEJBQLFieldDef(ZipCode.class, "Place", "pl.Name", "left join zc.Place as pl", false));
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
