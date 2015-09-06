/*
 * RadioButtonGroup.java
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
package com.mg.framework.api.ui.widget;

import com.mg.framework.api.ui.FieldEditor;

/**
 * Элемент "Группа кнопок переключателей"
 *
 * @author Oleg V. Safonov
 * @version $Id: RadioButtonGroup.java,v 1.1 2006/12/02 11:51:33 safonov Exp $
 */
public interface RadioButtonGroup extends FieldEditor {

  /**
   * Атрибут значения индекса списка
   */
  final static String SELECTED_INDEX = "selectedIndex";

  /**
   * Тэг содержащий список элементов
   */
  final static String ITEMS = "jfd:items";

  /**
   * Тэг описывающий элемент, используется внутри тэга <code>ITEMS</code>
   */
  final static String ITEM = "jfd:item";

  /**
   * Атрибут значения элемента, используется внутри тэга <code>ITEM</code>
   */
  final static String ITEM_VALUE = "value";

  /**
   * установить список значений, количество кнопок переключателей будет соответствовать количеству
   * элементов в списке
   *
   * @param items список значений
   */
  void setItems(Object[] items);

}
