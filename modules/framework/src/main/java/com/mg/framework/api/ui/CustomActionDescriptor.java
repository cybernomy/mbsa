/**
 * CustomActionDescriptor.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.framework.api.ui;

/**
 * Настраиваемое действие, используется для создания точек вызова дополнительной функциональности в
 * стандартных формах и элементах пользовательского интерфейса
 *
 * @author Oleg V. Safonov
 * @version $Id: CustomActionDescriptor.java,v 1.1 2007/11/15 08:35:11 safonov Exp $
 */
public interface CustomActionDescriptor {

  /**
   * имя действия
   *
   * @return имя
   */
  String getName();

  /**
   * заголовок
   *
   * @return заголовок
   */
  String getCaption();

  /**
   * подсказка
   *
   * @return подсказка
   */
  String getToolTip();

  /**
   * пиктограмма
   *
   * @return имя ресурса содержащего пиктограмму
   */
  String getIcon();

  /**
   * комбинация клавиш быстрого вызова
   */
  String getKeyStroke();

  /**
   * признак использования в контекстном меню
   *
   * @return <code>true</code> если использовать в контекстном меню
   */
  boolean isPopupMenu();

  /**
   * признак использования в панели инструментов
   *
   * @return <code>true</code> если использовать в панели инструментов
   */
  boolean isToolBar();

  /**
   * признак обновления содержимого элемента пользовательского интерфейса из которого было вызвано
   * действие
   *
   * @return <code>true</code> если необходимо обновить содержимое
   */
  boolean isForceRefresh();

  /**
   * признак создания разделителя перед элементом вызова действия
   *
   * @return <code>true</code> если необходим разделитель
   */
  boolean isSeparatorBefore();

  /**
   * признак создания разделителя после элементом вызова действия
   *
   * @return <code>true</code> если необходим разделитель
   */
  boolean isSeparatorAfter();

}
