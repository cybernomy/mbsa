/*
 * AnlPlanAccKtSearchHelp.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.account.support.ui;

/**
 * Базовый класс для SearchHelp аналитических счетов по кредиту
 *
 * @author leonova
 * @version $Id: AnlPlanAccKtSearchHelp.java,v 1.1 2006/10/04 06:20:19 leonova Exp $
 */
public abstract class AnlPlanAccKtSearchHelp extends AnlPlanSearchHelp {

  /* (non-Javadoc)
   * @see com.mg.merp.account.support.ui.AnlPlanSearchHelp#getAccPlanContextName()
   */
  @Override
  protected String getAccPlanContextName() {
    return "AccKt";
  }


}
