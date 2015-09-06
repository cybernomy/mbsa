/*
 * MetalMt.java
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
package com.mg.merp.account.support.ui;

import com.mg.framework.generic.ui.DefaultMaintenanceForm;

/**
 * Контроллер формы списка бизнес-компонента "Драгоценные металлы"
 *
 * @author Konstantin S. Alikaev
 * @version $Id: MetalMt.java,v 1.1 2008/02/19 08:57:34 alikaev Exp $
 */
public class MetalMt extends DefaultMaintenanceForm {

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnAdd()
   */
  @Override
  protected void doOnAdd() {
    setReadOnlyCode(false);
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnEdit()
   */
  @Override
  protected void doOnEdit() {
    setReadOnlyCode(true);
  }

  /**
   * Для поля код устанавливаем его доступность
   */
  private void setReadOnlyCode(boolean flag) {
    view.getWidget("Code").setReadOnly(flag); //$NON-NLS-1$
  }

}
