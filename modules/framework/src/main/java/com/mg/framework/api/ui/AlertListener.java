/*
 * AlertListener.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.framework.api.ui;

import java.util.EventListener;

/**
 * Alert listener, used for receives alert events
 *
 * @author Oleg V. Safonov
 * @version $Id: AlertListener.java,v 1.1 2006/06/27 12:05:56 safonov Exp $
 */
public interface AlertListener extends EventListener {

  /**
   * Constant for window closing with out choosing anything
   */
  static final String WINDOW_CLOSING = "windowClosing"; //$NON-NLS-1$

  /**
   * Invoked when the user attempts to close the alert
   *
   * @param value the value the user has selected, {@link #WINDOW_CLOSING} means the user closed the
   *              alert with out choosing anything
   */
  void alertClosing(String value);

}
