/*
 * ToolBar.java
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
 * Toolbar widget
 *
 * @author Oleg V. Safonov
 * @version $Id: ToolBar.java,v 1.2 2006/11/21 15:34:06 safonov Exp $
 */
public interface ToolBar extends Container {

  /**
   * Атрибут признака "всплывающая панель", имеет тип <code>boolean</code>
   */
  final static String FLOATABLE = "floatable";
}
