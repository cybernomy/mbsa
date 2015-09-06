/*
 * SplitPane.java
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

import com.mg.framework.api.ui.Container;

/**
 * Элемент "Панель разделитель"
 *
 * @author Oleg V. Safonov
 * @version $Id: SplitPane.java,v 1.4 2008/06/06 13:28:02 safonov Exp $
 */
public interface SplitPane extends Container {
  /**
   * атрибут ориентации, может иметь значения "hor" или "ver"
   */
  final static String ORIENTATION = "orientation"; //$NON-NLS-1$

  /**
   * вертикальная ориентация
   */
  final static String VERTICAL_ORIENTATION = "ver"; //$NON-NLS-1$

  /**
   * горизонтальная ориентация
   */
  final static String HORIZONTAL_ORIENTATION = "hor"; //$NON-NLS-1$

  /**
   * атрибут положения делителя, задается в процентах, указывает сколько займет места левый (или
   * верхний) элемент отделенный данным сплитером
   */
  final static String DIVIDER_LOCATION = "dividerLocation"; //$NON-NLS-1$

  /**
   * атрибут установки быстрого расширения/сужения, тип boolean
   */
  final static String ONE_TOUCH_EXPANDABLE = "oneTouchExpandable";

  /**
   * атрибут установки размера сплитера, задается в пикселах
   */
  final static String DIVIDER_SIZE = "dividerSize";

  /**
   * получить положение делителя
   *
   * @return положение делителя
   * @see #DIVIDER_LOCATION
   */
  double getDividerLocation();

  /**
   * установка положения делителя, задается в процентах
   *
   * @param dividerLocation положение делителя
   * @see #DIVIDER_LOCATION
   */
  void setDividerLocation(double dividerLocation);

}
