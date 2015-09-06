/*
 * OperationMt.java
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
package com.mg.merp.crm.support.ui;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.ui.MaintenanceFormActionListener;
import com.mg.framework.api.ui.MaintenanceFormEvent;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultCompoundMaintenanceForm;
import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.core.model.Folder;
import com.mg.merp.crm.LinkedDocumentServiceLocal;
import com.mg.merp.crm.model.LinkedDocument;
import com.mg.merp.crm.model.Operation;
import com.mg.merp.reference.OriginalDocumentServiceLocal;
import com.mg.merp.reference.model.OriginalDocument;
import com.mg.merp.reference.support.ui.OriginalDocumentFolderSearchHelp;

import java.io.Serializable;
import java.util.Set;

/**
 * Контроллер формы поддержки действий
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: OperationMt.java,v 1.7 2008/06/17 11:33:52 sharapov Exp $
 */
public class OperationMt extends DefaultCompoundMaintenanceForm implements MasterModelListener {
  protected AttributeMap originalProperties = new LocalDataTransferObject();
  private MaintenanceTableController original;
  private LinkedDocumentServiceLocal originalService;
  private OriginalDocumentServiceLocal originalDocumentService = (OriginalDocumentServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(OriginalDocumentServiceLocal.SERVICE_NAME);

  public OperationMt() throws Exception {
    super();
    addMasterModelListener(this);

    originalService = (LinkedDocumentServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(LinkedDocumentServiceLocal.SERVICE_NAME);
    original = new MaintenanceTableController(originalProperties) {

      /* (non-Javadoc)
       * @see com.mg.framework.support.ui.widget.MaintenanceTableController#doAdd()
       */
      @Override
      protected void doAdd() {
        SearchHelp searchHelp = SearchHelpProcessor.createSearch(OriginalDocumentFolderSearchHelp.SEARCH_HELP_NAME);
        searchHelp.addSearchHelpListener(new SearchHelpListener() {

          public void searchPerformed(SearchHelpEvent event) {
            doAddOriginalDocumentOfLinkedDocument((Folder) event.getItems()[0]);
          }

          public void searchCanceled(SearchHelpEvent event) {
            //do nothing
          }
        });
        searchHelp.search();

      }

      /* (non-Javadoc)
       * @see com.mg.framework.support.ui.widget.MaintenanceTableController#doView()
       */
      @Override
      protected void doView() {
        doViewOriginalDocumentOfLinkedDocument();
      }

      /* (non-Javadoc)
       * @see com.mg.framework.support.ui.widget.MaintenanceTableController#doEdit()
       */
      @Override
      protected void doEdit() {
        doEditOriginalDocumentOfLinkedDocument();
      }
    };

    original.initController(originalService, new LinkedDocumentMaintenanceEJBQLTableModel() {
      protected final String INIT_QUERY_TEXT = "select %s from LinkedDocument ld %s where ld.Operation = :operation"; //$NON-NLS-1$

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("operation"); //$NON-NLS-1$
        paramsValue.add(getEntity());
        return String.format(INIT_QUERY_TEXT, fieldsList, fromList);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
       */
      @Override
      protected int getPrimaryKeyFieldIndex() {
        return 0;
      }

      /*
       * (non-Javadoc)
       * @see com.mg.merp.crm.support.ui.LinkedDocumentMaintenanceEJBQLTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }

    });
    addMasterModelListener(original);
    addMasterModelListener(this);
  }

  public void masterChange(ModelChangeEvent event) {
    originalProperties.put("Operation", event.getEntity());     //$NON-NLS-1$
  }

  /**
   * Добавить связанный документ с оригиналом
   *
   * @param originalDocument - оригинал
   */
  protected void doAddLinkedDocumentWithOriginalDocument(OriginalDocument originalDocument) {
    originalService.createForOperationWithOriginal((Operation) getEntity(), originalDocument);
    original.refresh();
  }

  /**
   * Просмотр оригинала связанного документа
   */
  protected void doViewOriginalDocumentOfLinkedDocument() {
    OriginalDocument originalDocument = getOriginalDocumentOfCurrentLinkedDocument();
    if (originalDocument != null)
      MaintenanceHelper.view(originalDocumentService, originalDocument.getId(), null, null);
  }

  /**
   * Редактирование оригинала связанного документа
   */
  protected void doEditOriginalDocumentOfLinkedDocument() {
    OriginalDocument originalDocument = getOriginalDocumentOfCurrentLinkedDocument();
    if (originalDocument != null) {
      MaintenanceHelper.edit(originalDocumentService, originalDocument.getId(), null, new MaintenanceFormActionListener() {

        /* (non-Javadoc)
         * @see com.mg.framework.api.ui.MaintenanceFormActionListener#canceled(com.mg.framework.api.ui.MaintenanceFormEvent)
         */
        public void canceled(MaintenanceFormEvent event) {
          // do nothing
        }

        /* (non-Javadoc)
         * @see com.mg.framework.api.ui.MaintenanceFormActionListener#performed(com.mg.framework.api.ui.MaintenanceFormEvent)
         */
        public void performed(MaintenanceFormEvent event) {
          original.refresh();
        }
      });
    }
  }

  /**
   * Обработчик пункта КМ "Выбрать документ"
   *
   * @param event - событие
   */
  public void onActionSelectOriginalDocumnet(WidgetEvent event) {
    SearchHelp searchHelp = SearchHelpProcessor.createSearch("com.mg.merp.reference.support.ui.OriginalDocumentSearchHelp"); //$NON-NLS-1$
    searchHelp.addSearchHelpListener(new SearchHelpListener() {

      public void searchPerformed(SearchHelpEvent event) {
        doAddLinkedDocumentWithOriginalDocument((OriginalDocument) event.getItems()[0]);
      }

      public void searchCanceled(SearchHelpEvent event) {
        //do nothing
      }
    });
    searchHelp.search();
  }

  /**
   * Добавить оригинал со связанным документом
   *
   * @param originalDocumentDstFolder - папка-приемник документа оригинала связанного документа
   */
  protected void doAddOriginalDocumentOfLinkedDocument(Folder originalDocumentDstFolder) {
    if (originalDocumentDstFolder != null) {
      final OriginalDocument originalDocument = originalDocumentService.initialize();
      originalDocument.setFolder(originalDocumentDstFolder);
      MaintenanceHelper.add(originalDocumentService, originalDocument, null, new MaintenanceFormActionListener() {

        /* (non-Javadoc)
         * @see com.mg.framework.api.ui.MaintenanceFormActionListener#canceled(com.mg.framework.api.ui.MaintenanceFormEvent)
         */
        public void canceled(MaintenanceFormEvent event) {
          // do nothing
        }

        /* (non-Javadoc)
         * @see com.mg.framework.api.ui.MaintenanceFormActionListener#performed(com.mg.framework.api.ui.MaintenanceFormEvent)
         */
        public void performed(MaintenanceFormEvent event) {
          doAddLinkedDocumentWithOriginalDocument(originalDocument);
        }
      });
    }
  }

  /**
   * Получить оригинал текущего связанного документа
   *
   * @return оригинал связанного документа или <code>null</code> если не найден
   */
  private OriginalDocument getOriginalDocumentOfCurrentLinkedDocument() {
    Serializable[] selectedIds = ((DefaultEJBQLTableModel) original.getModel()).getSelectedPrimaryKeys();
    if (selectedIds == null || selectedIds.length == 0)
      return null;
    else {
      LinkedDocument linkedDocument = originalService.load((Integer) selectedIds[0]);
      if (linkedDocument != null)
        return linkedDocument.getOriginal();
      else
        return null;
    }
  }

}
