/*
 * WarehouseDocumentSpecInServiceBean.java
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

import com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean;
import com.mg.merp.warehouse.WarehouseDocumentHeadInServiceLocal;
import com.mg.merp.warehouse.WarehouseDocumentSpecInServiceLocal;
import com.mg.merp.warehouse.model.StockDocumentSpec;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Спецификация приходных ордеров"
 *
 * @author leonova
 * @version $Id: WarehouseDocumentSpecInServiceBean.java,v 1.6 2008/12/01 09:37:57 safonov Exp $
 */
@Stateless(name = "merp/warehouse/WarehouseDocumentSpecInService")
public class WarehouseDocumentSpecInServiceBean extends GoodsDocumentSpecificationServiceBean<StockDocumentSpec, Integer> implements WarehouseDocumentSpecInServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean#doAdjust(com.mg.merp.document.model.DocSpec)
   */
  @Override
  protected void doAdjust(StockDocumentSpec entity) {
    super.doAdjust(entity);
    entity.setSrcStock(null);
    entity.setSrcMol(null);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean#getDocSectionIdentifier()
   */
  @Override
  protected int getDocSectionIdentifier() {
    return WarehouseDocumentHeadInServiceLocal.DOCSECTION;
  }

}
