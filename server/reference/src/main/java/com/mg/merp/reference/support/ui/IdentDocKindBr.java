/*
 * IdentDocKindBr.java
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
import com.mg.merp.reference.model.IdentDocKind;

import java.util.Set;

/**
 * Браузер видов документов
 *
 * @author leonova
 * @version $Id: IdentDocKindBr.java,v 1.2 2006/08/11 07:28:38 leonova Exp $
 */
public class IdentDocKindBr extends DefaultPlainBrowseForm {
  private final String INIT_QUERY_TEXT = "select %s from IdentDocKind iddockind";

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
        result.add(new TableEJBQLFieldDef(IdentDocKind.class, "Id", "iddockind.Id", true));
        result.add(new TableEJBQLFieldDef(IdentDocKind.class, "KCode", "iddockind.KCode", false));
        result.add(new TableEJBQLFieldDef(IdentDocKind.class, "KName", "iddockind.KName", false));
        result.add(new TableEJBQLFieldDef(IdentDocKind.class, "LSeriesLength", "iddockind.LSeriesLength", false));
        result.add(new TableEJBQLFieldDef(IdentDocKind.class, "RSeriesLength", "iddockind.RSeriesLength", false));
        result.add(new TableEJBQLFieldDef(IdentDocKind.class, "SeriesDivider", "iddockind.SeriesDivider", false));
        result.add(new TableEJBQLFieldDef(IdentDocKind.class, "NumberLength", "iddockind.NumberLength", false));
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
