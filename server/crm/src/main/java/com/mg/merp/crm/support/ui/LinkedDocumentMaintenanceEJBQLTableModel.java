/*
 * LinkedDocumentMaintenanceEJBQLTableModel.java
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
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.crm.LinkedDocumentServiceLocal;
import com.mg.merp.crm.model.LinkedDocument;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author leonova
 * @version $Id: LinkedDocumentMaintenanceEJBQLTableModel.java,v 1.1 2006/09/06 05:23:35 leonova Exp
 *          $
 */
public class LinkedDocumentMaintenanceEJBQLTableModel extends
    DefaultMaintenanceEJBQLTableModel {

  protected final String INIT_QUERY_TEXT = "";
  protected List<String> paramsName = new ArrayList<String>();
  protected List<Object> paramsValue = new ArrayList<Object>();
  protected LinkedDocumentServiceLocal originalService;
  protected String fieldsList;
  protected String fromList;

  protected String createQueryText() {
    Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
    String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
    paramsName.clear();
    paramsValue.clear();


    return String.format(INIT_QUERY_TEXT, fieldsList, fromList);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
   */
  @Override
  protected int getPrimaryKeyFieldIndex() {
    return 0;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
   */
  @Override
  protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
    Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
    result.add(new TableEJBQLFieldDef(LinkedDocument.class, "Id", "ld.Id", true));
    result.add(new TableEJBQLFieldDef(LinkedDocument.class, "Original.DocNumber", "o.DocNumber", "left join ld.Original as o", true));
    result.add(new TableEJBQLFieldDef(LinkedDocument.class, "Original.DocDate", "o.DocDate", false));
    result.add(new TableEJBQLFieldDef(LinkedDocument.class, "Original.DocName", "o.DocName", false));
    result.add(new TableEJBQLFieldDef(LinkedDocument.class, "Original.Comments", "o.Comments", false));
    return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, originalService);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
   */
  @Override
  protected void doLoad() {
    setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
  }

}
