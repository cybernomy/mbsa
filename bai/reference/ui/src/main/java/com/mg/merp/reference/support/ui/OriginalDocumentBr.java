/*
 * OriginalDocumentBr.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.HierarchyRestrictionSupport;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultHierarchyBrowseForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.core.support.CoreUtils;
import com.mg.merp.reference.OriginalDocumentServiceLocal;
import com.mg.merp.reference.model.OriginalDocument;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер браузера "Оригиналов документов"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: OriginalDocumentBr.java,v 1.9 2008/05/19 14:40:21 safonov Exp $
 */
public class OriginalDocumentBr extends DefaultHierarchyBrowseForm {
  private final String INIT_QUERY_TEXT = "select %s from OriginalDocument od %s"; //$NON-NLS-1$
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();

  public OriginalDocumentBr() throws Exception {
    super();
    folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder"); //$NON-NLS-1$
    tree.setParentPropertyName("Folder.Id"); //$NON-NLS-1$
    treeUIProperties.put("FolderType", OriginalDocumentServiceLocal.FOLDER_PART); //$NON-NLS-1$
    restrictionFormName = "com/mg/merp/reference/resources/OriginalDocumentRest.mfd.xml"; //$NON-NLS-1$
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
    return CoreUtils.loadFolderHierarchy(OriginalDocumentServiceLocal.FOLDER_PART);
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
        result.add(new TableEJBQLFieldDef(OriginalDocument.class, "Id", "od.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(OriginalDocument.class, "DocNumber", "od.DocNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(OriginalDocument.class, "DocDate", "od.DocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(OriginalDocument.class, "DocName", "od.DocName", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(OriginalDocument.class, "CreateDate", "od.CreateDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(OriginalDocument.class, "Comments", "od.Comments", false));             //$NON-NLS-1$ //$NON-NLS-2$
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
    OriginalDocumentRest restForm = (OriginalDocumentRest) getRestrictionForm();

    paramsName.clear();
    paramsValue.clear();
    StringBuilder whereText = new StringBuilder(" where "). //$NON-NLS-1$
        append(DatabaseUtils.formatEJBQLHierarchyRestriction(((HierarchyRestrictionSupport) restForm).isUseHierarchy(), "od.Folder", 0, "folder", folderEntity, paramsName, paramsValue, true)).  //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRangeRestriction("od.DocDate", restForm.getDocDateFrom(), restForm.getDocDateTo(), "docDateFrom", "docDateTo", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        append(DatabaseUtils.formatEJBQLObjectRangeRestriction("od.CreateDate", restForm.getCreateDateFrom(), restForm.getCreateDateTo(), "createDateFrom", "createDateTo", paramsName, paramsValue, false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    return String.format(INIT_QUERY_TEXT, fieldsList, whereText.toString());
  }

  /**
   * обработчик загрузки оригинала на клиента
   */
  public void onActionDownloadOriginal(WidgetEvent event) {
    AttachmentHelper.download(((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys(), OriginalDocumentServiceLocal.SERVICE_NAME);
  }

  /**
   * обработчик загрузки оригинала в систему
   */
  public void onActionUploadOriginal(WidgetEvent event) {
    AttachmentHelper.upload(((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys(), OriginalDocumentServiceLocal.SERVICE_NAME);
  }

  /**
   * обработчик удаления оригиналов
   */
  public void onActionRemoveOriginal(WidgetEvent event) {
    AttachmentHelper.remove(((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys(), OriginalDocumentServiceLocal.SERVICE_NAME);
  }

  /**
   * обработчик показа оригиналов
   */
  public void onActionShowOriginal(WidgetEvent event) {
    AttachmentHelper.show(((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys(), OriginalDocumentServiceLocal.SERVICE_NAME);
  }

}

