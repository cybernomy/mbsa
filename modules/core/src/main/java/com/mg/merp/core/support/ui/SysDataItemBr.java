/**
 * SysDataItemBr.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.merp.core.support.ui;

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.core.model.SysDataItem;

import java.util.Set;

/**
 * Браузер элементов данных
 *
 * @author Oleg V. Safonov
 * @version $Id: SysDataItemBr.java,v 1.1 2008/03/03 13:05:04 safonov Exp $
 */
public class SysDataItemBr extends DefaultPlainBrowseForm {
  private final String INIT_QUERY_TEXT = "select %s from com.mg.merp.core.model.SysDataItem d %s";

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
        result.add(new TableEJBQLFieldDef(SysDataItem.class, "Id", "d.Id", true));
        result.add(new TableEJBQLFieldDef(SysDataItem.class, "Name", "d.Name", false));
        result.add(new TableEJBQLFieldDef(SysDataItem.class, "Description", "d.Description", false));
        result.add(new TableEJBQLFieldDef(SysDataItem.class, "ApplicationLayer", "d.ApplicationLayer", false));
        result.add(new TableEJBQLFieldDef(SysDataItem.class, "Kind", "d.Kind", false));
        result.add(new TableEJBQLFieldDef(SysDataItem.class, "SysDomain", "dm.Name", " left join d.SysDomain as dm", false));
        result.add(new TableEJBQLFieldDef(SysDataItem.class, "ReferenceDataItemName", "d.ReferenceDataItemName", false));
        result.add(new TableEJBQLFieldDef(SysDataItem.class, "ShortLabel", "d.ShortLabel", false));
        result.add(new TableEJBQLFieldDef(SysDataItem.class, "MediumLabel", "d.MediumLabel", false));
        result.add(new TableEJBQLFieldDef(SysDataItem.class, "LongLabel", "d.LongLabel", false));
        result.add(new TableEJBQLFieldDef(SysDataItem.class, "Header", "d.Header", false));
        result.add(new TableEJBQLFieldDef(SysDataItem.class, "SearchHelpName", "d.SearchHelpName", false));
        result.add(new TableEJBQLFieldDef(SysDataItem.class, "EntityPropertyText", "d.EntityPropertyText", false));
        result.add(new TableEJBQLFieldDef(SysDataItem.class, "EntityTextFormat", "d.EntityTextFormat", false));
        result.add(new TableEJBQLFieldDef(SysDataItem.class, "ReadOnly", "d.ReadOnly", false));
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
