/*
 * WidgetFactory.java
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
package com.mg.framework.api.ui;

import org.dom4j.Element;

/**
 * Widget factory
 *
 * @author Oleg V. Safonov
 * @version $Id: WidgetFactory.java,v 1.12 2008/07/28 13:36:57 safonov Exp $
 */
public interface WidgetFactory {

  /**
   * элемент текстовая метка
   *
   * @see com.mg.framework.api.ui.widget.Label
   */
  final static String LABEL_WIDGET = "jfd:label"; //$NON-NLS-1$

  /**
   * элемент панель прокрутки
   *
   * @see com.mg.framework.api.ui.widget.ScrollPane
   */
  final static String SCROLL_PANE = "jfd:scroll"; //$NON-NLS-1$

  /**
   * элемент редактор целых чисел
   *
   * @see com.mg.framework.api.ui.widget.IntegerField
   */
  final static String INTEGER_EDIT_WIDGET = "jfd:integerField"; //$NON-NLS-1$

  /**
   * элемент флаг
   *
   * @see com.mg.framework.api.ui.widget.CheckBox
   */
  final static String CHECKBOX_WIDGET = "jfd:checkBox"; //$NON-NLS-1$

  /**
   * элемент редактор однострочного текста
   *
   * @see com.mg.framework.api.ui.widget.TextField
   */
  final static String TEXT_EDIT_WIDGET = "jfd:textField"; //$NON-NLS-1$

  /**
   * элемент редактор многострочного текста
   *
   * @see com.mg.framework.api.ui.widget.TextAreaField
   */
  final static String TEXT_AREA_EDIT_WIDGET = "jfd:textAreaField"; //$NON-NLS-1$

  /**
   * элемент редактор дат
   *
   * @see com.mg.framework.api.ui.widget.DateField
   */
  final static String DATE_EDIT_WIDGET = "jfd:dateField"; //$NON-NLS-1$

  /**
   * элемент редактор даты/времени
   *
   * @see com.mg.framework.api.ui.widget.DateTimeField
   */
  final static String DATE_TIME_EDIT_WIDGET = "jfd:dateTimeField"; //$NON-NLS-1$

  /**
   * элемент редактор времени
   *
   * @see com.mg.framework.api.ui.widget.TimeField
   */
  final static String TIME_EDIT_WIDGET = "jfd:timeField"; //$NON-NLS-1$

  /**
   * элемент редактор дробных чисел
   *
   * @see com.mg.framework.api.ui.widget.NumberField
   */
  final static String NUMBER_EDIT_WIDGET = "jfd:numberField"; //$NON-NLS-1$

  /**
   * элемент редактор денежных величин
   *
   * @see com.mg.framework.api.ui.widget.MonetaryAmountField
   */
  final static String MONETARY_AMOUNT_EDIT_WIDGET = "jfd:monetaryAmountField"; //$NON-NLS-1$

  /**
   * элемент редактор перечислимых типов
   *
   * @see com.mg.framework.api.ui.widget.EnumField
   */
  final static String ENUM_EDIT_WIDGET = "jfd:enumField"; //$NON-NLS-1$

  /**
   * элемент редактор перечислимых типов в виде раскрывающегося списка
   *
   * @see com.mg.framework.api.ui.widget.EnumComboBoxField
   */
  final static String ENUM_COMBOBOX_EDIT_WIDGET = "jfd:enumComboBoxField"; //$NON-NLS-1$

  /**
   * элемент редактор перечислимых типов в виде кнопок переключателей
   *
   * @see com.mg.framework.api.ui.widget.EnumRadioButtonField
   */
  final static String ENUM_RADIOBUTTON_EDIT_WIDGET = "jfd:enumRadioButtonField"; //$NON-NLS-1$

  /**
   * элемент редактор сущностей
   *
   * @see com.mg.framework.api.ui.widget.EntityField
   */
  final static String ENTITY_EDIT_WIDGET = "jfd:entityField"; //$NON-NLS-1$

  /**
   * элемент раскрывающейся список
   *
   * @see com.mg.framework.api.ui.widget.ComboBox
   */
  final static String COMBOBOX_WIDGET = "jfd:comboBox"; //$NON-NLS-1$

  /**
   * элемент кнопка
   *
   * @see com.mg.framework.api.ui.widget.Button
   */
  final static String BUTTON_WIDGET = "jfd:button"; //$NON-NLS-1$

  /**
   * элемент таблица с функциями поддержки бизнес-компонентов
   *
   * @see com.mg.framework.api.ui.widget.MaintenanceTable
   */
  final static String MT_TABLE_WIDGET = "jfd:maintenanceTable"; //$NON-NLS-1$

  /**
   * элемент дерево с функциями поддержки бизнес-компонентов
   *
   * @see com.mg.framework.api.ui.widget.MaintenanceTree
   */
  final static String MT_TREE_WIDGET = "jfd:maintenanceTree"; //$NON-NLS-1$

