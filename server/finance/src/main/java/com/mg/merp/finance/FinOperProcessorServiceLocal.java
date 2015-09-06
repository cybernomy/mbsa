/*
 * FinOperProcessorServiceLocal.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.finance;

import com.mg.framework.api.BusinessObjectService;

/**
 * @author Konstantin S. Alikaev
 * @version $Id: FinOperProcessorServiceLocal.java,v 1.1 2007/11/30 12:55:41 alikaev Exp $
 */
public interface FinOperProcessorServiceLocal extends BusinessObjectService, FinOperProcessor {

  /**
   * Локальное имя сервиса
   */
  static final String LOCAL_SERVICE_NAME = "merp/finance/FinOperProcessor";

}
