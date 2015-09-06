/*
 * TabbedPane.java
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
import com.mg.framework.api.ui.Widget;

/**
 * Элемент "Панель с вкладками"
 *
 * @author Oleg V. Safonov
 * @version $Id: TabbedPane.java,v 1.4 2007/11/15 16:39:43 safonov Exp $
 */
public interface TabbedPane extends Container {

  /**
   * атрибут размещения закладок, может иметь следующие значения: </br>top </br>left </br>bottom
   * </br>right
   *
   * @see Defaults
   */
  final static String PLACEMENT = "tabPlacement"; //$NON-NLS-1$

  /**
   * атрибут заголовка закладки
   */
  final static String TITLE = "tabTitle"; //$NON-NLS-1$

  /**
   * Returns the currently selected component for this tabbed pane or <code>null</code> if no tab is
   * selected.
   *
   * @return the component corresponding to the selected tab or <code>null</code> if no tab is
   * selected
   */
  Widget getSelectedComponent();

  /**
   * Sets the selected component for this tabbed pane. The selected index will automatically be set
   * to the index corresponding to the specified component.
   *
   * @param widget the component to be selected
   */
  void setSelectedComponent(Widget widget);

}
