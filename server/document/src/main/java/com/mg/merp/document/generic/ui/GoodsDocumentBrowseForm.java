/*
 * GoodsDocumentBrowseForm.java
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
package com.mg.merp.document.generic.ui;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.ui.MaintenanceFormActionListener;
import com.mg.framework.api.ui.MaintenanceFormEvent;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.Widget;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.CheckBoxMenuItem;
import com.mg.framework.api.ui.widget.SplitPane;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.document.Document;
import com.mg.merp.document.GoodsDocument;
import com.mg.merp.document.GoodsDocumentSpecification;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.reference.CurrentStockSituationLocator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Базовый класс контроллера формы списка товарных документов
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @author Konstantin S. Alikaev
 * @version $Id: GoodsDocumentBrowseForm.java,v 1.6 2009/02/12 08:22:23 safonov Exp $
 */
public class GoodsDocumentBrowseForm extends DocumentBrowseForm {
  /**
   * наименование таблицы спецификаций
   */
  protected final String SPEC_TABLE_WIDGET = "spec";
  private final String IS_SHOW_SPEC_TABLE = "isShowSpecTable";
  private final String SPLIT_CONTAINER_NAME = "tables";
  private final String VIEW_DOCUMENTLINE_LIST_MENUITEM = "viewDocumentLineList";
  protected GoodsDocumentRest restGoodDocument;
  protected String specMaintenanceFormName;
  /**
   * таблица спецификаций
   */
  protected MaintenanceTableController spec;

  /**
   * инициализирующие свойства спецификации
   */
  protected AttributeMap specProperties = new LocalDataTransferObject();

  /**
   * бизнес-компонент спецификация
   */
  protected GoodsDocumentSpecification<? extends DocSpec, Integer> specService = null;

  /**
   * Признак отображения таблицы спецификаций
   */
  protected boolean isShowSpecTable = false;

  public GoodsDocumentBrowseForm() {
    super();
    spec = createGoodsDocSpecTableController();
    table.addMasterModelListener(new MasterModelListener() {

      public void masterChange(ModelChangeEvent event) {
        if (isShowSpecTable) {
          specProperties.put("DocHead.Id", event.getModelKey());
          spec.fireMasterChange(event);
        }
      }

    });
  }

  /**
   * создание адаптера таблицы спецификаций дополнительного списка
   */
  protected MaintenanceTableController createGoodsDocSpecTableController() {
    return new GoodsDocSpecMaintenanceTableController(specProperties);
  }

  /**
   * получить бизнес-компонент спецификации документа
   *
   * @return бизнес-компонент спецификации документа
   */
  @SuppressWarnings("unchecked")
  protected GoodsDocumentSpecification<?, Integer> getDocSpecService() {
    if (specService == null)
      specService = ((GoodsDocument) service).getSpecificationService();
    return specService;
  }

