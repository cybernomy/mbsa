/*
 * BillHeadOutServiceBean.java
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
import com.mg.merp.warehouse.BillHeadModelOutServiceLocal;
import com.mg.merp.warehouse.BillHeadOutServiceLocal;
import com.mg.merp.warehouse.BillSpecOutServiceLocal;
import com.mg.merp.warehouse.generic.AbstractWarehouseDocument;
import com.mg.merp.warehouse.model.BillHead;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Исходящие счета"
 *
 * @author leonova
 * @version $Id: BillHeadOutServiceBean.java,v 1.6 2007/03/27 13:36:47 safonov Exp $
 */
@Stateless(name = "merp/warehouse/BillHeadOutService")
public class BillHeadOutServiceBean extends AbstractWarehouseDocument<BillHead, Integer, BillHeadModelOutServiceLocal, BillSpecOutServiceLocal> implements BillHeadOutServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.DocumentServiceBean#getDocSectionIdentifier()
   */
  @Override
  protected int getDocSectionIdentifier() {
    return BillHeadOutServiceLocal.DOCSECTION;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.GoodsDocumentServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, BillHead entity) {
    super.onValidate(context, entity);

    context.addRule(new MandatoryAttribute(entity, "To"));
    context.addRule(new MandatoryAttribute(entity, "From"));
  }

}
