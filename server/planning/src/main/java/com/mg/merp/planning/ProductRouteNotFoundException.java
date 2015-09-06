/*
 * ProductRouteNotFoundException.java
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
package com.mg.merp.planning;

import com.mg.framework.api.BusinessException;
import com.mg.merp.planning.support.Messages;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.warehouse.model.Warehouse;

import javax.ejb.ApplicationException;

/**
 * ИС генерируется если не найден логистический маршрут
 *
 * @author Oleg V. Safonov
 * @version $Id: ProductRouteNotFoundException.java,v 1.1 2007/07/30 10:37:51 safonov Exp $
 */
@ApplicationException
public class ProductRouteNotFoundException extends BusinessException {
  private String catalog;
  private String warehouse;

  public ProductRouteNotFoundException(Catalog catalog, Warehouse warehouse) {
    super("product route not found");
    this.catalog = catalog.getCode().trim();
    this.warehouse = warehouse.getCode().trim();
  }

  /* (non-Javadoc)
   * @see java.lang.Throwable#getLocalizedMessage()
   */
  @Override
  public String getLocalizedMessage() {
    return Messages.getInstance().getMessage(Messages.PRODUCT_ROUTE_NOT_FOUND, new Object[]{catalog, warehouse});
  }

}
