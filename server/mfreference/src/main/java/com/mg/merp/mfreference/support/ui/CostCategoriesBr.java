/*
 * CostCategoriesBr.java
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
import com.mg.merp.mfreference.model.CostCategories;

import java.util.Set;

/**
 * Браузер категорий затрат
 *
 * @author leonova
 * @version $Id: CostCategoriesBr.java,v 1.1 2006/08/24 12:20:15 leonova Exp $
 */
public class CostCategoriesBr extends DefaultPlainBrowseForm {

  private final String INIT_QUERY_TEXT = "select %s from CostCategories cc %s";

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
        result.add(new TableEJBQLFieldDef(CostCategories.class, "Id", "cc.Id", true));
        result.add(new TableEJBQLFieldDef(CostCategories.class, "Code", "cc.Code", false));
        result.add(new TableEJBQLFieldDef(CostCategories.class, "Description", "cc.Description", false));
        result.add(new TableEJBQLFieldDef(CostCategories.class, "ViewOrder", "cc.ViewOrder", false));
        result.add(new TableEJBQLFieldDef(CostCategories.class, "CalculationSequence", "cc.CalculationSequence", false));
        result.add(new TableEJBQLFieldDef(CostCategories.class, "AlgRepository", "cc.AlgRepository.Code", false));
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

