/*
 * ShuttleListener.java
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
package com.mg.framework.support.ui.widget;

import com.mg.framework.api.ui.ShuttleChangeEvent;

import java.io.Serializable;
import java.util.EventListener;

/**
 * Слушатель событий элемента "Shuttle", предназначен для создания обработчиков на события
 * перемещения элементов между списками
 *
 * @author Oleg V. Safonov
 * @version $Id: ShuttleListener.java,v 1.1 2006/08/31 08:36:41 safonov Exp $
 */
public interface ShuttleListener extends EventListener, Serializable {

  /**
   * содержимое перенесено из списка источника в список приемник
   *
   * @param event событие
   */
  void shuttleContentsMoved(ShuttleChangeEvent event);

  /**
   * содержимое перенесено из списка источника в список приемника
   *
   * @param event событие
   */
  void shuttleContentsRemoved(ShuttleChangeEvent event);

}
