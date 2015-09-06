/*
 * Dialog.java
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
import com.mg.framework.api.ui.Window;

/**
 * диалоговое окно (модальное), используется при UI диалогах
 *
 * @author Oleg V. Safonov
 * @version $Id: Dialog.java,v 1.4 2006/11/21 15:34:06 safonov Exp $
 */
public interface Dialog extends Widget, Window {

  /**
   * заголовок диалога по умолчанию
   */
  final static String DEFAULT_TITLE = "Dialog";

  /**
   * установка размеров окна
   *
   * @param width  ширина (в пикселах)
   * @param height высота (в пикселах)
   */
  void setSize(int width, int height);
}
