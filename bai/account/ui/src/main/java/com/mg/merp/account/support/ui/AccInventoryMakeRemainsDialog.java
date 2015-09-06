/*
 * AccInventoryMakeRemainsDialog.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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

import com.mg.framework.generic.ui.DefaultWizardDialog;
import com.mg.merp.account.model.Period;

/**
 * Контроллер формы ввода параметров для операции формирование остатков инвентарных карточек
 *
 * @author Konstantin S. Alikaev
 * @version $Id: AccInventoryMakeRemainsDialog.java,v 1.1 2008/04/29 05:29:04 alikaev Exp $
 */
public class AccInventoryMakeRemainsDialog extends DefaultWizardDialog {

  /**
   * период бугалтерского учета
   */
  private Period period;

  public AccInventoryMakeRemainsDialog() {
  }

  public Period getPeriod() {
    return period;
  }

}
