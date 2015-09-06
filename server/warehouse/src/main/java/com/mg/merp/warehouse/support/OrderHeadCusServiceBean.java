/*
 * OrderHeadCusServiceBean.java
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

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.warehouse.OrderHeadCusServiceLocal;
import com.mg.merp.warehouse.OrderHeadModelCusServiceLocal;
import com.mg.merp.warehouse.OrderSpecCusServiceLocal;
import com.mg.merp.warehouse.generic.AbstractWarehouseDocument;
import com.mg.merp.warehouse.model.OrderDueDateKind;
import com.mg.merp.warehouse.model.OrderHead;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Заказы покупателей"
 *
 * @author leonova
 * @version $Id: OrderHeadCusServiceBean.java,v 1.9 2007/03/28 13:06:03 safonov Exp $
 */
@Stateless(name = "merp/warehouse/OrderHeadCusService")
public class OrderHeadCusServiceBean extends AbstractWarehouseDocument<OrderHead, Integer, OrderHeadModelCusServiceLocal, OrderSpecCusServiceLocal> implements OrderHeadCusServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.DocumentServiceBean#doAdjust(com.mg.merp.document.model.DocHead)
   */
  @Override
  protected void doAdjust(OrderHead entity) {
    super.doAdjust(entity);
    if (entity.getDueDateKind() == null)
      entity.setDueDateKind(OrderDueDateKind.NONE);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.GoodsDocumentServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, OrderHead entity) {
    super.onValidate(context, entity);

    context.addRule(new MandatoryAttribute(entity, "To"));
    context.addRule(new MandatoryAttribute(entity, "From"));
  }

  /**
   * @ejb.interface-method view-type = "local"
   */
  public int createDocument(int[] orderList, int docsumentSection, int modelId, int folderId) throws ApplicationException {
    return 0;//((OrderHeadCusDomainImpl) getDataDomain()).createDocument(orderList, docsumentSection, modelId, folderId);
  }

  @Override
  protected int getDocSectionIdentifier() {
    return OrderHeadCusServiceLocal.DOCSECTION;
  }


}
