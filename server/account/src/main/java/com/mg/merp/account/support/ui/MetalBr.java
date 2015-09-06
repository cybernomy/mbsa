/*
 * MetalBr.java
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
package com.mg.merp.account.support.ui;

import com.mg.framework.api.ui.widget.MaintenanceTable;
import com.mg.framework.api.ui.widget.PopupMenu;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.account.model.Metal;

import java.util.Set;

/**
 * Браузер драгоценных металов
 *
 * @author leonova
 * @version $Id: MetalBr.java,v 1.2 2008/02/21 08:21:12 alikaev Exp $
 */
public class MetalBr extends DefaultPlainBrowseForm {

  private final String INIT_QUERY_TEXT = "select %s from Metal m ";

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
   */
  @Override
  protected String createQueryText() {
    Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
    String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    return String.format(INIT_QUERY_TEXT, fieldsList);

  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    super.doOnRun();
    PopupMenu menuItem = view.getWidget(TABLE_WIDGET).getPopupMenu();
    menuItem.getMenuItem(MaintenanceTable.CLONE_MENU_ITEM).setEnabled(false);
    menuItem.getMenuItem(MaintenanceTable.DEEP_CLONE_MENU_ITEM).setEnabled(false);
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createModel()
   */
  @Override
  protected MaintenanceTableModel createModel() {
    return new DefaultMaintenanceEJBQLTableModel() {

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(Metal.class, "UpCode", "m.UpCode", true));
        result.add(new TableEJBQLFieldDef(Metal.class, "Code", "m.Code", false));
        result.add(new TableEJBQLFieldDef(Metal.class, "Name", "m.Name", false));
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


