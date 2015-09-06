/*
 * BOMBr.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.merp.mfreference.support.ui;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.HierarchyRestrictionSupport;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultHierarchyBrowseForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.framework.support.ui.Dialogs;
import com.mg.framework.support.ui.Dialogs.InputQueryDialogListener;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.merp.core.support.CoreUtils;
import com.mg.merp.mfreference.BOMServiceLocal;
import com.mg.merp.mfreference.model.Bom;
import com.mg.merp.mfreference.support.Messages;
import com.mg.merp.reference.support.ui.CatalogSearchHelp;

import org.apache.commons.lang.BooleanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Браузер состава изделий
 *
 * @author leonova
 * @version $Id: BomBr.java,v 1.14 2009/03/05 11:22:24 safonov Exp $
 */
public class BomBr extends DefaultHierarchyBrowseForm {
  private final String INIT_QUERY_TEXT = "select distinct %s from Bom b %s %s";
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();

  public BomBr() throws Exception {
    super();
    folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder");
    treeUIProperties.put("FolderType", new Integer(BOMServiceLocal.FOLDER_PART));
    tree.setParentPropertyName("Folder.Id");
    restrictionFormName = "com/mg/merp/mfreference/resources/BomRest.mfd.xml";
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
    return CoreUtils.loadFolderHierarchy(BOMServiceLocal.FOLDER_PART);
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
        result.add(new TableEJBQLFieldDef(Bom.class, "Id", "b.Id", true));
        result.add(new TableEJBQLFieldDef(Bom.class, "BomType", "b.BomType", false));
        result.add(new TableEJBQLFieldDef(Bom.class, "BomStatus", "b.BomStatus", false));
        result.add(new TableEJBQLFieldDef(Bom.class, "Revision", "b.Revision", false));
        result.add(new TableEJBQLFieldDef(Bom.class, "RevisionDateTime", "b.RevisionDateTime", false));
        result.add(new TableEJBQLFieldDef(Bom.class, "RollUpDateTime", "b.RollUpDateTime", false));
        result.add(new TableEJBQLFieldDef(Bom.class, "RunTicks", "b.RunTicks", false));
        result.add(new TableEJBQLFieldDef(Bom.class, "SetupTicks", "b.SetupTicks", false));
        result.add(new TableEJBQLFieldDef(Bom.class, "ScheduleDirection", "b.ScheduleDirection", false));
        result.add(new TableEJBQLFieldDef(Bom.class, "UseMoveTimes", "b.UseMoveTimes", false));
        result.add(new TableEJBQLFieldDef(Bom.class, "UseQueueTimes", "b.UseQueueTimes", false));
        result.add(new TableEJBQLFieldDef(Bom.class, "PriorityFreezeFlag", "b.PriorityFreezeFlag", false));
        result.add(new TableEJBQLFieldDef(Bom.class, "Priority", "b.Priority", false));
        result.add(new TableEJBQLFieldDef(Bom.class, "UseFiniteCapacity", "b.UseFiniteCapacity", false));
        result.add(new TableEJBQLFieldDef(Bom.class, "Cell", "cl.CellName", "left join b.Cell as cl", false));
        result.add(new TableEJBQLFieldDef(Bom.class, "ApprovedFlag", "b.ApprovedFlag", false));
        result.add(new TableEJBQLFieldDef(Bom.class, "CreateJobApproved", "b.CreateJobApproved", false));
        result.add(new TableEJBQLFieldDef(Bom.class, "BomVersionRequired", "b.BomVersionRequired", false));
        result.add(new TableEJBQLFieldDef(Bom.class, "Comment", "b.Comment", false));
        result.add(new TableEJBQLFieldDef(Bom.class, "SetupTimeUM", "b.SetupTimeUM.Code", false));
        result.add(new TableEJBQLFieldDef(Bom.class, "RunTimeUM", "b.RunTimeUM.Code", false));
        result.add(new TableEJBQLFieldDef(Bom.class, "PcsPerHrCell", "b.PcsPerHrCell", false));
        result.add(new TableEJBQLFieldDef(Bom.class, "Catalog.Code", "b.Catalog.Code", false));
        result.add(new TableEJBQLFieldDef(Bom.class, "Catalog.FullName", "b.Catalog.FullName", false));
        result.add(new TableEJBQLFieldDef(Bom.class, "Catalog.Measure1", "meas.Code", "left join b.Catalog.Measure1 as meas", false));
        result.add(new TableEJBQLFieldDef(Bom.class, "MinLotQty", "b.MinLotQty", false));
        result.add(new TableEJBQLFieldDef(Bom.class, "MaxLotQty", "b.MaxLotQty", false));
        result.add(new TableEJBQLFieldDef(Bom.class, "LotIncrementQty", "b.LotIncrementQty", false));
        result.add(new TableEJBQLFieldDef(Bom.class, "PlanningLotQty", "b.PlanningLotQty", false));
        result.add(new TableEJBQLFieldDef(Bom.class, "DefSrcStock", "b.DefSrcStock.Code", false));
        result.add(new TableEJBQLFieldDef(Bom.class, "DefDstStock", "b.DefDstStock.Code", false));
        result.add(new TableEJBQLFieldDef(Bom.class, "DefSrcMol", "b.DefSrcMol.Code", false));
        result.add(new TableEJBQLFieldDef(Bom.class, "DefDstMol", "b.DefDstMol.Code", false));
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
    BomRest restForm = (BomRest) getRestrictionForm();
    StringBuilder whereText = new StringBuilder(" where ").append(DatabaseUtils.formatEJBQLHierarchyRestriction(((HierarchyRestrictionSupport) restForm).isUseHierarchy(), "b.Folder", 0, "folder", folderEntity, paramsName, paramsValue, true))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("b.BomType", restForm.getBomType(), "bomType", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("b.BomStatus", restForm.getBomStatus(), "bomStatus", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRangeRestriction("b.RevisionDateTime", restForm.getRevisionDateFrom(), restForm.getRevisionDateTill(), "revisionDateFrom", "revisionDateTill", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("b.DefDstMol", restForm.getDstMol(), "dstMol", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("b.Catalog", restForm.getCatalogCode(), "catalogCode", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("b.DefSrcMol", restForm.getSrcMol(), "srcMol", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("b.DefSrcStock", restForm.getSrcStock(), "srcStock", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("b.DefDstStock", restForm.getDstStock(), "dstStock", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("b.SchedDirection", restForm.getSchedDirection(), "schedDirection", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("b.Cell", restForm.getCellCode(), "cellCode", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("br.WorkCenter", restForm.getWcCode(), "wcCode", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("bmat.Catalog", restForm.getMaterialCode(), "materialCode", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("bmat.ResourceGroup", restForm.getResourceCodeMaterail(), "resourceCodeMaterail", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("bmac.ResourceGroup", restForm.getResourceCodeMachine(), "resourceCodeMachine", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("bl.ResourceGroup", restForm.getResourceCodeLabor(), "resourceCodeLabor", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("bl.LaborClass", restForm.getLaborClass(), "laborClass", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "b.Id", restForm.getAddinFieldsRestriction(), false));

    if (restForm.getApproverFlag() != 0) {
      whereText = whereText.append(DatabaseUtils.formatEJBQLObjectRestriction("b.ApprovedFlag", BooleanUtils.toBoolean(restForm.getApproverFlag(), 2, 1), "approvedFlag", paramsName, paramsValue, false));
    }
    if (whereText.toString().contains("br.") || whereText.toString().contains("bmat.") ||
        whereText.toString().contains("bmac.") || whereText.toString().contains("bl.")) {
      whereText = whereText.append(" and br.Bom = b.Id ");
      fromList = (", BomRoute br ").concat(fromList);
    }
    if (whereText.toString().contains("bmat.")) {
      whereText = whereText.append(" and bmat.BomRoute = br.Id ");
      fromList = (", BomMaterial bmat ").concat(fromList);
    }
    if (whereText.toString().contains("bmac.")) {
      whereText = whereText.append(" and bmac.BomRoute = br.Id ");
      fromList = (", BomMachine bmac ").concat(fromList);
    }
    if (whereText.toString().contains("bl.")) {
      whereText = whereText.append(" and bl.BomRoute = br.Id ");
      fromList = (", BomLabor bl ").concat(fromList);
    }
    return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText.toString());
  }

  private void performCalculateOperLeadTimes(Date actualityDate, PersistentObject[] catalogs) {
    int[] ids = new int[catalogs.length];
    for (int i = 0; i < ids.length; i++)
      ids[i] = (Integer) catalogs[i].getPrimaryKey();
    ((BOMServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(BOMServiceLocal.SERVICE_NAME))
        .calculateOperLeadTimes(actualityDate, ids);
  }

  private void showCatalogSearchHelp(final Date actualityDate) {
    CatalogSearchHelp searchHelp = (CatalogSearchHelp) SearchHelpProcessor.createSearch("com.mg.merp.reference.support.ui.CatalogSearchHelp");
    searchHelp.addSearchHelpListener(new SearchHelpListener() {

      public void searchCanceled(SearchHelpEvent event) {
      }

      public void searchPerformed(SearchHelpEvent event) {
        performCalculateOperLeadTimes(actualityDate, event.getItems());
      }

    });
    searchHelp.search();
  }

  public void onActionCalculateOperLeadTimes(WidgetEvent event) {
    Dialogs.inputQuery(Messages.getInstance().getMessage(Messages.ACTUALITY_DATE_TITLE), Messages.getInstance().getMessage(Messages.ACTUALITY_DATE_PROMPT), DateTimeUtils.nowDate(), new InputQueryDialogListener<Date>() {

      public void inputCanceled() {
      }

      public void inputPerformed(Date value) {
        showCatalogSearchHelp(value);
      }

    });
  }

}
