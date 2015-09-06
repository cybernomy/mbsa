/*
 * ScheduleBr.java
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
package com.mg.merp.lbschedule.support.ui;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.HierarchyRestrictionSupport;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.MaintenanceTable;
import com.mg.framework.api.ui.widget.MenuItem;
import com.mg.framework.generic.ui.DefaultHierarchyBrowseForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.support.DocumentUtils;
import com.mg.merp.lbschedule.ScheduleServiceLocal;
import com.mg.merp.lbschedule.model.Schedule;
import com.mg.merp.reference.support.ReferenceUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер браузера "Графиков исполнения обязательств"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ScheduleBr.java,v 1.7 2007/09/10 13:29:15 sharapov Exp $
 */
public class ScheduleBr extends DefaultHierarchyBrowseForm {
  private final String INIT_QUERY_TEXT = "select distinct %s from ScheduleDocHeadLink sdhl left join sdhl.Schedule s left join sdhl.DocHead d %s %s"; //$NON-NLS-1$
  private final String TABLE_WIDGET = "table"; //$NON-NLS-1$
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();

  public ScheduleBr() throws Exception {
    super();
    folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder"); //$NON-NLS-1$
    treeUIProperties.put("FolderType", ScheduleServiceLocal.FOLDER_PART); //$NON-NLS-1$
    tree.setParentPropertyName("Folder.Id"); //$NON-NLS-1$
    restrictionFormName = "com/mg/merp/lbschedule/resources/ScheduleRest.mfd.xml"; //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultHierarchyBrowseForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    super.doOnRun();
    MenuItem addMenuItem = view.getWidget(TABLE_WIDGET).getPopupMenu().getMenuItem(MaintenanceTable.ADD_MENU_ITEM);
    addMenuItem.setVisible(false);
    addMenuItem.setEnabled(false);
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
    return ReferenceUtils.loadFolderHierarchy(ScheduleServiceLocal.FOLDER_PART);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createModel()
   */
  @Override
  protected MaintenanceTableModel createModel() {

    return new DefaultMaintenanceEJBQLTableModel() {

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
       */
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
        result.add(new TableEJBQLFieldDef(Schedule.class, "Id", "s.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Schedule.class, "Name", "s.Name", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Schedule.class, "Comments", "s.Comments", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Schedule.class, "Status", "s.Status", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(DocHead.class, "DocType", "dt.Code", "left join d.DocType dt", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(DocHead.class, "DocNumber", "d.DocNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(DocHead.class, "DocDate", "d.DocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
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
    ScheduleRest restForm = (ScheduleRest) getRestrictionForm();
    StringBuilder whereText = new StringBuilder(" where ") //$NON-NLS-1$
        .append(DatabaseUtils.formatEJBQLHierarchyRestriction(((HierarchyRestrictionSupport) restForm).isUseHierarchy(), "s.Folder", 0, "folder", folderEntity, paramsName, paramsValue, true)) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.DocType", restForm.getDocType(), "docType", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
        .append(DatabaseUtils.formatEJBQLStringRestriction("d.DocNumber", restForm.getDocNumber(), "docNumber", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.DocDate", restForm.getDocDate(), "docDate", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
        .append(DatabaseUtils.formatEJBQLObjectRestriction("s.Status", restForm.getHeadStatus(), "headStatus", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
        .append(DatabaseUtils.formatEJBQLStringRestriction("s.Comments", restForm.getComments(), "comments", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
        .append(DatabaseUtils.formatEJBQLObjectRestriction("si.Status", restForm.getItemStatus(), "itemStatus", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
        .append(DatabaseUtils.formatEJBQLObjectRestriction("si.ItemKind", restForm.getItemKindCode(), "itemKindCode", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
        .append(DatabaseUtils.formatEJBQLObjectRestriction("si.CurCode", restForm.getItemCurrency(), "itemCurrency", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
        .append(DatabaseUtils.formatEJBQLStringRestriction("si.Comments", restForm.getItemComments(), "itemComments", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
        .append(DatabaseUtils.formatEJBQLObjectRangeRestriction("si.ResultDate", restForm.getItemDateFrom(), restForm.getItemDateTill(), "itemDateFrom", "itemDateTill", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        .append(DatabaseUtils.formatEJBQLObjectRangeRestriction("si.ResultSum", restForm.getSumFrom(), restForm.getSumTill(), "sumFrom", "sumTill", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        .append(DatabaseUtils.formatEJBQLObjectRangeRestriction("si.FactSum", restForm.getFactSumFrom(), restForm.getFactSumTill(), "factSumFrom", "factSumTill", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        .append(DatabaseUtils.formatEJBQLObjectRestriction("si.From", restForm.getFromCode(), "fromCode", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
        .append(DatabaseUtils.formatEJBQLObjectRestriction("si.To", restForm.getToCode(), "toCode", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
        .append(DatabaseUtils.formatEJBQLObjectRestriction("sis.Catalog", restForm.getCatalogCode(), "catalogCode", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
        .append(DatabaseUtils.formatEJBQLObjectRestriction("si.ResourceFrom", restForm.getResourceFromCode(), "resourceFromCode", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
        .append(DatabaseUtils.formatEJBQLObjectRestriction("si.ResourceTo", restForm.getResourceToCode(), "resourceToCode", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
        .append(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "s.Id", restForm.getAddinFieldsRestriction(), false)); //$NON-NLS-1$
    if (whereText.toString().contains("si.") || whereText.toString().contains("sis.")) { //$NON-NLS-1$ //$NON-NLS-2$
      fromList = (", Item as si, ItemSpec as sis").concat(fromList); //$NON-NLS-1$
      whereText.append(" and si.Schedule = s.Id and sis.Item = si"); //$NON-NLS-1$
    }
    return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);
  }

  /**
   * Обработчик пункта КМ "Просмотреть документ"
   *
   * @param event - событие
   */
  @SuppressWarnings("unchecked") //$NON-NLS-1$
  public void onActionViewDocument(WidgetEvent event) {
    Serializable[] scheduleIds = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getSelectedPrimaryKeys();
    if (scheduleIds != null && scheduleIds.length > 0) {
      DocHead docHead = getScheduleService().getDocHead((Integer) scheduleIds[0]);
      if (docHead != null)
        MaintenanceHelper.view(DocumentUtils.getDocumentService(docHead.getDocSection()), docHead.getId(), null, null);
    }
  }

  private ScheduleServiceLocal getScheduleService() {
    return (ScheduleServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(ScheduleServiceLocal.LOCAL_SERVICE_NAME);
  }

}
