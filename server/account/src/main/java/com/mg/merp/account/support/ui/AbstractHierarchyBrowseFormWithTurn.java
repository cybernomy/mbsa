/*
 * AbstractHierarchyBrowseFormWithTurn.java
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
package com.mg.merp.account.support.ui;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.Widget;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.CheckBoxMenuItem;
import com.mg.framework.api.ui.widget.MaintenanceTable;
import com.mg.framework.api.ui.widget.PopupMenu;
import com.mg.framework.api.ui.widget.SplitPane;
import com.mg.framework.generic.ui.DefaultHierarchyBrowseForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.merp.account.OperationServiceLocal;

import java.io.Serializable;
import java.util.List;

/**
 * Контроллер иерархического браузера с суббраузером оборотов
 *
 * @author Artem V. Sharapov
 * @version $Id: AbstractHierarchyBrowseFormWithTurn.java,v 1.2 2009/03/17 08:43:43 sharapov Exp $
 */
public abstract class AbstractHierarchyBrowseFormWithTurn extends DefaultHierarchyBrowseForm {

  private final String IS_SHOW_TURN_TABLES = "isShowTurnTables";
  private final String SPLIT_CONTAINER_NAME = "turnSplit";
  private final String VIEW_TURNS_MENUITEM = "viewTurns";
  private final String SPEC_TABLE_WIDGET = "turnFields";
  protected OperationServiceLocal operationService;
  protected boolean isShowTurn = false;
  protected AttributeMap turnDbProperties = new LocalDataTransferObject();
  protected MaintenanceTableController turnDb;
  protected AttributeMap turnKtProperties = new LocalDataTransferObject();
  protected MaintenanceTableController turnKt;
  protected AttributeMap turnProperties = new LocalDataTransferObject();
  protected MaintenanceTableController turn;


  public AbstractHierarchyBrowseFormWithTurn() throws Exception {
    super();
    operationService = (OperationServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/Operation");

    turnDb = new MaintenanceTableController(turnDbProperties);
    turnKt = new MaintenanceTableController(turnKtProperties);
    turn = new MaintenanceTableController(turnProperties);

    table.addMasterModelListener(new MasterModelListener() {

      /* (non-Javadoc)
       * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
       */
      public void masterChange(ModelChangeEvent event) {
        if (isShowTurn) {
          turnDb.fireMasterChange(event);
          turnKt.fireMasterChange(event);
          turn.fireMasterChange(event);
        }
      }
    });
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultHierarchyBrowseForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    isShowTurn = view.getUIProfile().getProperty(IS_SHOW_TURN_TABLES, false);

    turnDb.initController(operationService, new AbstractTurnTableModel() {

      /* (non-Javadoc)
       * @see com.mg.merp.account.support.ui.AbstractTurnTableModel#doGetWhereText(java.util.List)
       */
      @Override
      protected String doGetWhereText(List<String> paramsName) {
        return doGetTurnDbWhereText(paramsName);
      }
    });
    turnKt.initController(operationService, new AbstractTurnTableModel() {

      /* (non-Javadoc)
       * @see com.mg.merp.account.support.ui.AbstractTurnTableModel#doGetWhereText(java.util.List)
       */
      @Override
      protected String doGetWhereText(List<String> paramsName) {
        return doGetTurnKtWhereText(paramsName);
      }
    });
    turn.initController(operationService, new AbstractTurnTableModel() {

      /* (non-Javadoc)
       * @see com.mg.merp.account.support.ui.AbstractTurnTableModel#doGetWhereText(java.util.List)
       */
      @Override
      protected String doGetWhereText(List<String> paramsName) {
        return doGetTurnWhereText(paramsName);
      }
    });
    super.doOnRun();
    ((CheckBoxMenuItem) view.getWidget(TABLE_WIDGET).getPopupMenu().getMenuItem(VIEW_TURNS_MENUITEM)).setSelected(isShowTurn);
    setVisibleSpec(isShowTurn);
    adjustPopupMenuOfTurnTables();
  }

  /**
   * Получить where-часть текста запроса для выборки оборотов по дебету
   *
   * @param paramsName - список имен параметров
   * @return where-часть текста запроса для выборки оборотов по дебету
   */
  protected abstract String doGetTurnDbWhereText(List<String> paramsName);

  /**
   * Получить where-часть текста запроса для выборки оборотов по кредиту
   *
   * @param paramsName - список имен параметров
   * @return where-часть текста запроса для выборки оборотов по кредиту
   */
  protected abstract String doGetTurnKtWhereText(List<String> paramsName);

  /**
   * Получить where-часть текста запроса для выборки оборотов
   *
   * @param paramsName - список имен параметров
   * @return where-часть текста запроса для выборки оборотов
   */
  protected abstract String doGetTurnWhereText(List<String> paramsName);

  /**
   * Настроить КМ списков оборотов
   */
  private void adjustPopupMenuOfTurnTables() {
    doAdjustTurnDbTablePopupMenu(view.getWidget("turnDb").getPopupMenu());
    doAdjustTurnDbTablePopupMenu(view.getWidget("turnKt").getPopupMenu());
    doAdjustTurnDbTablePopupMenu(view.getWidget("turn").getPopupMenu());
  }

  /**
   * Настроить КМ списка оборотов по дебету
   *
   * @param popupMenu - КМ списка оборотов по дебету
   */
  protected void doAdjustTurnDbTablePopupMenu(PopupMenu popupMenu) {
    UIUtils.setVisibleEnabledProperty(popupMenu.getMenuItem(MaintenanceTable.ADD_MENU_ITEM), false);
  }

  /**
   * Настроить КМ списка оборотов по кредиту
   *
   * @param popupMenu - КМ списка оборотов по кредиту
   */
  protected void doAdjustTurnKtTablePopupMenu(PopupMenu popupMenu) {
    UIUtils.setVisibleEnabledProperty(popupMenu.getMenuItem(MaintenanceTable.ADD_MENU_ITEM), false);
  }

  /**
   * Настроить КМ списка оборотов
   *
   * @param popupMenu - КМ списка оборотов
   */
  protected void doAdjustTurnTablePopupMenu(PopupMenu popupMenu) {
    UIUtils.setVisibleEnabledProperty(popupMenu.getMenuItem(MaintenanceTable.ADD_MENU_ITEM), false);
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
    this.isShowTurn = isVisible;
    view.getUIProfile().setProperty(IS_SHOW_TURN_TABLES, isVisible);
    ((SplitPane) view.getWidget(SPLIT_CONTAINER_NAME)).setDividerLocation(isVisible ? 62 : 100);
  }

  /**
   * Обработчик пункта КМ "Показать обороты"
   *
   * @param event - событие
   */
  protected void onActionViewTurns(WidgetEvent event) {
    boolean selected = ((CheckBoxMenuItem) event.getWidget()).isSelected();
    if (isShowTurn != selected) {
      if (selected) {
        Serializable[] remnIds = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
        if (remnIds.length == 1) {
          Serializable remnId = remnIds[0];
          ((MaintenanceTableModel) turnDb.getModel()).setCurrentMaster(remnId);
          turnDb.refresh();

          ((MaintenanceTableModel) turnKt.getModel()).setCurrentMaster(remnId);
          turnKt.refresh();

          ((MaintenanceTableModel) turn.getModel()).setCurrentMaster(remnId);
          turn.refresh();
        }
      }
      isShowTurn = selected;
      setVisibleSpec(isShowTurn);
    }
  }

}