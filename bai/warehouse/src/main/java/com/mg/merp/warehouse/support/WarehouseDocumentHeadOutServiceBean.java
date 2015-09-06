/*
 * WarehouseDocumentHeadOutServiceBean.java
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
import com.mg.merp.warehouse.WarehouseDocumentHeadModelOutServiceLocal;
import com.mg.merp.warehouse.WarehouseDocumentHeadOutServiceLocal;
import com.mg.merp.warehouse.WarehouseDocumentSpecOutServiceLocal;
import com.mg.merp.warehouse.generic.AbstractWarehouseDocument;
import com.mg.merp.warehouse.model.StockDocumentHead;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Расходные ордера"
 *
 * @author leonova
 * @version $Id: WarehouseDocumentHeadOutServiceBean.java,v 1.6 2007/04/18 12:07:31 safonov Exp $
 */
@Stateless(name = "merp/warehouse/WarehouseDocumentHeadOutService")
public class WarehouseDocumentHeadOutServiceBean extends AbstractWarehouseDocument<StockDocumentHead, Integer, WarehouseDocumentHeadModelOutServiceLocal, WarehouseDocumentSpecOutServiceLocal> implements WarehouseDocumentHeadOutServiceLocal {

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
    return WarehouseDocumentHeadOutServiceLocal.DOCSECTION;
  }


}
