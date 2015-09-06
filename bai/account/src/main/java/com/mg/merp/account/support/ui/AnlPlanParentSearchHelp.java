/*
 * AnlPlanParentSearchHelp.java
 *
 * Copyright (c) 1998 - 2009 BusinessTechnology, Ltd.
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

import com.mg.merp.account.model.AnlPlan;

/**
 * @author leonova
 * @version $Id: AnlPlanParentSearchHelp.java,v 1.2 2009/02/25 15:09:59 safonov Exp $
 */
public class AnlPlanParentSearchHelp extends AnlPlanSearchHelp {
  private static final String ENTITY_FIELD_NAME = "entity";
  private short anlLevel = 0;

  @Override
  protected String[] defineImportContext() {
    return new String[]{ENTITY_FIELD_NAME};
  }

  /* (non-Javadoc)
   * @see com.mg.merp.account.support.ui.AnlPlanSearchHelp#getAccPlanContextName()
   */
  @Override
  protected String getAccPlanContextName() {
    AnlPlan anlPlan = (AnlPlan) getImportContextValue(ENTITY_FIELD_NAME);
    anlLevel = anlPlan.getAnlLevel();
    anlLevel--;
    setAccPlan(anlPlan.getAccPlan());
    return null;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.account.support.ui.AnlPlanSearchHelp#getAnalitikaLevel()
   */
  @Override
  protected short getAnalitikaLevel() {
    return anlLevel;
  }

}
