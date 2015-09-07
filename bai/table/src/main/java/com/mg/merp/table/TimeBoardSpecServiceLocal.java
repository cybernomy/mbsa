/*
 * TimeBoardSpecServiceLocal.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.table;

import com.mg.merp.table.model.TimeBoardSpec;

/**
 * Бизнес-компонент "Спецификация табеля"
 *
 * @author leonova
 * @version $Id: TimeBoardSpecServiceLocal.java,v 1.3 2008/08/12 14:08:13 sharapov Exp $
 */
public interface TimeBoardSpecServiceLocal extends com.mg.framework.api.DataBusinessObjectService<TimeBoardSpec, Integer> {

  /**
   * Имя сервиса
   */
  static final String SERVICE_NAME = "merp/table/TimeBoardSpec"; //$NON-NLS-1$

}
