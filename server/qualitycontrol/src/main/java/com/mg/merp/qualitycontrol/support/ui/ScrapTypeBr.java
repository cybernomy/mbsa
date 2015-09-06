/*
 * ScrapTypeBr.java
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
package com.mg.merp.qualitycontrol.support.ui;

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.qualitycontrol.model.ScrapType;

import java.util.Set;

/**
 * Браузер видов потерь
 *
 * @author leonova
 * @version $Id: ScrapTypeBr.java,v 1.1 2006/08/03 08:53:18 leonova Exp $
 */
public class ScrapTypeBr extends DefaultPlainBrowseForm {
  private final String INIT_QUERY_TEXT = "select scrtype.Id";
  private String fieldsList;

  @Override
  protected String createQueryText() {
    return DatabaseUtils.embedAddinFieldsBrowseEJBQL(INIT_QUERY_TEXT.concat(fieldsList).concat(" from ScrapType scrtype order by scrtype.Id"), service, "scrtype.Id", getFieldsSet()); //$NON-NLS-1$ //$NON-NLS-2$
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
        addColumnDef(ScrapType.class, "Id", null); //$NON-NLS-1$
        if (fields.contains("Code")) {
          fieldsList = fieldsList.concat(", scrtype.Code");
          addColumnDef(ScrapType.class, "Code", null); //$NON-NLS-1$
        }
        if (fields.contains("Name")) {
          fieldsList = fieldsList.concat(", scrtype.Name");
          addColumnDef(ScrapType.class, "Name", null); //$NON-NLS-1$
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
