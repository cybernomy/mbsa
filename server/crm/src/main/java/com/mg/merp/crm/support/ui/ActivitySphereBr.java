/*
 * ActivitySphereBr.java
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
package com.mg.merp.crm.support.ui;

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.crm.model.ActivitySphere;

import java.util.Set;

/**
 * Браузеры сфер деятельности
 *
 * @author leonova
 * @version $Id: ActivitySphereBr.java,v 1.1 2006/08/04 05:55:14 leonova Exp $
 */
public class ActivitySphereBr extends DefaultPlainBrowseForm {
  private final String INIT_QUERY_TEXT = "select actsph.Id";
  private String fieldsList;

  @Override
  protected String createQueryText() {
    return DatabaseUtils.embedAddinFieldsBrowseEJBQL(INIT_QUERY_TEXT.concat(fieldsList).concat(" from ActivitySphere actsph order by actsph.Id"), service, "actsph.Id", getFieldsSet()); //$NON-NLS-1$ //$NON-NLS-2$
  }

  @Override
  protected Set<String> getDefaultFieldsSet() {
    Set<String> result = super.getDefaultFieldsSet();
    result.addAll(StringUtils.split("Id,Code,Name", ",")); //$NON-NLS-1$ //$NON-NLS-2$
    return DatabaseUtils.embedAddinFieldsDefaultFieldsSet(result, service);

  }

  @Override
  protected MaintenanceTableModel createModel() {
    return new DefaultMaintenanceEJBQLTableModel() {

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#createColumnsModel()
       */
      @Override
      protected void createColumnsModel() {
        //order is important
        fieldsList = "";
        Set<String> fields = getFieldsSet();
        addColumnDef(ActivitySphere.class, "Id", null); //$NON-NLS-1$
        if (fields.contains("Code")) {
          fieldsList = fieldsList.concat(", actsph.Code");
          addColumnDef(ActivitySphere.class, "Code", null); //$NON-NLS-1$
        }
        if (fields.contains("Name")) {
          fieldsList = fieldsList.concat(", actsph.Name");
          addColumnDef(ActivitySphere.class, "Name", null); //$NON-NLS-1$
        }
        addAddinFieldDef(service);
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
