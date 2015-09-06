/*
 * DocFlowStageMt.java
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
package com.mg.merp.docflow.support.ui;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.PopupMenu;
import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.docflow.DocFlowStageServiceLocal;
import com.mg.merp.docflow.support.DocumentUtils;
import com.mg.merp.docprocess.model.DocProcessStage;
import com.mg.merp.docprocess.model.DocProcessStageRights;
import com.mg.merp.document.model.DocSection;
import com.mg.merp.security.model.Groups;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Oleg V. Safonov
 * @version $Id: DocFlowStageMt.java,v 1.7 2007/11/28 06:58:15 safonov Exp $
 */
public class DocFlowStageMt extends DefaultMaintenanceForm implements MasterModelListener {
  private static final String DOCFLOW_STAGE_SERVICE_NAME = "merp/docflow/DocFlowStage"; //$NON-NLS-1$
  private final static String LOAD_RIGHTS_EJBQL = "select r.Id, r.SecGroups.Name, r.Grants from DocProcessStageRights r where r.DocProcessStage = :docProcessStage"; //$NON-NLS-1$
  protected String linkDocModelName = null;
  protected DefaultTableController grantsTable;
  private int[] currentRightIds;

  public DocFlowStageMt() {
    super();
    setMasterDetail(true);
    grantsTable = new DefaultTableController(new DefaultEJBQLTableModel() {
      @Override
      protected void doLoad() {
        setQuery(LOAD_RIGHTS_EJBQL, new String[]{"docProcessStage"}, new Object[]{getEntity()});
      }

      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(DocProcessStageRights.class, "Id", "r.Id", true));//не перемещать данное поле, индекс 0 используется в методе setSelectedRows
        result.add(new TableEJBQLFieldDef(DocProcessStageRights.class, "SecGroups.Name", "r.SecGroups.Name", true));
        result.add(new TableEJBQLFieldDef(DocProcessStageRights.class, "Grants", "r.Grants", true));
        return result;
      }

      @Override
      public void setSelectedRows(int[] rows) {
        currentRightIds = new int[rows.length];
        for (int i = 0; i < rows.length; i++)
          currentRightIds[i] = (Integer) getRowList().get(rows[i])[0];
        super.setSelectedRows(rows);
      }
    });
    addMasterModelListener(this);
  }

  private void refreshRights() {
    grantsTable.getModel().load();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
   */
  public void masterChange(ModelChangeEvent event) {
    refreshRights();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doSetDependentReadOnly(boolean)
   */
  @Override
  protected void doSetDependentReadOnly(boolean readOnly) {
    super.doSetDependentReadOnly(readOnly);
    PopupMenu pm = view.getWidget("grantsTable").getPopupMenu();
    pm.getMenuItem("grantRightsForGroups").setEnabled(!readOnly);
    pm.getMenuItem("grantRights").setEnabled(!readOnly);
    pm.getMenuItem("revokeRights").setEnabled(!readOnly);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    Integer modelId = ((DocProcessStage) getEntity()).getLinkDocModel();
    if (modelId != null) {
      try {
        DocSection docSection = ServerUtils.getPersistentManager().find(DocSection.class, ((DocProcessStage) getEntity()).getLinkDocSection().getId());
        linkDocModelName = (String) DocumentUtils.loadDocumentModel(docSection.getModelSysClass(), ((DocProcessStage) getEntity()).getLinkDocModel()).getAttribute("ModelName");
      } catch (Exception e) {
        logger.error("Error during document model loading", e);
        linkDocModelName = "<unknown>";
      }
    }
    super.doOnRun();
  }

  protected void onActionChooseDocumentModel(WidgetEvent event) throws Exception {
    SearchHelp searchHelp = SearchHelpProcessor.createSearch("com.mg.merp.document.support.ui.UniversalDocModelSearchHelp");
    searchHelp.addSearchHelpListener(new SearchHelpListener() {
      public void searchPerformed(SearchHelpEvent event) {
        PersistentObject entity = event.getItems()[0];
        ((DocProcessStage) getEntity()).setLinkDocModel((Integer) entity.getPrimaryKey());
        linkDocModelName = (String) entity.getAttribute("ModelName");
        view.flushModel();
      }

      public void searchCanceled(SearchHelpEvent event) {
        //do nothing
      }
    });
    Map<String, Object> context = new HashMap<String, Object>();
    context.put("DocSection", ((DocProcessStage) getEntity()).getLinkDocSection());
    context.put("DocModelFolder", ((DocProcessStage) getEntity()).getLinkDocModelFolder());
    searchHelp.setImportContext(context);
    searchHelp.search();
  }

  protected void onActionClearDocumentModel(WidgetEvent event) {
    ((DocProcessStage) getEntity()).setLinkDocModel(null);
    linkDocModelName = null;
  }

  protected void onActionGrantRightsForGroups(WidgetEvent event) throws Exception {
    SearchHelp searchHelp = SearchHelpProcessor.createSearch("com.mg.merp.security.support.ui.SecGroupSearchHelp");
    searchHelp.addSearchHelpListener(new SearchHelpListener() {
      public void searchPerformed(SearchHelpEvent event) {
        final Groups[] groups = new Groups[event.getItems().length];
        for (int i = 0; i < groups.length; i++)
          groups[i] = (Groups) event.getItems()[i];
        final DocFlowStageRightsDialog rightDialog = (DocFlowStageRightsDialog) UIProducer.produceForm("com/mg/merp/docflow/resources/DocFlowStageRightsDialog.mfd.xml");
        rightDialog.addOkActionListener(new FormActionListener() {
          public void actionPerformed(FormEvent event) {
            DocFlowStageServiceLocal service = (DocFlowStageServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(DOCFLOW_STAGE_SERVICE_NAME);
            service.grantRightsForGroups(((DocProcessStage) getEntity()), groups, rightDialog.getGrants());
            refreshRights();
          }
        });
        rightDialog.run(true);
      }

      public void searchCanceled(SearchHelpEvent event) {
        //do nothing
      }
    });
    searchHelp.search();
  }

  protected void onActionRevokeRights(WidgetEvent event) {
    if (currentRightIds != null && currentRightIds.length != 0) {
      List<DocProcessStageRights> rights = new ArrayList<DocProcessStageRights>();
      for (int rightId : currentRightIds) {
        rights.add(ServerUtils.getPersistentManager().find(DocProcessStageRights.class, rightId));
      }
      DocFlowStageServiceLocal service = (DocFlowStageServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(DOCFLOW_STAGE_SERVICE_NAME);
      service.revokeRights(rights.toArray(new DocProcessStageRights[rights.size()]));
      refreshRights();
    }
  }

  protected void onActionGrantRights(WidgetEvent event) {
    if (currentRightIds != null && currentRightIds.length != 0) {
      final DocFlowStageRightsDialog rightDialog = (DocFlowStageRightsDialog) UIProducer.produceForm("com/mg/merp/docflow/resources/DocFlowStageRightsDialog.mfd.xml");
      rightDialog.addOkActionListener(new FormActionListener() {
        public void actionPerformed(FormEvent event) {
          List<DocProcessStageRights> rights = new ArrayList<DocProcessStageRights>();
          for (int rightId : currentRightIds)
            rights.add(ServerUtils.getPersistentManager().find(DocProcessStageRights.class, rightId));
          DocFlowStageServiceLocal service = (DocFlowStageServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(DOCFLOW_STAGE_SERVICE_NAME);
          service.grantRights(rights.toArray(new DocProcessStageRights[rights.size()]), rightDialog.getGrants());
          refreshRights();
        }
      });
      rightDialog.run(true);
    }
  }

}