  /**
   * создать модель для отображения спецификаций документа в дополнительном браузере
   *
   * @return модель спецификаций документа
   */
  protected AbstractGoodsDocSpecTableModel createGoodsDocSpecTableModel() {
    return new DefaultGoodsDocSpecEJBQLTableModel();
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultHierarchyBrowseForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    isShowSpecTable = view.getUIProfile().getProperty(IS_SHOW_SPEC_TABLE, false);
    spec.initController(getDocSpecService(), createGoodsDocSpecTableModel());
    super.doOnRun();
    ((CheckBoxMenuItem) view.getWidget(TABLE_WIDGET).getPopupMenu().getMenuItem(VIEW_DOCUMENTLINE_LIST_MENUITEM)).setSelected(isShowSpecTable);
    setVisibleSpec(isShowSpecTable);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.ui.DocumentBr#createQueryText()
   */
  @Override
  protected String createQueryText() {
    super.createQueryText();
    restGoodDocument = (GoodsDocumentRest) getRestrictionForm();
    whereText.append(DatabaseUtils.formatEJBQLStringRestriction("d.ContractNumber", restDocument.getContractNumber(), "contractDocNumber", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.ContractType", restDocument.getContractType(), "contractDocType", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.ContractDate", restDocument.getContractDate(), "contractDocDate", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
        .append(DatabaseUtils.formatEJBQLObjectRestriction("ds.Catalog", restGoodDocument.getCatalogName(), "catalogName", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
        .append(DatabaseUtils.formatEJBQLObjectRestriction("ds.Catalog.Folder", restGoodDocument.getCatalogFolder(), "catalogFolder", paramsName, paramsValue, false)); //$NON-NLS-1$ //$NON-NLS-2$
    if (whereText.indexOf("Catalog") != -1) {             //$NON-NLS-1$
      fromList = (", DocSpec as ds ").concat(fromList); //$NON-NLS-1$
      whereText.append(" and ds.DocHead = d.id "); //$NON-NLS-1$
    }
    return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText.toString());
  }

  /**
   * Обработчик пункта КМ "Изменить спецификации"
   */
  @SuppressWarnings("unchecked")
  protected void onActionEditSpecification(WidgetEvent event) {
    Serializable[] docIds = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
    if (docIds.length == 1) {
      //load form
      GoodsDocumentMaintenanceForm form = (GoodsDocumentMaintenanceForm) ApplicationDictionaryLocator.locate().getMaintenaceForm(service, specMaintenanceFormName);
      //set listener on action
      form.addMaintenanceFormActionListener(new MaintenanceFormActionListener() {

        public void canceled(MaintenanceFormEvent event) {
        }

        public void performed(MaintenanceFormEvent event) {
          table.refresh();
        }
      });
      //execute form
      form.executeEditSpecifications((Document) service, (DocHead) service.load(docIds[0]));
    }
  }

  /**
   * Обработчик пункта КМ "Показать спецификацию"
   */
  protected void onActionViewDocumentLineList(WidgetEvent event) {
    boolean selected = ((CheckBoxMenuItem) event.getWidget()).isSelected();
    if (isShowSpecTable != selected) {
      if (selected) {
        Serializable[] docIds = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
        if (docIds.length == 1) {
          Serializable docHeadId = docIds[0];
          ((MaintenanceTableModel) spec.getModel()).setCurrentMaster(docHeadId);
          spec.refresh();
        }
      }
      isShowSpecTable = selected;
      setVisibleSpec(isShowSpecTable);
    }
  }

  /**
   * обработчик пункта КМ "Количество на складах"
   */
  protected void onActionShowStockSituation(WidgetEvent event) {
    Serializable[] keys = (((AbstractGoodsDocSpecTableModel) spec.getModel())).getSelectedPrimaryKeys();
    if (keys.length > 0) {
      List<Integer> catalogIds = new ArrayList<Integer>();
      for (int i = 0; i < keys.length; i++) {
        DocSpec docSpec = getDocSpecService().load((Integer) keys[i]);
        if (docSpec != null)
          catalogIds.add(docSpec.getCatalog().getId());
      }
      if (!catalogIds.isEmpty())
        CurrentStockSituationLocator.locate().showSituationForm(catalogIds.toArray(new Integer[catalogIds.size()]));
    }
  }

  /**
   * Установка значения видимости таблицы
   *
   * @param isVisible - <code>true</code> - видна, иначе не видна
   */
  private void setVisibleSpec(boolean isVisible) {
    Widget widget = view.getWidget(SPEC_TABLE_WIDGET);
    if (widget != null)
      widget.setVisible(isVisible);
    this.isShowSpecTable = isVisible;
    view.getUIProfile().setProperty(IS_SHOW_SPEC_TABLE, isVisible);
    ((SplitPane) view.getWidget(SPLIT_CONTAINER_NAME)).setDividerLocation(isVisible ? 62 : 100);
  }

}
