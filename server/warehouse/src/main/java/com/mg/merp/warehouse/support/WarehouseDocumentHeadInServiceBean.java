/*
 * WarehouseDocumentHeadInServiceBean.java
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

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.warehouse.WarehouseDocumentHeadInServiceLocal;
import com.mg.merp.warehouse.WarehouseDocumentHeadModelInServiceLocal;
import com.mg.merp.warehouse.WarehouseDocumentSpecInServiceLocal;
import com.mg.merp.warehouse.generic.AbstractWarehouseDocument;
import com.mg.merp.warehouse.model.StockDocumentHead;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Приходные ордера"
 *
 * @author leonova
 * @version $Id: WarehouseDocumentHeadInServiceBean.java,v 1.7 2008/11/24 12:55:53 safonov Exp $
 */
@Stateless(name = "merp/warehouse/WarehouseDocumentHeadInService")
public class WarehouseDocumentHeadInServiceBean extends AbstractWarehouseDocument<StockDocumentHead, Integer, WarehouseDocumentHeadModelInServiceLocal, WarehouseDocumentSpecInServiceLocal> implements WarehouseDocumentHeadInServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.DocumentServiceBean#doAdjust(com.mg.merp.document.model.DocHead)
   */
  @Override
  protected void doAdjust(StockDocumentHead entity) {
    super.doAdjust(entity);
    entity.setSrcStock(null);
    entity.setSrcMol(null);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.GoodsDocumentServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, StockDocumentHead entity) {
    super.onValidate(context, entity);

    context.addRule(new MandatoryAttribute(entity, "To"));
    context.addRule(new MandatoryAttribute(entity, "From"));
  }

  @Override
  protected int getDocSectionIdentifier() {
    return WarehouseDocumentHeadInServiceLocal.DOCSECTION;
  }

}
