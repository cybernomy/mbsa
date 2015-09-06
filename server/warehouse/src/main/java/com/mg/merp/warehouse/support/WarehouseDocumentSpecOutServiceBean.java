/*
 * WarehouseDocumentSpecOutServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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

import com.mg.merp.warehouse.WarehouseDocumentHeadOutServiceLocal;
import com.mg.merp.warehouse.WarehouseDocumentSpecOutServiceLocal;
import com.mg.merp.warehouse.generic.AbstractWarehouseDocumentSpec;
import com.mg.merp.warehouse.model.StockDocumentSpec;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Спецификация расходных ордеров"
 *
 * @author leonova
 * @version $Id: WarehouseDocumentSpecOutServiceBean.java,v 1.6 2007/09/07 12:27:13 safonov Exp $
 */
@Stateless(name = "merp/warehouse/WarehouseDocumentSpecOutService")
public class WarehouseDocumentSpecOutServiceBean extends AbstractWarehouseDocumentSpec<StockDocumentSpec> implements WarehouseDocumentSpecOutServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean#getDocSectionIdentifier()
   */
  @Override
  protected int getDocSectionIdentifier() {
    return WarehouseDocumentHeadOutServiceLocal.DOCSECTION;
  }

}
