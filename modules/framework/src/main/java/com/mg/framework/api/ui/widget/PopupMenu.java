/*
 * PopupMenu.java
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

import com.mg.framework.api.ui.Widget;

/**
 * Элемент "Контекстное меню"
 *
 * @author Oleg V. Safonov
 * @version $Id: PopupMenu.java,v 1.2 2006/11/21 15:34:06 safonov Exp $
 */
public interface PopupMenu extends Widget {

  /**
   * получить элемент меню по наименованию
   *
   * @param id имя элемента меню
   * @return элемент меню или <code>null</code> если не найден
   */
  MenuItem getMenuItem(String id);

}
