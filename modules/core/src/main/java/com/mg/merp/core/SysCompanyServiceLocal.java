/**
 * SysCompanyServiceLocal.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.merp.core;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.merp.core.model.SysCompany;

/**
 * Бизнес-компонент "Балансовая единица"
 *
 * @author Oleg V. Safonov
 * @version $Id: SysCompanyServiceLocal.java,v 1.1 2007/09/20 15:04:48 safonov Exp $
 */
public interface SysCompanyServiceLocal extends DataBusinessObjectService<SysCompany, Integer> {
  /**
   * Имя сервиса
   */
  final static String SERVICE_NAME = "merp/core/SysCompany";
}
