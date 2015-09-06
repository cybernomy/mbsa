/*
 * JobMachineServiceLocal.java
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
package com.mg.merp.manufacture;

import com.mg.merp.manufacture.model.JobMachine;

/**
 * Бизнес-компонент "Оборудование ЗНП"
 *
 * @author Oleg V. Safonov
 * @version $Id: JobMachineServiceLocal.java,v 1.3 2007/07/30 10:28:17 safonov Exp $
 */
public interface JobMachineServiceLocal
    extends com.mg.framework.api.DataBusinessObjectService<JobMachine, Integer> {
  /**
   * имя сервиса
   */
  static final String SERVICE_NAME = "merp/manufacture/JobMachine";
}
