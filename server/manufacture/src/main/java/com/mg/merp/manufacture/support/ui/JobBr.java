/*
 * JobBr.java
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
package com.mg.merp.manufacture.support.ui;

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
import com.mg.merp.manufacture.JobServiceLocal;
import com.mg.merp.manufacture.model.Job;
import com.mg.merp.reference.support.ReferenceUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы списка заказ-нарядов на производство
 *
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: JobBr.java,v 1.5 2007/07/30 10:27:10 safonov Exp $
 */
public class JobBr extends DefaultHierarchyBrowseForm {
  private final String INIT_QUERY_TEXT = "select %s from Job j %s %s";
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();

  public JobBr() throws Exception {
    super();
    folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder");
    treeUIProperties.put("FolderType", JobServiceLocal.FOLDER_PART);
    tree.setParentPropertyName("Folder.Id");
    restrictionFormName = "com/mg/merp/manufacture/resources/JobRest.mfd.xml";
  }


  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#initializeMaster()
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
    return ReferenceUtils.loadFolderHierarchy(JobServiceLocal.FOLDER_PART);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
   */
  @Override
  protected String createQueryText() {
    String whereText = "";
    Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
    String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
    paramsName.clear();
    paramsValue.clear();
    JobRest restForm = (JobRest) getRestrictionForm();
    whereText = " where ".concat(DatabaseUtils.formatEJBQLHierarchyRestriction(((HierarchyRestrictionSupport) restForm).isUseHierarchy(), "j.Folder", 0, "folder", folderEntity, paramsName, paramsValue, true)).
        concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("j.JobDate", restForm.getJobDateFrom(), restForm.getJobDateTill(), "jobDateFrom", "jobDateTill", paramsName, paramsValue, false)).
        concat(DatabaseUtils.formatEJBQLStringRestriction("j.JobNumber", restForm.getJobNumber(), "jobNumber", paramsName, paramsValue, false)).
        concat(DatabaseUtils.formatEJBQLObjectRestriction("j.JobStatus", restForm.getJobStatus(), "jobStatus", paramsName, paramsValue, false)).
        concat(DatabaseUtils.formatEJBQLObjectRestriction("j.Catalog", restForm.getCatalogCode(), "catalogCode", paramsName, paramsValue, false)).
        concat(DatabaseUtils.formatEJBQLObjectRestriction("j.DefDstMol", restForm.getDstMolCode(), "dstMolCode", paramsName, paramsValue, false)).
        concat(DatabaseUtils.formatEJBQLObjectRestriction("j.DefDstStock", restForm.getDstStockCode(), "dstStockCode", paramsName, paramsValue, false)).
        concat(DatabaseUtils.formatEJBQLObjectRestriction("j.DefSrcStock", restForm.getSrcStockCode(), "srcStockCode", paramsName, paramsValue, false)).
        concat(DatabaseUtils.formatEJBQLObjectRestriction("j.DefSrcMol", restForm.getSrcMolCode(), "srcMolCode", paramsName, paramsValue, false)).
        concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("j.StartDate", restForm.getStartDateFrom(), restForm.getStartDateTill(), "startDateFrom", "startDateTill", paramsName, paramsValue, false)).
        concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("j.EndDate", restForm.getEndDateFrom(), restForm.getEndDateTill(), "endDateFrom", "endDateTill", paramsName, paramsValue, false)).
        concat(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "j.Id", restForm.getAddinFieldsRestriction(), false));
    return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);
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
        result.add(new TableEJBQLFieldDef(Job.class, "Id", "j.Id", true));
        result.add(new TableEJBQLFieldDef(Job.class, "JobNumber", "j.JobNumber", false));
        result.add(new TableEJBQLFieldDef(Job.class, "JobDate", "j.JobDate", false));
        result.add(new TableEJBQLFieldDef(Job.class, "JobStatus", "j.JobStatus", false));
        result.add(new TableEJBQLFieldDef(Job.class, "QtyComplete", "j.QtyComplete", false));
        result.add(new TableEJBQLFieldDef(Job.class, "QtyReleased", "j.QtyReleased", false));
        result.add(new TableEJBQLFieldDef(Job.class, "QtyScrapped", "j.QtyScrapped", false));
        result.add(new TableEJBQLFieldDef(Job.class, "RollUpDateTime", "j.RollUpDateTime", false));
        result.add(new TableEJBQLFieldDef(Job.class, "StartDate", "j.StartDate", false));
        result.add(new TableEJBQLFieldDef(Job.class, "EndDate", "j.EndDate", false));
        result.add(new TableEJBQLFieldDef(Job.class, "UseMoveTimes", "j.UseMoveTimes", false));
        result.add(new TableEJBQLFieldDef(Job.class, "UseFiniteCapacity", "j.UseFiniteCapacity", false));
        result.add(new TableEJBQLFieldDef(Job.class, "UseQueueTimes", "j.UseQueueTimes", false));
        result.add(new TableEJBQLFieldDef(Job.class, "Priority", "j.Priority", false));
        result.add(new TableEJBQLFieldDef(Job.class, "PriorityFreezeFlag", "j.PriorityFreezeFlag", false));
        result.add(new TableEJBQLFieldDef(Job.class, "MrpEndDate", "j.MrpEndDate", false));
        result.add(new TableEJBQLFieldDef(Job.class, "Comment", "j.Comment", false));
        result.add(new TableEJBQLFieldDef(Job.class, "ParentJob", "(pj.JobNumber||','||pj.JobDate) as parentJob", "left join j.ParentJob as pj", false));
        result.add(new TableEJBQLFieldDef(Job.class, "RootJob", "(rj.JobNumber||','||rj.JobDate) as rootJob", "left join j.RootJob as rj", false));
        result.add(new TableEJBQLFieldDef(Job.class, "DefSrcStock", "j.DefSrcStock.Code", false));
        result.add(new TableEJBQLFieldDef(Job.class, "DefSrcMol", "j.DefSrcMol.Code", false));
        result.add(new TableEJBQLFieldDef(Job.class, "DefDstStock", "j.DefDstStock.Code", false));
        result.add(new TableEJBQLFieldDef(Job.class, "DefDstMol", "j.DefDstMol.Code", false));
        result.add(new TableEJBQLFieldDef(Job.class, "Catalog.Code", "j.Catalog.Code", false));
        result.add(new TableEJBQLFieldDef(Job.class, "Catalog.FullName", "j.Catalog.FullName", false));
        result.add(new TableEJBQLFieldDef(Job.class, "Catalog.Measure1", "meas.Code", "left join j.Catalog.Measure1 as meas", false));
        result.add(new TableEJBQLFieldDef(Job.class, "Cell", "c.CellName", "left join j.Cell as c", false));
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }

    };
  }

  private JobServiceLocal getJobService() {
    return (JobServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(JobServiceLocal.SERVICE_NAME);
  }

  public void onActionCreateBOM(WidgetEvent event) {
    Serializable[] keys = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
    if (keys.length == 0)
      return;
    for (Serializable id : keys)
      getJobService().copyBOM((Integer) id);
  }

  public void onActionRun(WidgetEvent event) {
    Serializable[] keys = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
    if (keys.length == 0)
      return;
    for (Serializable id : keys)
      getJobService().run((Integer) id);
  }

  public void onActionStop(WidgetEvent event) {
    Serializable[] keys = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
    if (keys.length == 0)
      return;
    for (Serializable id : keys)
      getJobService().stop((Integer) id);
  }

  public void onActionComplete(WidgetEvent event) {
    Serializable[] keys = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
    if (keys.length == 0)
      return;
    for (Serializable id : keys)
      getJobService().complete((Integer) id);
  }

}