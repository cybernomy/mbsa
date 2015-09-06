/**
 * TableCellRenderParameters.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.framework.api.ui.widget;

import com.mg.framework.api.ui.Color;
import com.mg.framework.api.ui.Font;
import com.mg.framework.api.ui.HorizontalAlignment;
import com.mg.framework.api.ui.Icon;
import com.mg.framework.api.ui.VerticalAlignment;

/**
 * Параметры отрисовки ячейки таблицы
 *
 * @author Oleg V. Safonov
 * @version $Id: TableCellRenderParameters.java,v 1.1 2008/07/24 15:16:05 safonov Exp $
 */
public interface TableCellRenderParameters {
  /**
   * установить цвет фона
   *
   * @param color цвет фона
   */
  void setBackground(Color color);

  /**
   * установить цвет шрифта
   *
   * @param color цвет шрифта
   */
  void setForeground(Color color);

  /**
   * установить признак прозрачности
   *
   * @param opaque если <code>true</code> то не прозрачный
   */
  void setOpaque(boolean opaque);

  /**
   * установить шрифт
   *
   * @param font шрифт
   */
  void setFont(Font font);

  /**
   * установить признак отображения текста
   *
   * @param textVisible если <code>true</code> то текст отображается
   */
  void setTextVisible(boolean textVisible);

  /**
   * установить интервал между иконкой и текстом
   *
   * @param iconTextGap интервал в пикселах
   */
  void setIconTextGap(int iconTextGap);

  /**
   * установить горизонтальное выравнивание
   *
   * @param alignment горизонтальное выравнивание
   */
  void setHorizontalAlignment(HorizontalAlignment alignment);

  /**
   * установить вертикальное выравнивание
   *
   * @param alignment вертикальное выравнивание
   */
  void setVerticalAlignment(VerticalAlignment alignment);

  /**
   * установить иконку для отображения
   *
   * @param icon иконка
   */
  void setIcon(Icon icon);
}
