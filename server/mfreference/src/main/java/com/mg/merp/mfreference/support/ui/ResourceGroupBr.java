/*
 * ResourceGroupBr.java
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
package com.mg.merp.mfreference.support.ui;

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.mfreference.model.ResourceGroup;

import java.util.Set;

/**
 * Браузер групп ресурсов
 *
 * @author leonova
 * @version $Id: ResourceGroupBr.java,v 1.1 2006/08/24 12:20:32 leonova Exp $
 */
public class ResourceGroupBr extends DefaultPlainBrowseForm {

  private final String INIT_QUERY_TEXT = "select %s from ResourceGroup rg %s";

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
        result.add(new TableEJBQLFieldDef(ResourceGroup.class, "Id", "rg.Id", true));
        result.add(new TableEJBQLFieldDef(ResourceGroup.class, "ResourceGroupCode", "rg.ResourceGroupCode", false));
        result.add(new TableEJBQLFieldDef(ResourceGroup.class, "Description", "rg.Description", false));
        result.add(new TableEJBQLFieldDef(ResourceGroup.class, "ResourceType", "rg.ResourceType", false));
        result.add(new TableEJBQLFieldDef(ResourceGroup.class, "LimitedResourceFlag", "rg.LimitedResourceFlag", false));
        result.add(new TableEJBQLFieldDef(ResourceGroup.class, "PlanningLevel", "rg.PlanningLevel.Description", false));
        result.add(new TableEJBQLFieldDef(ResourceGroup.class, "Measure", "rg.Measure.Code", false));
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
