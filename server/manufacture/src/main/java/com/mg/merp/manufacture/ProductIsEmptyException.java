/*
 * ProductIsEmptyException.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.manufacture;

import com.mg.framework.api.BusinessException;
import com.mg.merp.manufacture.support.Messages;

/**
 * ИС генерируется если в ЗНП не указан продукт для производства
 *
 * @author safonov
 * @version $Id: ProductIsEmptyException.java,v 1.2 2007/07/30 10:28:17 safonov Exp $
 */
public class ProductIsEmptyException extends BusinessException {

  public ProductIsEmptyException() {
    super("Product is empty");
  }

  /* (non-Javadoc)
   * @see java.lang.Throwable#getLocalizedMessage()
   */
  @Override
  public String getLocalizedMessage() {
    return Messages.getInstance().getMessage(Messages.JOB_PRODUCT_IS_EMPTY);
  }

}
