/*
 * ContractStatus.java
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
package com.mg.merp.contract.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * Состояние контракта
 *
 * @author leonova
 * @version $Id: ContractStatus.java,v 1.1 2006/03/30 11:23:46 safonov Exp $
 */
@DataItemName("Contract.ContractStatus")
public enum ContractStatus {
  /**
   * Зарегистрирован
   */
  @EnumConstantText("resource://com.mg.merp.contract.resources.dataitemlabels#Contract.ContractStatus.Registered")
  REGISTRED,

  /**
   * В работе
   */
  @EnumConstantText("resource://com.mg.merp.contract.resources.dataitemlabels#Contract.ContractStatus.InWork")
  INWORK,

  /**
   * Выполнен
   */
  @EnumConstantText("resource://com.mg.merp.contract.resources.dataitemlabels#Contract.ContractStatus.Complete")
  COMPLETE
}