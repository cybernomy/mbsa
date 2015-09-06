/*
 * FacturaSpecInServiceBean.java
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

package com.mg.merp.factura.support;

import com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.factura.FacturaHeadInServiceLocal;
import com.mg.merp.factura.FacturaSpecInServiceLocal;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Спецификация входящих счет - фактур"
 *
 * @author leonova
 * @version $Id: FacturaSpecInServiceBean.java,v 1.6 2008/12/01 09:37:56 safonov Exp $
 */
@Stateless(name = "merp/factura/FacturaSpecInService")
public class FacturaSpecInServiceBean extends GoodsDocumentSpecificationServiceBean<DocSpec, Integer> implements FacturaSpecInServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean#doAdjust(com.mg.merp.document.model.DocSpec)
   */
  @Override
  protected void doAdjust(DocSpec entity) {
    super.doAdjust(entity);
    entity.setSrcStock(null);
    entity.setSrcMol(null);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean#getDocSectionIdentifier()
   */
  @Override
  protected int getDocSectionIdentifier() {
    return FacturaHeadInServiceLocal.DOCSECTION;
  }

}
