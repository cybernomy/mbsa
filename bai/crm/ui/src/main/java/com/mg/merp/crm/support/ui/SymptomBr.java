/*
 * RelationBr.java
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
package com.mg.merp.crm.support.ui;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.HierarchyRestrictionSupport;
import com.mg.framework.generic.ui.DefaultHierarchyBrowseForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.crm.SymptomServiceLocal;
import com.mg.merp.crm.model.Symptom;
import com.mg.merp.reference.support.ReferenceUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Браузер симптомов
 *
 * @author leonova
 * @version $Id: SymptomBr.java,v 1.4 2006/10/16 11:02:22 leonova Exp $
 */
public class SymptomBr extends DefaultHierarchyBrowseForm {
  private final String INIT_QUERY_TEXT = "select distinct %s from Symptom s %s %s";
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();

  public SymptomBr() throws Exception {
    super();
    folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder");
    tree.setParentPropertyName("Folder.Id");
    treeUIProperties.put("FolderType", SymptomServiceLocal.FOLDER_PART);
    restrictionFormName = "com/mg/merp/crm/resources/SymptomRest.mfd.xml";
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#initializeMaster(java.io.Serializable)
   */
  @Override
  protected void initializeMaster(PersistentObject master) {
    uiProperties.put("Folder", master);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#loadFolders()
   */
  @Override
  protected TreeNode loadFolders() throws ApplicationException {
    return ReferenceUtils.loadFolderHierarchy(SymptomServiceLocal.FOLDER_PART);
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
        result.add(new TableEJBQLFieldDef(Symptom.class, "Id", "s.Id", true));
        result.add(new TableEJBQLFieldDef(Symptom.class, "Creator", "p.Surname", "left join s.Creator.Person as p", false));
        result.add(new TableEJBQLFieldDef(Symptom.class, "Name", "s.Name", false));
        result.add(new TableEJBQLFieldDef(Symptom.class, "Info", "s.Info", false));

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
    SymptomRest restForm = (SymptomRest) getRestrictionForm();
    StringBuilder whereText = new StringBuilder(" where ").append(DatabaseUtils.formatEJBQLHierarchyRestriction(((HierarchyRestrictionSupport) restForm).isUseHierarchy(), "s.Folder", 0, "folder", folderEntity, paramsName, paramsValue, true)).
        append(DatabaseUtils.formatEJBQLStringRestriction("s.Name", restForm.getName(), "name", paramsName, paramsValue, false)).
        append(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "s.Id", restForm.getAddinFieldsRestriction(), false));

    if (restForm.getProblem() != null) {
      fromList = fromList.concat(" join s.LinkSymptomProblems ls ");
      whereText = whereText.append(" ").
          append(DatabaseUtils.formatEJBQLObjectRestriction("ls.Id.CrmProblem", restForm.getProblem(), "problem", paramsName, paramsValue, false));
    }

    return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText.toString());

  }

}
