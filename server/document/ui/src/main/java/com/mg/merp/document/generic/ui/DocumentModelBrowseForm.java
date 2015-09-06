/*
 * DocumentModelBrowseForm.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.document.generic.ui;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.DefaultHierarchyBrowseForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * Базовый класс контроллеров моделей документов
 *
 * @author leonova
 * @version $Id: DocumentModelBrowseForm.java,v 1.3 2006/09/20 10:42:17 safonov Exp $
 */
public class DocumentModelBrowseForm extends DefaultHierarchyBrowseForm {
  protected final String INIT_QUERY_TEXT = "select %s from DocHeadModel dhm %s %s";
  protected List<String> paramsName = new ArrayList<String>();
  protected List<Object> paramsValue = new ArrayList<Object>();
  protected String whereText;
  protected String fieldsList;
  protected String fromList;
  protected Set<TableEJBQLFieldDef> fieldDefs;

  public DocumentModelBrowseForm() {
    super();
    tree.setParentPropertyName("Folder.Id");
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultHierarchyBrowseForm#initializeMaster(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void initializeMaster(PersistentObject master) {
    uiProperties.put("Folder", master);
  }

  @Override
  protected String createQueryText() {
    whereText = "";
    Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
    fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
    paramsName.clear();
    paramsValue.clear();
    whereText = " where ".concat(DatabaseUtils.formatEJBQLHierarchyRestriction(true, "dhm.Folder", 0, "folder", folderEntity, paramsName, paramsValue, true));

    return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);

  }

}
