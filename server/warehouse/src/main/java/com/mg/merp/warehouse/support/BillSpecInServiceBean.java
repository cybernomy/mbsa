/*
 * BillSpecInServiceBean.java
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
import com.mg.merp.warehouse.BillHeadInServiceLocal;
import com.mg.merp.warehouse.BillSpecInServiceLocal;
import com.mg.merp.warehouse.model.BillSpec;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Спецификация входящих счетов"
 *
 * @author leonova
 * @version $Id: BillSpecInServiceBean.java,v 1.6 2008/12/01 09:37:57 safonov Exp $
 */
@Stateless(name = "merp/warehouse/BillSpecInService")
public class BillSpecInServiceBean extends GoodsDocumentSpecificationServiceBean<BillSpec, Integer> implements BillSpecInServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean#doAdjust(com.mg.merp.document.model.DocSpec)
   */
  @Override
  protected void doAdjust(BillSpec entity) {
    super.doAdjust(entity);
    entity.setSrcStock(null);
    entity.setSrcMol(null);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean#getDocSectionIdentifier()
   */
  @Override
  protected int getDocSectionIdentifier() {
    return BillHeadInServiceLocal.DOCSECTION;
  }

}
