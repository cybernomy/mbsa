/*
 * ContractTypeServiceLocal.java
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
package com.mg.merp.contract;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.merp.contract.model.ContractType;

/**
 * Сервис бизнес-компонента "Вид договора"
 *
 * @author Konstantin S. Alikaev
 * @version $Id: ContractTypeServiceLocal.java,v 1.1 2007/09/17 12:14:48 alikaev Exp $
 */
public interface ContractTypeServiceLocal extends DataBusinessObjectService<ContractType, Integer> {

  /**
   * Локальное имя сервиса
   */
  static final String LOCAL_SERVICE_NAME = "merp/contract/ContractType";

}
