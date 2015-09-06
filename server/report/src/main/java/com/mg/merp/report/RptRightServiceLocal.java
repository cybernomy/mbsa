/*
 * RptRightServiceLocal.java
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
package com.mg.merp.report;

import com.mg.merp.report.model.RptRight;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: RptRightServiceLocal.java,v 1.1 2007/04/11 06:46:06 poroxnenko Exp $
 */
public interface RptRightServiceLocal extends
    com.mg.framework.api.DataBusinessObjectService<RptRight, Integer> {
  /**
   * Локальное имя сервиса
   */
  static final String LOCAL_SERVICE_NAME = "merp/report/RptRight";
}
