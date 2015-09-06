/*
 * OrderSpecSupServiceBean.java
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
import com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.warehouse.OrderHeadSupServiceLocal;
import com.mg.merp.warehouse.OrderSpecSupServiceLocal;
import com.mg.merp.warehouse.model.OrderSpec;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Спецификация заказов поставщикам"
 *
 * @author leonova
 * @version $Id: OrderSpecSupServiceBean.java,v 1.7 2008/12/01 09:37:57 safonov Exp $
 */
@Stateless(name = "merp/warehouse/OrderSpecSupService")
public class OrderSpecSupServiceBean extends GoodsDocumentSpecificationServiceBean<OrderSpec, Integer> implements OrderSpecSupServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean#doAdjust(com.mg.merp.document.model.DocSpec)
   */
  @Override
  protected void doAdjust(OrderSpec entity) {
    super.doAdjust(entity);
    entity.setSrcStock(null);
    entity.setSrcMol(null);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean#getDocSectionIdentifier()
   */
  @Override
  protected int getDocSectionIdentifier() {
    return OrderHeadSupServiceLocal.DOCSECTION;
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
