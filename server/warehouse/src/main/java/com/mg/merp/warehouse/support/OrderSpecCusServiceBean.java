/*
 * OrderSpecCusServiceBean.java
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

import com.mg.merp.document.CreateSpecificationInfo;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.warehouse.OrderHeadCusServiceLocal;
import com.mg.merp.warehouse.OrderSpecCusServiceLocal;
import com.mg.merp.warehouse.generic.AbstractWarehouseDocumentSpec;
import com.mg.merp.warehouse.model.OrderSpec;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Спецификации заказов покупателей"
 *
 * @author leonova
 * @version $Id: OrderSpecCusServiceBean.java,v 1.7 2007/09/07 12:27:13 safonov Exp $
 */
@Stateless(name = "merp/warehouse/OrderSpecCusService")
public class OrderSpecCusServiceBean extends AbstractWarehouseDocumentSpec<OrderSpec> implements OrderSpecCusServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean#getDocSectionIdentifier()
   */
  @Override
  protected int getDocSectionIdentifier() {
    return OrderHeadCusServiceLocal.DOCSECTION;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean#initializeForBulkCreate(com.mg.merp.document.model.DocHead, com.mg.merp.document.CreateSpecificationInfo)
   */
  @Override
  protected OrderSpec initializeForBulkCreate(DocHead docHead, CreateSpecificationInfo goodsInfo) {
    OrderSpec result = super.initializeForBulkCreate(docHead, goodsInfo);
    if (goodsInfo instanceof CreateOrderSpecificationInfoImpl)
      ((CreateOrderSpecificationInfoImpl) goodsInfo).initOrderSpec(result);
    return result;
  }

}
