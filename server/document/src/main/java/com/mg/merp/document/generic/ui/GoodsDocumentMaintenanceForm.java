/*
 * GoodsDocumentMaintenanceForm.java
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
package com.mg.merp.document.generic.ui;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.ui.Form;
import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.api.ui.MaintenanceAction;
import com.mg.framework.api.ui.MaintenanceFormEvent;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.Widget;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.TabbedPane;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.docflow.support.DocFlowHelper;
import com.mg.merp.document.Document;
import com.mg.merp.document.GoodsDocumentSpecification;
import com.mg.merp.document.GoodsSelectionEvent;
import com.mg.merp.document.GoodsSelectionListener;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.reference.CurrentStockSituationLocator;
import com.mg.merp.reference.StockSituationBrowser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Контроллер формы поддержки товарных документов
 *
 * @author leonova
 * @version $Id: GoodsDocumentMaintenanceForm.java,v 1.21 2009/02/12 08:53:01 safonov Exp $
 */
public class GoodsDocumentMaintenanceForm extends DocumentMaintenanceForm implements MasterModelListener, StockSituationBrowser {
  /**
   * наименование таблицы спецификаций
   */
  protected final String SPEC_TABLE_WIDGET = "spec";
  /**
   * наименование панели вкладок
   */
  protected final String TABBED_PANE = "fields";
  /**
   * наименование кнопки переключения в режим поддержки спецификаций
   */
  protected final String LINE_MAINTENANCE_BUTTON = "LineMaintenanceButton";
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
  protected GoodsDocumentSpecification<? extends DocSpec, Integer> specService;
  /**
   * форма подбора номенклатуры для добавления спецификаций
   */
  protected Form goodsSelectionForm;
  /**
   * контекст импорта для SearchHelp поля "через кого"
   */
  protected String[] contractorThroughKinds;
  /**
   * имя формы для подбора спецификаций документа
   */
  protected String goodsSelectionFormName = "com.mg.merp.document.GoodsSelectionForm";

  public GoodsDocumentMaintenanceForm() throws Exception {
    super();
    setFreezeMaster(true);
    addMasterModelListener(this);

    spec = new MaintenanceTableController(specProperties) {

      @Override
      protected void doRefresh() {
        super.doRefresh();
        //обновим модель если находимся в режиме поддержки спецификаций, чтобы отобразить изменения
        //в заголовке документа, в противном случае не обновляем, т.к. находимся в режиме поддержки
        //заголовка и можем потерять данные введенные пользователем но не внесенные в хранилище
        //http://issues.m-g.ru/bugzilla/show_bug.cgi?id=4560
        if (!isDocLineTableReadOnly())
          refreshModel();
      }

      @Override
      protected void doAdd() {
        doAddSpecification();
      }

    };
  }

  /**
   * реализация добавления спецификаций документа
   */
  protected void doAddSpecification() {
    goodsSelectionForm = ApplicationDictionaryLocator.locate().getWindow(goodsSelectionFormName);
    goodsSelectionForm.addCloseActionListener(new FormActionListener() {

      public void actionPerformed(FormEvent event) {
        goodsSelectionForm = null;
      }

    });
    ((com.mg.merp.document.GoodsSelectionForm) goodsSelectionForm).execute(new GoodsSelectionListener() {

      public void doSelect(GoodsSelectionEvent event) {
        specService.bulkCreate((DocHead) getEntity(), event.getSpecInfo());
        spec.refresh();
      }

    }, ((DocHead) getEntity()));
  }

