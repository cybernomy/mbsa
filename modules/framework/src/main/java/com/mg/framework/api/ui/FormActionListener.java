/*
 * FormActionListener.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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

import java.io.Serializable;


/**
 * Слушатель событий формы пользовательского интерфейса
 *
 * @author Oleg V. Safonov
 * @version $Id: FormActionListener.java,v 1.2 2006/08/31 09:01:06 safonov Exp $
 */
public interface FormActionListener extends Serializable {

  /**
   * вызывается в процессе возникновения события от формы
   *
   * @param event событие формы
   */
  void actionPerformed(FormEvent event);
}
