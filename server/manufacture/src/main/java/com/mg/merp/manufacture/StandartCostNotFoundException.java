/*
 * StandartCostNotFoundException.java
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

import com.mg.framework.api.BusinessException;
import com.mg.merp.manufacture.support.Messages;
import com.mg.merp.reference.model.Catalog;

/**
 * ИС генерируется если не найдена нормативная цена для позоции каталога
 *
 * @author Oleg V. Safonov
 * @version $Id: StandartCostNotFoundException.java,v 1.1 2007/07/30 10:28:17 safonov Exp $
 */
public class StandartCostNotFoundException extends BusinessException {
  private Catalog catalog;

  public StandartCostNotFoundException(Catalog catalog) {
    super("Standart cost not found");
    this.catalog = catalog;
  }

  /* (non-Javadoc)
   * @see java.lang.Throwable#getLocalizedMessage()
   */
  @Override
  public String getLocalizedMessage() {
    return Messages.getInstance().getMessage(Messages.STANDART_COST_NOT_FOUND, new Object[]{catalog.getCode()});
  }

}