  /**
   * закрыть атрибуты формируемые на основе спецификаций
   */
  private void closeAggregateFields() {
    UIUtils.setReadOnlyProperty(view.getWidget("SumCur"), true);
    UIUtils.setReadOnlyProperty(view.getWidget("SumNat"), true);
    UIUtils.setReadOnlyProperty(view.getWidget("Weight"), true);
    UIUtils.setReadOnlyProperty(view.getWidget("Volume"), true);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractForm#doOnClose()
   */
  @Override
  protected void doOnClose() {
    //закроем связанную форму
    if (goodsSelectionForm != null) {
      goodsSelectionForm.close();
      goodsSelectionForm = null;
    }
    super.doOnClose();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnEdit()
   */
  @Override
  protected void doOnEdit() {
    super.doOnEdit();
    setDocLineTableReadOnly(true);
    closeAggregateFields();
    Widget lineMaintenanceButton = view.getWidget(LINE_MAINTENANCE_BUTTON);
    if (lineMaintenanceButton != null) {
      lineMaintenanceButton.setEnabled(true);
      lineMaintenanceButton.setVisible(true);
    }
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnAdd()
   */
  @Override
  protected void doOnAdd() {
    super.doOnAdd();
    closeAggregateFields();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnView()
   */
  @Override
  protected void doOnView() {
    super.doOnView();
    closeAggregateFields();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnClone()
   */
  @Override
  protected void doOnClone() {
    super.doOnClone();
    setDocLineTableReadOnly(true);
    closeAggregateFields();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnSave()
   */
  @Override
  protected void doOnSave() {
    super.doOnSave();
    if (MaintenanceAction.CLONE.equals(getAction()))
      setDocLineTableReadOnly(false);
  }

  /**
   * перевод формы поддержки документа в режим изменения спецификаций
   */
  protected void setSpecificationEditable() {
    setDocLineTableReadOnly(false);
    //установим активной закладку со спецификациями
    TabbedPane tabbedPane = (TabbedPane) view.getWidget(TABBED_PANE);
    if (tabbedPane != null)
      tabbedPane.setSelectedComponent(view.getWidget(SPEC_TABLE_WIDGET));
  }

  /**
   * получить режим поддержки таблицы спецификаций
   */
  protected boolean isDocLineTableReadOnly() {
    Widget docLineWidget = view.getWidget(SPEC_TABLE_WIDGET);
    return docLineWidget != null ? docLineWidget.isReadOnly() : false;
  }

  /**
   * установка режима поддержки таблицы спецификаций
   *
   * @param readOnly режим
   */
  protected void setDocLineTableReadOnly(boolean readOnly) {
    Widget docLineWidget = view.getWidget(SPEC_TABLE_WIDGET);
    if (docLineWidget != null)
      docLineWidget.setReadOnly(readOnly);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
   */
  public void masterChange(ModelChangeEvent event) {
    specProperties.put("DocHead", event.getEntity());
  }

  public void onActionShowStockSituation(WidgetEvent event) throws Exception {
    Serializable[] keys = (((DefaultMaintenanceEJBQLTableModel) spec.getModel())).getSelectedPrimaryKeys();
    if (keys.length > 0) {
      List<Integer> catalogIds = new ArrayList<Integer>();
      for (int i = 0; i < keys.length; i++) {
        DocSpec docSpec = specService.load((Integer) keys[i]);
        if (docSpec != null)
          catalogIds.add(docSpec.getCatalog().getId());
      }
      if (!catalogIds.isEmpty())
        CurrentStockSituationLocator.locate().showSituationForm(catalogIds.toArray(new Integer[catalogIds.size()]));
    }
  }

  @SuppressWarnings("unchecked")
  public void onActionLineMaintenance(WidgetEvent event) {
    //сохраняем заголовок документа
    //не можем вызвать doSave из предка, т.к. закроется форма, поэтому выполняем теже действия
    //только без закрытия
    setEntity(((Document) getService()).store((DocHead) getEntity()));
    //для отработки логики на СУБД
    ServerUtils.getPersistentManager().flush();
    if (getRefreshMode() != null && getRefreshMode().contains(RefreshMode.AFTER_STORE))
      doRefreshModel();
    //заблокируем кнопки
    view.getWidget(LINE_MAINTENANCE_BUTTON).setEnabled(false);
    view.getWidget(OK_BUTTON_NAME).setEnabled(false);
    //перейдем в режим поддержки спецификаций
    setSpecificationEditable();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doCancel()
   */
  @Override
  protected void doCancel() {
    //если находимся в режиме поддержки спецификаций, то посылаем событие о выполнении действия,
    //т.к. невозможно судить о том были ли изменения в спецификациях, также если редактировали
    //заголовок и перешли в режим поддержки спецификаций то необходимо отправить событие выполнено,
    //в противном случае внешние слушатели получат событие отмены, что неверно
    if (!isDocLineTableReadOnly()) {
      fireSaveAction(new MaintenanceFormEvent(this, getEntity()));
      close();
    } else
      super.doCancel();
  }

  /**
   * запуск формы поддержки документа для изменения списка спецификаций
   *
   * @param documentService сервис документа
   * @param documentEntity  документ
   */
  @SuppressWarnings("unchecked")
  public void executeEditSpecifications(final Document documentService, final DocHead documentEntity) {
    //проверим доступность редактирования документа (http://issues.m-g.ru/bugzilla/show_bug.cgi?id=4200)
    DocFlowHelper.checkStatus(documentEntity);
    execute(documentService, documentEntity, MaintenanceAction.VIEW);
    setSpecificationEditable();
  }

}
