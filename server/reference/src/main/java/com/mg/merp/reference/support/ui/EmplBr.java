/*
 * EmplBr.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.HierarchyRestrictionSupport;
import com.mg.framework.api.ui.widget.MaintenanceTree;
import com.mg.framework.generic.ui.DefaultHierarchyBrowseForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.reference.model.Employees;
import com.mg.merp.reference.model.OrgUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Браузер сотрудников
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @author Konstantin S. Alikaev
 * @version $Id: EmplBr.java,v 1.13 2009/02/09 16:25:03 safonov Exp $
 */
public class EmplBr extends DefaultHierarchyBrowseForm {
  private final String INIT_QUERY_TEXT = "select %s from Employees em %s %s order by em.Code "; //$NON-NLS-1$
  private final String TREE_WIDGET_NAME = "tree";  //$NON-NLS-1$
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();

  public EmplBr() throws Exception {
    super();
    folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/OrgUnit"); //$NON-NLS-1$
    tree.setParentPropertyName("FolderId"); //$NON-NLS-1$
    restrictionFormName = "com/mg/merp/reference/resources/EmployeesRest.mfd.xml"; //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacyTreeBrowseForm#loadFolders()
   */
  @Override
  protected TreeNode loadFolders() {
    List<OrgUnit> list = OrmTemplate.getInstance().find(OrgUnit.class, String.format("from OrgUnit org where %s order by org.FolderId, org.FullName", DatabaseUtils.generateFlatBrowseWhereEJBQL("org.Id", 4))); //$NON-NLS-1$ //$NON-NLS-2$
    return OrgUnitTreeNode.createTree(list);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#initializeMaster(java.io.Serializable)
   */
  @Override
  protected void initializeMaster(PersistentObject master) {
    uiProperties.put("FolderId", master.getAttribute("Id")); //$NON-NLS-1$ //$NON-NLS-2$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultHierarchyBrowseForm#getFolderAttributeName()
   */
  @Override
  protected String getFolderAttributeName() {
    return "FolderId";
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultHierarchyBrowseForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    super.doOnRun();
    view.getWidget(TREE_WIDGET_NAME).setReadOnly(true);
    view.getWidget(TREE_WIDGET_NAME).getPopupMenu().getMenuItem(MaintenanceTree.PERMISSION_MENU_ITEM).setEnabled(false);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createModel()
   */
  @Override
  protected MaintenanceTableModel createModel() {
    return new DefaultMaintenanceEJBQLTableModel() {

      @Override
      protected int getPrimaryKeyFieldIndex() {
        return 0;
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(Employees.class, "Id", "em.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Employees.class, "Code", "em.Code", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Employees.class, "NaturalPerson.Surname", "np.Surname", "left join em.NaturalPerson as np", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Employees.class, "NaturalPerson.Name", "np.Name", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Employees.class, "NaturalPerson.Patronymic", "np.Patronymic", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Employees.class, "Office", "em.Office", false));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Employees.class, "TabNum", "em.TabNum", false));         //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);

      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setQuery(java.lang.String)
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    };

  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
   */
  @Override
  protected String createQueryText() {
    Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
    String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
    paramsName.clear();
    paramsValue.clear();
    EmployeesRest restForm = (EmployeesRest) getRestrictionForm();
    StringBuilder whereText = new StringBuilder(" where "). //$NON-NLS-1$
        append(DatabaseUtils.formatEJBQLHierarchyRestriction(((HierarchyRestrictionSupport) restForm).isUseHierarchy(), "em.FolderId", 4, "folderId", folderEntity == null ? null : folderEntity.getAttribute("Id"), paramsName, paramsValue, true)).  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        append(DatabaseUtils.formatEJBQLStringRestriction("np.Surname", restForm.getSurName(), "surName", paramsName, paramsValue, false)).  //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLStringRestriction("np.Name", restForm.getName(), "name", paramsName, paramsValue, false)).  //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLStringRestriction("np.Patronymic", restForm.getPatronymic(), "patronymic", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLStringRestriction("em.TabNum", restForm.getTableNumber(), "tableNumber", paramsName, paramsValue, false)).  //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLStringRestriction("em.UpCode", restForm.getPersonCode(), "personCode", paramsName, paramsValue, false)).  //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "em.Id", restForm.getAddinFieldsRestriction(), false));  //$NON-NLS-1$
    return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText.toString());
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultHierarchyBrowseForm#setupFolderPermissions()
   */
  @Override
  protected void setupFolderPermissions() {
    if (folderEntity != null)
      ServerUtils.getSecuritySystem().setupTreePermission((Integer) folderEntity.getPrimaryKey(), 4, "com.mg.merp.reference.model.OrgUnit", "FolderId"); //$NON-NLS-1$ //$NON-NLS-2$
  }

}
