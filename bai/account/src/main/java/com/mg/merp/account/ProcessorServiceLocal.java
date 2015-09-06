/*
 * ProcessorServiceLocal.java
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
package com.mg.merp.account;

/**
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: ProcessorServiceLocal.java,v 1.2 2008/03/13 06:18:18 alikaev Exp $
 */
public interface ProcessorServiceLocal extends com.mg.framework.api.BusinessObjectService, Processor {
  /**
   * Локальное имя сервиса
   */
  static final String LOCAL_SERVICE_NAME = "merp/account/Processor";

}
