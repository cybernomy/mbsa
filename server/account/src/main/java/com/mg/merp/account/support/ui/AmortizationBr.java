/*
 * AmortizationBr.java
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

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.ui.ConversationBeginner;
import com.mg.framework.api.ui.DialogForm;
import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.MaintenanceTable;
import com.mg.framework.api.ui.widget.PopupMenu;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.account.model.Amortization;
import com.mg.merp.account.model.Inventory;
import com.mg.merp.account.support.Messages;
import com.mg.merp.reference.model.Catalog;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы списка бизнес-компонента "Ведомость начисления амортизации"
 *
 * @author Konstantin S. Alikaev
 * @version $Id: AmortizationBr.java,v 1.3 2008/05/20 11:49:20 alikaev Exp $
 */
public class AmortizationBr extends DefaultPlainBrowseForm implements ConversationBeginner, DialogForm {

  private final String INIT_QUERY_TEXT = "select %s from Amortization a %s %s "; //$NON-NLS-1$
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();

  private Integer batch;

  private String accKindName = StringUtils.EMPTY_STRING;

  private boolean okAction = false;

  /**
   * список слушателей на событие "Продолжить"
   */
  private List<FormActionListener> okActionListener = new ArrayList<FormActionListener>();
  /**
   * список слушателей на событие "Отменить"
   */
  private List<FormActionListener> cancelActionListener = new ArrayList<FormActionListener>();

  /**
   * отправка события о событии "Продолжить"
   *
   * @param event событие
   */
  public void fireOkAction(FormEvent event) throws ApplicationException {
    for (FormActionListener listener : okActionListener)
      listener.actionPerformed(event);
  }

  /**
   * отправка события о событии "Отменить"
   *
   * @param event событие
   */
  public void fireCancelAction(FormEvent event) throws ApplicationException {
    for (FormActionListener listener : cancelActionListener)
      listener.actionPerformed(event);
  }

  public void addCancelActionListener(FormActionListener listener) {
    cancelActionListener.add(listener);
  }

  public void addOkActionListener(FormActionListener listener) {
    okActionListener.add(listener);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    setTitle(StringUtils.format(Messages.getInstance().getMessage(Messages.AMORTIZATION_BR_TITLE), accKindName));
    super.doOnRun();
    PopupMenu popupMenu = view.getWidget(TABLE_WIDGET).getPopupMenu();
    popupMenu.getMenuItem(MaintenanceTable.ADD_MENU_ITEM).setReadOnly(true);
    popupMenu.getMenuItem(MaintenanceTable.ERASE_MENU_ITEM).setReadOnly(true);
    popupMenu.getMenuItem(MaintenanceTable.CLONE_MENU_ITEM).setReadOnly(true);
    popupMenu.getMenuItem(MaintenanceTable.DEEP_CLONE_MENU_ITEM).setReadOnly(true);
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
        result.add(new TableEJBQLFieldDef(Amortization.class, "Id", "a.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Inventory.class, "GroupNum", "i.GroupNum", "left join a.Inventory as i", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Inventory.class, "CardNum", "i.CardNum", false));
        result.add(new TableEJBQLFieldDef(Inventory.class, "ObjNum", "i.ObjNum", false));
        result.add(new TableEJBQLFieldDef(Catalog.class, "Code", "cat.Code", "left join i.Catalog as cat", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Catalog.class, "FullName", "cat.FullName", false));
        result.add(new TableEJBQLFieldDef(Catalog.class, "Measure1", "cat.Measure1", false));
        result.add(new TableEJBQLFieldDef(Inventory.class, "Contractor", "i.Contractor", false));
        result.add(new TableEJBQLFieldDef(Inventory.class, "AmCode", "ac.Code", "left join i.AmCode as ac", false));
        result.add(new TableEJBQLFieldDef(Amortization.class, "AmRate", "a.AmRate", false));
        result.add(new TableEJBQLFieldDef(Amortization.class, "SumRate", "a.SumRate", false));
        result.add(new TableEJBQLFieldDef(Amortization.class, "ExplPeriodY", "a.ExplPeriodY", false));
        result.add(new TableEJBQLFieldDef(Amortization.class, "ExplPeriodM", "a.ExplPeriodM", false));
        result.add(new TableEJBQLFieldDef(Amortization.class, "SumPeriod", "a.SumPeriod", false));
        result.add(new TableEJBQLFieldDef(Amortization.class, "ProductEst", "a.ProductEst", false));
        result.add(new TableEJBQLFieldDef(Amortization.class, "ProductFact", "a.ProductFact", false));
        result.add(new TableEJBQLFieldDef(Amortization.class, "SumProduct", "a.SumProduct", false));
        result.add(new TableEJBQLFieldDef(Amortization.class, "Factor", "a.Factor", false));
        result.add(new TableEJBQLFieldDef(Amortization.class, "SumAdd", "a.SumAdd", false));
        result.add(new TableEJBQLFieldDef(Amortization.class, "DeprValue", "a.DeprValue", false));
        result.add(new TableEJBQLFieldDef(Amortization.class, "SumDeprValue", "a.SumDeprValue", false));
        result.add(new TableEJBQLFieldDef(Amortization.class, "SumTotal", "a.SumTotal", false));
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

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
   */
  @Override
  protected String createQueryText() {
    StringBuilder whereText = new StringBuilder(" where (0 = 0) ");
    Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
    String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
    paramsName.clear();
    paramsValue.clear();
    if (batch != null) {
      paramsName.add("batch");
      paramsValue.add(batch);
      whereText.append(" and (a.Batch = :batch) ");
    }
    return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);
  }

  /**
   * Номер партии проведения массового начисления амортизации
   *
   * @param batch задаваемое batch
   */
  public void setBatch(Integer batch) {
    this.batch = batch;
  }

  /**
   * Вид учета основных средств и норм амортизации
   *
   * @param accKindName задаваемое accKindName
   */
  public void setAccKindName(String accKindName) {
    this.accKindName = accKindName;
  }

  /**
   * Обработчик кнопки контекстного меню "Продолжить"
   */
  public void onActionCommit(WidgetEvent event) throws Exception {
    fireOkAction(new FormEvent(this));
    close();
  }

  /**
   * Обработчик кнопки контекстного меню "Удаление ведомостей начисления амортизации"
   */
  public void onActionRollback(WidgetEvent event) throws ApplicationException {
    fireCancelAction(new FormEvent(this));
    close();
  }

  public void execute() {
    run(UIUtils.isModalMode());
  }

  public FormActionListener[] getCancelActionListenerList() {
    return cancelActionListener.toArray(new FormActionListener[0]);
  }

  public FormActionListener[] getOkActionListenerList() {
    return okActionListener.toArray(new FormActionListener[0]);
  }

  public void removeCancelActionListener(FormActionListener listener) {
    okActionListener.remove(listener);
  }

  public void removeOkActionListener(FormActionListener listener) {
    cancelActionListener.remove(listener);
  }

  /**
   * Возвращает признак нажатия кнопки продолжить <code>true</code> - нажата кнопка продолжить
   */
  public boolean isOkAction() {
    return okAction;
  }

  /**
   * Устанавливает признак нажати кнопки
   */
  public void setOkAction(boolean okAction) {
    this.okAction = okAction;
  }

}
