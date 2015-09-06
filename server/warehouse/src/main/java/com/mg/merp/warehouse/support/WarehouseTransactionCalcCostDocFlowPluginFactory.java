/* WarehouseTransactionCalcCostDocFlowPluginFactory.java
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
package com.mg.merp.warehouse.support;

import com.mg.merp.warehouse.generic.AbstractWarehouseTransactionDocFlowPluginFactory;

/**
 * Реализация фабрики реализаций этапа ДО "тработка по складу с вычислением цены списания"
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: WarehouseTransactionCalcCostDocFlowPluginFactory.java,v 1.2 2008/04/18 15:22:11
 *          safonov Exp $
 */
public class WarehouseTransactionCalcCostDocFlowPluginFactory extends
    AbstractWarehouseTransactionDocFlowPluginFactory {
  public final static int FACTORY_IDENTIFIER = 10002;

  /* (non-Javadoc)
   * @see com.mg.merp.warehouse.generic.AbstractWarehouseTransactionDocFlowPluginFactory#isCalculateCost()
   */
  @Override
  protected boolean isCalculateCost() {
    return true;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.warehouse.generic.AbstractWarehouseTransactionDocFlowPluginFactory#isInteractive()
   */
  @Override
  protected boolean isInteractive() {
    return false;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory#getIdentifier()
   */
  @Override
  public int getIdentifier() {
    return FACTORY_IDENTIFIER;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory#getName()
   */
  @Override
  public String getName() {
    return "Warehouse transaction with cost calculation";
  }

}
