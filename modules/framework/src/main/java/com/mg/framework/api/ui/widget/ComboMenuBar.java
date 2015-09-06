/**
 * ComboMenuBar.java
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

import com.mg.framework.api.ui.Widget;

/**
 * Элемент "Всплывающее меню"
 *
 * @author Oleg V. Safonov
 * @version $Id: ComboMenuBar.java,v 1.1 2008/05/19 14:30:35 safonov Exp $
 */
public interface ComboMenuBar extends Widget {

  /**
   * получить элемент меню по наименованию
   *
   * @param id имя элемента меню
   * @return элемент меню или <code>null</code> если не найден
   */
  MenuItem getMenuItem(String id);

  /**
   * установка заголовка
   *
   * @param text заголовок
   */
  void setText(String text);

}
