/**
 * TaskBr.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.merp.scheduler.support.ui;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.DefaultHierarchyBrowseForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.core.support.CoreUtils;
import com.mg.merp.scheduler.TaskServiceLocal;
import com.mg.merp.scheduler.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы браузера "Задачи планировщика"
 *
 * @author Oleg V. Safonov
 * @version $Id: TaskBr.java,v 1.1 2008/04/25 10:57:23 safonov Exp $
 */
public class TaskBr extends DefaultHierarchyBrowseForm {
  private final String INIT_QUERY_TEXT = "select %s from com.mg.merp.scheduler.model.Task t %s %s"; //$NON-NLS-1$
  protected AttributeMap constantProperties = new LocalDataTransferObject();
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();

  /**
   *
   */
  @SuppressWarnings("unchecked")
  public TaskBr() throws Exception {
    super();
    folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder"); //$NON-NLS-1$
    treeUIProperties.put("FolderType", TaskServiceLocal.FOLDER_TYPE); //$NON-NLS-1$
    tree.setParentPropertyName("Folder.Id"); //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#initializeMaster(java.io.Serializable)
   */
  @Override
  protected void initializeMaster(PersistentObject master) {
    uiProperties.put("Folder", master); //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#loadFolders()
   */
  @Override
  protected TreeNode loadFolders() throws ApplicationException {
    return CoreUtils.loadFolderHierarchy(TaskServiceLocal.FOLDER_TYPE);
  }

  @Override
  protected MaintenanceTableModel createModel() {
    return new DefaultMaintenanceEJBQLTableModel() {

      @Override
      protected int getPrimaryKeyFieldIndex() {
        return 0;
      }

      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(Task.class, "Id", "t.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Task.class, "Code", "t.Code", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Task.class, "Name", "t.Name", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Task.class, "BAi", "t.BAi", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Task.class, "Description", "t.Description", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Task.class, "CronExpression", "t.CronExpression", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Task.class, "Active", "t.Active", false)); //$NON-NLS-1$ //$NON-NLS-2$

        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
      }

      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }

    };
  }

  @Override
  protected String createQueryText() {
    Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
    String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
    paramsName.clear();
    paramsValue.clear();
    StringBuilder whereText = new StringBuilder(" where ") //$NON-NLS-1$
        .append(DatabaseUtils.formatEJBQLHierarchyRestriction(true, "t.Folder", 0, "folder", folderEntity, paramsName, paramsValue, true));
    return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText.toString());
  }

}