  /**
   * элемент таблица
   *
   * @see com.mg.framework.api.ui.widget.Table
   */
  final static String TABLE_WIDGET = "jfd:table"; //$NON-NLS-1$

  /**
   * элемент заполнитель
   *
   * @see com.mg.framework.api.ui.widget.Filler
   */
  final static String FILLER_WIDGET = "jfd:filler"; //$NON-NLS-1$

  /**
   * элемент блочное расположение
   *
   * @see com.mg.framework.api.ui.widget.BoxPane
   */
  final static String BOX_LAYOUT = "jfd:box"; //$NON-NLS-1$

  /**
   * элемент стандартное расположение на форме поддержки бизнес-компонента
   *
   * @deprecated use {@link #DEFAULT_MT_PANE_LAYOUT} instead
   */
  @Deprecated
  final static String DEFAULT_MT_GRID_BAG_LAYOUT = "jfd:defaultMtGridBag"; //$NON-NLS-1$

  /**
   * элемент стандартное расположение на форме поддержки бизнес-компонента
   *
   * @see com.mg.framework.api.ui.widget.DefaultMaintenancePane
   */
  final static String DEFAULT_MT_PANE_LAYOUT = "jfd:defaultMtLayout"; //$NON-NLS-1$

  /**
   * элемент разделяемая панель
   *
   * @see com.mg.framework.api.ui.widget.SplitPane
   */
  final static String SPLIT_PANE = "jfd:split"; //$NON-NLS-1$

  /**
   * элемент панель с вкладками
   *
   * @see com.mg.framework.api.ui.widget.TabbedPane
   */
  final static String TABBED_PANE = "jfd:tabbed"; //$NON-NLS-1$

  /**
   * элемент панель инструментов
   *
   * @see com.mg.framework.api.ui.widget.ToolBar
   */
  final static String TOOLBAR_WIDGET = "jfd:toolBar"; //$NON-NLS-1$

  /**
   * окно диалог
   *
   * @see com.mg.framework.api.ui.widget.Dialog
   */
  final static String DIALOG_WINDOW = "jfd:dialog"; //$NON-NLS-1$

  /**
   * окно внутренний фрейм, для MDI интерфейса
   *
   * @see com.mg.framework.api.ui.widget.InternalFrame
   */
  final static String INTERNAL_FRAME_WINDOW = "jfd:internalFrame"; //$NON-NLS-1$

  /**
   * элемент граф
   *
   * @see com.mg.framework.api.ui.widget.Graph
   */
  final static String GRAPH_WIDGET = "jfd:graph"; //$NON-NLS-1$

  /**
   * элемент Shuttle
   *
   * @see com.mg.framework.api.ui.widget.Shuttle
   */
  final static String SHUTTLE_WIDGET = "jfd:shuttle"; //$NON-NLS-1$

  /**
   * элемент список
   *
   * @see com.mg.framework.api.ui.widget.List
   */
  final static String LIST_WIDGET = "jfd:list"; //$NON-NLS-1$

  /**
   * элемент группа кнопок переключателей
   *
   * @see com.mg.framework.api.ui.widget.RadioButtonGroup
   */
  final static String RADIOBUTTON_GROUP_WIDGET = "jfd:radioButtonGroup"; //$NON-NLS-1$

  /**
   * элемент редактор секретного текста
   *
   * @see com.mg.framework.api.ui.widget.TextField
   */
  final static String SECRET_EDIT_WIDGET = "jfd:secretField"; //$NON-NLS-1$

  /**
   * элемент изображение
   *
   * @see com.mg.framework.api.ui.widget.Image
   */
  final static String IMAGE_WIDGET = "jfd:image"; //$NON-NLS-1$

  /**
   * контекстное меню
   *
   * @see com.mg.framework.api.ui.widget.PopupMenu
   */
  final static String POPUP_MENU = "jfd:popupMenu"; //$NON-NLS-1$

  /**
   * элемент разделитель меню и панели инструментов
   *
   * @see com.mg.framework.api.ui.widget.Separator
   */
  final static String SEPARATOR_WIDGET = "jfd:separator"; //$NON-NLS-1$

  /**
   * всплывающее меню
   *
   * @see com.mg.framework.api.ui.widget.ComboMenuBar
   */
  final static String COMBO_MENU_BAR = "jfd:comboMenuBar"; //$NON-NLS-1$

  /**
   * элемент редактор количественных величин
   *
   * @see com.mg.framework.api.ui.widget.QuantityField
   */
  final static String QUANTITY_EDIT_WIDGET = "jfd:quantityField"; //$NON-NLS-1$

  /**
   * Create widget by type
   *
   * @param type    type
   * @param name    name
   * @param element descriptor tag
   * @param view    view
   * @return created widget
   * @throws ApplicationException if any error appear
   */
  Widget createWidget(String type, String name, Element element, View view);

  /**
   * Create alert dialog
   *
   * @return alert dialog
   */
  Alert createAlert();

  /**
   * Create exception dialog
   *
   * @return exception dialog
   */
  ShowExceptionDialog createExceptionDialog();

}
