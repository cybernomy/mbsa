/*
 * FacturaSpecOutServiceBean.java
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
import com.mg.merp.factura.FacturaHeadOutServiceLocal;
import com.mg.merp.factura.FacturaSpecOutServiceLocal;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Спецификация исходящих счет - фактур"
 *
 * @author leonova
 * @version $Id: FacturaSpecOutServiceBean.java,v 1.5 2007/02/06 17:01:02 safonov Exp $
 */
@Stateless(name = "merp/factura/FacturaSpecOutService")
public class FacturaSpecOutServiceBean extends GoodsDocumentSpecificationServiceBean<DocSpec, Integer> implements FacturaSpecOutServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean#getDocSectionIdentifier()
   */
  @Override
  protected int getDocSectionIdentifier() {
    return FacturaHeadOutServiceLocal.DOCSECTION;
  }

}
