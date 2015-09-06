/* StandartBrowserController.java
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
package com.mg.merp.wb.core.ui.view;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TableItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Базовый класс для создания браузеров БК.Контроллер
 *
 * V - Класс управляемого вида.<br> I - Класс объектов наполнения таблицы
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: StandartBrowserViewController.java,v 1.1 2006/12/29 15:16:33 poroxnenko Exp $
 */
public abstract class StandartBrowserViewController<V extends StandartBrowserView, I>
    implements ITableLabelProvider, IStructuredContentProvider {

  /**
   * флаг наличия синхронизации панели и сервера приложений. Если синхронизация отсутствует, то
   * никакие действия не доступны с объектами
   */
  protected boolean isSynchronized = false;

  /**
   * Вид, управляемый данным контроллером
   */
  protected V view;

  public StandartBrowserViewController(V view) {
    this.view = view;
    this.isSynchronized = false;
  }

  public boolean isSynchronized() {
    return isSynchronized;
  }

  @SuppressWarnings("unchecked")
  public I getCurrentSelectedItem() {
    TableItem[] ti = view.tblItems.getSelection();
    if (ti != null && ti.length >= 0)
      return (I) (view.tblItems.getSelection()[0].getData());
    else
      return null;
  }

  @SuppressWarnings("unchecked")
  List<I> getSelectedItems() {
    TableItem[] tia = view.tblItems.getSelection();
    if (tia != null && tia.length >= 0) {
      ArrayList<I> objs = new ArrayList<I>(tia.length);
      for (TableItem ti : tia)
        objs.add((I) ti.getData());
      return objs;
    } else
      return null;
  }

  public abstract void refreshView(String query);

  public abstract Image getColumnImage(Object element, int columnIndex);

  public abstract String getColumnText(Object element, int columnIndex);

  public abstract void addListener(ILabelProviderListener listener);

  public abstract void dispose();

  public abstract boolean isLabelProperty(Object element, String property);

  public abstract void removeListener(ILabelProviderListener listener);

  public abstract Object[] getElements(Object inputElement);

  public abstract void abstractinputChanged(Viewer viewer, Object oldInput,
                                            Object newInput);

  public abstract void storeFilter(String[] array);

  public abstract String[] loadFilter();

  /**
   * Обработчик события выбора пункта меню "Добавить"
   */
  public abstract void doOnAddMenuAction();

  /**
   * Обработчик события выбора пункта меню "Изменить"
   *
   * @param data содержание БК
   */
  public abstract void doOnEditMenuAction(I data);

  /**
   * Обработчик события выбора пункта меню "Удалить"
   *
   * @param objects список БК, подлежащих удалению
   */
  public abstract void doOnDelMenuAction(List<I> objects);
}
